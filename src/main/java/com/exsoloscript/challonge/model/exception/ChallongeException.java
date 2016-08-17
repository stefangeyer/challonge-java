package com.exsoloscript.challonge.model.exception;

import java.util.List;

public class ChallongeException extends Exception {

    private List<String> errors;

    @Override
    public String getMessage() {
        return "The following errors were reported after the latest Api call: " + this.errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
