package com.exsoloscript.challonge.handler.sync;

import com.exsoloscript.challonge.handler.retrofit.RetrofitParticipantHandler;
import com.exsoloscript.challonge.handler.retrofit.ServiceProvider;
import com.exsoloscript.challonge.model.Participant;
import com.exsoloscript.challonge.model.query.ParticipantQuery;
import com.exsoloscript.challonge.model.exception.ChallongeException;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.io.IOException;
import java.util.List;

@Singleton
public class SyncParticipantHandler extends SyncHandler {

    private RetrofitParticipantHandler participantHandler;

    @Inject
    SyncParticipantHandler(ServiceProvider provider) {
        this.participantHandler = provider.createService(RetrofitParticipantHandler.class);
    }

    public List<Participant> getParticipants(String tournamentName) throws IOException, ChallongeException {
        return this.handleResponse(this.participantHandler.getParticipants(tournamentName)).body();
    }

    public Participant getParticipant(String tournamentName, int participantId, boolean includeMatches) throws IOException, ChallongeException {
        return this.handleResponse(this.participantHandler.getParticipant(tournamentName, participantId, includeMatches ? 1 : 0)).body();
    }

    public Participant addParticipant(String tournamentName, ParticipantQuery participant) throws IOException, ChallongeException {
        return this.handleResponse(this.participantHandler.addParticipant(tournamentName, participant)).body();
    }

    public Participant bulkAddParticipants(String tournamentName, List<ParticipantQuery> participants) throws IOException, ChallongeException {
        return this.handleResponse(this.participantHandler.bulkAddParticipants(tournamentName, participants)).body();
    }

    public Participant updateParticipant(String tournamentName, int participantId, ParticipantQuery participant) throws IOException, ChallongeException {
        return this.handleResponse(this.participantHandler.updateParticipant(tournamentName, participantId, participant)).body();
    }

    public Participant checkInParticipant(String tournamentName, int participantId) throws IOException, ChallongeException {
        return this.handleResponse(this.participantHandler.checkInParticipant(tournamentName, participantId)).body();
    }

    public Participant undoParticipantCheckIn(String tournamentName, int participantId) throws IOException, ChallongeException {
        return this.handleResponse(this.participantHandler.undoParticipantCheckIn(tournamentName, participantId)).body();
    }

    public Participant deleteParticipant(String tournamentName, int participantId) throws IOException, ChallongeException {
        return this.handleResponse(this.participantHandler.deleteParticipant(tournamentName, participantId)).body();
    }

    public List<Participant> randomizeParticipants(String tournamentName) throws IOException, ChallongeException {
        return this.handleResponse(this.participantHandler.randomizeParticipants(tournamentName)).body();
    }
}
