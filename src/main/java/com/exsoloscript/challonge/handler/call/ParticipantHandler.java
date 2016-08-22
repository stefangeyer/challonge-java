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

/**
 * Accessible API handler for participants.
 *
 * @author EXSolo
 * @version 20160822.1
 */
@Singleton
public class ParticipantHandler {

    private RetrofitParticipantHandler participantHandler;
    private RetrofitChallongeApiCallFactory factory;

    @Inject
    ParticipantHandler(RetrofitServiceProvider provider, RetrofitChallongeApiCallFactory factory) {
        this.participantHandler = provider.createService(RetrofitParticipantHandler.class);
        this.factory = factory;
    }

    /**
     * @see RetrofitParticipantHandler#getParticipants(String)
     */
    public ChallongeApiCall<List<Participant>> getParticipants(String tournament) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.participantHandler.getParticipants(tournament));
    }

    /**
     * @see RetrofitParticipantHandler#getParticipant(String, int, int)
     */
    public ChallongeApiCall<Participant> getParticipant(String tournament, int participantId, boolean includeMatches) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.participantHandler.getParticipant(tournament, participantId, includeMatches ? 1 : 0));
    }

    /**
     * @see RetrofitParticipantHandler#addParticipant(String, ParticipantQuery)
     */
    public ChallongeApiCall<Participant> addParticipant(String tournament, ParticipantQuery participant) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.participantHandler.addParticipant(tournament, participant));
    }

    /**
     * @see RetrofitParticipantHandler#bulkAddParticipants(String, List)
     */
    public ChallongeApiCall<Participant> bulkAddParticipants(String tournament, List<ParticipantQuery> participants) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.participantHandler.bulkAddParticipants(tournament, participants));
    }

    /**
     * @see RetrofitParticipantHandler#updateParticipant(String, int, ParticipantQuery)
     */
    public ChallongeApiCall<Participant> updateParticipant(String tournament, int participantId, ParticipantQuery participant) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.participantHandler.updateParticipant(tournament, participantId, participant));
    }

    /**
     * @see RetrofitParticipantHandler#checkInParticipant(String, int)
     */
    public ChallongeApiCall<Participant> checkInParticipant(String tournament, int participantId) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.participantHandler.checkInParticipant(tournament, participantId));
    }

    /**
     * @see RetrofitParticipantHandler#undoParticipantCheckIn(String, int)
     */
    public ChallongeApiCall<Participant> undoParticipantCheckIn(String tournament, int participantId) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.participantHandler.undoParticipantCheckIn(tournament, participantId));
    }

    /**
     * @see RetrofitParticipantHandler#deleteParticipant(String, int)
     */
    public ChallongeApiCall<Participant> deleteParticipant(String tournament, int participantId) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.participantHandler.deleteParticipant(tournament, participantId));
    }

    /**
     * @see RetrofitParticipantHandler#randomizeParticipants(String)
     */
    public ChallongeApiCall<List<Participant>> randomizeParticipants(String tournament) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.participantHandler.randomizeParticipants(tournament));
    }
}
