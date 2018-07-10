package at.stefangeyer.challonge

import at.stefangeyer.challonge.exception.DataAccessException
import at.stefangeyer.challonge.model.*
import at.stefangeyer.challonge.model.enum.TournamentType
import at.stefangeyer.challonge.model.query.AttachmentQuery
import at.stefangeyer.challonge.model.wrapper.AttachmentWrapper
import at.stefangeyer.challonge.rest.*
import at.stefangeyer.challonge.serializer.Serializer
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

class AttachmentTest {

    private var initial = true
    private lateinit var challonge: Challonge

    private val tournaments = listOf(
            Tournament(id = 10, url = "tourney123", tournamentType = TournamentType.SINGLE_ELIMINATION,
                    matches = mutableListOf(
                            Match(id = 1, tournamentId = 10, player1Id = 1, player2Id = 2, attachments = mutableListOf(
                                    Attachment(id = 1, description = "Attachment note"),
                                    Attachment(id = 2, description = "Some description"),
                                    Attachment(id = 3, url = "http://some.resource.com/resource")
                            ))
                    ),
                    participants = mutableListOf(
                            Participant(id = 1, tournamentId = 10, name = "Participant 1", matches = mutableListOf()),
                            Participant(id = 2, tournamentId = 10, name = "Participant 2", matches = mutableListOf())
                    )
            )
    )

    @Before
    fun setUp() {
        if (this.initial) {
            this.initial = false
            val tournamentRestClient = mock<TournamentRestClient>()
            val participantRestClient = mock<ParticipantRestClient>()
            val matchRestClient = mock<MatchRestClient>()

            val attachmentRestClient = mock<AttachmentRestClient> {
                on { getAttachments(any(), any()) } doAnswer { i ->
                    val tournament = tournaments.firstOrNull { t ->
                        val s = i.getArgument<String>(0)
                        s == t.url || s == t.id.toString()
                    } ?: throw DataAccessException("tournament not found")

                    val match = tournament.matches.firstOrNull { m ->
                        m.id == i.getArgument(1)
                    } ?: throw DataAccessException("match not found")

                    match.attachments?.map { a -> AttachmentWrapper(a) }
                }

                on { getAttachment(any(), any(), any()) } doAnswer { i ->
                    val tournament = tournaments.firstOrNull { t ->
                        val s = i.getArgument<String>(0)
                        s == t.url || s == t.id.toString()
                    } ?: throw DataAccessException("tournament not found")

                    val match = tournament.matches.firstOrNull { m ->
                        m.id == i.getArgument(1)
                    } ?: throw DataAccessException("match not found")

                    val attachment = match.attachments?.firstOrNull { a ->
                        a.id == i.getArgument(2)
                    } ?: throw DataAccessException("attachment not found")

                    AttachmentWrapper(attachment)
                }

                on { createAttachment(any(), any(), any()) } doAnswer { i ->
                    val tournament = tournaments.firstOrNull { t ->
                        val s = i.getArgument<String>(0)
                        s == t.url || s == t.id.toString()
                    } ?: throw DataAccessException("tournament not found")

                    val match = tournament.matches.firstOrNull { m ->
                        m.id == i.getArgument(1)
                    } ?: throw DataAccessException("match not found")

                    val id = Random().nextInt(1000).toLong()
                    val data = i.getArgument<AttachmentQuery>(2)
                    val attachment = Attachment(id = id, assetUrl = data.url, description = data.description)

                    (match.attachments as MutableList<Attachment>).add(attachment)

                    AttachmentWrapper(attachment)
                }

                on { updateAttachment(any(), any(), any(), any()) } doAnswer { i ->
                    val tournament = tournaments.firstOrNull { t ->
                        val s = i.getArgument<String>(0)
                        s == t.url || s == t.id.toString()
                    } ?: throw DataAccessException("tournament not found")

                    val match = tournament.matches.firstOrNull { m ->
                        m.id == i.getArgument(1)
                    } ?: throw DataAccessException("match not found")

                    val attachment = match.attachments?.firstOrNull { a ->
                        a.id == i.getArgument(2)
                    } ?: throw DataAccessException("attachment not found")

                    // emitted content update

                    AttachmentWrapper(attachment)
                }

                on { deleteAttachment(any(), any(), any()) } doAnswer { i ->
                    val tournament = tournaments.firstOrNull { t ->
                        val s = i.getArgument<String>(0)
                        s == t.url || s == t.id.toString()
                    } ?: throw DataAccessException("tournament not found")

                    val match = tournament.matches.firstOrNull { m ->
                        m.id == i.getArgument(1)
                    } ?: throw DataAccessException("match not found")

                    val attachment = match.attachments?.firstOrNull { a ->
                        a.id == i.getArgument(2)
                    } ?: throw DataAccessException("attachment not found")

                    (match.attachments as MutableList<Attachment>).remove(attachment)

                    AttachmentWrapper(attachment)
                }
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
    }

    @Test
    fun testGetAttachments() {
        val tournament = this.tournaments.first { t -> t.url == "tourney123" }
        val match = tournament.matches[0]
        val local = this.challonge.getAttachments(match)
        assertEquals(match.attachments, local)
    }

    @Test
    fun testGetAttachment() {
        val tournament = this.tournaments.first { t -> t.url == "tourney123" }
        val match = tournament.matches[0]
        val local = this.challonge.getAttachment(match, 1)
        assertEquals("Attachment note", local.description)
    }

    @Test
    fun testCreateAttachment() {
        val tournament = this.tournaments.first { t -> t.url == "tourney123" }
        val match = tournament.matches[0]
        assertNotNull(match.attachments)
        val attachments = match.attachments!!
        val local = this.challonge.createAttachment(match, AttachmentQuery(description = "Some new attachment"))
        assertTrue(attachments.contains(local))
    }

    @Test
    fun testUpdateAttachment() {
        val tournament = this.tournaments.first { t -> t.url == "tourney123" }
        val match = tournament.matches[0]
        assertNotNull(match.attachments)
        val attachments = match.attachments!!
        val local = this.challonge.updateAttachment(match, attachments[0], AttachmentQuery(url = "https://www.google.com"))
        assertEquals(attachments[0], local)
    }

    @Test
    fun testDeleteAttachment() {
        val tournament = this.tournaments.first { t -> t.url == "tourney123" }
        val match = tournament.matches[0]
        assertNotNull(match.attachments)
        val attachments = match.attachments!!
        val local = this.challonge.deleteAttachment(match, attachments[0])
        assertFalse(attachments.contains(local))
    }
}