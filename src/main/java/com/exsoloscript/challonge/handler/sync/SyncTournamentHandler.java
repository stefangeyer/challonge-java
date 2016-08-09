package com.exsoloscript.challonge.handler.sync;

import com.exsoloscript.challonge.handler.retrofit.RetrofitTournamentHandler;
import com.exsoloscript.challonge.model.Tournament;
import com.exsoloscript.challonge.model.enumeration.TournamentState;
import com.exsoloscript.challonge.model.enumeration.TournamentType;
import com.exsoloscript.challonge.model.query.TournamentQuery;

import java.io.IOException;
import java.util.List;

public class SyncTournamentHandler {

    private RetrofitTournamentHandler retrofitHandler;

    public SyncTournamentHandler(RetrofitTournamentHandler retrofitHandler) {
        this.retrofitHandler = retrofitHandler;
    }

    public List<Tournament> getTournaments(TournamentState state, TournamentType type, String createdAfter, String createdBefore, String subdomain) throws IOException {
        return this.retrofitHandler.getTournaments(state, type, createdAfter, createdBefore, subdomain).execute().body();
    }

    public Tournament getTournament(String name, boolean includeParticipants, boolean includeMatches) throws IOException {
        return this.retrofitHandler.getTournament(name, includeParticipants, includeMatches).execute().body();
    }

    public Tournament createTournament(TournamentQuery tournament) throws IOException {
        return this.retrofitHandler.createTournament(tournament).execute().body();
    }

    public Tournament updateTournament(String name, TournamentQuery tournament) throws IOException {
        return this.retrofitHandler.updateTournament(name, tournament).execute().body();
    }

    public Tournament deleteTournament(String name) throws IOException {
        return this.retrofitHandler.deleteTournament(name).execute().body();
    }

    public Tournament processCheckIns(String name, boolean includeParticipants, boolean includeMatches) throws IOException {
        return this.retrofitHandler.processCheckIns(name, includeParticipants, includeMatches).execute().body();
    }

    public Tournament abortCheckIn(String name, boolean includeParticipants, boolean includeMatches) throws IOException {
        return this.retrofitHandler.abortCheckIn(name, includeParticipants, includeMatches).execute().body();
    }

    public Tournament startTournament(String name, boolean includeParticipants, boolean includeMatches) throws IOException {
        return this.retrofitHandler.startTournament(name, includeParticipants, includeMatches).execute().body();
    }

    public Tournament finalizeTournament(String name, boolean includeParticipants, boolean includeMatches) throws IOException {
        return this.retrofitHandler.finalizeTournament(name, includeParticipants, includeMatches).execute().body();
    }

    public Tournament resetTournament(String name, boolean includeParticipants, boolean includeMatches) throws IOException {
        return this.retrofitHandler.resetTournament(name, includeParticipants, includeMatches).execute().body();
    }
}
