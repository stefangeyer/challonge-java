package at.stefangeyer.challonge.rest.retrofit;

import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.query.AttachmentQuery;
import at.stefangeyer.challonge.model.wrapper.AttachmentWrapper;
import at.stefangeyer.challonge.rest.retrofit.util.MockChallongeRetrofitFactory;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class RetrofitAttachmentTest {
    private static final String TOURNEY_NAME = "tourney123";
    private static final int MATCH_ID = 1;
    private static final int ATTACHMENT1_ID = 1;
    private static final int ATTACHMENT2_ID = 2;
    private static final int ATTACHMENT3_ID = 3;

    private Object[] holder = new Object[1];

    private MockChallongeRetrofit challongeRetrofit = MockChallongeRetrofitFactory.create();

    private RetrofitRestClient attachmentRestClient = new RetrofitRestClient(challongeRetrofit, false);

    @Test
    public void testGetAttachments() throws DataAccessException {
        Tournament tournament = this.challongeRetrofit.get(TOURNEY_NAME);
        Match match = tournament.getMatches().get(0);
        List<Attachment> local = this.attachmentRestClient.getAttachments(TOURNEY_NAME, MATCH_ID).stream()
                .map(AttachmentWrapper::getMatchAttachment).collect(Collectors.toList());
        assertEquals(match.getAttachments(), local);
    }

    @Test
    public void testGetAttachmentsAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament tournament = this.challongeRetrofit.get(TOURNEY_NAME);
        Match match = tournament.getMatches().get(0);

        this.attachmentRestClient.getAttachments(TOURNEY_NAME, MATCH_ID, l -> {
            this.holder[0] = l.stream().map(AttachmentWrapper::getMatchAttachment).collect(Collectors.toList());
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        assertEquals(match.getAttachments(), this.holder[0]);
    }

    @Test
    public void testGetAttachment() throws DataAccessException {
        Attachment local = this.attachmentRestClient.getAttachment(TOURNEY_NAME, MATCH_ID, ATTACHMENT1_ID).getMatchAttachment();
        assertEquals("Attachment note", local.getDescription());
    }

    @Test
    public void testGetAttachmentAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        this.attachmentRestClient.getAttachment(TOURNEY_NAME, MATCH_ID, ATTACHMENT1_ID, t -> {
            this.holder[0] = t.getMatchAttachment();
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Attachment local = (Attachment) this.holder[0];

        assertEquals("Attachment note", local.getDescription());
    }

    @Test
    public void testCreateAttachment() throws DataAccessException {
        Tournament tournament = this.challongeRetrofit.get(TOURNEY_NAME);
        Match match = tournament.getMatches().get(0);

        assertNotNull(match.getAttachments());

        List<Attachment> attachments = match.getAttachments();
        Attachment local = this.attachmentRestClient.createAttachment(TOURNEY_NAME, MATCH_ID,
                AttachmentQuery.builder().description("Some new attachment").build()).getMatchAttachment();
        assertTrue(attachments.contains(local));
    }

    @Test
    public void testCreateAttachmentAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament tournament = this.challongeRetrofit.get(TOURNEY_NAME);
        Match match = tournament.getMatches().get(0);

        assertNotNull(match.getAttachments());

        List<Attachment> attachments = match.getAttachments();
        AttachmentQuery query = AttachmentQuery.builder().description("Some new attachment").build();

        this.attachmentRestClient.createAttachment(TOURNEY_NAME, MATCH_ID, query, t -> {
            this.holder[0] = t.getMatchAttachment();
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Attachment local = (Attachment) this.holder[0];
        assertTrue(attachments.contains(local));
    }

    @Test
    public void testUpdateAttachment() throws DataAccessException {
        Tournament tournament = this.challongeRetrofit.get(TOURNEY_NAME);
        Match match = tournament.getMatches().get(0);
        assertNotNull(match.getAttachments());
        List<Attachment> attachments = match.getAttachments();
        Attachment local = this.attachmentRestClient.updateAttachment(TOURNEY_NAME, MATCH_ID, attachments.get(0).getId(),
                AttachmentQuery.builder().url("https://www.google.com").build()).getMatchAttachment();
        assertEquals(attachments.get(0), local);
    }

    @Test
    public void testUpdateAttachmentAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament tournament = this.challongeRetrofit.get(TOURNEY_NAME);
        Match match = tournament.getMatches().get(0);

        assertNotNull(match.getAttachments());

        List<Attachment> attachments = match.getAttachments();
        AttachmentQuery query = AttachmentQuery.builder().url("https://www.google.com").build();

        this.attachmentRestClient.updateAttachment(TOURNEY_NAME, MATCH_ID, ATTACHMENT1_ID, query, t -> {
            this.holder[0] = t.getMatchAttachment();
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Attachment local = (Attachment) this.holder[0];

        assertEquals(attachments.get(0), local);
    }

    @Test
    public void testDeleteAttachment() throws DataAccessException {
        Tournament tournament = this.challongeRetrofit.get(TOURNEY_NAME);
        Match match = this.challongeRetrofit.get(tournament, MATCH_ID);
        assertNotNull(match.getAttachments());
        List<Attachment> attachments = match.getAttachments();
        Attachment local = this.attachmentRestClient.deleteAttachment(TOURNEY_NAME, MATCH_ID, ATTACHMENT2_ID).getMatchAttachment();
        assertFalse(attachments.contains(local));
    }

    @Test
    public void testDeleteAttachmentAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Tournament tournament = this.challongeRetrofit.get(TOURNEY_NAME);
        Match match = this.challongeRetrofit.get(tournament, MATCH_ID);

        this.attachmentRestClient.deleteAttachment(TOURNEY_NAME, MATCH_ID, ATTACHMENT3_ID, aw -> {
            this.holder[0] = aw.getMatchAttachment();
            latch.countDown();
        }, e -> {
        });

        latch.await(2000, TimeUnit.MILLISECONDS);

        Attachment attachment = (Attachment) this.holder[0];

        assertFalse(match.getAttachments().contains(attachment));
    }
}
