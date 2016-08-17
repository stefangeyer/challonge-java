package com.exsoloscript.challonge.handler.sync;

import com.exsoloscript.challonge.handler.retrofit.RetrofitMatchHandler;
import com.exsoloscript.challonge.handler.retrofit.RetrofitServiceProvider;
import com.exsoloscript.challonge.model.Match;
import com.exsoloscript.challonge.model.enumeration.MatchState;
import com.exsoloscript.challonge.model.exception.ChallongeException;
import com.exsoloscript.challonge.model.query.MatchQuery;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.io.IOException;
import java.util.List;

@Singleton
public class SyncMatchHandler extends SyncHandler {

    private RetrofitMatchHandler matchHandler;

    @Inject
    SyncMatchHandler(RetrofitServiceProvider provider) {
        this.matchHandler = provider.createService(RetrofitMatchHandler.class);
    }

    public List<Match> getMatches(String tournamentName, int participantId, MatchState state) throws IOException, ChallongeException {
        return this.handleResponse(this.matchHandler.getMatches(tournamentName, participantId, state)).body();
    }

    public Match getMatch(String tournamentName, int matchId, boolean includeAttachments) throws IOException, ChallongeException {
        return this.handleResponse(matchHandler.getMatch(tournamentName, matchId, includeAttachments ? 1 : 0)).body();
    }

    public Match updateMatch(String tournamentName, int matchId, MatchQuery match) throws IOException, ChallongeException {
        return this.handleResponse(this.matchHandler.updateMatch(tournamentName, matchId, match)).body();
    }
}
