package com.dojo1.dojobf.exceptions;

public class JokeNotFoundException extends RuntimeException {

    public JokeNotFoundException(String message) {
        super(message);
    }
}