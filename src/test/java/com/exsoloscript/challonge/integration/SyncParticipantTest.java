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
import com.exsoloscript.challonge.integration.guice.ChallongeTestModule;
import com.exsoloscript.challonge.integration.guice.GuiceJUnitRunner;
import com.exsoloscript.challonge.model.Participant;
import com.exsoloscript.challonge.model.Tournament;
import com.exsoloscript.challonge.model.exception.ChallongeException;
import com.exsoloscript.challonge.model.query.ParticipantQuery;
import com.exsoloscript.challonge.model.query.TournamentQuery;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({ChallongeTestModule.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SyncParticipantTest {

    private ChallongeApi challongeApi;
    private Tournament tournament;

    @Inject
    public void setChallongeApi(ChallongeApi challongeApi) {
        this.challongeApi = challongeApi;
    }

    @Before
    public void setUp() throws Throwable {

        OffsetDateTime start = OffsetDateTime.now().plusMinutes(75);

        TournamentQuery query = TournamentQuery.builder()
                .name("Participants")
                .url("javatesttournament")
                .checkInDuration(120)
                .startAt(start)
                .build();
        this.tournament = this.challongeApi.tournaments().createTournament(query).sync();
    }

    @Test
    public void aAddParticipants() throws Throwable {
        ParticipantQuery query = ParticipantQuery.builder()
                .name("EXSolo")
                .seed(1)
                .misc("MiscTest")
                .build();
        Participant participant = this.challongeApi.participants().addParticipant(this.tournament.url(), query).sync();

        assertEquals("EXSolo", participant.name());
        assertEquals(Integer.valueOf(1), participant.seed());
        assertEquals("MiscTest", participant.misc());
    }

    @Test
    public void bBulkAddParticipants() throws Throwable {
        ParticipantQuery query1 = ParticipantQuery.builder()
                .name("Bulk1")
                .seed(1)
                .build();

        ParticipantQuery query2 = ParticipantQuery.builder()
                .name("EXSolo")
                .inviteNameOrEmail("EXSolo")
                .seed(2)
                .misc("BulkAdd")
                .build();

        List<ParticipantQuery> queries = Lists.newArrayList(query1, query2);
        List<Participant> participants = this.challongeApi.participants().bulkAddParticipants(this.tournament.url(), queries).sync();

        Participant participant1 = participants.get(0);
        Participant participant2 = participants.get(1);

        assertEquals("Bulk1", participant1.name());
        assertEquals(Integer.valueOf(1), participant1.seed());

        assertEquals("EXSolo", participant2.name());
        assertEquals("EXSolo", participant2.challongeUsername());
        assertEquals("BulkAdd", participant2.misc());
        assertEquals(Integer.valueOf(2), participant2.seed());
    }

    @Test
    public void cUpdateParticipant() throws Throwable {
        ParticipantQuery createQuery = ParticipantQuery.builder()
                .name("EXSolo")
                .misc("123")
                .build();

        Participant createdParticipant = this.challongeApi.participants().addParticipant(this.tournament.url(), createQuery).sync();

        assertEquals("123", createdParticipant.misc());
        assertEquals("EXSolo", createdParticipant.name());

        ParticipantQuery updateQuery = ParticipantQuery.builder()
                .name("EXSolo1")
                .misc("321")
                .build();

        Participant updatedParticipant = this.challongeApi.participants().updateParticipant(this.tournament.url(), createdParticipant.id(), updateQuery).sync();

        assertEquals("EXSolo1", updatedParticipant.name());
        assertEquals("321", updatedParticipant.misc());
    }

    @Test(expected = ChallongeException.class)
    public void dDeleteParticipant() throws Throwable {
        ParticipantQuery createQuery = ParticipantQuery.builder()
                .name("EXSolo")
                .build();

        Participant createdParticipant = this.challongeApi.participants().addParticipant(this.tournament.url(), createQuery).sync();
        Participant deletedParticipant = this.challongeApi.participants().deleteParticipant(this.tournament.url(), createdParticipant.id()).sync();

        this.challongeApi.participants().getParticipant(this.tournament.url(), deletedParticipant.id(), false).sync();

        assertEquals("EXSolo", deletedParticipant.name());
    }

    @Test
    public void eRandomizeParticipants() throws Exception {
        ParticipantQuery query1 = ParticipantQuery.builder().name("User1").build();
        ParticipantQuery query2 = ParticipantQuery.builder().name("User2").build();
        ParticipantQuery query3 = ParticipantQuery.builder().name("User3").build();

        this.challongeApi.participants().bulkAddParticipants(this.tournament.url(), Lists.newArrayList(query1, query2, query3)).sync();

        List<Participant> randomizedParticipants = this.challongeApi.participants().randomizeParticipants(this.tournament.url()).sync();

        assertTrue(randomizedParticipants.size() == 3);

        Optional<Participant> optUser1 = randomizedParticipants.stream().filter(p -> p.name().equals("User1")).findFirst();
        Optional<Participant> optUser2 = randomizedParticipants.stream().filter(p -> p.name().equals("User2")).findFirst();
        Optional<Participant> optUser3 = randomizedParticipants.stream().filter(p -> p.name().equals("User3")).findFirst();

        assertTrue(optUser1.isPresent());
        assertTrue(optUser2.isPresent());
        assertTrue(optUser3.isPresent());
    }

    @Test
    public void fCheckInParticipant() throws Throwable {
        ParticipantQuery createQuery = ParticipantQuery.builder()
                .name("EXSolo")
                .build();

        Participant createdParticipant = this.challongeApi.participants().addParticipant(this.tournament.url(), createQuery).sync();
        Participant checkedInParticipant = this.challongeApi.participants().checkInParticipant(this.tournament.url(), createdParticipant.id()).sync();

        assertTrue(checkedInParticipant.checkedIn());
        assertNotNull(checkedInParticipant.checkedInAt());
    }

    @Test
    public void gUndoCheckInParticipant() throws Throwable {
        ParticipantQuery createQuery = ParticipantQuery.builder()
                .name("EXSolo")
                .build();

        Participant createdParticipant = this.challongeApi.participants().addParticipant(this.tournament.url(), createQuery).sync();
        Participant checkedInParticipant = this.challongeApi.participants().checkInParticipant(this.tournament.url(), createdParticipant.id()).sync();

        assertTrue(checkedInParticipant.checkedIn());
        assertNotNull(checkedInParticipant.checkedInAt());

        Participant checkedOutParticipant = this.challongeApi.participants().undoParticipantCheckIn(this.tournament.url(), checkedInParticipant.id()).sync();

        assertFalse(checkedOutParticipant.checkedIn());
        assertNull(checkedOutParticipant.checkedInAt());
    }

    @Test
    public void hGetParticipants() throws Exception {
        ParticipantQuery query1 = ParticipantQuery.builder().name("User1").build();
        ParticipantQuery query2 = ParticipantQuery.builder().name("User2").build();
        ParticipantQuery query3 = ParticipantQuery.builder().name("User3").build();

        this.challongeApi.participants().bulkAddParticipants(this.tournament.url(), Lists.newArrayList(query1, query2, query3)).sync();

        List<Participant> participants = this.challongeApi.participants().getParticipants(this.tournament.url()).sync();

        Optional<Participant> optUser1 = participants.stream().filter(p -> p.name().equals("User1")).findFirst();
        Optional<Participant> optUser2 = participants.stream().filter(p -> p.name().equals("User2")).findFirst();
        Optional<Participant> optUser3 = participants.stream().filter(p -> p.name().equals("User3")).findFirst();

        assertTrue(optUser1.isPresent());
        assertTrue(optUser2.isPresent());
        assertTrue(optUser3.isPresent());
    }

    @After
    public void tearDown() throws Throwable {
        this.challongeApi.tournaments().deleteTournament(tournament.url()).sync();
    }
}
