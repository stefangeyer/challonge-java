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
        val id: Long = 0L,
        val url: String = "",
        val name: String? = null,
        @SerializedName("tournament_type")
        val tournamentType: TournamentType,
        val subdomain: String? = null,
        val description: String? = null,
        @SerializedName("open_signup")
        val openSignup: Boolean = false,
        @SerializedName("hold_third_place_match")
        val holdThirdPlaceMatch: Boolean = false,
        @SerializedName("pts_for_match_win")
        val pointsForMatchWin: Float = 0.0F,
        @SerializedName("pts_for_match_tie")
        val pointsForMatchTie: Float = 0.0F,
        @SerializedName("pts_for_game_win")
        val pointsForGameWin: Float = 0.0F,
        @SerializedName("pts_for_game_tie")
        val pointsForGameTie: Float = 0.0F,
        @SerializedName("pts_for_bye")
        val pointsForBye: Float = 0.0F,
        @SerializedName("swiss_rounds")
        val swissRounds: Int = 0,
        @SerializedName("ranked_by")
        val rankedBy: RankedBy? = null,
        @SerializedName("rr_pts_for_game_win")
        val roundRobinPointsForGameWin: Float = 0.0F,
        @SerializedName("rr_pts_for_game_tie")
        val roundRobinPointsForGameTie: Float = 0.0F,
        @SerializedName("rr_pts_for_match_win")
        val roundRobinPointsForMatchWin: Float = 0.0F,
        @SerializedName("rr_pts_for_match_tie")
        val roundRobinPointsForMatchTie: Float = 0.0F,
        @SerializedName("accept_attachments")
        val acceptAttachments: Boolean = false,
        @SerializedName("hide_forum")
        val hideForum: Boolean = false,
        @SerializedName("show_rounds")
        val showRounds: Boolean = false,
        @SerializedName("private")
        val private: Boolean = false,
        @SerializedName("notify_users_when_the_tournament_ends")
        val notifyUsersWhenTheTournamentEnds: Boolean = false,
        @SerializedName("sequential_pairings")
        val sequentialPairings: Boolean = false,
        @SerializedName("signup_cap")
        val signupCap: Int = 0,
        @SerializedName("start_at")
        val startAt: OffsetDateTime? = null,
        @SerializedName("check_in_duration")
        val checkInDuration: Long = 0,
        @SerializedName("allow_participant_match_reporting")
        val allowParticipantMatchReporting: Boolean = false,
        @SerializedName("anonymous_voting")
        val anonymousVoting: Boolean = false,
        val category: String? = null,
        @SerializedName("completed_at")
        val completedAt: OffsetDateTime? = null,
        @SerializedName("created_at")
        val createdAt: OffsetDateTime? = null,
        @SerializedName("created_by_api")
        val createdByApi: Boolean = false,
        @SerializedName("credit_capped")
        val creditCapped: Boolean = false,
        @SerializedName("game_id")
        val gameId: Long? = null,
        @SerializedName("group_stages_enabled")
        val groupStagesEnabled: Boolean = false,
        @SerializedName("hide_seeds")
        val hideSeeds: Boolean = false,
        @SerializedName("max_predictions_per_user")
        val maxPredictionsPerUser: Int = 0,
        @SerializedName("notify_users_when_matches_open")
        val notifyUsersWhenMatchesOpen: Boolean = false,
        @SerializedName("participants_count")
        val participantsCount: Int = 0,
        @SerializedName("prediction_method")
        val predictionMethod: Int = 0,
        @SerializedName("predictions_opened_at")
        val predictionsOpenedAt: OffsetDateTime? = null,
        @SerializedName("progress_meter")
        val progressMeter: Int = 0,
        @SerializedName("quick_advance")
        val quickAdvance: Boolean = false,
        @SerializedName("require_score_agreement")
        val requireScoreAgreement: Boolean = false,
        @SerializedName("started_at")
        val startedAt: OffsetDateTime? = null,
        @SerializedName("started_checking_in_at")
        val startedCheckingInAt: OffsetDateTime? = null,
        val state: TournamentState = TournamentState.PENDING,
        val teams: Boolean = false,
        @SerializedName("tie_breaks")
        val tieBreaks: List<String> = listOf(),
        @SerializedName("updated_at")
        val updatedAt: OffsetDateTime? = null,
        @SerializedName("description_source")
        val descriptionSource: String? = null,
        @SerializedName("full_challonge_url")
        val fullChallongeUrl: String? = null,
        @SerializedName("live_image_url")
        val liveImageUrl: String? = null,
        @SerializedName("sign_up_url")
        val signUpUrl: String? = null,
        @SerializedName("review_before_finalizing")
        val reviewBeforeFinalizing: Boolean = false,
        @SerializedName("accepting_predictions")
        val acceptingPredictions: Boolean = false,
        @SerializedName("participants_locked")
        val participantsLocked: Boolean = false,
        @SerializedName("game_name")
        val gameName: String? = null,
        @SerializedName("participants_swappable")
        val participantsSwappable: Boolean = false,
        @SerializedName("team_convertable")
        val teamConvertable: Boolean = false,
        @SerializedName("group_stages_were_started")
        val groupStagesWereStarted: Boolean = false,
        @SerializedName("locked_at")
        val lockedAt: OffsetDateTime? = null,
        @SerializedName("event_id")
        val eventId: Long = 0L,
        @SerializedName("public_predictions_before_start_time")
        val publicPredictionsBeforeStartTime: Boolean = false,
        @SerializedName("grand_finals_modifier")
        val grandFinalsModifier: GrandFinalsModifier = GrandFinalsModifier.BLANK,
        val participants: List<Participant> = listOf(),
        val matches: List<Match> = listOf()
)