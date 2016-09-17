package com.exsoloscript.challonge;

import com.exsoloscript.challonge.guice.AttachmentTestModule;
import com.exsoloscript.challonge.guice.ChallongeTestModule;
import com.exsoloscript.challonge.guice.GuiceJUnitRunner;
import com.exsoloscript.challonge.model.Attachment;
import com.exsoloscript.challonge.model.Match;
import com.exsoloscript.challonge.model.Participant;
import com.exsoloscript.challonge.model.Tournament;
import com.exsoloscript.challonge.model.exception.ChallongeException;
import com.exsoloscript.challonge.model.query.AttachmentQuery;
import com.exsoloscript.challonge.model.query.ParticipantQuery;
import com.exsoloscript.challonge.model.query.TournamentQuery;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({ChallongeTestModule.class, AttachmentTestModule.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SyncAttachmentTest {

    @Inject
    private ChallongeApi challongeApi;

    @Inject
    @Named("attachment")
    private File file;

    private Match match;

    @Before
    public void setUp() throws Exception {
        this.challongeApi.tournaments().createTournament(
                TournamentQuery.builder()
                        .setName("Test")
                        .setUrl("javatesttournament")
                        .setAcceptAttachments(true)
                        .build()
        ).sync();

        ParticipantQuery user1 = ParticipantQuery.builder().setName("User1").setSeed(1).build();
        ParticipantQuery user2 = ParticipantQuery.builder().setName("User2").setSeed(2).build();

        this.challongeApi.participants()
                .bulkAddParticipants("javatesttournament", Lists.newArrayList(user1, user2)).sync();

        Tournament started = this.challongeApi.tournaments().startTournament("javatesttournament", false, true).sync();

        this.match = started.matches().get(0);
    }

    @Test
    public void createAttachmentTest() throws Exception {
        AttachmentQuery query = AttachmentQuery.builder()
                .setDescription("TestDescription")
                .setUrl("http://www.example.com")
                .build();

        Attachment attachment = this.challongeApi.attachments()
                .createAttachment("javatesttournament", this.match.id(), query).sync();
        assertEquals(attachment.description(), "TestDescription");
        assertEquals(attachment.url(), "http://www.example.com");
    }

    @Test
    public void getAttachmentsTest() throws Exception {
        Attachment attachment1 = this.challongeApi.attachments().createAttachment(
                "javatesttournament",
                match.id(),
                AttachmentQuery.builder().setDescription("Attachment1").build()
        ).sync();

        Attachment attachment2 = this.challongeApi.attachments().createAttachment(
                "javatesttournament",
                match.id(),
                AttachmentQuery.builder().setDescription("Attachment2").build()
        ).sync();

        List<Attachment> attachments = this.challongeApi.attachments().getAttachments("javatesttournament", match.id()).sync();

        assertTrue(attachments.size() == 2);
        assertEquals(attachment1, attachments.stream().filter(a -> a.description().equals("Attachment1")).findFirst().get());
        assertEquals(attachment2, attachments.stream().filter(a -> a.description().equals("Attachment2")).findFirst().get());
    }

    @Test
    public void getAttachmentTest() throws Exception {
        Attachment createdAttachment = this.challongeApi.attachments().createAttachment(
                "javatesttournament",
                match.id(),
                AttachmentQuery.builder().setDescription("Attachment1").build()
        ).sync();

        Attachment readAttachment = this.challongeApi.attachments()
                .getAttachment("javatesttournament", match.id(), createdAttachment.id()).sync();

        assertEquals(createdAttachment, readAttachment);
    }

    @Test
    public void updateAttachmentTest() throws Exception {
        Attachment createdAttachment = this.challongeApi.attachments().createAttachment(
                "javatesttournament",
                match.id(),
                AttachmentQuery.builder().setDescription("Attachment1").build()
        ).sync();

        Attachment updatedAttachment = this.challongeApi.attachments().updateAttachment(
                "javatesttournament",
                match.id(),
                createdAttachment.id(),
                AttachmentQuery.builder().setDescription("update").build()
        ).sync();

        assertEquals("update", updatedAttachment.description());
    }

    @Test
    public void deleteAttachmentTest() throws Exception {
        Attachment attachment1 = this.challongeApi.attachments().createAttachment(
                "javatesttournament",
                match.id(),
                AttachmentQuery.builder().setDescription("Attachment1").build()
        ).sync();

        Attachment attachment2 = this.challongeApi.attachments().createAttachment(
                "javatesttournament",
                match.id(),
                AttachmentQuery.builder().setDescription("Attachment2").build()
        ).sync();

        List<Attachment> attachmentsBeforeDelete = this.challongeApi.attachments().getAttachments("javatesttournament", match.id()).sync();

        assertTrue(attachmentsBeforeDelete.size() == 2);

        Attachment deleted = this.challongeApi.attachments().deleteAttachment("javatesttournament", match.id(), attachment1.id()).sync();
        assertEquals(attachment1.id(), deleted.id());

        List<Attachment> attachmentsAfterDelete = this.challongeApi.attachments().getAttachments("javatesttournament", match.id()).sync();
        assertTrue(attachmentsAfterDelete.size() == 1);
    }

    @After
    public void tearDown() throws Exception {
        this.challongeApi.tournaments().deleteTournament("javatesttournament").sync();
    }
}
