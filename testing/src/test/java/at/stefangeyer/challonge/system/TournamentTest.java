package at.stefangeyer.challonge.system;

import at.stefangeyer.challonge.Challonge;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Credentials;
import at.stefangeyer.challonge.model.Match;
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
import kotlin.jvm.internal.Intrinsics;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TournamentTest {

    private Challonge challonge;

    private String tournamentUrl;

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

    @Test
    public final void aCreateTournamentTest() throws DataAccessException {
        try {
            Tournament t = this.challonge.getTournament(this.tournamentUrl);
            // Delete the tournament, if it already exists
            this.challonge.deleteTournament(t);
        } catch (DataAccessException ignored) {
        }

        OffsetDateTime dt = OffsetDateTime.of(2025, 8, 22, 10, 0, 0, 0, ZoneOffset.of("-04:00"));

        TournamentQuery query = TournamentQuery.builder().name("JavaApiTest").url("javaapitest")
                .tournamentType(TournamentType.DOUBLE_ELIMINATION).description("This is a description")
                .holdThirdPlaceMatch(true).openSignup(true).pointsForMatchWin(2.0F).pointsForMatchTie(0.0F)
                .pointsForGameWin(1.0F).pointsForGameTie(0.0F).pointsForBye(0.5F).signupCap(4)
                .rankedBy(RankedBy.MATCH_WINS).roundRobinPointsForGameWin(1.0F).roundRobinPointsForGameTie(1.5F)
                .roundRobinPointsForMatchWin(5.0F).roundRobinPointsForMatchTie(2.0F).acceptAttachments(true)
                .hideForum(true).showRounds(true).privateOnly(true).notifyUsersWhenMatchesOpen(true)
                .notifyUsersWhenTheTournamentEnds(true).sequentialPairings(true).startAt(dt).checkInDuration(5L)
                .grandFinalsModifier(GrandFinalsModifier.SINGLE_MATCH).build();

        Tournament tournament = this.challonge.createTournament(query);
        Intrinsics.checkExpressionValueIsNotNull(tournament, "tournament");
        assertEquals("JavaApiTest", tournament.getName());
        assertEquals("javaapitest", tournament.getUrl());
        assertEquals(4, (int) tournament.getSignupCap());
        assertEquals(TournamentType.DOUBLE_ELIMINATION, tournament.getTournamentType());
        assertEquals(tournament.getDescription(), "This is a description");
        assertEquals(1.0F, tournament.getRoundRobinPointsForGameWin(), 0);
        assertEquals(1.5F, tournament.getRoundRobinPointsForGameTie(), 0);
        assertEquals(5.0F, tournament.getRoundRobinPointsForMatchWin(), 0);
        assertEquals(2.0F, tournament.getRoundRobinPointsForMatchTie(), 0);
        assertTrue(tournament.getAcceptAttachments());
        assertTrue(tournament.getHideForum());
        assertTrue(tournament.getShowRounds());
        assertTrue(tournament.getPrivateOnly());
        assertTrue(tournament.getNotifyUsersWhenMatchesOpen());
        assertTrue(tournament.getNotifyUsersWhenTheTournamentEnds());
        assertTrue(tournament.getSequentialPairings());
        assertTrue(tournament.getHoldThirdPlaceMatch());
        assertTrue(tournament.getOpenSignup());
        assertEquals(2.0F, tournament.getPointsForMatchWin(), 0);
        assertEquals(0.0F, tournament.getPointsForMatchTie(), 0);
        assertEquals(1.0F, tournament.getPointsForGameWin(), 0);
        assertEquals(0.0F, tournament.getPointsForGameTie(), 0);
        assertEquals(0.5F, tournament.getPointsForBye(), 0);
        assertEquals(4, (int) tournament.getSignupCap());
        assertEquals(RankedBy.MATCH_WINS, tournament.getRankedBy());
        assertEquals(5L, (long) tournament.getCheckInDuration());
        assertEquals(GrandFinalsModifier.SINGLE_MATCH, tournament.getGrandFinalsModifier());
    }

    @Test
    public final void aaCreateSubdomainTournamentTest() throws DataAccessException {
        String subdomain = System.getProperty("challongeSubdomain");

        if (subdomain != null && !subdomain.isEmpty()) {
            // Delete the tournament, if it already exists
            this.challonge.deleteTournament(Tournament.builder().url("javasubdomaintournament").subdomain(subdomain)
                    .tournamentType(TournamentType.SINGLE_ELIMINATION).build());

            TournamentQuery query = TournamentQuery.builder().subdomain(subdomain).name("JavaApiTest Subdomain")
                    .url("javasubdomaintournament").build();
            Tournament tournament = this.challonge.createTournament(query);

            assertEquals(subdomain, tournament.getSubdomain());

            this.challonge.deleteTournament(tournament);
        }
    }

    @Test
    public final void bGetTournamentTest() throws DataAccessException {
        Tournament tournament = this.challonge.getTournament(this.tournamentUrl, false, false);
        assertEquals("JavaApiTest", tournament.getName());
    }

    @Test
    public final void cUpdateTournamentTest() throws DataAccessException {
        TournamentQuery query = TournamentQuery.builder().tournamentType(TournamentType.SWISS).signupCap(6)
                .acceptAttachments(true).description("TestDescription").holdThirdPlaceMatch(true).build();

        Tournament t = this.challonge.getTournament(this.tournamentUrl);
        Tournament tournament = this.challonge.updateTournament(t, query);

        assertEquals("JavaApiTest", tournament.getName());
        assertEquals("javaapitest", tournament.getUrl());
        assertEquals(TournamentType.SWISS, tournament.getTournamentType());
        assertEquals(6, (int) tournament.getSignupCap());
        assertTrue(tournament.getAcceptAttachments());
        assertEquals("TestDescription", tournament.getDescription());
        assertTrue(tournament.getHoldThirdPlaceMatch());
    }

    @Test
    public final void dProcessCheckIns() throws DataAccessException {
        OffsetDateTime start = OffsetDateTime.now().plusMinutes(5L);
        TournamentQuery tournamentQuery = TournamentQuery.builder().startAt(start).checkInDuration(10L).build();

        Tournament t = this.challonge.getTournament(this.tournamentUrl);
        this.challonge.updateTournament(t, tournamentQuery);

        List<Participant> participants = this.challonge.bulkAddParticipants(t,
                Arrays.asList(ParticipantQuery.builder().name("User1").build(),
                        ParticipantQuery.builder().name("User2").build()));

        Participant participant1 = participants.stream().filter(p -> p.getName().equals("User1")).findFirst().get();
        Participant participant2 = participants.stream().filter(p -> p.getName().equals("User2")).findFirst().get();

        assertEquals(1, (int) participant1.getSeed());


        assertEquals(2, (int) participant2.getSeed());

        this.challonge.checkInParticipant(participant2);

        Tournament processed = this.challonge.processCheckIns(t, true, false);
        assertEquals(TournamentState.CHECKED_IN, processed.getState());
    }

    @Test
    public final void eAbortCheckIns() throws DataAccessException {
        Tournament t = this.challonge.getTournament(this.tournamentUrl);
        Tournament aborted = this.challonge.abortCheckIn(t, true, false);

        List<Participant> participants = aborted.getParticipants();

        Participant participant1 = participants.stream().filter(p -> p.getName().equals("User1")).findFirst().get();
        Participant participant2 = participants.stream().filter(p -> p.getName().equals("User2")).findFirst().get();

        assertNull(participant1.getCheckedInAt());
        assertNull(participant2.getCheckedInAt());
        assertEquals(TournamentState.PENDING, aborted.getState());
    }

    @Test
    public final void fStartTournament() throws DataAccessException {
        Tournament t = this.challonge.getTournament(this.tournamentUrl);
        Tournament startedTournament = this.challonge.startTournament(t, true, false);

        assertEquals(2, startedTournament.getParticipants().size());
        assertEquals(TournamentState.UNDERWAY, startedTournament.getState());
    }

    @Test
    public final void gFinalizeTournament() throws DataAccessException {
        Tournament tournament = this.challonge.getTournament(this.tournamentUrl, true, true);
        Participant user1 = tournament.getParticipants().stream().filter(p -> p.getName().equals("User1")).findFirst().get();
        MatchQuery query = MatchQuery.builder().winnerId(user1.getId()).scoresCsv("1-3,3-0,3-2").build();
        Match toUpdate = tournament.getMatches().get(0);

        Match match = this.challonge.updateMatch(toUpdate, query);
        assertEquals("1-3,3-0,3-2", match.getScoresCsv());

        Tournament finalizedTournament = this.challonge.finalizeTournament(tournament, true, true);
        assertEquals(TournamentState.COMPLETE, finalizedTournament.getState());
    }

    @Test
    public final void hResetTournament() throws DataAccessException {
        Tournament t = this.challonge.getTournament(this.tournamentUrl);
        Tournament tournament = this.challonge.resetTournament(t, true, true);

        assertEquals(tournament.getState(), TournamentState.PENDING);
    }

    @Test
    public final void iGetTournaments() throws DataAccessException {
        Optional<Tournament> optional = this.challonge.getTournaments().stream()
                .filter(t -> t.getUrl().equals(this.tournamentUrl)).findFirst();

        assertTrue(optional.isPresent());
    }

    @Test(expected = DataAccessException.class)
    public final void zDeleteTournamentTest() throws DataAccessException {
        Tournament t = this.challonge.getTournament(this.tournamentUrl);
        Tournament tournament = this.challonge.deleteTournament(t);

        assertEquals(tournament.getName(), "JavaApiTest");

        // check if the tournament is still there
        this.challonge.getTournament(this.tournamentUrl, false, false);
    }
}
