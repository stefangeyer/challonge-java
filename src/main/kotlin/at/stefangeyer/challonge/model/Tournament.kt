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

import at.stefangeyer.challonge.model.enum.RankedBy
import at.stefangeyer.challonge.model.enum.TournamentState
import at.stefangeyer.challonge.model.enum.TournamentType
import at.stefangeyer.challonge.model.query.enum.GrandFinalsModifier
import java.time.OffsetDateTime

class Tournament(
        var id: Long = 0L,
        var url: String = "",
        var name: String? = null,
        var tournamentType: TournamentType = TournamentType.SINGLE_ELIMINATION,
        var subdomain: String? = null,
        var description: String? = null,
        var openSignup: Boolean = false,
        var holdThirdPlaceMatch: Boolean = false,
        var pointsForMatchWin: Float = 0.0F,
        var pointsForMatchTie: Float = 0.0F,
        var pointsForGameWin: Float = 0.0F,
        var pointsForGameTie: Float = 0.0F,
        var pointsForBye: Float = 0.0F,
        var swissRounds: Int = 0,
        var rankedBy: RankedBy? = null,
        var roundRobinPointsForGameWin: Float = 0.0F,
        var roundRobinPointsForGameTie: Float = 0.0F,
        var roundRobinPointsForMatchWin: Float = 0.0F,
        var roundRobinPointsForMatchTie: Float = 0.0F,
        var acceptAttachments: Boolean = false,
        var hideForum: Boolean = false,
        var showRounds: Boolean = false,
        var private: Boolean = false,
        var notifyUsersWhenTheTournamentEnds: Boolean = false,
        var sequentialPairings: Boolean = false,
        var signupCap: Int? = 0,
        var startAt: OffsetDateTime? = null,
        var checkInDuration: Long? = 0,
        var allowParticipantMatchReporting: Boolean = false,
        var anonymousVoting: Boolean = false,
        var category: String? = null,
        var completedAt: OffsetDateTime? = null,
        var createdAt: OffsetDateTime? = null,
        var createdByApi: Boolean = false,
        var creditCapped: Boolean = false,
        var gameId: Long? = null,
        var groupStagesEnabled: Boolean = false,
        var hideSeeds: Boolean = false,
        var maxPredictionsPerUser: Int = 0,
        var notifyUsersWhenMatchesOpen: Boolean = false,
        var participantsCount: Int = 0,
        var predictionMethod: Int = 0,
        var predictionsOpenedAt: OffsetDateTime? = null,
        var progressMeter: Int = 0,
        var quickAdvance: Boolean = false,
        var requireScoreAgreement: Boolean = false,
        var startedAt: OffsetDateTime? = null,
        var startedCheckingInAt: OffsetDateTime? = null,
        var state: TournamentState = TournamentState.PENDING,
        var teams: Boolean? = false,
        var tieBreaks: List<String>? = listOf(),
        var updatedAt: OffsetDateTime? = null,
        var descriptionSource: String? = null,
        var fullChallongeUrl: String? = null,
        var liveImageUrl: String? = null,
        var signUpUrl: String? = null,
        var reviewBeforeFinalizing: Boolean = false,
        var acceptingPredictions: Boolean = false,
        var participantsLocked: Boolean = false,
        var gameName: String? = null,
        var participantsSwappable: Boolean = false,
        var teamConvertable: Boolean = false,
        var groupStagesWereStarted: Boolean = false,
        var lockedAt: OffsetDateTime? = null,
        var eventId: Long? = 0L,
        var publicPredictionsBeforeStartTime: Boolean? = false,
        var grandFinalsModifier: GrandFinalsModifier? = null,
        var participants: List<Participant> = listOf(),
        var matches: List<Match> = listOf()
)