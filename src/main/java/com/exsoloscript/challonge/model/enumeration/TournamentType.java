package com.exsoloscript.challonge.model.enumeration;

import java.util.Arrays;
import java.util.Optional;

public enum TournamentType {
    SINGLE_ELIMINATION("single elimination"),
    DOUBLE_ELIMINATION("double elimination"),
    ROUND_ROBIN("round robin"),
    SWISS("swiss");

    private String lowerCase;

    TournamentType(String lowerCase) {
        this.lowerCase = lowerCase;
    }

    public static TournamentType fromString(String name) {
        Optional<TournamentType> optType = Arrays.stream(values()).filter(type -> type.toString().equals(name.toLowerCase())).findFirst();
        return optType.isPresent() ? optType.get() : null;
    }

    @Override
    public String toString() {
        return this.lowerCase;
    }
}