package com.exsoloscript.challonge.model.enumeration;

public enum MatchState {
    ALL("all"), PENDING("pending"), OPEN("open"), COMPLETE("complete");

    private String lowerCase;

    MatchState(String lowerCase) {
        this.lowerCase = lowerCase;
    }

    @Override
    public String toString() {
        return this.lowerCase;
    }
}
