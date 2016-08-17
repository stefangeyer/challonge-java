package com.exsoloscript.challonge.guice;

import com.exsoloscript.challonge.ChallongeCredentials;
import com.exsoloscript.challonge.handler.retrofit.RetrofitServiceProvider;
import com.google.inject.AbstractModule;
import retrofit2.Retrofit;

public class ChallongeModule extends AbstractModule {

    private ChallongeCredentials credentials;

    public ChallongeModule(ChallongeCredentials credentials) {
        this.credentials = credentials;
    }

    @Override
    protected void configure() {
        bind(ChallongeCredentials.class).toInstance(this.credentials);
        bind(Retrofit.class).toProvider(RetrofitServiceProvider.class);
    }
}
