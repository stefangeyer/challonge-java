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
        val name: String,
        @SerializedName("tournament_type")
        val tournamentType: TournamentType,
        val url: String,
        @SerializedName("subdomain")
        val subdomain: String,
        val description: String,
        @SerializedName("open_signup")
        val openSignup: Boolean,
        @SerializedName("hold_third_place_match")
        val holdThirdPlaceMatch: Boolean,
        @SerializedName("pts_for_match_win")
        val pointsForMatchWin: Float,
        @SerializedName("pts_for_match_tie")
        val pointsForMatchTie: Float,
        @SerializedName("pts_for_game_win")
        val pointsForGameWin: Float,
        @SerializedName("pts_for_game_tie")
        val pointsForGameTie: Float,
        @SerializedName("pts_for_bye")
        val pointsForBye: Float,
        @SerializedName("swiss_rounds")
        val swissRounds: Int,
        @SerializedName("ranked_by")
        val rankedBy: RankedBy,
        @SerializedName("rr_pts_for_game_win")
        val roundRobinPointsForGameWin: Float,
        @SerializedName("rr_pts_for_game_tie")
        val roundRobinPointsForGameTie: Float,
        @SerializedName("rr_pts_for_match_win")
        val roundRobinPointsForMatchWin: Float,
        @SerializedName("rr_pts_for_match_tie")
        val roundRobinPointsForMatchTie: Float,
        @SerializedName("accept_attachments")
        val acceptAttachments: Boolean,
        @SerializedName("hide_forum")
        val hideForum: Boolean,
        @SerializedName("show_rounds")
        val showRounds: Boolean,
        @SerializedName("private")
        val _private: Boolean,
        @SerializedName("notify_users_when_matches_open")
        val notifyUsersWhenMatchesOpen: Boolean,
        @SerializedName("notify_users_when_the_tournament_ends")
        val notifyUsersWhenTheTournamentEnds: Boolean,
        @SerializedName("sequential_pairings")
        val sequentialPairings: Boolean,
        @SerializedName("signup_cap")
        val signupCap: Int,
        @SerializedName("start_at")
        val startAt: OffsetDateTime,
        @SerializedName("check_in_duration")
        val checkInDuration: Int,
        @SerializedName("grand_finals_modifier")
        val grandFinalsModifier: GrandFinalsModifier,
        @SerializedName("tie_breaks")
        val tieBreaks: List<String>
)
