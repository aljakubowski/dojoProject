package com.dojo1.dojobf.exceptions;

import lombok.Data;

@Data
public class JokeNotFoundError {

    private int status;
    private String message;

    public JokeNotFoundError() {
    }
}
