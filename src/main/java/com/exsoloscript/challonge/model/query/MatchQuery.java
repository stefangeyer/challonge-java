package com.exsoloscript.challonge.model.query;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Query for updating a match. This class can be accessed using it's builder.
 *
 * @author EXSolo
 * @version 20160819.1
 */
@Data
@Accessors(fluent = true)
@Builder
public class MatchQuery {
    @SerializedName("winner_id")
    private String winnerId;
    @SerializedName("player1_votes")
    private Integer votesForPlayer1;
    @SerializedName("player2_votes")
    private Integer votesForPlayer2;
    @SerializedName("scores_csv")
    private String scoresCsv;
}
