package at.stefangeyer.challonge.rest.retrofit;

import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.query.ParticipantQuery;
import at.stefangeyer.challonge.model.query.wrapper.ParticipantQueryListWrapper;
import at.stefangeyer.challonge.model.query.wrapper.ParticipantQueryWrapper;
import at.stefangeyer.challonge.model.wrapper.ParticipantWrapper;
import at.stefangeyer.challonge.rest.retrofit.util.MockChallongeRetrofitFactory;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RetrofitParticipantTest {
    private static final String TOURNEY_NAME = "tourney123";
    private static final int PARTICIPANT1_ID = 1;
    private static final int PARTICIPANT2_ID = 2;
    private static final int PARTICIPANT3_ID = 3;

    private Object[] holder = new Object[1];

    private MockChallongeRetrofit challongeRetrofit = MockChallongeRetrofitFactory.create();

    private RetrofitParticipantRestClient participantRestClient = new RetrofitParticipantRestClient(this.challongeRetrofit);

    @Test
    public void testGetParticipants() throws DataAccessException {
        List<Participant> local = this.participantRestClient.getParticipants(TOURNEY_NAME).stream()
                .map(ParticipantWrapper::getParticipant).collect(Collectors.toList());
        assertEquals(this.challongeRetrofit.get(TOURNEY_NAME).getParticipants(), local);
    }

    @Test
    public void testGetParticipantsAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        this.participantRestClient.getParticipants(TOURNEY_NAME, l -> {
            this.holder[0] = l.stream().map(ParticipantWrapper::getParticipant).collect(Collectors.toList());
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        assertEquals(this.challongeRetrofit.get(TOURNEY_NAME).getParticipants(), this.holder[0]);
    }

    @Test
    public void testGetParticipant() throws DataAccessException {
        Participant local = this.participantRestClient.getParticipant(TOURNEY_NAME, PARTICIPANT2_ID, false).getParticipant();
        assertEquals("Participant 2", local.getName());
    }

    @Test
    public void testGetParticipantAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        this.participantRestClient.getParticipant(TOURNEY_NAME, PARTICIPANT2_ID, false, p -> {
            this.holder[0] = p.getParticipant();
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Participant local = (Participant) this.holder[0];

        assertEquals("Participant 2", local.getName());
    }

    @Test
    public void testAddParticipant() throws DataAccessException {
        Participant local = this.participantRestClient.addParticipant(TOURNEY_NAME,
                new ParticipantQueryWrapper(ParticipantQuery.builder().name("New Participants").build())).getParticipant();
        assertTrue(this.challongeRetrofit.get(TOURNEY_NAME).getParticipants().contains(local));
    }

    @Test
    public void testAddParticipantAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        ParticipantQuery query = ParticipantQuery.builder().name("New Participants").build();

        this.participantRestClient.addParticipant(TOURNEY_NAME, new ParticipantQueryWrapper(query), p -> {
            this.holder[0] = p.getParticipant();
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Participant local = (Participant) this.holder[0];

        assertTrue(this.challongeRetrofit.get(TOURNEY_NAME).getParticipants().contains(local));
    }

    @Test
    public void testBulkAddParticipant() throws DataAccessException {
        List<ParticipantQuery> participants = Arrays.asList(ParticipantQuery.builder().name("Another Participant 1").build(),
                ParticipantQuery.builder().name("Another Participant 2").build());
        List<Participant> local = this.participantRestClient.bulkAddParticipants(TOURNEY_NAME,
                new ParticipantQueryListWrapper(participants)).stream().map(ParticipantWrapper::getParticipant)
                .collect(Collectors.toList());
        assertTrue(this.challongeRetrofit.get(TOURNEY_NAME).getParticipants().containsAll(local));
    }

    @Test
    public void testBulkAddParticipantAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        List<ParticipantQuery> queries = Arrays.asList(ParticipantQuery.builder().name("Another Participant 1").build(),
                ParticipantQuery.builder().name("Another Participant 2").build());

        this.participantRestClient.bulkAddParticipants(TOURNEY_NAME, new ParticipantQueryListWrapper(queries), l -> {
            this.holder[0] = l.stream().map(ParticipantWrapper::getParticipant).collect(Collectors.toList());
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        List p = (List) this.holder[0];

        assertTrue(this.challongeRetrofit.get(TOURNEY_NAME).getParticipants().containsAll(p));
    }

    @Test
    public void testUpdateParticipant() throws DataAccessException {
        Participant local = this.participantRestClient.updateParticipant(TOURNEY_NAME, PARTICIPANT2_ID,
                new ParticipantQueryWrapper(ParticipantQuery.builder().name("New Name").build())).getParticipant();
        assertEquals("New Name", local.getName());
    }

    @Test
    public void testUpdateParticipantAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        ParticipantQuery query = ParticipantQuery.builder().name("New Name").build();

        this.participantRestClient.updateParticipant(TOURNEY_NAME, PARTICIPANT3_ID, new ParticipantQueryWrapper(query), p -> {
            this.holder[0] = p.getParticipant();
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Participant local = (Participant) this.holder[0];

        assertEquals("New Name", local.getName());
    }

    @Test
    public void testCheckInParticipant() throws DataAccessException {
        Participant local = this.participantRestClient.checkInParticipant(TOURNEY_NAME, PARTICIPANT1_ID).getParticipant();
        assertEquals(this.challongeRetrofit.get(TOURNEY_NAME).getParticipants().get(0), local);
    }

    @Test
    public void testCheckInParticipantAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Participant participant = this.challongeRetrofit.get(TOURNEY_NAME, PARTICIPANT1_ID);

        this.participantRestClient.checkInParticipant(TOURNEY_NAME, PARTICIPANT1_ID, p -> {
            this.holder[0] = p.getParticipant();
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Participant local = (Participant) this.holder[0];

        assertEquals(participant, local);
    }

    @Test
    public void testUndoCheckInParticipant() throws DataAccessException {
        Participant participant = this.challongeRetrofit.get(TOURNEY_NAME, PARTICIPANT1_ID);
        Participant local = this.participantRestClient.undoCheckInParticipant(TOURNEY_NAME, participant.getId()).getParticipant();
        assertEquals(participant, local);
    }

    @Test
    public void testUndoCheckInParticipantAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Participant participant = this.challongeRetrofit.get(TOURNEY_NAME, PARTICIPANT1_ID);

        this.participantRestClient.undoCheckInParticipant(TOURNEY_NAME, participant.getId(), p -> {
            this.holder[0] = p.getParticipant();
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Participant local = (Participant) this.holder[0];

        assertEquals(participant, local);
    }

    @Test
    public void testDeleteParticipant() throws DataAccessException {
        Participant participant = this.challongeRetrofit.get(TOURNEY_NAME, PARTICIPANT1_ID);
        Participant local = this.participantRestClient.deleteParticipant(TOURNEY_NAME, PARTICIPANT1_ID).getParticipant();
        assertEquals(participant, local);
    }

    @Test
    public void testDeleteParticipantAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Participant participant = this.challongeRetrofit.get(TOURNEY_NAME, PARTICIPANT1_ID);

        this.participantRestClient.deleteParticipant(TOURNEY_NAME, PARTICIPANT1_ID, p -> {
            this.holder[0] = p.getParticipant();
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Participant local = (Participant) this.holder[0];

        assertEquals(participant, local);
    }

    @Test
    public void testRandomizeParticipant() throws DataAccessException {
        List<Participant> local = this.participantRestClient.randomizeParticipants(TOURNEY_NAME)
                .stream().map(ParticipantWrapper::getParticipant).collect(Collectors.toList());
        assertEquals(this.challongeRetrofit.get(TOURNEY_NAME).getParticipants(), local);
    }

    @Test
    public void testRandomizeParticipantAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        this.participantRestClient.randomizeParticipants(TOURNEY_NAME, l -> {
            this.holder[0] = l.stream().map(ParticipantWrapper::getParticipant).collect(Collectors.toList());
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        List local = (List) this.holder[0];

        assertEquals(this.challongeRetrofit.get(TOURNEY_NAME).getParticipants(), local);
    }
}
