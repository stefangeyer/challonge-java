package com.exsoloscript.challonge.guice;

import com.exsoloscript.challonge.Challonge;
import com.exsoloscript.challonge.ChallongeCredentials;
import com.exsoloscript.challonge.handler.retrofit.RetrofitServiceProvider;
import com.google.inject.AbstractModule;
import retrofit2.Retrofit;

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
    }
}
