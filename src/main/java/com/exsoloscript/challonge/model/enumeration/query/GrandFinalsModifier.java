package com.exsoloscript.challonge.model.enumeration.query;

import com.google.gson.annotations.SerializedName;

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
    @SerializedName("")
    BLANK,
    @SerializedName("single match")
    SINGLE_MATCH,
    @SerializedName("skip")
    SKIP
}