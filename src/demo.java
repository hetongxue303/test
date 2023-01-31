import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class demo {

    public static void main(String[] args) {
        /*
         * ArrayList默认不安全
         * 解决方案：
         * 1.使用Vector(不推荐)
         * 2.Collections.synchronizedList(new ArrayList<>())
         * 3.new CopyOnWriteArrayList<>()
         */
//        List<String> list = new ArrayList<>();
//        List<String> list = new Vector<>();
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

        Set<Object> objects = new HashSet<>();

    }

}

class Phone {

    public synchronized void sendEmail() {
        System.out.println("发邮件...");
    }

    public synchronized void call() {
        System.out.println("打电话...");
    }

}