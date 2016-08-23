package com.exsoloscript.challonge;

import com.exsoloscript.challonge.guice.ChallongeTestModule;
import com.exsoloscript.challonge.guice.GuiceJUnitRunner;
import com.exsoloscript.challonge.model.Tournament;
import com.exsoloscript.challonge.model.enumeration.RankedBy;
import com.exsoloscript.challonge.model.enumeration.TournamentType;
import com.exsoloscript.challonge.model.enumeration.query.GrandFinalsModifier;
import com.exsoloscript.challonge.model.query.TournamentQuery;
import com.google.inject.Inject;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static junit.framework.TestCase.*;

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
    public void aCreateTournamentTest() throws Throwable {
        OffsetDateTime dt = OffsetDateTime.of(2022, 8, 22, 10, 0, 0, 0, ZoneOffset.of("-04:00"));

        TournamentQuery query = new TournamentQuery.Builder()
                .setName("JavaApiTest")
                .setUrl("javatesttournament")
                .setTournamentType(TournamentType.DOUBLE_ELIMINATION)
                .setDescription("This is a description")
                .setHoldThirdPlaceMatch(true)
                .setOpenSignup(true)
                .setPointsForMatchWin(2F)
                .setPointsForMatchTie(0F)
                .setPointsForGameWin(1F)
                .setPointsForGameTie(0F)
                .setPointsForBye(0.5F)
                .setSignupCap(4)
                .setRankedBy(RankedBy.MATCH_WINS)
                .setRoundRobinPointsForGameWin(1.0F)
                .setRoundRobinPointsForGameTie(1.5F)
                .setRoundRobinPointsForMatchWin(5F)
                .setRoundRobinPointsForMatchTie(2F)
                .setAcceptAttachments(true)
                .setHideForum(true)
                .setShowRounds(true)
                .setPrivate(true)
                .setNotifyUsersWhenMatchesOpen(true)
                .setNotifyUsersWhenTheTournamentEnds(true)
                .setSequentialPairings(true)
                .setStartAt(dt)
                .setCheckInDuration(5)
                .setGrandFinalsModifier(GrandFinalsModifier.SINGLE_MATCH)
                .build();
        Tournament tournament = this.challongeApi.tournaments().createTournament(query).sync();

        assertEquals("JavaApiTest", tournament.name());
        assertEquals("javatesttournament", tournament.url());
        assertEquals(Integer.valueOf(4), tournament.signupCap());
        assertEquals(TournamentType.DOUBLE_ELIMINATION, tournament.tournamentType());
        assertEquals(tournament.description(), "This is a description");
        assertEquals(1.0F, tournament.roundRobinPointsForGameWin());
        assertEquals(1.5F, tournament.roundRobinPointsForGameTie());
        assertEquals(5F, tournament.roundRobinPointsForMatchWin());
        assertEquals(2F, tournament.roundRobinPointsForMatchTie());
        assertTrue(tournament.acceptAttachments());
        assertTrue(tournament.hideForum());
        assertTrue(tournament.showRounds());
        assertTrue(tournament.isPrivate());
        assertTrue(tournament.notifyUsersWhenMatchesOpen());
        assertTrue(tournament.notifyUsersWhenTheTournamentEnds());
        assertTrue(tournament.sequentialPairings());
        assertTrue(tournament.holdThirdPlaceMatch());
        assertTrue(tournament.openSignup());
        assertEquals(2F, tournament.pointsForMatchWin());
        assertEquals(0F, tournament.pointsForMatchTie());
        assertEquals(1F, tournament.pointsForGameWin());
        assertEquals(0F, tournament.pointsForGameTie());
        assertEquals(0.5F, tournament.pointsForBye());
        assertEquals(Integer.valueOf(4), tournament.signupCap());
        assertEquals(RankedBy.MATCH_WINS, tournament.rankedBy());
        assertEquals(Integer.valueOf(5), tournament.checkInDuration());
        assertEquals(GrandFinalsModifier.SINGLE_MATCH, tournament.grandFinalsModifier());
    }

    @Test
    public void bGetTournamentTest() throws Throwable {
        Tournament tournament = this.challongeApi.tournaments().getTournament("javatesttournament", false, false).sync();
        assertEquals(tournament.name(), "JavaApiTest");
    }

    @Test
    public void cUpdateTournamentTest() throws Throwable {
        TournamentQuery query = new TournamentQuery.Builder()
                .noName()
                .noUrl()
                .setTournamentType(TournamentType.SWISS)
                .setSignupCap(6)
                .setAcceptAttachments(true)
                .setDescription("TestDescription")
                .setHoldThirdPlaceMatch(true)
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
    public void dDeleteTournamentTest() throws Throwable {
        Tournament tournament = this.challongeApi.tournaments().deleteTournament("javatesttournament").sync();
        assertEquals(tournament.name(), "JavaApiTest");
    }
}
