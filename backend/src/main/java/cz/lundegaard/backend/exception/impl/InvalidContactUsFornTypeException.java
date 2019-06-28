package cz.lundegaard.backend.exception.impl;

public class InvalidContactUsFornTypeException extends Exception {

    public InvalidContactUsFornTypeException(String message) {
        super(message);
    }

    public InvalidContactUsFornTypeException(String message, Throwable cause) {
        super(message, cause);
    }

}
