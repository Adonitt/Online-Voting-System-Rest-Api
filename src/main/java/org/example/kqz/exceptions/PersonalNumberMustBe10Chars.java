package org.example.kqz.exceptions;

public class PersonalNumberMustBe10Chars extends RuntimeException {
    public PersonalNumberMustBe10Chars(String message) {
        super(message);
    }
}
