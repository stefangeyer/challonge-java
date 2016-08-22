package com.exsoloscript.challonge.model;

import com.exsoloscript.challonge.model.enumeration.MatchState;
import com.google.gson.annotations.SerializedName;

import java.time.ZonedDateTime;

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

    public Integer attachmentCount() {
        return attachmentCount;
    }

    public ZonedDateTime createdAt() {
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

    public ZonedDateTime scheduledTime() {
        return scheduledTime;
    }

    public ZonedDateTime startedAt() {
        return startedAt;
    }

    public MatchState state() {
        return state;
    }

    public Integer tournamentId() {
        return tournamentId;
    }

    public ZonedDateTime underwayAt() {
        return underwayAt;
    }

    public ZonedDateTime updatedAt() {
        return updatedAt;
    }

    public String prerequisiteMatchIdsCsv() {
        return prerequisiteMatchIdsCsv;
    }
}
