package com.exsoloscript.challonge.model.enumeration.query;

/**
 * Represents the different states that can be queried.
 * This enumeration does not represent the actual state of the tournament,
 * but groups a list ouf tournaments to subgroups.
 *
 * @author EXSolo
 * @version 20160820.1
 */
public enum TournamentQueryState {
    ALL("all"),
    PENDING("pending"),
    IN_PROGRESS("in_progress"),
    ENDED("ended");

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
