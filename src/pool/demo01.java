package pool;

/**
 * @author 何同学
 */
public class demo01 {
    public static void main(String[] args) {
        A<String, String> a = new A<String, String>() {
            @Override
            String test(String s) {
                return s + "hello";
            }
        };
        System.out.println(a.computed("123"));
    }
}
