package at.stefangeyer.challonge.service

import at.stefangeyer.challonge.exception.DataAccessException
import at.stefangeyer.challonge.model.Tournament
import at.stefangeyer.challonge.model.enumeration.TournamentType
import at.stefangeyer.challonge.model.enumeration.query.TournamentQueryState
import at.stefangeyer.challonge.model.query.TournamentQuery
import java.time.OffsetDateTime

/**
 * Tournament Service Definitions
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
interface TournamentService {

    /**
     * Retrieve a set of tournaments created with your account.
     *
     * @param state         Only get tournaments with this state
     * @param type          Only get tournaments with this type
     * @param createdAfter  Get tournaments created after this date
     * @param createdBefore Get tournaments created before this date
     * @param subdomain     Only get tournaments with this subdomain
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The filtered tournaments
     */
    @Throws(DataAccessException::class)
    fun getTournaments(state: TournamentQueryState? = null, type: TournamentType? = null, createdAfter: OffsetDateTime? = null,
                       createdBefore: OffsetDateTime? = null, subdomain: String? = null): List<Tournament>

    /**
     * Retrieve a single tournament record created with your account.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The matching tournament
     */
    @Throws(DataAccessException::class)
    fun getTournament(tournament: String, includeParticipants: Boolean, includeMatches: Boolean): Tournament

    /**
     * Create a new tournament.
     *
     * @param data An object with all the necessary information to create the tournament
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The created tournament
     */
    @Throws(DataAccessException::class)
    fun createTournament(data: TournamentQuery): Tournament

    /**
     * Update a tournament's attributes.
     *
     * @param tournament     The tournament to update. Must contain id or url with an optional subdomain
     * @param data An object with all the necessary information to update the tournament
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The updated tournament
     */
    @Throws(DataAccessException::class)
    fun updateTournament(tournament: Tournament, data: TournamentQuery): Tournament

    /**
     * Deletes a tournament along with all its associated records. There is no undo, so use with care!
     *
     * @param tournament The tournament to delete. Must contain id or url with an optional subdomain
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The deleted tournament
     */
    @Throws(DataAccessException::class)
    fun deleteTournament(tournament: Tournament): Tournament

    /**
     * This should be invoked after a tournament's check-in window closes before the tournament is started.
     *
     *  1. Marks participants who have not checked in as inactive.
     *  2. Moves inactive participants to bottom seeds (ordered by original seed).
     *  3. Transitions the tournament state from 'checking_in' to 'checked_in'
     *
     * NOTE: Checked in participants on the waiting list will be promoted if slots become available.
     *
     * @param tournament          The tournament to process check ins for.
     *                            Must contain id or url with an optional subdomain
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The updated tournament
     */
    @Throws(DataAccessException::class)
    fun processCheckIns(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament

    /**
     * When your tournament is in a 'checking_in' or 'checked_in' state,
     * there's no way to edit the tournament's start time (start_at) or check-in duration (check_in_duration).
     * You must first abort check-in, then you may edit those attributes.
     *
     *  1. Makes all participants active and clears their checked_in_at times.
     *  2. Transitions the tournament state from 'checking_in' or 'checked_in' to 'pending'
     *
     * @param tournament          The tournament to abort check in for.
     *                            Must contain id or url with an optional subdomain
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The updated tournament
     */
    @Throws(DataAccessException::class)
    fun abortCheckIn(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament

    /**
     * Start a tournament, opening up first round matches for score reporting.
     * The tournament must have at least 2 participants.
     *
     * @param tournament          The tournament to start. Must contain id or url with an optional subdomain
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The started tournament
     */
    @Throws(DataAccessException::class)
    fun startTournament(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament

    /**
     * Finalize a tournament that has had all match scores submitted, rendering its results permanent.
     *
     * @param tournament          The tournament to finalize. Must contain id or url with an optional subdomain
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The finalized tournament
     */
    fun finalizeTournament(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament

    /**
     * Reset a tournament, clearing all of its scores and attachments.
     * You can then add/remove/edit participants before starting the tournament again.
     *
     * @param tournament          The tournament to reset. Must contain id or url with an optional subdomain
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The reset tournament
     */
    fun resetTournament(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament

    /**
     * Sets the state of the tournament to start accepting predictions.
     * Your tournament's 'prediction_method' attribute must be set to 1 (exponential scoring) or 2 (linear scoring)
     * to use this option. Note: Once open for predictions, match records will be persisted, so participant additions
     * and removals will no longer be permitted.
     *
     * @param tournament          The tournament to open predictions for.
     *                            Must contain id or url with an optional subdomain
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The reset tournament
     */
    fun openTournamentForPredictions(tournament: Tournament, includeParticipants: Boolean, includeMatches: Boolean): Tournament
}