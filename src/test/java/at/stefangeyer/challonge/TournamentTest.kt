package at.stefangeyer.challonge

import at.stefangeyer.challonge.model.Credentials
import at.stefangeyer.challonge.model.Tournament
import at.stefangeyer.challonge.model.enumeration.RankedBy
import at.stefangeyer.challonge.model.enumeration.TournamentType
import at.stefangeyer.challonge.model.query.TournamentQuery
import at.stefangeyer.challonge.rest.AttachmentRestClient
import at.stefangeyer.challonge.rest.MatchRestClient
import at.stefangeyer.challonge.rest.ParticipantRestClient
import at.stefangeyer.challonge.rest.TournamentRestClient
import at.stefangeyer.challonge.rest.client.RestClientFactory
import at.stefangeyer.challonge.serializer.Serializer
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.anyOrNull
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TournamentTest {

    private lateinit var challonge: Challonge

    private val tournaments = listOf(
            Tournament(id = 10, url = "https://www.challonge.com/tourney123",
                    tournamentType = TournamentType.SINGLE_ELIMINATION),
            Tournament(id = 11002, url = "https://www.challonge.com/sometourney",
                    tournamentType = TournamentType.ROUND_ROBIN),
            Tournament(id = 45678, url = "https://www.challonge.com/tournament45",
                    tournamentType = TournamentType.DOUBLE_ELIMINATION,
                    rankedBy = RankedBy.GAME_WINS))

    @Before
    fun setUp() {
        val tournamentRestClient = mock<TournamentRestClient> {
            on { getTournaments(anyOrNull(), anyOrNull(), anyOrNull(), anyOrNull(), anyOrNull()) } doReturn tournaments
            on { getTournament(any(), any(), any()) } doReturn tournaments[0]
            on { createTournament(any()) } doReturn tournaments[0]
            on { updateTournament(any(), any()) } doReturn tournaments[0]
            on { deleteTournament(any()) } doReturn tournaments[0]
            on { processCheckIns(any(), any(), any()) } doReturn tournaments[0]
            on { abortCheckIn(any(), any(), any()) } doReturn tournaments[0]
            on { startTournament(any(), any(), any()) } doReturn tournaments[0]
            on { finalizeTournament(any(), any(), any()) } doReturn tournaments[0]
            on { resetTournament(any(), any(), any()) } doReturn tournaments[0]
            on { openTournamentForPredictions(any(), any(), any()) } doReturn tournaments[0]
        }

        val participantRestClient = mock<ParticipantRestClient>()
        val matchRestClient = mock<MatchRestClient>()
        val attachmentRestClient = mock<AttachmentRestClient>()

        val restClientFactory = mock<RestClientFactory> {
            on { createTournamentRestClient() } doReturn tournamentRestClient
            on { createParticipantRestClient() } doReturn participantRestClient
            on { createMatchRestClient() } doReturn matchRestClient
            on { createAttachmentRestClient() } doReturn attachmentRestClient
        }

        val serializer = mock<Serializer>()

        this.challonge = Challonge(Credentials("", ""), serializer, restClientFactory)
    }

    @Test
    fun testGetTournaments() {
        val local = this.challonge.getTournaments()
        assertEquals(this.tournaments, local)
    }

    @Test
    fun testGetTournament() {
        val local = this.challonge.getTournament("tourney")
        assertEquals(this.tournaments[0], local)
    }

    @Test
    fun testCreateTournament() {
        val local = this.challonge.createTournament(
                TournamentQuery(name = "TournamentTest", tournamentType = TournamentType.SINGLE_ELIMINATION,
                        url = "sometournament"))
        assertEquals(this.tournaments[0], local)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testCreateTournamentNoData() {
        val local = this.challonge.createTournament(TournamentQuery())
        assertEquals(this.tournaments[0], local)
    }

    @Test
    fun testUpdateTournament() {
        val local = this.challonge.updateTournament(this.tournaments[0], TournamentQuery(name = "UpdatedName"))
        assertEquals(this.tournaments[0], local)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testUpdateTournamentNoData() {
        val local = this.challonge.updateTournament(this.tournaments[0], TournamentQuery())
        assertEquals(this.tournaments[0], local)
    }

    @Test
    fun testDeleteTournament() {
        val local = this.challonge.deleteTournament(this.tournaments[0])
        assertEquals(this.tournaments[0], local)
    }

    @Test
    fun testProcessCheckIns() {
        val local = this.challonge.processCheckIns(this.tournaments[0])
        assertEquals(this.tournaments[0], local)
    }

    @Test
    fun testAbortCheckIn() {
        val local = this.challonge.abortCheckIn(this.tournaments[0])
        assertEquals(this.tournaments[0], local)
    }

    @Test
    fun testStartTournament() {
        val local = this.challonge.startTournament(this.tournaments[0])
        assertEquals(this.tournaments[0], local)
    }

    @Test
    fun testFinalizeTournament() {
        val local = this.challonge.finalizeTournament(this.tournaments[0])
        assertEquals(this.tournaments[0], local)
    }

    @Test
    fun testResetTournament() {
        val local = this.challonge.resetTournament(this.tournaments[0])
        assertEquals(this.tournaments[0], local)
    }

    @Test
    fun testOpenTournamentForPredictions() {
        val local = this.challonge.openTournamentForPredictions(this.tournaments[0])
        assertEquals(this.tournaments[0], local)
    }
}