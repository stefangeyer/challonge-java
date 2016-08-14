package com.exsoloscript.challonge.model;

import com.exsoloscript.challonge.model.enumeration.MatchState;
import com.google.gson.annotations.SerializedName;

import java.time.ZonedDateTime;

public class Match {
    @SerializedName("attachment_count")
    private Integer attachmentCount;
    @SerializedName("createdAt")
    private ZonedDateTime createdAt;
    @SerializedName("group_id")
    private Integer groupId;
    @SerializedName("has_attachment")
    private Boolean hasAttachment;
    private Integer id;
    private String identifier;
    private String location;
    @SerializedName("loser_id")
    private Integer loserId;
    @SerializedName("player1_id")
    private Integer player1Id;
    @SerializedName("player1_is_prereq_match_loser")
    private Boolean player1IsPrereqMatchLoser;
    @SerializedName("player1_prereq_match_id")
    private Integer player1PrereqMatchId;
    @SerializedName("player2_id")
    private Integer player2Id;
    @SerializedName("player2_is_prereq_match_loser")
    private Boolean player2IsPrereqMatchLoser;
    @SerializedName("player2_prereq_match_id")
    private Integer player2PrereqMatchId;
    private Integer round;
    @SerializedName("scheduled_time")
    private ZonedDateTime scheduledTime;
    @SerializedName("started_at")
    private ZonedDateTime startedAt;
    private MatchState state;
    @SerializedName("tournament_id")
    private Integer tournamentId;
    @SerializedName("underway_at")
    private ZonedDateTime underwayAt;
    @SerializedName("updatedAt")
    private ZonedDateTime updatedAt;
    @SerializedName("prerequisite_match_ids_csv")
    private String prerequisiteMatchIdsCsv;

    public Integer getAttachmentCount() {
        return attachmentCount;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public Boolean getHasAttachment() {
        return hasAttachment;
    }

    public Integer getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getLocation() {
        return location;
    }

    public Integer getLoserId() {
        return loserId;
    }

    public Integer getPlayer1Id() {
        return player1Id;
    }

    public Boolean getPlayer1IsPrereqMatchLoser() {
        return player1IsPrereqMatchLoser;
    }

    public Integer getPlayer1PrereqMatchId() {
        return player1PrereqMatchId;
    }

    public Integer getPlayer2Id() {
        return player2Id;
    }

    public Boolean getPlayer2IsPrereqMatchLoser() {
        return player2IsPrereqMatchLoser;
    }

    public Integer getPlayer2PrereqMatchId() {
        return player2PrereqMatchId;
    }

    public Integer getRound() {
        return round;
    }

    public ZonedDateTime getScheduledTime() {
        return scheduledTime;
    }

    public ZonedDateTime getStartedAt() {
        return startedAt;
    }

    public MatchState getState() {
        return state;
    }

    public Integer getTournamentId() {
        return tournamentId;
    }

    public ZonedDateTime getUnderwayAt() {
        return underwayAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getPrerequisiteMatchIdsCsv() {
        return prerequisiteMatchIdsCsv;
    }
}
