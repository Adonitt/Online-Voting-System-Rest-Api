package org.example.kqz.exceptions;

public class UserCannotBeDeletedException extends RuntimeException {
    public UserCannotBeDeletedException(String message) {
        super(message);
    }
}
