package com.exsoloscript.challonge;

import com.exsoloscript.challonge.handler.sync.SyncTournamentHandler;
import com.exsoloscript.challonge.model.Tournament;
import com.exsoloscript.challonge.model.enumerations.TournamentType;
import com.exsoloscript.challonge.model.query.TournamentQuery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TournamentTest {

    private ChallongeApi challongeApi;

    @Before
    public void setUp() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File("src/test/resources/user.properties")));
        this.challongeApi = Challonge.getFor(properties.getProperty("username"), properties.getProperty("api-key"));
    }

    @Test
    public void createTournamentSyncTest() throws IOException {
        TournamentQuery query = new TournamentQuery.Builder().setName("JavaApiTest")
                .setTournamentType(TournamentType.DOUBLE_ELIMINATION)
                .setUrl("javatesttournament")
                .build();
        Tournament tournament = this.challongeApi.sync().tournaments().createTournament(query);
        assertEquals(tournament.getName(), "JavaApiTest");
    }

    @Test
    public void getTournamentSyncTest() throws IOException {
        Tournament tournament = this.challongeApi.sync().tournaments().getTournament("JavaApiTest", false, false);
        assertEquals(tournament.getName(), "JavaApiTest");
    }

    @After
    public void tearDown() {

    }
}
