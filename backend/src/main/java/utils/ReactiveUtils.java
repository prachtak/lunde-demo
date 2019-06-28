package utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;
import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ReactiveUtils {

    public static <T, R> Mono<R> map(T o, Function<T, Mono<R>>... options) {
        return Mono.defer(() -> {
            for (Function<T, Mono<R>> fun : options) {
                var result = fun.apply(o);
                if (result != null)
                    return result;
            }
            return Mono.empty();
        });
    }

    public static <T, R> Function<Object, Mono<R>> forType(Class<T> type, Function<T, Mono<R>> supplier) {
        return o -> {
            if (type.isInstance(o))
                return supplier.apply(type.cast(o));
            else
                return null;
        };
    }

    public static <T> Function<Object, Mono<T>> orDefault(Supplier<Mono<T>> supplier) {
        return o -> supplier.get();
    }

}
