package com.exsoloscript.challonge.model.enumeration;

import com.google.gson.annotations.SerializedName;

/**
 * This enumeration represents the available tournament types
 *
 * @author EXSolo
 * @version 20160820.1
 */
public enum TournamentType {
    @SerializedName("single elimination")
    SINGLE_ELIMINATION,
    @SerializedName("double elimination")
    DOUBLE_ELIMINATION,
    @SerializedName("round robin")
    ROUND_ROBIN,
    @SerializedName("swiss")
    SWISS
}