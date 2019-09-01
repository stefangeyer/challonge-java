package at.stefangeyer.challonge.unit;

import at.stefangeyer.challonge.Challonge;
import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.*;
import at.stefangeyer.challonge.model.enumeration.MatchState;
import at.stefangeyer.challonge.model.enumeration.TournamentType;
import at.stefangeyer.challonge.model.query.MatchQuery;
import at.stefangeyer.challonge.model.query.wrapper.MatchQueryWrapper;
import at.stefangeyer.challonge.model.wrapper.MatchWrapper;
import at.stefangeyer.challonge.rest.MatchRestClient;
import at.stefangeyer.challonge.rest.RestClient;
import at.stefangeyer.challonge.serializer.Serializer;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static at.stefangeyer.challonge.unit.util.Util.ifNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MatchTest {

    private Challonge challonge;

    private Object[] holder = new Object[1];

    private Tournament tournament = Tournament.builder()
            .id(10L).url("tourney123").tournamentType(TournamentType.SINGLE_ELIMINATION)
            .matches(new ArrayList<>(Arrays.asList(
                    Match.builder().id(1L).tournamentId(10L).player1Id(1L).player2Id(2L).attachments(new ArrayList<>(
                            Collections.singletonList(
                                    Attachment.builder().id(1L).description("Attachment note").build()
                            ))).build(),
                    Match.builder().id(2L).tournamentId(10L).player1Id(1L).player2Id(3L).build(),
                    Match.builder().id(3L).tournamentId(10L).player1Id(1L).player2Id(4L).build(),
                    Match.builder().id(4L).tournamentId(10L).player1Id(2L).player2Id(3L).build(),
                    Match.builder().id(5L).tournamentId(10L).player1Id(2L).player2Id(4L).build()
            ))).participants(new ArrayList<>(Arrays.asList(
                    Participant.builder().id(1L).tournamentId(10L).name("Participant 1").matches(new ArrayList<>()).build(),
                    Participant.builder().id(2L).tournamentId(10L).name("Participant 2").matches(new ArrayList<>()).build(),
                    Participant.builder().id(3L).tournamentId(10L).name("Participant 3").matches(new ArrayList<>()).build(),
                    Participant.builder().id(4L).tournamentId(10L).name("Participant 4").matches(new ArrayList<>()).build(),
                    Participant.builder().id(5L).tournamentId(10L).name("Participant 5").matches(new ArrayList<>()).build()
            ))).build();

    public MatchTest() throws DataAccessException {
        MatchRestClient mrc = mock(MatchRestClient.class);

        when(mrc.getMatches(any(), any(), any())).thenAnswer(i -> {
            Tournament t = getTournament(i.getArgument(0));
            List<Match> matches = t.getMatches();

            Long pid = i.getArgument(1);
            if (pid != null) {
                matches = matches.stream()
                        .filter(m -> m.getPlayer1Id().equals(pid) || m.getPlayer2Id().equals(pid))
                        .collect(Collectors.toList());
            }

            MatchState state = i.getArgument(2);
            if (state != null) {
                matches = matches.stream().filter(m -> m.getPlayer1Id().equals(pid) || m.getPlayer2Id().equals(pid))
                        .collect(Collectors.toList());
            }

            return matches.stream().map(MatchWrapper::new).collect(Collectors.toList());
        });

        doAnswer(i -> {
            Callback<List<MatchWrapper>> onSuccess = i.getArgument(3);

            List<MatchWrapper> matches = mrc.getMatches(i.getArgument(0), i.getArgument(1), i.getArgument(2));

            onSuccess.accept(matches);

            return null;
        }).when(mrc).getMatches(any(), any(), any(), any(), any());

        when(mrc.getMatch(any(), anyLong(), anyBoolean())).thenAnswer(i -> {
            Tournament t = getTournament(i.getArgument(0));
            Match m = getMatch(t, i.getArgument(1));

            if (!i.<Boolean>getArgument(2)) {
                m.getAttachments().clear();
            }

            return new MatchWrapper(m);
        });

        doAnswer(i -> {
            Callback<MatchWrapper> onSuccess = i.getArgument(3);

            MatchWrapper match = mrc.getMatch(i.getArgument(0), i.getArgument(1), i.getArgument(2));

            onSuccess.accept(match);

            return null;
        }).when(mrc).getMatch(any(), anyLong(), anyBoolean(), any(), any());

        when(mrc.updateMatch(any(), anyLong(), any())).thenAnswer(i -> {
            Tournament t = getTournament(i.getArgument(0));
            Match m = getMatch(t, i.getArgument(1));

            MatchQuery data = i.<MatchQueryWrapper>getArgument(2).getMatch();

            Match updated = Match.builder().id(m.getId()).tournamentId(m.getTournamentId())
                    .winnerId(ifNotNull(data.getWinnerId(), m.getWinnerId()))
                    .scoresCsv(ifNotNull(data.getScoresCsv(), m.getScoresCsv()))
                    .build();

            return new MatchWrapper(updated);
        });

        doAnswer(i -> {
            Callback<MatchWrapper> onSuccess = i.getArgument(3);

            MatchWrapper match = mrc.updateMatch(i.getArgument(0), i.getArgument(1), i.getArgument(2));

            onSuccess.accept(match);

            return null;
        }).when(mrc).updateMatch(any(), anyLong(), any(), any(), any());

        when(mrc.reopenMatch(any(), anyLong())).thenAnswer(i -> {
            Tournament t = getTournament(i.getArgument(0));
            Match m = getMatch(t, i.getArgument(1));

            // emitted content update

            return new MatchWrapper(m);
        });

        doAnswer(i -> {
            Callback<MatchWrapper> onSuccess = i.getArgument(2);

            MatchWrapper match = mrc.reopenMatch(i.getArgument(0), i.getArgument(1));

            onSuccess.accept(match);

            return null;
        }).when(mrc).reopenMatch(any(), anyLong(), any(), any());

        RestClient restClient = mock(RestClient.class);

        Serializer serializer = mock(Serializer.class);

        this.challonge = new Challonge(new Credentials("", ""), serializer, restClient);
    }

    private Tournament getTournament(String key) {
        return this.tournament;
    }

    private Match getMatch(Tournament t, long id) throws DataAccessException {
        Optional<Match> optional = t.getMatches().stream().filter(m -> m.getId().equals(id)).findFirst();

        if (!optional.isPresent()) {
            throw new DataAccessException("match not found");
        }

        return optional.get();
    }

    @Test
    public void testGetMatches() throws DataAccessException {
        Tournament tournament = getTournament("tourney123");

        List<Match> local = this.challonge.getMatches(tournament);

        assertEquals(tournament.getMatches(), local);
    }

    @Test
    public void testGetMatchesAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament tournament = getTournament("tourney123");

        this.challonge.getMatches(tournament, l -> {
            this.holder[0] = l;
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        assertEquals(this.tournament.getMatches(), this.holder[0]);
    }

    @Test
    public void testGetMatch() throws DataAccessException {
        Tournament tournament = getTournament("tourney123");
        Match local = this.challonge.getMatch(tournament, 1);
        assertEquals(tournament.getMatches().get(0), local);
    }

    @Test
    public void testGetMatchAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament tournament = getTournament("tourney123");

        this.challonge.getMatch(tournament, 1, m -> {
            this.holder[0] = m;
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        assertEquals(tournament.getMatches().get(0), this.holder[0]);
    }

    @Test
    public void testUpdateMatch() throws DataAccessException {
        Tournament tournament = getTournament("tourney123");
        Match match = tournament.getMatches().get(0);
        Match local = this.challonge.updateMatch(match, MatchQuery.builder().winnerId(120L).build());
        assertEquals(120L, (long) local.getWinnerId());
    }

    @Test
    public void testUpdateMatchAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament tournament = getTournament("tourney123");
        Match match = tournament.getMatches().get(0);

        this.challonge.updateMatch(match, MatchQuery.builder().winnerId(120L).build(), m -> {
            this.holder[0] = m;
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Match local = (Match) this.holder[0];

        assertEquals(120L, (long) local.getWinnerId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateMatchNoData() throws DataAccessException {
        Tournament tournament = getTournament("tourney123");
        Match match = tournament.getMatches().get(0);
        this.challonge.updateMatch(match, MatchQuery.builder().build());
    }

    @Test
    public void testReopenMatch() throws DataAccessException {
        Tournament tournament = getTournament("tourney123");
        Match match = tournament.getMatches().get(0);
        Match local = this.challonge.reopenMatch(match);
        assertEquals(match, local);
    }

    @Test
    public void testReopenMatchAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament tournament = getTournament("tourney123");
        Match match = tournament.getMatches().get(0);

        this.challonge.reopenMatch(match, m -> {
            this.holder[0] = m;
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Match local = (Match) this.holder[0];

        assertEquals(match, local);
    }
}
