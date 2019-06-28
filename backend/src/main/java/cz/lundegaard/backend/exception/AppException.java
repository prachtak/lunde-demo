package cz.lundegaard.backend.exception;

import cz.lundegaard.backend.annotation.ExceptionLabel;
import lombok.Getter;

import java.util.Optional;

import org.springframework.core.annotation.AnnotationUtils;

/**
 * Main business exception. Every exception derived from this parent
 * will have <code>getLabel</code> method available.
 */
@Getter
public abstract class AppException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected String label;

    public AppException() {
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Throwable cause) {
        super(cause);
    }

    public AppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    {
        Optional.ofNullable(AnnotationUtils.findAnnotation(this.getClass(), ExceptionLabel.class))
                .ifPresent(a -> this.label = a.value());
    }

}
