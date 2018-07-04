package at.stefangeyer.challonge.service

import at.stefangeyer.challonge.async.Callback
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
     * @param tournament The tournament to get the participants from. Must contain tournament id
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The tournaments participants
     */
    @Throws(DataAccessException::class)
    fun getParticipants(tournament: Tournament): List<Participant>

    /**
     * Retrieve a tournament's participant list.
     *
     * @param tournament The tournament to get the participants from. Must contain tournament id
     * @param onSuccess  Called with result if call was successful
     * @param onFailure  Called with exception if call was not successful
     */
    fun getParticipants(tournament: Tournament, onSuccess: Callback<List<Participant>>, onFailure: Callback<DataAccessException>)

    /**
     * Retrieve a single participant record for a tournament.
     *
     * @param tournament     The tournament to get the participant from. Must contain tournament id
     * @param participantId  The participant's unique ID
     * @param includeMatches Includes an array of associated match records
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The requested participant
     */
    @Throws(DataAccessException::class)
    fun getParticipant(tournament: Tournament, participantId: Long, includeMatches: Boolean = false): Participant

    /**
     * Retrieve a single participant record for a tournament.
     *
     * @param tournament     The tournament to get the participant from. Must contain tournament id
     * @param participantId  The participant's unique ID
     * @param includeMatches Includes an array of associated match records
     * @param onSuccess      Called with result if call was successful
     * @param onFailure      Called with exception if call was not successful
     */
    fun getParticipant(tournament: Tournament, participantId: Long, includeMatches: Boolean = false,
                       onSuccess: Callback<Participant>, onFailure: Callback<DataAccessException>)

    /**
     * Add a participant to a tournament (up until it is started).
     *
     * @param tournament  The tournament to add the participant to. Must contain tournament id
     * @param data        The participant data
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The added participant
     */
    @Throws(DataAccessException::class)
    fun addParticipant(tournament: Tournament, data: ParticipantQuery): Participant

    /**
     * Add a participant to a tournament (up until it is started).
     *
     * @param tournament  The tournament to add the participant to. Must contain tournament id
     * @param data        The participant data
     * @param onSuccess   Called with result if call was successful
     * @param onFailure   Called with exception if call was not successful
     */
    fun addParticipant(tournament: Tournament, data: ParticipantQuery, onSuccess: Callback<Participant>,
                       onFailure: Callback<DataAccessException>)

    /**
     * Bulk add participants to a tournament (up until it is started).
     * If an invalid participant is detected, bulk participant creation will halt
     * and any previously added participants (from this API request) will be rolled back.
     *
     * @param tournament The tournament to add the participants to. Must contain tournament id
     * @param data       The participant data
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The added participants
     */
    @Throws(DataAccessException::class)
    fun bulkAddParticipants(tournament: Tournament, data: List<ParticipantQuery>): List<Participant>

    /**
     * Bulk add participants to a tournament (up until it is started).
     * If an invalid participant is detected, bulk participant creation will halt
     * and any previously added participants (from this API request) will be rolled back.
     *
     * @param tournament The tournament to add the participants to. Must contain tournament id
     * @param data       The participant data
     * @param onSuccess  Called with result if call was successful
     * @param onFailure  Called with exception if call was not successful
     */
    fun bulkAddParticipants(tournament: Tournament, data: List<ParticipantQuery>, onSuccess: Callback<List<Participant>>,
                            onFailure: Callback<DataAccessException>)

    /**
     * Update the attributes of a tournament participant.
     *
     * @param participant The participant to update. Must contain the tournament id and the participant's id
     * @param data        The participant data
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The updates participant
     */
    @Throws(DataAccessException::class)
    fun updateParticipant(participant: Participant, data: ParticipantQuery): Participant

    /**
     * Update the attributes of a tournament participant.
     *
     * @param participant The participant to update. Must contain the tournament id and the participant's id
     * @param data        The participant data
     * @param onSuccess  Called with result if call was successful
     * @param onFailure  Called with exception if call was not successful
     */
    fun updateParticipant(participant: Participant, data: ParticipantQuery, onSuccess: Callback<Participant>,
                          onFailure: Callback<DataAccessException>)

    /**
     * Checks a participant in, setting checked_in_at to the current time.
     *
     * @param participant The participant to check in. Must contain the tournament id and the participant's id
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The checked in participant
     */
    @Throws(DataAccessException::class)
    fun checkInParticipant(participant: Participant): Participant

    /**
     * Checks a participant in, setting checked_in_at to the current time.
     *
     * @param participant The participant to check in. Must contain the tournament id and the participant's id
     * @param onSuccess   Called with result if call was successful
     * @param onFailure   Called with exception if call was not successful
     */
    fun checkInParticipant(participant: Participant, onSuccess: Callback<Participant>, onFailure: Callback<DataAccessException>)

    /**
     * Marks a participant as having not checked in, setting checked_in_at to nil.
     *
     * @param participant The participant to check in. Must contain the tournament id and the participant's id
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The checked out participant
     */
    @Throws(DataAccessException::class)
    fun undoCheckInParticipant(participant: Participant): Participant

    /**
     * Marks a participant as having not checked in, setting checked_in_at to nil.
     *
     * @param participant The participant to check in. Must contain the tournament id and the participant's id
     * @param onSuccess   Called with result if call was successful
     * @param onFailure   Called with exception if call was not successful
     */
    fun undoCheckInParticipant(participant: Participant, onSuccess: Callback<Participant>, onFailure: Callback<DataAccessException>)

    /**
     * If the tournament has not started, delete a participant, automatically filling in the abandoned seed number.
     * If tournament is underway, mark a participant inactive, automatically forfeiting his/her remaining matches.
     *
     * @param participant The participant to delete. Must contain the tournament id and the participant's id
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The deleted participant
     */
    @Throws(DataAccessException::class)
    fun deleteParticipant(participant: Participant): Participant

    /**
     * If the tournament has not started, delete a participant, automatically filling in the abandoned seed number.
     * If tournament is underway, mark a participant inactive, automatically forfeiting his/her remaining matches.
     *
     * @param participant The participant to delete. Must contain the tournament id and the participant's id
     * @param onSuccess   Called with result if call was successful
     * @param onFailure   Called with exception if call was not successful
     */
    fun deleteParticipant(participant: Participant, onSuccess: Callback<Participant>, onFailure: Callback<DataAccessException>)

    /**
     * Randomize seeds among participants. Only applicable before a tournament has started.
     *
     * @param tournament The tournament to randomize. Must contain the tournament id
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The randomized participants
     */
    @Throws(DataAccessException::class)
    fun randomizeParticipants(tournament: Tournament): List<Participant>

    /**
     * Randomize seeds among participants. Only applicable before a tournament has started.
     *
     * @param tournament The tournament to randomize. Must contain the tournament id
     * @param onSuccess  Called with result if call was successful
     * @param onFailure  Called with exception if call was not successful
     */
    fun randomizeParticipants(tournament: Tournament, onSuccess: Callback<List<Participant>>, onFailure: Callback<DataAccessException>)
}