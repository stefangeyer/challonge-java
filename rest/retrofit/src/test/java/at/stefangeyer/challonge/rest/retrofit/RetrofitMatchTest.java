package at.stefangeyer.challonge.rest.retrofit;

import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.query.MatchQuery;
import at.stefangeyer.challonge.model.query.wrapper.MatchQueryWrapper;
import at.stefangeyer.challonge.model.wrapper.MatchWrapper;
import at.stefangeyer.challonge.rest.retrofit.util.MockChallongeRetrofitFactory;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class RetrofitMatchTest {
    private static final String TOURNEY_NAME = "tourney123";

    private Object[] holder = new Object[1];

    private MockChallongeRetrofit challongeRetrofit = MockChallongeRetrofitFactory.create();

    private RetrofitMatchRestClient matchRestClient = new RetrofitMatchRestClient(this.challongeRetrofit);

    private Tournament get(String key) {
        return this.challongeRetrofit.getTournaments().stream()
                .filter(t -> t.getUrl().equals(key) || t.getId().toString().equals(key)).findFirst().orElse(null);
    }

    @Test
    public void testGetMatches() throws DataAccessException {
        Tournament tournament = get("tourney123");

        List<Match> local = this.matchRestClient.getMatches(TOURNEY_NAME, null, null).stream()
                .map(MatchWrapper::getMatch).collect(Collectors.toList());

        assertEquals(tournament.getMatches(), local);
    }

    @Test
    public void testGetMatchesAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament tournament = get("tourney123");

        this.matchRestClient.getMatches(TOURNEY_NAME, null, null, l -> {
            this.holder[0] = l.stream().map(MatchWrapper::getMatch).collect(Collectors.toList());
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        assertEquals(tournament.getMatches(), this.holder[0]);
    }

    @Test
    public void testGetMatch() throws DataAccessException {
        Tournament tournament = get("tourney123");
        Match local = this.matchRestClient.getMatch(TOURNEY_NAME, 1, false).getMatch();
        assertEquals(tournament.getMatches().get(0), local);
    }

    @Test
    public void testGetMatchAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament tournament = get("tourney123");

        this.matchRestClient.getMatch(TOURNEY_NAME, 1, false, m -> {
            this.holder[0] = m.getMatch();
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        assertEquals(tournament.getMatches().get(0), this.holder[0]);
    }

    @Test
    public void testUpdateMatch() throws DataAccessException {
        Match local = this.matchRestClient.updateMatch(TOURNEY_NAME, 1, new MatchQueryWrapper(
                MatchQuery.builder().winnerId(120L).build())).getMatch();
        assertEquals(120L, (long) local.getWinnerId());
    }

    @Test
    public void testUpdateMatchAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        this.matchRestClient.updateMatch(TOURNEY_NAME, 1, new MatchQueryWrapper(MatchQuery.builder().winnerId(120L).build()), m -> {
            this.holder[0] = m.getMatch();
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Match local = (Match) this.holder[0];

        assertEquals(120L, (long) local.getWinnerId());
    }

    @Test
    public void testReopenMatch() throws DataAccessException {
        Tournament tournament = get("tourney123");
        Match match = tournament.getMatches().get(0);
        Match local = this.matchRestClient.reopenMatch(TOURNEY_NAME, 1).getMatch();
        assertEquals(match, local);
    }

    @Test
    public void testReopenMatchAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament tournament = get("tourney123");
        Match match = tournament.getMatches().get(0);

        this.matchRestClient.reopenMatch(TOURNEY_NAME, 1, m -> {
            this.holder[0] = m.getMatch();
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Match local = (Match) this.holder[0];

        assertEquals(match, local);
    }
}
