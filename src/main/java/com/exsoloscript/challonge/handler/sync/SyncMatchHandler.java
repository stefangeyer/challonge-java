package com.exsoloscript.challonge.handler.sync;

import com.exsoloscript.challonge.handler.retrofit.RetrofitMatchHandler;
import com.exsoloscript.challonge.model.Match;
import com.exsoloscript.challonge.model.enumeration.MatchState;
import com.exsoloscript.challonge.model.query.MatchQuery;

import java.io.IOException;
import java.util.List;

public class SyncMatchHandler extends SyncHandler {

    private RetrofitMatchHandler matchHandler;

    public SyncMatchHandler(RetrofitMatchHandler matchHandler) {
        this.matchHandler = matchHandler;
    }

    public List<Match> getMatches(String tournamentName, int participantId, MatchState state) throws IOException {
        return this.handleResponse(this.matchHandler.getMatches(tournamentName, participantId, state)).body();
    }

    public Match getMatch(String tournamentName, int matchId, boolean includeAttachments) throws IOException {
        return this.handleResponse(matchHandler.getMatch(tournamentName, matchId, includeAttachments)).body();
    }

    public Match updateMatch(String tournamentName, int matchId, MatchQuery match) throws IOException {
        return this.handleResponse(this.matchHandler.updateMatch(tournamentName, matchId, match)).body();
    }
}
