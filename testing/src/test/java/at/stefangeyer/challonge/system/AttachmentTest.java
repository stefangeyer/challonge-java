package at.stefangeyer.challonge.system;

import at.stefangeyer.challonge.Challonge;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Credentials;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.query.AttachmentQuery;
import at.stefangeyer.challonge.model.query.ParticipantQuery;
import at.stefangeyer.challonge.model.query.TournamentQuery;
import at.stefangeyer.challonge.rest.retrofit.RetrofitRestClient;
import at.stefangeyer.challonge.serializer.gson.GsonSerializer;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AttachmentTest {
    private Challonge challonge;

    private Tournament tournament;
    private Match match;

    private String tournamentUrl;

    public AttachmentTest() {
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
    public final void setUp() throws DataAccessException {
        try {
            // Delete the tournament, if it already exists
            this.challonge.deleteTournament(this.challonge.getTournament(this.tournamentUrl));
        } catch (DataAccessException ignored) {
        }

        Tournament tournament = this.challonge.createTournament(TournamentQuery.builder().name("Test")
                .url(this.tournamentUrl).acceptAttachments(true).build());

        ParticipantQuery user1 = ParticipantQuery.builder().name("User1").seed(1).build();
        ParticipantQuery user2 = ParticipantQuery.builder().name("User2").seed(2).build();

        this.challonge.bulkAddParticipants(tournament, Arrays.asList(user1, user2));

        this.tournament = this.challonge.startTournament(tournament, false, true);

        this.match = this.tournament.getMatches().get(0);
    }

    @Test
    public final void testCreateAttachment() throws DataAccessException {
        AttachmentQuery query = AttachmentQuery.builder().description("TestDescription").url("http://www.example.com").build();

        Attachment attachment = this.challonge.createAttachment(this.match, query);

        assertEquals("TestDescription", attachment.getDescription());
        assertEquals("http://www.example.com", attachment.getUrl());
    }

    @Test
    public final void testCreateFileAttachment() throws DataAccessException, IOException {
        File assetFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("testfile1.txt")).getPath());
        AttachmentQuery query = AttachmentQuery.builder().asset(assetFile).description("TestDescription").build();

        Attachment attachment = this.challonge.createAttachment(this.match, query);

        assertEquals("TestDescription", attachment.getDescription());
        assertEquals("testfile1.txt", attachment.getAssetFileName());

        URL assetUrl = new URL(attachment.getAssetUrl());

        assertTrue(IOUtils.contentEquals(new InputStreamReader(new FileInputStream(assetFile)),
                new InputStreamReader(assetUrl.openStream())));
    }

    @Test
    public final void testGetAttachments() throws DataAccessException {
        Attachment attachment1 = this.challonge.createAttachment(this.match,
                AttachmentQuery.builder().description("Attachment1").build());

        Attachment attachment2 = this.challonge.createAttachment(this.match,
                AttachmentQuery.builder().description("Attachment2").build());

        List<Attachment> attachments = this.challonge.getAttachments(this.match);

        assertEquals(2, attachments.size());


        assertEquals(attachment1, getAttachment(attachments, "Attachment1"));
        assertEquals(attachment2, getAttachment(attachments, "Attachment2"));
    }

    @Test
    public final void testGetAttachment() throws DataAccessException {
        Attachment createdAttachment = this.challonge.createAttachment(this.match,
                AttachmentQuery.builder().description("Attachment1").build());
        Attachment readAttachment = this.challonge.getAttachment(this.match, createdAttachment.getId());

        assertEquals(createdAttachment, readAttachment);
    }

    @Test
    public final void testUpdateAttachment() throws DataAccessException {
        File assetFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("testfile1.txt")).getPath());

        Attachment createdAttachment = this.challonge.createAttachment(this.match,
                AttachmentQuery.builder().asset(assetFile).description("Attachment1").build());

        assertEquals("testfile1.txt", createdAttachment.getAssetFileName());

        File newAssetFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("testfile2.txt")).getPath());

        Attachment updatedAttachment = this.challonge.updateAttachment(this.match, createdAttachment,
                AttachmentQuery.builder().asset(newAssetFile).description("update").build());

        assertEquals("update", updatedAttachment.getDescription());
        assertEquals("testfile2.txt", updatedAttachment.getAssetFileName());
    }

    @Test
    public final void testDeleteAttachment() throws DataAccessException {
        Attachment attachment1 = this.challonge.createAttachment(this.match,
                AttachmentQuery.builder().description("Attachment1").build());

        this.challonge.createAttachment(this.match, AttachmentQuery.builder().description("Attachment2").build());

        List<Attachment> attachmentsBeforeDelete = this.challonge.getAttachments(this.match);
        assertEquals(2, attachmentsBeforeDelete.size());

        Attachment deleted = this.challonge.deleteAttachment(this.match, attachment1);
        assertEquals(attachment1.getId(), deleted.getId());

        List<Attachment> attachmentsAfterDelete = this.challonge.getAttachments(this.match);
        assertEquals(1, attachmentsAfterDelete.size());
    }

    @After
    public void tearDown() {
        try {
            this.challonge.deleteTournament(this.tournament);
        } catch (DataAccessException ignored) {
        }
    }

    private Attachment getAttachment(List<Attachment> list, String desc) {
        return list.stream().filter(a -> a.getDescription().equals(desc)).findFirst().orElse(null);
    }
}
