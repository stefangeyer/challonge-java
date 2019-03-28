package at.stefangeyer.challonge.system;

import at.stefangeyer.challonge.Challonge;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Credentials;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.enumeration.RankedBy;
import at.stefangeyer.challonge.model.enumeration.TournamentState;
import at.stefangeyer.challonge.model.enumeration.TournamentType;
import at.stefangeyer.challonge.model.query.MatchQuery;
import at.stefangeyer.challonge.model.query.ParticipantQuery;
import at.stefangeyer.challonge.model.query.TournamentQuery;
import at.stefangeyer.challonge.model.query.enumeration.GrandFinalsModifier;
import at.stefangeyer.challonge.rest.retrofit.RetrofitRestClient;
import at.stefangeyer.challonge.serializer.gson.GsonSerializer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class TournamentTest {

    private Challonge challonge;

    private String tournamentUrl;
    private Tournament tournament;

    public TournamentTest() {
        String username = System.getProperty("challongeUsername");
        String apiKey = System.getProperty("challongeApiKey");
        this.tournamentUrl = System.getProperty("challongeTournamentUrl");

        if (username == null || apiKey == null) {
            throw new IllegalArgumentException("Required system properties challongeUsername, challongeApiKey or " +
                    "challongeTournamentUrl are absent");
        }

        this.challonge = new Challonge(new Credentials(username, apiKey), new GsonSerializer(), new RetrofitRestClient());
    }

    @Before
    public void setUp() throws Exception {
        try {
            // Delete the tournament, if it already exists
            this.challonge.deleteTournament(this.challonge.getTournament(this.tournamentUrl));
        } catch (DataAccessException ignored) {
        }

        OffsetDateTime dt = OffsetDateTime.of(2025, 8, 22, 10, 0, 0, 0, ZoneOffset.of("-04:00"));

        TournamentQuery query = TournamentQuery.builder().name("JavaApiTest").url(this.tournamentUrl)
                .tournamentType(TournamentType.DOUBLE_ELIMINATION).description("This is a description")
                .holdThirdPlaceMatch(true).openSignup(true).pointsForMatchWin(2.0F).pointsForMatchTie(0.0F)
                .pointsForGameWin(1.0F).pointsForGameTie(0.0F).pointsForBye(0.5F).signupCap(4)
                .rankedBy(RankedBy.MATCH_WINS).roundRobinPointsForGameWin(1.0F).roundRobinPointsForGameTie(1.5F)
                .roundRobinPointsForMatchWin(5.0F).roundRobinPointsForMatchTie(2.0F).acceptAttachments(true)
                .hideForum(true).showRounds(true).privateOnly(true).notifyUsersWhenMatchesOpen(true)
                .notifyUsersWhenTheTournamentEnds(true).sequentialPairings(true).startAt(dt).checkInDuration(5L)
                .grandFinalsModifier(GrandFinalsModifier.SINGLE_MATCH).gameName("Some Game").build();

        this.tournament = this.challonge.createTournament(query);
    }

    @Test
    public final void testCreateTournament() {
        assertEquals("JavaApiTest", this.tournament.getName());
        assertEquals(this.tournamentUrl, this.tournament.getUrl());
        assertEquals(4, (int) this.tournament.getSignupCap());
        assertEquals(TournamentType.DOUBLE_ELIMINATION, this.tournament.getTournamentType());
        assertEquals(this.tournament.getDescription(), "This is a description");
        assertEquals(1.0F, this.tournament.getRoundRobinPointsForGameWin(), 0);
        assertEquals(1.5F, this.tournament.getRoundRobinPointsForGameTie(), 0);
        assertEquals(5.0F, this.tournament.getRoundRobinPointsForMatchWin(), 0);
        assertEquals(2.0F, this.tournament.getRoundRobinPointsForMatchTie(), 0);
        assertTrue(this.tournament.getAcceptAttachments());
        assertTrue(this.tournament.getHideForum());
        assertTrue(this.tournament.getShowRounds());
        assertTrue(this.tournament.getPrivateOnly());
        assertTrue(this.tournament.getNotifyUsersWhenMatchesOpen());
        assertTrue(this.tournament.getNotifyUsersWhenTheTournamentEnds());
        assertTrue(this.tournament.getSequentialPairings());
        assertTrue(this.tournament.getHoldThirdPlaceMatch());
        assertTrue(this.tournament.getOpenSignup());
        assertEquals(2.0F, this.tournament.getPointsForMatchWin(), 0);
        assertEquals(0.0F, this.tournament.getPointsForMatchTie(), 0);
        assertEquals(1.0F, this.tournament.getPointsForGameWin(), 0);
        assertEquals(0.0F, this.tournament.getPointsForGameTie(), 0);
        assertEquals(0.5F, this.tournament.getPointsForBye(), 0);
        assertEquals(4, (int) this.tournament.getSignupCap());
        assertEquals(RankedBy.MATCH_WINS, this.tournament.getRankedBy());
        assertEquals(5L, (long) this.tournament.getCheckInDuration());
        assertEquals("Some Game", this.tournament.getGameName());
        assertEquals(GrandFinalsModifier.SINGLE_MATCH, this.tournament.getGrandFinalsModifier());
    }

    @Test
    public final void testCreateSubdomainTournament() throws DataAccessException {
        String subdomain = System.getProperty("challongeSubdomain");

        if (subdomain != null && !subdomain.isEmpty()) {
            this.challonge.deleteTournament(Tournament.builder().url(this.tournamentUrl).subdomain(subdomain).build());

            TournamentQuery query = TournamentQuery.builder().subdomain(subdomain).name("JavaApiTest Subdomain")
                    .url(this.tournamentUrl).build();
            Tournament tournament = this.challonge.createTournament(query);

            assertEquals(subdomain, tournament.getSubdomain());

            this.challonge.deleteTournament(tournament);
        }
    }

    @Test
    public final void testGetTournament() throws DataAccessException {
        Tournament tournament = this.challonge.getTournament(this.tournamentUrl, false, false);

        assertEquals(this.tournament, tournament);
    }

    @Test
    public final void testUpdateTournament() throws DataAccessException {
        OffsetDateTime dt = OffsetDateTime.of(2026, 8, 22, 10, 0, 0, 0, ZoneOffset.of("-04:00"));

        TournamentQuery query = TournamentQuery.builder().name("JavaUpdateTest").tournamentType(TournamentType.SWISS)
                .description("TestDescription")
                .pointsForMatchWin(2.5F).pointsForMatchTie(1.0F).pointsForGameWin(1.5F).pointsForGameTie(1.0F)
                .pointsForBye(1.5F).signupCap(6).rankedBy(RankedBy.GAME_WINS).roundRobinPointsForGameWin(1.5F)
                .roundRobinPointsForGameTie(2.5F).roundRobinPointsForMatchWin(5.5F).roundRobinPointsForMatchTie(1.0F)
                .acceptAttachments(false).hideForum(false).showRounds(false).privateOnly(false).notifyUsersWhenMatchesOpen(false)
                .notifyUsersWhenTheTournamentEnds(false).sequentialPairings(false).holdThirdPlaceMatch(false).openSignup(false)
                .startAt(dt).checkInDuration(10L).grandFinalsModifier(GrandFinalsModifier.SKIP).build();

        Tournament tournament = this.challonge.updateTournament(this.tournament, query);

        assertEquals("JavaUpdateTest", tournament.getName());
        assertEquals(6, (int) tournament.getSignupCap());
        assertEquals(TournamentType.SWISS, tournament.getTournamentType());
        assertEquals("TestDescription", tournament.getDescription());
        assertEquals(1.5F, tournament.getRoundRobinPointsForGameWin(), 0);
        assertEquals(2.5F, tournament.getRoundRobinPointsForGameTie(), 0);
        assertEquals(5.5F, tournament.getRoundRobinPointsForMatchWin(), 0);
        assertEquals(1.0F, tournament.getRoundRobinPointsForMatchTie(), 0);
        assertFalse(tournament.getAcceptAttachments());
        assertFalse(tournament.getHideForum());
        assertFalse(tournament.getShowRounds());
        assertFalse(tournament.getPrivateOnly());
        assertFalse(tournament.getNotifyUsersWhenMatchesOpen());
        assertFalse(tournament.getNotifyUsersWhenTheTournamentEnds());
        assertFalse(tournament.getSequentialPairings());
        assertFalse(tournament.getHoldThirdPlaceMatch());
        assertFalse(tournament.getOpenSignup());
        assertEquals(2.5F, tournament.getPointsForMatchWin(), 0);
        assertEquals(1.0F, tournament.getPointsForMatchTie(), 0);
        assertEquals(1.5F, tournament.getPointsForGameWin(), 0);
        assertEquals(1.0F, tournament.getPointsForGameTie(), 0);
        assertEquals(1.5F, tournament.getPointsForBye(), 0);
        assertEquals(RankedBy.GAME_WINS, tournament.getRankedBy());
        assertEquals(10L, (long) tournament.getCheckInDuration());
        assertEquals(GrandFinalsModifier.SKIP, tournament.getGrandFinalsModifier());
    }

    @Test
    public final void testCheckIns() throws DataAccessException {
        OffsetDateTime start = OffsetDateTime.now().plusMinutes(5L);
        TournamentQuery tournamentQuery = TournamentQuery.builder().startAt(start).checkInDuration(10L).build();

        this.challonge.updateTournament(this.tournament, tournamentQuery);

        List<Participant> added = this.challonge.bulkAddParticipants(this.tournament,
                Arrays.asList(ParticipantQuery.builder().name("User1").build(),
                        ParticipantQuery.builder().name("User2").build()));

        Participant p1BeforeProcess = getParticipant(added, "User1");
        Participant p2BeforeProcess = getParticipant(added, "User2");

        assertTrue(p1BeforeProcess.getCheckedIn());
        assertTrue(p2BeforeProcess.getCheckedIn());

        Participant p1AfterCheckOut = this.challonge.undoCheckInParticipant(p1BeforeProcess);

        assertFalse(p1AfterCheckOut.getCheckedIn());

        this.challonge.checkInParticipant(p2BeforeProcess);

        Tournament processed = this.challonge.processCheckIns(this.tournament, true, false);

        assertEquals(TournamentState.CHECKED_IN, processed.getState());

        Participant p1AfterProcess = getParticipant(processed.getParticipants(), "User1");
        Participant p2AfterProcess = getParticipant(processed.getParticipants(), "User2");

        assertFalse(p1AfterProcess.getActive());
        assertTrue(p2AfterProcess.getCheckedIn());
        assertTrue(p2AfterProcess.getActive());

        Tournament aborted = this.challonge.abortCheckIn(this.tournament, true, false);

        Participant p1AfterAbort = getParticipant(aborted.getParticipants(), "User1");
        Participant p2AfterAbort = getParticipant(aborted.getParticipants(), "User2");

        assertNull(p1AfterAbort.getCheckedInAt());
        assertNull(p2AfterAbort.getCheckedInAt());
        assertEquals(TournamentState.PENDING, aborted.getState());
    }

    @Test
    public final void testStartAndFinalizeTournament() throws DataAccessException {
        List<Participant> participants = this.challonge.bulkAddParticipants(this.tournament, Arrays.asList(
                ParticipantQuery.builder().name("User1").build(),
                ParticipantQuery.builder().name("User2").build()));

        Tournament startedTournament = this.challonge.startTournament(this.tournament, true, true);

        assertEquals(2, startedTournament.getParticipants().size());
        assertEquals(TournamentState.UNDERWAY, startedTournament.getState());

        Participant user1 = getParticipant(participants, "User1");
        Participant user2 = getParticipant(participants, "User2");

        MatchQuery query1 = MatchQuery.builder().winnerId(user1.getId()).scoresCsv("1-3,3-0,3-2").build();
        this.challonge.updateMatch(startedTournament.getMatches().get(0), query1);

        MatchQuery query2 = MatchQuery.builder().winnerId(user2.getId()).scoresCsv("3-4,3-1,4-2").build();
        this.challonge.updateMatch(startedTournament.getMatches().get(1), query2);

        MatchQuery query3 = MatchQuery.builder().winnerId(user1.getId()).scoresCsv("0-3,3-0,3-0").build();
        this.challonge.updateMatch(startedTournament.getMatches().get(2), query3);

        Tournament finalizedTournament = this.challonge.finalizeTournament(this.tournament, true, true);
        assertEquals(TournamentState.COMPLETE, finalizedTournament.getState());
    }

    @Test
    public final void testResetTournament() throws DataAccessException {
        this.challonge.bulkAddParticipants(this.tournament, Arrays.asList(
                ParticipantQuery.builder().name("User1").build(),
                ParticipantQuery.builder().name("User2").build()));

        Tournament started = this.challonge.startTournament(this.tournament, true, true);

        assertEquals(3, started.getMatches().size());

        Tournament tournament = this.challonge.resetTournament(this.tournament, true, true);

        assertEquals(tournament.getState(), TournamentState.PENDING);
        assertEquals(0, tournament.getMatches().size());
    }

    @Test
    public final void testGetTournaments() throws DataAccessException {
        Optional<Tournament> optional = this.challonge.getTournaments().stream()
                .filter(t -> t.getUrl().equals(this.tournament.getUrl())).findFirst();

        assertTrue(optional.isPresent());
    }

    @Test(expected = DataAccessException.class)
    public final void testDeleteTournament() throws DataAccessException {
        Tournament tournament = this.challonge.deleteTournament(this.tournament);

        assertEquals(this.tournament.getName(), tournament.getName());

        // check if the tournament is still there
        this.challonge.getTournament(this.tournamentUrl, false, false);
    }

    @After
    public void tearDown() {
        try {
            this.challonge.deleteTournament(this.tournament);
        } catch (DataAccessException ignored) {
        }
    }

    private Participant getParticipant(List<Participant> list, String name) {
        return list.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
    }
}
