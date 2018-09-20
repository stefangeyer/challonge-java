package at.stefangeyer.challonge.rest.retrofit

import at.stefangeyer.challonge.async.Callback
import at.stefangeyer.challonge.exception.DataAccessException
import at.stefangeyer.challonge.model.query.wrapper.ParticipantQueryListWrapper
import at.stefangeyer.challonge.model.query.wrapper.ParticipantQueryWrapper
import at.stefangeyer.challonge.model.wrapper.ParticipantWrapper
import at.stefangeyer.challonge.rest.ParticipantRestClient
import at.stefangeyer.challonge.rest.retrofit.util.parse
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
        return response.parse("GetParticipants")
    }

    override fun getParticipants(tournament: String, onSuccess: Callback<List<ParticipantWrapper>>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.getParticipants(tournament).enqueue(object : retrofit2.Callback<List<ParticipantWrapper>> {
            override fun onFailure(call: Call<List<ParticipantWrapper>>, t: Throwable) {
                onFailure.accept(DataAccessException("GetParticipants request was not successful", t))
            }

            override fun onResponse(call: Call<List<ParticipantWrapper>>, response: Response<List<ParticipantWrapper>>) {
                onSuccess.accept(response.parse("GetParticipants"))
            }
        })
    }

    override fun getParticipant(tournament: String, participantId: Long, includeMatches: Boolean): ParticipantWrapper {
        val response = this.challongeRetrofit
                .getParticipant(tournament, participantId, if (includeMatches) 1 else 0)
                .execute()
        return response.parse("GetParticipant")
    }

    override fun getParticipant(tournament: String, participantId: Long, includeMatches: Boolean,
                                onSuccess: Callback<ParticipantWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.getParticipant(tournament, participantId, if (includeMatches) 1 else 0)
                .enqueue(object : retrofit2.Callback<ParticipantWrapper> {
                    override fun onFailure(call: Call<ParticipantWrapper>, t: Throwable) {
                        onFailure.accept(DataAccessException("GetParticipant request was not successful", t))
                    }

                    override fun onResponse(call: Call<ParticipantWrapper>, response: Response<ParticipantWrapper>) {
                        onSuccess.accept(response.parse("GetParticipant"))
                    }
                })
    }

    override fun addParticipant(tournament: String, participant: ParticipantQueryWrapper): ParticipantWrapper {
        val response = this.challongeRetrofit.addParticipant(tournament, participant).execute()
        return response.parse("AddParticipant")
    }

    override fun addParticipant(tournament: String, participant: ParticipantQueryWrapper,
                                onSuccess: Callback<ParticipantWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.addParticipant(tournament, participant).enqueue(object : retrofit2.Callback<ParticipantWrapper> {
            override fun onFailure(call: Call<ParticipantWrapper>, t: Throwable) {
                onFailure.accept(DataAccessException("AddParticipant request was not successful", t))
            }

            override fun onResponse(call: Call<ParticipantWrapper>, response: Response<ParticipantWrapper>) {
                onSuccess.accept(response.parse("AddParticipant"))
            }
        })
    }

    override fun bulkAddParticipants(tournament: String, participants: ParticipantQueryListWrapper): List<ParticipantWrapper> {
        val response = this.challongeRetrofit.bulkAddParticipants(tournament, participants).execute()
        return response.parse("BulkAddParticipant")
    }

    override fun bulkAddParticipants(tournament: String, participants: ParticipantQueryListWrapper,
                                     onSuccess: Callback<List<ParticipantWrapper>>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.bulkAddParticipants(tournament, participants).enqueue(object : retrofit2.Callback<List<ParticipantWrapper>> {
            override fun onFailure(call: Call<List<ParticipantWrapper>>, t: Throwable) {
                onFailure.accept(DataAccessException("BulkAddParticipant request was not successful", t))
            }

            override fun onResponse(call: Call<List<ParticipantWrapper>>, response: Response<List<ParticipantWrapper>>) {
                onSuccess.accept(response.parse("BulkAddParticipant"))
            }
        })
    }

    override fun updateParticipant(tournament: String, participantId: Long, participant: ParticipantQueryWrapper): ParticipantWrapper {
        val response = this.challongeRetrofit.updateParticipant(tournament, participantId, participant).execute()
        return response.parse("UpdateParticipant")
    }

    override fun updateParticipant(tournament: String, participantId: Long, participant: ParticipantQueryWrapper,
                                   onSuccess: Callback<ParticipantWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.updateParticipant(tournament, participantId, participant).enqueue(object : retrofit2.Callback<ParticipantWrapper> {
            override fun onFailure(call: Call<ParticipantWrapper>, t: Throwable) {
                onFailure.accept(DataAccessException("UpdateParticipant request was not successful", t))
            }

            override fun onResponse(call: Call<ParticipantWrapper>, response: Response<ParticipantWrapper>) {
                onSuccess.accept(response.parse("UpdateParticipant"))
            }
        })
    }

    override fun checkInParticipant(tournament: String, participantId: Long): ParticipantWrapper {
        val response = this.challongeRetrofit.checkInParticipant(tournament, participantId).execute()
        return response.parse("CheckInParticipant")
    }

    override fun checkInParticipant(tournament: String, participantId: Long,
                                    onSuccess: Callback<ParticipantWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.checkInParticipant(tournament, participantId).enqueue(object : retrofit2.Callback<ParticipantWrapper> {
            override fun onFailure(call: Call<ParticipantWrapper>, t: Throwable) {
                onFailure.accept(DataAccessException("CheckInParticipant request was not successful", t))
            }

            override fun onResponse(call: Call<ParticipantWrapper>, response: Response<ParticipantWrapper>) {
                onSuccess.accept(response.parse("CheckInParticipant"))
            }
        })
    }

    override fun undoCheckInParticipant(tournament: String, participantId: Long): ParticipantWrapper {
        val response = this.challongeRetrofit.undoCheckInParticipant(tournament, participantId).execute()
        return response.parse("UndoCheckInParticipant")
    }

    override fun undoCheckInParticipant(tournament: String, participantId: Long,
                                        onSuccess: Callback<ParticipantWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.undoCheckInParticipant(tournament, participantId).enqueue(object : retrofit2.Callback<ParticipantWrapper> {
            override fun onFailure(call: Call<ParticipantWrapper>, t: Throwable) {
                onFailure.accept(DataAccessException("UndoCheckInParticipant request was not successful", t))
            }

            override fun onResponse(call: Call<ParticipantWrapper>, response: Response<ParticipantWrapper>) {
                onSuccess.accept(response.parse("UndoCheckInParticipant"))
            }
        })
    }

    override fun deleteParticipant(tournament: String, participantId: Long): ParticipantWrapper {
        val response = this.challongeRetrofit.deleteParticipant(tournament, participantId).execute()
        return response.parse("DeleteParticipant")
    }

    override fun deleteParticipant(tournament: String, participantId: Long,
                                   onSuccess: Callback<ParticipantWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.deleteParticipant(tournament, participantId).enqueue(object : retrofit2.Callback<ParticipantWrapper> {
            override fun onFailure(call: Call<ParticipantWrapper>, t: Throwable) {
                onFailure.accept(DataAccessException("DeleteParticipant request was not successful", t))
            }

            override fun onResponse(call: Call<ParticipantWrapper>, response: Response<ParticipantWrapper>) {
                onSuccess.accept(response.parse("DeleteParticipant"))
            }
        })
    }

    override fun randomizeParticipants(tournament: String): List<ParticipantWrapper> {
        val response = this.challongeRetrofit.randomizeParticipants(tournament).execute()
        return response.parse("RandomizeParticipants")
    }

    override fun randomizeParticipants(tournament: String, onSuccess: Callback<List<ParticipantWrapper>>,
                                       onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.randomizeParticipants(tournament).enqueue(object : retrofit2.Callback<List<ParticipantWrapper>> {
            override fun onFailure(call: Call<List<ParticipantWrapper>>, t: Throwable) {
                onFailure.accept(DataAccessException("RandomizeParticipants request was not successful", t))
            }

            override fun onResponse(call: Call<List<ParticipantWrapper>>, response: Response<List<ParticipantWrapper>>) {
                onSuccess.accept(response.parse("RandomizeParticipants"))
            }
        })
    }
}