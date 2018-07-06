package at.stefangeyer.challonge.integrationtest

import at.stefangeyer.challonge.Challonge
import at.stefangeyer.challonge.exception.DataAccessException
import at.stefangeyer.challonge.model.Credentials
import at.stefangeyer.challonge.model.Tournament
import at.stefangeyer.challonge.model.query.ParticipantQuery
import at.stefangeyer.challonge.model.query.TournamentQuery
import org.junit.Assert.*
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import java.time.OffsetDateTime

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ParticipantTest {

    private var initial = true
    private lateinit var challonge: Challonge

    private lateinit var tournament: Tournament

    companion object {
        private const val TOURNAMENT_URL = "javaapitestparticipant"
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
        }

        val start = OffsetDateTime.now().plusMinutes(75)

        val query = TournamentQuery(name = "Participants", url = TOURNAMENT_URL, checkInDuration = 120,
                startAt = start)
        this.tournament = this.challonge.createTournament(query)
    }

    @Test
    fun aAddParticipants() {
        val query = ParticipantQuery(name = "Stefan", seed = 1, misc = "MiscTest")
        val participant = this.challonge.addParticipant(this.tournament, query)

        assertEquals("Stefan", participant.name)
        assertEquals(1, participant.seed)
        assertEquals("MiscTest", participant.misc)
    }

    @Test
    fun bBulkAddParticipants() {
        val query1 = ParticipantQuery(name = "Bulk1", seed = 1)
        val query2 = ParticipantQuery(name = "Bulk2", inviteNameOrEmail = "Bulk", seed = 2, misc = "BulkAdd")

        val queries = listOf(query1, query2)
        val participants = this.challonge.bulkAddParticipants(this.tournament, queries)

        val participant1 = participants[0]
        val participant2 = participants[1]

        assertEquals("Bulk1", participant1.name)
        assertEquals(1, participant1.seed)

        assertEquals("Bulk2", participant2.name)
        assertEquals("Bulk2", participant2.challongeUsername)
        assertEquals("BulkAdd", participant2.misc)
        assertEquals(2, participant2.seed)
    }

    @Test
    fun cUpdateParticipant() {
        val createQuery = ParticipantQuery(name = "Stefan", misc = "123")

        val createdParticipant = this.challonge.addParticipant(this.tournament, createQuery)

        val updateQuery = ParticipantQuery(name = "Geyer", misc = "321")

        val updatedParticipant = this.challonge.updateParticipant(createdParticipant, updateQuery)

        assertEquals("Geyer", updatedParticipant.name)
        assertEquals("321", updatedParticipant.misc)
    }

    @Test(expected = DataAccessException::class)
    fun dDeleteParticipant() {
        val createQuery = ParticipantQuery(name = "Stefan")

        val createdParticipant = this.challonge.addParticipant(this.tournament, createQuery)
        val deletedParticipant = this.challonge.deleteParticipant(createdParticipant)

        this.challonge.getParticipant(this.tournament, deletedParticipant.id, false)

        // Get call should fail
        assertFalse(true)
    }

    @Test
    fun eRandomizeParticipants() {
        val query1 = ParticipantQuery(name = "User1")
        val query2 = ParticipantQuery(name = "User2")
        val query3 = ParticipantQuery(name = "User3")

        this.challonge.bulkAddParticipants(this.tournament, listOf(query1, query2, query3))

        val randomizedParticipants = this.challonge.randomizeParticipants(this.tournament)

        assertTrue(randomizedParticipants.size == 3)

        val user1 = randomizedParticipants.firstOrNull { p -> p.name.equals("User1") }
        val user2 = randomizedParticipants.firstOrNull { p -> p.name.equals("User2") }
        val user3 = randomizedParticipants.firstOrNull { p -> p.name.equals("User3") }

        assertNotNull(user1)
        assertNotNull(user2)
        assertNotNull(user3)
    }

    @Test
    fun fCheckInParticipant() {
        val createQuery = ParticipantQuery(name = "Stefan")

        val createdParticipant = this.challonge.addParticipant(this.tournament, createQuery)
        val checkedInParticipant = this.challonge.checkInParticipant(createdParticipant)

        assertTrue(checkedInParticipant.checkedIn)
        assertNotNull(checkedInParticipant.checkedInAt)
    }

    @Test
    fun gUndoCheckInParticipant() {
        val createQuery = ParticipantQuery(name = "Stefan")

        val createdParticipant = this.challonge.addParticipant(this.tournament, createQuery)
        val checkedInParticipant = this.challonge.checkInParticipant(createdParticipant)

        val checkedOutParticipant = this.challonge.undoCheckInParticipant(checkedInParticipant)

        assertFalse(checkedOutParticipant.checkedIn)
        assertNull(checkedOutParticipant.checkedInAt)
    }

    @Test
    fun hGetParticipants() {
        val query1 = ParticipantQuery(name = "User1")
        val query2 = ParticipantQuery(name = "User2")
        val query3 = ParticipantQuery(name = "User3")

        this.challonge.bulkAddParticipants(this.tournament, listOf(query1, query2, query3))

        val participants = this.challonge.getParticipants(this.tournament)

        val user1 = participants.first { p -> p.name.equals("User1") }
        val user2 = participants.first { p -> p.name.equals("User2") }
        val user3 = participants.first { p -> p.name.equals("User3") }

        assertNotNull(user1)
        assertNotNull(user2)
        assertNotNull(user3)
    }

    @Test
    fun zDeleteTournament() {
        this.challonge.deleteTournament(tournament)
    }
}