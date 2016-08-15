package com.exsoloscript.challonge.handler.sync;

import com.exsoloscript.challonge.handler.retrofit.RetrofitTournamentHandler;
import com.exsoloscript.challonge.handler.retrofit.ServiceProvider;
import com.exsoloscript.challonge.model.Tournament;
import com.exsoloscript.challonge.model.enumeration.TournamentState;
import com.exsoloscript.challonge.model.enumeration.TournamentType;
import com.exsoloscript.challonge.model.query.TournamentQuery;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.io.IOException;
import java.util.List;

@Singleton
public class SyncTournamentHandler extends SyncHandler {

    private RetrofitTournamentHandler tournamentHandler;

    @Inject
    SyncTournamentHandler(ServiceProvider provider) {
        this.tournamentHandler = provider.createService(RetrofitTournamentHandler.class);
    }

    public List<Tournament> getTournaments(TournamentState state, TournamentType type, String createdAfter, String createdBefore, String subdomain) throws IOException {
        return this.handleResponse(this.tournamentHandler.getTournaments(state, type, createdAfter, createdBefore, subdomain)).body();
    }

    public Tournament getTournament(String name, boolean includeParticipants, boolean includeMatches) throws IOException {
        return this.handleResponse(this.tournamentHandler.getTournament(name, includeParticipants, includeMatches)).body();
    }

    public Tournament createTournament(TournamentQuery tournament) throws IOException {
        return this.handleResponse(this.tournamentHandler.createTournament(tournament)).body();
    }

    public Tournament updateTournament(String name, TournamentQuery tournament) throws IOException {
        return this.handleResponse(this.tournamentHandler.updateTournament(name, tournament)).body();
    }

    public Tournament deleteTournament(String name) throws IOException {
        return this.handleResponse(this.tournamentHandler.deleteTournament(name)).body();
    }

    public Tournament processCheckIns(String name, boolean includeParticipants, boolean includeMatches) throws IOException {
        return this.handleResponse(this.tournamentHandler.processCheckIns(name, includeParticipants, includeMatches)).body();
    }

    public Tournament abortCheckIn(String name, boolean includeParticipants, boolean includeMatches) throws IOException {
        return this.handleResponse(this.tournamentHandler.abortCheckIn(name, includeParticipants, includeMatches)).body();
    }

    public Tournament startTournament(String name, boolean includeParticipants, boolean includeMatches) throws IOException {
        return this.handleResponse(this.tournamentHandler.startTournament(name, includeParticipants, includeMatches)).body();
    }

    public Tournament finalizeTournament(String name, boolean includeParticipants, boolean includeMatches) throws IOException {
        return this.handleResponse(this.tournamentHandler.finalizeTournament(name, includeParticipants, includeMatches)).body();
    }

    public Tournament resetTournament(String name, boolean includeParticipants, boolean includeMatches) throws IOException {
        return this.handleResponse(this.tournamentHandler.resetTournament(name, includeParticipants, includeMatches)).body();
    }
}
