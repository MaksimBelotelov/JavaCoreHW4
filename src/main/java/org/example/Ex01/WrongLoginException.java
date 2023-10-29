package org.example.Ex01;

public class WrongLoginException extends RuntimeException {
    public WrongLoginException() {
        super();
    }

    public WrongLoginException(String message) {
        super(message);
    }
}
