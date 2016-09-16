package com.exsoloscript.challonge.model.enumeration.query;

import com.google.gson.annotations.SerializedName;

/**
 * Represents the different states that can be queried.
 * This enumeration does not represent the actual state of the tournament,
 * but groups a list ouf tournaments to subgroups.
 *
 * @author EXSolo
 * @version 20160820.1
 */
public enum TournamentQueryState {
    @SerializedName("all")
    ALL,
    @SerializedName("pending")
    PENDING,
    @SerializedName("in_progress")
    IN_PROGRESS,
    @SerializedName("ended")
    ENDED
}
