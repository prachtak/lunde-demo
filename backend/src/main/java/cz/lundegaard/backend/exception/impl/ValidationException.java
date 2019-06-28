package cz.lundegaard.backend.exception.impl;

import cz.lundegaard.backend.annotation.ExceptionLabel;
import cz.lundegaard.backend.exception.AppException;
import cz.lundegaard.backend.exception.DetailAware;

import java.util.Objects;
import java.util.Optional;

@ExceptionLabel("Validation Error")
public class ValidationException extends AppException implements DetailAware {

    private static final long serialVersionUID = 1L;

    private final Optional<Object> detail;

    public ValidationException(String message) {
        super(message);
        detail = Optional.empty();
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
        detail = Optional.empty();
    }

    public ValidationException(Object detail) {
        Objects.requireNonNull(detail);
        this.detail = Optional.of(detail);
    }

    public ValidationException(String message, Object detail) {
        super(message);
        Objects.requireNonNull(detail);
        this.detail = Optional.of(detail);
    }

    public ValidationException(String message, Object detail, Throwable cause) {
        super(message, cause);
        Objects.requireNonNull(detail);
        this.detail = Optional.of(detail);
    }

    @Override
    public Optional<Object> getDetail() {
        return detail;
    }
}
