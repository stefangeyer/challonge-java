package com.exsoloscript.challonge.handler.call;

import com.exsoloscript.challonge.handler.retrofit.RetrofitParticipantHandler;
import com.exsoloscript.challonge.handler.retrofit.RetrofitServiceProvider;
import com.exsoloscript.challonge.model.Participant;
import com.exsoloscript.challonge.model.exception.ChallongeException;
import com.exsoloscript.challonge.model.query.ParticipantQuery;
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

    public ChallongeApiCall<List<Participant>> getParticipants(String tournament) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.participantHandler.getParticipants(tournament));
    }

    public ChallongeApiCall<Participant> getParticipant(String tournament, int participantId, boolean includeMatches) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.participantHandler.getParticipant(tournament, participantId, includeMatches ? 1 : 0));
    }

    public ChallongeApiCall<Participant> addParticipant(String tournament, ParticipantQuery participant) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.participantHandler.addParticipant(tournament, participant));
    }

    public ChallongeApiCall<Participant> bulkAddParticipants(String tournament, List<ParticipantQuery> participants) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.participantHandler.bulkAddParticipants(tournament, participants));
    }

    public ChallongeApiCall<Participant> updateParticipant(String tournament, int participantId, ParticipantQuery participant) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.participantHandler.updateParticipant(tournament, participantId, participant));
    }

    public ChallongeApiCall<Participant> checkInParticipant(String tournament, int participantId) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.participantHandler.checkInParticipant(tournament, participantId));
    }

    public ChallongeApiCall<Participant> undoParticipantCheckIn(String tournament, int participantId) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.participantHandler.undoParticipantCheckIn(tournament, participantId));
    }

    public ChallongeApiCall<Participant> deleteParticipant(String tournament, int participantId) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.participantHandler.deleteParticipant(tournament, participantId));
    }

    public ChallongeApiCall<List<Participant>> randomizeParticipants(String tournament) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.participantHandler.randomizeParticipants(tournament));
    }
}
