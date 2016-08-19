package com.exsoloscript.challonge.handler.retrofit;

import com.exsoloscript.challonge.model.Tournament;
import com.exsoloscript.challonge.model.enumeration.TournamentState;
import com.exsoloscript.challonge.model.enumeration.TournamentType;
import com.exsoloscript.challonge.model.query.TournamentQuery;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface RetrofitTournamentHandler {

    @GET("tournaments.json")
    Call<List<Tournament>> getTournaments(@Query("state") TournamentState state,
                                          @Query("type") TournamentType type,
                                          @Query("created_after") String createdAfter,
                                          @Query("created_before") String createdBefore,
                                          @Query("subdomain") String subdomain
    );

    @GET("tournaments/{tournament}.json")
    Call<Tournament> getTournament(@Path("tournament") String tournament,
                                   @Query("include_participants") int includeParticipants,
                                   @Query("include_matches") int includeMatches);

    @POST("tournaments.json")
    Call<Tournament> createTournament(@Body TournamentQuery tournamentData);

    @PUT("tournaments/{tournament}.json")
    Call<Tournament> updateTournament(@Path("tournament") String tournament,
                                      @Body TournamentQuery tournamentData);

    @DELETE("tournaments/{tournament}.json")
    Call<Tournament> deleteTournament(@Path("tournament") String tournament);

    @POST("tournaments/{tournament}/process_check_ins.json")
    Call<Tournament> processCheckIns(@Path("tournament") String tournament,
                                     @Query("include_participants") int includeParticipants,
                                     @Query("include_matches") int includeMatches);

    @POST("tournaments/{tournament}/abort_check_in.json")
    Call<Tournament> abortCheckIn(@Path("tournament") String tournament,
                                  @Query("include_participants") int includeParticipants,
                                  @Query("include_matches") int includeMatches);

    @POST("tournaments/{tournament}/start.json")
    Call<Tournament> startTournament(@Path("tournament") String tournament,
                                     @Query("include_participants") int includeParticipants,
                                     @Query("include_matches") int includeMatches);

    @POST("tournaments/{tournament}/finalize.json")
    Call<Tournament> finalizeTournament(@Path("tournament") String tournament,
                                        @Query("include_participants") int includeParticipants,
                                        @Query("include_matches") int includeMatches);

    @POST("tournaments/{tournament}/reset.json")
    Call<Tournament> resetTournament(@Path("tournament") String tournament,
                                     @Query("include_participants") int includeParticipants,
                                     @Query("include_matches") int includeMatches);
}
