package pool;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author 何同学
 */
public class demo01 {
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        User user1 = new User(1, "A", 20, false);
        User user2 = new User("val");
        System.out.println(user1.getClass().getPackage().getName());
        System.out.println("============================");
        Constructor<User> constructor = User.class.getConstructor(Integer.class, String.class, Integer.class, Boolean.class);
        System.out.println(constructor.newInstance(2, "B", 23, false));
    }
}
