/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 EXSolo <exsoloscripter at gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

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
    @SerializedName("updated_at")
    private final OffsetDateTime updatedAt;
    @SerializedName("prerequisite_match_ids_csv")
    private final String prerequisiteMatchIdsCsv;
    @SerializedName("scores_csv")
    private final String scoresCsv;
}
