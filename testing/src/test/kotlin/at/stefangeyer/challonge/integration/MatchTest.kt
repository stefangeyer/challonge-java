package at.stefangeyer.challonge.integration

import at.stefangeyer.challonge.exception.DataAccessException
import at.stefangeyer.challonge.model.Credentials
import at.stefangeyer.challonge.model.Participant
import at.stefangeyer.challonge.model.Tournament
import at.stefangeyer.challonge.model.query.MatchQuery
import at.stefangeyer.challonge.model.query.ParticipantQuery
import at.stefangeyer.challonge.model.query.TournamentQuery
import at.stefangeyer.challonge.rest.retrofit.RetrofitRestClient
import at.stefangeyer.challonge.serializer.gson.GsonSerializer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MatchTest {

    private var initial = true
    private lateinit var challonge: Challonge

    private lateinit var tournament: Tournament
    private lateinit var participants: List<Participant>


    companion object {
        private const val TOURNAMENT_URL = "javaapitest"
    }

    @Before
    fun setUp() {
        if (initial) {
            initial = false

            val username = System.getProperty("challongeUsername")
            val apiKey = System.getProperty("challongeApiKey")

            if (username == null || apiKey == null) {
                throw IllegalArgumentException(
                        "Required system properties challongeUsername and challongeApiKey are absent")
            }

            challonge = Challonge(Credentials(username, apiKey), GsonSerializer(), RetrofitRestClient())

            try {
                val t = this.challonge.getTournament(TOURNAMENT_URL)
                // Delete the tournament, if it already exists
                this.challonge.deleteTournament(t)
            } catch (ignored: DataAccessException) {
            }

            val query = TournamentQuery(name = "Matches", url = TOURNAMENT_URL)
            this.tournament = this.challonge.createTournament(query)

            this.participants = this.challonge.bulkAddParticipants(this.tournament,
                    listOf(ParticipantQuery(name = "User1", seed = 1), ParticipantQuery(name = "User2", seed = 2),
                            ParticipantQuery(name = "User3", seed = 3), ParticipantQuery(name = "User4", seed = 4)))

            this.tournament = this.challonge.startTournament(this.tournament, true, true)
        }
    }

    @Test
    fun aIndexMatches() {
        val matches = this.challonge.getMatches(this.tournament)
        assertTrue(matches.size == 3)

        val firstSeed = matches[0]
        val secondSeed = matches[1]
        val finalMatch = matches[2]

        assertEquals(this.participants.first { p -> p.name.equals("User1") }.id, firstSeed.player1Id)
        assertEquals(this.participants.first { p -> p.name.equals("User4") }.id, firstSeed.player2Id)
        assertEquals(this.participants.first { p -> p.name.equals("User2") }.id, secondSeed.player1Id)
        assertEquals(this.participants.first { p -> p.name.equals("User3") }.id, secondSeed.player2Id)
        assertNull(finalMatch.player1Id)
        assertNull(finalMatch.player2Id)
    }

    @Test
    fun bIndexMatchesForPlayer() {
        val user1 = this.participants.first { p -> p.name.equals("User1") }
        val user4 = this.participants.first { p -> p.name.equals("User4") }

        val matches = this.challonge.getMatches(this.tournament, user1)

        assertTrue(matches.size == 1)

        val m = matches[0]

        assertEquals(user1.id, m.player1Id)
        assertEquals(user4.id, m.player2Id)
    }

    @Test
    fun cGetMatch() {
        val user1 = this.participants.first { p -> p.name.equals("User1") }

        val match1 = this.challonge.getMatches(this.tournament, user1)[0]

        val match2 = this.challonge.getMatch(this.tournament, match1.id, false)

        assertEquals(match1, match2)
    }

    @Test
    fun dUpdateMatch() {
        val user1 = this.participants.first { p -> p.name.equals("User1") }

        val initial = this.challonge.getMatches(this.tournament, user1)[0]

        val query = MatchQuery(winnerId = user1.id, scoresCsv = "1-0,1-0")

        val updated = this.challonge.updateMatch(initial, query)

        assertEquals(user1.id, updated.winnerId)
    }

    @After
    fun tearDown() {
        this.challonge.deleteTournament(this.tournament)
    }
}