package at.stefangeyer.challonge.rest.retrofit

import at.stefangeyer.challonge.async.Callback
import at.stefangeyer.challonge.exception.DataAccessException
import at.stefangeyer.challonge.model.enum.MatchState
import at.stefangeyer.challonge.model.query.wrapper.MatchQueryWrapper
import at.stefangeyer.challonge.model.wrapper.MatchWrapper
import at.stefangeyer.challonge.rest.MatchRestClient
import retrofit2.Call
import retrofit2.Response

/**
 * Retrofit gson of the match rest client
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
class RetrofitMatchRestClient(private val challongeRetrofit: ChallongeRetrofit) : MatchRestClient {

    override fun getMatches(tournament: String, participantId: Long?, state: MatchState?): List<MatchWrapper> {
        val response = this.challongeRetrofit.getMatches(tournament, participantId, state).execute()
        return parseResponse("GetMatches", response)
    }

    override fun getMatches(tournament: String, participantId: Long?, state: MatchState?,
                            onSuccess: Callback<List<MatchWrapper>>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.getMatches(tournament, participantId, state).enqueue(
                object : retrofit2.Callback<List<MatchWrapper>> {
                    override fun onFailure(call: Call<List<MatchWrapper>>, t: Throwable) {
                        onFailure.accept(DataAccessException("GetMatches request was not successful", t))
                    }

                    override fun onResponse(call: Call<List<MatchWrapper>>, response: Response<List<MatchWrapper>>) {
                        onSuccess.accept(parseResponse("GetMatches", response))
                    }
                })
    }

    override fun getMatch(tournament: String, matchId: Long, includeAttachments: Boolean): MatchWrapper {
        val response = this.challongeRetrofit.getMatch(tournament, matchId, if (includeAttachments) 1 else 0).execute()
        return parseResponse("GetMatch", response)
    }

    override fun getMatch(tournament: String, matchId: Long, includeAttachments: Boolean,
                          onSuccess: Callback<MatchWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.getMatch(tournament, matchId, if (includeAttachments) 1 else 0)
                .enqueue(object : retrofit2.Callback<MatchWrapper> {
                    override fun onFailure(call: Call<MatchWrapper>, t: Throwable) {
                        onFailure.accept(DataAccessException("GetMatch request was not successful", t))
                    }

                    override fun onResponse(call: Call<MatchWrapper>, response: Response<MatchWrapper>) {
                        onSuccess.accept(parseResponse("GetMatch", response))
                    }
                })
    }

    override fun updateMatch(tournament: String, matchId: Long, match: MatchQueryWrapper): MatchWrapper {
        val response = this.challongeRetrofit.updateMatch(tournament, matchId, match).execute()
        return parseResponse("UpdateMatch", response)
    }

    override fun updateMatch(tournament: String, matchId: Long, match: MatchQueryWrapper,
                             onSuccess: Callback<MatchWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.updateMatch(tournament, matchId, match).enqueue(object : retrofit2.Callback<MatchWrapper> {
            override fun onFailure(call: Call<MatchWrapper>, t: Throwable) {
                onFailure.accept(DataAccessException("UpdateMatch request was not successful", t))
            }

            override fun onResponse(call: Call<MatchWrapper>, response: Response<MatchWrapper>) {
                onSuccess.accept(parseResponse("UpdateMatch", response))
            }
        })
    }

    override fun reopenMatch(tournament: String, matchId: Long): MatchWrapper {
        val response = this.challongeRetrofit.reopenMatch(tournament, matchId).execute()
        return parseResponse("ReopenMatch", response)
    }

    override fun reopenMatch(tournament: String, matchId: Long, onSuccess: Callback<MatchWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.reopenMatch(tournament, matchId).enqueue(object : retrofit2.Callback<MatchWrapper> {
            override fun onFailure(call: Call<MatchWrapper>, t: Throwable) {
                onFailure.accept(DataAccessException("ReopenMatch request was not successful", t))
            }

            override fun onResponse(call: Call<MatchWrapper>, response: Response<MatchWrapper>) {
                onSuccess.accept(parseResponse("ReopenMatch", response))
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