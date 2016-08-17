package com.exsoloscript.challonge.handler.retrofit;

import com.exsoloscript.challonge.ChallongeCredentials;
import com.exsoloscript.challonge.gson.AdapterSuite;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class RetrofitServiceProvider implements Provider<Retrofit> {

    private Retrofit retrofit;

    @Inject
    private RetrofitServiceProvider(ChallongeCredentials credentials) {
        String baseUrl = "https://api.challonge.com/";

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();

            Request.Builder requestBuilder = original.newBuilder()
                    // Authorization Header with Basic HTTP Authentication
                    .header("Authorization", credentials.toHttpAuthString())
                    // only accept JSON as response
                    .header("Accept", "application/json")
                    .method(original.method(), original.body());

            Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        // add custom adapters
        Gson gson = AdapterSuite.createGson();

        this.retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public <S> S createService(Class<S> serviceClass) {
        return this.retrofit.create(serviceClass);
    }

    @Override
    public Retrofit get() {
        return this.retrofit;
    }
}
