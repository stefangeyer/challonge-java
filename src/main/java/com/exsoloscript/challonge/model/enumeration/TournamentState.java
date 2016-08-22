package com.exsoloscript.challonge.model.enumeration;

public enum TournamentState {
    CHECKING_IN("checking_in"),
    CHECKED_IN("checked_in"),
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
