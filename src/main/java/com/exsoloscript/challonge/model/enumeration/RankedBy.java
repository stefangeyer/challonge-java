package com.exsoloscript.challonge.model.enumeration;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Optional;

/**
 * This enumeration represents the available tournament types.
 * <p>
 * More information about the ranking systems can be found <a href="http://feedback.challonge.com/knowledgebase/articles/448540-rank-tie-break-statistics">here</a>
 *
 * @author EXSolo
 * @version 20160820.1
 */
public enum RankedBy {
    @SerializedName("match wins")
    MATCH_WINS,
    @SerializedName("game wins")
    GAME_WINS,
    @SerializedName("point scored")
    POINT_SCORED,
    @SerializedName("points difference")
    POINTS_DIFFERENCE,
    @SerializedName("custom")
    CUSTOM
}