package com.exsoloscript.challonge;

import com.exsoloscript.challonge.guice.ChallongeTestModule;
import com.exsoloscript.challonge.guice.GuiceJUnitRunner;
import com.exsoloscript.challonge.model.Tournament;
import com.exsoloscript.challonge.model.enumeration.TournamentType;
import com.exsoloscript.challonge.model.exception.ChallongeException;
import com.exsoloscript.challonge.model.query.TournamentQuery;
import com.google.inject.Inject;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({ChallongeTestModule.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TournamentTest {

    private ChallongeApi challongeApi;

    @Inject
    public void setChallongeApi(ChallongeApi challongeApi) {
        this.challongeApi = challongeApi;
    }

    @Test
    public void aCreateTournamentSyncTest() throws IOException, ChallongeException {
        TournamentQuery query = new TournamentQuery.Builder()
                .setName("JavaApiTest")
                .setUrl("javatesttournament")
                .setTournamentType(TournamentType.DOUBLE_ELIMINATION)
                .setSignupCap(4)
                .setRoundRobinPointsForGameWin(1.0F)
                .build();
        Tournament tournament = this.challongeApi.sync().tournaments().createTournament(query);
        assertEquals(tournament.getName(), "JavaApiTest");
        assertEquals(tournament.getRoundRobinPointsForGameWin(), 1.0F);
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
