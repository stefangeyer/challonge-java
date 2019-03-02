package at.stefangeyer.challonge.system;

import at.stefangeyer.challonge.Challonge;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Credentials;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.query.MatchQuery;
import at.stefangeyer.challonge.model.query.ParticipantQuery;
import at.stefangeyer.challonge.model.query.TournamentQuery;
import at.stefangeyer.challonge.rest.retrofit.RetrofitRestClient;
import at.stefangeyer.challonge.serializer.gson.GsonSerializer;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MatchTest {
    private Challonge challonge;
    private Tournament tournament;
    private List<Participant> participants;
    
    private String tournamentUrl;

    public MatchTest() {
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
    public void setUp() throws DataAccessException {
        try {
            // Delete the tournament, if it already exists
            this.challonge.deleteTournament(this.challonge.getTournament(this.tournamentUrl));
        } catch (DataAccessException ignored) {
        }

        TournamentQuery query = TournamentQuery.builder().name("Matches").url(this.tournamentUrl).build();

        Tournament tournament = this.challonge.createTournament(query);

        this.participants = this.challonge.bulkAddParticipants(tournament, Arrays.asList(
                ParticipantQuery.builder().name("User1").seed(1).build(),
                ParticipantQuery.builder().name("User2").seed(2).build(),
                ParticipantQuery.builder().name("User3").seed(3).build(),
                ParticipantQuery.builder().name("User4").seed(4).build()));

        this.tournament = this.challonge.startTournament(tournament, true, true);
    }

    @Test
    public void aIndexMatches() throws DataAccessException {
        List<Match> matches = this.challonge.getMatches(this.tournament);

        assertEquals(3, matches.size());

        Match firstSeed = matches.get(0);
        Match secondSeed = matches.get(1);
        Match finalMatch = matches.get(2);

        Participant participant1 = this.participants.stream().filter(p -> p.getName().equals("User1")).findFirst().get();
        Participant participant2 = this.participants.stream().filter(p -> p.getName().equals("User2")).findFirst().get();
        Participant participant3 = this.participants.stream().filter(p -> p.getName().equals("User3")).findFirst().get();
        Participant participant4 = this.participants.stream().filter(p -> p.getName().equals("User4")).findFirst().get();


        assertEquals(participant1.getId(), firstSeed.getPlayer1Id());
        assertEquals(participant4.getId(), firstSeed.getPlayer2Id());
        assertEquals(participant2.getId(), secondSeed.getPlayer1Id());
        assertEquals(participant3.getId(), secondSeed.getPlayer2Id());

        assertNull(finalMatch.getPlayer1Id());
        assertNull(finalMatch.getPlayer2Id());
    }

    @Test
    public void bIndexMatchesForPlayer() throws DataAccessException {
        Participant participant1 = this.participants.stream().filter(p -> p.getName().equals("User1")).findFirst().get();
        Participant participant4 = this.participants.stream().filter(p -> p.getName().equals("User4")).findFirst().get();

        List<Match> matches = this.challonge.getMatches(this.tournament, participant1);

        assertEquals(1, matches.size());

        Match m = matches.get(0);

        assertEquals(participant1.getId(), m.getPlayer1Id());
        assertEquals(participant4.getId(), m.getPlayer2Id());
    }

    @Test
    public void cGetMatch() throws DataAccessException {
        Participant participant1 = this.participants.stream().filter(p -> p.getName().equals("User1")).findFirst().get();

        Match match1 = this.challonge.getMatches(this.tournament, participant1).get(0);

        Match match2 = this.challonge.getMatch(this.tournament, match1.getId(), false);

        assertEquals(match1, match2);
    }

    @Test
    public void dUpdateMatch() throws DataAccessException {
        Participant participant1 = this.participants.stream().filter(p -> p.getName().equals("User1")).findFirst().get();

        Match initial = this.challonge.getMatches(this.tournament, participant1).get(0);
        MatchQuery query = MatchQuery.builder().winnerId(participant1.getId()).scoresCsv("1-0,1-0").build();

        Match updated = this.challonge.updateMatch(initial, query);

        assertEquals(participant1.getId(), updated.getWinnerId());
    }

    @After
    public void tearDown() throws DataAccessException {
        this.challonge.deleteTournament(this.tournament);
    }
}
