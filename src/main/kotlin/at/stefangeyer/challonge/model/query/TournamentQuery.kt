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
        val tournamentType: TournamentType? = null,
        val url: String? = null,
        val subdomain: String? = null,
        val description: String? = null,
        val openSignup: Boolean? = null,
        val holdThirdPlaceMatch: Boolean? = null,
        val pointsForMatchWin: Float? = null,
        val pointsForMatchTie: Float? = null,
        val pointsForGameWin: Float? = null,
        val pointsForGameTie: Float? = null,
        val pointsForBye: Float? = null,
        val swissRounds: Int? = null,
        val rankedBy: RankedBy? = null,
        val roundRobinPointsForGameWin: Float? = null,
        val roundRobinPointsForGameTie: Float? = null,
        val roundRobinPointsForMatchWin: Float? = null,
        val roundRobinPointsForMatchTie: Float? = null,
        val acceptAttachments: Boolean? = null,
        val hideForum: Boolean? = null,
        val showRounds: Boolean? = null,
        val private: Boolean? = null,
        val notifyUsersWhenMatchesOpen: Boolean? = null,
        val notifyUsersWhenTheTournamentEnds: Boolean? = null,
        val sequentialPairings: Boolean? = null,
        val signupCap: Int? = null,
        val startAt: OffsetDateTime? = null,
        val checkInDuration: Long? = null,
        val grandFinalsModifier: GrandFinalsModifier? = null,
        val tieBreaks: List<String>? = null
)
