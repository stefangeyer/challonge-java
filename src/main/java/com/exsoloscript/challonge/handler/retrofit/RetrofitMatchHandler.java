package com.exsoloscript.challonge.handler.retrofit;

import com.exsoloscript.challonge.model.Match;
import com.exsoloscript.challonge.model.enumeration.MatchState;
import com.exsoloscript.challonge.model.query.MatchQuery;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface RetrofitMatchHandler {

    @GET("/tournaments/{tournament}/matches.json")
    Call<List<Match>> getMatches(@Path("tournament") String tournamentName,
                                 @Query("participant_id") int participantId,
                                 @Query("state") MatchState state);

    @GET("/tournaments/{tournament}/matches/{match_id}.json")
    Call<Match> getMatch(@Path("tournament") String tournamentName,
                         @Query("match_id") int matchId,
                         @Query("include_attachments") int includeAttachments);

    @PUT("/tournaments/{tournament}/matches/{match_id}.json")
    Call<Match> updateMatch(@Path("tournament") String tournamentName,
                            @Query("match_id") int matchId,
                            @Body MatchQuery match);
}
