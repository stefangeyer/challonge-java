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
) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Tournament

        if (id != other.id) return false
        if (url != other.url) return false
        if (name != other.name) return false
        if (tournamentType != other.tournamentType) return false
        if (subdomain != other.subdomain) return false
        if (description != other.description) return false
        if (openSignup != other.openSignup) return false
        if (holdThirdPlaceMatch != other.holdThirdPlaceMatch) return false
        if (pointsForMatchWin != other.pointsForMatchWin) return false
        if (pointsForMatchTie != other.pointsForMatchTie) return false
        if (pointsForGameWin != other.pointsForGameWin) return false
        if (pointsForGameTie != other.pointsForGameTie) return false
        if (pointsForBye != other.pointsForBye) return false
        if (swissRounds != other.swissRounds) return false
        if (rankedBy != other.rankedBy) return false
        if (roundRobinPointsForGameWin != other.roundRobinPointsForGameWin) return false
        if (roundRobinPointsForGameTie != other.roundRobinPointsForGameTie) return false
        if (roundRobinPointsForMatchWin != other.roundRobinPointsForMatchWin) return false
        if (roundRobinPointsForMatchTie != other.roundRobinPointsForMatchTie) return false
        if (acceptAttachments != other.acceptAttachments) return false
        if (hideForum != other.hideForum) return false
        if (showRounds != other.showRounds) return false
        if (private != other.private) return false
        if (notifyUsersWhenTheTournamentEnds != other.notifyUsersWhenTheTournamentEnds) return false
        if (sequentialPairings != other.sequentialPairings) return false
        if (signupCap != other.signupCap) return false
        if (checkInDuration != other.checkInDuration) return false
        if (allowParticipantMatchReporting != other.allowParticipantMatchReporting) return false
        if (anonymousVoting != other.anonymousVoting) return false
        if (category != other.category) return false
        if (createdByApi != other.createdByApi) return false
        if (creditCapped != other.creditCapped) return false
        if (gameId != other.gameId) return false
        if (groupStagesEnabled != other.groupStagesEnabled) return false
        if (hideSeeds != other.hideSeeds) return false
        if (maxPredictionsPerUser != other.maxPredictionsPerUser) return false
        if (notifyUsersWhenMatchesOpen != other.notifyUsersWhenMatchesOpen) return false
        if (participantsCount != other.participantsCount) return false
        if (predictionMethod != other.predictionMethod) return false
        if (progressMeter != other.progressMeter) return false
        if (quickAdvance != other.quickAdvance) return false
        if (requireScoreAgreement != other.requireScoreAgreement) return false
        if (state != other.state) return false
        if (teams != other.teams) return false
        if (tieBreaks != other.tieBreaks) return false
        if (descriptionSource != other.descriptionSource) return false
        if (fullChallongeUrl != other.fullChallongeUrl) return false
        if (liveImageUrl != other.liveImageUrl) return false
        if (signUpUrl != other.signUpUrl) return false
        if (reviewBeforeFinalizing != other.reviewBeforeFinalizing) return false
        if (acceptingPredictions != other.acceptingPredictions) return false
        if (participantsLocked != other.participantsLocked) return false
        if (gameName != other.gameName) return false
        if (participantsSwappable != other.participantsSwappable) return false
        if (teamConvertable != other.teamConvertable) return false
        if (groupStagesWereStarted != other.groupStagesWereStarted) return false
        if (eventId != other.eventId) return false
        if (publicPredictionsBeforeStartTime != other.publicPredictionsBeforeStartTime) return false
        if (grandFinalsModifier != other.grandFinalsModifier) return false
        if (participants != other.participants) return false
        if (matches != other.matches) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + tournamentType.hashCode()
        result = 31 * result + (subdomain?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + openSignup.hashCode()
        result = 31 * result + holdThirdPlaceMatch.hashCode()
        result = 31 * result + pointsForMatchWin.hashCode()
        result = 31 * result + pointsForMatchTie.hashCode()
        result = 31 * result + pointsForGameWin.hashCode()
        result = 31 * result + pointsForGameTie.hashCode()
        result = 31 * result + pointsForBye.hashCode()
        result = 31 * result + swissRounds
        result = 31 * result + (rankedBy?.hashCode() ?: 0)
        result = 31 * result + roundRobinPointsForGameWin.hashCode()
        result = 31 * result + roundRobinPointsForGameTie.hashCode()
        result = 31 * result + roundRobinPointsForMatchWin.hashCode()
        result = 31 * result + roundRobinPointsForMatchTie.hashCode()
        result = 31 * result + acceptAttachments.hashCode()
        result = 31 * result + hideForum.hashCode()
        result = 31 * result + showRounds.hashCode()
        result = 31 * result + private.hashCode()
        result = 31 * result + notifyUsersWhenTheTournamentEnds.hashCode()
        result = 31 * result + sequentialPairings.hashCode()
        result = 31 * result + (signupCap ?: 0)
        result = 31 * result + (checkInDuration?.hashCode() ?: 0)
        result = 31 * result + allowParticipantMatchReporting.hashCode()
        result = 31 * result + anonymousVoting.hashCode()
        result = 31 * result + (category?.hashCode() ?: 0)
        result = 31 * result + createdByApi.hashCode()
        result = 31 * result + creditCapped.hashCode()
        result = 31 * result + (gameId?.hashCode() ?: 0)
        result = 31 * result + groupStagesEnabled.hashCode()
        result = 31 * result + hideSeeds.hashCode()
        result = 31 * result + maxPredictionsPerUser
        result = 31 * result + notifyUsersWhenMatchesOpen.hashCode()
        result = 31 * result + participantsCount
        result = 31 * result + predictionMethod
        result = 31 * result + progressMeter
        result = 31 * result + quickAdvance.hashCode()
        result = 31 * result + requireScoreAgreement.hashCode()
        result = 31 * result + state.hashCode()
        result = 31 * result + (teams?.hashCode() ?: 0)
        result = 31 * result + (tieBreaks?.hashCode() ?: 0)
        result = 31 * result + (descriptionSource?.hashCode() ?: 0)
        result = 31 * result + (fullChallongeUrl?.hashCode() ?: 0)
        result = 31 * result + (liveImageUrl?.hashCode() ?: 0)
        result = 31 * result + (signUpUrl?.hashCode() ?: 0)
        result = 31 * result + reviewBeforeFinalizing.hashCode()
        result = 31 * result + acceptingPredictions.hashCode()
        result = 31 * result + participantsLocked.hashCode()
        result = 31 * result + (gameName?.hashCode() ?: 0)
        result = 31 * result + participantsSwappable.hashCode()
        result = 31 * result + teamConvertable.hashCode()
        result = 31 * result + groupStagesWereStarted.hashCode()
        result = 31 * result + (eventId?.hashCode() ?: 0)
        result = 31 * result + (publicPredictionsBeforeStartTime?.hashCode() ?: 0)
        result = 31 * result + (grandFinalsModifier?.hashCode() ?: 0)
        result = 31 * result + participants.hashCode()
        result = 31 * result + matches.hashCode()
        return result
    }

    override fun toString(): String {
        return "Tournament(id=$id, url='$url', name=$name, tournamentType=$tournamentType, subdomain=$subdomain, description=$description, openSignup=$openSignup, holdThirdPlaceMatch=$holdThirdPlaceMatch, pointsForMatchWin=$pointsForMatchWin, pointsForMatchTie=$pointsForMatchTie, pointsForGameWin=$pointsForGameWin, pointsForGameTie=$pointsForGameTie, pointsForBye=$pointsForBye, swissRounds=$swissRounds, rankedBy=$rankedBy, roundRobinPointsForGameWin=$roundRobinPointsForGameWin, roundRobinPointsForGameTie=$roundRobinPointsForGameTie, roundRobinPointsForMatchWin=$roundRobinPointsForMatchWin, roundRobinPointsForMatchTie=$roundRobinPointsForMatchTie, acceptAttachments=$acceptAttachments, hideForum=$hideForum, showRounds=$showRounds, private=$private, notifyUsersWhenTheTournamentEnds=$notifyUsersWhenTheTournamentEnds, sequentialPairings=$sequentialPairings, signupCap=$signupCap, startAt=$startAt, checkInDuration=$checkInDuration, allowParticipantMatchReporting=$allowParticipantMatchReporting, anonymousVoting=$anonymousVoting, category=$category, completedAt=$completedAt, createdAt=$createdAt, createdByApi=$createdByApi, creditCapped=$creditCapped, gameId=$gameId, groupStagesEnabled=$groupStagesEnabled, hideSeeds=$hideSeeds, maxPredictionsPerUser=$maxPredictionsPerUser, notifyUsersWhenMatchesOpen=$notifyUsersWhenMatchesOpen, participantsCount=$participantsCount, predictionMethod=$predictionMethod, predictionsOpenedAt=$predictionsOpenedAt, progressMeter=$progressMeter, quickAdvance=$quickAdvance, requireScoreAgreement=$requireScoreAgreement, startedAt=$startedAt, startedCheckingInAt=$startedCheckingInAt, state=$state, teams=$teams, tieBreaks=$tieBreaks, updatedAt=$updatedAt, descriptionSource=$descriptionSource, fullChallongeUrl=$fullChallongeUrl, liveImageUrl=$liveImageUrl, signUpUrl=$signUpUrl, reviewBeforeFinalizing=$reviewBeforeFinalizing, acceptingPredictions=$acceptingPredictions, participantsLocked=$participantsLocked, gameName=$gameName, participantsSwappable=$participantsSwappable, teamConvertable=$teamConvertable, groupStagesWereStarted=$groupStagesWereStarted, lockedAt=$lockedAt, eventId=$eventId, publicPredictionsBeforeStartTime=$publicPredictionsBeforeStartTime, grandFinalsModifier=$grandFinalsModifier, participants=$participants, matches=$matches)"
    }
}