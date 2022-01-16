package org.example.exceptions;

/**
 * Custom Exception to differenciate from other exceptions
 */
public class JuegoException extends IllegalStateException {
    public JuegoException (String message) {
        super(message);
    }
}
