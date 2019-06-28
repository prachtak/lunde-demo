package cz.lundegaard.backend.controller.advice;

import cz.lundegaard.backend.exception.DetailAware;
import cz.lundegaard.backend.model.common.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.function.BiConsumer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Base exception handler class containing helper methods and provides an unified
 * approach how to log exceptions.
 */
@Slf4j
public abstract class AbstractExceptionHandler {

    /**
     * Returns Mono of {@link ResponseEntity} with {@link ErrorResponse}
     *
     * @param status         Http status dependeing on exception
     * @param message        Fills <code>message</code> field in a common object, for business exceptions,
     *                       {@link cz.lundegaard.backend.exception.AppException#getLabel()} should be used to obtain value for this argument
     * @param e              Caught exception
     * @param doWithResponse Consumer with purpose mainly for logging
     * @return Error object
     */
    protected Mono<ResponseEntity<ErrorResponse>> response(HttpStatus status, String message, Throwable e, BiConsumer<ErrorResponse, Throwable> doWithResponse) {
        return Mono.just(ResponseEntity.status(status).body(
                new ErrorResponse(message, e instanceof DetailAware
                        ? ((DetailAware) e).getDetail().orElse(e.getMessage())
                        : e.getMessage()
                )
        )).doOnSuccess(r -> doWithResponse.accept(r.getBody(), e));
    }

    /**
     * Logs exception in {@link org.slf4j.event.Level#WARN} level
     * when stacktrace is visible only if debug level is enabled.
     *
     * @return BiConsumer
     */
    protected BiConsumer<ErrorResponse, Throwable> logWarn() {
        return (r, e) -> {
            log.warn("{} [TraceID: {}]", e.getMessage(), r.getTraceId());
            log.debug(e.getMessage(), e);
        };
    }

    /**
     * Logs exception in {@link org.slf4j.event.Level#ERROR} level
     * when stacktrace is always visible.
     *
     * @return
     */
    protected BiConsumer<ErrorResponse, Throwable> logError() {
        return (r, e) -> log.error("{} [UUID: {}]", e.getMessage(), r.getTraceId(), e);
    }

}
