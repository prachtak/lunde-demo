package cz.lundegaard.backend.controller.advice;


import cz.lundegaard.backend.exception.AppException;
import cz.lundegaard.backend.exception.impl.AlreadyExistsException;
import cz.lundegaard.backend.exception.impl.NotFoundException;
import cz.lundegaard.backend.exception.impl.ValidationException;
import cz.lundegaard.backend.model.common.ErrorResponse;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;
import static utils.ReactiveUtils.*;

/**
 * Main application exception handler.
 */
@ControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler extends AbstractExceptionHandler {

    private static final String GENERIC_ERROR_DESCRIPTION = "Unexpected server error occurred.";

    /**
     * Handles HTTP 500 errors - Internal Server Error. Generic handler for all unexpected exceptions.
     *
     * @param e checked or unchecked exceptions
     * @return Error common
     */
    @ExceptionHandler(Exception.class)
    @SuppressWarnings("unchecked")
    public Mono<ResponseEntity<ErrorResponse>> handle500(Exception e) {
        return map(e,
                forType(AppException.class, ex -> response(INTERNAL_SERVER_ERROR, ex.getLabel(), e, logError())),
                orDefault(() -> response(INTERNAL_SERVER_ERROR, GENERIC_ERROR_DESCRIPTION, e, logError())));
    }

    /**
     * Handles exceptions associated with specific HTTP response status codes thrown by Spring.
     */
    @ExceptionHandler(ResponseStatusException.class)
    public Mono<ResponseEntity<ErrorResponse>> handle400(ResponseStatusException e) {
        var ex = Objects.requireNonNullElse(e.getRootCause(), e);
        return response(e.getStatus(), ex.getMessage(), ex, logWarn());
    }

    /**
     * Handles HTTP 400 errors - Bad Request (e.g. validations)
     *
     * @param e {@link AppException}
     * @return Error common
     */
    @ExceptionHandler({ ValidationException.class})
    public Mono<ResponseEntity<ErrorResponse>> handle400(AppException e) {
        return response(BAD_REQUEST, e.getLabel(), e, logWarn());
    }

    /**
     * Handles HTTP 404 errors - Not Found.
     *
     * @param e {@link AppException}
     * @return Error common
     */
    @ExceptionHandler({ NotFoundException.class })
    public Mono<ResponseEntity<ErrorResponse>> handle404(AppException e) {
        return response(NOT_FOUND, e.getLabel(), e, logWarn());
    }

    /**
     * Handles HTTP 409 errors - Conflict.
     *
     * @param e {@link AppException}
     * @return Error common
     */
    @ExceptionHandler({ AlreadyExistsException.class})
    public Mono<ResponseEntity<ErrorResponse>> handle409(AppException e) {
        return response(CONFLICT, e.getLabel(), e, logWarn());
    }

}
