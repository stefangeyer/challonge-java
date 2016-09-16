package com.exsoloscript.challonge;

import com.exsoloscript.challonge.guice.ChallongeTestModule;
import com.exsoloscript.challonge.guice.GuiceJUnitRunner;
import com.exsoloscript.challonge.model.Match;
import com.exsoloscript.challonge.model.Participant;
import com.exsoloscript.challonge.model.Tournament;
import com.exsoloscript.challonge.model.enumeration.RankedBy;
import com.exsoloscript.challonge.model.enumeration.TournamentState;
import com.exsoloscript.challonge.model.enumeration.TournamentType;
import com.exsoloscript.challonge.model.enumeration.query.GrandFinalsModifier;
import com.exsoloscript.challonge.model.exception.ChallongeException;
import com.exsoloscript.challonge.model.query.MatchQuery;
import com.exsoloscript.challonge.model.query.ParticipantQuery;
import com.exsoloscript.challonge.model.query.TournamentQuery;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

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
    public void aCreateTournamentTest() throws Throwable {
        try {
            // Delete the tournament, if it already exists
            this.challongeApi.tournaments().deleteTournament("javatesttournament").sync();
        } catch (ChallongeException ignored) {
        }

        OffsetDateTime dt = OffsetDateTime.of(2022, 8, 22, 10, 0, 0, 0, ZoneOffset.of("-04:00"));

        TournamentQuery query = TournamentQuery.builder()
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
//        assertEquals(Integer.valueOf(5), tournament.checkInDuration());
        assertEquals(GrandFinalsModifier.SINGLE_MATCH, tournament.grandFinalsModifier());
    }

    @Test
    public void bGetTournamentTest() throws Throwable {
        Tournament tournament = this.challongeApi.tournaments().getTournament("javatesttournament", false, false).sync();
        assertEquals(tournament.name(), "JavaApiTest");
    }

    @Test
    public void cUpdateTournamentTest() throws Throwable {
        TournamentQuery query = TournamentQuery.builder()
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
    public void dStartTournament() throws Throwable {
        ParticipantQuery participant1 = ParticipantQuery.builder()
                .setName("User1")
                .build();

        ParticipantQuery participant2 = ParticipantQuery.builder()
                .setName("User2")
                .build();

        this.challongeApi.participants().bulkAddParticipants("javatesttournament", Lists.newArrayList(participant1, participant2)).sync();

        Tournament startedTournament = this.challongeApi.tournaments().startTournament("javatesttournament", true, false).sync();

        assertTrue(startedTournament.participants().size() == 2);
        assertEquals(startedTournament.state(), TournamentState.UNDERWAY);
    }

    @Test
    public void eFinalizeTournament() throws Throwable {
        Tournament tournament = this.challongeApi.tournaments().getTournament("javatesttournament", true, true).sync();

        Optional<Participant> optUser1 = tournament.participants().stream().filter(participant -> participant.name().equals("User1")).findFirst();
        Optional<Participant> optUser2 = tournament.participants().stream().filter(participant -> participant.name().equals("User2")).findFirst();

        assertTrue(optUser1.isPresent());
        assertTrue(optUser2.isPresent());

        Participant user1 = optUser1.get();
        Participant user2 = optUser2.get();

        MatchQuery query = MatchQuery.builder()
                .setWinnerId(user1.id().toString())
                .setScoresCsv("1-3,3-0,3-2")
                .build();

        Match toUpdate = tournament.matches().get(0);
        Match match = this.challongeApi.matches().updateMatch(tournament.id().toString(), toUpdate.id(), query).sync();

        assertEquals(user1.id(), match.player1Id());
        assertEquals(user2.id(), match.player2Id());
        assertEquals("1-3,3-0,3-2", match.scoresCsv());

        Tournament finalizedTournament = this.challongeApi.tournaments().finalizeTournament("javatesttournament", true, true).sync();

        assertEquals(TournamentState.COMPLETE, finalizedTournament.state());
    }

    @Test
    public void fResetTournament() throws Throwable {
        Tournament tournament = this.challongeApi.tournaments().resetTournament("javatesttournament", true, true).sync();
        assertEquals(tournament.state(), TournamentState.PENDING);
    }

    @Test(expected = ChallongeException.class)
    public void zDeleteTournamentTest() throws Throwable {
        Tournament tournament = this.challongeApi.tournaments().deleteTournament("javatesttournament").sync();
        assertEquals(tournament.name(), "JavaApiTest");
        // check if the tournament is still there
        this.challongeApi.tournaments().getTournament("javatesttournament", false, false).sync();
    }
}
