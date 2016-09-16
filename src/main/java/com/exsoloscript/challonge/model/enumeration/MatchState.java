package com.exsoloscript.challonge.model.enumeration;

import com.google.gson.annotations.SerializedName;

/**
 * This enumeration represents the different states of a match.
 *
 * @author EXSolo
 * @version 20160820.1
 */
public enum MatchState {
    @SerializedName("all")
    ALL,
    @SerializedName("pending")
    PENDING,
    @SerializedName("open")
    OPEN,
    @SerializedName("complete")
    COMPLETE
}
