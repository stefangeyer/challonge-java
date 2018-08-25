package at.stefangeyer.challonge.unit

import at.stefangeyer.challonge.Challonge
import at.stefangeyer.challonge.exception.DataAccessException
import at.stefangeyer.challonge.model.*
import at.stefangeyer.challonge.model.enum.MatchState
import at.stefangeyer.challonge.model.enum.TournamentType
import at.stefangeyer.challonge.model.query.MatchQuery
import at.stefangeyer.challonge.model.query.wrapper.MatchQueryWrapper
import at.stefangeyer.challonge.model.wrapper.MatchWrapper
import at.stefangeyer.challonge.rest.*
import at.stefangeyer.challonge.serializer.Serializer
import com.nhaarman.mockito_kotlin.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MatchTest {

    private var initial = true
    private lateinit var challonge: Challonge

    private val tournaments = listOf(
            Tournament(id = 10, url = "tourney123", tournamentType = TournamentType.SINGLE_ELIMINATION,
                    participants = mutableListOf(
                            Participant(id = 1, tournamentId = 10, name = "Participant 1", matches = mutableListOf()),
                            Participant(id = 2, tournamentId = 10, name = "Participant 2", matches = mutableListOf()),
                            Participant(id = 3, tournamentId = 10, name = "Participant 3", matches = mutableListOf()),
                            Participant(id = 4, tournamentId = 10, name = "Participant 4", matches = mutableListOf()),
                            Participant(id = 5, tournamentId = 10, name = "Participant 5", matches = mutableListOf())
                    ),
                    matches = mutableListOf(
                            Match(id = 1, tournamentId = 10, player1Id = 1, player2Id = 2, attachments = mutableListOf(
                                    Attachment(id = 1, description = "Attachment note")
                            )),
                            Match(id = 2, tournamentId = 10, player1Id = 1, player2Id = 3, attachments = mutableListOf()),
                            Match(id = 3, tournamentId = 10, player1Id = 1, player2Id = 4, attachments = mutableListOf()),
                            Match(id = 4, tournamentId = 10, player1Id = 2, player2Id = 3, attachments = mutableListOf()),
                            Match(id = 5, tournamentId = 10, player1Id = 2, player2Id = 4, attachments = mutableListOf())
                    )
            )
    )

    @Before
    fun setUp() {
        if (this.initial) {
            this.initial = false

            val tournamentRestClient = mock<TournamentRestClient>()
            val participantRestClient = mock<ParticipantRestClient>()

            val matchRestClient = mock<MatchRestClient> {
                on { getMatches(any(), anyOrNull(), anyOrNull()) } doAnswer { i ->
                    val tournament = tournaments.firstOrNull { t ->
                        val s = i.getArgument<String>(0)
                        s == t.url || s == t.id.toString()
                    } ?: throw DataAccessException("tournament not found")

                    var matches = tournament.matches

                    val pid = i.getArgument<Long?>(1)
                    if (pid != null) {
                        matches = matches.filter { m ->
                            m.player1Id == pid || m.player2Id == pid
                        }
                    }

                    val state = i.getArgument<MatchState?>(2)
                    if (state != null) {
                        matches = matches.filter { m ->
                            m.state == state
                        }
                    }

                    matches.map { m -> MatchWrapper(m) }
                }

                on { getMatch(any(), any(), any()) } doAnswer { i ->
                    val tournament = tournaments.firstOrNull { t ->
                        val s = i.getArgument<String>(0)
                        s == t.url || s == t.id.toString()
                    } ?: throw DataAccessException("tournament not found")

                    val match = tournament.matches.firstOrNull { m ->
                        m.id == i.getArgument(1)
                    } ?: throw DataAccessException("match not found")

                    if (!i.getArgument<Boolean>(2)) {
                        (match.attachments as MutableList<Attachment>).clear()
                    }

                    MatchWrapper(match)
                }

                on { updateMatch(any(), any(), any()) } doAnswer { i ->
                    val tournament = tournaments.firstOrNull { t ->
                        val s = i.getArgument<String>(0)
                        s == t.url || s == t.id.toString()
                    } ?: throw DataAccessException("tournament not found")

                    val match = tournament.matches.firstOrNull { m ->
                        m.id == i.getArgument(1)
                    } ?: throw DataAccessException("match not found")

                    val data = i.getArgument<MatchQueryWrapper>(2).match
                    val updated = Match(id = match.id, tournamentId = tournament.id,
                            winnerId = data.winnerId ?: match.winnerId, scoresCsv = data.scoresCsv)

                    MatchWrapper(updated)
                }

                on { reopenMatch(any(), any()) } doAnswer { i ->
                    val tournament = tournaments.firstOrNull { t ->
                        val s = i.getArgument<String>(0)
                        s == t.url || s == t.id.toString()
                    } ?: throw DataAccessException("tournament not found")

                    val match = tournament.matches.firstOrNull { m ->
                        m.id == i.getArgument(1)
                    } ?: throw DataAccessException("match not found")

                    // emitted content update

                    MatchWrapper(match)
                }
            }

            val attachmentRestClient = mock<AttachmentRestClient>()

            val restClientFactory = mock<RestClient> {
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
    fun testGetMatches() {
        val tournament = this.tournaments.first { t -> t.url == "tourney123" }
        val local = this.challonge.getMatches(tournament)
        assertEquals(tournament.matches, local)
    }

    @Test
    fun testGetMatch() {
        val tournament = this.tournaments.first { t -> t.url == "tourney123" }
        val local = this.challonge.getMatch(tournament, 1)
        assertEquals(tournament.matches[0], local)
    }

    @Test
    fun testUpdateMatch() {
        val tournament = this.tournaments.first { t -> t.url == "tourney123" }
        val match = tournament.matches[0]
        val local = this.challonge.updateMatch(match, MatchQuery(winnerId = 120))
        assertEquals(120L, local.winnerId)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testUpdateMatchNoData() {
        val tournament = this.tournaments.first { t -> t.url == "tourney123" }
        val match = tournament.matches[0]
        this.challonge.updateMatch(match, MatchQuery())
    }

    @Test
    fun testReopenMatch() {
        val tournament = this.tournaments.first { t -> t.url == "tourney123" }
        val match = tournament.matches[0]
        val local = this.challonge.reopenMatch(match)
        assertEquals(match, local)
    }
}