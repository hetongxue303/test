import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 独占锁(写锁)：一次只能被一个线程写入
 * 共享锁(读锁)：多个线程可以同时占有
 */
public class demo {

    public static void main(String[] args) throws InterruptedException {

        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                synchronousQueue.put(1);
                synchronousQueue.put(2);
                synchronousQueue.put(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "A").start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName());
                System.out.println(synchronousQueue.take());
                System.out.println(synchronousQueue.take());
                System.out.println(synchronousQueue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }, "B").start();
    }

}

class Cache {
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Map<String, Object> map = new HashMap<>();

    public void put(String key, Object value) {
        try {
            readWriteLock.writeLock().lock();// 写入锁
            System.out.println(Thread.currentThread().getName() + "正在写入" + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        try {
            readWriteLock.readLock().lock();// 读锁
            System.out.println(Thread.currentThread().getName() + "正在读取" + key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取完成，结果：" + o);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
