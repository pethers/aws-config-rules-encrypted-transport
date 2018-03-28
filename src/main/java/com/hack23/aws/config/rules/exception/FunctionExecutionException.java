package com.hack23.aws.config.rules.exception;

public class FunctionExecutionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FunctionExecutionException(String message) {
        super(message);
    }
}
