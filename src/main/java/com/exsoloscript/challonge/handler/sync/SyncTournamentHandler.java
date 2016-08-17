package com.exsoloscript.challonge.handler.sync;

import com.exsoloscript.challonge.handler.retrofit.RetrofitTournamentHandler;
import com.exsoloscript.challonge.handler.retrofit.RetrofitServiceProvider;
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
public class SyncTournamentHandler extends SyncHandler {

    private RetrofitTournamentHandler tournamentHandler;

    @Inject
    SyncTournamentHandler(RetrofitServiceProvider provider) {
        this.tournamentHandler = provider.createService(RetrofitTournamentHandler.class);
    }

    public List<Tournament> getTournaments(TournamentState state, TournamentType type, String createdAfter, String createdBefore, String subdomain) throws IOException, ChallongeException {
        return this.handleResponse(this.tournamentHandler.getTournaments(state, type, createdAfter, createdBefore, subdomain)).body();
    }

    public Tournament getTournament(String name, boolean includeParticipants, boolean includeMatches) throws IOException, ChallongeException {
        return this.handleResponse(this.tournamentHandler.getTournament(name, includeParticipants ? 1 : 0, includeMatches ? 1 : 0)).body();
    }

    public Tournament createTournament(TournamentQuery tournament) throws IOException, ChallongeException {
        return this.handleResponse(this.tournamentHandler.createTournament(tournament)).body();
    }

    public Tournament updateTournament(String name, TournamentQuery tournament) throws IOException, ChallongeException {
        return this.handleResponse(this.tournamentHandler.updateTournament(name, tournament)).body();
    }

    public Tournament deleteTournament(String name) throws IOException, ChallongeException {
        return this.handleResponse(this.tournamentHandler.deleteTournament(name)).body();
    }

    public Tournament processCheckIns(String name, boolean includeParticipants, boolean includeMatches) throws IOException, ChallongeException {
        return this.handleResponse(this.tournamentHandler.processCheckIns(name, includeParticipants ? 1 : 0, includeMatches ? 1 : 0)).body();
    }

    public Tournament abortCheckIn(String name, boolean includeParticipants, boolean includeMatches) throws IOException, ChallongeException {
        return this.handleResponse(this.tournamentHandler.abortCheckIn(name, includeParticipants ? 1 : 0, includeMatches ? 1 : 0)).body();
    }

    public Tournament startTournament(String name, boolean includeParticipants, boolean includeMatches) throws IOException, ChallongeException {
        return this.handleResponse(this.tournamentHandler.startTournament(name, includeParticipants ? 1 : 0, includeMatches ? 1 : 0)).body();
    }

    public Tournament finalizeTournament(String name, boolean includeParticipants, boolean includeMatches) throws IOException, ChallongeException {
        return this.handleResponse(this.tournamentHandler.finalizeTournament(name, includeParticipants ? 1 : 0, includeMatches ? 1 : 0)).body();
    }

    public Tournament resetTournament(String name, boolean includeParticipants, boolean includeMatches) throws IOException, ChallongeException {
        return this.handleResponse(this.tournamentHandler.resetTournament(name, includeParticipants ? 1 : 0, includeMatches ? 1 : 0)).body();
    }
}
