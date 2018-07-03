package at.stefangeyer.challonge

import at.stefangeyer.challonge.model.*
import at.stefangeyer.challonge.model.enumeration.MatchState
import at.stefangeyer.challonge.model.enumeration.TournamentType
import at.stefangeyer.challonge.model.enumeration.query.TournamentQueryState
import at.stefangeyer.challonge.model.query.AttachmentQuery
import at.stefangeyer.challonge.model.query.MatchQuery
import at.stefangeyer.challonge.model.query.ParticipantQuery
import at.stefangeyer.challonge.model.query.TournamentQuery
import at.stefangeyer.challonge.rest.RestClientFactory
import at.stefangeyer.challonge.rest.implementation.retrofit.RetrofitRestClientFactory
import at.stefangeyer.challonge.serializer.Serializer
import at.stefangeyer.challonge.serializer.gson.GsonSerializer
import at.stefangeyer.challonge.service.AttachmentService
import at.stefangeyer.challonge.service.MatchService
import at.stefangeyer.challonge.service.ParticipantService
import at.stefangeyer.challonge.service.TournamentService
import at.stefangeyer.challonge.service.implementation.SimpleAttachmentService
import at.stefangeyer.challonge.service.implementation.SimpleMatchService
import at.stefangeyer.challonge.service.implementation.SimpleParticipantService
import at.stefangeyer.challonge.service.implementation.SimpleTournamentService
import java.time.OffsetDateTime

class Challonge(credentials: Credentials, serializer: Serializer = GsonSerializer(),
                private val restClientFactory: RestClientFactory = RetrofitRestClientFactory()
) : TournamentService, ParticipantService, MatchService, AttachmentService {

    private val tournaments: TournamentService
    private val participants: ParticipantService
    private val matches: MatchService
    private val attachments: AttachmentService

    init {
        // Initialize factory
        this.restClientFactory.initialize(credentials, serializer)

        this.tournaments = SimpleTournamentService(this.restClientFactory.createTournamentRestClient())
        this.participants = SimpleParticipantService(this.restClientFactory.createParticipantRestClient())
        this.matches = SimpleMatchService(this.restClientFactory.createMatchRestClient())
        this.attachments = SimpleAttachmentService(this.restClientFactory.createAttachmentRestClient())
    }

    override fun getTournaments(state: TournamentQueryState?, type: TournamentType?, createdAfter: OffsetDateTime?,
                                createdBefore: OffsetDateTime?, subdomain: String?): List<Tournament> =
            this.tournaments.getTournaments(state, type, createdAfter, createdBefore, subdomain)

    override fun getTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.tournaments.getTournament(tournament, includeParticipants, includeMatches)

    override fun createTournament(data: TournamentQuery): Tournament = this.tournaments.createTournament(data)

    override fun updateTournament(tournament: Tournament, data: TournamentQuery): Tournament =
            this.tournaments.updateTournament(tournament, data)

    override fun deleteTournament(tournament: Tournament): Tournament = this.tournaments.deleteTournament(tournament)

    override fun processCheckIns(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.tournaments.processCheckIns(tournament, includeParticipants, includeMatches)

    override fun abortCheckIn(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.tournaments.abortCheckIn(tournament, includeParticipants, includeMatches)

    override fun startTournament(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.tournaments.startTournament(tournament, includeParticipants, includeMatches)

    override fun finalizeTournament(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.tournaments.finalizeTournament(tournament, includeParticipants, includeMatches)

    override fun resetTournament(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.tournaments.resetTournament(tournament, includeParticipants, includeMatches)

    override fun openTournamentForPredictions(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.tournaments.openTournamentForPredictions(tournament, includeParticipants, includeMatches)

    override fun getParticipants(tournament: Tournament): List<Participant> =
            this.participants.getParticipants(tournament)

    override fun getParticipant(tournament: Tournament, participantId: Long, includeMatches: Boolean): Participant =
            this.participants.getParticipant(tournament, participantId, includeMatches)

    override fun addParticipant(tournament: Tournament, data: ParticipantQuery): Participant =
            this.participants.addParticipant(tournament, data)

    override fun bulkAddParticipants(tournament: Tournament, data: List<ParticipantQuery>): List<Participant> =
            this.participants.bulkAddParticipants(tournament, data)

    override fun updateParticipant(participant: Participant, data: ParticipantQuery): Participant =
            this.participants.updateParticipant(participant, data)

    override fun checkInParticipant(participant: Participant): Participant =
            this.participants.checkInParticipant(participant)

    override fun undoCheckInParticipant(participant: Participant): Participant =
            this.participants.undoCheckInParticipant(participant)

    override fun deleteParticipant(participant: Participant): Participant =
            this.participants.deleteParticipant(participant)

    override fun randomizeParticipants(tournament: Tournament): List<Participant> =
            this.participants.randomizeParticipants(tournament)

    override fun getMatches(tournament: Tournament, participant: Participant?, state: MatchState?): List<Match> =
            this.matches.getMatches(tournament, participant, state)

    override fun getMatch(tournament: Tournament, matchId: Long, includeAttachments: Boolean): Match =
            this.matches.getMatch(tournament, matchId, includeAttachments)

    override fun updateMatch(match: Match, data: MatchQuery): Match = this.matches.updateMatch(match, data)

    override fun reopenMatch(match: Match): Match = this.matches.reopenMatch(match)

    override fun getAttachments(match: Match): List<Attachment> = this.attachments.getAttachments(match)

    override fun getAttachment(match: Match, attachmentId: Long): Attachment =
            this.attachments.getAttachment(match, attachmentId)

    override fun createAttachment(match: Match, data: AttachmentQuery): Attachment =
            this.attachments.createAttachment(match, data)

    override fun updateAttachment(match: Match, attachment: Attachment, data: AttachmentQuery): Attachment =
            this.attachments.updateAttachment(match, attachment, data)

    override fun deleteAttachment(match: Match, attachment: Attachment): Attachment =
            this.attachments.deleteAttachment(match, attachment)
}