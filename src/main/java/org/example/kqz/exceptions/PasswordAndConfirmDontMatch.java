package org.example.kqz.exceptions;

public class PasswordAndConfirmDontMatch extends RuntimeException {
    public PasswordAndConfirmDontMatch(String message) {
        super(message);
    }
}
