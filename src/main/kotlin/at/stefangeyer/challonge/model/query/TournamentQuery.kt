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

package at.stefangeyer.challonge.model.query

import at.stefangeyer.challonge.model.enumeration.RankedBy
import at.stefangeyer.challonge.model.enumeration.TournamentType
import at.stefangeyer.challonge.model.enumeration.query.GrandFinalsModifier
import com.google.gson.annotations.SerializedName
import java.time.OffsetDateTime

class TournamentQuery(
        val name: String? = null,
        @SerializedName("tournament_type")
        val tournamentType: TournamentType? = null,
        val url: String? = null,
        @SerializedName("subdomain")
        val subdomain: String? = null,
        val description: String? = null,
        @SerializedName("open_signup")
        val openSignup: Boolean? = null,
        @SerializedName("hold_third_place_match")
        val holdThirdPlaceMatch: Boolean? = null,
        @SerializedName("pts_for_match_win")
        val pointsForMatchWin: Float? = null,
        @SerializedName("pts_for_match_tie")
        val pointsForMatchTie: Float? = null,
        @SerializedName("pts_for_game_win")
        val pointsForGameWin: Float? = null,
        @SerializedName("pts_for_game_tie")
        val pointsForGameTie: Float? = null,
        @SerializedName("pts_for_bye")
        val pointsForBye: Float? = null,
        @SerializedName("swiss_rounds")
        val swissRounds: Int? = null,
        @SerializedName("ranked_by")
        val rankedBy: RankedBy? = null,
        @SerializedName("rr_pts_for_game_win")
        val roundRobinPointsForGameWin: Float? = null,
        @SerializedName("rr_pts_for_game_tie")
        val roundRobinPointsForGameTie: Float? = null,
        @SerializedName("rr_pts_for_match_win")
        val roundRobinPointsForMatchWin: Float? = null,
        @SerializedName("rr_pts_for_match_tie")
        val roundRobinPointsForMatchTie: Float? = null,
        @SerializedName("accept_attachments")
        val acceptAttachments: Boolean? = null,
        @SerializedName("hide_forum")
        val hideForum: Boolean? = null,
        @SerializedName("show_rounds")
        val showRounds: Boolean? = null,
        @SerializedName("private")
        val private: Boolean? = null,
        @SerializedName("notify_users_when_matches_open")
        val notifyUsersWhenMatchesOpen: Boolean? = null,
        @SerializedName("notify_users_when_the_tournament_ends")
        val notifyUsersWhenTheTournamentEnds: Boolean? = null,
        @SerializedName("sequential_pairings")
        val sequentialPairings: Boolean? = null,
        @SerializedName("signup_cap")
        val signupCap: Int? = null,
        @SerializedName("start_at")
        val startAt: OffsetDateTime? = null,
        @SerializedName("check_in_duration")
        val checkInDuration: Long? = null,
        @SerializedName("grand_finals_modifier")
        val grandFinalsModifier: GrandFinalsModifier? = null,
        @SerializedName("tie_breaks")
        val tieBreaks: List<String>? = null
)
