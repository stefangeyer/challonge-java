package at.stefangeyer.challonge

import at.stefangeyer.challonge.async.Callback
import at.stefangeyer.challonge.exception.DataAccessException
import at.stefangeyer.challonge.model.*
import at.stefangeyer.challonge.model.enum.MatchState
import at.stefangeyer.challonge.model.enum.TournamentType
import at.stefangeyer.challonge.model.query.AttachmentQuery
import at.stefangeyer.challonge.model.query.MatchQuery
import at.stefangeyer.challonge.model.query.ParticipantQuery
import at.stefangeyer.challonge.model.query.TournamentQuery
import at.stefangeyer.challonge.model.query.enum.TournamentQueryState
import at.stefangeyer.challonge.rest.RestClient
import at.stefangeyer.challonge.serializer.Serializer
import at.stefangeyer.challonge.service.AttachmentService
import at.stefangeyer.challonge.service.MatchService
import at.stefangeyer.challonge.service.ParticipantService
import at.stefangeyer.challonge.service.TournamentService
import at.stefangeyer.challonge.service.implementation.SimpleAttachmentService
import at.stefangeyer.challonge.service.implementation.SimpleMatchService
import at.stefangeyer.challonge.service.implementation.SimpleParticipantService
import at.stefangeyer.challonge.service.implementation.SimpleTournamentService
import java.time.OffsetDateTime

class Challonge(credentials: Credentials, serializer: Serializer, private val restClient: RestClient) {

    private val tournaments: TournamentService
    private val participants: ParticipantService
    private val matches: MatchService
    private val attachments: AttachmentService

    init {
        // Initialize factory
        this.restClient.initialize(credentials, serializer)

        this.tournaments = SimpleTournamentService(this.restClient.createTournamentRestClient())
        this.participants = SimpleParticipantService(this.restClient.createParticipantRestClient())
        this.matches = SimpleMatchService(this.restClient.createMatchRestClient())
        this.attachments = SimpleAttachmentService(this.restClient.createAttachmentRestClient())
    }

    /**
     * @see TournamentService
     */
    @JvmOverloads
    @Throws(DataAccessException::class)
    fun getTournaments(state: TournamentQueryState? = null, type: TournamentType? = null, createdAfter: OffsetDateTime? = null,
                       createdBefore: OffsetDateTime? = null, subdomain: String? = null): List<Tournament> =
            this.tournaments.getTournaments(state, type, createdAfter, createdBefore, subdomain)

    /**
     * @see TournamentService
     */
    @JvmOverloads
    fun getTournaments(state: TournamentQueryState? = null, type: TournamentType? = null, createdAfter: OffsetDateTime? = null,
                       createdBefore: OffsetDateTime? = null, subdomain: String? = null, onSuccess: Callback<List<Tournament>>,
                       onFailure: Callback<DataAccessException>) =
            this.tournaments.getTournaments(state, type, createdAfter, createdBefore, subdomain, onSuccess, onFailure)

    /**
     * @see TournamentService
     */
    @JvmOverloads
    @Throws(DataAccessException::class)
    fun getTournament(tournament: String, includeParticipants: Boolean = false, includeMatches: Boolean = false): Tournament =
            this.tournaments.getTournament(tournament, includeParticipants, includeMatches)

    /**
     * @see TournamentService
     */
    @JvmOverloads
    fun getTournament(tournament: String, includeParticipants: Boolean = false, includeMatches: Boolean = false,
                      onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) =
        this.tournaments.getTournament(tournament, includeParticipants, includeMatches, onSuccess, onFailure)

    /**
     * @see TournamentService
     */
    @Throws(DataAccessException::class)
    fun createTournament(data: TournamentQuery): Tournament = this.tournaments.createTournament(data)

    /**
     * @see TournamentService
     */
    fun createTournament(data: TournamentQuery, onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) =
            this.tournaments.createTournament(data, onSuccess, onFailure)

    /**
     * @see TournamentService
     */
    @Throws(DataAccessException::class)
    fun updateTournament(tournament: Tournament, data: TournamentQuery): Tournament =
            this.tournaments.updateTournament(tournament, data)

    /**
     * @see TournamentService
     */
    fun updateTournament(tournament: Tournament, data: TournamentQuery, onSuccess: Callback<Tournament>,
                         onFailure: Callback<DataAccessException>) =
            this.tournaments.updateTournament(tournament, data, onSuccess, onFailure)

    /**
     * @see TournamentService
     */
    @Throws(DataAccessException::class)
    fun deleteTournament(tournament: Tournament): Tournament = this.tournaments.deleteTournament(tournament)

    /**
     * @see TournamentService
     */
    fun deleteTournament(tournament: Tournament, onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) =
            this.tournaments.deleteTournament(tournament, onSuccess, onFailure)

    /**
     * @see TournamentService
     */
    @JvmOverloads
    @Throws(DataAccessException::class)
    fun processCheckIns(tournament: Tournament, includeParticipants: Boolean = false, includeMatches: Boolean = false): Tournament =
            this.tournaments.processCheckIns(tournament, includeParticipants, includeMatches)

    /**
     * @see TournamentService
     */
    @JvmOverloads
    fun processCheckIns(tournament: Tournament, includeParticipants: Boolean = false, includeMatches: Boolean = false,
                        onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) =
            this.tournaments.processCheckIns(tournament, includeParticipants, includeMatches, onSuccess, onFailure)

    /**
     * @see TournamentService
     */
    @JvmOverloads
    @Throws(DataAccessException::class)
    fun abortCheckIn(tournament: Tournament, includeParticipants: Boolean = false, includeMatches: Boolean = false): Tournament =
            this.tournaments.abortCheckIn(tournament, includeParticipants, includeMatches)

    /**
     * @see TournamentService
     */
    @JvmOverloads
    fun abortCheckIn(tournament: Tournament, includeParticipants: Boolean = false, includeMatches: Boolean = false,
                     onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) =
            this.tournaments.abortCheckIn(tournament, includeParticipants, includeMatches, onSuccess, onFailure)

    /**
     * @see TournamentService
     */
    @JvmOverloads
    @Throws(DataAccessException::class)
    fun startTournament(tournament: Tournament, includeParticipants: Boolean = false, includeMatches: Boolean = false): Tournament =
            this.tournaments.startTournament(tournament, includeParticipants, includeMatches)

    /**
     * @see TournamentService
     */
    @JvmOverloads
    fun startTournament(tournament: Tournament, includeParticipants: Boolean = false, includeMatches: Boolean = false,
                        onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) =
            this.tournaments.startTournament(tournament, includeParticipants, includeMatches, onSuccess, onFailure)

    /**
     * @see TournamentService
     */
    @JvmOverloads
    @Throws(DataAccessException::class)
    fun finalizeTournament(tournament: Tournament, includeParticipants: Boolean = false, includeMatches: Boolean = false): Tournament =
            this.tournaments.finalizeTournament(tournament, includeParticipants, includeMatches)

    /**
     * @see TournamentService
     */
    @JvmOverloads
    fun finalizeTournament(tournament: Tournament, includeParticipants: Boolean = false, includeMatches: Boolean = false,
                           onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) =
            this.tournaments.finalizeTournament(tournament, includeParticipants, includeMatches, onSuccess, onFailure)

    /**
     * @see TournamentService
     */
    @JvmOverloads
    @Throws(DataAccessException::class)
    fun resetTournament(tournament: Tournament, includeParticipants: Boolean = false, includeMatches: Boolean = false): Tournament =
            this.tournaments.resetTournament(tournament, includeParticipants, includeMatches)

    /**
     * @see TournamentService
     */
    @JvmOverloads
    fun resetTournament(tournament: Tournament, includeParticipants: Boolean = false, includeMatches: Boolean = false,
                        onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) =
            this.tournaments.resetTournament(tournament, includeParticipants, includeMatches, onSuccess, onFailure)

    /**
     * @see TournamentService
     */
    @JvmOverloads
    @Throws(DataAccessException::class)
    fun openTournamentForPredictions(tournament: Tournament, includeParticipants: Boolean = false, includeMatches: Boolean = false): Tournament =
            this.tournaments.openTournamentForPredictions(tournament, includeParticipants, includeMatches)

    /**
     * @see TournamentService
     */
    @JvmOverloads
    fun openTournamentForPredictions(tournament: Tournament, includeParticipants: Boolean = false, includeMatches: Boolean = false,
                                     onSuccess: Callback<Tournament>, onFailure: Callback<DataAccessException>) =
            this.tournaments.openTournamentForPredictions(tournament, includeParticipants, includeMatches, onSuccess, onFailure)

    /**
     * @see ParticipantService
     */
    @Throws(DataAccessException::class)
    fun getParticipants(tournament: Tournament): List<Participant> =
            this.participants.getParticipants(tournament)

    /**
     * @see ParticipantService
     */
    fun getParticipants(tournament: Tournament, onSuccess: Callback<List<Participant>>, onFailure: Callback<DataAccessException>) =
            this.participants.getParticipants(tournament, onSuccess, onFailure)

    @JvmOverloads
    @Throws(DataAccessException::class)
    fun getParticipant(tournament: Tournament, participantId: Long, includeMatches: Boolean = false): Participant =
            this.participants.getParticipant(tournament, participantId, includeMatches)

    /**
     * @see ParticipantService
     */
    @JvmOverloads
    fun getParticipant(tournament: Tournament, participantId: Long, includeMatches: Boolean = false,
                       onSuccess: Callback<Participant>, onFailure: Callback<DataAccessException>) =
            this.participants.getParticipant(tournament, participantId, includeMatches, onSuccess, onFailure)

    /**
     * @see ParticipantService
     */
    @Throws(DataAccessException::class)
    fun addParticipant(tournament: Tournament, data: ParticipantQuery): Participant =
            this.participants.addParticipant(tournament, data)

    /**
     * @see ParticipantService
     */
    fun addParticipant(tournament: Tournament, data: ParticipantQuery, onSuccess: Callback<Participant>,
                       onFailure: Callback<DataAccessException>) =
            this.participants.addParticipant(tournament, data, onSuccess, onFailure)

    /**
     * @see ParticipantService
     */
    @Throws(DataAccessException::class)
    fun bulkAddParticipants(tournament: Tournament, data: List<ParticipantQuery>): List<Participant> =
            this.participants.bulkAddParticipants(tournament, data)

    /**
     * @see ParticipantService
     */
    fun bulkAddParticipants(tournament: Tournament, data: List<ParticipantQuery>, onSuccess: Callback<List<Participant>>,
                            onFailure: Callback<DataAccessException>) =
            this.participants.bulkAddParticipants(tournament, data, onSuccess, onFailure)

    /**
     * @see ParticipantService
     */
    @Throws(DataAccessException::class)
    fun updateParticipant(participant: Participant, data: ParticipantQuery): Participant =
            this.participants.updateParticipant(participant, data)

    /**
     * @see ParticipantService
     */
    fun updateParticipant(participant: Participant, data: ParticipantQuery, onSuccess: Callback<Participant>,
                          onFailure: Callback<DataAccessException>) =
            this.participants.updateParticipant(participant, data, onSuccess, onFailure)

    /**
     * @see ParticipantService
     */
    @Throws(DataAccessException::class)
    fun checkInParticipant(participant: Participant): Participant =
            this.participants.checkInParticipant(participant)

    /**
     * @see ParticipantService
     */
    fun checkInParticipant(participant: Participant, onSuccess: Callback<Participant>, onFailure: Callback<DataAccessException>) =
            this.participants.checkInParticipant(participant, onSuccess, onFailure)

    /**
     * @see ParticipantService
     */
    @Throws(DataAccessException::class)
    fun undoCheckInParticipant(participant: Participant): Participant =
            this.participants.undoCheckInParticipant(participant)

    /**
     * @see ParticipantService
     */
    fun undoCheckInParticipant(participant: Participant, onSuccess: Callback<Participant>, onFailure: Callback<DataAccessException>) =
            this.participants.undoCheckInParticipant(participant, onSuccess, onFailure)

    /**
     * @see ParticipantService
     */
    @Throws(DataAccessException::class)
    fun deleteParticipant(participant: Participant): Participant =
            this.participants.deleteParticipant(participant)

    /**
     * @see ParticipantService
     */
    fun deleteParticipant(participant: Participant, onSuccess: Callback<Participant>, onFailure: Callback<DataAccessException>) =
            this.participants.deleteParticipant(participant, onSuccess, onFailure)

    /**
     * @see ParticipantService
     */
    @Throws(DataAccessException::class)
    fun randomizeParticipants(tournament: Tournament): List<Participant> =
            this.participants.randomizeParticipants(tournament)

    /**
     * @see ParticipantService
     */
    fun randomizeParticipants(tournament: Tournament, onSuccess: Callback<List<Participant>>, onFailure: Callback<DataAccessException>) =
            this.participants.randomizeParticipants(tournament, onSuccess, onFailure)

    /**
     * @see MatchService
     */
    @JvmOverloads
    @Throws(DataAccessException::class)
    fun getMatches(tournament: Tournament, participant: Participant? = null, state: MatchState? = null): List<Match> =
            this.matches.getMatches(tournament, participant, state)

    /**
     * @see MatchService
     */
    @JvmOverloads
    fun getMatches(tournament: Tournament, participant: Participant? = null, state: MatchState? = null,
                   onSuccess: Callback<List<Match>>, onFailure: Callback<DataAccessException>) =
            this.matches.getMatches(tournament, participant, state, onSuccess, onFailure)

    /**
     * @see MatchService
     */
    @JvmOverloads
    @Throws(DataAccessException::class)
    fun getMatch(tournament: Tournament, matchId: Long, includeAttachments: Boolean = false): Match =
            this.matches.getMatch(tournament, matchId, includeAttachments)

    /**
     * @see MatchService
     */
    @JvmOverloads
    fun getMatch(tournament: Tournament, matchId: Long, includeAttachments: Boolean = false,
                 onSuccess: Callback<Match>, onFailure: Callback<DataAccessException>) =
            this.matches.getMatch(tournament, matchId, includeAttachments, onSuccess, onFailure)

    /**
     * @see MatchService
     */
    @Throws(DataAccessException::class)
    fun updateMatch(match: Match, data: MatchQuery): Match = this.matches.updateMatch(match, data)

    /**
     * @see MatchService
     */
    fun updateMatch(match: Match, data: MatchQuery, onSuccess: Callback<Match>, onFailure: Callback<DataAccessException>) =
            this.matches.updateMatch(match, data, onSuccess, onFailure)

    /**
     * @see MatchService
     */
    @Throws(DataAccessException::class)
    fun reopenMatch(match: Match): Match = this.matches.reopenMatch(match)

    /**
     * @see MatchService
     */
    fun reopenMatch(match: Match, onSuccess: Callback<Match>, onFailure: Callback<DataAccessException>) =
            this.matches.reopenMatch(match, onSuccess, onFailure)

    /**
     * @see AttachmentService
     */
    @Throws(DataAccessException::class)
    fun getAttachments(match: Match): List<Attachment> = this.attachments.getAttachments(match)

    /**
     * @see AttachmentService
     */
    fun getAttachments(match: Match, onSuccess: Callback<List<Attachment>>, onFailure: Callback<DataAccessException>) =
            this.attachments.getAttachments(match, onSuccess, onFailure)

    /**
     * @see AttachmentService
     */
    @Throws(DataAccessException::class)
    fun getAttachment(match: Match, attachmentId: Long): Attachment =
            this.attachments.getAttachment(match, attachmentId)

    /**
     * @see AttachmentService
     */
    fun getAttachment(match: Match, attachmentId: Long, onSuccess: Callback<Attachment>, onFailure: Callback<DataAccessException>) =
            this.attachments.getAttachment(match, attachmentId, onSuccess, onFailure)

    /**
     * @see AttachmentService
     */
    @Throws(DataAccessException::class)
    fun createAttachment(match: Match, data: AttachmentQuery): Attachment =
            this.attachments.createAttachment(match, data)

    /**
     * @see AttachmentService
     */
    fun createAttachment(match: Match, data: AttachmentQuery, onSuccess: Callback<Attachment>,
                         onFailure: Callback<DataAccessException>) =
            this.attachments.createAttachment(match, data, onSuccess, onFailure)

    /**
     * @see AttachmentService
     */
    @Throws(DataAccessException::class)
    fun updateAttachment(match: Match, attachment: Attachment, data: AttachmentQuery): Attachment =
            this.attachments.updateAttachment(match, attachment, data)

    /**
     * @see AttachmentService
     */
    fun updateAttachment(match: Match, attachment: Attachment, data: AttachmentQuery, onSuccess: Callback<Attachment>,
                         onFailure: Callback<DataAccessException>) =
            this.attachments.updateAttachment(match, attachment, data, onSuccess, onFailure)

    /**
     * @see AttachmentService
     */
    @Throws(DataAccessException::class)
    fun deleteAttachment(match: Match, attachment: Attachment): Attachment =
            this.attachments.deleteAttachment(match, attachment)

    /**
     * @see AttachmentService
     */
    fun deleteAttachment(match: Match, attachment: Attachment, onSuccess: Callback<Attachment>,
                         onFailure: Callback<DataAccessException>) =
            this.attachments.deleteAttachment(match, attachment, onSuccess, onFailure)
}