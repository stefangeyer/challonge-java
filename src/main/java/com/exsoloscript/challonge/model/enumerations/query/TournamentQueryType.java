package com.exsoloscript.challonge.model.enumerations.query;

public enum TournamentQueryType {
    SINGLE_ELIMINATION("single_elimination"), DOUBLE_ELIMINATION("double_elimination"), ROUND_ROBIN("round_robin"), SWISS("swiss");

    private String lowerCase;

    TournamentQueryType(String lowerCase) {
        this.lowerCase = lowerCase;
    }

    public static TournamentQueryType fromString(String name) {
        return valueOf(name.toUpperCase());
    }

    @Override
    public String toString() {
        return this.lowerCase;
    }
}
