package com.exsoloscript.challonge;

import com.exsoloscript.challonge.model.Tournament;
import com.exsoloscript.challonge.model.enumeration.query.TournamentQueryType;
import com.exsoloscript.challonge.model.exception.ChallongeException;
import com.exsoloscript.challonge.model.query.TournamentQuery;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TournamentTest {

    private ChallongeApi challongeApi;

    @Before
    public void setUp() throws IOException {
        Map<String, String> env = System.getenv();

        String usernameKey = "CHALLONGE_USERNAME";
        String apiKeyKey = "CHALLONGE_API_KEY";

        if (env.get(usernameKey) == null || env.get(apiKeyKey) == null) {
            throw new IllegalArgumentException("Provide Environment variables for CHALLONGE_USERNAME and CHALLONGE_API_KEY");
        }

        this.challongeApi = Challonge.getFor(env.get(usernameKey), env.get(apiKeyKey));
    }

    @Test
    public void aCreateTournamentSyncTest() throws IOException, ChallongeException {
        TournamentQuery query = new TournamentQuery.Builder().setName("JavaApiTest")
                .setTournamentType(TournamentQueryType.DOUBLE_ELIMINATION)
                .setUrl("javatesttournament")
                .setSignupCap(4)
                .build();
        Tournament tournament = this.challongeApi.sync().tournaments().createTournament(query);
        assertEquals(tournament.getName(), "JavaApiTest");
    }

    @Test
    public void bGetTournamentSyncTest() throws IOException, ChallongeException {
        Tournament tournament = this.challongeApi.sync().tournaments().getTournament("javatesttournament", false, false);
        assertEquals(tournament.getName(), "JavaApiTest");
    }

    @Test
    public void cDeleteTournamentSyncTest() throws IOException, ChallongeException {
        Tournament tournament = this.challongeApi.sync().tournaments().deleteTournament("javatesttournament");
        assertEquals(tournament.getName(), "JavaApiTest");
    }

    @After
    public void tearDown() {

    }
}
