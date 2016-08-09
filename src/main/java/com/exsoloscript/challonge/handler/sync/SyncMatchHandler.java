package com.exsoloscript.challonge.handler.sync;

import com.exsoloscript.challonge.handler.retrofit.RetrofitMatchHandler;
import com.exsoloscript.challonge.model.Match;
import com.exsoloscript.challonge.model.MatchBase;

import java.io.IOException;
import java.util.List;

public class SyncMatchHandler {

    private RetrofitMatchHandler matchHandler;

    public SyncMatchHandler(RetrofitMatchHandler matchHandler) {
        this.matchHandler = matchHandler;
    }

    public List<Match> getMatches(String tournamentName, int participantId, Match.MatchState state) throws IOException {
        return this.matchHandler.getMatches(tournamentName, participantId, state).execute().body();
    }

    public Match getMatch(String tournamentName, int matchId, boolean includeAttachments) throws IOException {
        return this.matchHandler.getMatch(tournamentName, matchId, includeAttachments).execute().body();
    }

    public Match updateMatch(String tournamentName, int matchId, MatchBase match) throws IOException {
        return this.matchHandler.updateMatch(tournamentName, matchId, match).execute().body();
    }
}
