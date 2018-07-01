package at.stefangeyer.challonge

import at.stefangeyer.challonge.model.Credentials
import at.stefangeyer.challonge.model.Tournament
import at.stefangeyer.challonge.model.enumeration.TournamentType
import at.stefangeyer.challonge.rest.AttachmentRestClient
import at.stefangeyer.challonge.rest.MatchRestClient
import at.stefangeyer.challonge.rest.ParticipantRestClient
import at.stefangeyer.challonge.rest.TournamentRestClient
import at.stefangeyer.challonge.rest.client.RestClientFactory
import at.stefangeyer.challonge.serializer.Serializer
import com.nhaarman.mockito_kotlin.any
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
                    tournamentType = TournamentType.DOUBLE_ELIMINATION))

    @Before
    fun setUp() {
        val tournamentRestClient = mock<TournamentRestClient> {
            on { getTournaments(any(), any(), any(), any(), any()) } doReturn tournaments
            on { getTournament(any(), any(), any()) } doReturn tournaments[0]
            on { createTournament(any()) } doReturn tournaments[0]
            on { updateTournament(any(), any()) } doReturn tournaments[0]
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
    fun testGetTournament() {
        val local = this.challonge.tournaments.getTournament("tourney", false, false)
        assertEquals(tournaments[0], local)
    }
}