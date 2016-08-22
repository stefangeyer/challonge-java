package com.exsoloscript.challonge.handler.retrofit;

import com.exsoloscript.challonge.model.Match;
import com.exsoloscript.challonge.model.enumeration.MatchState;
import com.exsoloscript.challonge.model.query.MatchQuery;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Match related API bindings
 *
 * @author EXSolo
 * @version 20160822.1
 */
public interface RetrofitMatchHandler {

    /**
     * Retrieve a tournament's match list.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim). If assigned to a subdomain, URL format must be :subdomain-:tournament_url (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId Only retrieve matches that include the specified participant.
     * @param state all (default), pending, open, complete
     * @return Call
     */
    @GET("tournaments/{tournament}/matches.json")
    Call<List<Match>> getMatches(@Path("tournament") String tournament,
                                 @Query("participant_id") int participantId,
                                 @Query("state") MatchState state);

    /**
     * Retrieve a single match record for a tournament.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim). If assigned to a subdomain, URL format must be :subdomain-:tournament_url (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId The match's unique ID
     * @param includeAttachments 0 or 1; include an array of associated attachment records
     * @return Call
     */
    @GET("tournaments/{tournament}/matches/{match_id}.json")
    Call<Match> getMatch(@Path("tournament") String tournament,
                         @Query("match_id") int matchId,
                         @Query("include_attachments") int includeAttachments);

    /**
     * Update/submit the score(s) for a match.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim). If assigned to a subdomain, URL format must be :subdomain-:tournament_url (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId The match's unique ID
     * @param match The match data
     * @return Call
     */
    @PUT("tournaments/{tournament}/matches/{match_id}.json")
    Call<Match> updateMatch(@Path("tournament") String tournament,
                            @Query("match_id") int matchId,
                            @Body MatchQuery match);
}
