package com.exsoloscript.challonge.model;

import com.exsoloscript.challonge.model.enumeration.MatchState;
import com.google.gson.annotations.SerializedName;

import java.time.OffsetDateTime;

/**
 * The POJO that will be mapped to the match requests by Gson
 *
 * @author EXSolo
 * @version 20160820.1
 */
public class Match {

    @SerializedName("attachment_count")
    private Integer attachmentCount;
    @SerializedName("createdAt")
    private OffsetDateTime createdAt;
    @SerializedName("group_id")
    private Integer groupId;
    @SerializedName("has_attachment")
    private Boolean hasAttachment;
    private Integer id;
    private String identifier;
    private String location;
    @SerializedName("loser_id")
    private Integer loserId;
    @SerializedName("winner_id")
    private Integer winnerId;
    @SerializedName("player1_id")
    private Integer player1Id;
    @SerializedName("player1_is_prereq_match_loser")
    private Boolean player1IsPrerequisiteMatchLoser;
    @SerializedName("player1_prereq_match_id")
    private Integer player1PrerequisiteMatchId;
    @SerializedName("player2_id")
    private Integer player2Id;
    @SerializedName("player2_is_prereq_match_loser")
    private Boolean player2IsPrerequisiteMatchLoser;
    @SerializedName("player2_prereq_match_id")
    private Integer player2PrerequisiteMatchId;
    private Integer round;
    @SerializedName("scheduled_time")
    private OffsetDateTime scheduledTime;
    @SerializedName("started_at")
    private OffsetDateTime startedAt;
    private MatchState state;
    @SerializedName("tournament_id")
    private Integer tournamentId;
    @SerializedName("underway_at")
    private OffsetDateTime underwayAt;
    @SerializedName("updatedAt")
    private OffsetDateTime updatedAt;
    @SerializedName("prerequisite_match_ids_csv")
    private String prerequisiteMatchIdsCsv;
    @SerializedName("scores_csv")
    private String scoresCsv;

    public Integer attachmentCount() {
        return attachmentCount;
    }

    public OffsetDateTime createdAt() {
        return createdAt;
    }

    public Integer groupId() {
        return groupId;
    }

    public Boolean hasAttachment() {
        return hasAttachment;
    }

    public Integer id() {
        return id;
    }

    public String identifier() {
        return identifier;
    }

    public String location() {
        return location;
    }

    public Integer loserId() {
        return loserId;
    }

    public Integer player1Id() {
        return player1Id;
    }

    public Boolean player1IsPrerequisiteMatchLoser() {
        return player1IsPrerequisiteMatchLoser;
    }

    public Integer player1PrerequisiteMatchId() {
        return player1PrerequisiteMatchId;
    }

    public Integer player2Id() {
        return player2Id;
    }

    public Boolean player2IsPrerequisiteMatchLoser() {
        return player2IsPrerequisiteMatchLoser;
    }

    public Integer player2PrerequisiteMatchId() {
        return player2PrerequisiteMatchId;
    }

    public Integer round() {
        return round;
    }

    public OffsetDateTime scheduledTime() {
        return scheduledTime;
    }

    public OffsetDateTime startedAt() {
        return startedAt;
    }

    public MatchState state() {
        return state;
    }

    public Integer tournamentId() {
        return tournamentId;
    }

    public OffsetDateTime underwayAt() {
        return underwayAt;
    }

    public OffsetDateTime updatedAt() {
        return updatedAt;
    }

    public String prerequisiteMatchIdsCsv() {
        return prerequisiteMatchIdsCsv;
    }

    public String scoresCsv() {
        return scoresCsv;
    }

    public Integer winnerId() {
        return winnerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Match match = (Match) o;

        if (attachmentCount != null ? !attachmentCount.equals(match.attachmentCount) : match.attachmentCount != null)
            return false;
        if (groupId != null ? !groupId.equals(match.groupId) : match.groupId != null) return false;
        if (hasAttachment != null ? !hasAttachment.equals(match.hasAttachment) : match.hasAttachment != null)
            return false;
        if (id != null ? !id.equals(match.id) : match.id != null) return false;
        if (identifier != null ? !identifier.equals(match.identifier) : match.identifier != null) return false;
        if (location != null ? !location.equals(match.location) : match.location != null) return false;
        if (loserId != null ? !loserId.equals(match.loserId) : match.loserId != null) return false;
        if (winnerId != null ? !winnerId.equals(match.winnerId) : match.winnerId != null) return false;
        if (player1Id != null ? !player1Id.equals(match.player1Id) : match.player1Id != null) return false;
        if (player1IsPrerequisiteMatchLoser != null ? !player1IsPrerequisiteMatchLoser.equals(match.player1IsPrerequisiteMatchLoser) : match.player1IsPrerequisiteMatchLoser != null)
            return false;
        if (player1PrerequisiteMatchId != null ? !player1PrerequisiteMatchId.equals(match.player1PrerequisiteMatchId) : match.player1PrerequisiteMatchId != null)
            return false;
        if (player2Id != null ? !player2Id.equals(match.player2Id) : match.player2Id != null) return false;
        if (player2IsPrerequisiteMatchLoser != null ? !player2IsPrerequisiteMatchLoser.equals(match.player2IsPrerequisiteMatchLoser) : match.player2IsPrerequisiteMatchLoser != null)
            return false;
        if (player2PrerequisiteMatchId != null ? !player2PrerequisiteMatchId.equals(match.player2PrerequisiteMatchId) : match.player2PrerequisiteMatchId != null)
            return false;
        if (round != null ? !round.equals(match.round) : match.round != null) return false;
        if (state != match.state) return false;
        if (tournamentId != null ? !tournamentId.equals(match.tournamentId) : match.tournamentId != null) return false;
        if (prerequisiteMatchIdsCsv != null ? !prerequisiteMatchIdsCsv.equals(match.prerequisiteMatchIdsCsv) : match.prerequisiteMatchIdsCsv != null)
            return false;
        return scoresCsv != null ? scoresCsv.equals(match.scoresCsv) : match.scoresCsv == null;

    }

    @Override
    public int hashCode() {
        int result = attachmentCount != null ? attachmentCount.hashCode() : 0;
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + (hasAttachment != null ? hasAttachment.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (identifier != null ? identifier.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (loserId != null ? loserId.hashCode() : 0);
        result = 31 * result + (winnerId != null ? winnerId.hashCode() : 0);
        result = 31 * result + (player1Id != null ? player1Id.hashCode() : 0);
        result = 31 * result + (player1IsPrerequisiteMatchLoser != null ? player1IsPrerequisiteMatchLoser.hashCode() : 0);
        result = 31 * result + (player1PrerequisiteMatchId != null ? player1PrerequisiteMatchId.hashCode() : 0);
        result = 31 * result + (player2Id != null ? player2Id.hashCode() : 0);
        result = 31 * result + (player2IsPrerequisiteMatchLoser != null ? player2IsPrerequisiteMatchLoser.hashCode() : 0);
        result = 31 * result + (player2PrerequisiteMatchId != null ? player2PrerequisiteMatchId.hashCode() : 0);
        result = 31 * result + (round != null ? round.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (tournamentId != null ? tournamentId.hashCode() : 0);
        result = 31 * result + (prerequisiteMatchIdsCsv != null ? prerequisiteMatchIdsCsv.hashCode() : 0);
        result = 31 * result + (scoresCsv != null ? scoresCsv.hashCode() : 0);
        return result;
    }
}
