package com.exsoloscript.challonge;

import com.exsoloscript.challonge.guice.ChallongeTestModule;
import com.exsoloscript.challonge.guice.GuiceJUnitRunner;
import com.exsoloscript.challonge.model.Match;
import com.exsoloscript.challonge.model.Participant;
import com.exsoloscript.challonge.model.Tournament;
import com.exsoloscript.challonge.model.query.MatchQuery;
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
public class SyncMatchTest {

    private ChallongeApi challongeApi;
    private List<Participant> participants;
    private Tournament tournament;

    @Inject
    public void setChallongeApi(ChallongeApi challongeApi) {
        this.challongeApi = challongeApi;
    }

    @Before
    public void setUp() throws Exception {
        this.challongeApi.tournaments().createTournament(
                TournamentQuery.builder().name("Test").url("javatesttournament").build())
                .sync();

        this.participants = this.challongeApi.participants().bulkAddParticipants("javatesttournament",
                Lists.newArrayList(
                        ParticipantQuery.builder().name("User1").seed(1).build(),
                        ParticipantQuery.builder().name("User2").seed(2).build(),
                        ParticipantQuery.builder().name("User3").seed(3).build(),
                        ParticipantQuery.builder().name("User4").seed(4).build()
                )
        ).sync();

        this.tournament = this.challongeApi.tournaments().startTournament("javatesttournament", false, false).sync();
    }

    @Test
    public void aIndexMatches() throws Exception {
        List<Match> matches = this.challongeApi.matches().getMatches("javatesttournament").sync();
        assertTrue(matches.size() == 3);

        Match firstSeed = matches.get(0);
        Match secondSeed = matches.get(1);
        Match finalMatch = matches.get(2);

        assertEquals(this.participants.stream().filter(p -> p.name().equals("User1")).findFirst().get().id(), firstSeed.player1Id());
        assertEquals(this.participants.stream().filter(p -> p.name().equals("User4")).findFirst().get().id(), firstSeed.player2Id());
        assertEquals(this.participants.stream().filter(p -> p.name().equals("User2")).findFirst().get().id(), secondSeed.player1Id());
        assertEquals(this.participants.stream().filter(p -> p.name().equals("User3")).findFirst().get().id(), secondSeed.player2Id());
        assertEquals(null, finalMatch.player1Id());
        assertEquals(null, finalMatch.player2Id());
    }

    @Test
    public void bIndexMatchesForPlayer() throws Exception {
        Participant user1 = this.participants.stream().filter(p -> p.name().equals("User1")).findFirst().get();
        Participant user4 = this.participants.stream().filter(p -> p.name().equals("User4")).findFirst().get();

        List<Match> matches = this.challongeApi.matches().getMatches(
                this.tournament.url(),
                user1.id(),
                null).sync();

        assertTrue(matches.size() == 1);

        Match m = matches.get(0);

        assertEquals(user1.id(), m.player1Id());
        assertEquals(user4.id(), m.player2Id());
    }

    @Test
    public void cGetMatch() throws Exception {
        Participant user1 = this.participants.stream().filter(p -> p.name().equals("User1")).findFirst().get();

        Match match1 = this.challongeApi.matches().getMatches(
                this.tournament.url(),
                user1.id(),
                null).sync().get(0);

        Match match2 = this.challongeApi.matches().getMatch(this.tournament.url(), match1.id(), false).sync();

        assertEquals(match1, match2);
    }

    @Test
    public void dUpdateMatch() throws Exception {
        Participant user1 = this.participants.stream().filter(p -> p.name().equals("User1")).findFirst().get();

        Match initial = this.challongeApi.matches().getMatches(
                this.tournament.url(),
                user1.id(),
                null).sync().get(0);

        MatchQuery query = MatchQuery.builder().winnerId(user1.id().toString()).scoresCsv("1-0,1-0").build();

        Match updated = this.challongeApi.matches().updateMatch(this.tournament.url(), initial.id(), query).sync();

        assertEquals(user1.id(), updated.winnerId());
    }

    @After
    public void tearDown() throws Exception {
        this.challongeApi.tournaments().deleteTournament(this.tournament.url()).sync();
    }
}
