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
    private final Integer attachmentCount;
    @SerializedName("createdAt")
    private final OffsetDateTime createdAt;
    @SerializedName("group_id")
    private final Integer groupId;
    @SerializedName("has_attachment")
    private final Boolean hasAttachment;
    private final Integer id;
    private final String identifier;
    private final String location;
    @SerializedName("loser_id")
    private final Integer loserId;
    @SerializedName("winner_id")
    private final Integer winnerId;
    @SerializedName("player1_id")
    private final Integer player1Id;
    @SerializedName("player1_is_prereq_match_loser")
    private final Boolean player1IsPrerequisiteMatchLoser;
    @SerializedName("player1_prereq_match_id")
    private final Integer player1PrerequisiteMatchId;
    @SerializedName("player2_id")
    private final Integer player2Id;
    @SerializedName("player2_is_prereq_match_loser")
    private final Boolean player2IsPrerequisiteMatchLoser;
    @SerializedName("player2_prereq_match_id")
    private final Integer player2PrerequisiteMatchId;
    private final Integer round;
    @SerializedName("scheduled_time")
    private final OffsetDateTime scheduledTime;
    @SerializedName("started_at")
    private final OffsetDateTime startedAt;
    private final MatchState state;
    @SerializedName("tournament_id")
    private final Integer tournamentId;
    @SerializedName("underway_at")
    private final OffsetDateTime underwayAt;
    @SerializedName("updatedAt")
    private final OffsetDateTime updatedAt;
    @SerializedName("prerequisite_match_ids_csv")
    private final String prerequisiteMatchIdsCsv;
    @SerializedName("scores_csv")
    private final String scoresCsv;
}
