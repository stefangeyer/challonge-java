package com.exsoloscript.challonge.handler.sync;

import com.exsoloscript.challonge.handler.retrofit.RetrofitTournamentHandler;
import com.exsoloscript.challonge.model.Tournament;
import com.exsoloscript.challonge.model.query.TournamentQuery;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class TournamentHandler {

    private RetrofitTournamentHandler retrofitHandler;

    public TournamentHandler(RetrofitTournamentHandler retrofitHandler) {
        this.retrofitHandler = retrofitHandler;
    }

    public List<Tournament> getTournaments(Tournament.TournamentState state, Tournament.TournamentType type, String createdAfter, String createdBefore, String subdomain) throws IOException {
        return this.retrofitHandler.getTournaments(state, type, createdAfter, createdBefore, subdomain).execute().body();
    }

    public Tournament getTournament(String name, boolean includeParticipants, boolean includeMatches) throws IOException {
        return this.retrofitHandler.getTournament(name, includeParticipants, includeMatches).execute().body();
    }

    public Call<Tournament> createTournament(TournamentQuery tournament) {
        return null;
    }

    public Call<Tournament> updateTournament(String name, TournamentQuery tournament) {
        return null;
    }

    public Tournament deleteTournament(String name) throws IOException {
        Call<Tournament> tournamentCall = this.retrofitHandler.deleteTournament(name);
        Response<Tournament> callResponse = tournamentCall.execute();
        return callResponse.body();
    }

    public Call<Tournament> processCheckIns(String name, boolean includeParticipants, boolean includeMatches) {
        return null;
    }

    public Call<Tournament> abortCheckIn(String name, boolean includeParticipants, boolean includeMatches) {
        return null;
    }

    public Call<Tournament> startTournament(String name, boolean includeParticipants, boolean includeMatches) {
        return null;
    }

    public Call<Tournament> finalizeTournament(String name, boolean includeParticipants, boolean includeMatches) {
        return null;
    }

    public Call<Tournament> resetTournament(String name, boolean includeParticipants, boolean includeMatches) {
        return null;
    }
}
