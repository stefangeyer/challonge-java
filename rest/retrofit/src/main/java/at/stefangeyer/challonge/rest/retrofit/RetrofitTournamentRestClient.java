package at.stefangeyer.challonge.rest.retrofit;

import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.enumeration.TournamentType;
import at.stefangeyer.challonge.model.query.enumeration.TournamentQueryState;
import at.stefangeyer.challonge.model.query.wrapper.TournamentQueryWrapper;
import at.stefangeyer.challonge.model.wrapper.TournamentWrapper;
import at.stefangeyer.challonge.rest.TournamentRestClient;
import retrofit2.Response;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;

import static at.stefangeyer.challonge.rest.retrofit.util.RetrofitUtil.callback;
import static at.stefangeyer.challonge.rest.retrofit.util.RetrofitUtil.parse;

/**
 * Retrofit Gson of the tournament rest client
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
public class RetrofitTournamentRestClient implements TournamentRestClient {

    private ChallongeRetrofit challongeRetrofit;

    public RetrofitTournamentRestClient(ChallongeRetrofit challongeRetrofit) {
        this.challongeRetrofit = challongeRetrofit;
    }

    @Override
    public List<TournamentWrapper> getTournaments(TournamentQueryState state, TournamentType type, OffsetDateTime createdAfter,
                                                  OffsetDateTime createdBefore, String subdomain) throws DataAccessException {
        Response<List<TournamentWrapper>> response;

        try {
            response = this.challongeRetrofit.getTournaments(state, type, createdAfter, createdBefore, subdomain).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while getting tournaments", e);
        }

        return parse(response);
    }

    @Override
    public void getTournaments(TournamentQueryState state, TournamentType type, OffsetDateTime createdAfter,
                               OffsetDateTime createdBefore, String subdomain, Callback<List<TournamentWrapper>> onSuccess,
                               Callback<DataAccessException> onFailure) {
        this.challongeRetrofit.getTournaments(state, type, createdAfter, createdBefore, subdomain)
                .enqueue(callback(onSuccess, onFailure, "Error while getting tournaments"));
    }

    @Override
    public TournamentWrapper getTournament(String tournament, boolean includeParticipants,
                                           boolean includeMatches) throws DataAccessException {
        Response<TournamentWrapper> response;

        try {
            response = this.challongeRetrofit.getTournament(tournament,
                    includeParticipants ? 1 : 0, includeMatches ? 1 : 0).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while getting tournament " + tournament, e);
        }

        return parse(response);
    }

    @Override
    public void getTournament(String tournament, boolean includeParticipants, boolean includeMatches,
                              Callback<TournamentWrapper> onSuccess, Callback<DataAccessException> onFailure) {
        this.challongeRetrofit.getTournament(tournament, includeParticipants ? 1 : 0, includeMatches ? 1 : 0)
                .enqueue(callback(onSuccess, onFailure, "Error while getting tournament"));
    }

    @Override
    public TournamentWrapper createTournament(TournamentQueryWrapper tournamentData) throws DataAccessException {
        Response<TournamentWrapper> response;

        try {
            response = this.challongeRetrofit.createTournament(tournamentData).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while creating tournament", e);
        }

        return parse(response);
    }

    @Override
    public void createTournament(TournamentQueryWrapper tournamentData, Callback<TournamentWrapper> onSuccess,
                                 Callback<DataAccessException> onFailure) {
        this.challongeRetrofit.createTournament(tournamentData)
                .enqueue(callback(onSuccess, onFailure, "Error while creating tournament"));
    }

    @Override
    public TournamentWrapper updateTournament(String tournament,
                                              TournamentQueryWrapper tournamentData) throws DataAccessException {
        Response<TournamentWrapper> response;

        try {
            response = this.challongeRetrofit.updateTournament(tournament, tournamentData).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while updating tournament", e);
        }

        return parse(response);
    }

    @Override
    public void updateTournament(String tournament, TournamentQueryWrapper tournamentData,
                                 Callback<TournamentWrapper> onSuccess, Callback<DataAccessException> onFailure) {
        this.challongeRetrofit.updateTournament(tournament, tournamentData)
                .enqueue(callback(onSuccess, onFailure, "Error while updating tournament"));
    }

    @Override
    public TournamentWrapper deleteTournament(String tournament) throws DataAccessException {
        Response<TournamentWrapper> response;

        try {
            response = this.challongeRetrofit.deleteTournament(tournament).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while deleting tournament", e);
        }

        return parse(response);
    }

    @Override
    public void deleteTournament(String tournament, Callback<TournamentWrapper> onSuccess,
                                 Callback<DataAccessException> onFailure) {
        this.challongeRetrofit.deleteTournament(tournament)
                .enqueue(callback(onSuccess, onFailure, "Error while deleting tournament"));
    }

    @Override
    public TournamentWrapper processCheckIns(String tournament, boolean includeParticipants,
                                             boolean includeMatches) throws DataAccessException {
        Response<TournamentWrapper> response;

        try {
            response = this.challongeRetrofit.processCheckIns(tournament,
                    includeParticipants ? 1 : 0, includeMatches ? 1 : 0).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while check in processing", e);
        }

        return parse(response);
    }

    @Override
    public void processCheckIns(String tournament, boolean includeParticipants, boolean includeMatches,
                                Callback<TournamentWrapper> onSuccess, Callback<DataAccessException> onFailure) {
        this.challongeRetrofit.processCheckIns(tournament,
                includeParticipants ? 1 : 0, includeMatches ? 1 : 0)
                .enqueue(callback(onSuccess, onFailure, "Error while check in processing"));
    }

    @Override
    public TournamentWrapper abortCheckIn(String tournament, boolean includeParticipants,
                                          boolean includeMatches) throws DataAccessException {
        Response<TournamentWrapper> response;

        try {
            response = this.challongeRetrofit.abortCheckIn(tournament, includeParticipants ? 1 : 0,
                    includeMatches ? 1 : 0).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while check in abort", e);
        }

        return parse(response);
    }

    @Override
    public void abortCheckIn(String tournament, boolean includeParticipants, boolean includeMatches,
                             Callback<TournamentWrapper> onSuccess, Callback<DataAccessException> onFailure) {
        this.challongeRetrofit.abortCheckIn(tournament, includeParticipants ? 1 : 0, includeMatches ? 1 : 0)
                .enqueue(callback(onSuccess, onFailure, "Error while check in abort"));
    }

    @Override
    public TournamentWrapper startTournament(String tournament, boolean includeParticipants,
                                             boolean includeMatches) throws DataAccessException {
        Response<TournamentWrapper> response;

        try {
            response = this.challongeRetrofit.startTournament(tournament, includeParticipants ? 1 : 0,
                    includeMatches ? 1 : 0).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while starting tournament", e);
        }

        return parse(response);
    }

    @Override
    public void startTournament(String tournament, boolean includeParticipants, boolean includeMatches,
                                Callback<TournamentWrapper> onSuccess, Callback<DataAccessException> onFailure) {
        this.challongeRetrofit.startTournament(tournament, includeParticipants ? 1 : 0,
                includeMatches ? 1 : 0).enqueue(callback(onSuccess, onFailure, "Error while starting tournament"));
    }

    @Override
    public TournamentWrapper finalizeTournament(String tournament, boolean includeParticipants,
                                                boolean includeMatches) throws DataAccessException {
        Response<TournamentWrapper> response;

        try {
            response = this.challongeRetrofit.finalizeTournament(tournament, includeParticipants ? 1 : 0,
                    includeMatches ? 1 : 0).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while finalizing tournament", e);
        }

        return parse(response);
    }

    @Override
    public void finalizeTournament(String tournament, boolean includeParticipants, boolean includeMatches,
                                   Callback<TournamentWrapper> onSuccess, Callback<DataAccessException> onFailure) {
        this.challongeRetrofit.finalizeTournament(tournament, includeParticipants ? 1 : 0,
                includeMatches ? 1 : 0).enqueue(callback(onSuccess, onFailure, "Error while finalizing tournament"));
    }

    @Override
    public TournamentWrapper resetTournament(String tournament, boolean includeParticipants,
                                             boolean includeMatches) throws DataAccessException {
        Response<TournamentWrapper> response;

        try {
            response = this.challongeRetrofit.resetTournament(tournament, includeParticipants ? 1 : 0,
                    includeMatches ? 1 : 0).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while resetting tournament", e);
        }

        return parse(response);
    }

    @Override
    public void resetTournament(String tournament, boolean includeParticipants, boolean includeMatches,
                                Callback<TournamentWrapper> onSuccess, Callback<DataAccessException> onFailure) {
        this.challongeRetrofit.resetTournament(tournament, includeParticipants ? 1 : 0, includeMatches ? 1 : 0)
                .enqueue(callback(onSuccess, onFailure, "Error while resetting tournament"));
    }

    @Override
    public TournamentWrapper openTournamentForPredictions(String tournament, boolean includeParticipants,
                                                          boolean includeMatches) throws DataAccessException {
        Response<TournamentWrapper> response;

        try {
            response = this.challongeRetrofit.openTournamentForPredictions(tournament, includeParticipants ? 1 : 0,
                    includeMatches ? 1 : 0).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while opening tournament for predictions", e);
        }

        return parse(response);
    }

    @Override
    public void openTournamentForPredictions(String tournament, boolean includeParticipants, boolean includeMatches,
                                             Callback<TournamentWrapper> onSuccess, Callback<DataAccessException> onFailure) {
        this.challongeRetrofit.openTournamentForPredictions(tournament, includeParticipants ? 1 : 0, includeMatches ? 1 : 0)
                .enqueue(callback(onSuccess, onFailure, "Error while opening tournament for predictions"));
    }
}
