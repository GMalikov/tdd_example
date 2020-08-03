package com.example;

public class MissingValueException extends RuntimeException {
    public MissingValueException(String message) {
        super(message);
    }
}
