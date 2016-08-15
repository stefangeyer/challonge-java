package com.exsoloscript.challonge.model.query;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.Validate;

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

    public int getWinnerId() {
        return winnerId;
    }

    public int getVotesForPlayer1() {
        return votesForPlayer1;
    }

    public int getVotesForPlayer2() {
        return votesForPlayer2;
    }

    public String getScoresCsv() {
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

        public MatchQuery build() {
            if (this.winnerId != null) {
                Validate.notNull(this.scoresCsv, "If you're updating winnerId, scoresCsv must also be provided. You may, however, update scoreCsv without providing winnerId for live score updates.");
            }
            return new MatchQuery(winnerId, votesForPlayer1, votesForPlayer2, scoresCsv);
        }
    }
}
