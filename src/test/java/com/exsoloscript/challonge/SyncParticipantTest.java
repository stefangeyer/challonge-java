package com.exsoloscript.challonge;

import com.exsoloscript.challonge.guice.ChallongeTestModule;
import com.exsoloscript.challonge.guice.GuiceJUnitRunner;
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

import static org.junit.Assert.assertEquals;

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

        OffsetDateTime start = OffsetDateTime.now().plusMinutes(5);

        try {
            this.tournament = this.challongeApi.tournaments().getTournament("participantjavatournament", true, false).sync();
        } catch (ChallongeException e) {
            TournamentQuery query = TournamentQuery.builder()
                    .name("Participants")
                    .url("participantjavatournament")
                    .signupCap(10)
                    .checkInDuration(10)
                    .startAt(start)
                    .build();
            this.tournament = this.challongeApi.tournaments().createTournament(query).sync();
        }
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

    }

    @Test
    public void fCheckInParticipant() throws Throwable {
//        ParticipantQuery createQuery = ParticipantQuery.builder()
//                .name("EXSolo")
//                .build();
//
//        Participant createdParticipant = this.challongeApi.participants().addParticipant(this.tournament.url(), createQuery).sync();
//        Participant checkedInParticipant = this.challongeApi.participants().checkInParticipant(this.tournament.url(), createdParticipant.id()).sync();
//
//        assertTrue(checkedInParticipant.checkedIn());
//        assertNotNull(checkedInParticipant.checkedInAt());
    }

    @After
    public void tearDown() throws Throwable {
        this.challongeApi.tournaments().deleteTournament(tournament.url()).sync();
    }
}
