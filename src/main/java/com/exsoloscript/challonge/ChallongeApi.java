package com.exsoloscript.challonge;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import retrofit2.Retrofit;

@Singleton
public class ChallongeApi {

    @Inject
    private Retrofit retrofit;
    @Inject
    private SyncChallonge sync;
    @Inject
    private AsyncChallonge async;

    ChallongeApi() {
    }

    public SyncChallonge sync() {
        return this.sync;
    }

    public AsyncChallonge async() {
        return this.async;
    }
}