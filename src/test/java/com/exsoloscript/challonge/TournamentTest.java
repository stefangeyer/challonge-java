package com.exsoloscript.challonge;

import com.exsoloscript.challonge.gson.AdapterSuite;
import com.exsoloscript.challonge.handler.sync.TournamentHandler;
import com.exsoloscript.challonge.model.Tournament;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TournamentTest {

    private Challonge challonge;

    @Before
    public void setUp() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File("src/test/resources/user.properties")));
        this.challonge = ChallongeApi.getFor(properties.getProperty("username"), properties.getProperty("api-key"));
    }

    @Test
    public void getTournamentTest() throws IOException {
        TournamentHandler handler = new TournamentHandler(this.challonge.sync().tournaments());
        Tournament tournament = handler.getTournament("mk4ahit", false, false);
        assertTrue(tournament != null);
    }

    @After
    public void tearDown() {

    }
}
