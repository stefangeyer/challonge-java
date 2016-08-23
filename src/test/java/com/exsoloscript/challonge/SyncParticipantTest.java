package com.exsoloscript.challonge;

import com.exsoloscript.challonge.guice.ChallongeTestModule;
import com.exsoloscript.challonge.guice.GuiceJUnitRunner;
import com.exsoloscript.challonge.model.Participant;
import com.exsoloscript.challonge.model.Tournament;
import com.exsoloscript.challonge.model.exception.ChallongeException;
import com.exsoloscript.challonge.model.query.ParticipantQuery;
import com.exsoloscript.challonge.model.query.TournamentQuery;
import com.google.inject.Inject;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.io.IOException;

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
        TournamentQuery query = new TournamentQuery.Builder()
                .setName("Participants")
                .setUrl("participantjavatournament")
                .setSignupCap(10)
                .build();
        this.tournament = this.challongeApi.tournaments().createTournament(query).sync();
    }

    @Test
    public void aAddParticipants() throws Throwable {
        ParticipantQuery query = new ParticipantQuery.Builder()
                .setName("EXSolo")
                .setSeed(1)
                .setMisc("MiscTest")
                .build();
        Participant participant = this.challongeApi.participants().addParticipant(this.tournament.url(), query).sync();

        assertEquals("EXSolo", participant.name());
    }

    @After
    public void tearDown() throws Exception {
        this.challongeApi.tournaments().deleteTournament(tournament.url());

    }
}
