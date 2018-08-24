package at.stefangeyer.challonge.rest.retrofit

import at.stefangeyer.challonge.async.Callback
import at.stefangeyer.challonge.exception.DataAccessException
import at.stefangeyer.challonge.model.query.wrapper.ParticipantQueryListWrapper
import at.stefangeyer.challonge.model.query.wrapper.ParticipantQueryWrapper
import at.stefangeyer.challonge.model.wrapper.ParticipantWrapper
import at.stefangeyer.challonge.rest.ParticipantRestClient
import retrofit2.Call
import retrofit2.Response

/**
 * Retrofit gson of the participant rest client
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
class RetrofitParticipantRestClient(private val challongeRetrofit: ChallongeRetrofit) : ParticipantRestClient {

    override fun getParticipants(tournament: String): List<ParticipantWrapper> {
        val response = this.challongeRetrofit.getParticipants(tournament).execute()
        return parseResponse("GetParticipants", response)
    }

    override fun getParticipants(tournament: String, onSuccess: Callback<List<ParticipantWrapper>>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.getParticipants(tournament).enqueue(object : retrofit2.Callback<List<ParticipantWrapper>> {
            override fun onFailure(call: Call<List<ParticipantWrapper>>, t: Throwable) {
                onFailure(DataAccessException("GetParticipants request was not successful", t))
            }

            override fun onResponse(call: Call<List<ParticipantWrapper>>, response: Response<List<ParticipantWrapper>>) {
                onSuccess(parseResponse("GetParticipants", response))
            }
        })
    }

    override fun getParticipant(tournament: String, participantId: Long, includeMatches: Boolean): ParticipantWrapper {
        val response = this.challongeRetrofit
                .getParticipant(tournament, participantId, if (includeMatches) 1 else 0)
                .execute()
        return parseResponse("GetParticipant", response)
    }

    override fun getParticipant(tournament: String, participantId: Long, includeMatches: Boolean,
                                onSuccess: Callback<ParticipantWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.getParticipant(tournament, participantId, if (includeMatches) 1 else 0)
                .enqueue(object : retrofit2.Callback<ParticipantWrapper> {
                    override fun onFailure(call: Call<ParticipantWrapper>, t: Throwable) {
                        onFailure(DataAccessException("GetParticipant request was not successful", t))
                    }

                    override fun onResponse(call: Call<ParticipantWrapper>, response: Response<ParticipantWrapper>) {
                        onSuccess(parseResponse("GetParticipant", response))
                    }
                })
    }

    override fun addParticipant(tournament: String, participant: ParticipantQueryWrapper): ParticipantWrapper {
        val response = this.challongeRetrofit.addParticipant(tournament, participant).execute()
        return parseResponse("AddParticipant", response)
    }

    override fun addParticipant(tournament: String, participant: ParticipantQueryWrapper,
                                onSuccess: Callback<ParticipantWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.addParticipant(tournament, participant).enqueue(object : retrofit2.Callback<ParticipantWrapper> {
            override fun onFailure(call: Call<ParticipantWrapper>, t: Throwable) {
                onFailure(DataAccessException("AddParticipant request was not successful", t))
            }

            override fun onResponse(call: Call<ParticipantWrapper>, response: Response<ParticipantWrapper>) {
                onSuccess(parseResponse("AddParticipant", response))
            }
        })
    }

    override fun bulkAddParticipants(tournament: String, participants: ParticipantQueryListWrapper): List<ParticipantWrapper> {
        val response = this.challongeRetrofit.bulkAddParticipants(tournament, participants).execute()
        return parseResponse("BulkAddParticipant", response)
    }

    override fun bulkAddParticipants(tournament: String, participants: ParticipantQueryListWrapper,
                                     onSuccess: Callback<List<ParticipantWrapper>>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.bulkAddParticipants(tournament, participants).enqueue(object : retrofit2.Callback<List<ParticipantWrapper>> {
            override fun onFailure(call: Call<List<ParticipantWrapper>>, t: Throwable) {
                onFailure(DataAccessException("BulkAddParticipant request was not successful", t))
            }

            override fun onResponse(call: Call<List<ParticipantWrapper>>, response: Response<List<ParticipantWrapper>>) {
                onSuccess(parseResponse("BulkAddParticipant", response))
            }
        })
    }

    override fun updateParticipant(tournament: String, participantId: Long, participant: ParticipantQueryWrapper): ParticipantWrapper {
        val response = this.challongeRetrofit.updateParticipant(tournament, participantId, participant).execute()
        return parseResponse("UpdateParticipant", response)
    }

    override fun updateParticipant(tournament: String, participantId: Long, participant: ParticipantQueryWrapper,
                                   onSuccess: Callback<ParticipantWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.updateParticipant(tournament, participantId, participant).enqueue(object : retrofit2.Callback<ParticipantWrapper> {
            override fun onFailure(call: Call<ParticipantWrapper>, t: Throwable) {
                onFailure(DataAccessException("UpdateParticipant request was not successful", t))
            }

            override fun onResponse(call: Call<ParticipantWrapper>, response: Response<ParticipantWrapper>) {
                onSuccess(parseResponse("UpdateParticipant", response))
            }
        })
    }

    override fun checkInParticipant(tournament: String, participantId: Long): ParticipantWrapper {
        val response = this.challongeRetrofit.checkInParticipant(tournament, participantId).execute()
        return parseResponse("CheckInParticipant", response)
    }

    override fun checkInParticipant(tournament: String, participantId: Long,
                                    onSuccess: Callback<ParticipantWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.checkInParticipant(tournament, participantId).enqueue(object : retrofit2.Callback<ParticipantWrapper> {
            override fun onFailure(call: Call<ParticipantWrapper>, t: Throwable) {
                onFailure(DataAccessException("CheckInParticipant request was not successful", t))
            }

            override fun onResponse(call: Call<ParticipantWrapper>, response: Response<ParticipantWrapper>) {
                onSuccess(parseResponse("CheckInParticipant", response))
            }
        })
    }

    override fun undoCheckInParticipant(tournament: String, participantId: Long): ParticipantWrapper {
        val response = this.challongeRetrofit.undoCheckInParticipant(tournament, participantId).execute()
        return parseResponse("UndoCheckInParticipant", response)
    }

    override fun undoCheckInParticipant(tournament: String, participantId: Long,
                                        onSuccess: Callback<ParticipantWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.undoCheckInParticipant(tournament, participantId).enqueue(object : retrofit2.Callback<ParticipantWrapper> {
            override fun onFailure(call: Call<ParticipantWrapper>, t: Throwable) {
                onFailure(DataAccessException("UndoCheckInParticipant request was not successful", t))
            }

            override fun onResponse(call: Call<ParticipantWrapper>, response: Response<ParticipantWrapper>) {
                onSuccess(parseResponse("UndoCheckInParticipant", response))
            }
        })
    }

    override fun deleteParticipant(tournament: String, participantId: Long): ParticipantWrapper {
        val response = this.challongeRetrofit.deleteParticipant(tournament, participantId).execute()
        return parseResponse("DeleteParticipant", response)
    }

    override fun deleteParticipant(tournament: String, participantId: Long,
                                   onSuccess: Callback<ParticipantWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.deleteParticipant(tournament, participantId).enqueue(object : retrofit2.Callback<ParticipantWrapper> {
            override fun onFailure(call: Call<ParticipantWrapper>, t: Throwable) {
                onFailure(DataAccessException("DeleteParticipant request was not successful", t))
            }

            override fun onResponse(call: Call<ParticipantWrapper>, response: Response<ParticipantWrapper>) {
                onSuccess(parseResponse("DeleteParticipant", response))
            }
        })
    }

    override fun randomizeParticipants(tournament: String): List<ParticipantWrapper> {
        val response = this.challongeRetrofit.randomizeParticipants(tournament).execute()
        return parseResponse("RandomizeParticipants", response)
    }

    override fun randomizeParticipants(tournament: String, onSuccess: Callback<List<ParticipantWrapper>>,
                                       onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.randomizeParticipants(tournament).enqueue(object : retrofit2.Callback<List<ParticipantWrapper>> {
            override fun onFailure(call: Call<List<ParticipantWrapper>>, t: Throwable) {
                onFailure(DataAccessException("RandomizeParticipants request was not successful", t))
            }

            override fun onResponse(call: Call<List<ParticipantWrapper>>, response: Response<List<ParticipantWrapper>>) {
                onSuccess(parseResponse("RandomizeParticipants", response))
            }
        })
    }

    private fun <T> parseResponse(action: String, response: Response<T>): T {
        if (!response.isSuccessful) {
            throw DataAccessException(action + " request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }
}