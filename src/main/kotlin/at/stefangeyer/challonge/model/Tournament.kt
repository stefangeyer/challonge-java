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
        var id: Long = 0L,
        var url: String = "",
        var name: String? = null,
        @SerializedName("tournament_type")
        var tournamentType: TournamentType = TournamentType.SINGLE_ELIMINATION,
        var subdomain: String? = null,
        var description: String? = null,
        @SerializedName("open_signup")
        var openSignup: Boolean = false,
        @SerializedName("hold_third_place_match")
        var holdThirdPlaceMatch: Boolean = false,
        @SerializedName("pts_for_match_win")
        var pointsForMatchWin: Float = 0.0F,
        @SerializedName("pts_for_match_tie")
        var pointsForMatchTie: Float = 0.0F,
        @SerializedName("pts_for_game_win")
        var pointsForGameWin: Float = 0.0F,
        @SerializedName("pts_for_game_tie")
        var pointsForGameTie: Float = 0.0F,
        @SerializedName("pts_for_bye")
        var pointsForBye: Float = 0.0F,
        @SerializedName("swiss_rounds")
        var swissRounds: Int = 0,
        @SerializedName("ranked_by")
        var rankedBy: RankedBy? = null,
        @SerializedName("rr_pts_for_game_win")
        var roundRobinPointsForGameWin: Float = 0.0F,
        @SerializedName("rr_pts_for_game_tie")
        var roundRobinPointsForGameTie: Float = 0.0F,
        @SerializedName("rr_pts_for_match_win")
        var roundRobinPointsForMatchWin: Float = 0.0F,
        @SerializedName("rr_pts_for_match_tie")
        var roundRobinPointsForMatchTie: Float = 0.0F,
        @SerializedName("accept_attachments")
        var acceptAttachments: Boolean = false,
        @SerializedName("hide_forum")
        var hideForum: Boolean = false,
        @SerializedName("show_rounds")
        var showRounds: Boolean = false,
        @SerializedName("private")
        var private: Boolean = false,
        @SerializedName("notify_users_when_the_tournament_ends")
        var notifyUsersWhenTheTournamentEnds: Boolean = false,
        @SerializedName("sequential_pairings")
        var sequentialPairings: Boolean = false,
        @SerializedName("signup_cap")
        var signupCap: Int? = 0,
        @SerializedName("start_at")
        var startAt: OffsetDateTime? = null,
        @SerializedName("check_in_duration")
        var checkInDuration: Long? = 0,
        @SerializedName("allow_participant_match_reporting")
        var allowParticipantMatchReporting: Boolean = false,
        @SerializedName("anonymous_voting")
        var anonymousVoting: Boolean = false,
        var category: String? = null,
        @SerializedName("completed_at")
        var completedAt: OffsetDateTime? = null,
        @SerializedName("created_at")
        var createdAt: OffsetDateTime? = null,
        @SerializedName("created_by_api")
        var createdByApi: Boolean = false,
        @SerializedName("credit_capped")
        var creditCapped: Boolean = false,
        @SerializedName("game_id")
        var gameId: Long? = null,
        @SerializedName("group_stages_enabled")
        var groupStagesEnabled: Boolean = false,
        @SerializedName("hide_seeds")
        var hideSeeds: Boolean = false,
        @SerializedName("max_predictions_per_user")
        var maxPredictionsPerUser: Int = 0,
        @SerializedName("notify_users_when_matches_open")
        var notifyUsersWhenMatchesOpen: Boolean = false,
        @SerializedName("participants_count")
        var participantsCount: Int = 0,
        @SerializedName("prediction_method")
        var predictionMethod: Int = 0,
        @SerializedName("predictions_opened_at")
        var predictionsOpenedAt: OffsetDateTime? = null,
        @SerializedName("progress_meter")
        var progressMeter: Int = 0,
        @SerializedName("quick_advance")
        var quickAdvance: Boolean = false,
        @SerializedName("require_score_agreement")
        var requireScoreAgreement: Boolean = false,
        @SerializedName("started_at")
        var startedAt: OffsetDateTime? = null,
        @SerializedName("started_checking_in_at")
        var startedCheckingInAt: OffsetDateTime? = null,
        var state: TournamentState = TournamentState.PENDING,
        var teams: Boolean? = false,
        @SerializedName("tie_breaks")
        var tieBreaks: List<String>? = listOf(),
        @SerializedName("updated_at")
        var updatedAt: OffsetDateTime? = null,
        @SerializedName("description_source")
        var descriptionSource: String? = null,
        @SerializedName("full_challonge_url")
        var fullChallongeUrl: String? = null,
        @SerializedName("live_image_url")
        var liveImageUrl: String? = null,
        @SerializedName("sign_up_url")
        var signUpUrl: String? = null,
        @SerializedName("review_before_finalizing")
        var reviewBeforeFinalizing: Boolean = false,
        @SerializedName("accepting_predictions")
        var acceptingPredictions: Boolean = false,
        @SerializedName("participants_locked")
        var participantsLocked: Boolean = false,
        @SerializedName("game_name")
        var gameName: String? = null,
        @SerializedName("participants_swappable")
        var participantsSwappable: Boolean = false,
        @SerializedName("team_convertable")
        var teamConvertable: Boolean = false,
        @SerializedName("group_stages_were_started")
        var groupStagesWereStarted: Boolean = false,
        @SerializedName("locked_at")
        var lockedAt: OffsetDateTime? = null,
        @SerializedName("event_id")
        var eventId: Long? = 0L,
        @SerializedName("public_predictions_before_start_time")
        var publicPredictionsBeforeStartTime: Boolean? = false,
        @SerializedName("grand_finals_modifier")
        var grandFinalsModifier: GrandFinalsModifier? = null,
        var participants: List<Participant> = listOf(),
        var matches: List<Match> = listOf()
)