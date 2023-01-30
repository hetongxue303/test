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