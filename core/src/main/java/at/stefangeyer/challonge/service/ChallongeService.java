package at.stefangeyer.challonge.service;

import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.enumeration.MatchState;
import at.stefangeyer.challonge.model.enumeration.TournamentType;
import at.stefangeyer.challonge.model.query.AttachmentQuery;
import at.stefangeyer.challonge.model.query.MatchQuery;
import at.stefangeyer.challonge.model.query.ParticipantQuery;
import at.stefangeyer.challonge.model.query.TournamentQuery;
import at.stefangeyer.challonge.model.query.enumeration.TournamentQueryState;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Challonge Service Definitions
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
public interface ChallongeService {

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


    /**
     * Retrieve a tournament's participant list.
     *
     * @param tournament The tournament to get the participants from. Must contain tournament id
     * @return The tournaments participants
     * @throws DataAccessException Exchange with the rest api or validation failed
     */
    List<Participant> getParticipants(Tournament tournament) throws DataAccessException;

    /**
     * Retrieve a tournament's participant list.
     *
     * @param tournament The tournament to get the participants from. Must contain tournament id
     * @param onSuccess  Called with result if call was successful
     * @param onFailure  Called with exception if call was not successful
     */
    void getParticipants(Tournament tournament, Callback<List<Participant>> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * Retrieve a single participant record for a tournament.
     *
     * @param tournament     The tournament to get the participant from. Must contain tournament id
     * @param participantId  The participant's unique ID
     * @param includeMatches Includes an array of associated match records
     * @return The requested participant
     * @throws DataAccessException Exchange with the rest api or validation failed
     */
    Participant getParticipant(Tournament tournament, long participantId, boolean includeMatches) throws DataAccessException;

    /**
     * Retrieve a single participant record for a tournament.
     *
     * @param tournament     The tournament to get the participant from. Must contain tournament id
     * @param participantId  The participant's unique ID
     * @param includeMatches Includes an array of associated match records
     * @param onSuccess      Called with result if call was successful
     * @param onFailure      Called with exception if call was not successful
     */
    void getParticipant(Tournament tournament, long participantId, boolean includeMatches, Callback<Participant> onSuccess,
                        Callback<DataAccessException> onFailure);

    /**
     * Add a participant to a tournament (up until it is started).
     *
     * @param tournament The tournament to add the participant to. Must contain tournament id
     * @param data       The participant data
     * @return The added participant
     * @throws DataAccessException Exchange with the rest api or validation failed
     */
    Participant addParticipant(Tournament tournament, ParticipantQuery data) throws DataAccessException;

    /**
     * Add a participant to a tournament (up until it is started).
     *
     * @param tournament The tournament to add the participant to. Must contain tournament id
     * @param data       The participant data
     * @param onSuccess  Called with result if call was successful
     * @param onFailure  Called with exception if call was not successful
     */
    void addParticipant(Tournament tournament, ParticipantQuery data, Callback<Participant> onSuccess,
                        Callback<DataAccessException> onFailure);

    /**
     * Bulk add participants to a tournament (up until it is started).
     * If an invalid participant is detected, bulk participant creation will halt
     * and any previously added participants (from this API request) will be rolled back.
     *
     * @param tournament The tournament to add the participants to. Must contain tournament id
     * @param data       The participant data
     * @return The added participants
     * @throws DataAccessException Exchange with the rest api or validation failed
     */
    List<Participant> bulkAddParticipants(Tournament tournament, List<ParticipantQuery> data) throws DataAccessException;

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
    void bulkAddParticipants(Tournament tournament, List<ParticipantQuery> data, Callback<List<Participant>> onSuccess,
                             Callback<DataAccessException> onFailure);

    /**
     * Update the attributes of a tournament participant.
     *
     * @param participant The participant to update. Must contain the tournament id and the participant's id
     * @param data        The participant data
     * @return The updates participant
     * @throws DataAccessException Exchange with the rest api or validation failed
     */
    Participant updateParticipant(Participant participant, ParticipantQuery data) throws DataAccessException;

    /**
     * Update the attributes of a tournament participant.
     *
     * @param participant The participant to update. Must contain the tournament id and the participant's id
     * @param data        The participant data
     * @param onSuccess   Called with result if call was successful
     * @param onFailure   Called with exception if call was not successful
     */
    void updateParticipant(Participant participant, ParticipantQuery data, Callback<Participant> onSuccess,
                           Callback<DataAccessException> onFailure);

    /**
     * Checks a participant in, setting checked_in_at to the current time.
     *
     * @param participant The participant to check in. Must contain the tournament id and the participant's id
     * @return The checked in participant
     * @throws DataAccessException Exchange with the rest api or validation failed
     */
    Participant checkInParticipant(Participant participant) throws DataAccessException;

    /**
     * Checks a participant in, setting checked_in_at to the current time.
     *
     * @param participant The participant to check in. Must contain the tournament id and the participant's id
     * @param onSuccess   Called with result if call was successful
     * @param onFailure   Called with exception if call was not successful
     */
    void checkInParticipant(Participant participant, Callback<Participant> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * Marks a participant as having not checked in, setting checked_in_at to nil.
     *
     * @param participant The participant to check in. Must contain the tournament id and the participant's id
     * @return The checked out participant
     * @throws DataAccessException Exchange with the rest api or validation failed
     */
    Participant undoCheckInParticipant(Participant participant) throws DataAccessException;

    /**
     * Marks a participant as having not checked in, setting checked_in_at to nil.
     *
     * @param participant The participant to check in. Must contain the tournament id and the participant's id
     * @param onSuccess   Called with result if call was successful
     * @param onFailure   Called with exception if call was not successful
     */
    void undoCheckInParticipant(Participant participant, Callback<Participant> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * If the tournament has not started, delete a participant, automatically filling in the abandoned seed number.
     * If tournament is underway, mark a participant inactive, automatically forfeiting his/her remaining matches.
     *
     * @param participant The participant to delete. Must contain the tournament id and the participant's id
     * @return The deleted participant
     * @throws DataAccessException Exchange with the rest api or validation failed
     */
    Participant deleteParticipant(Participant participant) throws DataAccessException;

    /**
     * If the tournament has not started, delete a participant, automatically filling in the abandoned seed number.
     * If tournament is underway, mark a participant inactive, automatically forfeiting his/her remaining matches.
     *
     * @param participant The participant to delete. Must contain the tournament id and the participant's id
     * @param onSuccess   Called with result if call was successful
     * @param onFailure   Called with exception if call was not successful
     */
    void deleteParticipant(Participant participant, Callback<Participant> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * Randomize seeds among participants. Only applicable before a tournament has started.
     *
     * @param tournament The tournament to randomize. Must contain the tournament id
     * @return The randomized participants
     * @throws DataAccessException Exchange with the rest api or validation failed
     */
    List<Participant> randomizeParticipants(Tournament tournament) throws DataAccessException;

    /**
     * Randomize seeds among participants. Only applicable before a tournament has started.
     *
     * @param tournament The tournament to randomize. Must contain the tournament id
     * @param onSuccess  Called with result if call was successful
     * @param onFailure  Called with exception if call was not successful
     */
    void randomizeParticipants(Tournament tournament, Callback<List<Participant>> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * Retrieve a tournament's match list.
     *
     * @param tournament  The tournament to get the matches from. Must contain id or url with an optional subdomain
     * @param participant Only retrieve matches that include the specified participant.
     *                    This parameter is optional. Provide null if you want to skip it.
     * @param state       all (default), pending, open, complete.
     *                    This parameter is optional. Provide null if you want to skip it.
     * @return The tournament's matches
     * @throws DataAccessException Exchange with the rest api or validation failed
     */
    List<Match> getMatches(Tournament tournament, Participant participant, MatchState state) throws DataAccessException;

    /**
     * Retrieve a tournament's match list.
     *
     * @param tournament  The tournament to get the matches from. Must contain id or url with an optional subdomain
     * @param participant Only retrieve matches that include the specified participant.
     *                    This parameter is optional. Provide null if you want to skip it.
     * @param state       all (default), pending, open, complete.
     *                    This parameter is optional. Provide null if you want to skip it.
     * @param onSuccess   Called with result if call was successful
     * @param onFailure   Called with exception if call was not successful
     */
    void getMatches(Tournament tournament, Participant participant, MatchState state, Callback<List<Match>> onSuccess,
                    Callback<DataAccessException> onFailure);

    /**
     * Retrieve a single match record for a tournament.
     *
     * @param tournament         The tournament to get the match from. Must contain tournament id
     * @param matchId            The match's unique ID
     * @param includeAttachments Include an array of associated attachment records
     * @return The requested match
     * @throws DataAccessException Exchange with the rest api or validation failed
     */
    Match getMatch(Tournament tournament, long matchId, boolean includeAttachments) throws DataAccessException;

    /**
     * Retrieve a single match record for a tournament.
     *
     * @param tournament         The tournament to get the match from. Must contain tournament id
     * @param matchId            The match's unique ID
     * @param includeAttachments Include an array of associated attachment records
     * @param onSuccess          Called with result if call was successful
     * @param onFailure          Called with exception if call was not successful
     */
    void getMatch(Tournament tournament, long matchId, boolean includeAttachments, Callback<Match> onSuccess,
                  Callback<DataAccessException> onFailure);

    /**
     * Update/submit the score(s) for a match.
     *
     * @param match The match to update. Must contain the tournament- and match id
     * @param data  The new match data
     * @return The updated match
     * @throws DataAccessException Exchange with the rest api or validation failed
     */
    Match updateMatch(Match match, MatchQuery data) throws DataAccessException;

    /**
     * Update/submit the score(s) for a match.
     *
     * @param match     The match to update. Must contain the tournament- and match id
     * @param data      The new match data
     * @param onSuccess Called with result if call was successful
     * @param onFailure Called with exception if call was not successful
     */
    void updateMatch(Match match, MatchQuery data, Callback<Match> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * Marks a match as underway
     * 
     * @param match The match to mark as underway. Must contain the tournament- and match id
     * @return The updated match
     * @throws DataAccessException Exchange with the rest api failed
     */
    Match markMatchAsUnderway(Match match) throws DataAccessException;

    /**
     * Marks a match as underway
     * 
     * @param match     The match to mark as underway. Must contain the tournament- and match id
     * @param onSuccess Called with result if call was successful
     * @param onFailure Called with exception if call was not successful
     */
    void markMatchAsUnderway(Match match, Callback<Match> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * Marks a match as not underway
     * 
     * @param match The match to mark as not underway. Must contain the tournament- and match id
     * @return The updated match
     * @throws DataAccessException Exchange with the rest api failed
     */
    Match unmarkMatchAsUnderway(Match match) throws DataAccessException;

    /**
     * Marks a match as not underway
     * 
     * @param match     The match to mark as not underway. Must contain the tournament- and match id
     * @param onSuccess Called with result if call was successful
     * @param onFailure Called with exception if call was not successful
     */
    void unmarkMatchAsUnderway(Match match, Callback<Match> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * Reopens a match that was marked completed, automatically resetting matches that follow it.
     *
     * @param match The match to reopen. Must contain the tournament- and match id
     * @return The reopened match
     * @throws DataAccessException Exchange with the rest api or validation failed
     */
    Match reopenMatch(Match match) throws DataAccessException;

    /**
     * Reopens a match that was marked completed, automatically resetting matches that follow it.
     *
     * @param match     The match to reopen. Must contain the tournament- and match id
     * @param onSuccess Called with result if call was successful
     * @param onFailure Called with exception if call was not successful
     */
    void reopenMatch(Match match, Callback<Match> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * Retrieve a match's attachments.
     *
     * @param match The match to get the attachments from. Must contain the tournament- and match id
     * @return The match attachments
     * @throws DataAccessException Exchange with the rest api failed
     */
    List<Attachment> getAttachments(Match match) throws DataAccessException;

    /**
     * Retrieve a match's attachments.
     *
     * @param match     The match to get the attachments from. Must contain the tournament- and match id
     * @param onSuccess Called with result if call was successful
     * @param onFailure Called with exception if call was not successful
     */
    void getAttachments(Match match, Callback<List<Attachment>> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * Retrieve a single match attachment record.
     *
     * @param match        The match to get the attachment from. Must contain the tournament- and match id
     * @param attachmentId The attachment's unique ID
     * @return The requested attachment
     * @throws DataAccessException Exchange with the rest api failed
     */
    Attachment getAttachment(Match match, long attachmentId) throws DataAccessException;

    /**
     * Retrieve a single match attachment record.
     *
     * @param match        The match to get the attachment from. Must contain the tournament- and match id
     * @param attachmentId The attachment's unique ID
     * @param onSuccess    Called with result if call was successful
     * @param onFailure    Called with exception if call was not successful
     */
    void getAttachment(Match match, long attachmentId, Callback<Attachment> onSuccess,
                       Callback<DataAccessException> onFailure);

    /**
     * Add a file, link, or text attachment to a match. NOTE: The associated tournament's
     * "accept_attachments" attribute must be true for this action to succeed.
     * <p>
     * At least 1 of the 3 optional parameters (asset, url or description in the enumeration object) must be provided.
     * Files up to 25MB are allowed for tournaments hosted by Challonge Premier subscribers.
     *
     * @param match The match to create the attachment for. Must contain the tournament- and match id
     * @param data  The attachment to create
     * @return The created attachment
     * @throws DataAccessException Exchange with the rest api failed
     */
    Attachment createAttachment(Match match, AttachmentQuery data) throws DataAccessException;

    /**
     * Add a file, link, or text attachment to a match. NOTE: The associated tournament's
     * "accept_attachments" attribute must be true for this action to succeed.
     * <p>
     * At least 1 of the 3 optional parameters (asset, url or description in the enumeration object) must be provided.
     * Files up to 25MB are allowed for tournaments hosted by Challonge Premier subscribers.
     *
     * @param match     The match to create the attachment for. Must contain the tournament- and match id
     * @param data      The attachment to create
     * @param onSuccess Called with result if call was successful
     * @param onFailure Called with exception if call was not successful
     */
    void createAttachment(Match match, AttachmentQuery data, Callback<Attachment> onSuccess,
                          Callback<DataAccessException> onFailure);

    /**
     * Update the attributes of a match attachment.
     * <p>
     * Sending the asset does neither work with base64 nor with a multipart-form-data request
     * <p>
     * At least 1 of the 3 optional parameters (asset, url or description in the enumeration object) must be provided.
     * Files up to 25MB are allowed for tournaments hosted by Challonge Premier subscribers.
     *
     * @param match      The match to delete the attachment from. Must contain the tournament- and match id
     * @param attachment The attachment to update. Must contain the tournament- and match id
     * @param data       The attachment to update
     * @return The updated attachment
     * @throws DataAccessException Exchange with the rest api failed
     */
    Attachment updateAttachment(Match match, Attachment attachment, AttachmentQuery data) throws DataAccessException;

    /**
     * Update the attributes of a match attachment.
     * <p>
     * Sending the asset does neither work with base64 nor with a multipart-form-data request
     * <p>
     * At least 1 of the 3 optional parameters (asset, url or description in the enumeration object) must be provided.
     * Files up to 25MB are allowed for tournaments hosted by Challonge Premier subscribers.
     *
     * @param match      The match to delete the attachment from. Must contain the tournament- and match id
     * @param attachment The attachment to update. Must contain the tournament- and match id
     * @param data       The attachment to update
     * @param onSuccess  Called with result if call was successful
     * @param onFailure  Called with exception if call was not successful
     */
    void updateAttachment(Match match, Attachment attachment, AttachmentQuery data, Callback<Attachment> onSuccess,
                          Callback<DataAccessException> onFailure);

    /**
     * Delete a match attachment.
     *
     * @param match      The match to delete the attachment from. Must contain the tournament- and match id
     * @param attachment The attachment to delete. Must contain the tournament- and match id
     * @return The deleted attachment
     * @throws DataAccessException Exchange with the rest api failed
     */
    Attachment deleteAttachment(Match match, Attachment attachment) throws DataAccessException;

    /**
     * Delete a match attachment.
     *
     * @param match      The match to delete the attachment from. Must contain the tournament- and match id
     * @param attachment The attachment to delete. Must contain the tournament- and match id
     * @param onSuccess  Called with result if call was successful
     * @param onFailure  Called with exception if call was not successful
     */
    void deleteAttachment(Match match, Attachment attachment, Callback<Attachment> onSuccess,
                          Callback<DataAccessException> onFailure);
}
