package at.stefangeyer.challonge.service.implementation

import at.stefangeyer.challonge.async.Callback
import at.stefangeyer.challonge.exception.DataAccessException
import at.stefangeyer.challonge.model.Participant
import at.stefangeyer.challonge.model.Tournament
import at.stefangeyer.challonge.model.query.ParticipantQuery
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
            this.restClient.getParticipants(tournament.id.toString())

    override fun getParticipants(tournament: Tournament, onSuccess: Callback<List<Participant>>, onFailure: Callback<DataAccessException>) {
        this.restClient.getParticipants(tournament.id.toString(), onSuccess, onFailure)
    }

    override fun getParticipant(tournament: Tournament, participantId: Long, includeMatches: Boolean): Participant =
            this.restClient.getParticipant(tournament.id.toString(), participantId, includeMatches)

    override fun getParticipant(tournament: Tournament, participantId: Long, includeMatches: Boolean, onSuccess: Callback<Participant>, onFailure: Callback<DataAccessException>) {
        this.restClient.getParticipant(tournament.id.toString(), participantId, includeMatches, onSuccess, onFailure)
    }

    override fun addParticipant(tournament: Tournament, data: ParticipantQuery): Participant {
        validateParticipantQuery(data)
        return this.restClient.addParticipant(tournament.id.toString(), data)
    }

    override fun addParticipant(tournament: Tournament, data: ParticipantQuery, onSuccess: Callback<Participant>, onFailure: Callback<DataAccessException>) {
        validateParticipantQuery(data)
        this.restClient.addParticipant(tournament.id.toString(), data, onSuccess, onFailure)
    }

    override fun bulkAddParticipants(tournament: Tournament, data: List<ParticipantQuery>): List<Participant> {
        for (query in data) {
            validateParticipantQuery(query)
        }
        return this.restClient.bulkAddParticipants(tournament.id.toString(), data)
    }

    override fun bulkAddParticipants(tournament: Tournament, data: List<ParticipantQuery>, onSuccess: Callback<List<Participant>>, onFailure: Callback<DataAccessException>) {
        for (query in data) {
            validateParticipantQuery(query)
        }
        this.restClient.bulkAddParticipants(tournament.id.toString(), data, onSuccess, onFailure)
    }

    override fun updateParticipant(participant: Participant, data: ParticipantQuery): Participant {
        validateParticipantQuery(data)
        return this.restClient.updateParticipant(participant.tournamentId.toString(), participant.id, data)
    }

    override fun updateParticipant(participant: Participant, data: ParticipantQuery, onSuccess: Callback<Participant>, onFailure: Callback<DataAccessException>) {
        validateParticipantQuery(data)
        return this.restClient.updateParticipant(participant.tournamentId.toString(), participant.id, data, onSuccess, onFailure)
    }

    override fun checkInParticipant(participant: Participant): Participant =
            this.restClient.checkInParticipant(participant.tournamentId.toString(), participant.id)

    override fun checkInParticipant(participant: Participant, onSuccess: Callback<Participant>, onFailure: Callback<DataAccessException>) {
        this.restClient.checkInParticipant(participant.tournamentId.toString(), participant.id, onSuccess, onFailure)
    }

    override fun undoCheckInParticipant(participant: Participant): Participant =
            this.restClient.undoCheckInParticipant(participant.tournamentId.toString(), participant.id)

    override fun undoCheckInParticipant(participant: Participant, onSuccess: Callback<Participant>, onFailure: Callback<DataAccessException>) {
        this.restClient.undoCheckInParticipant(participant.tournamentId.toString(), participant.id, onSuccess, onFailure)
    }

    override fun deleteParticipant(participant: Participant): Participant =
            this.restClient.deleteParticipant(participant.tournamentId.toString(), participant.id)

    override fun deleteParticipant(participant: Participant, onSuccess: Callback<Participant>, onFailure: Callback<DataAccessException>) {
        this.restClient.deleteParticipant(participant.tournamentId.toString(), participant.id, onSuccess, onFailure)
    }

    override fun randomizeParticipants(tournament: Tournament): List<Participant> =
            this.restClient.randomizeParticipants(tournament.id.toString())

    override fun randomizeParticipants(tournament: Tournament, onSuccess: Callback<List<Participant>>, onFailure: Callback<DataAccessException>) {
        this.restClient.randomizeParticipants(tournament.id.toString(), onSuccess, onFailure)
    }

    private fun validateParticipantQuery(data: ParticipantQuery) {
        if (data.name == null && data.email == null && data.challongeUsername == null &&
                data.seed == null && data.misc == null && data.inviteNameOrEmail == null) {
            throw IllegalArgumentException("All data parameters are null. Provide at least one")
        }
    }
}