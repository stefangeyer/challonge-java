package com.exsoloscript.challonge;

import com.exsoloscript.challonge.guice.GuiceJUnitRunner;
import com.exsoloscript.challonge.model.Tournament;
import com.exsoloscript.challonge.model.enumeration.TournamentType;
import com.exsoloscript.challonge.model.query.TournamentQuery;
import com.exsoloscript.challonge.guice.ChallongeTestModule;
import com.exsoloscript.challonge.model.exception.ChallongeException;
import com.google.inject.Inject;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({ChallongeTestModule.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SyncTournamentTest {

    private ChallongeApi challongeApi;

    @Inject
    public void setChallongeApi(ChallongeApi challongeApi) {
        this.challongeApi = challongeApi;
    }

    @Test
    public void aCreateTournamentTest() throws IOException, ChallongeException {
        TournamentQuery query = new TournamentQuery.Builder()
                .setName("JavaApiTest")
                .setUrl("javatesttournament")
                .setTournamentType(TournamentType.DOUBLE_ELIMINATION)
                .setSignupCap(4)
                .setRoundRobinPointsForGameWin(1.0F)
                .build();
        Tournament tournament = this.challongeApi.tournaments().createTournament(query).sync();

        assertEquals(tournament.name(), "JavaApiTest");
        assertEquals(tournament.url(), "javatesttournament");
        assertEquals(tournament.signupCap(), Integer.valueOf(4));
        assertEquals(tournament.tournamentType(), TournamentType.DOUBLE_ELIMINATION);
        assertEquals(tournament.roundRobinPointsForGameWin(), 1.0F);
    }

    @Test
    public void bGetTournamentTest() throws IOException, ChallongeException {
        Tournament tournament = this.challongeApi.tournaments().getTournament("javatesttournament", false, false).sync();
        assertEquals(tournament.name(), "JavaApiTest");
    }

    @Test
    public void cUpdateTournamentTest() throws IOException, ChallongeException {
        TournamentQuery query = new TournamentQuery.Builder()
                .noName()
                .noUrl()
                .setTournamentType(TournamentType.SWISS)
                .setSignupCap(6)
                .setAcceptAttachments(true)
                .setDescription("TestDescription")
                .holdThirdPlaceMatch(true)
                .build();
        Tournament tournament = this.challongeApi.tournaments().updateTournament("javatesttournament", query).sync();

        assertEquals(tournament.name(), "JavaApiTest");
        assertEquals(tournament.url(), "javatesttournament");
        assertEquals(tournament.tournamentType(), TournamentType.SWISS);
        assertEquals(tournament.signupCap(), Integer.valueOf(6));
        assertTrue(tournament.acceptAttachments());
        assertEquals(tournament.description(), "TestDescription");
        assertTrue(tournament.holdThirdPlaceMatch());
    }

    @Test
    public void dDeleteTournamentTest() throws IOException, ChallongeException {
        Tournament tournament = this.challongeApi.tournaments().deleteTournament("javatesttournament").sync();
        assertEquals(tournament.name(), "JavaApiTest");
    }

    @After
    public void tearDown() {

    }
}
