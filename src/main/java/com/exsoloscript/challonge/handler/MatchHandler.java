package com.exsoloscript.challonge.handler;

import com.exsoloscript.challonge.model.Match;
import com.exsoloscript.challonge.model.Match.MatchState;
import com.exsoloscript.challonge.model.MatchBase;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface MatchHandler extends ApiHandler {

    @GET("/tournaments/{tournament}/matches.json")
    Call<List<Match>> getMatches(@Path("tournament") String tournamentName, @Query("participant_id") int participantId, @Query("state") MatchState state);

    @GET("/tournaments/{tournament}/matches/{match_id}.json")
    Call<Match> getMatch(@Path("tournament") String tournamentName, @Query("match_id") int matchId, @Query("include_attachments") boolean includeAttachments);

    @PUT("/tournaments/{tournament}/matches/{match_id}.json")
    Call<Match> updateMatch(@Path("tournament") String tournamentName, @Query("match_id") int matchId, @Body MatchBase match);
}
