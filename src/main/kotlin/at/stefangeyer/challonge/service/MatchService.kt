package at.stefangeyer.challonge.service

import at.stefangeyer.challonge.async.Callback
import at.stefangeyer.challonge.exception.DataAccessException
import at.stefangeyer.challonge.model.Match
import at.stefangeyer.challonge.model.Participant
import at.stefangeyer.challonge.model.Tournament
import at.stefangeyer.challonge.model.enumeration.MatchState
import at.stefangeyer.challonge.model.query.MatchQuery

/**
 * Match Service Definitions
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
interface MatchService {

    /**
     * Retrieve a tournament's match list.
     *
     * @param tournament    The tournament to get the matches from. Must contain id or url with an optional subdomain
     * @param participant   Only retrieve matches that include the specified participant.
     *                      This parameter is optional. Provide null if you want to skip it.
     * @param state         all (default), pending, open, complete.
     *                      This parameter is optional. Provide null if you want to skip it.
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The tournament's matches
     */
    @Throws(DataAccessException::class)
    fun getMatches(tournament: Tournament, participant: Participant? = null, state: MatchState? = null): List<Match>

    /**
     * Retrieve a tournament's match list.
     *
     * @param tournament    The tournament to get the matches from. Must contain id or url with an optional subdomain
     * @param participant   Only retrieve matches that include the specified participant.
     *                      This parameter is optional. Provide null if you want to skip it.
     * @param state         all (default), pending, open, complete.
     *                      This parameter is optional. Provide null if you want to skip it.
     * @param onSuccess     Called with result if call was successful
     * @param onFailure     Called with exception if call was not successful
     */
    fun getMatches(tournament: Tournament, participant: Participant? = null, state: MatchState? = null,
                   onSuccess: Callback<List<Match>>, onFailure: Callback<DataAccessException>)

    /**
     * Retrieve a single match record for a tournament.
     *
     * @param tournament         The tournament to get the match from. Must contain tournament id
     * @param matchId            The match's unique ID
     * @param includeAttachments Include an array of associated attachment records
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The requested match
     */
    @Throws(DataAccessException::class)
    fun getMatch(tournament: Tournament, matchId: Long, includeAttachments: Boolean = false): Match

    /**
     * Retrieve a single match record for a tournament.
     *
     * @param tournament         The tournament to get the match from. Must contain tournament id
     * @param matchId            The match's unique ID
     * @param includeAttachments Include an array of associated attachment records
     * @param onSuccess          Called with result if call was successful
     * @param onFailure          Called with exception if call was not successful
     */
    fun getMatch(tournament: Tournament, matchId: Long, includeAttachments: Boolean = false,
                 onSuccess: Callback<Match>, onFailure: Callback<DataAccessException>)

    /**
     * Update/submit the score(s) for a match.
     *
     * @param match     The match to update. Must contain the tournament- and match id
     * @param data      The new match data
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The updated match
     */
    @Throws(DataAccessException::class)
    fun updateMatch(match: Match, data: MatchQuery): Match

    /**
     * Update/submit the score(s) for a match.
     *
     * @param match     The match to update. Must contain the tournament- and match id
     * @param data      The new match data
     * @param onSuccess Called with result if call was successful
     * @param onFailure Called with exception if call was not successful
     */
    fun updateMatch(match: Match, data: MatchQuery, onSuccess: Callback<Match>, onFailure: Callback<DataAccessException>)

    /**
     * Reopens a match that was marked completed, automatically resetting matches that follow it.
     *
     * @param match The match to reopen. Must contain the tournament- and match id
     * @throws DataAccessException Exchange with the rest api or validation failed
     * @return The reopened match
     */
    @Throws(DataAccessException::class)
    fun reopenMatch(match: Match): Match

    /**
     * Reopens a match that was marked completed, automatically resetting matches that follow it.
     *
     * @param match The match to reopen. Must contain the tournament- and match id
     * @param onSuccess Called with result if call was successful
     * @param onFailure Called with exception if call was not successful
     */
    fun reopenMatch(match: Match, onSuccess: Callback<Match>, onFailure: Callback<DataAccessException>)
}