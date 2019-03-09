package at.stefangeyer.challonge.rest.retrofit;

import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.query.wrapper.ParticipantQueryListWrapper;
import at.stefangeyer.challonge.model.query.wrapper.ParticipantQueryWrapper;
import at.stefangeyer.challonge.model.wrapper.ParticipantWrapper;
import at.stefangeyer.challonge.rest.ParticipantRestClient;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static at.stefangeyer.challonge.rest.retrofit.util.RetrofitUtil.callback;
import static at.stefangeyer.challonge.rest.retrofit.util.RetrofitUtil.parse;

public class RetrofitParticipantRestClient implements ParticipantRestClient {

    private ChallongeRetrofit challongeRetrofit;

    public RetrofitParticipantRestClient(ChallongeRetrofit challongeRetrofit) {
        this.challongeRetrofit = challongeRetrofit;
    }

    @Override
    public List<ParticipantWrapper> getParticipants(String tournament) throws DataAccessException {
        Response<List<ParticipantWrapper>> response;

        try {
            response = this.challongeRetrofit.getParticipants(tournament).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while getting participants", e);
        }

        return parse(response);
    }

    @Override
    public void getParticipants(String tournament, Callback<List<ParticipantWrapper>> onSuccess,
                                Callback<DataAccessException> onFailure) {
        this.challongeRetrofit.getParticipants(tournament)
                .enqueue(callback(onSuccess, onFailure, "Error while getting participants"));
    }

    @Override
    public ParticipantWrapper getParticipant(String tournament, long participantId,
                                             boolean includeMatches) throws DataAccessException {
        Response<ParticipantWrapper> response;

        try {
            response = this.challongeRetrofit.getParticipant(tournament, participantId, includeMatches ? 1 : 0).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while getting participant", e);
        }

        return parse(response);
    }

    @Override
    public void getParticipant(String tournament, long participantId, boolean includeMatches,
                               Callback<ParticipantWrapper> onSuccess, Callback<DataAccessException> onFailure) {
        this.challongeRetrofit.getParticipant(tournament, participantId, includeMatches ? 1 : 0)
                .enqueue(callback(onSuccess, onFailure, "Error while getting participant"));
    }

    @Override
    public ParticipantWrapper addParticipant(String tournament,
                                             ParticipantQueryWrapper participant) throws DataAccessException {
        Response<ParticipantWrapper> response;

        try {
            response = this.challongeRetrofit.addParticipant(tournament, participant).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while adding participant", e);
        }

        return parse(response);
    }

    @Override
    public void addParticipant(String tournament, ParticipantQueryWrapper participant,
                               Callback<ParticipantWrapper> onSuccess, Callback<DataAccessException> onFailure) {
        this.challongeRetrofit.addParticipant(tournament, participant)
                .enqueue(callback(onSuccess, onFailure, "Error while adding participant"));
    }

    @Override
    public List<ParticipantWrapper> bulkAddParticipants(String tournament,
                                                        ParticipantQueryListWrapper participants) throws DataAccessException {
        Response<List<ParticipantWrapper>> response;

        try {
            response = this.challongeRetrofit.bulkAddParticipants(tournament, participants).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while bulk adding participant", e);
        }

        return parse(response);
    }

    @Override
    public void bulkAddParticipants(String tournament, ParticipantQueryListWrapper participants,
                                    Callback<List<ParticipantWrapper>> onSuccess, Callback<DataAccessException> onFailure) {
        this.challongeRetrofit.bulkAddParticipants(tournament, participants).
                enqueue(callback(onSuccess, onFailure, "Error while bulk adding participant"));
    }

    @Override
    public ParticipantWrapper updateParticipant(String tournament, long participantId,
                                                ParticipantQueryWrapper participant) throws DataAccessException {
        Response<ParticipantWrapper> response;

        try {
            response = this.challongeRetrofit.updateParticipant(tournament, participantId, participant).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while updating participant", e);
        }

        return parse(response);
    }

    @Override
    public void updateParticipant(String tournament, long participantId, ParticipantQueryWrapper participant,
                                  Callback<ParticipantWrapper> onSuccess, Callback<DataAccessException> onFailure) {
        this.challongeRetrofit.updateParticipant(tournament, participantId, participant)
                .enqueue(callback(onSuccess, onFailure, "Error while updating participant"));
    }

    @Override
    public ParticipantWrapper checkInParticipant(String tournament, long participantId) throws DataAccessException {
        Response<ParticipantWrapper> response;

        try {
            response = this.challongeRetrofit.checkInParticipant(tournament, participantId).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while checking in participant", e);
        }

        return parse(response);
    }

    @Override
    public void checkInParticipant(String tournament, long participantId, Callback<ParticipantWrapper> onSuccess,
                                   Callback<DataAccessException> onFailure) {
        this.challongeRetrofit.checkInParticipant(tournament, participantId)
                .enqueue(callback(onSuccess, onFailure, "Error while checking in participant"));
    }

    @Override
    public ParticipantWrapper undoCheckInParticipant(String tournament, long participantId) throws DataAccessException {
        Response<ParticipantWrapper> response;

        try {
            response = this.challongeRetrofit.undoCheckInParticipant(tournament, participantId).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while undoing participant check in", e);
        }

        return parse(response);
    }

    @Override
    public void undoCheckInParticipant(String tournament, long participantId, Callback<ParticipantWrapper> onSuccess,
                                       Callback<DataAccessException> onFailure) {
        this.challongeRetrofit.undoCheckInParticipant(tournament, participantId)
                .enqueue(callback(onSuccess, onFailure, "Error while undoing participant check in"));
    }

    @Override
    public ParticipantWrapper deleteParticipant(String tournament, long participantId) throws DataAccessException {
        Response<ParticipantWrapper> response;

        try {
            response = this.challongeRetrofit.deleteParticipant(tournament, participantId).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while deleting participant", e);
        }

        return parse(response);
    }

    @Override
    public void deleteParticipant(String tournament, long participantId, Callback<ParticipantWrapper> onSuccess,
                                  Callback<DataAccessException> onFailure) {
        this.challongeRetrofit.deleteParticipant(tournament, participantId)
                .enqueue(callback(onSuccess, onFailure, "Error while deleting participant"));
    }

    @Override
    public List<ParticipantWrapper> randomizeParticipants(String tournament) throws DataAccessException {
        Response<List<ParticipantWrapper>> response;

        try {
            response = this.challongeRetrofit.randomizeParticipants(tournament).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while randomizing participants", e);
        }

        return parse(response);
    }

    @Override
    public void randomizeParticipants(String tournament, Callback<List<ParticipantWrapper>> onSuccess,
                                      Callback<DataAccessException> onFailure) {
        this.challongeRetrofit.randomizeParticipants(tournament)
                .enqueue(callback(onSuccess, onFailure, "Error while randomizing participants"));
    }
}
