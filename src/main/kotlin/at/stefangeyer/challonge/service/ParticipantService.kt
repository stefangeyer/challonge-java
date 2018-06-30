package at.stefangeyer.challonge.service

import at.stefangeyer.challonge.exception.DataAccessException
import at.stefangeyer.challonge.model.Participant
import at.stefangeyer.challonge.model.Tournament
import at.stefangeyer.challonge.model.query.ParticipantQuery

/**
 * Participant Service Definitions
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
interface ParticipantService {

    /**
     * Retrieve a tournament's participant list.
     *
     * @param tournament The tournament to get the participants from. Must contain id or url with an optional subdomain
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The tournaments participants
     */
    @Throws(DataAccessException::class)
    fun getParticipants(tournament: Tournament): List<Participant>

    /**
     * Retrieve a single participant record for a tournament.
     *
     * @param tournament     The tournament to get the participant from. Must contain id or url with an optional subdomain
     * @param participantId  The participant's unique ID
     * @param includeMatches Includes an array of associated match records
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The requested participant
     */
    @Throws(DataAccessException::class)
    fun getParticipant(tournament: Tournament, participantId: Long, includeMatches: Boolean): Participant

    /**
     * Add a participant to a tournament (up until it is started).
     *
     * @param tournament  The tournament to add the participant to. Must contain id or url with an optional subdomain
     * @param data        The participant data
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The added participant
     */
    @Throws(DataAccessException::class)
    fun addParticipant(tournament: Tournament, data: ParticipantQuery): Participant

    /**
     * Bulk add participants to a tournament (up until it is started).
     * If an invalid participant is detected, bulk participant creation will halt
     * and any previously added participants (from this API request) will be rolled back.
     *
     * @param tournament The tournament to add the participants to. Must contain id or url with an optional subdomain
     * @param data       The participant data
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The added participants
     */
    @Throws(DataAccessException::class)
    fun bulkAddParticipants(tournament: Tournament, data: List<ParticipantQuery>): List<Participant>

    /**
     * Update the attributes of a tournament participant.
     *
     * @param participant The participant to update. Must contain the tournament id and
     *                    the id of the participant
     * @param data        The participant data
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The updates participant
     */
    @Throws(DataAccessException::class)
    fun updateParticipant(participant: Participant, data: ParticipantQuery): Participant

    /**
     * Checks a participant in, setting checked_in_at to the current time.
     *
     * @param participant The participant to check in. Must contain the tournament id and
     *                    the id of the participant
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The checked in participant
     */
    @Throws(DataAccessException::class)
    fun checkInParticipant(participant: Participant): Participant

    /**
     * Marks a participant as having not checked in, setting checked_in_at to nil.
     *
     * @param participant The participant to check in. Must contain the tournament id and
     *                    the id of the participant
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The checked out participant
     */
    @Throws(DataAccessException::class)
    fun undoCheckInParticipant(participant: Participant): Participant

    /**
     * If the tournament has not started, delete a participant, automatically filling in the abandoned seed number.
     * If tournament is underway, mark a participant inactive, automatically forfeiting his/her remaining matches.
     *
     * @param participant The participant to delete. Must contain the tournament id and
     *                    the id of the participant
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The deleted participant
     */
    @Throws(DataAccessException::class)
    fun deleteParticipant(participant: Participant): Participant

    /**
     * Randomize seeds among participants. Only applicable before a tournament has started.
     *
     * @param tournament The tournament to randomize
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The randomized participants
     */
    @Throws(DataAccessException::class)
    fun randomizeParticipants(tournament: Tournament): List<Participant>
}