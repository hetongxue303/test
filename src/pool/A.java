package pool;

/**
 * @author 何同学
 */
public abstract class A<T, R> implements I<T, R> {

    abstract R test(T t);

    @Override
    public R computed(T t) {
        return test(t);
    }
}