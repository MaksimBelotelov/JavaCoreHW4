package org.example.Ex01;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {
        super();
    }

    public WrongPasswordException(String message) {
        super(message);
    }
}
