package com.exsoloscript.challonge;

import com.exsoloscript.challonge.handler.retrofit.RetrofitAttachmentHandler;
import com.exsoloscript.challonge.handler.retrofit.RetrofitMatchHandler;
import com.exsoloscript.challonge.handler.retrofit.RetrofitParticipantHandler;
import com.exsoloscript.challonge.handler.retrofit.RetrofitTournamentHandler;
import com.google.inject.Inject;
import retrofit2.Retrofit;

class AsyncChallonge {
    private RetrofitTournamentHandler tournaments;
    private RetrofitParticipantHandler participants;
    private RetrofitMatchHandler matches;
    private RetrofitAttachmentHandler attachments;

    @Inject
    public AsyncChallonge(Retrofit retrofit) {
        this.tournaments = retrofit.create(RetrofitTournamentHandler.class);
        this.participants = retrofit.create(RetrofitParticipantHandler.class);
        this.matches = retrofit.create(RetrofitMatchHandler.class);
        this.attachments = retrofit.create(RetrofitAttachmentHandler.class);
    }

    public RetrofitTournamentHandler tournaments() {
        return tournaments;
    }

    public RetrofitParticipantHandler participants() {
        return participants;
    }

    public RetrofitMatchHandler matches() {
        return matches;
    }

    public RetrofitAttachmentHandler attachments() {
        return attachments;
    }
}