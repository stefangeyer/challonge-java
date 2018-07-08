package at.stefangeyer.challonge

import at.stefangeyer.challonge.exception.DataAccessException
import at.stefangeyer.challonge.model.Credentials
import at.stefangeyer.challonge.model.Match
import at.stefangeyer.challonge.model.Participant
import at.stefangeyer.challonge.model.Tournament
import at.stefangeyer.challonge.model.enum.TournamentType
import at.stefangeyer.challonge.model.query.ParticipantQuery
import at.stefangeyer.challonge.model.wrapper.ParticipantWrapper
import at.stefangeyer.challonge.rest.*
import at.stefangeyer.challonge.serializer.Serializer
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.*

class ParticipantTest {

    private var initial = true
    private lateinit var challonge: Challonge

    private val tournaments = listOf(
            Tournament(id = 10, url = "tourney123", tournamentType = TournamentType.SINGLE_ELIMINATION,
                    matches = mutableListOf(
                            Match(id = 1, tournamentId = 10, player1Id = 1, player2Id = 2),
                            Match(id = 2, tournamentId = 10, player1Id = 1, player2Id = 3),
                            Match(id = 3, tournamentId = 10, player1Id = 1, player2Id = 4),
                            Match(id = 4, tournamentId = 10, player1Id = 2, player2Id = 3),
                            Match(id = 5, tournamentId = 10, player1Id = 2, player2Id = 4)
                    ),
                    participants = mutableListOf(
                            Participant(id = 1, tournamentId = 10, name = "Participant 1", matches = mutableListOf()),
                            Participant(id = 2, tournamentId = 10, name = "Participant 2", matches = mutableListOf()),
                            Participant(id = 3, tournamentId = 10, name = "Participant 3", matches = mutableListOf()),
                            Participant(id = 4, tournamentId = 10, name = "Participant 4", matches = mutableListOf()),
                            Participant(id = 5, tournamentId = 10, name = "Participant 5", matches = mutableListOf())
                    )
            )
    )

    @Before
    fun setUp() {
        if (this.initial) {
            this.initial = false

            val tournamentRestClient = mock<TournamentRestClient>()

            val participantRestClient = mock<ParticipantRestClient> {
                on { getParticipants(any()) } doAnswer { i ->
                    val tournament = tournaments.firstOrNull { t ->
                        val s = i.getArgument<String>(0)
                        s == t.url || s == t.id.toString()
                    } ?: throw DataAccessException("tournament not found")

                    tournament.participants.map { p -> ParticipantWrapper(p) }
                }

                on { getParticipant(any(), any(), any()) } doAnswer { i ->
                    val tournament = tournaments.firstOrNull { t ->
                        val s = i.getArgument<String>(0)
                        s == t.url || s == t.id.toString()
                    } ?: throw DataAccessException("tournament not found")

                    val participant = tournament.participants.firstOrNull { p ->
                        p.id == i.getArgument<Long>(1)
                    } ?: throw DataAccessException("participant not found")

                    if (i.getArgument<Boolean>(2)) {
                        val matches = tournament.matches.filter { m ->
                            m.player1Id == participant.id || m.player2Id == participant.id
                        }
                        (participant.matches as MutableList<Match>).addAll(matches)
                    }

                    ParticipantWrapper(participant)
                }

                on { addParticipant(any(), any()) } doAnswer { i ->
                    val tournament = tournaments.firstOrNull { t ->
                        val s = i.getArgument<String>(0)
                        s == t.url || s == t.id.toString()
                    } ?: throw DataAccessException("tournament not found")

                    val data = i.getArgument<ParticipantQuery>(1)
                    val id = Random().nextInt(1000).toLong()
                    val participant = Participant(id = id, name = data.name, inviteEmail = data.email,
                            challongeUsername = data.challongeUsername, seed = data.seed ?: 0, misc = data.misc,
                            displayNameWithInvitationEmailAddress = data.inviteNameOrEmail, matches = mutableListOf())

                    (tournament.participants as MutableList<Participant>).add(participant)

                    ParticipantWrapper(participant)
                }

                on { bulkAddParticipants(any(), any()) } doAnswer { i ->
                    val tournament = tournaments.firstOrNull { t ->
                        val s = i.getArgument<String>(0)
                        s == t.url || s == t.id.toString()
                    } ?: throw DataAccessException("tournament not found")

                    val dataList = i.getArgument<List<ParticipantQuery>>(1)
                    val result = mutableListOf<ParticipantWrapper>()

                    for (data in dataList) {
                        val id = Random().nextInt(1000).toLong()
                        val participant = Participant(id = id, name = data.name, inviteEmail = data.email,
                                challongeUsername = data.challongeUsername, seed = data.seed ?: 0, misc = data.misc,
                                displayNameWithInvitationEmailAddress = data.inviteNameOrEmail, matches = mutableListOf())

                        (tournament.participants as MutableList<Participant>).add(participant)
                        result.add(ParticipantWrapper(participant))
                    }

                    result
                }

                on { updateParticipant(any(), any(), any()) } doAnswer { i ->
                    val tournament = tournaments.firstOrNull { t ->
                        val s = i.getArgument<String>(0)
                        s == t.url || s == t.id.toString()
                    } ?: throw DataAccessException("tournament not found")

                    val current = tournament.participants.firstOrNull { p ->
                        p.id == i.getArgument<Long>(1)
                    } ?: throw DataAccessException("participant not found")
                    val data = i.getArgument<ParticipantQuery>(2)

                    val participant = Participant(name = data.name ?: current.name, inviteEmail = data.email
                            ?: current.inviteEmail,
                            challongeUsername = data.challongeUsername ?: current.challongeUsername, seed = data.seed
                            ?: current.seed,
                            misc = data.misc ?: current.misc,
                            displayNameWithInvitationEmailAddress = data.inviteNameOrEmail
                                    ?: current.displayNameWithInvitationEmailAddress,
                            matches = current.matches)

                    (tournament.participants as MutableList<Participant>).add(participant)

                    ParticipantWrapper(participant)
                }

                on { checkInParticipant(any(), any()) } doAnswer { i ->
                    val tournament = tournaments.firstOrNull { t ->
                        val s = i.getArgument<String>(0)
                        s == t.url || s == t.id.toString()
                    } ?: throw DataAccessException("tournament not found")

                    val participant = tournament.participants.first { p ->
                        p.id == i.getArgument<Long>(1)
                    }

                    // emitted content update

                    ParticipantWrapper(participant)
                }

                on { undoCheckInParticipant(any(), any()) } doAnswer { i ->
                    val tournament = tournaments.firstOrNull { t ->
                        val s = i.getArgument<String>(0)
                        s == t.url || s == t.id.toString()
                    } ?: throw DataAccessException("tournament not found")

                    val participant = tournament.participants.firstOrNull { p ->
                        p.id == i.getArgument<Long>(1)
                    } ?: throw DataAccessException("participant not found")

                    // emitted content update

                    ParticipantWrapper(participant)
                }

                on { deleteParticipant(any(), any()) } doAnswer { i ->
                    val tournament = tournaments.firstOrNull { t ->
                        val s = i.getArgument<String>(0)
                        s == t.url || s == t.id.toString()
                    } ?: throw DataAccessException("tournament not found")

                    val participant = tournament.participants.firstOrNull { p ->
                        p.id == i.getArgument<Long>(1)
                    } ?: throw DataAccessException("participant not found")

                    (tournament.participants as MutableList<Participant>).remove(participant)

                    ParticipantWrapper(participant)
                }

                on { randomizeParticipants(any()) } doAnswer { i ->
                    val tournament = tournaments.firstOrNull { t ->
                        val s = i.getArgument<String>(0)
                        s == t.url || s == t.id.toString()
                    } ?: throw DataAccessException("tournament not found")

                    val participants = tournament.participants as MutableList<Participant>
                    participants.shuffle()

                    participants.map { p -> ParticipantWrapper(p) }
                }
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
    }

    @Test
    fun testGetParticipants() {
        val tournament = this.tournaments.first { t -> t.url == "tourney123" }
        val local = this.challonge.getParticipants(tournament)
        assertEquals(tournament.participants, local)
    }

    @Test
    fun testGetParticipant() {
        val tournament = this.tournaments.first { t -> t.url == "tourney123" }
        val local = this.challonge.getParticipant(tournament, 2)
        assertEquals("Participant 2", local.name)
    }

    @Test
    fun testAddParticipant() {
        val tournament = this.tournaments.first { t -> t.url == "tourney123" }
        val local = this.challonge.addParticipant(tournament, ParticipantQuery(name = "New Participants"))
        assertTrue(tournament.participants.contains(local))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testAddParticipantNoData() {
        val tournament = this.tournaments.first { t -> t.url == "tourney123" }
        this.challonge.addParticipant(tournament, ParticipantQuery())
    }

    @Test
    fun testBulkAddParticipant() {
        val tournament = this.tournaments.first { t -> t.url == "tourney123" }
        val participants = listOf(ParticipantQuery(name = "Another Participant 1"), ParticipantQuery(name = "Another Participant 2"))
        val local = this.challonge.bulkAddParticipants(tournament, participants)
        assertTrue(tournament.participants.containsAll(local))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testBulkAddParticipantNoData() {
        val tournament = this.tournaments.first { t -> t.url == "tourney123" }
        this.challonge.bulkAddParticipants(tournament, listOf(ParticipantQuery()))
    }

    @Test
    fun testUpdateParticipant() {
        val tournament = this.tournaments.first { t -> t.url == "tourney123" }
        val participant = tournament.participants.first()
        val local = this.challonge.updateParticipant(participant, ParticipantQuery(name = "New Name"))
        assertEquals("New Name", local.name)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testUpdateParticipantNoData() {
        val tournament = this.tournaments.first { t -> t.url == "tourney123" }
        val participant = tournament.participants.first()
        this.challonge.updateParticipant(participant, ParticipantQuery())
    }

    @Test
    fun testCheckInParticipant() {
        val tournament = this.tournaments.first { t -> t.url == "tourney123" }
        val participant = tournament.participants.first()
        val local = this.challonge.checkInParticipant(participant)
        assertEquals(participant, local)
    }

    @Test
    fun testUndoCheckInParticipant() {
        val tournament = this.tournaments.first { t -> t.url == "tourney123" }
        val participant = tournament.participants.first()
        val local = this.challonge.undoCheckInParticipant(participant)
        assertEquals(participant, local)
    }

    @Test
    fun testDeleteParticipant() {
        val tournament = this.tournaments.first { t -> t.url == "tourney123" }
        val participant = tournament.participants.first()
        val local = this.challonge.deleteParticipant(participant)
        assertEquals(participant, local)
    }

    @Test
    fun testRandomizeParticipant() {
        val tournament = this.tournaments.first { t -> t.url == "tourney123" }
        val local = this.challonge.randomizeParticipants(tournament)
        assertEquals(tournament.participants, local)
    }
}