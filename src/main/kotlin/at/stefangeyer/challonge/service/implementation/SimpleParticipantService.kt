package at.stefangeyer.challonge.service.implementation

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

    override fun getParticipant(tournament: Tournament, participantId: Long, includeMatches: Boolean): Participant =
            this.restClient.getParticipant(tournament.id.toString(), participantId, includeMatches)

    override fun addParticipant(tournament: Tournament, data: ParticipantQuery): Participant {
        if (data.name == null && data.email == null && data.challongeUsername == null &&
                data.seed == null && data.misc == null && data.inviteNameOrEmail == null) {
            throw IllegalArgumentException("All data parameters are null. Provide at least one")
        }
        return this.restClient.addParticipant(tournament.id.toString(), data)
    }

    override fun bulkAddParticipants(tournament: Tournament, data: List<ParticipantQuery>): List<Participant> {
        for (query in data) {
            if (query.name == null && query.email == null && query.challongeUsername == null &&
                    query.seed == null && query.misc == null && query.inviteNameOrEmail == null) {
                throw IllegalArgumentException("All data parameters are null. Provide at least one")
            }
        }
        return this.restClient.bulkAddParticipants(tournament.id.toString(), data)
    }

    override fun updateParticipant(participant: Participant, data: ParticipantQuery): Participant {
        if (data.name == null && data.email == null && data.challongeUsername == null &&
                data.seed == null && data.misc == null && data.inviteNameOrEmail == null) {
            throw IllegalArgumentException("All data parameters are null. Provide at least one")
        }
        return this.restClient.updateParticipant(participant.tournamentId.toString(), participant.id, data)
    }

    override fun checkInParticipant(participant: Participant): Participant =
            this.restClient.checkInParticipant(participant.tournamentId.toString(), participant.id)

    override fun undoCheckInParticipant(participant: Participant): Participant =
            this.restClient.undoCheckInParticipant(participant.tournamentId.toString(), participant.id)

    override fun deleteParticipant(participant: Participant): Participant =
            this.restClient.deleteParticipant(participant.tournamentId.toString(), participant.id)

    override fun randomizeParticipants(tournament: Tournament): List<Participant> =
            this.restClient.randomizeParticipants(tournament.id.toString())
}