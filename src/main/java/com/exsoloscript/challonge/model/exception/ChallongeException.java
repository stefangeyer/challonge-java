package com.exsoloscript.challonge.model.exception;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * This exception is being used to represent errors received by the Challonge REST API
 *
 * @author EXSolo
 * @version 20160820.1
 */
public class ChallongeException extends Exception {

    private List<String> errors;

    public ChallongeException(String... errors) {
        this.errors = Lists.newArrayList(errors);
    }

    @Override
    public String getMessage() {
        return this.errors.toString();
    }

    public List<String> getErrors() {
        return errors;
    }
}
