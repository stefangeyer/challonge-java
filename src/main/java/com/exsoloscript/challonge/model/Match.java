package com.exsoloscript.challonge.model;

import com.exsoloscript.challonge.model.enumeration.MatchState;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

/**
 * The POJO that will be mapped to the match requests by Gson
 *
 * @author EXSolo
 * @version 20160820.1
 */
@Data
@Accessors(fluent = true)
@EqualsAndHashCode(exclude = {"createdAt", "scheduledTime", "startedAt", "underwayAt", "updatedAt"})
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
}
