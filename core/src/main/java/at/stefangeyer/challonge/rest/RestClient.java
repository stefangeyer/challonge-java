package at.stefangeyer.challonge.rest;

import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Credentials;
import at.stefangeyer.challonge.model.enumeration.MatchState;
import at.stefangeyer.challonge.model.enumeration.TournamentType;
import at.stefangeyer.challonge.model.query.AttachmentQuery;
import at.stefangeyer.challonge.model.query.enumeration.TournamentQueryState;
import at.stefangeyer.challonge.model.query.wrapper.MatchQueryWrapper;
import at.stefangeyer.challonge.model.query.wrapper.ParticipantQueryListWrapper;
import at.stefangeyer.challonge.model.query.wrapper.ParticipantQueryWrapper;
import at.stefangeyer.challonge.model.query.wrapper.TournamentQueryWrapper;
import at.stefangeyer.challonge.model.wrapper.AttachmentWrapper;
import at.stefangeyer.challonge.model.wrapper.MatchWrapper;
import at.stefangeyer.challonge.model.wrapper.ParticipantWrapper;
import at.stefangeyer.challonge.model.wrapper.TournamentWrapper;
import at.stefangeyer.challonge.serializer.Serializer;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Challonge Rest Client Definition
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
public interface RestClient {

    /**
     * Initializes the factory with the necessary dependencies
     *
     * @param credentials The username and api key to use
     * @param serializer  The serializer to use
     */
    void initialize(Credentials credentials, Serializer serializer);

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

    /**
     * Retrieve a tournament's participant list.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @return The tournaments participants
     * @throws DataAccessException Exchange with the rest api failed
     */
    List<ParticipantWrapper> getParticipants(String tournament) throws DataAccessException;

    /**
     * Retrieve a tournament's participant list.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param onSuccess  Called with result if call was successful
     * @param onFailure  Called with exception if call was not successful
     */
    void getParticipants(String tournament, Callback<List<ParticipantWrapper>> onSuccess,
                         Callback<DataAccessException> onFailure);

    /**
     * Retrieve a single participant record for a tournament.
     *
     * @param tournament     Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                       If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                       (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId  The participant's unique ID
     * @param includeMatches Includes an array of associated match records
     * @return The requested participant
     * @throws DataAccessException Exchange with the rest api failed
     */
    ParticipantWrapper getParticipant(String tournament, long participantId,
                                      boolean includeMatches) throws DataAccessException;

    /**
     * Retrieve a single participant record for a tournament.
     *
     * @param tournament     Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                       If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                       (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId  The participant's unique ID
     * @param includeMatches Includes an array of associated match records
     * @param onSuccess      Called with result if call was successful
     * @param onFailure      Called with exception if call was not successful
     */
    void getParticipant(String tournament, long participantId, boolean includeMatches,
                        Callback<ParticipantWrapper> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * Add a participant to a tournament (up until it is started).
     *
     * @param tournament  Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                    If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                    (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participant The participant data
     * @return The added participant
     * @throws DataAccessException Exchange with the rest api failed
     */
    ParticipantWrapper addParticipant(String tournament, ParticipantQueryWrapper participant) throws DataAccessException;

    /**
     * Add a participant to a tournament (up until it is started).
     *
     * @param tournament  Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                    If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                    (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participant The participant data
     * @param onSuccess   Called with result if call was successful
     * @param onFailure   Called with exception if call was not successful
     */
    void addParticipant(String tournament, ParticipantQueryWrapper participant, Callback<ParticipantWrapper> onSuccess,
                        Callback<DataAccessException> onFailure);

    /**
     * Bulk add participants to a tournament (up until it is started).
     * If an invalid participant is detected, bulk participant creation will halt
     * and any previously added participants (from this API request) will be rolled back.
     *
     * @param tournament   Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                     If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                     (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participants The participant data
     * @return The added participants
     * @throws DataAccessException Exchange with the rest api failed
     */
    List<ParticipantWrapper> bulkAddParticipants(String tournament, ParticipantQueryListWrapper participants) throws DataAccessException;

    /**
     * Bulk add participants to a tournament (up until it is started).
     * If an invalid participant is detected, bulk participant creation will halt
     * and any previously added participants (from this API request) will be rolled back.
     *
     * @param tournament   Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                     If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                     (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participants The participant data
     * @param onSuccess    Called with result if call was successful
     * @param onFailure    Called with exception if call was not successful
     */
    void bulkAddParticipants(String tournament, ParticipantQueryListWrapper participants,
                             Callback<List<ParticipantWrapper>> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * Update the attributes of a tournament participant.
     *
     * @param tournament    Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                      If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                      (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId The participant's unique ID
     * @param participant   The participant data
     * @return The updates participant
     * @throws DataAccessException Exchange with the rest api failed
     */
    ParticipantWrapper updateParticipant(String tournament, long participantId,
                                         ParticipantQueryWrapper participant) throws DataAccessException;

    /**
     * Update the attributes of a tournament participant.
     *
     * @param tournament    Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                      If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                      (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId The participant's unique ID
     * @param participant   The participant data
     * @param onSuccess     Called with result if call was successful
     * @param onFailure     Called with exception if call was not successful
     */
    void updateParticipant(String tournament, long participantId, ParticipantQueryWrapper participant,
                           Callback<ParticipantWrapper> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * Checks a participant in, setting checked_in_at to the current time.
     *
     * @param tournament    Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                      If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                      (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId The participant's unique ID
     * @return The checked in participant
     * @throws DataAccessException Exchange with the rest api failed
     */
    ParticipantWrapper checkInParticipant(String tournament, long participantId) throws DataAccessException;

    /**
     * Checks a participant in, setting checked_in_at to the current time.
     *
     * @param tournament    Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                      If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                      (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId The participant's unique ID
     * @param onSuccess     Called with result if call was successful
     * @param onFailure     Called with exception if call was not successful
     */
    void checkInParticipant(String tournament, long participantId, Callback<ParticipantWrapper> onSuccess,
                            Callback<DataAccessException> onFailure);

    /**
     * Marks a participant as having not checked in, setting checked_in_at to nil.
     *
     * @param tournament    Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                      If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                      (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId The participant's unique ID
     * @return The checked out participant
     * @throws DataAccessException Exchange with the rest api failed
     */
    ParticipantWrapper undoCheckInParticipant(String tournament, long participantId) throws DataAccessException;

    /**
     * Marks a participant as having not checked in, setting checked_in_at to nil.
     *
     * @param tournament    Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                      If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                      (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId The participant's unique ID
     * @param onSuccess     Called with result if call was successful
     * @param onFailure     Called with exception if call was not successful
     */
    void undoCheckInParticipant(String tournament, long participantId, Callback<ParticipantWrapper> onSuccess,
                                Callback<DataAccessException> onFailure);

    /**
     * If the tournament has not started, delete a participant, automatically filling in the abandoned seed number.
     * If tournament is underway, mark a participant inactive, automatically forfeiting his/her remaining matches.
     *
     * @param tournament    Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                      If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                      (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId The participant's unique ID
     * @return The deleted participant
     * @throws DataAccessException Exchange with the rest api failed
     */
    ParticipantWrapper deleteParticipant(String tournament, long participantId) throws DataAccessException;

    /**
     * If the tournament has not started, delete a participant, automatically filling in the abandoned seed number.
     * If tournament is underway, mark a participant inactive, automatically forfeiting his/her remaining matches.
     *
     * @param tournament    Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                      If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                      (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId The participant's unique ID
     * @param onSuccess     Called with result if call was successful
     * @param onFailure     Called with exception if call was not successful
     */
    void deleteParticipant(String tournament, long participantId, Callback<ParticipantWrapper> onSuccess,
                           Callback<DataAccessException> onFailure);

    /**
     * Randomize seeds among participants. Only applicable before a tournament has started.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @return The randomized participants
     * @throws DataAccessException Exchange with the rest api failed
     */
    List<ParticipantWrapper> randomizeParticipants(String tournament) throws DataAccessException;

    /**
     * Randomize seeds among participants. Only applicable before a tournament has started.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param onSuccess  Called with result if call was successful
     * @param onFailure  Called with exception if call was not successful
     */
    void randomizeParticipants(String tournament, Callback<List<ParticipantWrapper>> onSuccess,
                               Callback<DataAccessException> onFailure);

    /**
     * Retrieve a tournament's match list.
     *
     * @param tournament    Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                      If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                      (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId Only retrieve matches that include the specified participant.
     *                      This parameter is optional. Provide null if you want to skip it.
     * @param state         all (default), pending, open, complete.
     *                      This parameter is optional. Provide null if you want to skip it.
     * @return The tournament's matches
     * @throws DataAccessException Exchange with the rest api failed
     */
    List<MatchWrapper> getMatches(String tournament, Long participantId, MatchState state) throws DataAccessException;

    /**
     * Retrieve a tournament's match list.
     *
     * @param tournament    Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                      If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                      (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId Only retrieve matches that include the specified participant.
     *                      This parameter is optional. Provide null if you want to skip it.
     * @param state         all (default), pending, open, complete.
     *                      This parameter is optional. Provide null if you want to skip it.
     * @param onSuccess     Called with result if call was successful
     * @param onFailure     Called with exception if call was not successful
     */
    void getMatches(String tournament, Long participantId, MatchState state, Callback<List<MatchWrapper>> onSuccess,
                    Callback<DataAccessException> onFailure);

    /**
     * Retrieve a single match record for a tournament.
     *
     * @param tournament         Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                           If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                           (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId            The match's unique ID
     * @param includeAttachments Include an array of associated attachment records
     * @return The requested match
     * @throws DataAccessException Exchange with the rest api failed
     */
    MatchWrapper getMatch(String tournament, long matchId, boolean includeAttachments) throws DataAccessException;

    /**
     * Retrieve a single match record for a tournament.
     *
     * @param tournament         Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                           If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                           (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId            The match's unique ID
     * @param includeAttachments Include an array of associated attachment records
     * @param onSuccess          Called with result if call was successful
     * @param onFailure          Called with exception if call was not successful
     */
    void getMatch(String tournament, long matchId, boolean includeAttachments, Callback<MatchWrapper> onSuccess,
                  Callback<DataAccessException> onFailure);

    /**
     * Update/submit the score(s) for a match.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId    The match's unique ID
     * @param match      The match data
     * @return The updated match
     * @throws DataAccessException Exchange with the rest api failed
     */
    MatchWrapper updateMatch(String tournament, long matchId, MatchQueryWrapper match) throws DataAccessException;

    /**
     * Update/submit the score(s) for a match.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId    The match's unique ID
     * @param match      The match data
     * @param onSuccess  Called with result if call was successful
     * @param onFailure  Called with exception if call was not successful
     */
    void updateMatch(String tournament, long matchId, MatchQueryWrapper match, Callback<MatchWrapper> onSuccess,
                     Callback<DataAccessException> onFailure);

    /**
     * Marks a match as underway
     * 
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim). If assigned to a subdomain, URL format must be :subdomain-:tournament_url (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId    The match's unique ID
     * @return The updated match
     * @throws DataAccessException Exchange with the rest api failed
     */
    MatchWrapper markMatchAsUnderway(String tournament, long matchId) throws DataAccessException;

    /**
     * Marks a match as underway
     * 
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim). If assigned to a subdomain, URL format must be :subdomain-:tournament_url (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId    The match's unique ID
     * @param onSuccess  Called with result if call was successful
     * @param onFailure  Called with exception if call was not successful
     */
    void markMatchAsUnderway(String tournament, long matchId, Callback<MatchWrapper> onSuccess,
                             Callback<DataAccessException> onFailure);

    /**
     * Marks a match as not underway
     * 
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim). If assigned to a subdomain, URL format must be :subdomain-:tournament_url (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId    The match's unique ID
     * @return The updated match
     * @throws DataAccessException Exchange with the rest api failed
     */
    MatchWrapper unmarkMatchAsUnderway(String tournament, long matchId) throws DataAccessException;

    /**
     * Marks a match as underway
     * 
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim). If assigned to a subdomain, URL format must be :subdomain-:tournament_url (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId    The match's unique ID
     * @param onSuccess  Called with result if call was successful
     * @param onFailure  Called with exception if call was not successful
     */
    void unmarkMatchAsUnderway(String tournament, long matchId, Callback<MatchWrapper> onSuccess,
                               Callback<DataAccessException> onFailure);

    /**
     * Reopens a match that was marked completed, automatically resetting matches that follow it
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId    The match's unique ID
     * @return The reopened match
     * @throws DataAccessException Exchange with the rest api failed
     */
    MatchWrapper reopenMatch(String tournament, long matchId) throws DataAccessException;

    /**
     * Reopens a match that was marked completed, automatically resetting matches that follow it
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId    The match's unique ID
     * @param onSuccess  Called with result if call was successful
     * @param onFailure  Called with exception if call was not successful
     */
    void reopenMatch(String tournament, long matchId, Callback<MatchWrapper> onSuccess,
                     Callback<DataAccessException> onFailure);

    /**
     * Retrieve a match's attachments.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId    The match's unique ID
     * @return The match attachments
     * @throws DataAccessException Exchange with the rest api failed
     */
    List<AttachmentWrapper> getAttachments(String tournament, long matchId) throws DataAccessException;

    /**
     * Retrieve a match's attachments.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId    The match's unique ID
     * @param onSuccess  Called with result if call was successful
     * @param onFailure  Called with exception if call was not successful
     */
    void getAttachments(String tournament, long matchId, Callback<List<AttachmentWrapper>> onSuccess,
                        Callback<DataAccessException> onFailure);

    /**
     * Retrieve a single match attachment record.
     *
     * @param tournament   Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                     If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                     (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId      The match's unique ID
     * @param attachmentId The attachment's unique ID
     * @return The requested attachment
     * @throws DataAccessException Exchange with the rest api failed
     */
    AttachmentWrapper getAttachment(String tournament, long matchId, long attachmentId) throws DataAccessException;

    /**
     * Retrieve a single match attachment record.
     *
     * @param tournament   Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                     If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                     (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId      The match's unique ID
     * @param attachmentId The attachment's unique ID
     * @param onSuccess    Called with result if call was successful
     * @param onFailure    Called with exception if call was not successful
     */
    void getAttachment(String tournament, long matchId, long attachmentId, Callback<AttachmentWrapper> onSuccess,
                       Callback<DataAccessException> onFailure);

    /**
     * Add a file, link, or text attachment to a match. NOTE: The associated tournament's
     * "accept_attachments" attribute must be true for this action to succeed.
     * <p>
     * At least 1 of the 3 optional parameters (asset, url or description in the enumeration object) must be provided.
     * Files up to 25MB are allowed for tournaments hosted by Challonge Premier subscribers.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId    The match's unique ID
     * @param attachment The attachment to create
     * @return The created attachment
     * @throws DataAccessException Exchange with the rest api failed
     */
    AttachmentWrapper createAttachment(String tournament, long matchId, AttachmentQuery attachment) throws DataAccessException;

    /**
     * Add a file, link, or text attachment to a match. NOTE: The associated tournament's
     * "accept_attachments" attribute must be true for this action to succeed.
     * <p>
     * At least 1 of the 3 optional parameters (asset, url or description in the enumeration object) must be provided.
     * Files up to 25MB are allowed for tournaments hosted by Challonge Premier subscribers.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId    The match's unique ID
     * @param attachment The attachment to create
     * @param onSuccess  Called with result if call was successful
     * @param onFailure  Called with exception if call was not successful
     */
    void createAttachment(String tournament, long matchId, AttachmentQuery attachment,
                          Callback<AttachmentWrapper> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * Update the attributes of a match attachment.
     * <p>
     * Sending the asset does neither work with base64 nor with a multipart-form-data request
     * <p>
     * At least 1 of the 3 optional parameters (asset, url or description in the enumeration object) must be provided.
     * Files up to 25MB are allowed for tournaments hosted by Challonge Premier subscribers.
     *
     * @param tournament   Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                     If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                     (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId      The match's unique ID
     * @param attachmentId The attachment's unique ID
     * @param attachment   The attachment to update
     * @return The updated attachment
     * @throws DataAccessException Exchange with the rest api failed
     */
    AttachmentWrapper updateAttachment(String tournament, long matchId, long attachmentId,
                                       AttachmentQuery attachment) throws DataAccessException;

    /**
     * Update the attributes of a match attachment.
     * <p>
     * Sending the asset does neither work with base64 nor with a multipart-form-data request
     * <p>
     * At least 1 of the 3 optional parameters (asset, url or description in the enumeration object) must be provided.
     * Files up to 25MB are allowed for tournaments hosted by Challonge Premier subscribers.
     *
     * @param tournament   Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                     If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                     (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId      The match's unique ID
     * @param attachmentId The attachment's unique ID
     * @param attachment   The attachment to update
     * @param onSuccess    Called with result if call was successful
     * @param onFailure    Called with exception if call was not successful
     */
    void updateAttachment(String tournament, long matchId, long attachmentId, AttachmentQuery attachment,
                          Callback<AttachmentWrapper> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * Delete a match attachment.
     *
     * @param tournament   Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                     If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                     (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId      The match's unique ID
     * @param attachmentId The attachment's unique ID
     * @return The deleted attachment
     * @throws DataAccessException Exchange with the rest api failed
     */
    AttachmentWrapper deleteAttachment(String tournament, long matchId, long attachmentId) throws DataAccessException;

    /**
     * Delete a match attachment.
     *
     * @param tournament   Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                     If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                     (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId      The match's unique ID
     * @param attachmentId The attachment's unique ID
     * @param onSuccess    Called with result if call was successful
     * @param onFailure    Called with exception if call was not successful
     */
    void deleteAttachment(String tournament, long matchId, long attachmentId, Callback<AttachmentWrapper> onSuccess,
                          Callback<DataAccessException> onFailure);
}
