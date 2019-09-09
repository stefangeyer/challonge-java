package at.stefangeyer.challonge.unit;

import at.stefangeyer.challonge.Challonge;
import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Credentials;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.enumeration.RankedBy;
import at.stefangeyer.challonge.model.enumeration.TournamentType;
import at.stefangeyer.challonge.model.query.TournamentQuery;
import at.stefangeyer.challonge.model.query.enumeration.GrandFinalsModifier;
import at.stefangeyer.challonge.model.query.wrapper.TournamentQueryWrapper;
import at.stefangeyer.challonge.model.wrapper.TournamentWrapper;
import at.stefangeyer.challonge.rest.RestClient;
import at.stefangeyer.challonge.serializer.Serializer;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static at.stefangeyer.challonge.unit.util.Util.ifNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.*;

public class TournamentTest {

    private Challonge challonge;

    private Random random = new Random();

    private Object[] holder = new Object[1];

    private List<Tournament> tournaments = new ArrayList<>(Arrays.asList(
            Tournament.builder().id(10L).url("tourney123")
                    .tournamentType(TournamentType.SINGLE_ELIMINATION)
                    .participants(new ArrayList<>(Arrays.asList(
                            Participant.builder().id(1L).tournamentId(10L).build(),
                            Participant.builder().id(2L).tournamentId(10L).build()
                    ))).matches(new ArrayList<>(Collections.singletonList(
                    Match.builder().id(1L).tournamentId(10L).player1Id(1L).player2Id(2L).build()
            ))).build(),
            Tournament.builder().id(11002L).url("sometourney")
                    .tournamentType(TournamentType.ROUND_ROBIN)
                    .participants(new ArrayList<>())
                    .matches(new ArrayList<>()).build(),
            Tournament.builder().id(45678L).url("tournament45")
                    .tournamentType(TournamentType.DOUBLE_ELIMINATION)
                    .rankedBy(RankedBy.GAME_WINS)
                    .participants(new ArrayList<>())
                    .matches(new ArrayList<>()).build()));

    public TournamentTest() throws DataAccessException {
        RestClient trc = mock(RestClient.class);

        when(trc.getTournaments(any(), any(), any(), any(), any())).thenAnswer(i -> this.tournaments.stream()
                .filter(t -> i.getArgument(0) == null || t.getState().equals(i.getArgument(0)))
                .filter(t -> i.getArgument(1) == null || t.getTournamentType().equals(i.getArgument(1)))
                .filter(t -> i.getArgument(2) == null || t.getCreatedAt().isBefore(i.getArgument(2)))
                .filter(t -> i.getArgument(3) == null || t.getCreatedAt().isAfter(i.getArgument(3)))
                .filter(t -> i.getArgument(4) == null || t.getSubdomain().equals(i.getArgument(4)))
                .map(TournamentWrapper::new).collect(Collectors.toList()));

        doAnswer(i -> {
            Callback<List<TournamentWrapper>> onSuccess = i.getArgument(5);

            List<TournamentWrapper> tournaments = trc.getTournaments(i.getArgument(0), i.getArgument(1), i.getArgument(2),
                    i.getArgument(3), i.getArgument(4));

            onSuccess.accept(tournaments);

            return null;
        }).when(trc).getTournaments(any(), any(), any(), any(), any(), any(), any());

        when(trc.getTournament(any(), anyBoolean(), anyBoolean())).thenAnswer(i -> {
            Optional<Tournament> optional = this.tournaments.stream()
                    .filter(t -> t.getUrl().equals(i.getArgument(0)) || t.getId().toString().equals(i.getArgument(0))).findFirst();

            if (!optional.isPresent()) throw new DataAccessException("No tournament found");

            Tournament tournament = optional.get();

            if (!i.<Boolean>getArgument(1)) tournament.getParticipants().clear();
            if (!i.<Boolean>getArgument(2)) tournament.getMatches().clear();

            return new TournamentWrapper(tournament);
        });

        doAnswer(i -> {
            Callback<TournamentWrapper> onSuccess = i.getArgument(3);

            TournamentWrapper tournament = trc.getTournament(i.getArgument(0), i.getArgument(1), i.getArgument(2));

            onSuccess.accept(tournament);

            return null;
        }).when(trc).getTournament(any(), anyBoolean(), anyBoolean(), any(), any());

        when(trc.createTournament(any())).thenAnswer(i -> {
            TournamentQuery data = i.<TournamentQueryWrapper>getArgument(0).getTournament();

            if (data.getTournamentType() == null || data.getUrl() == null) {
                throw new DataAccessException("Must provide valid query data");
            }

            Tournament tournament = Tournament.builder().id((long) this.random.nextInt(1000))
                    .name(data.getName()).tournamentType(data.getTournamentType())
                    .url(data.getUrl()).description(data.getDescription())
                    .openSignup(ifNotNull(data.getOpenSignup(), false))
                    .holdThirdPlaceMatch(ifNotNull(data.getHoldThirdPlaceMatch(), false))
                    .pointsForMatchWin(ifNotNull(data.getPointsForMatchWin(), 0F))
                    .pointsForMatchTie(ifNotNull(data.getPointsForMatchTie(), 0F))
                    .pointsForGameWin(ifNotNull(data.getPointsForGameWin(), 0F))
                    .pointsForGameTie(ifNotNull(data.getPointsForGameTie(), 0F))
                    .pointsForBye(ifNotNull(data.getPointsForBye(), 0F))
                    .swissRounds(ifNotNull(data.getSwissRounds(), 0))
                    .rankedBy(ifNotNull(data.getRankedBy(), RankedBy.MATCH_WINS))
                    .roundRobinPointsForGameWin(ifNotNull(data.getRoundRobinPointsForGameWin(), 0F))
                    .roundRobinPointsForGameTie(ifNotNull(data.getRoundRobinPointsForGameTie(), 0F))
                    .roundRobinPointsForMatchWin(ifNotNull(data.getRoundRobinPointsForMatchWin(), 0F))
                    .roundRobinPointsForMatchTie(ifNotNull(data.getRoundRobinPointsForMatchTie(), 0F))
                    .acceptAttachments(ifNotNull(data.getAcceptAttachments(), false))
                    .hideForum(ifNotNull(data.getHideForum(), false))
                    .showRounds(ifNotNull(data.getShowRounds(), false))
                    .privateOnly(ifNotNull(data.getPrivateOnly(), false))
                    .notifyUsersWhenMatchesOpen(ifNotNull(data.getNotifyUsersWhenMatchesOpen(), false))
                    .notifyUsersWhenTheTournamentEnds(ifNotNull(data.getNotifyUsersWhenTheTournamentEnds(), false))
                    .sequentialPairings(ifNotNull(data.getSequentialPairings(), false))
                    .signupCap(ifNotNull(data.getSignupCap(), 0))
                    .startAt(data.getStartAt()).checkInDuration(ifNotNull(data.getCheckInDuration(), 0L))
                    .grandFinalsModifier(ifNotNull(data.getGrandFinalsModifier(), GrandFinalsModifier.BLANK))
                    .tieBreaks(ifNotNull(data.getTieBreaks(), new ArrayList<>()))
                    .createdAt(OffsetDateTime.now()).build();

            this.tournaments.add(tournament);

            return new TournamentWrapper(tournament);
        });

        doAnswer(i -> {
            Callback<TournamentWrapper> onSuccess = i.getArgument(1);

            TournamentWrapper tournament = trc.createTournament(i.getArgument(0));

            onSuccess.accept(tournament);

            return null;
        }).when(trc).createTournament(any(), any(), any());

        when(trc.updateTournament(any(), any())).thenAnswer(i -> {
            Tournament curr = getTournament(i.getArgument(0));
            TournamentQuery data = i.<TournamentQueryWrapper>getArgument(1).getTournament();

            Tournament updated = Tournament.builder().id(curr.getId())
                    .name(ifNotNull(data.getName(), curr.getName()))
                    .tournamentType(ifNotNull(data.getTournamentType(), curr.getTournamentType()))
                    .url(ifNotNull(data.getUrl(), curr.getUrl()))
                    .description(ifNotNull(data.getDescription(), curr.getDescription()))
                    .openSignup(ifNotNull(data.getOpenSignup(), curr.getOpenSignup()))
                    .holdThirdPlaceMatch(ifNotNull(data.getHoldThirdPlaceMatch(), curr.getHoldThirdPlaceMatch()))
                    .pointsForMatchWin(ifNotNull(data.getPointsForMatchWin(), curr.getPointsForMatchWin()))
                    .pointsForMatchTie(ifNotNull(data.getPointsForMatchTie(), curr.getPointsForMatchTie()))
                    .pointsForGameWin(ifNotNull(data.getPointsForGameWin(), curr.getPointsForGameWin()))
                    .pointsForGameTie(ifNotNull(data.getPointsForGameTie(), curr.getPointsForGameTie()))
                    .pointsForBye(ifNotNull(data.getPointsForBye(), curr.getPointsForBye()))
                    .swissRounds(ifNotNull(data.getSwissRounds(), curr.getSwissRounds()))
                    .rankedBy(ifNotNull(data.getRankedBy(), curr.getRankedBy()))
                    .roundRobinPointsForGameWin(ifNotNull(data.getRoundRobinPointsForGameWin(), curr.getRoundRobinPointsForGameWin()))
                    .roundRobinPointsForGameTie(ifNotNull(data.getRoundRobinPointsForGameTie(), curr.getRoundRobinPointsForGameTie()))
                    .roundRobinPointsForMatchWin(ifNotNull(data.getRoundRobinPointsForMatchWin(), curr.getRoundRobinPointsForMatchWin()))
                    .roundRobinPointsForMatchTie(ifNotNull(data.getRoundRobinPointsForMatchTie(), curr.getPointsForMatchTie()))
                    .acceptAttachments(ifNotNull(data.getAcceptAttachments(), curr.getAcceptAttachments()))
                    .hideForum(ifNotNull(data.getHideForum(), curr.getHideForum()))
                    .showRounds(ifNotNull(data.getShowRounds(), curr.getShowRounds()))
                    .privateOnly(ifNotNull(data.getPrivateOnly(), curr.getPrivateOnly()))
                    .notifyUsersWhenMatchesOpen(ifNotNull(data.getNotifyUsersWhenMatchesOpen(), curr.getNotifyUsersWhenMatchesOpen()))
                    .notifyUsersWhenTheTournamentEnds(ifNotNull(data.getNotifyUsersWhenTheTournamentEnds(), curr.getNotifyUsersWhenTheTournamentEnds()))
                    .sequentialPairings(ifNotNull(data.getSequentialPairings(), curr.getSequentialPairings()))
                    .signupCap(ifNotNull(data.getSignupCap(), curr.getSignupCap()))
                    .startAt(data.getStartAt()).checkInDuration(ifNotNull(data.getCheckInDuration(), curr.getCheckInDuration()))
                    .grandFinalsModifier(ifNotNull(data.getGrandFinalsModifier(), curr.getGrandFinalsModifier()))
                    .tieBreaks(ifNotNull(data.getTieBreaks(), curr.getTieBreaks()))
                    .createdAt(curr.getCreatedAt()).updatedAt(OffsetDateTime.now())
                    .participants(curr.getParticipants()).matches(curr.getMatches())
                    .build();

            this.tournaments.set(this.tournaments.indexOf(curr), updated);

            return new TournamentWrapper(updated);
        });

        doAnswer(i -> {
            Callback<TournamentWrapper> onSuccess = i.getArgument(2);

            TournamentWrapper tournament = trc.updateTournament(i.getArgument(0), i.getArgument(1));

            onSuccess.accept(tournament);

            return null;
        }).when(trc).updateTournament(any(), any(), any(), any());

        when(trc.deleteTournament(any())).thenAnswer(i -> {
            Tournament t = getTournament(i.getArgument(0));
            this.tournaments.remove(t);
            return new TournamentWrapper(t);
        });

        doAnswer(i -> {
            Callback<TournamentWrapper> onSuccess = i.getArgument(1);

            TournamentWrapper tournament = trc.deleteTournament(i.getArgument(0));

            onSuccess.accept(tournament);

            return null;
        }).when(trc).deleteTournament(any(), any(), any());

        when(trc.processCheckIns(any(), anyBoolean(), anyBoolean())).thenAnswer(i -> {
            Tournament t = getTournament(i.getArgument(0));

            if (!i.<Boolean>getArgument(1)) t.getParticipants().clear();
            if (!i.<Boolean>getArgument(2)) t.getMatches().clear();

            // emitted content update

            return new TournamentWrapper(t);
        });

        doAnswer(i -> {
            Callback<TournamentWrapper> onSuccess = i.getArgument(3);

            TournamentWrapper tournament = trc.processCheckIns(i.getArgument(0), i.getArgument(1), i.getArgument(2));

            onSuccess.accept(tournament);

            return null;
        }).when(trc).processCheckIns(any(), anyBoolean(), anyBoolean(), any(), any());

        when(trc.abortCheckIn(any(), anyBoolean(), anyBoolean())).thenAnswer(i -> {
            Tournament t = getTournament(i.getArgument(0));

            // emitted content update

            if (!i.<Boolean>getArgument(1)) t.getParticipants().clear();
            if (!i.<Boolean>getArgument(2)) t.getMatches().clear();

            return new TournamentWrapper(t);
        });

        doAnswer(i -> {
            Callback<TournamentWrapper> onSuccess = i.getArgument(3);

            TournamentWrapper tournament = trc.abortCheckIn(i.getArgument(0), i.getArgument(1), i.getArgument(2));

            onSuccess.accept(tournament);

            return null;
        }).when(trc).abortCheckIn(any(), anyBoolean(), anyBoolean(), any(), any());

        when(trc.startTournament(any(), anyBoolean(), anyBoolean())).thenAnswer(i -> {
            Tournament t = getTournament(i.getArgument(0));

            // emitted content update

            if (!i.<Boolean>getArgument(1)) t.getParticipants().clear();
            if (!i.<Boolean>getArgument(2)) t.getMatches().clear();

            return new TournamentWrapper(t);
        });

        doAnswer(i -> {
            Callback<TournamentWrapper> onSuccess = i.getArgument(3);

            TournamentWrapper tournament = trc.startTournament(i.getArgument(0), i.getArgument(1), i.getArgument(2));

            onSuccess.accept(tournament);

            return null;
        }).when(trc).startTournament(any(), anyBoolean(), anyBoolean(), any(), any());

        when(trc.finalizeTournament(any(), anyBoolean(), anyBoolean())).thenAnswer(i -> {
            Tournament t = getTournament(i.getArgument(0));

            // emitted content update

            if (!i.<Boolean>getArgument(1)) t.getParticipants().clear();
            if (!i.<Boolean>getArgument(2)) t.getMatches().clear();

            return new TournamentWrapper(t);
        });

        doAnswer(i -> {
            Callback<TournamentWrapper> onSuccess = i.getArgument(3);

            TournamentWrapper tournament = trc.finalizeTournament(i.getArgument(0), i.getArgument(1), i.getArgument(2));

            onSuccess.accept(tournament);

            return null;
        }).when(trc).finalizeTournament(any(), anyBoolean(), anyBoolean(), any(), any());

        when(trc.resetTournament(any(), anyBoolean(), anyBoolean())).thenAnswer(i -> {
            Tournament t = getTournament(i.getArgument(0));

            // emitted content update

            if (!i.<Boolean>getArgument(1)) t.getParticipants().clear();
            if (!i.<Boolean>getArgument(2)) t.getMatches().clear();

            return new TournamentWrapper(t);
        });

        doAnswer(i -> {
            Callback<TournamentWrapper> onSuccess = i.getArgument(3);

            TournamentWrapper tournament = trc.resetTournament(i.getArgument(0), i.getArgument(1), i.getArgument(2));

            onSuccess.accept(tournament);

            return null;
        }).when(trc).resetTournament(any(), anyBoolean(), anyBoolean(), any(), any());

        when(trc.openTournamentForPredictions(any(), anyBoolean(), anyBoolean())).thenAnswer(i -> {
            Tournament t = getTournament(i.getArgument(0));

            // emitted content update

            if (!i.<Boolean>getArgument(1)) t.getParticipants().clear();
            if (!i.<Boolean>getArgument(2)) t.getMatches().clear();

            return new TournamentWrapper(t);
        });

        doAnswer(i -> {
            Callback<TournamentWrapper> onSuccess = i.getArgument(3);

            TournamentWrapper tournament = trc.openTournamentForPredictions(i.getArgument(0), i.getArgument(1), i.getArgument(2));

            onSuccess.accept(tournament);

            return null;
        }).when(trc).openTournamentForPredictions(any(), anyBoolean(), anyBoolean(), any(), any());

        Serializer serializer = mock(Serializer.class);

        this.challonge = new Challonge(new Credentials("", ""), serializer, trc);
    }

    private Tournament getTournament(String key) throws DataAccessException {
        Optional<Tournament> optional = this.tournaments.stream().filter(
                t -> t.getId().toString().equals(key) || t.getUrl().equals(key)).findFirst();

        if (!optional.isPresent()) throw new DataAccessException("tournament not found");

        return optional.get();
    }

    @Test
    public void testGetTournaments() throws DataAccessException {
        List<Tournament> local = this.challonge.getTournaments();
        assertEquals(this.tournaments, local);
    }

    @Test
    public void testGetTournamentsAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        this.challonge.getTournaments(l -> {
            this.holder[0] = l;
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        assertEquals(this.tournaments, this.holder[0]);
    }

    @Test
    public void testGetTournament() throws DataAccessException {
        Tournament local = this.challonge.getTournament("tourney123");
        assertEquals(this.tournaments.get(0), local);
    }

    @Test(expected = DataAccessException.class)
    public void testGetTournamentInvalid() throws DataAccessException {
        this.challonge.getTournament("abc");
    }

    @Test
    public void testGetTournamentAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        this.challonge.getTournament("tourney123", true, true, t -> {
            this.holder[0] = t;
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        assertEquals(this.tournaments.get(0), this.holder[0]);
    }

    @Test
    public void testCreateTournament() throws DataAccessException {
        Tournament local = this.challonge.createTournament(TournamentQuery.builder().name("TournamentTest")
                .tournamentType(TournamentType.SINGLE_ELIMINATION).url("sometournament").build());
        assertEquals(
                this.tournaments.stream().filter(t -> t.getUrl().equals("sometournament")).findFirst().get(),
                local);
    }

    @Test
    public void testCreateTournamentAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        TournamentQuery query = TournamentQuery.builder().name("TournamentTest").url("sometournament")
                .tournamentType(TournamentType.SINGLE_ELIMINATION).build();

        this.challonge.createTournament(query, local -> {
                    this.holder[0] = local;
                    latch.countDown();
                },
                e -> {
                });

        latch.await(2000, TimeUnit.MILLISECONDS);

        assertEquals(this.tournaments.stream().filter(t -> t.getUrl().equals("sometournament")).findFirst().get(),
                this.holder[0]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateTournamentNoData() throws DataAccessException {
        Tournament local = this.challonge.createTournament(TournamentQuery.builder().build());
        assertEquals(this.tournaments.get(0), local);
    }

    @Test
    public void testUpdateTournament() throws DataAccessException {
        Tournament updated = this.challonge.updateTournament(this.tournaments.get(0),
                TournamentQuery.builder().name("UpdatedName12345").build());

        // If one param got passed correctly, all params will be passed correctly (same enumeration object)
        assertEquals("UpdatedName12345", updated.getName());
        assertEquals(this.tournaments.get(0), updated);
    }

    @Test
    public void testUpdateTournamentAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        this.challonge.updateTournament(this.tournaments.get(0), TournamentQuery.builder().name("UpdatedName12345").build(), t -> {
            this.holder[0] = t;
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Tournament t = (Tournament) this.holder[0];

        assertEquals("UpdatedName12345", t.getName());
        assertEquals(this.tournaments.get(0), t);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateTournamentNoData() throws DataAccessException {
        Tournament local = this.challonge.updateTournament(this.tournaments.get(0), TournamentQuery.builder().build());
        assertEquals(this.tournaments.get(0), local);
    }

    @Test
    public void testDeleteTournament() throws DataAccessException {
        Tournament toDelete = this.tournaments.get(2);
        Tournament deleted = this.challonge.deleteTournament(toDelete);

        assertFalse(this.tournaments.contains(toDelete));
        assertEquals(toDelete, deleted);
    }

    @Test
    public void testDeleteTournamentAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament toDelete = this.tournaments.get(1);

        this.challonge.deleteTournament(toDelete, t -> {
            this.holder[0] = t;
            latch.countDown();
        }, e -> {
        });

        Tournament t = (Tournament) this.holder[0];

        latch.await(2000, TimeUnit.MILLISECONDS);

        assertFalse(this.tournaments.contains(toDelete));
        assertEquals(toDelete, t);
    }

    @Test
    public void testProcessCheckIns() throws DataAccessException {
        Tournament toUpdate = getTournament("tourney123");
        Tournament result = this.challonge.processCheckIns(toUpdate, true, true);

        assertEquals(toUpdate, result);
        assertFalse(result.getParticipants().isEmpty());
        assertFalse(result.getMatches().isEmpty());
    }

    @Test
    public void testProcessCheckInsAsync() throws InterruptedException, DataAccessException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament toUpdate = getTournament("tourney123");

        this.challonge.processCheckIns(toUpdate, t -> {
            this.holder[0] = t;
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        assertEquals(this.tournaments.get(0), this.holder[0]);
    }

    @Test
    public void testAbortCheckIn() throws DataAccessException {
        Tournament toUpdate = getTournament("tourney123");
        Tournament result = this.challonge.abortCheckIn(toUpdate, true, true);

        assertEquals(toUpdate, result);
        assertFalse(result.getParticipants().isEmpty());
        assertFalse(result.getMatches().isEmpty());
    }

    @Test
    public void testAbortCheckInAsync() throws InterruptedException, DataAccessException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament toUpdate = getTournament("tourney123");

        this.challonge.abortCheckIn(toUpdate, t -> {
            this.holder[0] = t;
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        assertEquals(toUpdate, this.holder[0]);
    }

    @Test
    public void testStartTournament() throws DataAccessException {
        Tournament toUpdate = getTournament("tourney123");
        Tournament result = this.challonge.startTournament(toUpdate, true, true);

        assertEquals(toUpdate, result);
        assertFalse(result.getParticipants().isEmpty());
        assertFalse(result.getMatches().isEmpty());
    }

    @Test
    public void testStartTournamentAsync() throws InterruptedException, DataAccessException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament toUpdate = getTournament("tourney123");

        this.challonge.startTournament(toUpdate, t -> {
            this.holder[0] = t;
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        assertEquals(toUpdate, this.holder[0]);
    }

    @Test
    public void testFinalizeTournament() throws DataAccessException {
        Tournament toUpdate = getTournament("tourney123");
        Tournament result = this.challonge.finalizeTournament(toUpdate, true, true);

        assertEquals(toUpdate, result);
        assertFalse(result.getParticipants().isEmpty());
        assertFalse(result.getMatches().isEmpty());
    }

    @Test
    public void testFinalizeTournamentAsync() throws InterruptedException, DataAccessException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament toUpdate = getTournament("tourney123");

        this.challonge.finalizeTournament(toUpdate, t -> {
            this.holder[0] = t;
            latch.countDown();
        }, e -> {
        });

        Tournament t = (Tournament) this.holder[0];

        latch.await(2000, TimeUnit.MILLISECONDS);

        assertEquals(toUpdate, t);
    }

    @Test
    public void testResetTournament() throws DataAccessException {
        Tournament toUpdate = getTournament("tourney123");
        Tournament result = this.challonge.resetTournament(toUpdate, true, true);

        assertEquals(toUpdate, result);
        assertFalse(result.getParticipants().isEmpty());
        assertFalse(result.getMatches().isEmpty());
    }

    @Test
    public void testResetTournamentAsync() throws InterruptedException, DataAccessException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament toUpdate = getTournament("tourney123");

        this.challonge.resetTournament(toUpdate, t -> {
            this.holder[0] = t;
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Tournament t = (Tournament) this.holder[0];

        assertEquals(toUpdate, t);
    }

    @Test
    public void testOpenTournamentForPredictions() throws DataAccessException {
        Tournament toUpdate = getTournament("tourney123");
        Tournament result = this.challonge.openTournamentForPredictions(toUpdate, true, true);

        assertEquals(toUpdate, result);
        assertFalse(result.getParticipants().isEmpty());
        assertFalse(result.getMatches().isEmpty());
    }

    @Test
    public void testOpenTournamentForPredictionsAsync() throws InterruptedException, DataAccessException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament toUpdate = getTournament("tourney123");

        this.challonge.openTournamentForPredictions(toUpdate, t -> {
            this.holder[0] = t;
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Tournament t = (Tournament) this.holder[0];

        assertEquals(toUpdate, t);
    }
}
