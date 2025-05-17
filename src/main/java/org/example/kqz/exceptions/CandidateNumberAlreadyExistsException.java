package org.example.kqz.exceptions;

public class CandidateNumberAlreadyExistsException extends RuntimeException {
    public CandidateNumberAlreadyExistsException(String message) {
        super(message);
    }
}
