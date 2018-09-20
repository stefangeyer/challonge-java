package at.stefangeyer.challonge.service.implementation

import at.stefangeyer.challonge.async.Callback
import at.stefangeyer.challonge.exception.DataAccessException
import at.stefangeyer.challonge.model.Participant
import at.stefangeyer.challonge.model.Tournament
import at.stefangeyer.challonge.model.query.ParticipantQuery
import at.stefangeyer.challonge.model.query.wrapper.ParticipantQueryListWrapper
import at.stefangeyer.challonge.model.query.wrapper.ParticipantQueryWrapper
import at.stefangeyer.challonge.rest.ParticipantRestClient
import at.stefangeyer.challonge.service.ParticipantService

/**
 * Participant Service Implementation
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
class SimpleParticipantService(private val restClient: ParticipantRestClient) : ParticipantService {

    override fun getParticipants(tournament: Tournament): List<Participant> =
            this.restClient.getParticipants(tournament.id.toString()).map { pw -> pw.participant }

    override fun getParticipants(tournament: Tournament,
                                 onSuccess: Callback<List<Participant>>, onFailure: Callback<DataAccessException>) {
        this.restClient.getParticipants(tournament.id.toString(),
                Callback { list -> onSuccess.accept(list.map { pw -> pw.participant }) }, onFailure)
    }

    override fun getParticipant(tournament: Tournament, participantId: Long, includeMatches: Boolean): Participant =
            this.restClient.getParticipant(tournament.id.toString(), participantId, includeMatches).participant

    override fun getParticipant(tournament: Tournament, participantId: Long, includeMatches: Boolean,
                                onSuccess: Callback<Participant>, onFailure: Callback<DataAccessException>) {
        this.restClient.getParticipant(tournament.id.toString(), participantId, includeMatches,
                Callback { pw -> onSuccess.accept(pw.participant) }, onFailure)
    }

    override fun addParticipant(tournament: Tournament, data: ParticipantQuery): Participant {
        validateParticipantQuery(data)
        return this.restClient.addParticipant(tournament.id.toString(), ParticipantQueryWrapper(data)).participant
    }

    override fun addParticipant(tournament: Tournament, data: ParticipantQuery,
                                onSuccess: Callback<Participant>, onFailure: Callback<DataAccessException>) {
        validateParticipantQuery(data)
        this.restClient.addParticipant(tournament.id.toString(), ParticipantQueryWrapper(data),
                Callback { pw -> onSuccess.accept(pw.participant) }, onFailure)
    }

    override fun bulkAddParticipants(tournament: Tournament, data: List<ParticipantQuery>): List<Participant> {
        for (query in data) {
            validateParticipantQuery(query)
        }
        return this.restClient.bulkAddParticipants(tournament.id.toString(),
                ParticipantQueryListWrapper(data)).map { pq -> pq.participant }
    }

    override fun bulkAddParticipants(tournament: Tournament, data: List<ParticipantQuery>,
                                     onSuccess: Callback<List<Participant>>, onFailure: Callback<DataAccessException>) {
        for (query in data) {
            validateParticipantQuery(query)
        }
        this.restClient.bulkAddParticipants(
                tournament.id.toString(), ParticipantQueryListWrapper(data),
                Callback { list -> onSuccess.accept(list.map { pw -> pw.participant }) }, onFailure)
    }

    override fun updateParticipant(participant: Participant, data: ParticipantQuery): Participant {
        validateParticipantQuery(data)
        return this.restClient.updateParticipant(participant.tournamentId.toString(), participant.id,
                ParticipantQueryWrapper(data)).participant
    }

    override fun updateParticipant(participant: Participant, data: ParticipantQuery,
                                   onSuccess: Callback<Participant>, onFailure: Callback<DataAccessException>) {
        validateParticipantQuery(data)
        return this.restClient.updateParticipant(participant.tournamentId.toString(), participant.id,
                ParticipantQueryWrapper(data), Callback { pw -> onSuccess.accept(pw.participant) }, onFailure)
    }

    override fun checkInParticipant(participant: Participant): Participant =
            this.restClient.checkInParticipant(participant.tournamentId.toString(), participant.id).participant

    override fun checkInParticipant(participant: Participant,
                                    onSuccess: Callback<Participant>, onFailure: Callback<DataAccessException>) {
        this.restClient.checkInParticipant(participant.tournamentId.toString(), participant.id,
                Callback { pw -> onSuccess.accept(pw.participant) }, onFailure)
    }

    override fun undoCheckInParticipant(participant: Participant): Participant =
            this.restClient.undoCheckInParticipant(participant.tournamentId.toString(), participant.id).participant

    override fun undoCheckInParticipant(participant: Participant,
                                        onSuccess: Callback<Participant>, onFailure: Callback<DataAccessException>) {
        this.restClient.undoCheckInParticipant(participant.tournamentId.toString(), participant.id,
                Callback { pw -> onSuccess.accept(pw.participant) }, onFailure)
    }

    override fun deleteParticipant(participant: Participant): Participant =
            this.restClient.deleteParticipant(participant.tournamentId.toString(), participant.id).participant

    override fun deleteParticipant(participant: Participant,
                                   onSuccess: Callback<Participant>, onFailure: Callback<DataAccessException>) {
        this.restClient.deleteParticipant(participant.tournamentId.toString(), participant.id,
                Callback { pw -> onSuccess.accept(pw.participant) }, onFailure)
    }

    override fun randomizeParticipants(tournament: Tournament): List<Participant> =
            this.restClient.randomizeParticipants(tournament.id.toString()).map { pw -> pw.participant }

    override fun randomizeParticipants(tournament: Tournament,
                                       onSuccess: Callback<List<Participant>>, onFailure: Callback<DataAccessException>) {
        this.restClient.randomizeParticipants(tournament.id.toString(),
                Callback { list -> onSuccess.accept(list.map { pw -> pw.participant }) }, onFailure)
    }

    private fun validateParticipantQuery(data: ParticipantQuery) {
        if (data.name == null && data.email == null && data.challongeUsername == null &&
                data.seed == null && data.misc == null && data.inviteNameOrEmail == null) {
            throw IllegalArgumentException("All data parameters are null. Provide at least one")
        }
    }
}