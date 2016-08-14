package com.exsoloscript.challonge.handler.sync;

import com.exsoloscript.challonge.handler.retrofit.RetrofitTournamentHandler;
import com.exsoloscript.challonge.model.Tournament;
import com.exsoloscript.challonge.model.enumeration.TournamentState;
import com.exsoloscript.challonge.model.enumeration.TournamentType;
import com.exsoloscript.challonge.model.query.TournamentQuery;

import java.io.IOException;
import java.util.List;

public class SyncTournamentHandler extends SyncHandler {

    private RetrofitTournamentHandler retrofitHandler;

    public SyncTournamentHandler(RetrofitTournamentHandler retrofitHandler) {
        this.retrofitHandler = retrofitHandler;
    }

    public List<Tournament> getTournaments(TournamentState state, TournamentType type, String createdAfter, String createdBefore, String subdomain) throws IOException {
        return this.handleResponse(this.retrofitHandler.getTournaments(state, type, createdAfter, createdBefore, subdomain)).body();
    }

    public Tournament getTournament(String name, boolean includeParticipants, boolean includeMatches) throws IOException {
        return this.handleResponse(this.retrofitHandler.getTournament(name, includeParticipants, includeMatches)).body();
    }

    public Tournament createTournament(TournamentQuery tournament) throws IOException {
        return this.handleResponse(this.retrofitHandler.createTournament(tournament)).body();
    }

    public Tournament updateTournament(String name, TournamentQuery tournament) throws IOException {
        return this.handleResponse(this.retrofitHandler.updateTournament(name, tournament)).body();
    }

    public Tournament deleteTournament(String name) throws IOException {
        return this.handleResponse(this.retrofitHandler.deleteTournament(name)).body();
    }

    public Tournament processCheckIns(String name, boolean includeParticipants, boolean includeMatches) throws IOException {
        return this.handleResponse(this.retrofitHandler.processCheckIns(name, includeParticipants, includeMatches)).body();
    }

    public Tournament abortCheckIn(String name, boolean includeParticipants, boolean includeMatches) throws IOException {
        return this.handleResponse(this.retrofitHandler.abortCheckIn(name, includeParticipants, includeMatches)).body();
    }

    public Tournament startTournament(String name, boolean includeParticipants, boolean includeMatches) throws IOException {
        return this.handleResponse(this.retrofitHandler.startTournament(name, includeParticipants, includeMatches)).body();
    }

    public Tournament finalizeTournament(String name, boolean includeParticipants, boolean includeMatches) throws IOException {
        return this.handleResponse(this.retrofitHandler.finalizeTournament(name, includeParticipants, includeMatches)).body();
    }

    public Tournament resetTournament(String name, boolean includeParticipants, boolean includeMatches) throws IOException {
        return this.handleResponse(this.retrofitHandler.resetTournament(name, includeParticipants, includeMatches)).body();
    }
}
