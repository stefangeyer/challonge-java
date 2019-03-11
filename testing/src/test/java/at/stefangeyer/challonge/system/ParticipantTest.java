package at.stefangeyer.challonge.system;

import at.stefangeyer.challonge.Challonge;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Credentials;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.query.ParticipantQuery;
import at.stefangeyer.challonge.model.query.TournamentQuery;
import at.stefangeyer.challonge.rest.retrofit.RetrofitRestClient;
import at.stefangeyer.challonge.serializer.gson.GsonSerializer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class ParticipantTest {

    private Challonge challonge;
    private Tournament tournament;

    private String tournamentUrl;

    public ParticipantTest() {
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

        OffsetDateTime start = OffsetDateTime.now().plusMinutes(75L);
        TournamentQuery query = TournamentQuery.builder().name("API Participant Test").url(this.tournamentUrl)
                .checkInDuration(120L).startAt(start).build();

        this.tournament = this.challonge.createTournament(query);
    }

    @Test
    public final void testAddParticipants() throws DataAccessException {
        ParticipantQuery query = ParticipantQuery.builder().name("Stefan").seed(1).misc("MiscTest")
                .challongeUsername("EXSolo").email("stefan@mail.com").inviteNameOrEmail("invite@mail.com").build();

        Participant participant = this.challonge.addParticipant(this.tournament, query);

        assertEquals("Stefan", participant.getName());
        assertEquals(1, (int) participant.getSeed());
        assertEquals("MiscTest", participant.getMisc());
    }

    @Test
    public final void testBulkAddParticipants() throws DataAccessException {
        ParticipantQuery query1 = ParticipantQuery.builder().name("Bulk1").seed(1).build();
        ParticipantQuery query2 = ParticipantQuery.builder().name("Bulk2").inviteNameOrEmail("bulk").seed(2).misc("BulkAdd").build();

        List<Participant> participants = this.challonge.bulkAddParticipants(this.tournament, Arrays.asList(query1, query2));
        Participant participant1 = participants.get(0);
        Participant participant2 = participants.get(1);

        assertEquals("Bulk1", participant1.getName());
        assertEquals(1, (int) participant1.getSeed());
        assertEquals("Bulk2", participant2.getName());

        assertEquals("bulk", participant2.getUsername());
        assertEquals("BulkAdd", participant2.getMisc());
        assertEquals(2, (int) participant2.getSeed());
    }

    @Test
    public final void testUpdateParticipant() throws DataAccessException {
        ParticipantQuery createQuery = ParticipantQuery.builder().name("Stefan").misc("123").build();
        Participant createdParticipant = this.challonge.addParticipant(this.tournament, createQuery);

        ParticipantQuery updateQuery = ParticipantQuery.builder().name("Geyer").misc("321").build();
        Participant updatedParticipant = this.challonge.updateParticipant(createdParticipant, updateQuery);

        assertEquals("Geyer", updatedParticipant.getName());
        assertEquals("321", updatedParticipant.getMisc());
    }

    @Test(expected = DataAccessException.class)
    public final void testDeleteParticipant() throws DataAccessException {
        ParticipantQuery createQuery = ParticipantQuery.builder().name("Stefan").build();
        Participant createdParticipant = this.challonge.addParticipant(this.tournament, createQuery);

        Participant deletedParticipant = this.challonge.deleteParticipant(createdParticipant);

        this.challonge.getParticipant(this.tournament, deletedParticipant.getId(), false);

        // Get call should fail
        fail();
    }

    @Test
    public final void testRandomizeParticipants() throws DataAccessException {
        ParticipantQuery query1 = ParticipantQuery.builder().name("User1").build();
        ParticipantQuery query2 = ParticipantQuery.builder().name("User2").build();
        ParticipantQuery query3 = ParticipantQuery.builder().name("User3").build();

        this.challonge.bulkAddParticipants(this.tournament, Arrays.asList(query1, query2, query3));

        List<Participant> randomized = this.challonge.randomizeParticipants(this.tournament);
        assertEquals(3, randomized.size());

        Optional<Participant> user1 = randomized.stream().filter(p -> p.getName().equals("User1")).findFirst();
        Optional<Participant> user2 = randomized.stream().filter(p -> p.getName().equals("User2")).findFirst();
        Optional<Participant> user3 = randomized.stream().filter(p -> p.getName().equals("User3")).findFirst();

        assertTrue(user1.isPresent());
        assertTrue(user2.isPresent());
        assertTrue(user3.isPresent());
    }

    @Test
    public final void testCheckInParticipant() throws DataAccessException {
        ParticipantQuery createQuery = ParticipantQuery.builder().name("Stefan").build();

        Participant createdParticipant = this.challonge.addParticipant(this.tournament, createQuery);
        Participant checkedInParticipant = this.challonge.checkInParticipant(createdParticipant);

        assertTrue(checkedInParticipant.getCheckedIn());
        assertNotNull(checkedInParticipant.getCheckedInAt());
    }

    @Test
    public final void testUndoCheckInParticipant() throws DataAccessException {
        ParticipantQuery createQuery = ParticipantQuery.builder().name("Stefan").build();

        Participant createdParticipant = this.challonge.addParticipant(this.tournament, createQuery);
        Participant checkedInParticipant = this.challonge.checkInParticipant(createdParticipant);

        Participant checkedOutParticipant = this.challonge.undoCheckInParticipant(checkedInParticipant);

        Assert.assertFalse(checkedOutParticipant.getCheckedIn());
        Assert.assertNull(checkedOutParticipant.getCheckedInAt());
    }

    @Test
    public final void testGetParticipants() throws DataAccessException {
        ParticipantQuery query1 = ParticipantQuery.builder().name("User1").build();
        ParticipantQuery query2 = ParticipantQuery.builder().name("User2").build();
        ParticipantQuery query3 = ParticipantQuery.builder().name("User3").build();

        this.challonge.bulkAddParticipants(this.tournament, Arrays.asList(query1, query2, query3));

        List<Participant> participants = this.challonge.getParticipants(this.tournament);

        Participant user1 = getParticipant(participants, "User1");
        Participant user2 = getParticipant(participants, "User2");
        Participant user3 = getParticipant(participants, "User3");

        assertNotNull(user1);
        assertNotNull(user2);
        assertNotNull(user3);
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
