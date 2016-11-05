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
import java.util.List;
import java.util.Optional;

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
        try {
            // Delete the tournament, if it already exists
            this.challongeApi.tournaments().deleteTournament("javatesttournament").sync();
        } catch (ChallongeException ignored) {
        }

        OffsetDateTime dt = OffsetDateTime.of(2022, 8, 22, 10, 0, 0, 0, ZoneOffset.of("-04:00"));

        TournamentQuery query = TournamentQuery.builder()
                .name("JavaApiTest")
                .url("javatesttournament")
                .tournamentType(TournamentType.DOUBLE_ELIMINATION)
                .description("This is a description")
                .holdThirdPlaceMatch(true)
                .openSignup(true)
                .pointsForMatchWin(2F)
                .pointsForMatchTie(0F)
                .pointsForGameWin(1F)
                .pointsForGameTie(0F)
                .pointsForBye(0.5F)
                .signupCap(4)
                .rankedBy(RankedBy.MATCH_WINS)
                .roundRobinPointsForGameWin(1.0F)
                .roundRobinPointsForGameTie(1.5F)
                .roundRobinPointsForMatchWin(5F)
                .roundRobinPointsForMatchTie(2F)
                .acceptAttachments(true)
                .hideForum(true)
                .showRounds(true)
                ._private(true)
                .notifyUsersWhenMatchesOpen(true)
                .notifyUsersWhenTheTournamentEnds(true)
                .sequentialPairings(true)
                .startAt(dt)
                .checkInDuration(5)
                .grandFinalsModifier(GrandFinalsModifier.SINGLE_MATCH)
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
        assertTrue(tournament._private());
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
        TournamentQuery query = TournamentQuery.builder()
                .tournamentType(TournamentType.SWISS)
                .signupCap(6)
                .acceptAttachments(true)
                .description("TestDescription")
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
    public void dProcessCheckIns() throws Exception {
        OffsetDateTime start = OffsetDateTime.now().plusMinutes(10);

        TournamentQuery tournamentQuery = TournamentQuery.builder()
                .startAt(start)
                .checkInDuration(10)
                .build();

        this.challongeApi.tournaments().updateTournament("javatesttournament", tournamentQuery).sync();

        List<Participant> participants = this.challongeApi.participants().bulkAddParticipants("javatesttournament",
                Lists.newArrayList(
                        ParticipantQuery.builder().name("User1").seed(1).build(),
                        ParticipantQuery.builder().name("User2").seed(2).build())
        ).sync();

        Participant participant1 = participants.stream().filter(p -> p.name().equals("User1")).findFirst().get();
        Participant participant2 = participants.stream().filter(p -> p.name().equals("User2")).findFirst().get();

        assertTrue(participant1.seed() == 1);
        assertTrue(participant2.seed() == 2);

        this.challongeApi.participants().checkInParticipant("javatesttournament", participant2.id()).sync();
        Tournament processed = this.challongeApi.tournaments().processCheckIns("javatesttournament", true, false).sync();

        assertEquals(TournamentState.CHECKED_IN, processed.state());
    }

    @Test
    public void eAbortCheckIns() throws Exception {
        Tournament aborted = this.challongeApi.tournaments().abortCheckIn("javatesttournament", true, false).sync();
        List<Participant> participants = aborted.participants();

        Participant participant1 = participants.stream().filter(p -> p.name().equals("User1")).findFirst().get();
        Participant participant2 = participants.stream().filter(p -> p.name().equals("User2")).findFirst().get();

        assertNull(participant1.checkedInAt());
        assertNull(participant2.checkedInAt());
        assertEquals(TournamentState.PENDING, aborted.state());
    }

    @Test
    public void fStartTournament() throws Throwable {
        Tournament startedTournament = this.challongeApi.tournaments().startTournament("javatesttournament", true, false).sync();

        assertTrue(startedTournament.participants().size() == 2);
        assertEquals(startedTournament.state(), TournamentState.UNDERWAY);
    }

    @Test
    public void gFinalizeTournament() throws Throwable {
        Tournament tournament = this.challongeApi.tournaments().getTournament("javatesttournament", true, true).sync();

        Optional<Participant> optUser1 = tournament.participants().stream().filter(participant -> participant.name().equals("User1")).findFirst();
        Optional<Participant> optUser2 = tournament.participants().stream().filter(participant -> participant.name().equals("User2")).findFirst();

        assertTrue(optUser1.isPresent());
        assertTrue(optUser2.isPresent());

        Participant user1 = optUser1.get();
        Participant user2 = optUser2.get();

        MatchQuery query = MatchQuery.builder()
                .winnerId(user1.id().toString())
                .scoresCsv("1-3,3-0,3-2")
                .build();

        Match toUpdate = tournament.matches().get(0);
        Match match = this.challongeApi.matches().updateMatch(tournament.id().toString(), toUpdate.id(), query).sync();

        assertEquals(user1.id(), match.player2Id());
        assertEquals(user2.id(), match.player1Id());
        assertEquals("1-3,3-0,3-2", match.scoresCsv());

        Tournament finalizedTournament = this.challongeApi.tournaments().finalizeTournament("javatesttournament", true, true).sync();

        assertEquals(TournamentState.COMPLETE, finalizedTournament.state());
    }

    @Test
    public void hResetTournament() throws Throwable {
        Tournament tournament = this.challongeApi.tournaments().resetTournament("javatesttournament", true, true).sync();
        assertEquals(tournament.state(), TournamentState.PENDING);
    }

    @Test
    public void iGetTournaments() throws Exception {
        List<Tournament> tournaments = this.challongeApi.tournaments().getTournaments().sync();

        Optional<Tournament> javatesttournament = tournaments.stream().filter(t -> t.url().equals("javatesttournament")).findFirst();

        assertTrue(javatesttournament.isPresent());
    }

    @Test(expected = ChallongeException.class)
    public void zDeleteTournamentTest() throws Throwable {
        Tournament tournament = this.challongeApi.tournaments().deleteTournament("javatesttournament").sync();
        assertEquals(tournament.name(), "JavaApiTest");
        // check if the tournament is still there
        this.challongeApi.tournaments().getTournament("javatesttournament", false, false).sync();
    }
}
