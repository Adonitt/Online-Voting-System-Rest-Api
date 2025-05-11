package org.example.kqz.exceptions;

public class PersonalNumberAlreadyExists extends RuntimeException {
    public PersonalNumberAlreadyExists(String message) {
        super(message);
    }
}
