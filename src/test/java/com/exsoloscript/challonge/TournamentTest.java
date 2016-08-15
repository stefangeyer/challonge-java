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
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TournamentTest {

    private ChallongeApi challongeApi;

    @Before
    public void setUp() throws IOException {
        Properties properties = new Properties();
        Map<String, String> env = System.getenv();

        String usernameKey = "challonge-username";
        String apiKeyKey = "challonge-api-key";

        if (env.get(usernameKey) != null && env.get(apiKeyKey) != null) {
            properties.putAll(env);
        } else {
            properties.load(new FileInputStream(new File("src/test/resources/user.properties")));
        }

        this.challongeApi = Challonge.getFor(properties.getProperty(usernameKey), properties.getProperty(apiKeyKey));
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
