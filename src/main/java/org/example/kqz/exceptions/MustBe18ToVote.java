package org.example.kqz.exceptions;

public class MustBe18ToVote extends RuntimeException {
    public MustBe18ToVote(String message) {
        super(message);
    }
}
