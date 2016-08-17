package com.exsoloscript.challonge.handler.async;

import com.exsoloscript.challonge.handler.async.callback.AsyncCallback;
import com.exsoloscript.challonge.handler.retrofit.RetrofitServiceProvider;
import com.exsoloscript.challonge.handler.retrofit.RetrofitTournamentHandler;
import com.exsoloscript.challonge.model.Tournament;
import com.exsoloscript.challonge.model.enumeration.TournamentState;
import com.exsoloscript.challonge.model.enumeration.TournamentType;
import com.exsoloscript.challonge.model.exception.ChallongeException;
import com.exsoloscript.challonge.model.query.TournamentQuery;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.io.IOException;
import java.util.List;

@Singleton
public class AsyncTournamentHandler extends AsyncHandler {

    private RetrofitTournamentHandler tournamentHandler;

    @Inject
    AsyncTournamentHandler(RetrofitServiceProvider provider) {
        this.tournamentHandler = provider.createService(RetrofitTournamentHandler.class);
    }

    public void getTournaments(TournamentState state, TournamentType type, String createdAfter, String createdBefore, String subdomain, AsyncCallback<List<Tournament>> callback) throws IOException, ChallongeException {
        this.handleResponse(this.tournamentHandler.getTournaments(state, type, createdAfter, createdBefore, subdomain), callback);
    }

    public void getTournament(String name, boolean includeParticipants, boolean includeMatches, AsyncCallback<Tournament> callback) throws IOException, ChallongeException {
        this.handleResponse(this.tournamentHandler.getTournament(name, includeParticipants ? 1 : 0, includeMatches ? 1 : 0), callback);
    }

    public void createTournament(TournamentQuery tournament, AsyncCallback<Tournament> callback) throws IOException, ChallongeException {
        this.handleResponse(this.tournamentHandler.createTournament(tournament), callback);
    }

    public void updateTournament(String name, TournamentQuery tournament, AsyncCallback<Tournament> callback) throws IOException, ChallongeException {
        this.handleResponse(this.tournamentHandler.updateTournament(name, tournament), callback);
    }

    public void deleteTournament(String name, AsyncCallback<Tournament> callback) throws IOException, ChallongeException {
        this.handleResponse(this.tournamentHandler.deleteTournament(name), callback);
    }

    public void processCheckIns(String name, boolean includeParticipants, boolean includeMatches, AsyncCallback<Tournament> callback) throws IOException, ChallongeException {
        this.handleResponse(this.tournamentHandler.processCheckIns(name, includeParticipants ? 1 : 0, includeMatches ? 1 : 0), callback);
    }

    public void abortCheckIn(String name, boolean includeParticipants, boolean includeMatches, AsyncCallback<Tournament> callback) throws IOException, ChallongeException {
        this.handleResponse(this.tournamentHandler.abortCheckIn(name, includeParticipants ? 1 : 0, includeMatches ? 1 : 0), callback);
    }

    public void startTournament(String name, boolean includeParticipants, boolean includeMatches, AsyncCallback<Tournament> callback) throws IOException, ChallongeException {
        this.handleResponse(this.tournamentHandler.startTournament(name, includeParticipants ? 1 : 0, includeMatches ? 1 : 0), callback);
    }

    public void finalizeTournament(String name, boolean includeParticipants, boolean includeMatches, AsyncCallback<Tournament> callback) throws IOException, ChallongeException {
        this.handleResponse(this.tournamentHandler.finalizeTournament(name, includeParticipants ? 1 : 0, includeMatches ? 1 : 0), callback);
    }

    public void resetTournament(String name, boolean includeParticipants, boolean includeMatches, AsyncCallback<Tournament> callback) throws IOException, ChallongeException {
        this.handleResponse(this.tournamentHandler.resetTournament(name, includeParticipants ? 1 : 0, includeMatches ? 1 : 0), callback);
    }
}
