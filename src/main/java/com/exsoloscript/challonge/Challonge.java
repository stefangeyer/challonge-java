package com.exsoloscript.challonge;

import com.exsoloscript.challonge.gson.AdapterSuite;
import com.exsoloscript.challonge.handler.retrofit.RetrofitAttachmentHandler;
import com.exsoloscript.challonge.handler.retrofit.RetrofitMatchHandler;
import com.exsoloscript.challonge.handler.retrofit.RetrofitParticipantHandler;
import com.exsoloscript.challonge.handler.retrofit.RetrofitTournamentHandler;
import com.google.gson.Gson;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.commons.codec.binary.Base64;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

class Challonge {

    private SyncChallongeHandler sync;
    private AsyncChallongeHandler async;

    Challonge(String username, String apiKey) {
        String baseUrl = "https://api.challonge.com/";
        String credentials = username + ":" + apiKey;
        final String basic = "Basic " + Base64.encodeBase64String(credentials.getBytes());

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();

            Request.Builder requestBuilder = original.newBuilder()
                    .header("Authorization", basic)
                    .header("Accept", "application/json")
                    .method(original.method(), original.body());

            Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        // add custom adapters
        Gson gson = AdapterSuite.createGson();

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        this.sync = new SyncChallongeHandler(retrofit);
        this.async = new AsyncChallongeHandler(retrofit);
    }

    public SyncChallongeHandler sync() {
        return this.sync;
    }

    public AsyncChallongeHandler async() {
        return this.async;
    }
}

// will be moved later
class SyncChallongeHandler {
    private RetrofitTournamentHandler tournaments;
    private RetrofitParticipantHandler participants;
    private RetrofitMatchHandler matches;
    private RetrofitAttachmentHandler attachments;

    public SyncChallongeHandler(Retrofit retrofit) {
        this.tournaments = retrofit.create(RetrofitTournamentHandler.class);
        this.participants = retrofit.create(RetrofitParticipantHandler.class);
        this.matches = retrofit.create(RetrofitMatchHandler.class);
        this.attachments = retrofit.create(RetrofitAttachmentHandler.class);
    }

    public RetrofitTournamentHandler tournaments() {
        return tournaments;
    }

    public RetrofitParticipantHandler participants() {
        return participants;
    }

    public RetrofitMatchHandler matches() {
        return matches;
    }

    public RetrofitAttachmentHandler attachments() {
        return attachments;
    }
}

// will be moved later
class AsyncChallongeHandler {
    private RetrofitTournamentHandler tournaments;
    private RetrofitParticipantHandler participants;
    private RetrofitMatchHandler matches;
    private RetrofitAttachmentHandler attachments;

    public AsyncChallongeHandler(Retrofit retrofit) {
        this.tournaments = retrofit.create(RetrofitTournamentHandler.class);
        this.participants = retrofit.create(RetrofitParticipantHandler.class);
        this.matches = retrofit.create(RetrofitMatchHandler.class);
        this.attachments = retrofit.create(RetrofitAttachmentHandler.class);
    }

    public RetrofitTournamentHandler tournaments() {
        return tournaments;
    }

    public RetrofitParticipantHandler participants() {
        return participants;
    }

    public RetrofitMatchHandler matches() {
        return matches;
    }

    public RetrofitAttachmentHandler attachments() {
        return attachments;
    }
}