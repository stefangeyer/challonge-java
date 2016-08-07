package com.exsoloscript.challonge;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static junit.framework.TestCase.assertTrue;

public class ApiTest {

    private Challonge challonge;

    @Before
    public void setUp() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("src/test/java/com/exsoloscript/challonge/user.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.challonge = ChallongeApi.getFor(properties.getProperty("username"), properties.getProperty("api-key"));
    }

    @Test
    public void createTournamentTest() {
        this.challonge.tournaments().createTournament(null);
        assertTrue(true);
    }

    @After
    public void tearDown() {

    }
}
