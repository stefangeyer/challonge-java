package at.stefangeyer.challonge.integration

import at.stefangeyer.challonge.Challonge
import at.stefangeyer.challonge.exception.DataAccessException
import at.stefangeyer.challonge.model.Credentials
import at.stefangeyer.challonge.model.Tournament
import at.stefangeyer.challonge.model.enum.RankedBy
import at.stefangeyer.challonge.model.enum.TournamentState
import at.stefangeyer.challonge.model.enum.TournamentType
import at.stefangeyer.challonge.model.query.MatchQuery
import at.stefangeyer.challonge.model.query.ParticipantQuery
import at.stefangeyer.challonge.model.query.TournamentQuery
import at.stefangeyer.challonge.model.query.enum.GrandFinalsModifier
import at.stefangeyer.challonge.rest.retrofit.RetrofitRestClient
import at.stefangeyer.challonge.serializer.gson.GsonSerializer
import org.junit.Assert.*
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import java.time.OffsetDateTime
import java.time.ZoneOffset

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TournamentTest {

    private var initial = true
    private lateinit var challonge: Challonge

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
        }
    }

    @Test
    fun aCreateTournamentTest() {
        try {
            val t = this.challonge.getTournament(TOURNAMENT_URL)
            // Delete the tournament, if it already exists
            this.challonge.deleteTournament(t)
        } catch (ignored: DataAccessException) {
        }

        val dt = OffsetDateTime.of(2025, 8, 22, 10, 0, 0, 0, ZoneOffset.of("-04:00"))

        val query = TournamentQuery(name = "JavaApiTest", url = TOURNAMENT_URL, tournamentType = TournamentType.DOUBLE_ELIMINATION,
                description = "This is a description", holdThirdPlaceMatch = true, openSignup = true, pointsForMatchWin = 2F,
                pointsForMatchTie = 0F, pointsForGameWin = 1F, pointsForGameTie = 0F, pointsForBye = 0.5F, signupCap = 4,
                rankedBy = RankedBy.MATCH_WINS, roundRobinPointsForGameWin = 1F, roundRobinPointsForGameTie = 1.5F,
                roundRobinPointsForMatchWin = 5F, roundRobinPointsForMatchTie = 2F, acceptAttachments = true, hideForum = true,
                showRounds = true, private = true, notifyUsersWhenMatchesOpen = true, notifyUsersWhenTheTournamentEnds = true,
                sequentialPairings = true, startAt = dt, checkInDuration = 5, grandFinalsModifier = GrandFinalsModifier.SINGLE_MATCH)

        val tournament = this.challonge.createTournament(query)

        assertEquals("JavaApiTest", tournament.name)
        assertEquals(TOURNAMENT_URL, tournament.url)
        assertEquals(4, tournament.signupCap)
        assertEquals(TournamentType.DOUBLE_ELIMINATION, tournament.tournamentType)
        assertEquals(tournament.description, "This is a description")
        assertEquals(1F, tournament.roundRobinPointsForGameWin)
        assertEquals(1.5F, tournament.roundRobinPointsForGameTie)
        assertEquals(5F, tournament.roundRobinPointsForMatchWin)
        assertEquals(2F, tournament.roundRobinPointsForMatchTie)
        assertTrue(tournament.acceptAttachments)
        assertTrue(tournament.hideForum)
        assertTrue(tournament.showRounds)
        assertTrue(tournament.private)
        assertTrue(tournament.notifyUsersWhenMatchesOpen)
        assertTrue(tournament.notifyUsersWhenTheTournamentEnds)
        assertTrue(tournament.sequentialPairings)
        assertTrue(tournament.holdThirdPlaceMatch)
        assertTrue(tournament.openSignup)
        assertEquals(2F, tournament.pointsForMatchWin)
        assertEquals(0F, tournament.pointsForMatchTie)
        assertEquals(1F, tournament.pointsForGameWin)
        assertEquals(0F, tournament.pointsForGameTie)
        assertEquals(0.5F, tournament.pointsForBye)
        assertEquals(4, tournament.signupCap)
        assertEquals(RankedBy.MATCH_WINS, tournament.rankedBy)
        assertEquals(5L, tournament.checkInDuration)
        assertEquals(GrandFinalsModifier.SINGLE_MATCH, tournament.grandFinalsModifier)
    }

    @Test
    fun aaCreateSubdomainTournamentTest() {
        val subdomain = System.getProperty("challongeSubdomain")

        if (subdomain != null && !subdomain.isBlank()) {
            // Delete the tournament, if it already exists
            this.challonge.deleteTournament(Tournament(url = "javasubdomaintournament", subdomain = subdomain,
                    tournamentType = TournamentType.SINGLE_ELIMINATION))

            val query = TournamentQuery(subdomain = subdomain, name = "JavaApiTest Subdomain",
                    url = "javasubdomaintournament")

            val tournament = this.challonge.createTournament(query)

            assertEquals(subdomain, tournament.subdomain)

            this.challonge.deleteTournament(tournament)
        }
    }

    @Test
    fun bGetTournamentTest() {
        val tournament = this.challonge.getTournament(TOURNAMENT_URL, false, false)
        assertEquals("JavaApiTest", tournament.name)
    }

    @Test
    fun cUpdateTournamentTest() {
        val query = TournamentQuery(tournamentType = TournamentType.SWISS, signupCap = 6, acceptAttachments = true,
                description = "TestDescription", holdThirdPlaceMatch = true)
        val t = this.challonge.getTournament(TOURNAMENT_URL)
        val tournament = this.challonge.updateTournament(t, query)

        assertEquals(tournament.name, "JavaApiTest")
        assertEquals(tournament.url, TOURNAMENT_URL)
        assertEquals(tournament.tournamentType, TournamentType.SWISS)
        assertEquals(tournament.signupCap, Integer.valueOf(6))
        assertTrue(tournament.acceptAttachments)
        assertEquals(tournament.description, "TestDescription")
        assertTrue(tournament.holdThirdPlaceMatch)
    }

    @Test
    fun dProcessCheckIns() {
        val start = OffsetDateTime.now().plusMinutes(5)

        val tournamentQuery = TournamentQuery(startAt = start, checkInDuration = 10)

        val t = this.challonge.getTournament(TOURNAMENT_URL)
        this.challonge.updateTournament(t, tournamentQuery)

        val participants = this.challonge.bulkAddParticipants(t,
                listOf(ParticipantQuery(name = "User1"), ParticipantQuery(name = "User2")))

        val participant1 = participants.stream().filter { p -> p.name.equals("User1") }.findFirst().get()
        val participant2 = participants.stream().filter { p -> p.name.equals("User2") }.findFirst().get()

        assertTrue(participant1.seed == 1)
        assertTrue(participant2.seed == 2)

        this.challonge.checkInParticipant(participant2)
        val processed = this.challonge.processCheckIns(t, true, false)

        assertEquals(TournamentState.CHECKED_IN, processed.state)
    }

    @Test
    fun eAbortCheckIns() {
        val t = this.challonge.getTournament(TOURNAMENT_URL)
        val aborted = this.challonge.abortCheckIn(t, true, false)
        val participants = aborted.participants

        val participant1 = participants.stream().filter { p -> p.name.equals("User1") }.findFirst().get()
        val participant2 = participants.stream().filter { p -> p.name.equals("User2") }.findFirst().get()

        assertNull(participant1.checkedInAt)
        assertNull(participant2.checkedInAt)
        assertEquals(TournamentState.PENDING, aborted.state)
    }

    @Test
    fun fStartTournament() {
        val t = this.challonge.getTournament(TOURNAMENT_URL)
        val startedTournament = this.challonge.startTournament(t, true, false)

        assertTrue(startedTournament.participants.size == 2)
        assertEquals(startedTournament.state, TournamentState.UNDERWAY)
    }

    @Test
    fun gFinalizeTournament() {
        val tournament = this.challonge.getTournament(TOURNAMENT_URL, true, true)

        val user1 = tournament.participants.first { p -> p.name.equals("User1") }

        val query = MatchQuery(winnerId = user1.id, scoresCsv = "1-3,3-0,3-2")

        val toUpdate = tournament.matches[0]
        val match = this.challonge.updateMatch(toUpdate, query)

        assertEquals("1-3,3-0,3-2", match.scoresCsv)

        val finalizedTournament = this.challonge.finalizeTournament(tournament, true, true)

        assertEquals(TournamentState.COMPLETE, finalizedTournament.state)
    }

    @Test
    fun hResetTournament() {
        val t = this.challonge.getTournament(TOURNAMENT_URL)
        val tournament = this.challonge.resetTournament(t, true, true)
        assertEquals(tournament.state, TournamentState.PENDING)
    }

    @Test
    fun iGetTournaments() {
        val tournaments = this.challonge.getTournaments()
        val tournament = tournaments.firstOrNull { t -> t.url == TOURNAMENT_URL }

        assertNotNull(tournament)
    }

    @Test(expected = DataAccessException::class)
    fun zDeleteTournamentTest() {
        val t = this.challonge.getTournament(TOURNAMENT_URL)
        val tournament = this.challonge.deleteTournament(t)
        assertEquals(tournament.name, "JavaApiTest")
        // check if the tournament is still there
        this.challonge.getTournament(TOURNAMENT_URL, false, false)
    }
}
