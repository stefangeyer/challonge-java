package at.stefangeyer.challonge.service;

import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.query.ParticipantQuery;

import java.util.List;

/**
 * Participant Service Definitions
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
public interface ParticipantService {

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
}
