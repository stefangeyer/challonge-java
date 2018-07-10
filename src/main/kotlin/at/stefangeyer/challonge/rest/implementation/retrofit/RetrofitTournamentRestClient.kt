package at.stefangeyer.challonge.rest.implementation.retrofit

import at.stefangeyer.challonge.async.Callback
import at.stefangeyer.challonge.exception.DataAccessException
import at.stefangeyer.challonge.model.enum.TournamentType
import at.stefangeyer.challonge.model.query.enum.TournamentQueryState
import at.stefangeyer.challonge.model.query.wrapper.TournamentQueryWrapper
import at.stefangeyer.challonge.model.wrapper.TournamentWrapper
import at.stefangeyer.challonge.rest.TournamentRestClient
import retrofit2.Call
import retrofit2.Response
import java.time.OffsetDateTime

/**
 * Retrofit gson of the tournament rest client
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
class RetrofitTournamentRestClient(private val challongeRetrofit: ChallongeRetrofit) : TournamentRestClient {

    override fun getTournaments(state: TournamentQueryState?, type: TournamentType?, createdAfter: OffsetDateTime?,
                                createdBefore: OffsetDateTime?, subdomain: String?): List<TournamentWrapper> {
        val response = this.challongeRetrofit.getTournaments(state, type, createdAfter, createdBefore, subdomain).execute()
        return parseResponse("GetTournaments", response)
    }

    override fun getTournaments(state: TournamentQueryState?, type: TournamentType?, createdAfter: OffsetDateTime?, createdBefore: OffsetDateTime?, subdomain: String?,
                                onSuccess: Callback<List<TournamentWrapper>>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.getTournaments(state, type, createdAfter, createdBefore, subdomain).enqueue(
                object : retrofit2.Callback<List<TournamentWrapper>> {
                    override fun onFailure(call: Call<List<TournamentWrapper>>, t: Throwable) {
                        onFailure(DataAccessException("GetTournaments request was not successful", t))
                    }

                    override fun onResponse(call: Call<List<TournamentWrapper>>, response: Response<List<TournamentWrapper>>) {
                        onSuccess(parseResponse("GetTournaments", response))
                    }
                })
    }

    override fun getTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): TournamentWrapper {
        val response = this.challongeRetrofit
                .getTournament(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .execute()
        return parseResponse("GetTournament", response)
    }

    override fun getTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean,
                               onSuccess: Callback<TournamentWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.getTournament(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .enqueue(object : retrofit2.Callback<TournamentWrapper> {
                    override fun onFailure(call: Call<TournamentWrapper>, t: Throwable) {
                        onFailure(DataAccessException("GetTournament request was not successful", t))
                    }

                    override fun onResponse(call: Call<TournamentWrapper>, response: Response<TournamentWrapper>) {
                        onSuccess(parseResponse("GetTournament", response))
                    }
                })
    }

    override fun createTournament(tournamentData: TournamentQueryWrapper): TournamentWrapper {
        val response = this.challongeRetrofit.createTournament(tournamentData).execute()
        return parseResponse("CreateTournament", response)
    }

    override fun createTournament(tournamentData: TournamentQueryWrapper, onSuccess: Callback<TournamentWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.createTournament(tournamentData).enqueue(object : retrofit2.Callback<TournamentWrapper> {
            override fun onFailure(call: Call<TournamentWrapper>, t: Throwable) {
                onFailure(DataAccessException("CreateTournament request was not successful", t))
            }

            override fun onResponse(call: Call<TournamentWrapper>, response: Response<TournamentWrapper>) {
                onSuccess(parseResponse("CreateTournament", response))
            }
        })
    }

    override fun updateTournament(tournament: String, tournamentData: TournamentQueryWrapper): TournamentWrapper {
        val response = this.challongeRetrofit.updateTournament(tournament, tournamentData).execute()
        return parseResponse("UpdateTournament", response)
    }

    override fun updateTournament(tournament: String, tournamentData: TournamentQueryWrapper,
                                  onSuccess: Callback<TournamentWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.updateTournament(tournament, tournamentData).enqueue(object : retrofit2.Callback<TournamentWrapper> {
            override fun onFailure(call: Call<TournamentWrapper>, t: Throwable) {
                onFailure(DataAccessException("UpdateTournament request was not successful", t))
            }

            override fun onResponse(call: Call<TournamentWrapper>, response: Response<TournamentWrapper>) {
                onSuccess(parseResponse("UpdateTournament", response))
            }
        })
    }

    override fun deleteTournament(tournament: String): TournamentWrapper {
        val response = this.challongeRetrofit.deleteTournament(tournament).execute()
        return parseResponse("DeleteTournament", response)
    }

    override fun deleteTournament(tournament: String, onSuccess: Callback<TournamentWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.deleteTournament(tournament).enqueue(object : retrofit2.Callback<TournamentWrapper> {
            override fun onFailure(call: Call<TournamentWrapper>, t: Throwable) {
                onFailure(DataAccessException("DeleteTournament request was not successful", t))
            }

            override fun onResponse(call: Call<TournamentWrapper>, response: Response<TournamentWrapper>) {
                onSuccess(parseResponse("DeleteTournament", response))
            }
        })
    }

    override fun processCheckIns(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): TournamentWrapper {
        val response = this.challongeRetrofit
                .processCheckIns(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .execute()
        return parseResponse("ProcessCheckIns", response)
    }

    override fun processCheckIns(tournament: String, includeParticipants: Boolean, includeMatches: Boolean,
                                 onSuccess: Callback<TournamentWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.processCheckIns(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .enqueue(object : retrofit2.Callback<TournamentWrapper> {
                    override fun onFailure(call: Call<TournamentWrapper>, t: Throwable) {
                        onFailure(DataAccessException("ProcessCheckIns request was not successful", t))
                    }

                    override fun onResponse(call: Call<TournamentWrapper>, response: Response<TournamentWrapper>) {
                        onSuccess(parseResponse("ProcessCheckIns", response))
                    }
                })
    }

    override fun abortCheckIn(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): TournamentWrapper {
        val response = this.challongeRetrofit
                .abortCheckIn(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .execute()
        return parseResponse("AbortCheckIns", response)
    }

    override fun abortCheckIn(tournament: String, includeParticipants: Boolean, includeMatches: Boolean,
                              onSuccess: Callback<TournamentWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.abortCheckIn(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .enqueue(object : retrofit2.Callback<TournamentWrapper> {
                    override fun onFailure(call: Call<TournamentWrapper>, t: Throwable) {
                        onFailure(DataAccessException("AbortCheckIns request was not successful", t))
                    }

                    override fun onResponse(call: Call<TournamentWrapper>, response: Response<TournamentWrapper>) {
                        onSuccess(parseResponse("AbortCheckIns", response))
                    }
                })
    }

    override fun startTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): TournamentWrapper {
        val response = this.challongeRetrofit
                .startTournament(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .execute()
        return parseResponse("StartTournament", response)
    }

    override fun startTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean,
                                 onSuccess: Callback<TournamentWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.startTournament(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .enqueue(object : retrofit2.Callback<TournamentWrapper> {
                    override fun onFailure(call: Call<TournamentWrapper>, t: Throwable) {
                        onFailure(DataAccessException("StartTournament request was not successful", t))
                    }

                    override fun onResponse(call: Call<TournamentWrapper>, response: Response<TournamentWrapper>) {
                        onSuccess(parseResponse("StartTournament", response))
                    }
                })
    }

    override fun finalizeTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): TournamentWrapper {
        val response = this.challongeRetrofit
                .finalizeTournament(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .execute()
        return parseResponse("FinalizeTournament", response)
    }

    override fun finalizeTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean,
                                    onSuccess: Callback<TournamentWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.finalizeTournament(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .enqueue(object : retrofit2.Callback<TournamentWrapper> {
                    override fun onFailure(call: Call<TournamentWrapper>, t: Throwable) {
                        onFailure(DataAccessException("FinalizeTournament request was not successful", t))
                    }

                    override fun onResponse(call: Call<TournamentWrapper>, response: Response<TournamentWrapper>) {
                        onSuccess(parseResponse("FinalizeTournament", response))
                    }
                })
    }

    override fun resetTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): TournamentWrapper {
        val response = this.challongeRetrofit
                .resetTournament(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .execute()
        return parseResponse("ResetTournament", response)
    }

    override fun resetTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean,
                                 onSuccess: Callback<TournamentWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.finalizeTournament(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .enqueue(object : retrofit2.Callback<TournamentWrapper> {
                    override fun onFailure(call: Call<TournamentWrapper>, t: Throwable) {
                        onFailure(DataAccessException("ResetTournament request was not successful", t))
                    }

                    override fun onResponse(call: Call<TournamentWrapper>, response: Response<TournamentWrapper>) {
                        onSuccess(parseResponse("ResetTournament", response))
                    }
                })
    }

    override fun openTournamentForPredictions(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): TournamentWrapper {
        val response = this.challongeRetrofit
                .openTournamentForPredictions(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .execute()
        return parseResponse("OpenTournamentForPredictions", response)
    }

    override fun openTournamentForPredictions(tournament: String, includeParticipants: Boolean, includeMatches: Boolean,
                                              onSuccess: Callback<TournamentWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.finalizeTournament(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .enqueue(object : retrofit2.Callback<TournamentWrapper> {
                    override fun onFailure(call: Call<TournamentWrapper>, t: Throwable) {
                        onFailure(DataAccessException("OpenTournamentForPredictions request was not successful", t))
                    }

                    override fun onResponse(call: Call<TournamentWrapper>, response: Response<TournamentWrapper>) {
                        onSuccess(parseResponse("OpenTournamentForPredictions", response))
                    }
                })
    }

    private fun <T> parseResponse(action: String, response: Response<T>): T {
        if (!response.isSuccessful) {
            throw DataAccessException(action + " request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody()?.string())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }
}