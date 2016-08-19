package com.exsoloscript.challonge;

import com.google.inject.Provider;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.Map;

@RunWith(Suite.class)
@Suite.SuiteClasses({SyncTournamentTest.class, AttachmentTest.class})
public class ChallongeTestSuite implements Provider<ChallongeApi> {

    @Override
    public ChallongeApi get() {
        Map<String, String> env = System.getenv();

        String usernameKey = "CHALLONGE_USERNAME";
        String apiKeyKey = "CHALLONGE_API_KEY";

        if (env.get(usernameKey) == null || env.get(apiKeyKey) == null) {
            throw new IllegalArgumentException("Provide environment variables for CHALLONGE_USERNAME and CHALLONGE_API_KEY");
        }

        return Challonge.getFor(env.get(usernameKey), env.get(apiKeyKey));
    }
}
