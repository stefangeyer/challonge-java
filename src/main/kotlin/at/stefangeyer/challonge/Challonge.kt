package at.stefangeyer.challonge

import at.stefangeyer.challonge.async.Callback
import at.stefangeyer.challonge.exception.DataAccessException
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

    override fun getTournaments(state: TournamentQueryState?, type: TournamentType?, createdAfter: OffsetDateTime?, createdBefore: OffsetDateTime?, subdomain: String?, onSuccess: Callback<List<Tournament>>, onFailure: Callback<DataAccessException>) {
        this.tournaments.getTournaments(state, type, createdAfter, createdBefore, subdomain, onSuccess, onFailure)
    }

    override fun getTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.tournaments.getTournament(tournament, includeParticipants, includeMatches)

    override fun getTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean, onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) {
        this.tournaments.getTournament(tournament, includeParticipants, includeMatches, onSuccess, onFailure)
    }

    override fun createTournament(data: TournamentQuery): Tournament = this.tournaments.createTournament(data)

    override fun createTournament(data: TournamentQuery, onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) {
        this.tournaments.createTournament(data, onSuccess, onFailure)
    }

    override fun updateTournament(tournament: Tournament, data: TournamentQuery): Tournament =
            this.tournaments.updateTournament(tournament, data)

    override fun updateTournament(tournament: Tournament, data: TournamentQuery, onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) {
        this.tournaments.updateTournament(tournament, data, onSuccess, onFailure)
    }

    override fun deleteTournament(tournament: Tournament): Tournament = this.tournaments.deleteTournament(tournament)

    override fun deleteTournament(tournament: Tournament, onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) {
        this.tournaments.deleteTournament(tournament, onSuccess, onFailure)
    }

    override fun processCheckIns(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.tournaments.processCheckIns(tournament, includeParticipants, includeMatches)

    override fun processCheckIns(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean, onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) {
        this.tournaments.processCheckIns(tournament, includeParticipants, includeMatches, onSuccess, onFailure)
    }

    override fun abortCheckIn(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.tournaments.abortCheckIn(tournament, includeParticipants, includeMatches)

    override fun abortCheckIn(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean, onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) {
        this.tournaments.abortCheckIn(tournament, includeParticipants, includeMatches, onSuccess, onFailure)
    }

    override fun startTournament(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.tournaments.startTournament(tournament, includeParticipants, includeMatches)

    override fun startTournament(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean, onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) {
        this.tournaments.startTournament(tournament, includeParticipants, includeMatches, onSuccess, onFailure)
    }

    override fun finalizeTournament(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.tournaments.finalizeTournament(tournament, includeParticipants, includeMatches)

    override fun finalizeTournament(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean, onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) {
        this.tournaments.finalizeTournament(tournament, includeParticipants, includeMatches, onSuccess, onFailure)
    }

    override fun resetTournament(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.tournaments.resetTournament(tournament, includeParticipants, includeMatches)

    override fun resetTournament(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean, onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) {
        this.tournaments.resetTournament(tournament, includeParticipants, includeMatches, onSuccess, onFailure)
    }

    override fun openTournamentForPredictions(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament =
            this.tournaments.openTournamentForPredictions(tournament, includeParticipants, includeMatches)

    override fun openTournamentForPredictions(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean, onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) {
        this.tournaments.openTournamentForPredictions(tournament, includeParticipants, includeMatches, onSuccess, onFailure)
    }

    override fun getParticipants(tournament: Tournament): List<Participant> =
            this.participants.getParticipants(tournament)

    override fun getParticipants(tournament: Tournament, onSuccess: Callback<List<Participant>>, onFailure: Callback<DataAccessException>) {
        this.participants.getParticipants(tournament, onSuccess, onFailure)
    }

    override fun getParticipant(tournament: Tournament, participantId: Long, includeMatches: Boolean): Participant =
            this.participants.getParticipant(tournament, participantId, includeMatches)

    override fun getParticipant(tournament: Tournament, participantId: Long, includeMatches: Boolean, onSuccess: Callback<Participant>, onFailure: Callback<DataAccessException>) {
        this.participants.getParticipant(tournament, participantId, includeMatches, onSuccess, onFailure)
    }

    override fun addParticipant(tournament: Tournament, data: ParticipantQuery): Participant =
            this.participants.addParticipant(tournament, data)

    override fun addParticipant(tournament: Tournament, data: ParticipantQuery, onSuccess: Callback<Participant>, onFailure: Callback<DataAccessException>) {
        this.participants.addParticipant(tournament, data, onSuccess, onFailure)
    }

    override fun bulkAddParticipants(tournament: Tournament, data: List<ParticipantQuery>): List<Participant> =
            this.participants.bulkAddParticipants(tournament, data)

    override fun bulkAddParticipants(tournament: Tournament, data: List<ParticipantQuery>, onSuccess: Callback<List<Participant>>, onFailure: Callback<DataAccessException>) {
        this.participants.bulkAddParticipants(tournament, data, onSuccess, onFailure)
    }

    override fun updateParticipant(participant: Participant, data: ParticipantQuery): Participant =
            this.participants.updateParticipant(participant, data)

    override fun updateParticipant(participant: Participant, data: ParticipantQuery, onSuccess: Callback<Participant>, onFailure: Callback<DataAccessException>) {
        this.participants.updateParticipant(participant, data, onSuccess, onFailure)
    }

    override fun checkInParticipant(participant: Participant): Participant =
            this.participants.checkInParticipant(participant)

    override fun checkInParticipant(participant: Participant, onSuccess: Callback<Participant>, onFailure: Callback<DataAccessException>) {
        this.participants.checkInParticipant(participant, onSuccess, onFailure)
    }

    override fun undoCheckInParticipant(participant: Participant): Participant =
            this.participants.undoCheckInParticipant(participant)

    override fun undoCheckInParticipant(participant: Participant, onSuccess: Callback<Participant>, onFailure: Callback<DataAccessException>) {
        this.participants.undoCheckInParticipant(participant, onSuccess, onFailure)
    }

    override fun deleteParticipant(participant: Participant): Participant =
            this.participants.deleteParticipant(participant)

    override fun deleteParticipant(participant: Participant, onSuccess: Callback<Participant>, onFailure: Callback<DataAccessException>) {
        this.participants.deleteParticipant(participant, onSuccess, onFailure)
    }

    override fun randomizeParticipants(tournament: Tournament): List<Participant> =
            this.participants.randomizeParticipants(tournament)

    override fun randomizeParticipants(tournament: Tournament, onSuccess: Callback<List<Participant>>, onFailure: Callback<DataAccessException>) {
        this.participants.randomizeParticipants(tournament, onSuccess, onFailure)
    }

    override fun getMatches(tournament: Tournament, participant: Participant?, state: MatchState?): List<Match> =
            this.matches.getMatches(tournament, participant, state)

    override fun getMatches(tournament: Tournament, participant: Participant?, state: MatchState?, onSuccess: Callback<List<Match>>, onFailure: Callback<DataAccessException>) {
        this.matches.getMatches(tournament, participant, state, onSuccess, onFailure)
    }

    override fun getMatch(tournament: Tournament, matchId: Long, includeAttachments: Boolean): Match =
            this.matches.getMatch(tournament, matchId, includeAttachments)

    override fun getMatch(tournament: Tournament, matchId: Long, includeAttachments: Boolean, onSuccess: Callback<Match>, onFailure: Callback<DataAccessException>) {
        this.matches.getMatch(tournament, matchId, includeAttachments, onSuccess, onFailure)
    }

    override fun updateMatch(match: Match, data: MatchQuery): Match = this.matches.updateMatch(match, data)

    override fun updateMatch(match: Match, data: MatchQuery, onSuccess: Callback<Match>, onFailure: Callback<DataAccessException>) {
        this.matches.updateMatch(match, data, onSuccess, onFailure)
    }

    override fun reopenMatch(match: Match): Match = this.matches.reopenMatch(match)

    override fun reopenMatch(match: Match, onSuccess: Callback<Match>, onFailure: Callback<DataAccessException>) {
        this.matches.reopenMatch(match, onSuccess, onFailure)
    }

    override fun getAttachments(match: Match): List<Attachment> = this.attachments.getAttachments(match)

    override fun getAttachments(match: Match, onSuccess: Callback<List<Attachment>>, onFailure: Callback<DataAccessException>) {
        this.attachments.getAttachments(match, onSuccess, onFailure)
    }

    override fun getAttachment(match: Match, attachmentId: Long): Attachment =
            this.attachments.getAttachment(match, attachmentId)

    override fun getAttachment(match: Match, attachmentId: Long, onSuccess: Callback<List<Attachment>>, onFailure: Callback<DataAccessException>) {
        this.attachments.getAttachment(match, attachmentId, onSuccess, onFailure)
    }

    override fun createAttachment(match: Match, data: AttachmentQuery): Attachment =
            this.attachments.createAttachment(match, data)

    override fun createAttachment(match: Match, data: AttachmentQuery, onSuccess: Callback<List<Attachment>>, onFailure: Callback<DataAccessException>) {
        this.attachments.createAttachment(match, data, onSuccess, onFailure)
    }

    override fun updateAttachment(match: Match, attachment: Attachment, data: AttachmentQuery): Attachment =
            this.attachments.updateAttachment(match, attachment, data)

    override fun updateAttachment(match: Match, attachment: Attachment, data: AttachmentQuery, onSuccess: Callback<List<Attachment>>, onFailure: Callback<DataAccessException>) {
        this.attachments.updateAttachment(match, attachment, data, onSuccess, onFailure)
    }

    override fun deleteAttachment(match: Match, attachment: Attachment): Attachment =
            this.attachments.deleteAttachment(match, attachment)

    override fun deleteAttachment(match: Match, attachment: Attachment, onSuccess: Callback<List<Attachment>>, onFailure: Callback<DataAccessException>) {
        this.attachments.deleteAttachment(match, attachment, onSuccess, onFailure)
    }
}