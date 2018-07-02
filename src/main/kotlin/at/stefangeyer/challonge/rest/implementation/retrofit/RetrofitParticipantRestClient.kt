package at.stefangeyer.challonge.rest.implementation.retrofit

import at.stefangeyer.challonge.model.Participant
import at.stefangeyer.challonge.model.query.ParticipantQuery
import at.stefangeyer.challonge.rest.ParticipantRestClient
import at.stefangeyer.challonge.rest.client.retrofit.ChallongeRetrofit
import at.stefangeyer.challonge.exception.DataAccessException

/**
 * Retrofit gson of the participant rest client
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
class RetrofitParticipantRestClient(private val challongeRetrofit: ChallongeRetrofit): ParticipantRestClient {

    override fun getParticipants(tournament: String): List<Participant> {
        val response = this.challongeRetrofit.getParticipants(tournament).execute()

        if (!response.isSuccessful) {
            throw DataAccessException("GetAttachments request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }

    override fun getParticipant(tournament: String, participantId: Long, includeMatches: Boolean): Participant {
        val response = this.challongeRetrofit
                .getParticipant(tournament, participantId, if (includeMatches) 1 else 0)
                .execute()

        if (!response.isSuccessful) {
            throw DataAccessException("GetAttachments request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }

    override fun addParticipant(tournament: String, participant: ParticipantQuery): Participant {
        val response = this.challongeRetrofit.addParticipant(tournament, participant).execute()

        if (!response.isSuccessful) {
            throw DataAccessException("GetAttachments request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }

    override fun bulkAddParticipants(tournament: String, participants: List<ParticipantQuery>): List<Participant> {
        val response = this.challongeRetrofit.bulkAddParticipants(tournament, participants).execute()

        if (!response.isSuccessful) {
            throw DataAccessException("GetAttachments request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }

    override fun updateParticipant(tournament: String, participantId: Long, participant: ParticipantQuery): Participant {
        val response = this.challongeRetrofit.updateParticipant(tournament, participantId, participant).execute()

        if (!response.isSuccessful) {
            throw DataAccessException("GetAttachments request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }

    override fun checkInParticipant(tournament: String, participantId: Long): Participant {
        val response = this.challongeRetrofit.checkInParticipant(tournament, participantId).execute()

        if (!response.isSuccessful) {
            throw DataAccessException("GetAttachments request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }

    override fun undoCheckInParticipant(tournament: String, participantId: Long): Participant {
        val response = this.challongeRetrofit.undoCheckInParticipant(tournament, participantId).execute()

        if (!response.isSuccessful) {
            throw DataAccessException("GetAttachments request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }

    override fun deleteParticipant(tournament: String, participantId: Long): Participant {
        val response = this.challongeRetrofit.deleteParticipant(tournament, participantId).execute()

        if (!response.isSuccessful) {
            throw DataAccessException("GetAttachments request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }

    override fun randomizeParticipants(tournament: String): List<Participant> {
        val response = this.challongeRetrofit.randomizeParticipants(tournament).execute()

        if (!response.isSuccessful) {
            throw DataAccessException("GetAttachments request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }
}