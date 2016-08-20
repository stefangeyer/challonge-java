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
     * Get all tournaments of a user matching the query parameters
     *
     * @param state only get tournaments with this state
     * @param type only get tournaments with this type
     * @param createdAfter get tournaments created after this date
     * @param createdBefore get tournaments created before this date
     * @param subdomain only get tournaments with this subdomain
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
     * Get a specific tournament
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim). If assigned to a subdomain, URL format must be :subdomain-:tournament_url (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants include a list of participants in the response
     * @param includeMatches include a list of matches in the response
     * @return Call
     */
    @GET("tournaments/{tournament}.json")
    Call<Tournament> getTournament(@Path("tournament") String tournament,
                                   @Query("include_participants") int includeParticipants,
                                   @Query("include_matches") int includeMatches);

    /**
     * Create a tournament with the given tournament data
     *
     * @param tournamentData An object with all the necessary information to create the tournament
     * @return Call
     */
    @POST("tournaments.json")
    Call<Tournament> createTournament(@Body TournamentQuery tournamentData);

    /**
     * Update a tournament with the given tournament data
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim). If assigned to a subdomain, URL format must be :subdomain-:tournament_url (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param tournamentData An object with all the necessary information to update the tournament
     * @return Call
     */
    @PUT("tournaments/{tournament}.json")
    Call<Tournament> updateTournament(@Path("tournament") String tournament,
                                      @Body TournamentQuery tournamentData);

    /**
     * Delete the given tournament
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim). If assigned to a subdomain, URL format must be :subdomain-:tournament_url (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @return Call
     */
    @DELETE("tournaments/{tournament}.json")
    Call<Tournament> deleteTournament(@Path("tournament") String tournament);

    /**
     * Process check ins for the given tournament
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim). If assigned to a subdomain, URL format must be :subdomain-:tournament_url (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants include a list of participants in the response
     * @param includeMatches include a list of matches in the response
     * @return Call
     */
    @POST("tournaments/{tournament}/process_check_ins.json")
    Call<Tournament> processCheckIns(@Path("tournament") String tournament,
                                     @Query("include_participants") int includeParticipants,
                                     @Query("include_matches") int includeMatches);

    /**
     * Abort check ins for the given tournament
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim). If assigned to a subdomain, URL format must be :subdomain-:tournament_url (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants include a list of participants in the response
     * @param includeMatches include a list of matches in the response
     * @return Call
     */
    @POST("tournaments/{tournament}/abort_check_in.json")
    Call<Tournament> abortCheckIn(@Path("tournament") String tournament,
                                  @Query("include_participants") int includeParticipants,
                                  @Query("include_matches") int includeMatches);

    /**
     * Start the given tournament
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim). If assigned to a subdomain, URL format must be :subdomain-:tournament_url (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants include a list of participants in the response
     * @param includeMatches include a list of matches in the response
     * @return Call
     */
    @POST("tournaments/{tournament}/start.json")
    Call<Tournament> startTournament(@Path("tournament") String tournament,
                                     @Query("include_participants") int includeParticipants,
                                     @Query("include_matches") int includeMatches);

    /**
     * Finalize the given tournament
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim). If assigned to a subdomain, URL format must be :subdomain-:tournament_url (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants include a list of participants in the response
     * @param includeMatches include a list of matches in the response
     * @return Call
     */
    @POST("tournaments/{tournament}/finalize.json")
    Call<Tournament> finalizeTournament(@Path("tournament") String tournament,
                                        @Query("include_participants") int includeParticipants,
                                        @Query("include_matches") int includeMatches);

    /**
     * Reset the given tournament
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim). If assigned to a subdomain, URL format must be :subdomain-:tournament_url (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param includeParticipants include a list of participants in the response
     * @param includeMatches include a list of matches in the response
     * @return Call
     */
    @POST("tournaments/{tournament}/reset.json")
    Call<Tournament> resetTournament(@Path("tournament") String tournament,
                                     @Query("include_participants") int includeParticipants,
                                     @Query("include_matches") int includeMatches);
}
