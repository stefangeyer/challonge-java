package at.stefangeyer.challonge.unit;

import at.stefangeyer.challonge.Challonge;
import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.*;
import at.stefangeyer.challonge.model.enumeration.TournamentType;
import at.stefangeyer.challonge.model.query.AttachmentQuery;
import at.stefangeyer.challonge.model.wrapper.AttachmentWrapper;
import at.stefangeyer.challonge.rest.RestClient;
import at.stefangeyer.challonge.serializer.Serializer;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AttachmentTest {

    private Challonge challonge;

    private Random random = new Random();

    private Object[] holder = new Object[1];

    private Tournament tournament = Tournament.builder()
            .id(10L).url("tourney123").tournamentType(TournamentType.SINGLE_ELIMINATION)
            .matches(new ArrayList<>(Collections.singletonList(
                    Match.builder().id(1L).tournamentId(10L).player1Id(1L).player2Id(2L).attachments(new ArrayList<>(Arrays.asList(
                            Attachment.builder().id(1L).description("Attachment note").build(),
                            Attachment.builder().id(2L).description("Some description").build(),
                            Attachment.builder().id(3L).url("http://some.resource.com/resource").build()
                    ))).build()
            ))).participants(new ArrayList<>(Arrays.asList(
                    Participant.builder().id(1L).tournamentId(10L).name("Participant 1").matches(new ArrayList<>()).build(),
                    Participant.builder().id(2L).tournamentId(10L).name("Participant 2").matches(new ArrayList<>()).build()
            )))
            .build();

    public AttachmentTest() throws DataAccessException {
        RestClient arc = mock(RestClient.class);

        when(arc.getAttachments(any(), anyLong())).thenAnswer(i -> {
            Tournament t = getTournament(i.getArgument(0));
            Match m = getMatch(t, i.getArgument(1));

            return m.getAttachments().stream().map(AttachmentWrapper::new).collect(Collectors.toList());
        });

        doAnswer(i -> {
            Callback<List<AttachmentWrapper>> onSuccess = i.getArgument(2);

            List<AttachmentWrapper> attachments = arc.getAttachments(i.getArgument(0), i.getArgument(1));

            onSuccess.accept(attachments);

            return null;
        }).when(arc).getAttachments(any(), anyLong(), any(), any());

        when(arc.getAttachment(any(), anyLong(), anyLong())).thenAnswer(i -> {
            Tournament t = getTournament(i.getArgument(0));
            Match m = getMatch(t, i.getArgument(1));
            Attachment a = getAttachment(m, i.getArgument(2));

            return new AttachmentWrapper(a);
        });

        doAnswer(i -> {
            Callback<AttachmentWrapper> onSuccess = i.getArgument(3);

            AttachmentWrapper attachment = arc.getAttachment(i.getArgument(0), i.getArgument(1), i.getArgument(2));

            onSuccess.accept(attachment);

            return null;
        }).when(arc).getAttachment(any(), anyLong(), anyLong(), any(), any());

        when(arc.createAttachment(any(), anyLong(), any())).thenAnswer(i -> {
            Tournament t = getTournament(i.getArgument(0));
            Match m = getMatch(t, i.getArgument(1));

            AttachmentQuery data = i.getArgument(2);
            Attachment a = Attachment.builder()
                    .id((long) this.random.nextInt(1000))
                    .assetUrl(data.getUrl())
                    .description(data.getDescription()).build();

            m.getAttachments().add(a);

            return new AttachmentWrapper(a);
        });

        doAnswer(i -> {
            Callback<AttachmentWrapper> onSuccess = i.getArgument(3);

            AttachmentWrapper attachment = arc.createAttachment(i.getArgument(0), i.getArgument(1), i.getArgument(2));

            onSuccess.accept(attachment);

            return null;
        }).when(arc).createAttachment(any(), anyLong(), any(), any(), any());

        when(arc.updateAttachment(any(), anyLong(), anyLong(), any())).thenAnswer(i -> {
            Tournament t = getTournament(i.getArgument(0));
            Match m = getMatch(t, i.getArgument(1));
            Attachment a = getAttachment(m, i.getArgument(2));

            // emitted content update

            return new AttachmentWrapper(a);
        });

        doAnswer(i -> {
            Callback<AttachmentWrapper> onSuccess = i.getArgument(4);

            AttachmentWrapper attachment = arc.updateAttachment(i.getArgument(0), i.getArgument(1),
                    i.getArgument(2), i.getArgument(3));

            onSuccess.accept(attachment);

            return null;
        }).when(arc).updateAttachment(any(), anyLong(), anyLong(), any(), any(), any());

        when(arc.deleteAttachment(any(), anyLong(), anyLong())).thenAnswer(i -> {
            Tournament t = getTournament(i.getArgument(0));
            Match m = getMatch(t, i.getArgument(1));
            Attachment a = getAttachment(m, i.getArgument(2));

            m.getAttachments().remove(a);

            return new AttachmentWrapper(a);
        });

        doAnswer(i -> {
            Callback<AttachmentWrapper> onSuccess = i.getArgument(3);

            AttachmentWrapper attachment = arc.deleteAttachment(i.getArgument(0), i.getArgument(1), i.getArgument(2));

            onSuccess.accept(attachment);

            return null;
        }).when(arc).deleteAttachment(any(), anyLong(), anyLong(), any(), any());

        Serializer serializer = mock(Serializer.class);

        this.challonge = new Challonge(new Credentials("", ""), serializer, arc);
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

    private Attachment getAttachment(Match m, long id) throws DataAccessException {
        Optional<Attachment> optional = m.getAttachments().stream().filter(a -> a.getId().equals(id)).findFirst();

        if (!optional.isPresent()) {
            throw new DataAccessException("attachment not found");
        }

        return optional.get();
    }

    @Test
    public void testGetAttachments() throws DataAccessException {
        Tournament tournament = getTournament("tourney123");
        Match match = tournament.getMatches().get(0);
        List<Attachment> local = this.challonge.getAttachments(match);
        assertEquals(match.getAttachments(), local);
    }

    @Test
    public void testGetAttachmentsAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament tournament = getTournament("tourney123");
        Match match = tournament.getMatches().get(0);

        this.challonge.getAttachments(match, l -> {
            this.holder[0] = l;
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        assertEquals(match.getAttachments(), this.holder[0]);
    }

    @Test
    public void testGetAttachment() throws DataAccessException {
        Tournament tournament = getTournament("tourney123");
        Match match = tournament.getMatches().get(0);
        Attachment local = this.challonge.getAttachment(match, 1);
        assertEquals("Attachment note", local.getDescription());
    }

    @Test
    public void testGetAttachmentAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament tournament = getTournament("tourney123");
        Match match = tournament.getMatches().get(0);

        this.challonge.getAttachment(match, 1, t -> {
            this.holder[0] = t;
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Attachment local = (Attachment) this.holder[0];
        assertEquals("Attachment note", local.getDescription());
    }

    @Test
    public void testCreateAttachment() throws DataAccessException {
        Tournament tournament = getTournament("tourney123");
        Match match = tournament.getMatches().get(0);
        assertNotNull(match.getAttachments());

        List<Attachment> attachments = match.getAttachments();
        Attachment local = this.challonge.createAttachment(match,
                AttachmentQuery.builder().description("Some new attachment").build());
        assertTrue(attachments.contains(local));
    }

    @Test
    public void testCreateAttachmentAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament tournament = getTournament("tourney123");
        Match match = tournament.getMatches().get(0);

        assertNotNull(match.getAttachments());

        List<Attachment> attachments = match.getAttachments();
        AttachmentQuery query = AttachmentQuery.builder().description("Some new attachment").build();

        this.challonge.createAttachment(match, query, t -> {
            this.holder[0] = t;
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Attachment local = (Attachment) this.holder[0];
        assertTrue(attachments.contains(local));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateInvalidAttachment() throws DataAccessException {
        Tournament tournament = getTournament("tourney123");
        Match match = tournament.getMatches().get(0);

        this.challonge.createAttachment(match, AttachmentQuery.builder().build());
    }

    @Test
    public void testUpdateAttachment() throws DataAccessException {
        Tournament tournament = getTournament("tourney123");
        Match match = tournament.getMatches().get(0);
        assertNotNull(match.getAttachments());
        List<Attachment> attachments = match.getAttachments();
        Attachment local = this.challonge.updateAttachment(match, attachments.get(0),
                AttachmentQuery.builder().url("https://www.google.com").build());
        assertEquals(attachments.get(0), local);
    }

    @Test
    public void testUpdateAttachmentAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament tournament = getTournament("tourney123");
        Match match = tournament.getMatches().get(0);

        assertNotNull(match.getAttachments());

        List<Attachment> attachments = match.getAttachments();
        AttachmentQuery query = AttachmentQuery.builder().url("https://www.google.com").build();

        this.challonge.updateAttachment(match, attachments.get(0), query, t -> {
            this.holder[0] = t;
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Attachment local = (Attachment) this.holder[0];

        assertEquals(attachments.get(0), local);
    }

    @Test
    public void testDeleteAttachment() throws DataAccessException {
        Tournament tournament = getTournament("tourney123");
        Match match = tournament.getMatches().get(0);
        assertNotNull(match.getAttachments());
        List<Attachment> attachments = match.getAttachments();
        Attachment local = this.challonge.deleteAttachment(match, attachments.get(0));
        assertFalse(attachments.contains(local));
    }

    @Test
    public void testDeleteAttachmentAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament tournament = getTournament("tourney123");
        Match match = tournament.getMatches().get(0);

        assertNotNull(match.getAttachments());

        List<Attachment> attachments = match.getAttachments();

        this.challonge.deleteAttachment(match, attachments.get(0), t -> {
            this.holder[0] = t;
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Attachment local = (Attachment) this.holder[0];

        assertFalse(attachments.contains(local));
    }
}
