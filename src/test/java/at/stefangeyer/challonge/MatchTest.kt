package at.stefangeyer.challonge

import at.stefangeyer.challonge.model.Credentials
import at.stefangeyer.challonge.model.Match
import at.stefangeyer.challonge.model.Tournament
import at.stefangeyer.challonge.model.enumeration.TournamentType
import at.stefangeyer.challonge.model.query.MatchQuery
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

class MatchTest {
    private lateinit var challonge: Challonge

    private val tournament = Tournament(id = 10, url = "https://www.challonge.com/tourney123",
            tournamentType = TournamentType.SINGLE_ELIMINATION)

    private val matches = listOf(Match(id = 12, tournamentId = 10), Match(id = 2, tournamentId = 10))

    @Before
    fun setUp() {
        val tournamentRestClient = mock<TournamentRestClient>()
        val participantRestClient = mock<ParticipantRestClient>()

        val matchRestClient = mock<MatchRestClient> {
            on { getMatches(any(), anyOrNull(), anyOrNull()) } doReturn matches
            on { getMatch(any(), any(), any()) } doReturn matches[0]
            on { updateMatch(any(), any(), any()) } doReturn matches[0]
            on { reopenMatch(any(), any()) } doReturn matches[0]
        }

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
    fun testGetMatches() {
        val local = this.challonge.getMatches(this.tournament)
        assertEquals(this.matches, local)
    }

    @Test
    fun testGetMatch() {
        val local = this.challonge.getMatch(this.tournament, 12)
        assertEquals(this.matches[0], local)
    }

    @Test
    fun testUpdateMatch() {
        val local = this.challonge.updateMatch(this.matches[0], MatchQuery(winnerId = 120))
        assertEquals(this.matches[0], local)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testUpdateMatchNoData() {
        val local = this.challonge.updateMatch(this.matches[0], MatchQuery())
        assertEquals(this.matches[0], local)
    }

    @Test
    fun testReopenMatch() {
        val local = this.challonge.reopenMatch(this.matches[0])
        assertEquals(this.matches[0], local)
    }
}