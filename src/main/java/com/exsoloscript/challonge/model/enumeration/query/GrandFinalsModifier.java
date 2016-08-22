package com.exsoloscript.challonge.model.enumeration.query;

/**
 * Type of the grand finals in a double elimination tournament.
 * <p>
 * This option only affects double elimination.
 * <p>
 * null/blank (default) - give the winners bracket finalist two chances to beat the losers bracket finalist<br>
 * 'single match' - create only one grand finals match<br>
 * 'skip' - don't create a finals match between winners and losers bracket finalists
 * </p>
 *
 * @author EXSolo
 * @version 20160820.1
 */
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