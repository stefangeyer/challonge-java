package at.stefangeyer.challonge.rest;

import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.enumeration.TournamentType;
import at.stefangeyer.challonge.model.query.enumeration.TournamentQueryState;
import at.stefangeyer.challonge.model.query.wrapper.TournamentQueryWrapper;
import at.stefangeyer.challonge.model.wrapper.TournamentWrapper;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Tournament Rest Client Definition
 *
 * @author Stefan Geyer
 * @version 2018-06-29
 */
public interface TournamentRestClient {

    /**
     * Retrieve a set of tournaments created with your account.
     *
     * @param state         only get tournaments with this state
     * @param type          only get tournaments with this type
     * @param createdAfter  get tournaments created after this date
     * @param createdBefore get tournaments created before this date
     * @param subdomain     only get tournaments with this subdomain
     * @return The filtered tournaments
     * @throws DataAccessException Exchange with the rest api failed
     */
    List<TournamentWrapper> getTournaments(TournamentQueryState state, TournamentType type, OffsetDateTime createdAfter,
                                           OffsetDateTime createdBefore, String subdomain) throws DataAccessException;

    /**
     * Retrieve a set of tournaments created with your account.
     *
     * @param state         only get tournaments with this state
     * @param type          only get tournaments with this type
     * @param createdAfter  get tournaments created after this date
     * @param createdBefore get tournaments created before this date
     * @param subdomain     only get tournaments with this subdomain
     * @param onSuccess     Called with result if call was successful
     * @param onFailure     Called with exception if call was not successful
     */
    void getTournaments(TournamentQueryState state, TournamentType type, OffsetDateTime createdAfter,
                        OffsetDateTime createdBefore, String subdomain, Callback<List<TournamentWrapper>> onSuccess,
                        Callback<DataAccessException> onFailure);

    /**
     * Retrieve a single tournament record created with your account.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @return The matching tournament
     * @throws DataAccessException Exchange with the rest api failed
     */
    TournamentWrapper getTournament(String tournament, boolean includeParticipants,
                                    boolean includeMatches) throws DataAccessException;

    /**
     * Retrieve a single tournament record created with your account.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @param onSuccess           Called with result if call was successful
     * @param onFailure           Called with exception if call was not successful
     */
    void getTournament(String tournament, boolean includeParticipants, boolean includeMatches,
                       Callback<TournamentWrapper> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * Create a new tournament.
     *
     * @param tournamentData An object with all the necessary information to create the tournament
     * @return The created tournament
     * @throws DataAccessException Exchange with the rest api failed
     */
    TournamentWrapper createTournament(TournamentQueryWrapper tournamentData) throws DataAccessException;

    /**
     * Create a new tournament.
     *
     * @param tournamentData An object with all the necessary information to create the tournament
     * @param onSuccess      Called with result if call was successful
     * @param onFailure      Called with exception if call was not successful
     */
    void createTournament(TournamentQueryWrapper tournamentData, Callback<TournamentWrapper> onSuccess,
                          Callback<DataAccessException> onFailure);

    /**
     * Update a tournament's attributes.
     *
     * @param tournament     Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                       If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                       (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param tournamentData An object with all the necessary information to update the tournament
     * @return The updated tournament
     * @throws DataAccessException Exchange with the rest api failed
     */
    TournamentWrapper updateTournament(String tournament, TournamentQueryWrapper tournamentData) throws DataAccessException;

    /**
     * Update a tournament's attributes.
     *
     * @param tournament     Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                       If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                       (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param tournamentData An object with all the necessary information to update the tournament
     * @param onSuccess      Called with result if call was successful
     * @param onFailure      Called with exception if call was not successful
     */
    void updateTournament(String tournament, TournamentQueryWrapper tournamentData, Callback<TournamentWrapper> onSuccess,
                          Callback<DataAccessException> onFailure);

    /**
     * Deletes a tournament along with all its associated records. There is no undo, so use with care!
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @return The deleted tournament
     * @throws DataAccessException Exchange with the rest api failed
     */
    TournamentWrapper deleteTournament(String tournament) throws DataAccessException;

    /**
     * Deletes a tournament along with all its associated records. There is no undo, so use with care!
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param onSuccess  Called with result if call was successful
     * @param onFailure  Called with exception if call was not successful
     */
    void deleteTournament(String tournament, Callback<TournamentWrapper> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * This should be invoked after a tournament's check-in window closes before the tournament is started.
     * <p>
     * 1. Marks participants who have not checked in as inactive.
     * 2. Moves inactive participants to bottom seeds (ordered by original seed).
     * 3. Transitions the tournament state from 'checking_in' to 'checked_in'
     * <p>
     * NOTE: Checked in participants on the waiting list will be promoted if slots become available.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @return The updated tournament
     * @throws DataAccessException Exchange with the rest api failed
     */
    TournamentWrapper processCheckIns(String tournament, boolean includeParticipants,
                                      boolean includeMatches) throws DataAccessException;

    /**
     * This should be invoked after a tournament's check-in window closes before the tournament is started.
     * <p>
     * 1. Marks participants who have not checked in as inactive.
     * 2. Moves inactive participants to bottom seeds (ordered by original seed).
     * 3. Transitions the tournament state from 'checking_in' to 'checked_in'
     * <p>
     * NOTE: Checked in participants on the waiting list will be promoted if slots become available.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @param onSuccess           Called with result if call was successful
     * @param onFailure           Called with exception if call was not successful
     */
    void processCheckIns(String tournament, boolean includeParticipants, boolean includeMatches,
                         Callback<TournamentWrapper> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * When your tournament is in a 'checking_in' or 'checked_in' state,
     * there's no way to edit the tournament's start time (start_at) or check-in duration (check_in_duration).
     * You must first abort check-in, then you may edit those attributes.
     * <p>
     * 1. Makes all participants active and clears their checked_in_at times.
     * 2. Transitions the tournament state from 'checking_in' or 'checked_in' to 'pending'
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @return The updated tournament
     * @throws DataAccessException Exchange with the rest api failed
     */
    TournamentWrapper abortCheckIn(String tournament, boolean includeParticipants,
                                   boolean includeMatches) throws DataAccessException;

    /**
     * When your tournament is in a 'checking_in' or 'checked_in' state,
     * there's no way to edit the tournament's start time (start_at) or check-in duration (check_in_duration).
     * You must first abort check-in, then you may edit those attributes.
     * <p>
     * 1. Makes all participants active and clears their checked_in_at times.
     * 2. Transitions the tournament state from 'checking_in' or 'checked_in' to 'pending'
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @param onSuccess           Called with result if call was successful
     * @param onFailure           Called with exception if call was not successful
     */
    void abortCheckIn(String tournament, boolean includeParticipants, boolean includeMatches,
                      Callback<TournamentWrapper> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * Start a tournament, opening up first round matches for score reporting.
     * The tournament must have at least 2 participants.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @return The started tournament
     * @throws DataAccessException Exchange with the rest api failed
     */
    TournamentWrapper startTournament(String tournament, boolean includeParticipants,
                                      boolean includeMatches) throws DataAccessException;

    /**
     * Start a tournament, opening up first round matches for score reporting.
     * The tournament must have at least 2 participants.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @param onSuccess           Called with result if call was successful
     * @param onFailure           Called with exception if call was not successful
     */
    void startTournament(String tournament, boolean includeParticipants, boolean includeMatches,
                         Callback<TournamentWrapper> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * Finalize a tournament that has had all match scores submitted, rendering its results permanent.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @return The finalized tournament
     * @throws DataAccessException Exchange with the rest api failed
     */
    TournamentWrapper finalizeTournament(String tournament, boolean includeParticipants,
                                         boolean includeMatches) throws DataAccessException;

    /**
     * Finalize a tournament that has had all match scores submitted, rendering its results permanent.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @param onSuccess           Called with result if call was successful
     * @param onFailure           Called with exception if call was not successful
     */
    void finalizeTournament(String tournament, boolean includeParticipants, boolean includeMatches,
                            Callback<TournamentWrapper> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * Reset a tournament, clearing all of its scores and attachments.
     * You can then add/remove/edit participants before starting the tournament again.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @return The reset tournament
     * @throws DataAccessException Exchange with the rest api failed
     */
    TournamentWrapper resetTournament(String tournament, boolean includeParticipants,
                                      boolean includeMatches) throws DataAccessException;

    /**
     * Reset a tournament, clearing all of its scores and attachments.
     * You can then add/remove/edit participants before starting the tournament again.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @param onSuccess           Called with result if call was successful
     * @param onFailure           Called with exception if call was not successful
     */
    void resetTournament(String tournament, boolean includeParticipants, boolean includeMatches,
                         Callback<TournamentWrapper> onSuccess, Callback<DataAccessException> onFailure);

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
     * @return The reset tournament
     * @throws DataAccessException Exchange with the rest api failed
     */
    TournamentWrapper openTournamentForPredictions(String tournament, boolean includeParticipants,
                                                   boolean includeMatches) throws DataAccessException;

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
     * @param onSuccess           Called with result if call was successful
     * @param onFailure           Called with exception if call was not successful
     */
    void openTournamentForPredictions(String tournament, boolean includeParticipants, boolean includeMatches,
                                      Callback<TournamentWrapper> onSuccess, Callback<DataAccessException> onFailure);
}
