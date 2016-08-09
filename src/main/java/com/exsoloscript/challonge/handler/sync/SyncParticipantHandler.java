package com.exsoloscript.challonge.handler.sync;

import com.exsoloscript.challonge.handler.retrofit.RetrofitParticipantHandler;
import com.exsoloscript.challonge.model.Participant;
import com.exsoloscript.challonge.model.ParticipantBase;

import java.io.IOException;
import java.util.List;

public class SyncParticipantHandler {

    private RetrofitParticipantHandler participantHandler;

    public SyncParticipantHandler(RetrofitParticipantHandler participantHandler) {
        this.participantHandler = participantHandler;
    }

    public List<Participant> getParticipants(String tournamentName) throws IOException {
        return this.participantHandler.getParticipants(tournamentName).execute().body();
    }

    public Participant getParticipant(String tournamentName, int participantId, boolean includeMatches) throws IOException {
        return this.participantHandler.getParticipant(tournamentName, participantId, includeMatches).execute().body();
    }

    public Participant addParticipant(String tournamentName, ParticipantBase participant) throws IOException {
        return this.participantHandler.addParticipant(tournamentName, participant).execute().body();
    }

    public Participant bulkAddParticipants(String tournamentName, List<ParticipantBase> participants) throws IOException {
        return this.participantHandler.bulkAddParticipants(tournamentName, participants).execute().body();
    }

    public Participant updateParticipant(String tournamentName, int participantId, ParticipantBase participant) throws IOException {
        return this.participantHandler.updateParticipant(tournamentName, participantId, participant).execute().body();
    }

    public Participant checkInParticipant(String tournamentName, int participantId) throws IOException {
        return this.participantHandler.checkInParticipant(tournamentName, participantId).execute().body();
    }

    public Participant undoParticipantCheckIn(String tournamentName, int participantId) throws IOException {
        return this.participantHandler.undoParticipantCheckIn(tournamentName, participantId).execute().body();
    }

    public Participant deleteParticipant(String tournamentName, int participantId) throws IOException {
        return this.participantHandler.deleteParticipant(tournamentName, participantId).execute().body();
    }

    public List<Participant> randomizeParticipants(String tournamentName) throws IOException {
        return this.participantHandler.randomizeParticipants(tournamentName).execute().body();
    }
}
