package com.exsoloscript.challonge;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ChallongeApi {

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