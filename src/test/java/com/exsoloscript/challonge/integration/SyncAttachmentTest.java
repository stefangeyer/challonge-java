/*
 * Copyright 2017 Stefan Geyer <stefangeyer at outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.exsoloscript.challonge.integration;

import com.exsoloscript.challonge.ChallongeApi;
import com.exsoloscript.challonge.integration.guice.AttachmentTestModule;
import com.exsoloscript.challonge.integration.guice.ChallongeTestModule;
import com.exsoloscript.challonge.integration.guice.GuiceJUnitRunner;
import com.exsoloscript.challonge.model.Attachment;
import com.exsoloscript.challonge.model.Match;
import com.exsoloscript.challonge.model.Tournament;
import com.exsoloscript.challonge.model.query.AttachmentQuery;
import com.exsoloscript.challonge.model.query.ParticipantQuery;
import com.exsoloscript.challonge.model.query.TournamentQuery;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.CharStreams;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
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

    private Tournament tournament;
    private Match match;

    @Before
    public void setUp() throws Exception {
        this.challongeApi.tournaments().createTournament(
                TournamentQuery.builder()
                        .name("Test")
                        .url("javatesttournament")
                        .acceptAttachments(true)
                        .build()
        ).sync();

        ParticipantQuery user1 = ParticipantQuery.builder().name("User1").seed(1).build();
        ParticipantQuery user2 = ParticipantQuery.builder().name("User2").seed(2).build();

        this.challongeApi.participants()
                .bulkAddParticipants("javatesttournament", Lists.newArrayList(user1, user2)).sync();

        this.tournament = this.challongeApi.tournaments().startTournament("javatesttournament", false, true).sync();

        this.match = this.tournament.matches().get(0);
    }

    @Test
    public void createAttachmentTest() throws Exception {
        AttachmentQuery query = AttachmentQuery.builder()
                .description("TestDescription")
                .url("http://www.example.com")
                .build();

        Attachment attachment = this.challongeApi.attachments()
                .createAttachment(this.tournament.url(), this.match.id(), query).sync();
        assertEquals(attachment.description(), "TestDescription");
        assertEquals(attachment.url(), "http://www.example.com");
    }

    @Test
    public void createFileAttachmentTest() throws Exception {
        File assetFile = new File(Thread.currentThread().getContextClassLoader().getResource("testfile1.txt").getPath());
        AttachmentQuery query = AttachmentQuery.builder()
                .asset(assetFile)
                .description("TestDescription")
                .build();

        Attachment attachment = this.challongeApi.attachments()
                .createAttachment(this.tournament.url(), this.match.id(), query).sync();

        assertEquals(attachment.description(), "TestDescription");
        assertEquals(attachment.assetFileName(), "testfile1.txt");

        URL assetUrl = new URL(attachment.assetUrl());

        assertEquals(
                CharStreams.toString(new InputStreamReader(new FileInputStream(assetFile), Charsets.UTF_8)),
                CharStreams.toString(new InputStreamReader(assetUrl.openStream(), Charsets.UTF_8))
        );
    }

    @Test
    public void getAttachmentsTest() throws Exception {
        Attachment attachment1 = this.challongeApi.attachments().createAttachment(
                this.tournament.url(),
                match.id(),
                AttachmentQuery.builder().description("Attachment1").build()
        ).sync();

        Attachment attachment2 = this.challongeApi.attachments().createAttachment(
                this.tournament.url(),
                match.id(),
                AttachmentQuery.builder().description("Attachment2").build()
        ).sync();

        List<Attachment> attachments = this.challongeApi.attachments().getAttachments(this.tournament.url(), match.id()).sync();

        assertTrue(attachments.size() == 2);
        assertEquals(attachment1, attachments.stream().filter(a -> a.description().equals("Attachment1")).findFirst().get());
        assertEquals(attachment2, attachments.stream().filter(a -> a.description().equals("Attachment2")).findFirst().get());
    }

    @Test
    public void getAttachmentTest() throws Exception {
        Attachment createdAttachment = this.challongeApi.attachments().createAttachment(
                this.tournament.url(),
                match.id(),
                AttachmentQuery.builder().description("Attachment1").build()
        ).sync();

        Attachment readAttachment = this.challongeApi.attachments()
                .getAttachment(this.tournament.url(), match.id(), createdAttachment.id()).sync();

        assertEquals(createdAttachment, readAttachment);
    }

    @Test
    public void updateAttachmentTest() throws Exception {
        File assetFile = new File(Thread.currentThread().getContextClassLoader().getResource("testfile1.txt").getPath());

        Attachment createdAttachment = this.challongeApi.attachments().createAttachment(
                this.tournament.url(),
                match.id(),
                AttachmentQuery.builder().asset(assetFile).description("Attachment1").build()
        ).sync();

        assertEquals("testfile1.txt", createdAttachment.assetFileName());

        File newAssetFile = new File(Thread.currentThread().getContextClassLoader().getResource("testfile2.txt").getPath());

        Attachment updatedAttachment = this.challongeApi.attachments().updateAttachment(
                this.tournament.url(),
                match.id(),
                createdAttachment.id(),
                AttachmentQuery.builder().asset(newAssetFile).description("update").build()
        ).sync();

        assertEquals("update", updatedAttachment.description());
        assertEquals("testfile2.txt", updatedAttachment.assetFileName());
    }

    @Test
    public void deleteAttachmentTest() throws Exception {
        Attachment attachment1 = this.challongeApi.attachments().createAttachment(
                this.tournament.url(),
                match.id(),
                AttachmentQuery.builder().description("Attachment1").build()
        ).sync();

        Attachment attachment2 = this.challongeApi.attachments().createAttachment(
                this.tournament.url(),
                match.id(),
                AttachmentQuery.builder().description("Attachment2").build()
        ).sync();

        List<Attachment> attachmentsBeforeDelete = this.challongeApi.attachments().getAttachments(this.tournament.url(), match.id()).sync();

        assertTrue(attachmentsBeforeDelete.size() == 2);

        Attachment deleted = this.challongeApi.attachments().deleteAttachment(this.tournament.url(), match.id(), attachment1.id()).sync();
        assertEquals(attachment1.id(), deleted.id());

        List<Attachment> attachmentsAfterDelete = this.challongeApi.attachments().getAttachments(this.tournament.url(), match.id()).sync();
        assertTrue(attachmentsAfterDelete.size() == 1);
    }

    @After
    public void tearDown() throws Exception {
        this.challongeApi.tournaments().deleteTournament(this.tournament.url()).sync();
    }
}
