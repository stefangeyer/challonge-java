package at.stefangeyer.challonge.integrationtest

import at.stefangeyer.challonge.Challonge
import at.stefangeyer.challonge.exception.DataAccessException
import at.stefangeyer.challonge.model.Credentials
import at.stefangeyer.challonge.model.Match
import at.stefangeyer.challonge.model.Tournament
import at.stefangeyer.challonge.model.query.AttachmentQuery
import at.stefangeyer.challonge.model.query.ParticipantQuery
import at.stefangeyer.challonge.model.query.TournamentQuery
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.net.URL

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class AttachmentTest {

    private var initial = true
    private lateinit var challonge: Challonge

    private lateinit var tournament: Tournament
    private lateinit var match: Match

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

            challonge = Challonge(Credentials(username, apiKey))

            try {
                val t = this.challonge.getTournament(TOURNAMENT_URL)
                // Delete the tournament, if it already exists
                this.challonge.deleteTournament(t)
            } catch (ignored: DataAccessException) {
            }

            this.tournament = this.challonge.createTournament(
                    TournamentQuery(name = "Test", url = TOURNAMENT_URL, acceptAttachments = true))

            val user1 = ParticipantQuery(name = "User1", seed = 1)
            val user2 = ParticipantQuery(name = "User2", seed = 2)

            this.challonge.bulkAddParticipants(this.tournament, listOf(user1, user2))

            this.tournament = this.challonge.startTournament(this.tournament, false, true)

            this.match = this.tournament.matches[0]
        }
    }

    @Test
    fun aCreateAttachmentTest() {
        val query = AttachmentQuery(description = "TestDescription", url = "http://www.example.com")

        val attachment = this.challonge.createAttachment(this.match, query)
        assertEquals("TestDescription", attachment.description)
        assertEquals("http://www.example.com", attachment.url)
    }

    @Test
    fun bCreateFileAttachmentTest() {
        val assetFile = File(Thread.currentThread().contextClassLoader.getResource("testfile1.txt")!!.path)
        val query = AttachmentQuery(asset = assetFile, description = "TestDescription")

        val attachment = this.challonge.createAttachment(this.match, query)

        assertEquals("TestDescription", attachment.description)
        assertEquals("testfile1.txt", attachment.assetFileName)

        val assetUrl = URL(attachment.assetUrl)

        assertEquals(
                InputStreamReader(FileInputStream(assetFile), Charsets.UTF_8).readText(),
                InputStreamReader(assetUrl.openStream(), Charsets.UTF_8).readText()
        )
    }

    @Test
    fun cGetAttachmentsTest() {
        val attachment1 = this.challonge.createAttachment(
                match,
                AttachmentQuery(description = "Attachment1")
        )

        val attachment2 = this.challonge.createAttachment(
                match,
                AttachmentQuery(description = "Attachment2")
        )

        val attachments = this.challonge.getAttachments(match)

        assertEquals(2, attachments.size)
        assertEquals(attachment1, attachments.first { a -> a.description.equals("Attachment1") })
        assertEquals(attachment2, attachments.first { a -> a.description.equals("Attachment2") })
    }

    @Test
    fun dGetAttachmentTest() {
        val createdAttachment = this.challonge.createAttachment(match, AttachmentQuery(description = "Attachment1"))

        val readAttachment = this.challonge.getAttachment(match, createdAttachment.id)

        assertEquals(createdAttachment, readAttachment)
    }

    @Test
    fun eUpdateAttachmentTest() {
        val assetFile = File(Thread.currentThread().contextClassLoader.getResource("testfile1.txt")!!.path)

        val createdAttachment =
                this.challonge.createAttachment(match, AttachmentQuery(asset = assetFile, description = "Attachment1"))

        assertEquals("testfile1.txt", createdAttachment.assetFileName)

        val newAssetFile = File(Thread.currentThread().contextClassLoader.getResource("testfile2.txt")!!.path)

        val updatedAttachment = this.challonge.updateAttachment(match, createdAttachment,
                AttachmentQuery(asset = newAssetFile, description = "update"))

        assertEquals("update", updatedAttachment.description)
        assertEquals("testfile2.txt", updatedAttachment.assetFileName)
    }

    @Test
    fun fDeleteAttachmentTest() {
        val attachment1 = this.challonge.createAttachment(match, AttachmentQuery(description = "Attachment1"))
        this.challonge.createAttachment(match, AttachmentQuery(description = "Attachment2"))

        val attachmentsBeforeDelete = this.challonge.getAttachments(match)

        assertEquals(2, attachmentsBeforeDelete.size)

        val deleted = this.challonge.deleteAttachment(match, attachment1)
        assertEquals(attachment1.id, deleted.id)

        val attachmentsAfterDelete = this.challonge.getAttachments(match)
        assertEquals(1, attachmentsAfterDelete.size)
    }

    @Test
    fun zDeleteTournament() {
        this.challonge.deleteTournament(this.tournament)
    }
}