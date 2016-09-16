package com.exsoloscript.challonge.handler.retrofit;

import com.exsoloscript.challonge.ChallongeCredentials;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class is configured to instantiate the Retrofit handlers
 *
 * @author EXSolo
 * @version 20160822.1
 */
@Singleton
public class RetrofitServiceProvider implements Provider<Retrofit> {

    private Retrofit retrofit;

    @Inject
    private RetrofitServiceProvider(ChallongeCredentials credentials, Gson gson) {
        String baseUrl = "https://api.challonge.com/v1/";

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.addInterceptor(chain -> {
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

        // for better debugging
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(System.out::println);
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        httpClientBuilder.addInterceptor(loggingInterceptor);

        this.retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
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
