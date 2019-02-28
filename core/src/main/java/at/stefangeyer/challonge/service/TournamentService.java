package at.stefangeyer.challonge.service;

import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.enumeration.TournamentType;
import at.stefangeyer.challonge.model.query.TournamentQuery;
import at.stefangeyer.challonge.model.query.enumeration.TournamentQueryState;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Tournament Service Definitions
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
public interface TournamentService {

    /**
     * Retrieve a set of tournaments created with your account.
     *
     * @param state         Only get tournaments with this state
     * @param type          Only get tournaments with this type
     * @param createdAfter  Get tournaments created after this date
     * @param createdBefore Get tournaments created before this date
     * @param subdomain     Only get tournaments with this subdomain
     * @return The filtered tournaments
     * @throws DataAccessException Exchange with the rest api or validation failed
     */
    List<Tournament> getTournaments(TournamentQueryState state, TournamentType type, OffsetDateTime createdAfter,
                                    OffsetDateTime createdBefore, String subdomain) throws DataAccessException;

    /**
     * Retrieve a set of tournaments created with your account.
     *
     * @param state         Only get tournaments with this state
     * @param type          Only get tournaments with this type
     * @param createdAfter  Get tournaments created after this date
     * @param createdBefore Get tournaments created before this date
     * @param subdomain     Only get tournaments with this subdomain
     * @param onSuccess     Called with result if call was successful
     * @param onFailure     Called with exception if call was not successful
     */
    void getTournaments(TournamentQueryState state, TournamentType type, OffsetDateTime createdAfter,
                        OffsetDateTime createdBefore, String subdomain, Callback<List<Tournament>> onSuccess,
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
     * @throws DataAccessException Exchange with the rest api or validation failed
     */
    Tournament getTournament(String tournament, boolean includeParticipants, boolean includeMatches) throws DataAccessException;

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
                       Callback<Tournament> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * Create a new tournament.
     *
     * @param data An object with all the necessary information to create the tournament
     * @return The created tournament
     * @throws DataAccessException Exchange with the rest api or validation failed
     */
    Tournament createTournament(TournamentQuery data) throws DataAccessException;

    /**
     * Create a new tournament.
     *
     * @param data      An object with all the necessary information to create the tournament
     * @param onSuccess Called with result if call was successful
     * @param onFailure Called with exception if call was not successful
     */
    void createTournament(TournamentQuery data, Callback<Tournament> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * Update a tournament's attributes.
     *
     * @param tournament The tournament to update. Must contain tournament id
     * @param data       An object with all the necessary information to update the tournament
     * @return The updated tournament
     * @throws DataAccessException Exchange with the rest api or validation failed
     */
    Tournament updateTournament(Tournament tournament, TournamentQuery data) throws DataAccessException;

    /**
     * Update a tournament's attributes.
     *
     * @param tournament The tournament to update. Must contain tournament id
     * @param data       An object with all the necessary information to update the tournament
     * @param onSuccess  Called with result if call was successful
     * @param onFailure  Called with exception if call was not successful
     */
    void updateTournament(Tournament tournament, TournamentQuery data, Callback<Tournament> onSuccess,
                          Callback<DataAccessException> onFailure);

    /**
     * Deletes a tournament along with all its associated records. There is no undo, so use with care!
     *
     * @param tournament The tournament to delete. Must contain tournament id
     * @return The deleted tournament
     * @throws DataAccessException Exchange with the rest api or validation failed
     */
    Tournament deleteTournament(Tournament tournament) throws DataAccessException;

    /**
     * Deletes a tournament along with all its associated records. There is no undo, so use with care!
     *
     * @param tournament The tournament to delete. Must contain tournament id
     * @param onSuccess  Called with result if call was successful
     * @param onFailure  Called with exception if call was not successful
     */
    void deleteTournament(Tournament tournament, Callback<Tournament> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * This should be invoked after a tournament's check-in window closes before the tournament is started.
     * <p>
     * 1. Marks participants who have not checked in as inactive.
     * 2. Moves inactive participants to bottom seeds (ordered by original seed).
     * 3. Transitions the tournament state from 'checking_in' to 'checked_in'
     * <p>
     * NOTE: Checked in participants on the waiting list will be promoted if slots become available.
     *
     * @param tournament          The tournament to process check ins for. Must contain tournament id
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @return The updated tournament
     * @throws DataAccessException Exchange with the rest api or validation failed
     */
    Tournament processCheckIns(Tournament tournament, boolean includeParticipants,
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
     * @param tournament          The tournament to process check ins for. Must contain tournament id
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @param onSuccess           Called with result if call was successful
     * @param onFailure           Called with exception if call was not successful
     */
    void processCheckIns(Tournament tournament, boolean includeParticipants, boolean includeMatches,
                         Callback<Tournament> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * When your tournament is in a 'checking_in' or 'checked_in' state,
     * there's no way to edit the tournament's start time (start_at) or check-in duration (check_in_duration).
     * You must first abort check-in, then you may edit those attributes.
     * <p>
     * 1. Makes all participants active and clears their checked_in_at times.
     * 2. Transitions the tournament state from 'checking_in' or 'checked_in' to 'pending'
     *
     * @param tournament          The tournament to abort check in for. Must contain tournament id
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @return The updated tournament
     * @throws DataAccessException Exchange with the rest api or validation failed
     */
    Tournament abortCheckIn(Tournament tournament, boolean includeParticipants,
                            boolean includeMatches) throws DataAccessException;

    /**
     * When your tournament is in a 'checking_in' or 'checked_in' state,
     * there's no way to edit the tournament's start time (start_at) or check-in duration (check_in_duration).
     * You must first abort check-in, then you may edit those attributes.
     * <p>
     * 1. Makes all participants active and clears their checked_in_at times.
     * 2. Transitions the tournament state from 'checking_in' or 'checked_in' to 'pending'
     *
     * @param tournament          The tournament to abort check in for. Must contain tournament id
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @param onSuccess           Called with result if call was successful
     * @param onFailure           Called with exception if call was not successful
     */
    void abortCheckIn(Tournament tournament, boolean includeParticipants, boolean includeMatches,
                      Callback<Tournament> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * Start a tournament, opening up first round matches for score reporting.
     * The tournament must have at least 2 participants.
     *
     * @param tournament          The tournament to start. Must contain tournament id
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @return The started tournament
     * @throws DataAccessException Exchange with the rest api or validation failed
     */
    Tournament startTournament(Tournament tournament, boolean includeParticipants,
                               boolean includeMatches) throws DataAccessException;

    /**
     * Start a tournament, opening up first round matches for score reporting.
     * The tournament must have at least 2 participants.
     *
     * @param tournament          The tournament to start. Must contain tournament id
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @param onSuccess           Called with result if call was successful
     * @param onFailure           Called with exception if call was not successful
     */
    void startTournament(Tournament tournament, boolean includeParticipants, boolean includeMatches,
                         Callback<Tournament> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * Finalize a tournament that has had all match scores submitted, rendering its results permanent.
     *
     * @param tournament          The tournament to finalize. Must contain tournament id
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @return The finalized tournament
     * @throws DataAccessException Exchange with the rest api or validation failed
     */
    Tournament finalizeTournament(Tournament tournament, boolean includeParticipants,
                                  boolean includeMatches) throws DataAccessException;

    /**
     * Finalize a tournament that has had all match scores submitted, rendering its results permanent.
     *
     * @param tournament          The tournament to finalize. Must contain tournament id
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @param onSuccess           Called with result if call was successful
     * @param onFailure           Called with exception if call was not successful
     */
    void finalizeTournament(Tournament tournament, boolean includeParticipants, boolean includeMatches,
                            Callback<Tournament> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * Reset a tournament, clearing all of its scores and attachments.
     * You can then add/remove/edit participants before starting the tournament again.
     *
     * @param tournament          The tournament to reset. Must contain tournament id
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @return The reset tournament
     * @throws DataAccessException Exchange with the rest api or validation failed
     */
    Tournament resetTournament(Tournament tournament, boolean includeParticipants,
                               boolean includeMatches) throws DataAccessException;

    /**
     * Reset a tournament, clearing all of its scores and attachments.
     * You can then add/remove/edit participants before starting the tournament again.
     *
     * @param tournament          The tournament to reset. Must contain tournament id
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @param onSuccess           Called with result if call was successful
     * @param onFailure           Called with exception if call was not successful
     */
    void resetTournament(Tournament tournament, boolean includeParticipants, boolean includeMatches,
                         Callback<Tournament> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * Sets the state of the tournament to start accepting predictions.
     * Your tournament's 'prediction_method' attribute must be set to 1 (exponential scoring) or 2 (linear scoring)
     * to use this option. Note: Once open for predictions, match records will be persisted, so participant additions
     * and removals will no longer be permitted.
     *
     * @param tournament          The tournament to open predictions for. Must contain tournament id
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @return The reset tournament
     * @throws DataAccessException Exchange with the rest api or validation failed
     */
    Tournament openTournamentForPredictions(Tournament tournament, boolean includeParticipants,
                                            boolean includeMatches) throws DataAccessException;

    /**
     * Sets the state of the tournament to start accepting predictions.
     * Your tournament's 'prediction_method' attribute must be set to 1 (exponential scoring) or 2 (linear scoring)
     * to use this option. Note: Once open for predictions, match records will be persisted, so participant additions
     * and removals will no longer be permitted.
     *
     * @param tournament          The tournament to open predictions for. Must contain tournament id
     * @param includeParticipants Include a list of participants in the response
     * @param includeMatches      Include a list of matches in the response
     * @param onSuccess           Called with result if call was successful
     * @param onFailure           Called with exception if call was not successful
     */
    void openTournamentForPredictions(Tournament tournament, boolean includeParticipants, boolean includeMatches,
                                      Callback<Tournament> onSuccess, Callback<DataAccessException> onFailure);
}
