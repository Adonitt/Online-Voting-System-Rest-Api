package org.example.kqz.exceptions;

public class PartyAlreadyExistsException extends RuntimeException {
    public PartyAlreadyExistsException(String message) {
        super(message);
    }
}
