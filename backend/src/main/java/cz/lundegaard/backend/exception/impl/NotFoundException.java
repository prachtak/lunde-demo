package cz.lundegaard.backend.exception.impl;

import cz.lundegaard.backend.annotation.ExceptionLabel;
import cz.lundegaard.backend.exception.AppException;

/**
 * Generic exception which represents HTTP 404 - Not Found
 */
@ExceptionLabel("Requested Resource Not Found")
public class NotFoundException extends AppException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
