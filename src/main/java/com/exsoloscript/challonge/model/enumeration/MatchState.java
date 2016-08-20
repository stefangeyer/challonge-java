package com.exsoloscript.challonge.model.enumeration;

/**
 * This enumeration represents the different states of a match.
 *
 * @author EXSolo
 * @version 20160820.1
 */
public enum MatchState {
    ALL("all"),
    PENDING("pending"),
    OPEN("open"),
    COMPLETE("complete");

    private String lowerCase;

    MatchState(String lowerCase) {
        this.lowerCase = lowerCase;
    }

    @Override
    public String toString() {
        return this.lowerCase;
    }
}
