package at.stefangeyer.challonge.rest;

import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.enumeration.MatchState;
import at.stefangeyer.challonge.model.query.wrapper.MatchQueryWrapper;
import at.stefangeyer.challonge.model.wrapper.MatchWrapper;

import java.util.List;

public interface MatchRestClient {

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
}
