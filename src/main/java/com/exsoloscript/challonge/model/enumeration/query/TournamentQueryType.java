package com.exsoloscript.challonge.model.enumeration.query;

//TODO might be obsolete
public enum TournamentQueryType {
    SINGLE_ELIMINATION("single elimination"), DOUBLE_ELIMINATION("double elimination"), ROUND_ROBIN("round robin"), SWISS("swiss");

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
