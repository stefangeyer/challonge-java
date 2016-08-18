package com.exsoloscript.challonge.handler.call;

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
public class TournamentHandler {

    private RetrofitTournamentHandler tournamentHandler;
    private ChallongeApiCallFactory factory;

    @Inject
    TournamentHandler(RetrofitServiceProvider provider, ChallongeApiCallFactory factory) {
        this.tournamentHandler = provider.createService(RetrofitTournamentHandler.class);
        this.factory = factory;
    }

    public ChallongeApiCall<List<Tournament>> getTournaments(TournamentState state, TournamentType type, String createdAfter, String createdBefore, String subdomain) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.tournamentHandler.getTournaments(state, type, createdAfter, createdBefore, subdomain));
    }

    public ChallongeApiCall<Tournament> getTournament(String name, boolean includeParticipants, boolean includeMatches) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.tournamentHandler.getTournament(name, includeParticipants ? 1 : 0, includeMatches ? 1 : 0));
    }

    public ChallongeApiCall<Tournament> createTournament(TournamentQuery tournament) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.tournamentHandler.createTournament(tournament));
    }

    public ChallongeApiCall<Tournament> updateTournament(String name, TournamentQuery tournament) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.tournamentHandler.updateTournament(name, tournament));
    }

    public ChallongeApiCall<Tournament> deleteTournament(String name) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.tournamentHandler.deleteTournament(name));
    }

    public ChallongeApiCall<Tournament> processCheckIns(String name, boolean includeParticipants, boolean includeMatches) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.tournamentHandler.processCheckIns(name, includeParticipants ? 1 : 0, includeMatches ? 1 : 0));
    }

    public ChallongeApiCall<Tournament> abortCheckIn(String name, boolean includeParticipants, boolean includeMatches) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.tournamentHandler.abortCheckIn(name, includeParticipants ? 1 : 0, includeMatches ? 1 : 0));
    }

    public ChallongeApiCall<Tournament> startTournament(String name, boolean includeParticipants, boolean includeMatches) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.tournamentHandler.startTournament(name, includeParticipants ? 1 : 0, includeMatches ? 1 : 0));
    }

    public ChallongeApiCall<Tournament> finalizeTournament(String name, boolean includeParticipants, boolean includeMatches) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.tournamentHandler.finalizeTournament(name, includeParticipants ? 1 : 0, includeMatches ? 1 : 0));
    }

    public ChallongeApiCall<Tournament> resetTournament(String name, boolean includeParticipants, boolean includeMatches) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.tournamentHandler.resetTournament(name, includeParticipants ? 1 : 0, includeMatches ? 1 : 0));
    }
}
