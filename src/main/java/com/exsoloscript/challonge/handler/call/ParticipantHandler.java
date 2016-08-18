package com.exsoloscript.challonge.handler.call;

import com.exsoloscript.challonge.handler.retrofit.RetrofitParticipantHandler;
import com.exsoloscript.challonge.handler.retrofit.RetrofitServiceProvider;
import com.exsoloscript.challonge.model.Participant;
import com.exsoloscript.challonge.model.query.ParticipantQuery;
import com.exsoloscript.challonge.model.exception.ChallongeException;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.io.IOException;
import java.util.List;

@Singleton
public class ParticipantHandler {

    private RetrofitParticipantHandler participantHandler;
    private ChallongeApiCallFactory factory;

    @Inject
    ParticipantHandler(RetrofitServiceProvider provider, ChallongeApiCallFactory factory) {
        this.participantHandler = provider.createService(RetrofitParticipantHandler.class);
        this.factory = factory;
    }

    public ChallongeApiCall<List<Participant>> getParticipants(String tournamentName) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.participantHandler.getParticipants(tournamentName));
    }

    public ChallongeApiCall<Participant> getParticipant(String tournamentName, int participantId, boolean includeMatches) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.participantHandler.getParticipant(tournamentName, participantId, includeMatches ? 1 : 0));
    }

    public ChallongeApiCall<Participant> addParticipant(String tournamentName, ParticipantQuery participant) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.participantHandler.addParticipant(tournamentName, participant));
    }

    public ChallongeApiCall<Participant> bulkAddParticipants(String tournamentName, List<ParticipantQuery> participants) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.participantHandler.bulkAddParticipants(tournamentName, participants));
    }

    public ChallongeApiCall<Participant> updateParticipant(String tournamentName, int participantId, ParticipantQuery participant) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.participantHandler.updateParticipant(tournamentName, participantId, participant));
    }

    public ChallongeApiCall<Participant> checkInParticipant(String tournamentName, int participantId) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.participantHandler.checkInParticipant(tournamentName, participantId));
    }

    public ChallongeApiCall<Participant> undoParticipantCheckIn(String tournamentName, int participantId) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.participantHandler.undoParticipantCheckIn(tournamentName, participantId));
    }

    public ChallongeApiCall<Participant> deleteParticipant(String tournamentName, int participantId) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.participantHandler.deleteParticipant(tournamentName, participantId));
    }

    public ChallongeApiCall<List<Participant>> randomizeParticipants(String tournamentName) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.participantHandler.randomizeParticipants(tournamentName));
    }
}
