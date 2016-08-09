package com.exsoloscript.challonge.model.enumerations;

public enum TournamentState {
    PENDING("pending"),
    UNDERWAY("underway"),
    GROUP_STAGES_UNDERWAY("group_stages_underway"),
    GROUP_STAGES_FINALIZED("group_stages_finalized"),
    AWAITING_REVIEW("awaiting_review"),
    COMPLETE("complete");

    private String lowerCase;

    TournamentState(String lowerCase) {
        this.lowerCase = lowerCase;
    }

    public static TournamentState fromString(String name) {
        return valueOf(name.toUpperCase());
    }

    @Override
    public String toString() {
        return this.lowerCase;
    }
}
