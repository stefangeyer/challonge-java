package com.exsoloscript.challonge.guice;

import com.exsoloscript.challonge.Challonge;
import com.exsoloscript.challonge.ChallongeCredentials;
import com.exsoloscript.challonge.gson.AdapterSuite;
import com.exsoloscript.challonge.gson.OffsetDateTimeAdapter;
import com.exsoloscript.challonge.handler.retrofit.RetrofitServiceProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import retrofit2.Retrofit;

import java.time.OffsetDateTime;

/**
 * Guice module for {@link Challonge} initialization.
 *
 * @author EXSolo
 * @version 20160819.1
 */
public class ChallongeModule extends AbstractModule {

    private ChallongeCredentials credentials;

    /**
     * Binding the credentials, that will be injected later
     *
     * @param credentials ChallongeCredentials
     */
    public ChallongeModule(ChallongeCredentials credentials) {
        this.credentials = credentials;
    }

    @Override
    protected void configure() {
        bind(ChallongeCredentials.class).toInstance(this.credentials);
        bind(Retrofit.class).toProvider(RetrofitServiceProvider.class);
        bind(Gson.class).toProvider(AdapterSuite.class);
    }
}
