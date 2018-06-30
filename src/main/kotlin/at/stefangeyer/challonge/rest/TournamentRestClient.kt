package at.stefangeyer.challonge.rest

import at.stefangeyer.challonge.model.Tournament
import at.stefangeyer.challonge.model.enumeration.TournamentType
import at.stefangeyer.challonge.model.enumeration.query.TournamentQueryState
import at.stefangeyer.challonge.model.query.TournamentQuery
import at.stefangeyer.challonge.exception.DataAccessException
import java.time.OffsetDateTime

/**
 * Tournament Rest Client Definition
 *
 * @author Stefan Geyer
 * @version 2018-06-29
 */
interface TournamentRestClient {

    /**
     * Retrieve a set of tournaments created with your account.
     *
     * @param state         only get tournaments with this state
     * @param type          only get tournaments with this type
     * @param createdAfter  get tournaments created after this date
     * @param createdBefore get tournaments created before this date
     * @param subdomain     only get tournaments with this subdomain
     * @throws DataAccessException Exchange with the rest api failed
     * @return The filtered tournaments
     */
    @Throws(DataAccessException::class)
    fun getTournaments(state: TournamentQueryState, type: TournamentType, createdAfter: OffsetDateTime,
                       createdBefore: OffsetDateTime, subdomain: String): List<Tournament>

    /**
     * Retrieve a single tournament record created with your account.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @throws DataAccessException Exchange with the rest api failed
     * @return The matching tournament
     */
    @Throws(DataAccessException::class)
    fun getTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): Tournament

    /**
     * Create a new tournament.
     *
     * @param tournamentData An object with all the necessary information to create the tournament
     * @throws DataAccessException Exchange with the rest api failed
     * @return The created tournament
     */
    @Throws(DataAccessException::class)
    fun createTournament(tournamentData: TournamentQuery): Tournament

    /**
     * Update a tournament's attributes.
     *
     * @param tournament     Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                       If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                       (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param tournamentData An object with all the necessary information to update the tournament
     * @throws DataAccessException Exchange with the rest api failed
     * @return The updated tournament
     */
    @Throws(DataAccessException::class)
    fun updateTournament(tournament: String, tournamentData: TournamentQuery): Tournament

    /**
     * Deletes a tournament along with all its associated records. There is no undo, so use with care!
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @throws DataAccessException Exchange with the rest api failed
     * @return The deleted tournament
     */
    @Throws(DataAccessException::class)
    fun deleteTournament(tournament: String): Tournament

    /**
     * This should be invoked after a tournament's check-in window closes before the tournament is started.
     *
     *  1. Marks participants who have not checked in as inactive.
     *  2. Moves inactive participants to bottom seeds (ordered by original seed).
     *  3. Transitions the tournament state from 'checking_in' to 'checked_in'
     *
     * NOTE: Checked in participants on the waiting list will be promoted if slots become available.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @throws DataAccessException Exchange with the rest api failed
     * @return The updated tournament
     */
    @Throws(DataAccessException::class)
    fun processCheckIns(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): Tournament

    /**
     * When your tournament is in a 'checking_in' or 'checked_in' state,
     * there's no way to edit the tournament's start time (start_at) or check-in duration (check_in_duration).
     * You must first abort check-in, then you may edit those attributes.
     *
     *  1. Makes all participants active and clears their checked_in_at times.
     *  2. Transitions the tournament state from 'checking_in' or 'checked_in' to 'pending'
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @throws DataAccessException Exchange with the rest api failed
     * @return The updated tournament
     */
    @Throws(DataAccessException::class)
    fun abortCheckIn(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): Tournament

    /**
     * Start a tournament, opening up first round matches for score reporting.
     * The tournament must have at least 2 participants.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @throws DataAccessException Exchange with the rest api failed
     * @return The started tournament
     */
    @Throws(DataAccessException::class)
    fun startTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): Tournament

    /**
     * Finalize a tournament that has had all match scores submitted, rendering its results permanent.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @throws DataAccessException Exchange with the rest api failed
     * @return The finalized tournament
     */
    @Throws(DataAccessException::class)
    fun finalizeTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): Tournament

    /**
     * Reset a tournament, clearing all of its scores and attachments.
     * You can then add/remove/edit participants before starting the tournament again.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @throws DataAccessException Exchange with the rest api failed
     * @return The reset tournament
     */
    @Throws(DataAccessException::class)
    fun resetTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): Tournament

    /**
     * Sets the state of the tournament to start accepting predictions.
     * Your tournament's 'prediction_method' attribute must be set to 1 (exponential scoring) or 2 (linear scoring)
     * to use this option. Note: Once open for predictions, match records will be persisted, so participant additions
     * and removals will no longer be permitted.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @throws DataAccessException Exchange with the rest api failed
     * @return The reset tournament
     */
    @Throws(DataAccessException::class)
    fun openTournamentForPredictions(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): Tournament
}