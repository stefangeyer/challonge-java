package com.exsoloscript.challonge.handler.sync;

import com.exsoloscript.challonge.handler.retrofit.RetrofitParticipantHandler;
import com.exsoloscript.challonge.model.Participant;
import com.exsoloscript.challonge.model.ParticipantBase;

import java.io.IOException;
import java.util.List;

public class SyncParticipantHandler extends SyncHandler {

    private RetrofitParticipantHandler participantHandler;

    public SyncParticipantHandler(RetrofitParticipantHandler participantHandler) {
        this.participantHandler = participantHandler;
    }

    public List<Participant> getParticipants(String tournamentName) throws IOException {
        return this.handleResponse(this.participantHandler.getParticipants(tournamentName)).body();
    }

    public Participant getParticipant(String tournamentName, int participantId, boolean includeMatches) throws IOException {
        return this.handleResponse(this.participantHandler.getParticipant(tournamentName, participantId, includeMatches)).body();
    }

    public Participant addParticipant(String tournamentName, ParticipantBase participant) throws IOException {
        return this.handleResponse(this.participantHandler.addParticipant(tournamentName, participant)).body();
    }

    public Participant bulkAddParticipants(String tournamentName, List<ParticipantBase> participants) throws IOException {
        return this.handleResponse(this.participantHandler.bulkAddParticipants(tournamentName, participants)).body();
    }

    public Participant updateParticipant(String tournamentName, int participantId, ParticipantBase participant) throws IOException {
        return this.handleResponse(this.participantHandler.updateParticipant(tournamentName, participantId, participant)).body();
    }

    public Participant checkInParticipant(String tournamentName, int participantId) throws IOException {
        return this.handleResponse(this.participantHandler.checkInParticipant(tournamentName, participantId)).body();
    }

    public Participant undoParticipantCheckIn(String tournamentName, int participantId) throws IOException {
        return this.handleResponse(this.participantHandler.undoParticipantCheckIn(tournamentName, participantId)).body();
    }

    public Participant deleteParticipant(String tournamentName, int participantId) throws IOException {
        return this.handleResponse(this.participantHandler.deleteParticipant(tournamentName, participantId)).body();
    }

    public List<Participant> randomizeParticipants(String tournamentName) throws IOException {
        return this.handleResponse(this.participantHandler.randomizeParticipants(tournamentName)).body();
    }
}
