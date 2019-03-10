package at.stefangeyer.challonge.rest;

import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.query.wrapper.ParticipantQueryListWrapper;
import at.stefangeyer.challonge.model.query.wrapper.ParticipantQueryWrapper;
import at.stefangeyer.challonge.model.wrapper.ParticipantWrapper;

import java.util.List;

/**
 * Participant Rest Client Definition
 *
 * @author Stefan Geyer
 * @version 2018-06-29
 */
public interface ParticipantRestClient {

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
}
