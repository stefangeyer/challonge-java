package com.exsoloscript.challonge.model.enumeration.query;

import java.util.Arrays;
import java.util.Optional;

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
        Optional<GrandFinalsModifier> optType = Arrays.stream(values()).filter(type -> name.toLowerCase().equals(type.toString())).findFirst();
        return optType.isPresent() ? optType.get() : null;
    }

    @Override
    public String toString() {
        return this.lowerCase;
    }
}