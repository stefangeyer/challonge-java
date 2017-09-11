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

import com.exsoloscript.challonge.model.Tournament;
import com.exsoloscript.challonge.model.enumeration.TournamentType;
import com.exsoloscript.challonge.model.enumeration.query.TournamentQueryState;
import com.exsoloscript.challonge.model.query.TournamentQuery;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Tournament related API bindings
 *
 * @author EXSolo
 * @version 20160820.1
 */
public interface RetrofitTournamentHandler {

    /**
     * Retrieve a set of tournaments created with your account.
     *
     * @param state         only get tournaments with this state
     * @param type          only get tournaments with this type
     * @param createdAfter  get tournaments created after this date
     * @param createdBefore get tournaments created before this date
     * @param subdomain     only get tournaments with this subdomain
     * @return Call
     */
    @GET("tournaments.json")
    Call<List<Tournament>> getTournaments(@Query("state") TournamentQueryState state,
                                          @Query("type") TournamentType type,
                                          @Query("created_after") String createdAfter,
                                          @Query("created_before") String createdBefore,
                                          @Query("subdomain") String subdomain
    );

    /**
     * Retrieve a single tournament record created with your account.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants 0 or 1; include a list of participants in the response
     * @param includeMatches      include a list of matches in the response
     * @return Call
     */
    @GET("tournaments/{tournament}.json")
    Call<Tournament> getTournament(@Path("tournament") String tournament,
                                   @Query("include_participants") int includeParticipants,
                                   @Query("include_matches") int includeMatches);

    /**
     * Create a new tournament.
     *
     * @param tournamentData An object with all the necessary information to create the tournament
     * @return Call
     */
    @POST("tournaments.json")
    Call<Tournament> createTournament(@Body TournamentQuery tournamentData);

    /**
     * Update a tournament's attributes.
     *
     * @param tournament     Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                       If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                       (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param tournamentData An object with all the necessary information to update the tournament
     * @return Call
     */
    @PUT("tournaments/{tournament}.json")
    Call<Tournament> updateTournament(@Path("tournament") String tournament,
                                      @Body TournamentQuery tournamentData);

    /**
     * Deletes a tournament along with all its associated records. There is no undo, so use with care!
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @return Call
     */
    @DELETE("tournaments/{tournament}.json")
    Call<Tournament> deleteTournament(@Path("tournament") String tournament);

    /**
     * This should be invoked after a tournament's check-in window closes before the tournament is started.
     * <p>
     * <ol>
     * <li>Marks participants who have not checked in as inactive.</li>
     * <li>Moves inactive participants to bottom seeds (ordered by original seed).</li>
     * <li>Transitions the tournament state from 'checking_in' to 'checked_in'</li>
     * </ol>
     * <p>
     * NOTE: Checked in participants on the waiting list will be promoted if slots become available.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants 0 or 1; include a list of participants in the response
     * @param includeMatches      0 or 1; include a list of matches in the response
     * @return Call
     */
    @POST("tournaments/{tournament}/process_check_ins.json")
    Call<Tournament> processCheckIns(@Path("tournament") String tournament,
                                     @Query("include_participants") int includeParticipants,
                                     @Query("include_matches") int includeMatches);

    /**
     * When your tournament is in a 'checking_in' or 'checked_in' state,
     * there's no way to edit the tournament's start time (start_at) or check-in duration (check_in_duration).
     * You must first abort check-in, then you may edit those attributes.
     * <p>
     * <ol>
     * <li>Makes all participants active and clears their checked_in_at times.</li>
     * <li>Transitions the tournament state from 'checking_in' or 'checked_in' to 'pending'</li>
     * </ol>
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants 0 or 1; include a list of participants in the response
     * @param includeMatches      0 or 1; include a list of matches in the response
     * @return Call
     */
    @POST("tournaments/{tournament}/abort_check_in.json")
    Call<Tournament> abortCheckIn(@Path("tournament") String tournament,
                                  @Query("include_participants") int includeParticipants,
                                  @Query("include_matches") int includeMatches);

    /**
     * Start a tournament, opening up first round matches for score reporting.
     * The tournament must have at least 2 participants.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants 0 or 1; include a list of participants in the response
     * @param includeMatches      0 or 1; include a list of matches in the response
     * @return Call
     */
    @POST("tournaments/{tournament}/start.json")
    Call<Tournament> startTournament(@Path("tournament") String tournament,
                                     @Query("include_participants") int includeParticipants,
                                     @Query("include_matches") int includeMatches);

    /**
     * Finalize a tournament that has had all match scores submitted, rendering its results permanent.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants 0 or 1; include a list of participants in the response
     * @param includeMatches      0 or 1; include a list of matches in the response
     * @return Call
     */
    @POST("tournaments/{tournament}/finalize.json")
    Call<Tournament> finalizeTournament(@Path("tournament") String tournament,
                                        @Query("include_participants") int includeParticipants,
                                        @Query("include_matches") int includeMatches);

    /**
     * Reset a tournament, clearing all of its scores and attachments.
     * You can then add/remove/edit participants before starting the tournament again.
     *
     * @param tournament          Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                            If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                            (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants 0 or 1; include a list of participants in the response
     * @param includeMatches      0 or 1; include a list of matches in the response
     * @return Call
     */
    @POST("tournaments/{tournament}/reset.json")
    Call<Tournament> resetTournament(@Path("tournament") String tournament,
                                     @Query("include_participants") int includeParticipants,
                                     @Query("include_matches") int includeMatches);
}
