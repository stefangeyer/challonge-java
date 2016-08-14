package com.exsoloscript.challonge;

import com.exsoloscript.challonge.guice.ChallongeModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Challonge {

    public static ChallongeApi getFor(String username, String apiKey) {
        ChallongeCredentials credentials = new ChallongeCredentials(username, apiKey);
        Injector injector = Guice.createInjector(new ChallongeModule(credentials));
        return injector.getInstance(ChallongeApi.class);
    }
}
