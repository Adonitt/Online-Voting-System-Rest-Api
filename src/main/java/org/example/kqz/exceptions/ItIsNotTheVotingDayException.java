package org.example.kqz.exceptions;

public class ItIsNotTheVotingDayException extends RuntimeException {
    public ItIsNotTheVotingDayException(String message) {
        super(message);
    }
}
