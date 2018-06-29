/*
 * Copyright 2017 Stefan Geyer <stefangeyer at outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package at.stefangeyer.challonge.model

import at.stefangeyer.challonge.model.enumeration.RankedBy
import at.stefangeyer.challonge.model.enumeration.TournamentState
import at.stefangeyer.challonge.model.enumeration.TournamentType
import at.stefangeyer.challonge.model.enumeration.query.GrandFinalsModifier
import com.google.gson.annotations.SerializedName
import java.time.OffsetDateTime

class Tournament {
    private val name: String? = null
    @SerializedName("tournament_type")
    private val tournamentType: TournamentType? = null
    private val url: String? = null
    private val subdomain: String? = null
    private val description: String? = null
    @SerializedName("open_signup")
    private val openSignup: Boolean? = null
    @SerializedName("hold_third_place_match")
    private val holdThirdPlaceMatch: Boolean? = null
    @SerializedName("pts_for_match_win")
    private val pointsForMatchWin: Float? = null
    @SerializedName("pts_for_match_tie")
    private val pointsForMatchTie: Float? = null
    @SerializedName("pts_for_game_win")
    private val pointsForGameWin: Float? = null
    @SerializedName("pts_for_game_tie")
    private val pointsForGameTie: Float? = null
    @SerializedName("pts_for_bye")
    private val pointsForBye: Float? = null
    @SerializedName("swiss_rounds")
    private val swissRounds: Int? = null
    @SerializedName("ranked_by")
    private val rankedBy: RankedBy? = null
    @SerializedName("rr_pts_for_game_win")
    private val roundRobinPointsForGameWin: Float? = null
    @SerializedName("rr_pts_for_game_tie")
    private val roundRobinPointsForGameTie: Float? = null
    @SerializedName("rr_pts_for_match_win")
    private val roundRobinPointsForMatchWin: Float? = null
    @SerializedName("rr_pts_for_match_tie")
    private val roundRobinPointsForMatchTie: Float? = null
    @SerializedName("accept_attachments")
    private val acceptAttachments: Boolean? = null
    @SerializedName("hide_forum")
    private val hideForum: Boolean? = null
    @SerializedName("show_rounds")
    private val showRounds: Boolean? = null
    @SerializedName("private")
    private val _private: Boolean? = null
    @SerializedName("notify_users_when_the_tournament_ends")
    private val notifyUsersWhenTheTournamentEnds: Boolean? = null
    @SerializedName("sequential_pairings")
    private val sequentialPairings: Boolean? = null
    @SerializedName("signup_cap")
    private val signupCap: Int? = null
    @SerializedName("start_at")
    private val startAt: OffsetDateTime? = null
    @SerializedName("check_in_duration")
    private val checkInDuration: Int? = null
    @SerializedName("allow_participant_match_reporting")
    private val allowParticipantMatchReporting: Boolean? = null
    @SerializedName("anonymous_voting")
    private val anonymousVoting: Boolean? = null
    private val category: String? = null
    @SerializedName("completed_at")
    private val completedAt: OffsetDateTime? = null
    @SerializedName("created_at")
    private val createdAt: OffsetDateTime? = null
    @SerializedName("created_by_api")
    private val createdByApi: Boolean? = null
    @SerializedName("credit_capped")
    private val creditCapped: Boolean? = null
    @SerializedName("game_id")
    private val gameId: Int? = null
    @SerializedName("group_stages_enabled")
    private val groupStagesEnabled: Boolean? = null
    @SerializedName("hide_seeds")
    private val hideSeeds: Boolean? = null
    private val id: Int? = null
    @SerializedName("max_predictions_per_user")
    private val maxPredictionsPerUser: Int? = null
    @SerializedName("notify_users_when_matches_open")
    private val notifyUsersWhenMatchesOpen: Boolean? = null
    @SerializedName("participants_count")
    private val participantsCount: Int? = null
    @SerializedName("prediction_method")
    private val predictionMethod: Int? = null
    @SerializedName("predictions_opened_at")
    private val predictionsOpenedAt: OffsetDateTime? = null
    @SerializedName("progress_meter")
    private val progressMeter: Int? = null
    @SerializedName("quick_advance")
    private val quickAdvance: Boolean? = null
    @SerializedName("require_score_agreement")
    private val requireScoreAgreement: Boolean? = null
    @SerializedName("started_at")
    private val startedAt: OffsetDateTime? = null
    @SerializedName("started_checking_in_at")
    private val startedCheckingInAt: OffsetDateTime? = null
    private val state: TournamentState? = null
    private val teams: Boolean? = null
    @SerializedName("tie_breaks")
    private val tieBreaks: Array<String>? = null
    @SerializedName("updated_at")
    private val updatedAt: OffsetDateTime? = null
    @SerializedName("description_source")
    private val descriptionSource: String? = null
    @SerializedName("full_challonge_url")
    private val fullChallongeUrl: String? = null
    @SerializedName("live_image_url")
    private val liveImageUrl: String? = null
    @SerializedName("sign_up_url")
    private val signUpUrl: String? = null
    @SerializedName("review_before_finalizing")
    private val reviewBeforeFinalizing: Boolean? = null
    @SerializedName("accepting_predictions")
    private val acceptingPredictions: Boolean? = null
    @SerializedName("participants_locked")
    private val participantsLocked: Boolean? = null
    @SerializedName("game_name")
    private val gameName: String? = null
    @SerializedName("participants_swappable")
    private val participantsSwappable: Boolean? = null
    @SerializedName("team_convertable")
    private val teamConvertable: Boolean? = null
    @SerializedName("group_stages_were_started")
    private val groupStagesWereStarted: Boolean? = null
    @SerializedName("locked_at")
    private val lockedAt: OffsetDateTime? = null
    @SerializedName("event_id")
    private val eventId: Int? = null
    @SerializedName("public_predictions_before_start_time")
    private val publicPredictionsBeforeStartTime: Boolean? = null
    @SerializedName("grand_finals_modifier")
    private val grandFinalsModifier: GrandFinalsModifier? = null
    private val participants: List<Participant>? = null
    private val matches: List<Match>? = null
}
