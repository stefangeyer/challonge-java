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
        Call<List<Tournament>> tournamentCall = this.retrofitHandler.getTournaments(state, type, createdAfter, createdBefore, subdomain);
        Response<List<Tournament>> callResponse = tournamentCall.execute();
        return callResponse.body();
    }

    public Call<Tournament> getTournament(String name, boolean includeParticipants, boolean includeMatches) {
        return null;
    }

    public Call<Tournament> createTournament(TournamentQuery tournament) {
        return null;
    }

    public Call<Tournament> updateTournament(String name, TournamentQuery tournament) {
        return null;
    }

    public Call<Tournament> deleteTournament(String name) {
        return null;
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
