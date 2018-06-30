package at.stefangeyer.challonge.service.implementation

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

    override fun getTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.restClient.getTournament(tournament, includeParticipants, includeMatches)

    override fun createTournament(data: TournamentQuery): Tournament = this.restClient.createTournament(data)

    override fun updateTournament(tournament: Tournament, data: TournamentQuery): Tournament =
            this.restClient.updateTournament(tournament.id.toString(), data)

    override fun deleteTournament(tournament: Tournament): Tournament =
            this.restClient.deleteTournament(tournament.id.toString())

    override fun processCheckIns(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.restClient.processCheckIns(tournament.id.toString(), includeParticipants, includeMatches)

    override fun abortCheckIn(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.restClient.abortCheckIn(tournament.id.toString(), includeParticipants, includeMatches)

    override fun startTournament(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.restClient.startTournament(tournament.id.toString(), includeParticipants, includeMatches)

    override fun finalizeTournament(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.restClient.finalizeTournament(tournament.id.toString(), includeParticipants, includeMatches)

    override fun resetTournament(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.restClient.resetTournament(tournament.id.toString(), includeParticipants, includeMatches)

    override fun openTournamentForPredictions(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.restClient.openTournamentForPredictions(tournament.id.toString(), includeParticipants, includeMatches)
}