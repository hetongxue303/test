package pool;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author 何同学
 */
@FunctionalInterface
public interface I<T, R> {
    static <T> Function<T, T> identity() {
        return t -> t;
    }

    R computed(T t);

    default <M> I<M, R> compose(I<? super M, ? extends T> before) {
        Objects.requireNonNull(before);
        return (M m) -> computed(before.computed(m));
    }

    default <M> Function<T, M> andThen(Function<? super R, ? extends M> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(computed(t));
    }
}