package at.stefangeyer.challonge.rest.implementation.retrofit

import at.stefangeyer.challonge.exception.DataAccessException
import at.stefangeyer.challonge.model.Match
import at.stefangeyer.challonge.model.enumeration.MatchState
import at.stefangeyer.challonge.model.query.MatchQuery
import at.stefangeyer.challonge.rest.MatchRestClient
import at.stefangeyer.challonge.rest.client.retrofit.ChallongeRetrofit

/**
 * Retrofit gson of the match rest client
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
class RetrofitMatchRestClient(private val challongeRetrofit: ChallongeRetrofit) : MatchRestClient {

    override fun getMatches(tournament: String, participantId: Long?, state: MatchState?): List<Match> {
        val response = this.challongeRetrofit.getMatches(tournament, participantId, state).execute()

        if (!response.isSuccessful) {
            throw DataAccessException("GetMatches request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }

    override fun getMatch(tournament: String, matchId: Long, includeAttachments: Boolean): Match {
        val response = this.challongeRetrofit.getMatch(tournament, matchId, if (includeAttachments) 1 else 0).execute()

        if (!response.isSuccessful) {
            throw DataAccessException("GetMatch request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }

    override fun updateMatch(tournament: String, matchId: Long, match: MatchQuery): Match {
        val response = this.challongeRetrofit.updateMatch(tournament, matchId, match).execute()

        if (!response.isSuccessful) {
            throw DataAccessException("UpdateMatch request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }

    override fun reopenMatch(tournament: String, matchId: Long): Match {
        val response = this.challongeRetrofit.reopenMatch(tournament, matchId).execute()

        if (!response.isSuccessful) {
            throw DataAccessException("ReopenMatch request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }
}