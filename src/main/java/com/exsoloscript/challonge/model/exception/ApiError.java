package com.exsoloscript.challonge.model.exception;

import java.util.List;

public class ApiError {

    private List<String> errors;

    public List<String> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "errors=" + errors +
                '}';
    }
}