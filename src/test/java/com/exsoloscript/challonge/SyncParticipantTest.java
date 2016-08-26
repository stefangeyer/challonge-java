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

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        try {
            this.tournament = this.challongeApi.tournaments().getTournament("participantjavatournament", true, false).sync();
        } catch (ChallongeException e) {
            TournamentQuery query = TournamentQuery.builder()
                    .setName("Participants")
                    .setUrl("participantjavatournament")
                    .setSignupCap(10)
                    .build();
            this.tournament = this.challongeApi.tournaments().createTournament(query).sync();
        }
    }

    @Test
    public void aAddParticipants() throws Throwable {
        ParticipantQuery query = ParticipantQuery.builder()
                .setName("EXSolo")
                .setSeed(1)
                .setMisc("MiscTest")
                .build();
        Participant participant = this.challongeApi.participants().addParticipant(this.tournament.url(), query).sync();

        assertEquals("EXSolo", participant.name());
        assertEquals(Integer.valueOf(1), participant.seed());
        assertEquals("MiscTest", participant.misc());
    }

    @Test
    public void bBulkAddParticipants() throws Throwable {
        ParticipantQuery query1 = ParticipantQuery.builder()
                .setName("Bulk1")
                .setSeed(2)
                .build();

        ParticipantQuery query2 = ParticipantQuery.builder()
                .setName("Bulk2")
                .setSeed(1)
                .setMisc("BulkAdd")
                .build();

        List<Participant> participants = this.challongeApi.participants().bulkAddParticipants(this.tournament.url(), Lists.newArrayList(query1, query2)).sync();

        assertTrue(participants.size() == 2);
    }

    @After
    public void tearDown() throws Throwable {
        this.challongeApi.tournaments().deleteTournament(tournament.url()).sync();
    }
}
