package at.stefangeyer.challonge.rest.retrofit

import at.stefangeyer.challonge.async.Callback
import at.stefangeyer.challonge.exception.DataAccessException
import at.stefangeyer.challonge.model.enum.TournamentType
import at.stefangeyer.challonge.model.query.enum.TournamentQueryState
import at.stefangeyer.challonge.model.query.wrapper.TournamentQueryWrapper
import at.stefangeyer.challonge.model.wrapper.TournamentWrapper
import at.stefangeyer.challonge.rest.TournamentRestClient
import at.stefangeyer.challonge.rest.retrofit.util.parse
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
        return response.parse("GetTournaments")
    }

    override fun getTournaments(state: TournamentQueryState?, type: TournamentType?, createdAfter: OffsetDateTime?, createdBefore: OffsetDateTime?, subdomain: String?,
                                onSuccess: Callback<List<TournamentWrapper>>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.getTournaments(state, type, createdAfter, createdBefore, subdomain).enqueue(
                object : retrofit2.Callback<List<TournamentWrapper>> {
                    override fun onFailure(call: Call<List<TournamentWrapper>>, t: Throwable) {
                        onFailure.accept(DataAccessException("GetTournaments request was not successful", t))
                    }

                    override fun onResponse(call: Call<List<TournamentWrapper>>, response: Response<List<TournamentWrapper>>) {
                        onSuccess.accept(response.parse("GetTournaments"))
                    }
                })
    }

    override fun getTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): TournamentWrapper {
        val response = this.challongeRetrofit
                .getTournament(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .execute()
        return response.parse("GetTournament")
    }

    override fun getTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean,
                               onSuccess: Callback<TournamentWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.getTournament(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .enqueue(object : retrofit2.Callback<TournamentWrapper> {
                    override fun onFailure(call: Call<TournamentWrapper>, t: Throwable) {
                        onFailure.accept(DataAccessException("GetTournament request was not successful", t))
                    }

                    override fun onResponse(call: Call<TournamentWrapper>, response: Response<TournamentWrapper>) {
                        onSuccess.accept(response.parse("GetTournament"))
                    }
                })
    }

    override fun createTournament(tournamentData: TournamentQueryWrapper): TournamentWrapper {
        val response = this.challongeRetrofit.createTournament(tournamentData).execute()
        return response.parse("CreateTournament")
    }

    override fun createTournament(tournamentData: TournamentQueryWrapper, onSuccess: Callback<TournamentWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.createTournament(tournamentData).enqueue(object : retrofit2.Callback<TournamentWrapper> {
            override fun onFailure(call: Call<TournamentWrapper>, t: Throwable) {
                onFailure.accept(DataAccessException("CreateTournament request was not successful", t))
            }

            override fun onResponse(call: Call<TournamentWrapper>, response: Response<TournamentWrapper>) {
                onSuccess.accept(response.parse("CreateTournament"))
            }
        })
    }

    override fun updateTournament(tournament: String, tournamentData: TournamentQueryWrapper): TournamentWrapper {
        val response = this.challongeRetrofit.updateTournament(tournament, tournamentData).execute()
        return response.parse("UpdateTournament")
    }

    override fun updateTournament(tournament: String, tournamentData: TournamentQueryWrapper,
                                  onSuccess: Callback<TournamentWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.updateTournament(tournament, tournamentData).enqueue(object : retrofit2.Callback<TournamentWrapper> {
            override fun onFailure(call: Call<TournamentWrapper>, t: Throwable) {
                onFailure.accept(DataAccessException("UpdateTournament request was not successful", t))
            }

            override fun onResponse(call: Call<TournamentWrapper>, response: Response<TournamentWrapper>) {
                onSuccess.accept(response.parse("UpdateTournament"))
            }
        })
    }

    override fun deleteTournament(tournament: String): TournamentWrapper {
        val response = this.challongeRetrofit.deleteTournament(tournament).execute()
        return response.parse("DeleteTournament")
    }

    override fun deleteTournament(tournament: String, onSuccess: Callback<TournamentWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.deleteTournament(tournament).enqueue(object : retrofit2.Callback<TournamentWrapper> {
            override fun onFailure(call: Call<TournamentWrapper>, t: Throwable) {
                onFailure.accept(DataAccessException("DeleteTournament request was not successful", t))
            }

            override fun onResponse(call: Call<TournamentWrapper>, response: Response<TournamentWrapper>) {
                onSuccess.accept(response.parse("DeleteTournament"))
            }
        })
    }

    override fun processCheckIns(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): TournamentWrapper {
        val response = this.challongeRetrofit
                .processCheckIns(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .execute()
        return response.parse("ProcessCheckIns")
    }

    override fun processCheckIns(tournament: String, includeParticipants: Boolean, includeMatches: Boolean,
                                 onSuccess: Callback<TournamentWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.processCheckIns(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .enqueue(object : retrofit2.Callback<TournamentWrapper> {
                    override fun onFailure(call: Call<TournamentWrapper>, t: Throwable) {
                        onFailure.accept(DataAccessException("ProcessCheckIns request was not successful", t))
                    }

                    override fun onResponse(call: Call<TournamentWrapper>, response: Response<TournamentWrapper>) {
                        onSuccess.accept(response.parse("ProcessCheckIns"))
                    }
                })
    }

    override fun abortCheckIn(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): TournamentWrapper {
        val response = this.challongeRetrofit
                .abortCheckIn(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .execute()
        return response.parse("AbortCheckIns")
    }

    override fun abortCheckIn(tournament: String, includeParticipants: Boolean, includeMatches: Boolean,
                              onSuccess: Callback<TournamentWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.abortCheckIn(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .enqueue(object : retrofit2.Callback<TournamentWrapper> {
                    override fun onFailure(call: Call<TournamentWrapper>, t: Throwable) {
                        onFailure.accept(DataAccessException("AbortCheckIns request was not successful", t))
                    }

                    override fun onResponse(call: Call<TournamentWrapper>, response: Response<TournamentWrapper>) {
                        onSuccess.accept(response.parse("AbortCheckIns"))
                    }
                })
    }

    override fun startTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): TournamentWrapper {
        val response = this.challongeRetrofit
                .startTournament(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .execute()
        return response.parse("StartTournament")
    }

    override fun startTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean,
                                 onSuccess: Callback<TournamentWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.startTournament(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .enqueue(object : retrofit2.Callback<TournamentWrapper> {
                    override fun onFailure(call: Call<TournamentWrapper>, t: Throwable) {
                        onFailure.accept(DataAccessException("StartTournament request was not successful", t))
                    }

                    override fun onResponse(call: Call<TournamentWrapper>, response: Response<TournamentWrapper>) {
                        onSuccess.accept(response.parse("StartTournament"))
                    }
                })
    }

    override fun finalizeTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): TournamentWrapper {
        val response = this.challongeRetrofit
                .finalizeTournament(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .execute()
        return response.parse("FinalizeTournament")
    }

    override fun finalizeTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean,
                                    onSuccess: Callback<TournamentWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.finalizeTournament(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .enqueue(object : retrofit2.Callback<TournamentWrapper> {
                    override fun onFailure(call: Call<TournamentWrapper>, t: Throwable) {
                        onFailure.accept(DataAccessException("FinalizeTournament request was not successful", t))
                    }

                    override fun onResponse(call: Call<TournamentWrapper>, response: Response<TournamentWrapper>) {
                        onSuccess.accept(response.parse("FinalizeTournament"))
                    }
                })
    }

    override fun resetTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): TournamentWrapper {
        val response = this.challongeRetrofit
                .resetTournament(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .execute()
        return response.parse("ResetTournament")
    }

    override fun resetTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean,
                                 onSuccess: Callback<TournamentWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.finalizeTournament(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .enqueue(object : retrofit2.Callback<TournamentWrapper> {
                    override fun onFailure(call: Call<TournamentWrapper>, t: Throwable) {
                        onFailure.accept(DataAccessException("ResetTournament request was not successful", t))
                    }

                    override fun onResponse(call: Call<TournamentWrapper>, response: Response<TournamentWrapper>) {
                        onSuccess.accept(response.parse("ResetTournament"))
                    }
                })
    }

    override fun openTournamentForPredictions(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): TournamentWrapper {
        val response = this.challongeRetrofit
                .openTournamentForPredictions(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .execute()
        return response.parse("OpenTournamentForPredictions")
    }

    override fun openTournamentForPredictions(tournament: String, includeParticipants: Boolean, includeMatches: Boolean,
                                              onSuccess: Callback<TournamentWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.finalizeTournament(tournament, if (includeParticipants) 1 else 0, if (includeMatches) 1 else 0)
                .enqueue(object : retrofit2.Callback<TournamentWrapper> {
                    override fun onFailure(call: Call<TournamentWrapper>, t: Throwable) {
                        onFailure.accept(DataAccessException("OpenTournamentForPredictions request was not successful", t))
                    }

                    override fun onResponse(call: Call<TournamentWrapper>, response: Response<TournamentWrapper>) {
                        onSuccess.accept(response.parse("OpenTournamentForPredictions"))
                    }
                })
    }
}