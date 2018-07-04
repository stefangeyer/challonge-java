package at.stefangeyer.challonge.service.implementation

import at.stefangeyer.challonge.async.Callback
import at.stefangeyer.challonge.exception.DataAccessException
import at.stefangeyer.challonge.model.Tournament
import at.stefangeyer.challonge.model.enumeration.TournamentType
import at.stefangeyer.challonge.model.enumeration.query.TournamentQueryState
import at.stefangeyer.challonge.model.query.TournamentQuery
import at.stefangeyer.challonge.rest.TournamentRestClient
import at.stefangeyer.challonge.service.TournamentService
import java.time.OffsetDateTime

/**
 * Tournament Service Implementation
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
class SimpleTournamentService(private val restClient: TournamentRestClient) : TournamentService {

    override fun getTournaments(state: TournamentQueryState?, type: TournamentType?, createdAfter: OffsetDateTime?,
                                createdBefore: OffsetDateTime?, subdomain: String?): List<Tournament> =
            this.restClient.getTournaments(state, type, createdAfter, createdBefore, subdomain)

    override fun getTournaments(state: TournamentQueryState?, type: TournamentType?, createdAfter: OffsetDateTime?,
                                createdBefore: OffsetDateTime?, subdomain: String?,
                                onSuccess: Callback<List<Tournament>>, onFailure: Callback<DataAccessException>) {
        this.restClient.getTournaments(state, type, createdAfter, createdBefore, subdomain, onSuccess, onFailure)
    }

    override fun getTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.restClient.getTournament(tournament, includeParticipants, includeMatches)

    override fun getTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean, onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) {
        this.restClient.getTournament(tournament, includeParticipants, includeMatches, onSuccess, onFailure)
    }

    override fun createTournament(data: TournamentQuery): Tournament {
        if (data.name == null && data.tournamentType == null &&
                data.subdomain == null && data.description == null && data.openSignup == null &&
                data.holdThirdPlaceMatch == null && data.pointsForMatchWin == null && data.pointsForMatchTie == null &&
                data.pointsForGameWin == null && data.pointsForGameTie == null && data.pointsForBye == null &&
                data.swissRounds == null && data.rankedBy == null && data.roundRobinPointsForMatchWin == null &&
                data.roundRobinPointsForMatchTie == null && data.roundRobinPointsForGameWin == null &&
                data.roundRobinPointsForGameTie == null && data.acceptAttachments == null && data.hideForum == null &&
                data.showRounds == null && data.private == null && data.notifyUsersWhenMatchesOpen == null &&
                data.notifyUsersWhenTheTournamentEnds == null && data.sequentialPairings == null &&
                data.signupCap == null && data.startAt == null && data.checkInDuration == null &&
                data.grandFinalsModifier == null && data.tieBreaks == null) {
            throw IllegalArgumentException("All data parameters are null. Provide at least one")
        }
        return this.restClient.createTournament(data)
    }

    override fun createTournament(data: TournamentQuery, onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) {
        if (data.name == null && data.tournamentType == null &&
                data.subdomain == null && data.description == null && data.openSignup == null &&
                data.holdThirdPlaceMatch == null && data.pointsForMatchWin == null && data.pointsForMatchTie == null &&
                data.pointsForGameWin == null && data.pointsForGameTie == null && data.pointsForBye == null &&
                data.swissRounds == null && data.rankedBy == null && data.roundRobinPointsForMatchWin == null &&
                data.roundRobinPointsForMatchTie == null && data.roundRobinPointsForGameWin == null &&
                data.roundRobinPointsForGameTie == null && data.acceptAttachments == null && data.hideForum == null &&
                data.showRounds == null && data.private == null && data.notifyUsersWhenMatchesOpen == null &&
                data.notifyUsersWhenTheTournamentEnds == null && data.sequentialPairings == null &&
                data.signupCap == null && data.startAt == null && data.checkInDuration == null &&
                data.grandFinalsModifier == null && data.tieBreaks == null) {
            throw IllegalArgumentException("All data parameters are null. Provide at least one")
        }
        this.restClient.createTournament(data, onSuccess, onFailure)
    }

    override fun updateTournament(tournament: Tournament, data: TournamentQuery): Tournament {
        if (data.name == null && data.tournamentType == null &&
                data.subdomain == null && data.description == null && data.openSignup == null &&
                data.holdThirdPlaceMatch == null && data.pointsForMatchWin == null && data.pointsForMatchTie == null &&
                data.pointsForGameWin == null && data.pointsForGameTie == null && data.pointsForBye == null &&
                data.swissRounds == null && data.rankedBy == null && data.roundRobinPointsForMatchWin == null &&
                data.roundRobinPointsForMatchTie == null && data.roundRobinPointsForGameWin == null &&
                data.roundRobinPointsForGameTie == null && data.acceptAttachments == null && data.hideForum == null &&
                data.showRounds == null && data.private == null && data.notifyUsersWhenMatchesOpen == null &&
                data.notifyUsersWhenTheTournamentEnds == null && data.sequentialPairings == null &&
                data.signupCap == null && data.startAt == null && data.checkInDuration == null &&
                data.grandFinalsModifier == null && data.tieBreaks == null) {
            throw IllegalArgumentException("All data parameters are null. Provide at least one")
        }
        return this.restClient.updateTournament(tournament.id.toString(), data)
    }

    override fun updateTournament(tournament: Tournament, data: TournamentQuery, onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) {
        if (data.name == null && data.tournamentType == null &&
                data.subdomain == null && data.description == null && data.openSignup == null &&
                data.holdThirdPlaceMatch == null && data.pointsForMatchWin == null && data.pointsForMatchTie == null &&
                data.pointsForGameWin == null && data.pointsForGameTie == null && data.pointsForBye == null &&
                data.swissRounds == null && data.rankedBy == null && data.roundRobinPointsForMatchWin == null &&
                data.roundRobinPointsForMatchTie == null && data.roundRobinPointsForGameWin == null &&
                data.roundRobinPointsForGameTie == null && data.acceptAttachments == null && data.hideForum == null &&
                data.showRounds == null && data.private == null && data.notifyUsersWhenMatchesOpen == null &&
                data.notifyUsersWhenTheTournamentEnds == null && data.sequentialPairings == null &&
                data.signupCap == null && data.startAt == null && data.checkInDuration == null &&
                data.grandFinalsModifier == null && data.tieBreaks == null) {
            throw IllegalArgumentException("All data parameters are null. Provide at least one")
        }
        this.restClient.updateTournament(tournament.id.toString(), data, onSuccess, onFailure)
    }

    override fun deleteTournament(tournament: Tournament): Tournament =
            this.restClient.deleteTournament(tournament.id.toString())

    override fun deleteTournament(tournament: Tournament, onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) {
        this.restClient.deleteTournament(tournament.id.toString(), onSuccess, onFailure)
    }

    override fun processCheckIns(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.restClient.processCheckIns(tournament.id.toString(), includeParticipants, includeMatches)

    override fun processCheckIns(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean, onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) {
        this.restClient.processCheckIns(tournament.id.toString(), includeParticipants, includeMatches, onSuccess, onFailure)
    }

    override fun abortCheckIn(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.restClient.abortCheckIn(tournament.id.toString(), includeParticipants, includeMatches)

    override fun abortCheckIn(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean, onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) {
        this.restClient.abortCheckIn(tournament.id.toString(), includeParticipants, includeMatches, onSuccess, onFailure)
    }

    override fun startTournament(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.restClient.startTournament(tournament.id.toString(), includeParticipants, includeMatches)

    override fun startTournament(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean, onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) {
        this.restClient.startTournament(tournament.id.toString(), includeParticipants, includeMatches, onSuccess, onFailure)
    }

    override fun finalizeTournament(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.restClient.finalizeTournament(tournament.id.toString(), includeParticipants, includeMatches)

    override fun finalizeTournament(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean, onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) {
        this.restClient.finalizeTournament(tournament.id.toString(), includeParticipants, includeMatches, onSuccess, onFailure)
    }

    override fun resetTournament(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.restClient.resetTournament(tournament.id.toString(), includeParticipants, includeMatches)

    override fun resetTournament(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean, onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) {
        this.restClient.resetTournament(tournament.id.toString(), includeParticipants, includeMatches, onSuccess, onFailure)
    }

    override fun openTournamentForPredictions(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.restClient.openTournamentForPredictions(tournament.id.toString(), includeParticipants, includeMatches)

    override fun openTournamentForPredictions(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean, onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) {
        this.restClient.openTournamentForPredictions(tournament.id.toString(), includeParticipants, includeMatches, onSuccess, onFailure)
    }
}