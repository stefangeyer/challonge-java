package at.stefangeyer.challonge

import at.stefangeyer.challonge.model.Attachment
import at.stefangeyer.challonge.model.Credentials
import at.stefangeyer.challonge.model.Match
import at.stefangeyer.challonge.model.Tournament
import at.stefangeyer.challonge.model.enumeration.TournamentType
import at.stefangeyer.challonge.model.query.AttachmentQuery
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

class AttachmentTest {

    private lateinit var challonge: Challonge

    private val match = Match(id = 12, tournamentId = 10)

    private val attachments = listOf(Attachment(id = 10, description = "Some description"),
            Attachment(id = 5, url = "http://some.resource.com/resource"))

    @Before
    fun setUp() {
        val tournamentRestClient = mock<TournamentRestClient>()
        val participantRestClient = mock<ParticipantRestClient>()
        val matchRestClient = mock<MatchRestClient>()

        val attachmentRestClient = mock<AttachmentRestClient> {
            on { getAttachments(any(), any()) } doReturn attachments
            on { getAttachment(any(), any(), any()) } doReturn attachments[0]
            on { createAttachment(any(), any(), any()) } doReturn attachments[0]
            on { updateAttachment(any(), any(), any(), any()) } doReturn attachments[0]
            on { deleteAttachment(any(), any(), any()) } doReturn attachments[0]
        }

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
    fun testGetAttachments() {
        val local = this.challonge.getAttachments(this.match)
        assertEquals(this.attachments, local)
    }

    @Test
    fun testGetAttachment() {
        val local = this.challonge.getAttachment(this.match, 10)
        assertEquals(this.attachments[0], local)
    }

    @Test
    fun testCreateAttachment() {
        val local = this.challonge.createAttachment(match, AttachmentQuery(description = "Some new attachment"))
        assertEquals(this.attachments[0], local)
    }

    @Test
    fun testUpdateAttachment() {
        val local = this.challonge.updateAttachment(this.match, this.attachments[0], AttachmentQuery(url = "https://www.google.com"))
        assertEquals(this.attachments[0], local)
    }

    @Test
    fun testDeleteAttachment() {
        val local = this.challonge.deleteAttachment(this.match, this.attachments[0])
        assertEquals(this.attachments[0], local)
    }
}