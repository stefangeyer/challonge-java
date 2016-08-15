package com.exsoloscript.challonge;

import com.exsoloscript.challonge.model.Tournament;
import com.exsoloscript.challonge.model.enumeration.TournamentType;
import com.exsoloscript.challonge.model.exception.ChallongeException;
import com.exsoloscript.challonge.model.query.TournamentQuery;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.Map;

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
            throw new IllegalArgumentException("Provide environment variables for CHALLONGE_USERNAME and CHALLONGE_API_KEY");
        }

        this.challongeApi = Challonge.getFor(env.get(usernameKey), env.get(apiKeyKey));
    }

    @Test
    public void aCreateTournamentSyncTest() throws IOException, ChallongeException {
        TournamentQuery query = new TournamentQuery.Builder()
                .setName("JavaApiTest")
                .setTournamentType(TournamentType.DOUBLE_ELIMINATION)
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
    public void cUpdateTournamentSyncTest() throws IOException, ChallongeException {
        TournamentQuery query = new TournamentQuery.Builder()
                .noName()
                .noUrl()
                .setTournamentType(TournamentType.SWISS)
                .setSignupCap(6)
                .setAcceptAttachments(false)
                .setDescription("This is a test tournament")
                .holdThirdPlaceMatch(true)
                .build();
        Tournament tournament = this.challongeApi.sync().tournaments().updateTournament("javatesttournament", query);
        assertEquals(tournament.getName(), "JavaApiTest");
    }

    @Test
    public void dDeleteTournamentSyncTest() throws IOException, ChallongeException {
        Tournament tournament = this.challongeApi.sync().tournaments().deleteTournament("javatesttournament");
        assertEquals(tournament.getName(), "JavaApiTest");
    }

    @After
    public void tearDown() {

    }
}
