/*
 * Copyright 2017 Stefan Geyer <stefangeyer at outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
     * @param tournament    Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim). If assigned to a subdomain, URL format must be :subdomain-:tournament_url (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId Only retrieve matches that include the specified participant. This parameter is optional. Provide null if you want to skip it.
     * @param state         all (default), pending, open, complete. This parameter is optional. Provide null if you want to skip it.
     * @return Call
     */
    @GET("tournaments/{tournament}/matches.json")
    Call<List<Match>> getMatches(@Path("tournament") String tournament,
                                 @Query("participant_id") Integer participantId,
                                 @Query("state") MatchState state);

    /**
     * Retrieve a single match record for a tournament.
     *
     * @param tournament         Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim). If assigned to a subdomain, URL format must be :subdomain-:tournament_url (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId            The match's unique ID
     * @param includeAttachments 0 or 1; include an array of associated attachment records
     * @return Call
     */
    @GET("tournaments/{tournament}/matches/{match_id}.json")
    Call<Match> getMatch(@Path("tournament") String tournament,
                         @Path("match_id") int matchId,
                         @Query("include_attachments") int includeAttachments);

    /**
     * Update/submit the score(s) for a match.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim). If assigned to a subdomain, URL format must be :subdomain-:tournament_url (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId    The match's unique ID
     * @param match      The match data
     * @return Call
     */
    @PUT("tournaments/{tournament}/matches/{match_id}.json")
    Call<Match> updateMatch(@Path("tournament") String tournament,
                            @Path("match_id") int matchId,
                            @Body MatchQuery match);
}
