package com.exsoloscript.challonge;

import com.exsoloscript.challonge.gson.AdapterSuite;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.commons.codec.binary.Base64;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class ChallongeApi {

    private SyncChallonge sync;
    private AsyncChallonge async;

    ChallongeApi(String username, String apiKey) {
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

        this.sync = new SyncChallonge(retrofit);
        this.async = new AsyncChallonge(retrofit);
    }

    public SyncChallonge sync() {
        return this.sync;
    }

    public AsyncChallonge async() {
        return this.async;
    }
}