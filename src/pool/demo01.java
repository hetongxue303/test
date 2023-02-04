package pool;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

/**
 * @author 何同学
 */
@SuppressWarnings("all ")
public class demo01 {
    public static void main(String... args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        User user = new User();
        System.out.println(user.getClass().hashCode());
        System.out.println(Class.forName("pool.User").hashCode());
        System.out.println(User.class.hashCode());
        Annotation[] annotations = User.class.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
//        Anno anno = User.class.getAnnotation(Anno.class);
//        AnnoEnum value = anno.value();
//        if (value == AnnoEnum.OK) {
//            System.out.println("ok");
//        } else if (value == AnnoEnum.FAILED) {
//            System.out.println("failed");
//        } else {
//            System.out.println("error");
//        }
//        System.out.println(anno.value());
        Anno id = User.class.getDeclaredField("id").getAnnotation(Anno.class);
        Anno name = User.class.getDeclaredField("name").getAnnotation(Anno.class);
        System.out.println(id.value());
        System.out.println(name.value());

    }

}
