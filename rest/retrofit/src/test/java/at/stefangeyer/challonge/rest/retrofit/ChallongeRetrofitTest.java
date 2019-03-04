package at.stefangeyer.challonge.rest.retrofit;

import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.enumeration.TournamentType;
import at.stefangeyer.challonge.model.query.TournamentQuery;
import at.stefangeyer.challonge.model.query.wrapper.TournamentQueryWrapper;
import at.stefangeyer.challonge.model.wrapper.TournamentWrapper;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ChallongeRetrofitTest {

    private static final String BASE_URL = "https://api.challonge.com/v1/";

    private Object[] holder = new Object[1];

    private MockChallongeRetrofit challongeRetrofit;

    private RetrofitTournamentRestClient tournamentRestClient;
    private RetrofitParticipantRestClient participantRestClient;
    private RetrofitMatchRestClient matchRestClient;
    private RetrofitAttachmentRestClient attachmentRestClient;

    public ChallongeRetrofitTest() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).build();

        NetworkBehavior networkBehavior = NetworkBehavior.create();
        networkBehavior.setDelay(0, TimeUnit.MILLISECONDS);

        MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit).networkBehavior(networkBehavior).build();

        BehaviorDelegate<ChallongeRetrofit> delegate = mockRetrofit.create(ChallongeRetrofit.class);

        this.challongeRetrofit = new MockChallongeRetrofit(delegate);

        this.tournamentRestClient = new RetrofitTournamentRestClient(challongeRetrofit);
        this.participantRestClient = new RetrofitParticipantRestClient(challongeRetrofit);
        this.matchRestClient = new RetrofitMatchRestClient(challongeRetrofit);
        this.attachmentRestClient = new RetrofitAttachmentRestClient(challongeRetrofit);
    }

    @Test
    public void testGetTournaments() throws DataAccessException {
        List<Tournament> tournaments = this.tournamentRestClient.getTournaments(null, null, null, null, null).stream()
                .map(TournamentWrapper::getTournament).collect(Collectors.toList());

        assertEquals(this.challongeRetrofit.getTournaments(), tournaments);
    }

    @Test
    public void testGetTournamentsAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        this.tournamentRestClient.getTournaments(null, null, null, null, null, l -> {
            this.holder[0] = l.stream().map(TournamentWrapper::getTournament).collect(Collectors.toList());
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        assertEquals(this.challongeRetrofit.getTournaments(), this.holder[0]);
    }

    @Test
    public void testGetTournament() throws DataAccessException {
        Tournament local = this.tournamentRestClient.getTournament("tourney123", true, true).getTournament();
        assertEquals(this.challongeRetrofit.getTournaments().get(0), local);
    }

    @Test
    public void testGetTournamentAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        this.tournamentRestClient.getTournament("tourney123", true, true, t -> {
            this.holder[0] = t.getTournament();
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        assertEquals(this.challongeRetrofit.getTournaments().get(0), this.holder[0]);
    }

    @Test
    public void testCreateTournament() throws DataAccessException {
        Tournament local = this.tournamentRestClient.createTournament(new TournamentQueryWrapper(
                TournamentQuery.builder().name("TournamentTest").tournamentType(TournamentType.SINGLE_ELIMINATION)
                        .url("sometournament").build())).getTournament();

        assertEquals(this.challongeRetrofit.getTournaments().stream().filter(t -> t.getUrl().equals("sometournament"))
                .findFirst().get(), local);
    }

    @Test
    public void testCreateTournamentAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        TournamentQuery query = TournamentQuery.builder().name("TournamentTest").url("sometournament")
                .tournamentType(TournamentType.SINGLE_ELIMINATION).build();

        this.tournamentRestClient.createTournament(new TournamentQueryWrapper(query), t -> {
                    this.holder[0] = t.getTournament();
                    latch.countDown();
                },
                e -> {
                });

        latch.await(2000, TimeUnit.MILLISECONDS);

        assertEquals(this.challongeRetrofit.getTournaments().stream().filter(t -> t.getUrl().equals("sometournament"))
                        .findFirst().get(), this.holder[0]);
    }

    @Test
    public void testUpdateTournament() throws DataAccessException {
        Tournament updated = this.tournamentRestClient.updateTournament(this.challongeRetrofit.getTournaments().get(0).getUrl(),
                new TournamentQueryWrapper(TournamentQuery.builder().name("UpdatedName12345").build())).getTournament();

        // If one param got passed correctly, all params will be passed correctly (same enumeration object)
        assertEquals("UpdatedName12345", updated.getName());
        assertEquals(this.challongeRetrofit.getTournaments().get(0), updated);
    }

    @Test
    public void testUpdateTournamentAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        this.tournamentRestClient.updateTournament("tourney123",
                new TournamentQueryWrapper(TournamentQuery.builder().name("UpdatedName12345").build()), t -> {
            this.holder[0] = t.getTournament();
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Tournament t = (Tournament) this.holder[0];

        assertEquals("UpdatedName12345", t.getName());
        assertEquals(this.challongeRetrofit.getTournaments().get(0), t);
    }

    @Test
    public void testDeleteTournament() throws DataAccessException {
        Tournament toDelete = this.tournamentRestClient.getTournament("tournament45", true, true).getTournament();
        Tournament deleted = this.tournamentRestClient.deleteTournament("tournament45").getTournament();

        assertFalse(this.challongeRetrofit.getTournaments().contains(toDelete));
        assertEquals(toDelete, deleted);
    }

    @Test
    public void testDeleteTournamentAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament toDelete = this.challongeRetrofit.getTournaments().get(1);

        this.tournamentRestClient.deleteTournament(toDelete.getUrl(), t -> {
            this.holder[0] = t.getTournament();
            latch.countDown();
        }, e -> {
        });

        Tournament t = (Tournament) this.holder[0];

        latch.await(2000, TimeUnit.MILLISECONDS);

        assertFalse(this.challongeRetrofit.getTournaments().contains(toDelete));
        assertEquals(toDelete, t);
    }

    @Test
    public void testProcessCheckIns() throws DataAccessException {
        Tournament result = this.tournamentRestClient.processCheckIns("tourney123", false, false).getTournament();

        assertEquals(this.challongeRetrofit.getTournaments().get(0), result);
        assertTrue(result.getParticipants().isEmpty());
        assertTrue(result.getMatches().isEmpty());
    }

    @Test
    public void testProcessCheckInsAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament toUpdate = this.challongeRetrofit.getTournaments().get(0);

        this.tournamentRestClient.processCheckIns(toUpdate.getUrl(), false, false, t -> {
            this.holder[0] = t.getTournament();
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        assertEquals(toUpdate, this.holder[0]);
    }

    @Test
    public void testAbortCheckIn() throws DataAccessException {
        Tournament result = this.tournamentRestClient.abortCheckIn("tourney123", true, true).getTournament();

        assertEquals(this.challongeRetrofit.getTournaments().get(0), result);
        assertFalse(result.getParticipants().isEmpty());
        assertFalse(result.getMatches().isEmpty());
    }

    @Test
    public void testAbortCheckInAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament toUpdate = this.challongeRetrofit.getTournaments().get(0);

        this.tournamentRestClient.abortCheckIn(toUpdate.getUrl(), false, false, t -> {
            this.holder[0] = t.getTournament();
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        assertEquals(toUpdate, this.holder[0]);
    }

    @Test
    public void testStartTournament() throws DataAccessException {
        Tournament result = this.tournamentRestClient.startTournament("tourney123", true, true).getTournament();

        assertEquals(this.challongeRetrofit.getTournaments().get(0), result);
        assertFalse(result.getParticipants().isEmpty());
        assertFalse(result.getMatches().isEmpty());
    }

    @Test
    public void testStartTournamentAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament toUpdate = this.challongeRetrofit.getTournaments().get(0);

        this.tournamentRestClient.startTournament(toUpdate.getUrl(), false, false, t -> {
            this.holder[0] = t.getTournament();
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        assertEquals(toUpdate, this.holder[0]);
    }

    @Test
    public void testFinalizeTournament() throws DataAccessException {
        Tournament result = this.tournamentRestClient.finalizeTournament("tourney123", true, true).getTournament();

        assertEquals(this.challongeRetrofit.getTournaments().get(0), result);
        assertFalse(result.getParticipants().isEmpty());
        assertFalse(result.getMatches().isEmpty());
    }

    @Test
    public void testFinalizeTournamentAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament toUpdate = this.challongeRetrofit.getTournaments().get(0);

        this.tournamentRestClient.finalizeTournament(toUpdate.getUrl(), false, false, t -> {
            this.holder[0] = t.getTournament();
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        assertEquals(toUpdate, this.holder[0]);
    }

    @Test
    public void testResetTournament() throws DataAccessException {
        Tournament result = this.tournamentRestClient.resetTournament("tourney123", true, true).getTournament();

        assertEquals(this.challongeRetrofit.getTournaments().get(0), result);
        assertFalse(result.getParticipants().isEmpty());
        assertFalse(result.getMatches().isEmpty());
    }

    @Test
    public void testResetTournamentAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament toUpdate = this.challongeRetrofit.getTournaments().get(0);

        this.tournamentRestClient.resetTournament(toUpdate.getUrl(), false, false, t -> {
            this.holder[0] = t.getTournament();
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        assertEquals(toUpdate, this.holder[0]);
    }

    @Test
    public void testOpenTournamentForPredictions() throws DataAccessException {
        Tournament result = this.tournamentRestClient.openTournamentForPredictions("tourney123", true, true).getTournament();

        assertEquals(this.challongeRetrofit.getTournaments().get(0), result);
        assertFalse(result.getParticipants().isEmpty());
        assertFalse(result.getMatches().isEmpty());
    }

    @Test
    public void testOpenTournamentForPredictionsAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament toUpdate = this.challongeRetrofit.getTournaments().get(0);

        this.tournamentRestClient.openTournamentForPredictions(toUpdate.getUrl(), false, false, t -> {
            this.holder[0] = t.getTournament();
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        assertEquals(toUpdate, this.holder[0]);
    }
}
