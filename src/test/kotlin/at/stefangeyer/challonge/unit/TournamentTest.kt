package at.stefangeyer.challonge.unit

import at.stefangeyer.challonge.Challonge
import at.stefangeyer.challonge.exception.DataAccessException
import at.stefangeyer.challonge.model.Credentials
import at.stefangeyer.challonge.model.Match
import at.stefangeyer.challonge.model.Participant
import at.stefangeyer.challonge.model.Tournament
import at.stefangeyer.challonge.model.enum.RankedBy
import at.stefangeyer.challonge.model.enum.TournamentType
import at.stefangeyer.challonge.model.query.TournamentQuery
import at.stefangeyer.challonge.model.query.enum.GrandFinalsModifier
import at.stefangeyer.challonge.model.query.enum.TournamentQueryState
import at.stefangeyer.challonge.model.query.wrapper.TournamentQueryWrapper
import at.stefangeyer.challonge.model.wrapper.TournamentWrapper
import at.stefangeyer.challonge.rest.*
import at.stefangeyer.challonge.serializer.Serializer
import com.nhaarman.mockito_kotlin.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import java.time.OffsetDateTime
import java.util.*

class TournamentTest {

    private var initial = true
    private lateinit var challonge: Challonge

    private val tournaments = mutableListOf(
            Tournament(id = 10, url = "tourney123",
                    tournamentType = TournamentType.SINGLE_ELIMINATION,
                    participants = mutableListOf(
                            Participant(id = 1, tournamentId = 10),
                            Participant(id = 2, tournamentId = 10)),
                    matches = mutableListOf(Match(id = 1, tournamentId = 10, player1Id = 1, player2Id = 2))),
            Tournament(id = 11002, url = "sometourney",
                    tournamentType = TournamentType.ROUND_ROBIN,
                    participants = mutableListOf(),
                    matches = mutableListOf()),
            Tournament(id = 45678, url = "tournament45",
                    tournamentType = TournamentType.DOUBLE_ELIMINATION,
                    rankedBy = RankedBy.GAME_WINS,
                    participants = mutableListOf(),
                    matches = mutableListOf()))

    @Before
    fun setUp() {
        if (this.initial) {
            this.initial = false

            val tournamentRestClient = mock<TournamentRestClient> {
                on { getTournaments(anyOrNull(), anyOrNull(), anyOrNull(), anyOrNull(), anyOrNull()) } doAnswer { i ->
                    tournaments.filter { t ->
                        if (i.getArgument<TournamentQueryState>(0) != null) t.state == i.getArgument(0) else true
                    }.filter { t ->
                        if (i.getArgument<TournamentType>(1) != null) t.tournamentType == i.getArgument(1) else true
                    }.filter { t ->
                        if (i.getArgument<OffsetDateTime>(2) != null)
                            t.createdAt != null && t.createdAt!!.isBefore(i.getArgument(2))
                        else true
                    }.filter { t ->
                        if (i.getArgument<OffsetDateTime>(3) != null)
                            t.createdAt != null && t.createdAt!!.isAfter(i.getArgument(3))
                        else true
                    }.filter { t ->
                        if (i.getArgument<String>(4) != null)
                            t.subdomain != null && t.subdomain.equals(i.getArgument(4))
                        else true
                    }.map { t -> TournamentWrapper(t) }
                }

                on { getTournament(any(), any(), any()) } doAnswer { i ->
                    val tournament = tournaments.firstOrNull { t ->
                        val s = i.getArgument<String>(0)
                        s == t.url || s == t.id.toString()
                    } ?: throw DataAccessException("No tournament found")

                    if (!i.getArgument<Boolean>(1)) (tournament.participants as MutableList<Participant>).clear()
                    if (!i.getArgument<Boolean>(2)) (tournament.matches as MutableList<Match>).clear()

                    TournamentWrapper(tournament)
                }

                on { createTournament(any()) } doAnswer { i ->
                    val data = i.getArgument<TournamentQueryWrapper>(0).tournament
                    if (data.tournamentType == null || data.url == null) throw DataAccessException("Data is invalid")

                    val tournament = Tournament(id = Random().nextInt(1000).toLong(), name = data.name,
                            tournamentType = data.tournamentType!!, url = data.url!!, description = data.description,
                            openSignup = data.openSignup ?: false, holdThirdPlaceMatch = data.holdThirdPlaceMatch
                            ?: false,
                            pointsForMatchWin = data.pointsForMatchWin ?: 0F, pointsForMatchTie = data.pointsForMatchTie
                            ?: 0F,
                            pointsForGameWin = data.pointsForGameWin ?: 0F, pointsForGameTie = data.pointsForGameTie
                            ?: 0F,
                            pointsForBye = data.pointsForBye ?: 0F, swissRounds = data.swissRounds
                            ?: 0, rankedBy = data.rankedBy ?: RankedBy.MATCH_WINS,
                            roundRobinPointsForGameWin = data.roundRobinPointsForGameWin ?: 0F,
                            roundRobinPointsForGameTie = data.roundRobinPointsForGameTie ?: 0F,
                            roundRobinPointsForMatchWin = data.roundRobinPointsForMatchWin ?: 0F,
                            roundRobinPointsForMatchTie = data.roundRobinPointsForMatchTie ?: 0F,
                            acceptAttachments = data.acceptAttachments ?: false, hideForum = data.hideForum ?: false,
                            showRounds = data.showRounds ?: true, private = data.private ?: false,
                            notifyUsersWhenMatchesOpen = data.notifyUsersWhenMatchesOpen ?: false,
                            notifyUsersWhenTheTournamentEnds = data.notifyUsersWhenTheTournamentEnds ?: false,
                            sequentialPairings = data.sequentialPairings ?: false, signupCap = data.signupCap ?: 0,
                            startAt = data.startAt, checkInDuration = data.checkInDuration ?: 0,
                            grandFinalsModifier = data.grandFinalsModifier ?: GrandFinalsModifier.BLANK,
                            tieBreaks = data.tieBreaks ?: listOf(), createdAt = OffsetDateTime.now())
                    tournaments.add(tournament)

                    TournamentWrapper(tournament)
                }

                on { updateTournament(any(), any()) } doAnswer { i ->
                    val current = tournaments.firstOrNull { t ->
                        val s = i.getArgument<String>(0)
                        s == t.url || s == t.id.toString()
                    } ?: throw DataAccessException("tournament not found")

                    val data = i.getArgument<TournamentQueryWrapper>(1).tournament

                    val tournament = updateTournament(current, data)
                    tournaments[tournaments.indexOf(current)] = tournament
                    TournamentWrapper(tournament)
                }

                on { deleteTournament(any()) } doAnswer { i ->
                    val tournament = tournaments.firstOrNull { t ->
                        val s = i.getArgument<String>(0)
                        s == t.url || s == t.id.toString()
                    } ?: throw DataAccessException("tournament not found")

                    tournaments.remove(tournament)
                    TournamentWrapper(tournament)
                }

                on { processCheckIns(any(), any(), any()) } doAnswer { i ->
                    val tournament = tournaments.firstOrNull { t ->
                        val s = i.getArgument<String>(0)
                        s == t.url || s == t.id.toString()
                    } ?: throw DataAccessException("tournament not found")

                    // emitted content update

                    if (!i.getArgument<Boolean>(1)) (tournament.participants as MutableList<Participant>).clear()
                    if (!i.getArgument<Boolean>(2)) (tournament.matches as MutableList<Match>).clear()

                    TournamentWrapper(tournament)
                }

                on { abortCheckIn(any(), any(), any()) } doAnswer { i ->
                    val tournament = tournaments.firstOrNull { t ->
                        val s = i.getArgument<String>(0)
                        s == t.url || s == t.id.toString()
                    } ?: throw DataAccessException("tournament not found")

                    // emitted content update

                    if (!i.getArgument<Boolean>(1)) (tournament.participants as MutableList<Participant>).clear()
                    if (!i.getArgument<Boolean>(2)) (tournament.matches as MutableList<Match>).clear()

                    TournamentWrapper(tournament)
                }

                on { startTournament(any(), any(), any()) } doAnswer { i ->
                    val tournament = tournaments.firstOrNull { t ->
                        val s = i.getArgument<String>(0)
                        s == t.url || s == t.id.toString()
                    } ?: throw DataAccessException("tournament not found")

                    // emitted content update

                    if (!i.getArgument<Boolean>(1)) (tournament.participants as MutableList<Participant>).clear()
                    if (!i.getArgument<Boolean>(2)) (tournament.matches as MutableList<Match>).clear()

                    TournamentWrapper(tournament)
                }

                on { finalizeTournament(any(), any(), any()) } doAnswer { i ->
                    val tournament = tournaments.firstOrNull { t ->
                        val s = i.getArgument<String>(0)
                        s == t.url || s == t.id.toString()
                    } ?: throw DataAccessException("tournament not found")

                    // emitted content update

                    if (!i.getArgument<Boolean>(1)) (tournament.participants as MutableList<Participant>).clear()
                    if (!i.getArgument<Boolean>(2)) (tournament.matches as MutableList<Match>).clear()

                    TournamentWrapper(tournament)
                }

                on { resetTournament(any(), any(), any()) } doAnswer { i ->
                    val tournament = tournaments.firstOrNull { t ->
                        val s = i.getArgument<String>(0)
                        s == t.url || s == t.id.toString()
                    } ?: throw DataAccessException("tournament not found")

                    // emitted content update

                    if (!i.getArgument<Boolean>(1)) (tournament.participants as MutableList<Participant>).clear()
                    if (!i.getArgument<Boolean>(2)) (tournament.matches as MutableList<Match>).clear()

                    TournamentWrapper(tournament)
                }

                on { openTournamentForPredictions(any(), any(), any()) } doAnswer { i ->
                    val tournament = tournaments.firstOrNull { t ->
                        val s = i.getArgument<String>(0)
                        s == t.url || s == t.id.toString()
                    } ?: throw DataAccessException("tournament not found")

                    // emitted content update

                    if (!i.getArgument<Boolean>(1)) (tournament.participants as MutableList<Participant>).clear()
                    if (!i.getArgument<Boolean>(2)) (tournament.matches as MutableList<Match>).clear()

                    TournamentWrapper(tournament)
                }
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
    }

    private fun updateTournament(current: Tournament, data: TournamentQuery): Tournament =
            Tournament(id = current.id, name = data.name ?: current.name,
                    tournamentType = data.tournamentType ?: current.tournamentType, url = data.url
                    ?: current.url,
                    description = data.description ?: current.description, openSignup = data.openSignup
                    ?: current.openSignup,
                    holdThirdPlaceMatch = data.holdThirdPlaceMatch ?: current.holdThirdPlaceMatch,
                    pointsForMatchWin = data.pointsForMatchWin ?: current.pointsForMatchWin,
                    pointsForMatchTie = data.pointsForMatchTie ?: current.pointsForMatchTie,
                    pointsForGameWin = data.pointsForGameWin ?: current.pointsForGameWin,
                    pointsForGameTie = data.pointsForGameTie ?: current.pointsForGameTie,
                    pointsForBye = data.pointsForBye ?: current.pointsForBye,
                    swissRounds = data.swissRounds ?: current.swissRounds,
                    rankedBy = data.rankedBy ?: current.rankedBy,
                    roundRobinPointsForGameWin = data.roundRobinPointsForGameWin
                            ?: current.roundRobinPointsForGameWin,
                    roundRobinPointsForGameTie = data.roundRobinPointsForGameTie
                            ?: current.roundRobinPointsForGameTie,
                    roundRobinPointsForMatchWin = data.roundRobinPointsForMatchWin
                            ?: current.roundRobinPointsForMatchWin,
                    roundRobinPointsForMatchTie = data.roundRobinPointsForMatchTie
                            ?: current.roundRobinPointsForMatchTie,
                    acceptAttachments = data.acceptAttachments ?: current.acceptAttachments,
                    hideForum = data.hideForum ?: current.hideForum,
                    showRounds = data.showRounds ?: current.showRounds, private = data.private
                    ?: current.private,
                    notifyUsersWhenMatchesOpen = data.notifyUsersWhenMatchesOpen
                            ?: current.notifyUsersWhenMatchesOpen,
                    notifyUsersWhenTheTournamentEnds = data.notifyUsersWhenTheTournamentEnds
                            ?: current.notifyUsersWhenTheTournamentEnds,
                    sequentialPairings = data.sequentialPairings ?: current.sequentialPairings,
                    signupCap = data.signupCap ?: current.signupCap, startAt = data.startAt ?: current.startAt,
                    checkInDuration = data.checkInDuration ?: current.checkInDuration,
                    grandFinalsModifier = data.grandFinalsModifier ?: current.grandFinalsModifier,
                    tieBreaks = data.tieBreaks ?: current.tieBreaks,
                    createdAt = current.createdAt, updatedAt = OffsetDateTime.now(),
                    participants = current.participants, matches = current.matches)

    @Test
    fun testGetTournaments() {
        val local = this.challonge.getTournaments()
        assertEquals(this.tournaments, local)
    }

    @Test
    fun testGetTournament() {
        val local = this.challonge.getTournament("tourney123")
        assertEquals(this.tournaments[0], local)
    }

    @Test
    fun testCreateTournament() {
        val local = this.challonge.createTournament(TournamentQuery(
                name = "TournamentTest", tournamentType = TournamentType.SINGLE_ELIMINATION,
                url = "sometournament"))
        assertEquals(this.tournaments.firstOrNull { t -> t.url == "sometournament" }, local)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testCreateTournamentNoData() {
        val local = this.challonge.createTournament(TournamentQuery())
        assertEquals(this.tournaments[0], local)
    }

    @Test
    fun testUpdateTournament() {
        val updated = this.challonge.updateTournament(this.tournaments[0], TournamentQuery(name = "UpdatedName12345"))

        // If one param got passed correctly, all params will be passed correctly (same enum object)
        assertEquals("UpdatedName12345", updated.name)
        assertEquals(this.tournaments[0], updated)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testUpdateTournamentNoData() {
        val local = this.challonge.updateTournament(this.tournaments[0], TournamentQuery())
        assertEquals(this.tournaments[0], local)
    }

    @Test
    fun testDeleteTournament() {
        val toDelete = this.tournaments[2]
        val deleted = this.challonge.deleteTournament(toDelete)

        assertFalse(this.tournaments.contains(toDelete))
        assertEquals(toDelete, deleted)
    }

    @Test
    fun testProcessCheckIns() {
        val toUpdate = this.tournaments.first { t -> t.url == "tourney123" }
        val result = this.challonge.processCheckIns(toUpdate, true, true)
        assertEquals(toUpdate, result)
        assertFalse(result.participants.isEmpty())
        assertFalse(result.matches.isEmpty())
    }

    @Test
    fun testAbortCheckIn() {
        val toUpdate = this.tournaments.first { t -> t.url == "tourney123" }
        val result = this.challonge.abortCheckIn(toUpdate, true, true)
        assertEquals(toUpdate, result)
        assertFalse(result.participants.isEmpty())
        assertFalse(result.matches.isEmpty())
    }

    @Test
    fun testStartTournament() {
        val toUpdate = this.tournaments.first { t -> t.url == "tourney123" }
        val result = this.challonge.startTournament(toUpdate, true, true)
        assertEquals(toUpdate, result)
        assertFalse(result.participants.isEmpty())
        assertFalse(result.matches.isEmpty())
    }

    @Test
    fun testFinalizeTournament() {
        val toUpdate = this.tournaments.first { t -> t.url == "tourney123" }
        val result = this.challonge.finalizeTournament(toUpdate, true, true)
        assertEquals(toUpdate, result)
        assertFalse(result.participants.isEmpty())
        assertFalse(result.matches.isEmpty())
    }

    @Test
    fun testResetTournament() {
        val toUpdate = this.tournaments.first { t -> t.url == "tourney123" }
        val result = this.challonge.resetTournament(toUpdate, true, true)
        assertEquals(toUpdate, result)
        assertFalse(result.participants.isEmpty())
        assertFalse(result.matches.isEmpty())
    }

    @Test
    fun testOpenTournamentForPredictions() {
        val toUpdate = this.tournaments.first { t -> t.url == "tourney123" }
        val result = this.challonge.openTournamentForPredictions(toUpdate, true, true)
        assertEquals(toUpdate, result)
        assertFalse(result.participants.isEmpty())
        assertFalse(result.matches.isEmpty())
    }
}