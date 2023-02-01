import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 独占锁(写锁)：一次只能被一个线程写入
 * 共享锁(读锁)：多个线程可以同时占有
 */
public class demo {

    public static void main(String[] args) {
        Cache cache = new Cache();
        for (int i = 0; i <= 5; i++) {
            int finalI = i;
            new Thread(() -> cache.put(String.valueOf(finalI), finalI), String.valueOf(i)).start();
        }
        for (int i = 0; i <= 5; i++) {
            int finalI = i;
            new Thread(() -> cache.get(String.valueOf(finalI)), String.valueOf(i)).start();
        }

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
