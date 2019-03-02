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
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParticipantTest {
    private static final String TOURNAMENT_URL = "javaapitest";

    private Challonge challonge;
    private Tournament tournament;

    public ParticipantTest() throws DataAccessException {
        String username = System.getProperty("challongeUsername");
        String apiKey = System.getProperty("challongeApiKey");
        if (username == null || apiKey == null) {
            throw new IllegalArgumentException("Required system properties challongeUsername and challongeApiKey are absent");
        }

        this.challonge = new Challonge(new Credentials(username, apiKey), new GsonSerializer(), new RetrofitRestClient());

        OffsetDateTime start = OffsetDateTime.now().plusMinutes(75L);
        TournamentQuery query = TournamentQuery.builder().name("Participants").url(TOURNAMENT_URL)
                .checkInDuration(120L).startAt(start).build();

        this.tournament = this.challonge.createTournament(query);
    }

    @Test
    public final void aAddParticipants() throws DataAccessException {
        ParticipantQuery query = ParticipantQuery.builder().name("Stefan").seed(1).misc("MiscTest").build();

        Participant participant = this.challonge.addParticipant(this.tournament, query);
        assertEquals("Stefan", participant.getName());
        assertEquals(1, (int) participant.getSeed());
        assertEquals("MiscTest", participant.getMisc());
    }

    @Test
    public final void bBulkAddParticipants() throws DataAccessException {
        ParticipantQuery query1 = ParticipantQuery.builder().name("Bulk1").seed(1).build();
        ParticipantQuery query2 = ParticipantQuery.builder().name("Bulk2").inviteNameOrEmail("Bulk").seed(2).misc("BulkAdd").build();

        List<Participant> participants = this.challonge.bulkAddParticipants(this.tournament, List.of(query1, query2));
        Participant participant1 = participants.get(0);
        Participant participant2 = participants.get(1);

        assertEquals("Bulk1", participant1.getName());
        assertEquals(1, (int) participant1.getSeed());
        assertEquals("Bulk2", participant2.getName());

        assertEquals("bulk", participant2.getUsername().toLowerCase());
        assertEquals("BulkAdd", participant2.getMisc());
        assertEquals(2, (int) participant2.getSeed());
    }

    @Test
    public final void cUpdateParticipant() throws DataAccessException {
        ParticipantQuery createQuery = ParticipantQuery.builder().name("Stefan").misc("123").build();
        Participant createdParticipant = this.challonge.addParticipant(this.tournament, createQuery);
        ParticipantQuery updateQuery = ParticipantQuery.builder().name("Geyer").misc("321").build();

        Participant updatedParticipant = this.challonge.updateParticipant(createdParticipant, updateQuery);

        assertEquals("Geyer", updatedParticipant.getName());
        assertEquals("321", updatedParticipant.getMisc());
    }

    @Test(expected = DataAccessException.class)
    public final void dDeleteParticipant() throws DataAccessException {
        ParticipantQuery createQuery = ParticipantQuery.builder().name("Stefan").build();

        Participant createdParticipant = this.challonge.addParticipant(this.tournament, createQuery);
        Participant deletedParticipant = this.challonge.deleteParticipant(createdParticipant);

        this.challonge.getParticipant(this.tournament, deletedParticipant.getId(), false);

        // Get call should fail
        fail();
    }

    @Test
    public final void eRandomizeParticipants() throws DataAccessException {
        ParticipantQuery query1 = ParticipantQuery.builder().name("User1").build();
        ParticipantQuery query2 = ParticipantQuery.builder().name("User2").build();
        ParticipantQuery query3 = ParticipantQuery.builder().name("User3").build();

        this.challonge.bulkAddParticipants(this.tournament, List.of(query1, query2, query3));

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
    public final void fCheckInParticipant() throws DataAccessException {
        ParticipantQuery createQuery = ParticipantQuery.builder().name("Stefan").build();

        Participant createdParticipant = this.challonge.addParticipant(this.tournament, createQuery);
        Participant checkedInParticipant = this.challonge.checkInParticipant(createdParticipant);

        assertTrue(checkedInParticipant.getCheckedIn());
        assertNotNull(checkedInParticipant.getCheckedInAt());
    }

    @Test
    public final void gUndoCheckInParticipant() throws DataAccessException {
        ParticipantQuery createQuery = ParticipantQuery.builder().name("Stefan").build();

        Participant createdParticipant = this.challonge.addParticipant(this.tournament, createQuery);
        Participant checkedInParticipant = this.challonge.checkInParticipant(createdParticipant);

        Participant checkedOutParticipant = this.challonge.undoCheckInParticipant(checkedInParticipant);

        Assert.assertFalse(checkedOutParticipant.getCheckedIn());
        Assert.assertNull(checkedOutParticipant.getCheckedInAt());
    }

    @Test
    public final void hGetParticipants() throws DataAccessException {
        ParticipantQuery query1 = ParticipantQuery.builder().name("User1").build();
        ParticipantQuery query2 = ParticipantQuery.builder().name("User2").build();
        ParticipantQuery query3 = ParticipantQuery.builder().name("User3").build();

        this.challonge.bulkAddParticipants(this.tournament, List.of(query1, query2, query3));

        List<Participant> participants = this.challonge.getParticipants(this.tournament);

        Optional<Participant> user1 = participants.stream().filter(p -> p.getName().equals("User1")).findFirst();
        Optional<Participant> user2 = participants.stream().filter(p -> p.getName().equals("User2")).findFirst();
        Optional<Participant> user3 = participants.stream().filter(p -> p.getName().equals("User3")).findFirst();

        assertTrue(user1.isPresent());
        assertTrue(user2.isPresent());
        assertTrue(user3.isPresent());
    }

    @Test
    public final void zDeleteTournament() throws DataAccessException {
        this.challonge.deleteTournament(this.tournament);
    }
}
