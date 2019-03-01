package at.stefangeyer.challonge.rest.retrofit;

import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.enumeration.MatchState;
import at.stefangeyer.challonge.model.query.wrapper.MatchQueryWrapper;
import at.stefangeyer.challonge.model.wrapper.MatchWrapper;
import at.stefangeyer.challonge.rest.MatchRestClient;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static at.stefangeyer.challonge.rest.retrofit.RetrofitUtil.callback;
import static at.stefangeyer.challonge.rest.retrofit.RetrofitUtil.parse;

public class RetrofitMatchRestClient implements MatchRestClient {

    private ChallongeRetrofit challongeRetrofit;

    public RetrofitMatchRestClient(ChallongeRetrofit challongeRetrofit) {
        this.challongeRetrofit = challongeRetrofit;
    }

    @Override
    public List<MatchWrapper> getMatches(String tournament, Long participantId, MatchState state) throws DataAccessException {
        Response<List<MatchWrapper>> response;

        try {
            response = this.challongeRetrofit.getMatches(tournament, participantId, state).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while getting matches", e);
        }

        return parse(response);
    }

    @Override
    public void getMatches(String tournament, Long participantId, MatchState state, Callback<List<MatchWrapper>> onSuccess,
                           Callback<DataAccessException> onFailure) {
        this.challongeRetrofit.getMatches(tournament, participantId, state)
                .enqueue(callback(onSuccess, onFailure, "Error while getting matches"));
    }

    @Override
    public MatchWrapper getMatch(String tournament, long matchId, boolean includeAttachments) throws DataAccessException {
        Response<MatchWrapper> response;

        try {
            response = this.challongeRetrofit.getMatch(tournament, matchId, includeAttachments ? 1 : 0).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while getting match", e);
        }

        return parse(response);
    }

    @Override
    public void getMatch(String tournament, long matchId, boolean includeAttachments, Callback<MatchWrapper> onSuccess,
                         Callback<DataAccessException> onFailure) {
        this.challongeRetrofit.getMatch(tournament, matchId, includeAttachments ? 1 : 0)
                .enqueue(callback(onSuccess, onFailure, "Error while getting match"));
    }

    @Override
    public MatchWrapper updateMatch(String tournament, long matchId, MatchQueryWrapper match) throws DataAccessException {
        Response<MatchWrapper> response;

        try {
            response = this.challongeRetrofit.updateMatch(tournament, matchId, match).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while updating match", e);
        }

        return parse(response);
    }

    @Override
    public void updateMatch(String tournament, long matchId, MatchQueryWrapper match, Callback<MatchWrapper> onSuccess,
                            Callback<DataAccessException> onFailure) {
        this.challongeRetrofit.updateMatch(tournament, matchId, match)
                .enqueue(callback(onSuccess, onFailure, "Error while updating matches"));
    }

    @Override
    public MatchWrapper reopenMatch(String tournament, long matchId) throws DataAccessException {
        Response<MatchWrapper> response;

        try {
            response = this.challongeRetrofit.reopenMatch(tournament, matchId).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while reopening match", e);
        }

        return parse(response);
    }

    @Override
    public void reopenMatch(String tournament, long matchId, Callback<MatchWrapper> onSuccess,
                            Callback<DataAccessException> onFailure) {
        this.challongeRetrofit.reopenMatch(tournament, matchId)
                .enqueue(callback(onSuccess, onFailure, "Error while reopening match"));
    }
}
