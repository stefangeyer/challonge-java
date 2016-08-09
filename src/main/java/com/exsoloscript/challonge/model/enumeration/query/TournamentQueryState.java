package com.exsoloscript.challonge.model.enumeration.query;

public enum TournamentQueryState {
    ALL("all"), PENDING("pending"), IN_PROGRESS("in_progress"), ENDED("ended");

    private String lowerCase;

    TournamentQueryState(String lowerCase) {
        this.lowerCase = lowerCase;
    }

    public static TournamentQueryState fromString(String name) {
        return valueOf(name.toUpperCase());
    }

    @Override
    public String toString() {
        return this.lowerCase;
    }
}
