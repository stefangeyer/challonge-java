package com.exsoloscript.challonge.model.enumerations.query;

public enum GrandFinalsModifier {
    BLANK(null),
    SINGLE_MATCH("single match"),
    SKIP("skip");

    private String lowerCase;

    GrandFinalsModifier(String lowerCase) {
        this.lowerCase = lowerCase;
    }

    public static GrandFinalsModifier fromString(String name) {
        return valueOf(name.toUpperCase());
    }

    @Override
    public String toString() {
        return this.lowerCase;
    }
}