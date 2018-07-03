package at.stefangeyer.challonge

import at.stefangeyer.challonge.model.Credentials
import at.stefangeyer.challonge.model.Participant
import at.stefangeyer.challonge.model.Tournament
import at.stefangeyer.challonge.model.enumeration.TournamentType
import at.stefangeyer.challonge.model.query.ParticipantQuery
import at.stefangeyer.challonge.rest.AttachmentRestClient
import at.stefangeyer.challonge.rest.MatchRestClient
import at.stefangeyer.challonge.rest.ParticipantRestClient
import at.stefangeyer.challonge.rest.TournamentRestClient
import at.stefangeyer.challonge.rest.RestClientFactory
import at.stefangeyer.challonge.serializer.Serializer
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ParticipantTest {
    private lateinit var challonge: Challonge

    private val tournament = Tournament(id = 10, url = "https://www.challonge.com/tourney123",
            tournamentType = TournamentType.SINGLE_ELIMINATION)

    private val participants = listOf(Participant(id = 120, name = "Some Participant"),
            Participant(id = 934, name = "Another Participant"))

    @Before
    fun setUp() {
        val tournamentRestClient = mock<TournamentRestClient>()

        val participantRestClient = mock<ParticipantRestClient> {
            on { getParticipants(any()) } doReturn participants
            on { getParticipant(any(), any(), any()) } doReturn participants[0]
            on { addParticipant(any(), any()) } doReturn participants[0]
            on { bulkAddParticipants(any(), any()) } doReturn participants
            on { updateParticipant(any(), any(), any()) } doReturn participants[0]
            on { checkInParticipant(any(), any()) } doReturn participants[0]
            on { undoCheckInParticipant(any(), any()) } doReturn participants[0]
            on { deleteParticipant(any(), any()) } doReturn participants[0]
            on { randomizeParticipants(any()) } doReturn participants
        }

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
    fun testGetParticipants() {
        val local = this.challonge.getParticipants(this.tournament)
        assertEquals(this.participants, local)
    }

    @Test
    fun testGetParticipant() {
        val local = this.challonge.getParticipant(this.tournament, 120)
        assertEquals(this.participants[0], local)
    }

    @Test
    fun testAddParticipant() {
        val local = this.challonge.addParticipant(this.tournament, ParticipantQuery(name = "New Participants"))
        assertEquals(this.participants[0], local)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testAddParticipantNoData() {
        val local = this.challonge.addParticipant(this.tournament, ParticipantQuery())
        assertEquals(this.participants[0], local)
    }

    @Test
    fun testBulkAddParticipant() {
        val local = this.challonge.bulkAddParticipants(this.tournament, listOf(ParticipantQuery(name = "Another Participant")))
        assertEquals(this.participants, local)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testBulkAddParticipantNoData() {
        val local = this.challonge.bulkAddParticipants(this.tournament, listOf(ParticipantQuery()))
        assertEquals(this.participants, local)
    }

    @Test
    fun testUpdateParticipant() {
        val local = this.challonge.updateParticipant(this.participants[0], ParticipantQuery(name = "New Name"))
        assertEquals(this.participants[0], local)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testUpdateParticipantNoData() {
        val local = this.challonge.updateParticipant(this.participants[0], ParticipantQuery())
        assertEquals(this.participants[0], local)
    }

    @Test
    fun testCheckInParticipant() {
        val local = this.challonge.checkInParticipant(this.participants[0])
        assertEquals(this.participants[0], local)
    }

    @Test
    fun testUndoCheckInParticipant() {
        val local = this.challonge.undoCheckInParticipant(this.participants[0])
        assertEquals(this.participants[0], local)
    }

    @Test
    fun testDeleteParticipant() {
        val local = this.challonge.deleteParticipant(this.participants[0])
        assertEquals(this.participants[0], local)
    }

    @Test
    fun testRandomizeParticipant() {
        val local = this.challonge.randomizeParticipants(this.tournament)
        assertEquals(this.participants, local)
    }
}