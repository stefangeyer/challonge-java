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

import com.exsoloscript.challonge.model.enumeration.RankedBy;
import com.exsoloscript.challonge.model.enumeration.TournamentState;
import com.exsoloscript.challonge.model.enumeration.TournamentType;
import com.exsoloscript.challonge.model.enumeration.query.GrandFinalsModifier;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * The POJO that will be mapped to the tournament requests by Gson
 *
 * @author EXSolo
 * @version 20160820.1
 */
@Data
@Accessors(fluent = true)
@EqualsAndHashCode(exclude = {
        "startAt", "completedAt", "createdAt",
        "predictionsOpenedAt", "startedAt", "startedCheckingInAt",
        "updatedAt", "lockedAt"})
public class Tournament {
    private final String name;
    @SerializedName("tournament_type")
    private final TournamentType tournamentType;
    private final String url;
    private final String subdomain;
    private final String description;
    @SerializedName("open_signup")
    private final Boolean openSignup;
    @SerializedName("hold_third_place_match")
    private final Boolean holdThirdPlaceMatch;
    @SerializedName("pts_for_match_win")
    private final Float pointsForMatchWin;
    @SerializedName("pts_for_match_tie")
    private final Float pointsForMatchTie;
    @SerializedName("pts_for_game_win")
    private final Float pointsForGameWin;
    @SerializedName("pts_for_game_tie")
    private final Float pointsForGameTie;
    @SerializedName("pts_for_bye")
    private final Float pointsForBye;
    @SerializedName("swiss_rounds")
    private final Integer swissRounds;
    @SerializedName("ranked_by")
    private final RankedBy rankedBy;
    @SerializedName("rr_pts_for_game_win")
    private final Float roundRobinPointsForGameWin;
    @SerializedName("rr_pts_for_game_tie")
    private final Float roundRobinPointsForGameTie;
    @SerializedName("rr_pts_for_match_win")
    private final Float roundRobinPointsForMatchWin;
    @SerializedName("rr_pts_for_match_tie")
    private final Float roundRobinPointsForMatchTie;
    @SerializedName("accept_attachments")
    private final Boolean acceptAttachments;
    @SerializedName("hide_forum")
    private final Boolean hideForum;
    @SerializedName("show_rounds")
    private final Boolean showRounds;
    @SerializedName("private")
    private final Boolean _private;
    @SerializedName("notify_users_when_the_tournament_ends")
    private final Boolean notifyUsersWhenTheTournamentEnds;
    @SerializedName("sequential_pairings")
    private final Boolean sequentialPairings;
    @SerializedName("signup_cap")
    private final Integer signupCap;
    @SerializedName("start_at")
    private final OffsetDateTime startAt;
    @SerializedName("check_in_duration")
    private final Integer checkInDuration;
    @SerializedName("allow_participant_match_reporting")
    private final Boolean allowParticipantMatchReporting;
    @SerializedName("anonymous_voting")
    private final Boolean anonymousVoting;
    private final String category;
    @SerializedName("completed_at")
    private final OffsetDateTime completedAt;
    @SerializedName("created_at")
    private final OffsetDateTime createdAt;
    @SerializedName("created_by_api")
    private final Boolean createdByApi;
    @SerializedName("credit_capped")
    private final Boolean creditCapped;
    @SerializedName("game_id")
    private final Integer gameId;
    @SerializedName("group_stages_enabled")
    private final Boolean groupStagesEnabled;
    @SerializedName("hide_seeds")
    private final Boolean hideSeeds;
    private final Integer id;
    @SerializedName("max_predictions_per_user")
    private final Integer maxPredictionsPerUser;
    @SerializedName("notify_users_when_matches_open")
    private final Boolean notifyUsersWhenMatchesOpen;
    @SerializedName("participants_count")
    private final Integer participantsCount;
    @SerializedName("prediction_method")
    private final Integer predictionMethod;
    @SerializedName("predictions_opened_at")
    private final OffsetDateTime predictionsOpenedAt;
    @SerializedName("progress_meter")
    private final Integer progressMeter;
    @SerializedName("quick_advance")
    private final Boolean quickAdvance;
    @SerializedName("require_score_agreement")
    private final Boolean requireScoreAgreement;
    @SerializedName("started_at")
    private final OffsetDateTime startedAt;
    @SerializedName("started_checking_in_at")
    private final OffsetDateTime startedCheckingInAt;
    private final TournamentState state;
    private final Boolean teams;
    @SerializedName("tie_breaks")
    private final String[] tieBreaks;
    @SerializedName("updated_at")
    private final OffsetDateTime updatedAt;
    @SerializedName("description_source")
    private final String descriptionSource;
    @SerializedName("full_challonge_url")
    private final String fullChallongeUrl;
    @SerializedName("live_image_url")
    private final String liveImageUrl;
    @SerializedName("sign_up_url")
    private final String signUpUrl;
    @SerializedName("review_before_finalizing")
    private final Boolean reviewBeforeFinalizing;
    @SerializedName("accepting_predictions")
    private final Boolean acceptingPredictions;
    @SerializedName("participants_locked")
    private final Boolean participantsLocked;
    @SerializedName("game_name")
    private final String gameName;
    @SerializedName("participants_swappable")
    private final Boolean participantsSwappable;
    @SerializedName("team_convertable")
    private final Boolean teamConvertable;
    @SerializedName("group_stages_were_started")
    private final Boolean groupStagesWereStarted;
    @SerializedName("locked_at")
    private final OffsetDateTime lockedAt;
    @SerializedName("event_id")
    private final Integer eventId;
    @SerializedName("public_predictions_before_start_time")
    private final Boolean publicPredictionsBeforeStartTime;
    @SerializedName("grand_finals_modifier")
    private final GrandFinalsModifier grandFinalsModifier;
    private final List<Participant> participants;
    private final List<Match> matches;
}
