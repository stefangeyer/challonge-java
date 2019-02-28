package at.stefangeyer.challonge.service;

import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.enumeration.MatchState;
import at.stefangeyer.challonge.model.query.MatchQuery;

import java.util.List;

/**
 * Match Service Definitions
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
public interface MatchService {

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
}
