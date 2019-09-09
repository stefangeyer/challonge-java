package at.stefangeyer.challonge.rest.retrofit;

import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Credentials;
import at.stefangeyer.challonge.model.enumeration.MatchState;
import at.stefangeyer.challonge.model.enumeration.TournamentType;
import at.stefangeyer.challonge.model.query.AttachmentQuery;
import at.stefangeyer.challonge.model.query.enumeration.TournamentQueryState;
import at.stefangeyer.challonge.model.query.wrapper.MatchQueryWrapper;
import at.stefangeyer.challonge.model.query.wrapper.ParticipantQueryListWrapper;
import at.stefangeyer.challonge.model.query.wrapper.ParticipantQueryWrapper;
import at.stefangeyer.challonge.model.query.wrapper.TournamentQueryWrapper;
import at.stefangeyer.challonge.model.wrapper.AttachmentWrapper;
import at.stefangeyer.challonge.model.wrapper.MatchWrapper;
import at.stefangeyer.challonge.model.wrapper.ParticipantWrapper;
import at.stefangeyer.challonge.model.wrapper.TournamentWrapper;
import at.stefangeyer.challonge.rest.RestClient;
import at.stefangeyer.challonge.rest.retrofit.converter.RetrofitConverterFactory;
import at.stefangeyer.challonge.serializer.Serializer;
import okhttp3.*;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import static at.stefangeyer.challonge.rest.retrofit.util.RetrofitUtil.*;

public class RetrofitRestClient implements RestClient, Closeable {

    private static final String BASE_URL = "https://api.challonge.com/v1/";

    private OkHttpClient httpClient;
    private boolean useHttp2;

    private ChallongeRetrofit challongeRetrofit;

    /**
     * Create a Retrofit rest client.
     *
     * If HTTP 1.1 not used, it may be necessary to close the rest client using {@link #close()} in order for all
     * running threads to be terminated. See the following <a href="https://github.com/square/okhttp/issues/4029">link</a>
     * for more information.
     *
     * @param useHttp2 use HTTP 2 or HTTP 1.1
     * @param challongeRetrofit The retrofit implementation to use. Can be null if initialized via @{link {@link #initialize(Credentials, Serializer)}}
     */
    public RetrofitRestClient(ChallongeRetrofit challongeRetrofit, boolean useHttp2) {
        this.challongeRetrofit = challongeRetrofit;
        this.useHttp2 = useHttp2;
    }

    /**
     * Create a Retrofit rest client using the default implementation
     *
     * @param useHttp2 use HTTP 2 or HTTP 1.1
     */
    public RetrofitRestClient(boolean useHttp2) {
        this(null, useHttp2);
    }

    /**
     * Create a Retrofit rest client using HTTP 1.1
     */
    public RetrofitRestClient() {
        this(false);
    }

    @Override
    public void initialize(Credentials credentials, Serializer serializer) {
        if (challongeRetrofit != null) return;

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        if (!this.useHttp2) {
            httpClientBuilder.protocols(Collections.singletonList(Protocol.HTTP_1_1));
        }

        httpClientBuilder.authenticator((route, response) -> {
            String credential = okhttp3.Credentials.basic(credentials.getUsername(), credentials.getKey(),
                    Charset.forName("UTF-8"));
            // retry authentication 5 times only
            if (responseCount(response) >= 5) {
                return null;
            }
            return response.request().newBuilder().header("Authorization", credential).build();
        });

        httpClientBuilder.addInterceptor((chain -> {
            Request original = chain.request();

            Request.Builder requestBuilder = original.newBuilder()
                    .header("Accept", "application/json")
                    .method(original.method(), original.body());

            return chain.proceed(requestBuilder.build());
        }));

        this.httpClient = httpClientBuilder.build();

        RetrofitConverterFactory factory = new RetrofitConverterFactory(serializer);

        Retrofit retrofit = new Retrofit.Builder().client(this.httpClient).baseUrl(BASE_URL).addConverterFactory(factory).build();

        this.challongeRetrofit = retrofit.create(ChallongeRetrofit.class);
    }

    private void checkInitialized() {
        if (this.challongeRetrofit == null)
            throw new IllegalStateException("Attempted to create rest client before initialization");
    }

    @Override
    public List<TournamentWrapper> getTournaments(TournamentQueryState state, TournamentType type, OffsetDateTime createdAfter,
                                                  OffsetDateTime createdBefore, String subdomain) throws DataAccessException {
        checkInitialized();

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
        checkInitialized();

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
        checkInitialized();

        this.challongeRetrofit.getTournament(tournament, includeParticipants ? 1 : 0, includeMatches ? 1 : 0)
                .enqueue(callback(onSuccess, onFailure, "Error while getting tournament"));
    }

    @Override
    public TournamentWrapper createTournament(TournamentQueryWrapper tournamentData) throws DataAccessException {
        checkInitialized();

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
        checkInitialized();

        this.challongeRetrofit.createTournament(tournamentData)
                .enqueue(callback(onSuccess, onFailure, "Error while creating tournament"));
    }

    @Override
    public TournamentWrapper updateTournament(String tournament,
                                              TournamentQueryWrapper tournamentData) throws DataAccessException {
        checkInitialized();

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
        checkInitialized();

        this.challongeRetrofit.updateTournament(tournament, tournamentData)
                .enqueue(callback(onSuccess, onFailure, "Error while updating tournament"));
    }

    @Override
    public TournamentWrapper deleteTournament(String tournament) throws DataAccessException {
        checkInitialized();

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
        checkInitialized();

        this.challongeRetrofit.deleteTournament(tournament)
                .enqueue(callback(onSuccess, onFailure, "Error while deleting tournament"));
    }

    @Override
    public TournamentWrapper processCheckIns(String tournament, boolean includeParticipants,
                                             boolean includeMatches) throws DataAccessException {
        checkInitialized();

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
        checkInitialized();

        this.challongeRetrofit.processCheckIns(tournament,
                includeParticipants ? 1 : 0, includeMatches ? 1 : 0)
                .enqueue(callback(onSuccess, onFailure, "Error while check in processing"));
    }

    @Override
    public TournamentWrapper abortCheckIn(String tournament, boolean includeParticipants,
                                          boolean includeMatches) throws DataAccessException {
        checkInitialized();

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
        checkInitialized();

        this.challongeRetrofit.abortCheckIn(tournament, includeParticipants ? 1 : 0, includeMatches ? 1 : 0)
                .enqueue(callback(onSuccess, onFailure, "Error while check in abort"));
    }

    @Override
    public TournamentWrapper startTournament(String tournament, boolean includeParticipants,
                                             boolean includeMatches) throws DataAccessException {
        checkInitialized();

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
        checkInitialized();

        this.challongeRetrofit.startTournament(tournament, includeParticipants ? 1 : 0,
                includeMatches ? 1 : 0).enqueue(callback(onSuccess, onFailure, "Error while starting tournament"));
    }

    @Override
    public TournamentWrapper finalizeTournament(String tournament, boolean includeParticipants,
                                                boolean includeMatches) throws DataAccessException {
        checkInitialized();

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
        checkInitialized();

        this.challongeRetrofit.finalizeTournament(tournament, includeParticipants ? 1 : 0,
                includeMatches ? 1 : 0).enqueue(callback(onSuccess, onFailure, "Error while finalizing tournament"));
    }

    @Override
    public TournamentWrapper resetTournament(String tournament, boolean includeParticipants,
                                             boolean includeMatches) throws DataAccessException {
        checkInitialized();

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
        checkInitialized();

        this.challongeRetrofit.resetTournament(tournament, includeParticipants ? 1 : 0, includeMatches ? 1 : 0)
                .enqueue(callback(onSuccess, onFailure, "Error while resetting tournament"));
    }

    @Override
    public TournamentWrapper openTournamentForPredictions(String tournament, boolean includeParticipants,
                                                          boolean includeMatches) throws DataAccessException {
        checkInitialized();

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
        checkInitialized();

        this.challongeRetrofit.openTournamentForPredictions(tournament, includeParticipants ? 1 : 0, includeMatches ? 1 : 0)
                .enqueue(callback(onSuccess, onFailure, "Error while opening tournament for predictions"));
    }

    @Override
    public List<ParticipantWrapper> getParticipants(String tournament) throws DataAccessException {
        checkInitialized();

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
        checkInitialized();

        this.challongeRetrofit.getParticipants(tournament)
                .enqueue(callback(onSuccess, onFailure, "Error while getting participants"));
    }

    @Override
    public ParticipantWrapper getParticipant(String tournament, long participantId,
                                             boolean includeMatches) throws DataAccessException {
        checkInitialized();

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
        checkInitialized();

        this.challongeRetrofit.getParticipant(tournament, participantId, includeMatches ? 1 : 0)
                .enqueue(callback(onSuccess, onFailure, "Error while getting participant"));
    }

    @Override
    public ParticipantWrapper addParticipant(String tournament,
                                             ParticipantQueryWrapper participant) throws DataAccessException {
        checkInitialized();

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
        checkInitialized();

        this.challongeRetrofit.addParticipant(tournament, participant)
                .enqueue(callback(onSuccess, onFailure, "Error while adding participant"));
    }

    @Override
    public List<ParticipantWrapper> bulkAddParticipants(String tournament,
                                                        ParticipantQueryListWrapper participants) throws DataAccessException {
        checkInitialized();

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
        checkInitialized();

        this.challongeRetrofit.bulkAddParticipants(tournament, participants).
                enqueue(callback(onSuccess, onFailure, "Error while bulk adding participant"));
    }

    @Override
    public ParticipantWrapper updateParticipant(String tournament, long participantId,
                                                ParticipantQueryWrapper participant) throws DataAccessException {
        checkInitialized();

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
        checkInitialized();

        this.challongeRetrofit.updateParticipant(tournament, participantId, participant)
                .enqueue(callback(onSuccess, onFailure, "Error while updating participant"));
    }

    @Override
    public ParticipantWrapper checkInParticipant(String tournament, long participantId) throws DataAccessException {
        checkInitialized();

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
        checkInitialized();

        this.challongeRetrofit.checkInParticipant(tournament, participantId)
                .enqueue(callback(onSuccess, onFailure, "Error while checking in participant"));
    }

    @Override
    public ParticipantWrapper undoCheckInParticipant(String tournament, long participantId) throws DataAccessException {
        checkInitialized();

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
        checkInitialized();

        this.challongeRetrofit.undoCheckInParticipant(tournament, participantId)
                .enqueue(callback(onSuccess, onFailure, "Error while undoing participant check in"));
    }

    @Override
    public ParticipantWrapper deleteParticipant(String tournament, long participantId) throws DataAccessException {
        checkInitialized();

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
        checkInitialized();

        this.challongeRetrofit.deleteParticipant(tournament, participantId)
                .enqueue(callback(onSuccess, onFailure, "Error while deleting participant"));
    }

    @Override
    public List<ParticipantWrapper> randomizeParticipants(String tournament) throws DataAccessException {
        checkInitialized();

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
        checkInitialized();

        this.challongeRetrofit.randomizeParticipants(tournament)
                .enqueue(callback(onSuccess, onFailure, "Error while randomizing participants"));
    }

    @Override
    public List<MatchWrapper> getMatches(String tournament, Long participantId, MatchState state) throws DataAccessException {
        checkInitialized();

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
        checkInitialized();

        this.challongeRetrofit.getMatches(tournament, participantId, state)
                .enqueue(callback(onSuccess, onFailure, "Error while getting matches"));
    }

    @Override
    public MatchWrapper getMatch(String tournament, long matchId, boolean includeAttachments) throws DataAccessException {
        checkInitialized();

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
        checkInitialized();

        this.challongeRetrofit.getMatch(tournament, matchId, includeAttachments ? 1 : 0)
                .enqueue(callback(onSuccess, onFailure, "Error while getting match"));
    }

    @Override
    public MatchWrapper updateMatch(String tournament, long matchId, MatchQueryWrapper match) throws DataAccessException {
        checkInitialized();

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
        checkInitialized();

        this.challongeRetrofit.updateMatch(tournament, matchId, match)
                .enqueue(callback(onSuccess, onFailure, "Error while updating matches"));
    }

    @Override
    public MatchWrapper reopenMatch(String tournament, long matchId) throws DataAccessException {
        checkInitialized();

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
        checkInitialized();

        this.challongeRetrofit.reopenMatch(tournament, matchId)
                .enqueue(callback(onSuccess, onFailure, "Error while reopening match"));
    }

    @Override
    public List<AttachmentWrapper> getAttachments(String tournament, long matchId) throws DataAccessException {
        checkInitialized();

        Response<List<AttachmentWrapper>> response;

        try {
            response = this.challongeRetrofit.getAttachments(tournament, matchId).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while getting attachments", e);
        }

        return parse(response);
    }

    @Override
    public void getAttachments(String tournament, long matchId, Callback<List<AttachmentWrapper>> onSuccess,
                               Callback<DataAccessException> onFailure) {
        checkInitialized();

        this.challongeRetrofit.getAttachments(tournament, matchId)
                .enqueue(callback(onSuccess, onFailure, "Error while getting attachments"));
    }

    @Override
    public AttachmentWrapper getAttachment(String tournament, long matchId, long attachmentId) throws DataAccessException {
        checkInitialized();

        Response<AttachmentWrapper> response;

        try {
            response = this.challongeRetrofit.getAttachment(tournament, matchId, attachmentId).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while getting attachment", e);
        }

        return parse(response);
    }

    @Override
    public void getAttachment(String tournament, long matchId, long attachmentId, Callback<AttachmentWrapper> onSuccess,
                              Callback<DataAccessException> onFailure) {
        checkInitialized();

        this.challongeRetrofit.getAttachment(tournament, matchId, attachmentId)
                .enqueue(callback(onSuccess, onFailure, "Error while getting attachment"));
    }

    @Override
    public AttachmentWrapper createAttachment(String tournament, long matchId,
                                              AttachmentQuery attachment) throws DataAccessException {
        checkInitialized();

        Response<AttachmentWrapper> response;

        MultipartBody.Part asset;
        MultipartBody.Part url = createUrlPart(attachment);
        MultipartBody.Part desc = createDescriptionPart(attachment);

        try {
            asset = createAssetPart(attachment);
        } catch (IOException e) {
            throw new DataAccessException("Error creating the asset body part", e);
        }

        try {
            response = this.challongeRetrofit.createAttachment(tournament, matchId, asset, url, desc).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while creating attachment", e);
        }

        return parse(response);
    }

    @Override
    public void createAttachment(String tournament, long matchId, AttachmentQuery attachment,
                                 Callback<AttachmentWrapper> onSuccess, Callback<DataAccessException> onFailure) {
        checkInitialized();

        MultipartBody.Part asset;
        MultipartBody.Part url = createUrlPart(attachment);
        MultipartBody.Part desc = createDescriptionPart(attachment);

        try {
            asset = createAssetPart(attachment);
        } catch (IOException e) {
            onFailure.accept(new DataAccessException("Error creating the asset body part", e));
            return;
        }

        this.challongeRetrofit.createAttachment(tournament, matchId, asset, url, desc)
                .enqueue(callback(onSuccess, onFailure, "Error while creating attachment"));
    }

    @Override
    public AttachmentWrapper updateAttachment(String tournament, long matchId, long attachmentId,
                                              AttachmentQuery attachment) throws DataAccessException {
        checkInitialized();

        Response<AttachmentWrapper> response;

        MultipartBody.Part asset;
        MultipartBody.Part url = createUrlPart(attachment);
        MultipartBody.Part desc = createDescriptionPart(attachment);

        try {
            asset = createAssetPart(attachment);
        } catch (IOException e) {
            throw new DataAccessException("Error creating the asset body part", e);
        }

        try {
            response = this.challongeRetrofit.updateAttachment(tournament, matchId, attachmentId, asset, url, desc).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while updating attachment", e);
        }

        return parse(response);
    }

    @Override
    public void updateAttachment(String tournament, long matchId, long attachmentId, AttachmentQuery attachment,
                                 Callback<AttachmentWrapper> onSuccess, Callback<DataAccessException> onFailure) {
        checkInitialized();

        MultipartBody.Part asset;
        MultipartBody.Part url = createUrlPart(attachment);
        MultipartBody.Part desc = createDescriptionPart(attachment);

        try {
            asset = createAssetPart(attachment);
        } catch (IOException e) {
            onFailure.accept(new DataAccessException("Error creating the asset body part", e));
            return;
        }

        this.challongeRetrofit.updateAttachment(tournament, matchId, attachmentId, asset, url, desc)
                .enqueue(callback(onSuccess, onFailure, "Error while updating attachment"));
    }

    @Override
    public AttachmentWrapper deleteAttachment(String tournament, long matchId, long attachmentId) throws DataAccessException {
        checkInitialized();

        Response<AttachmentWrapper> response;

        try {
            response = this.challongeRetrofit.deleteAttachment(tournament, matchId, attachmentId).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while deleting attachment", e);
        }

        return parse(response);
    }

    @Override
    public void deleteAttachment(String tournament, long matchId, long attachmentId, Callback<AttachmentWrapper> onSuccess,
                                 Callback<DataAccessException> onFailure) {
        checkInitialized();

        this.challongeRetrofit.deleteAttachment(tournament, matchId, attachmentId)
                .enqueue(callback(onSuccess, onFailure, "Error while deleting attachment"));
    }

    private MultipartBody.Part createAssetPart(AttachmentQuery attachment) throws IOException {
        if (attachment.getAsset() != null) {
            File asset = attachment.getAsset();
            MediaType mediaType = attachment.getAssetMimeType() != null ? MediaType.parse(attachment.getAssetMimeType()) : null;
            RequestBody requestBody = RequestBody.create(mediaType, attachment.getAsset());
            return MultipartBody.Part.createFormData("match_attachment[asset]", asset.getName(), requestBody);
        }
        return null;
    }

    private MultipartBody.Part createUrlPart(AttachmentQuery attachment) {
        if (attachment.getUrl() != null) {
            return MultipartBody.Part.createFormData("match_attachment[url]", attachment.getUrl());
        }
        return null;
    }

    private MultipartBody.Part createDescriptionPart(AttachmentQuery attachment) {
        if (attachment.getDescription() != null) {
            return MultipartBody.Part.createFormData("match_attachment[description]", attachment.getDescription());
        }
        return null;
    }

    @Override
    public void close() {
        // Stops all non daemon threads used for HTTP/2 which might prevent the application from stopping
        // See https://github.com/square/okhttp/issues/4029 for further information

        this.httpClient.connectionPool().evictAll();
    }
}
