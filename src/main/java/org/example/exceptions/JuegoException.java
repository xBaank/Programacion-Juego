package org.example.exceptions;

import java.rmi.server.ExportException;

public class JuegoException extends IllegalStateException {
    public JuegoException (String message) {
        super(message);
    }
}
