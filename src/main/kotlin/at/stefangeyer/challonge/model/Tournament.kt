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

class Tournament(
        val name: String?,
        @SerializedName("tournament_type")
        val tournamentType: TournamentType?,
        val url: String,
        val subdomain: String?,
        val description: String?,
        @SerializedName("open_signup")
        val openSignup: Boolean?,
        @SerializedName("hold_third_place_match")
        val holdThirdPlaceMatch: Boolean?,
        @SerializedName("pts_for_match_win")
        val pointsForMatchWin: Float?,
        @SerializedName("pts_for_match_tie")
        val pointsForMatchTie: Float?,
        @SerializedName("pts_for_game_win")
        val pointsForGameWin: Float?,
        @SerializedName("pts_for_game_tie")
        val pointsForGameTie: Float?,
        @SerializedName("pts_for_bye")
        val pointsForBye: Float?,
        @SerializedName("swiss_rounds")
        val swissRounds: Int?,
        @SerializedName("ranked_by")
        val rankedBy: RankedBy?,
        @SerializedName("rr_pts_for_game_win")
        val roundRobinPointsForGameWin: Float?,
        @SerializedName("rr_pts_for_game_tie")
        val roundRobinPointsForGameTie: Float?,
        @SerializedName("rr_pts_for_match_win")
        val roundRobinPointsForMatchWin: Float?,
        @SerializedName("rr_pts_for_match_tie")
        val roundRobinPointsForMatchTie: Float?,
        @SerializedName("accept_attachments")
        val acceptAttachments: Boolean?,
        @SerializedName("hide_forum")
        val hideForum: Boolean?,
        @SerializedName("show_rounds")
        val showRounds: Boolean?,
        @SerializedName("private")
        val _private: Boolean?,
        @SerializedName("notify_users_when_the_tournament_ends")
        val notifyUsersWhenTheTournamentEnds: Boolean?,
        @SerializedName("sequential_pairings")
        val sequentialPairings: Boolean?,
        @SerializedName("signup_cap")
        val signupCap: Int?,
        @SerializedName("start_at")
        val startAt: OffsetDateTime?,
        @SerializedName("check_in_duration")
        val checkInDuration: Int?,
        @SerializedName("allow_participant_match_reporting")
        val allowParticipantMatchReporting: Boolean?,
        @SerializedName("anonymous_voting")
        val anonymousVoting: Boolean?,
        val category: String?,
        @SerializedName("completed_at")
        val completedAt: OffsetDateTime?,
        @SerializedName("created_at")
        val createdAt: OffsetDateTime?,
        @SerializedName("created_by_api")
        val createdByApi: Boolean?,
        @SerializedName("credit_capped")
        val creditCapped: Boolean?,
        @SerializedName("game_id")
        val gameId: Long?,
        @SerializedName("group_stages_enabled")
        val groupStagesEnabled: Boolean?,
        @SerializedName("hide_seeds")
        val hideSeeds: Boolean?,
        val id: Long,
        @SerializedName("max_predictions_per_user")
        val maxPredictionsPerUser: Int?,
        @SerializedName("notify_users_when_matches_open")
        val notifyUsersWhenMatchesOpen: Boolean?,
        @SerializedName("participants_count")
        val participantsCount: Int?,
        @SerializedName("prediction_method")
        val predictionMethod: Int?,
        @SerializedName("predictions_opened_at")
        val predictionsOpenedAt: OffsetDateTime?,
        @SerializedName("progress_meter")
        val progressMeter: Int?,
        @SerializedName("quick_advance")
        val quickAdvance: Boolean?,
        @SerializedName("require_score_agreement")
        val requireScoreAgreement: Boolean?,
        @SerializedName("started_at")
        val startedAt: OffsetDateTime?,
        @SerializedName("started_checking_in_at")
        val startedCheckingInAt: OffsetDateTime?,
        val state: TournamentState?,
        val teams: Boolean?,
        @SerializedName("tie_breaks")
        val tieBreaks: Array<String>?,
        @SerializedName("updated_at")
        val updatedAt: OffsetDateTime?,
        @SerializedName("description_source")
        val descriptionSource: String?,
        @SerializedName("full_challonge_url")
        val fullChallongeUrl: String?,
        @SerializedName("live_image_url")
        val liveImageUrl: String?,
        @SerializedName("sign_up_url")
        val signUpUrl: String?,
        @SerializedName("review_before_finalizing")
        val reviewBeforeFinalizing: Boolean?,
        @SerializedName("accepting_predictions")
        val acceptingPredictions: Boolean?,
        @SerializedName("participants_locked")
        val participantsLocked: Boolean?,
        @SerializedName("game_name")
        val gameName: String?,
        @SerializedName("participants_swappable")
        val participantsSwappable: Boolean?,
        @SerializedName("team_convertable")
        val teamConvertable: Boolean?,
        @SerializedName("group_stages_were_started")
        val groupStagesWereStarted: Boolean?,
        @SerializedName("locked_at")
        val lockedAt: OffsetDateTime?,
        @SerializedName("event_id")
        val eventId: Long?,
        @SerializedName("public_predictions_before_start_time")
        val publicPredictionsBeforeStartTime: Boolean?,
        @SerializedName("grand_finals_modifier")
        val grandFinalsModifier: GrandFinalsModifier?,
        val participants: List<Participant>?,
        val matches: List<Match>?
)