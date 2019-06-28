package cz.lundegaard.backend.exception.impl;

import cz.lundegaard.backend.annotation.ExceptionLabel;
import cz.lundegaard.backend.exception.AppException;

@ExceptionLabel("Resource already exists")
public class AlreadyExistsException extends AppException {

    private static final long serialVersionUID = 1L;

    public AlreadyExistsException(String msg) {
        super(msg);
    }
}
