package at.stefangeyer.challonge.rest.implementation.retrofit

import at.stefangeyer.challonge.model.Tournament
import at.stefangeyer.challonge.model.enumeration.TournamentType
import at.stefangeyer.challonge.model.enumeration.query.TournamentQueryState
import at.stefangeyer.challonge.model.query.TournamentQuery
import at.stefangeyer.challonge.rest.TournamentRestClient
import at.stefangeyer.challonge.rest.client.retrofit.ChallongeRetrofit
import at.stefangeyer.challonge.exception.DataAccessException
import java.time.OffsetDateTime

/**
 * Retrofit gson of the tournament rest client
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
class RetrofitTournamentRestClient(private val challongeRetrofit: ChallongeRetrofit) : TournamentRestClient {

    override fun getTournaments(state: TournamentQueryState?, type: TournamentType?, createdAfter: OffsetDateTime?,
                                createdBefore: OffsetDateTime?, subdomain: String?): List<Tournament> {
        val response = this.challongeRetrofit.getTournaments(state, type, createdAfter, createdBefore, subdomain).execute()

        if (!response.isSuccessful) {
            throw DataAccessException("GetTournaments request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }

    override fun getTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): Tournament {
        val response = this.challongeRetrofit
                .getTournament(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .execute()

        if (!response.isSuccessful) {
            throw DataAccessException("GetTournament request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }

    override fun createTournament(tournamentData: TournamentQuery): Tournament {
        val response = this.challongeRetrofit.createTournament(tournamentData).execute()

        if (!response.isSuccessful) {
            throw DataAccessException("CreateTournament request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }

    override fun updateTournament(tournament: String, tournamentData: TournamentQuery): Tournament {
        val response = this.challongeRetrofit.updateTournament(tournament, tournamentData).execute()

        if (!response.isSuccessful) {
            throw DataAccessException("UpdateTournament request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }

    override fun deleteTournament(tournament: String): Tournament {
        val response = this.challongeRetrofit.deleteTournament(tournament).execute()

        if (!response.isSuccessful) {
            throw DataAccessException("DeleteTournament request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }

    override fun processCheckIns(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): Tournament {
        val response = this.challongeRetrofit
                .processCheckIns(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .execute()

        if (!response.isSuccessful) {
            throw DataAccessException("ProcessCheckIns request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }

    override fun abortCheckIn(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): Tournament {
        val response = this.challongeRetrofit
                .abortCheckIn(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .execute()

        if (!response.isSuccessful) {
            throw DataAccessException("AbortCheckIns request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }

    override fun startTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): Tournament {
        val response = this.challongeRetrofit
                .startTournament(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .execute()

        if (!response.isSuccessful) {
            throw DataAccessException("StartTournament request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }

    override fun finalizeTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): Tournament {
        val response = this.challongeRetrofit
                .finalizeTournament(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .execute()

        if (!response.isSuccessful) {
            throw DataAccessException("FinalizeTournament request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }

    override fun resetTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): Tournament {
        val response = this.challongeRetrofit
                .resetTournament(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .execute()

        if (!response.isSuccessful) {
            throw DataAccessException("ResetTournament request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }

    override fun openTournamentForPredictions(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): Tournament {
        val response = this.challongeRetrofit
                .openTournamentForPredictions(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .execute()

        if (!response.isSuccessful) {
            throw DataAccessException("OpenForPredictions request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }
}