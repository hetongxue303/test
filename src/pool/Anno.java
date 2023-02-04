package pool;

import java.lang.annotation.*;

/**
 * @author 何同学
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Anno {

    AnnoEnum value() default AnnoEnum.OK;

}