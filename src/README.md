# java多线程

## 如何创建多线程？

- 继承Thread类

```java
public class demo implements Runnable {

    public static void main(String[] args) {
        new Thread(new demo()).start();
        new Thread(new demo()).start();
        // 使用匿名内部类实现
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("(匿名内部类)当前线程：" + Thread.currentThread().getName());
            }
        }).start();
        // 简化为lambda表达式
        new Thread(() -> System.out.println("(匿名内部类-lambda)当前线程：" + Thread.currentThread().getName())).start();
    }

    @Override
    public void run() {
        System.out.println("当前线程：" + Thread.currentThread().getName());
    }

}
```

- 实现Runnable接口

```java
public class demo implements Runnable {

    public static void main(String[] args) {
        new Thread(new demo()).start();// 第一个线程
        new Thread(new demo()).start();// 第二个线程
    }

    @Override
    public void run() {
        System.out.println("当前线程：" + Thread.currentThread().getName());
    }

}
```

- 实现Callable接口



## 线程优先级

参数：`MIN_PRIORITY(最低优先级)`、`NORM_PRIORITY(标准)`、`MAX_PRIORITY(最大优先级)`
方法：`setPriority()`
注意：并不能100%保证线程优先级，因为java使用CPU资源是抢占式的。

```java
public class demo {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> System.out.println("当前线程：" + Thread.currentThread().getName()));
//            thread.setPriority(Thread.MIN_PRIORITY);
//            thread.setPriority(Thread.NORM_PRIORITY);
            thread.setPriority(Thread.MAX_PRIORITY);
            thread.start();
        }
    }

}
```

## 线程睡眠

```java
public class demo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("开始");
        Thread.sleep(1000);// 单位：毫秒
        System.out.println("结束");
    }

}
```

## wait等待 和 notify通知

wait和notify是object的方法，所以所有的java类都有此方法；
wait：使当前线程等待直到被唤醒
notify：通知(唤醒)线程

```java
public class demo {

    public static void main(String[] args) {
        String data = "数据";
        new Thread(() -> {
            System.out.println("A线程开始等待");
            // 使用 synchronized 加锁
            synchronized (data) {
                System.out.println("A线程正在释放锁...");
                try {
                    // 等待
                    data.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("A线程结束");
            }
            System.out.println("A结束等待");
        }).start();

        new Thread(() -> {
            System.out.println("B线程准备开始...");
            synchronized (data) {
                System.out.println("B线程拿到锁...通知...(自己并不会释放锁)");
                // 通知
                data.notify();
//                data.notifyAll();
            }
            System.out.println("B线程结束");
        }).start();

        System.out.println("main线程结束");
    }

}
```

## 线程让步

```java
public class demo {

    public static void main(String[] args) {
        /*
         * 假设遇到偶数线程时进行让步，但由于java是抢占式 虽然让步 但运行太快依旧无法完美实现让步
         */
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                if (finalI % 2 == 0) {
                    Thread.yield();
                }
                System.out.println(Thread.currentThread().getName());
            }).start();
        }
    }

}
```

## 等待线程结束

```java
public class demo {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        Thread t2 = new Thread(() -> {
            try {
                t1.join();// 等待t1执行结束后在执行
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                t2.join();// 等待t2执行结束后在执行
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t4 = new Thread(() -> {
            try {
                t3.join();// 等待t3执行结束后在执行
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

}
```

## 中断线程

```java
public class demo {

    public static void main(String[] args) {
        /*
         * 使其沉睡100秒 通过使用 interrupt 使其在1秒后中断沉睡
         */
        Object object = new Object();
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(100 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("沉睡结束");
        });
        t1.start();
        try {
            Thread.sleep(1000);
            t1.interrupt();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
```