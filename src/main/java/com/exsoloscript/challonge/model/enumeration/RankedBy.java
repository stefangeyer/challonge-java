package com.exsoloscript.challonge.model.enumeration;

import java.util.Arrays;
import java.util.Optional;

public enum RankedBy {
    MATCH_WINS("match wins"),
    GAME_WINS("game wins"),
    POINT_SCORED("point scored"),
    POINTS_DIFFERENCE("points difference"),
    CUSTOM("custom");

    private String lowerCase;

    RankedBy(String lowerCase) {
        this.lowerCase = lowerCase;
    }

    public static RankedBy fromString(String name) {
        Optional<RankedBy> optRankedBy = Arrays.stream(values()).filter(rankedBy -> rankedBy.toString().equals(name.toLowerCase())).findFirst();
        return optRankedBy.isPresent() ? optRankedBy.get() : null;
    }

    @Override
    public String toString() {
        return this.lowerCase;
    }
}