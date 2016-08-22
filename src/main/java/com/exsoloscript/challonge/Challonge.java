package com.exsoloscript.challonge;

import com.exsoloscript.challonge.guice.ChallongeModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * The only purpose of this class is to instantiate the {@link ChallongeApi} class.
 *
 * @author EXSolo
 * @version 20160822.1
 */
public class Challonge {

    /**
     * Create a new {@link ChallongeApi} object with the given credentials.
     * Every time this method is called a new object will be created.
     *
     * @param username Challonge username
     * @param apiKey Challonge api key
     * @return ChallongeApi
     */
    public static ChallongeApi getFor(String username, String apiKey) {
        ChallongeCredentials credentials = new ChallongeCredentials(username, apiKey);
        Injector injector = Guice.createInjector(new ChallongeModule(credentials));
        return injector.getInstance(ChallongeApi.class);
    }
}
