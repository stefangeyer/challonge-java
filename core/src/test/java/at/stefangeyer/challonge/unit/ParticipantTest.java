package at.stefangeyer.challonge.unit;

import at.stefangeyer.challonge.Challonge;
import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Credentials;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.enumeration.TournamentType;
import at.stefangeyer.challonge.model.query.ParticipantQuery;
import at.stefangeyer.challonge.model.query.wrapper.ParticipantQueryListWrapper;
import at.stefangeyer.challonge.model.query.wrapper.ParticipantQueryWrapper;
import at.stefangeyer.challonge.model.wrapper.ParticipantWrapper;
import at.stefangeyer.challonge.rest.ParticipantRestClient;
import at.stefangeyer.challonge.rest.RestClient;
import at.stefangeyer.challonge.serializer.Serializer;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static at.stefangeyer.challonge.unit.util.Util.ifNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ParticipantTest {

    private Challonge challonge;

    private Random random = new Random();

    private Object[] holder = new Object[1];

    private Tournament tournament = Tournament.builder()
            .id(10L).url("tourney123").tournamentType(TournamentType.SINGLE_ELIMINATION)
            .matches(new ArrayList<>(Arrays.asList(
                    Match.builder().id(1L).tournamentId(10L).player1Id(1L).player2Id(2L).build(),
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

    public ParticipantTest() throws DataAccessException {
        ParticipantRestClient prc = mock(ParticipantRestClient.class);

        when(prc.getParticipants(any())).thenAnswer(
                i -> this.tournament.getParticipants().stream().map(ParticipantWrapper::new).collect(Collectors.toList()));

        doAnswer(i -> {
            Callback<List<ParticipantWrapper>> onSuccess = i.getArgument(1);

            List<ParticipantWrapper> participants = prc.getParticipants(i.getArgument(0));

            onSuccess.accept(participants);

            return null;
        }).when(prc).getParticipants(any(), any(), any());

        when(prc.getParticipant(any(), anyLong(), anyBoolean())).thenAnswer(i -> {
            Participant p = getParticipant(i.getArgument(0), i.getArgument(1));
            List<Match> matches = new ArrayList<>();

            if (i.getArgument(2)) {
                matches = this.tournament.getMatches().stream()
                        .filter(m -> m.getPlayer1Id().equals(p.getId()) || m.getPlayer2Id().equals(p.getId())).collect(Collectors.toList());
            }
            p.setMatches(matches);

            return new ParticipantWrapper(p);
        });

        doAnswer(i -> {
            Callback<ParticipantWrapper> onSuccess = i.getArgument(3);

            ParticipantWrapper participant = prc.getParticipant(i.getArgument(0), i.getArgument(1), i.getArgument(2));

            onSuccess.accept(participant);

            return null;
        }).when(prc).getParticipant(any(), anyLong(), anyBoolean(), any(), any());

        when(prc.addParticipant(any(), any())).thenAnswer(i -> {
            ParticipantQuery data = i.<ParticipantQueryWrapper>getArgument(1).getParticipant();
            Participant p = Participant.builder().id((long) this.random.nextInt(1000)).name(data.getName())
                    .inviteEmail(data.getEmail()).challongeUsername(data.getChallongeUsername()).seed(data.getSeed())
                    .misc(data.getMisc()).displayNameWithInvitationEmailAddress(data.getInviteNameOrEmail())
                    .matches(new ArrayList<>()).build();

            this.tournament.getParticipants().add(p);

            return new ParticipantWrapper(p);
        });

        doAnswer(i -> {
            Callback<ParticipantWrapper> onSuccess = i.getArgument(2);

            ParticipantWrapper participant = prc.addParticipant(i.getArgument(0), i.getArgument(1));

            onSuccess.accept(participant);

            return null;
        }).when(prc).addParticipant(any(), any(), any(), any());

        when(prc.bulkAddParticipants(any(), any())).thenAnswer(i -> {
            List<ParticipantWrapper> result = new ArrayList<>();

            for (ParticipantQuery data : i.<ParticipantQueryListWrapper>getArgument(1).getParticipants()) {
                Participant p = Participant.builder().id((long) this.random.nextInt(1000)).name(data.getName())
                        .inviteEmail(data.getEmail()).challongeUsername(data.getChallongeUsername()).seed(data.getSeed())
                        .misc(data.getMisc()).displayNameWithInvitationEmailAddress(data.getInviteNameOrEmail())
                        .matches(new ArrayList<>()).build();

                tournament.getParticipants().add(p);
                result.add(new ParticipantWrapper(p));
            }

            return result;
        });

        doAnswer(i -> {
            Callback<List<ParticipantWrapper>> onSuccess = i.getArgument(2);

            List<ParticipantWrapper> participants = prc.bulkAddParticipants(i.getArgument(0), i.getArgument(1));

            onSuccess.accept(participants);

            return null;
        }).when(prc).bulkAddParticipants(any(), any(), any(), any());

        when(prc.updateParticipant(any(), anyLong(), any())).thenAnswer(i -> {
            ParticipantQuery data = i.<ParticipantQueryWrapper>getArgument(2).getParticipant();
            Participant curr = getParticipant(i.getArgument(0), i.getArgument(1));

            Participant updated = Participant.builder()
                    .name(ifNotNull(data.getName(), curr.getName()))
                    .inviteEmail(ifNotNull(data.getEmail(), curr.getInviteEmail()))
                    .challongeUsername(ifNotNull(data.getChallongeUsername(), curr.getChallongeUsername()))
                    .seed(ifNotNull(data.getSeed(), curr.getSeed()))
                    .misc(ifNotNull(data.getMisc(), curr.getMisc()))
                    .displayNameWithInvitationEmailAddress(ifNotNull(data.getInviteNameOrEmail(),
                            curr.getDisplayNameWithInvitationEmailAddress()))
                    .matches(curr.getMatches())
                    .build();

            this.tournament.getParticipants().remove(curr);
            this.tournament.getParticipants().add(updated);

            return new ParticipantWrapper(updated);
        });

        doAnswer(i -> {
            Callback<ParticipantWrapper> onSuccess = i.getArgument(3);

            ParticipantWrapper participant = prc.updateParticipant(i.getArgument(0), i.getArgument(1), i.getArgument(2));

            onSuccess.accept(participant);

            return null;
        }).when(prc).updateParticipant(any(), anyLong(), any(), any(), any());

        when(prc.checkInParticipant(any(), anyLong())).thenAnswer(i -> {
            Participant p = getParticipant(i.getArgument(0), i.getArgument(1));

            // emitted content update

            return new ParticipantWrapper(p);
        });

        doAnswer(i -> {
            Callback<ParticipantWrapper> onSuccess = i.getArgument(2);

            ParticipantWrapper participant = prc.checkInParticipant(i.getArgument(0), i.getArgument(1));

            onSuccess.accept(participant);

            return null;
        }).when(prc).checkInParticipant(any(), anyLong(), any(), any());

        when(prc.undoCheckInParticipant(any(), anyLong())).thenAnswer(i -> {
            Participant p = getParticipant(i.getArgument(0), i.getArgument(1));

            // emitted content update

            return new ParticipantWrapper(p);
        });

        doAnswer(i -> {
            Callback<ParticipantWrapper> onSuccess = i.getArgument(2);

            ParticipantWrapper participant = prc.undoCheckInParticipant(i.getArgument(0), i.getArgument(1));

            onSuccess.accept(participant);

            return null;
        }).when(prc).undoCheckInParticipant(any(), anyLong(), any(), any());

        when(prc.deleteParticipant(any(), anyLong())).thenAnswer(i -> {
            Participant p = getParticipant(i.getArgument(0), i.getArgument(1));

            this.tournament.getParticipants().remove(p);

            return new ParticipantWrapper(p);
        });

        doAnswer(i -> {
            Callback<ParticipantWrapper> onSuccess = i.getArgument(2);

            ParticipantWrapper participant = prc.deleteParticipant(i.getArgument(0), i.getArgument(1));

            onSuccess.accept(participant);

            return null;
        }).when(prc).deleteParticipant(any(), anyLong(), any(), any());

        when(prc.randomizeParticipants(any())).thenAnswer(i -> {
            List<Participant> participants = this.tournament.getParticipants();

            Collections.shuffle(participants);

            return participants.stream().map(ParticipantWrapper::new).collect(Collectors.toList());
        });

        doAnswer(i -> {
            Callback<List<ParticipantWrapper>> onSuccess = i.getArgument(1);

            List<ParticipantWrapper> participants = prc.randomizeParticipants(i.getArgument(0));

            onSuccess.accept(participants);

            return null;
        }).when(prc).randomizeParticipants(any(), any(), any());

        RestClient restClient = mock(RestClient.class);

        when(restClient.createParticipantRestClient()).thenReturn(prc);

        Serializer serializer = mock(Serializer.class);

        this.challonge = new Challonge(new Credentials("", ""), serializer, restClient);
    }

    private Participant getParticipant(String tournament, long id) throws DataAccessException {
        Optional<Participant> optional = this.tournament.getParticipants().stream().filter(p -> p.getId() == id).findFirst();

        if (!optional.isPresent()) throw new DataAccessException("participant not found");

        return optional.get();
    }

    @Test
    public void testGetParticipants() throws DataAccessException {
        List<Participant> local = this.challonge.getParticipants(this.tournament);
        assertEquals(this.tournament.getParticipants(), local);
    }

    @Test
    public void testGetParticipantsAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        List<Participant> participants = new ArrayList<>();

        this.challonge.getParticipants(this.tournament, l -> {
            participants.addAll(l);
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        assertEquals(this.tournament.getParticipants(), participants);
    }

    @Test
    public void testGetParticipant() throws DataAccessException {
        Participant local = this.challonge.getParticipant(this.tournament, 2);
        assertEquals("Participant 2", local.getName());
    }

    @Test
    public void testGetParticipantAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        this.challonge.getParticipant(this.tournament, 2, p -> {
            this.holder[0] = p;
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Participant local = (Participant) this.holder[0];

        assertEquals("Participant 2", local.getName());
    }

    @Test
    public void testAddParticipant() throws DataAccessException {
        Participant local = this.challonge.addParticipant(tournament, ParticipantQuery.builder().name("New Participants").build());
        assertTrue(tournament.getParticipants().contains(local));
    }

    @Test
    public void testAddParticipantAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        ParticipantQuery query = ParticipantQuery.builder().name("New Participants").build();

        this.challonge.addParticipant(this.tournament, query, p -> {
            this.holder[0] = p;
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Participant local = (Participant) this.holder[0];

        assertTrue(this.tournament.getParticipants().contains(local));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddParticipantNoData() throws DataAccessException {
        this.challonge.addParticipant(tournament, ParticipantQuery.builder().build());
    }

    @Test
    public void testBulkAddParticipant() throws DataAccessException {
        List<ParticipantQuery> participants = Arrays.asList(ParticipantQuery.builder().name("Another Participant 1").build(),
                ParticipantQuery.builder().name("Another Participant 2").build());
        List<Participant> local = this.challonge.bulkAddParticipants(tournament, participants);
        assertTrue(tournament.getParticipants().containsAll(local));
    }

    @Test
    public void testBulkAddParticipantAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        List<ParticipantQuery> queries = Arrays.asList(ParticipantQuery.builder().name("Another Participant 1").build(),
                ParticipantQuery.builder().name("Another Participant 2").build());

        this.challonge.bulkAddParticipants(this.tournament, queries, l -> {
            this.holder[0] = l;
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        List p = (List) this.holder[0];

        assertTrue(tournament.getParticipants().containsAll(p));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBulkAddParticipantNoData() throws DataAccessException {
        this.challonge.bulkAddParticipants(tournament, Collections.singletonList(ParticipantQuery.builder().build()));
    }

    @Test
    public void testUpdateParticipant() throws DataAccessException {
        Participant participant = this.tournament.getParticipants().stream().findFirst().get();
        Participant local = this.challonge.updateParticipant(participant, ParticipantQuery.builder().name("New Name").build());
        assertEquals("New Name", local.getName());
    }

    @Test
    public void testUpdateParticipantAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Participant participant = this.tournament.getParticipants().stream().findFirst().get();
        ParticipantQuery query = ParticipantQuery.builder().name("New Name").build();

        this.challonge.updateParticipant(participant, query, p -> {
            this.holder[0] = p;
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Participant local = (Participant) this.holder[0];

        assertEquals("New Name", local.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateParticipantNoData() throws DataAccessException {
        Participant participant = this.tournament.getParticipants().stream().findFirst().get();
        this.challonge.updateParticipant(participant, ParticipantQuery.builder().build());
    }

    @Test
    public void testCheckInParticipant() throws DataAccessException {
        Participant participant = this.tournament.getParticipants().stream().findFirst().get();
        Participant local = this.challonge.checkInParticipant(participant);
        assertEquals(participant, local);
    }

    @Test
    public void testCheckInParticipantAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Participant participant = this.tournament.getParticipants().stream().findFirst().get();

        this.challonge.checkInParticipant(participant, p -> {
            this.holder[0] = p;
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Participant local = (Participant) this.holder[0];

        assertEquals(participant, local);
    }

    @Test
    public void testUndoCheckInParticipant() throws DataAccessException {
        Participant participant = this.tournament.getParticipants().stream().findFirst().get();
        Participant local = this.challonge.undoCheckInParticipant(participant);
        assertEquals(participant, local);
    }

    @Test
    public void testUndoCheckInParticipantAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Participant participant = this.tournament.getParticipants().stream().findFirst().get();

        this.challonge.undoCheckInParticipant(participant, p -> {
            this.holder[0] = p;
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Participant local = (Participant) this.holder[0];

        assertEquals(participant, local);
    }

    @Test
    public void testDeleteParticipant() throws DataAccessException {
        Participant participant = this.tournament.getParticipants().stream().findFirst().get();
        Participant local = this.challonge.deleteParticipant(participant);
        assertEquals(participant, local);
    }

    @Test
    public void testDeleteParticipantAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Participant participant = this.tournament.getParticipants().stream().findFirst().get();

        this.challonge.deleteParticipant(participant, p -> {
            this.holder[0] = p;
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Participant local = (Participant) this.holder[0];

        assertEquals(participant, local);
    }

    @Test
    public void testRandomizeParticipant() throws DataAccessException {
        List<Participant> local = this.challonge.randomizeParticipants(tournament);
        assertEquals(this.tournament.getParticipants(), local);
    }

    @Test
    public void testRandomizeParticipantAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        this.challonge.randomizeParticipants(this.tournament, l -> {
            this.holder[0] = l;
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        List local = (List) this.holder[0];

        assertEquals(this.tournament.getParticipants(), local);
    }
}
