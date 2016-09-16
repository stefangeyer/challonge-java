package com.exsoloscript.challonge.model.enumeration;

import com.google.gson.annotations.SerializedName;

public enum TournamentState {
    @SerializedName("checking_in")
    CHECKING_IN,
    @SerializedName("checked_in")
    CHECKED_IN,
    @SerializedName("pending")
    PENDING,
    @SerializedName("underway")
    UNDERWAY,
    @SerializedName("group_stages_underway")
    GROUP_STAGES_UNDERWAY,
    @SerializedName("group_stages_finalized")
    GROUP_STAGES_FINALIZED,
    @SerializedName("awaiting_review")
    AWAITING_REVIEW,
    @SerializedName("complete")
    COMPLETE
}
