package at.stefangeyer.challonge.rest

import at.stefangeyer.challonge.model.Participant
import at.stefangeyer.challonge.model.query.ParticipantQuery
import at.stefangeyer.challonge.exception.DataAccessException

/**
 * Participant Rest Client Definition
 *
 * @author Stefan Geyer
 * @version 2018-06-29
 */
interface ParticipantRestClient {

    /**
     * Retrieve a tournament's participant list.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @throws DataAccessException Exchange with the rest api failed
     * @return The tournaments participants
     */
    @Throws(DataAccessException::class)
    fun getParticipants(tournament: String): List<Participant>

    /**
     * Retrieve a single participant record for a tournament.
     *
     * @param tournament     Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                       If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                       (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId  The participant's unique ID
     * @param includeMatches Includes an array of associated match records
     * @throws DataAccessException Exchange with the rest api failed
     * @return The requested participant
     */
    @Throws(DataAccessException::class)
    fun getParticipant(tournament: String, participantId: Long, includeMatches: Boolean): Participant

    /**
     * Add a participant to a tournament (up until it is started).
     *
     * @param tournament  Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                    If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                    (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participant The participant data
     * @throws DataAccessException Exchange with the rest api failed
     * @return The added participant
     */
    @Throws(DataAccessException::class)
    fun addParticipant(tournament: String, participant: ParticipantQuery): Participant

    /**
     * Bulk add participants to a tournament (up until it is started).
     * If an invalid participant is detected, bulk participant creation will halt
     * and any previously added participants (from this API request) will be rolled back.
     *
     * @param tournament   Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                     If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                     (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participants The participant data
     * @throws DataAccessException Exchange with the rest api failed
     * @return The added participants
     */
    @Throws(DataAccessException::class)
    fun bulkAddParticipants(tournament: String, participants: List<ParticipantQuery>): List<Participant>

    /**
     * Update the attributes of a tournament participant.
     *
     * @param tournament    Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                      If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                      (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId The participant's unique ID
     * @param participant   The participant data
     * @throws DataAccessException Exchange with the rest api failed
     * @return The updates participant
     */
    @Throws(DataAccessException::class)
    fun updateParticipant(tournament: String, participantId: Long, participant: ParticipantQuery): Participant

    /**
     * Checks a participant in, setting checked_in_at to the current time.
     *
     * @param tournament    Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                      If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                      (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId The participant's unique ID
     * @throws DataAccessException Exchange with the rest api failed
     * @return The checked in participant
     */
    @Throws(DataAccessException::class)
    fun checkInParticipant(tournament: String, participantId: Long): Participant

    /**
     * Marks a participant as having not checked in, setting checked_in_at to nil.
     *
     * @param tournament    Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                      If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                      (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId The participant's unique ID
     * @throws DataAccessException Exchange with the rest api failed
     * @return The checked out participant
     */
    @Throws(DataAccessException::class)
    fun undoCheckInParticipant(tournament: String, participantId: Long): Participant

    /**
     * If the tournament has not started, delete a participant, automatically filling in the abandoned seed number.
     * If tournament is underway, mark a participant inactive, automatically forfeiting his/her remaining matches.
     *
     * @param tournament    Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                      If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                      (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId The participant's unique ID
     * @throws DataAccessException Exchange with the rest api failed
     * @return The deleted participant
     */
    @Throws(DataAccessException::class)
    fun deleteParticipant(tournament: String, participantId: Long): Participant

    /**
     * Randomize seeds among participants. Only applicable before a tournament has started.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @throws DataAccessException Exchange with the rest api failed
     * @return The randomized participants
     */
    @Throws(DataAccessException::class)
    fun randomizeParticipants(tournament: String): List<Participant>
}