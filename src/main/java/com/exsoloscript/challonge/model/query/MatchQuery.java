package com.exsoloscript.challonge.model.query;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.Validate;

/**
 * Query for updating a match. This class can be accessed using it's builder.
 *
 * @author EXSolo
 * @version 20160819.1
 */
public class MatchQuery {

    @SerializedName("winner_id")
    private Integer winnerId;

    @SerializedName("player1_votes")
    private Integer votesForPlayer1;

    @SerializedName("player2_votes")
    private Integer votesForPlayer2;

    @SerializedName("scores_csv")
    private String scoresCsv;

    private MatchQuery(int winnerId, int votesForPlayer1, int votesForPlayer2, String scoresCsv) {
        this.winnerId = winnerId;
        this.votesForPlayer1 = votesForPlayer1;
        this.votesForPlayer2 = votesForPlayer2;
        this.scoresCsv = scoresCsv;
    }

    /**
     * The participant ID of the winner or "tie" if applicable (Round Robin and Swiss).
     * NOTE: If you change the outcome of a completed match, all matches in the bracket
     * that branch from the updated match will be reset.
     */
    public Integer winnerId() {
        return winnerId;
    }

    /**
     * Overwrites the number of votes for player 1
     */
    public Integer votesForPlayer1() {
        return votesForPlayer1;
    }

    /**
     * Overwrites the number of votes for player 2
     */
    public Integer votesForPlayer2() {
        return votesForPlayer2;
    }

    /**
     * Comma separated set/game scores with player 1 score first (e.g. "1-3,3-0,3-2")
     */
    public String scoresCsv() {
        return scoresCsv;
    }

    public static class Builder {
        private Integer winnerId;
        private Integer votesForPlayer1;
        private Integer votesForPlayer2;
        private String scoresCsv;

        public Builder setWinnerId(int winnerId) {
            this.winnerId = winnerId;
            return this;
        }

        public Builder setVotesForPlayer1(int votesForPlayer1) {
            this.votesForPlayer1 = votesForPlayer1;
            return this;
        }

        public Builder setPlayer2Votes(int player2Votes) {
            this.votesForPlayer2 = player2Votes;
            return this;
        }

        public Builder setScoresCsv(String scoresCsv) {
            this.scoresCsv = scoresCsv;
            return this;
        }

        /**
         * Build the builder
         *
         * If you're updating winner_id, scores_csv must also be provided.
         * You may, however, update score_csv without providing winner_id
         * for live score updates.
         *
         * @return The built MatchQuery
         */
        public MatchQuery build() {
            if (this.winnerId != null) {
                Validate.notNull(this.scoresCsv, "If you're updating winnerId, scoresCsv must also be provided. You may, however, update scoreCsv without providing winnerId for live score updates.");
            }
            return new MatchQuery(winnerId, votesForPlayer1, votesForPlayer2, scoresCsv);
        }
    }
}
