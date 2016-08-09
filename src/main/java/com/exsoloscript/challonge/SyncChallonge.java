package com.exsoloscript.challonge;

import com.exsoloscript.challonge.handler.retrofit.RetrofitAttachmentHandler;
import com.exsoloscript.challonge.handler.retrofit.RetrofitMatchHandler;
import com.exsoloscript.challonge.handler.retrofit.RetrofitParticipantHandler;
import com.exsoloscript.challonge.handler.retrofit.RetrofitTournamentHandler;
import com.exsoloscript.challonge.handler.sync.SyncAttachmentHandler;
import com.exsoloscript.challonge.handler.sync.SyncMatchHandler;
import com.exsoloscript.challonge.handler.sync.SyncParticipantHandler;
import com.exsoloscript.challonge.handler.sync.SyncTournamentHandler;
import retrofit2.Retrofit;

class SyncChallonge {
    private SyncTournamentHandler tournaments;
    private SyncParticipantHandler participants;
    private SyncMatchHandler matches;
    private SyncAttachmentHandler attachments;

    public SyncChallonge(Retrofit retrofit) {
        this.tournaments = new SyncTournamentHandler(retrofit.create(RetrofitTournamentHandler.class));
        this.participants = new SyncParticipantHandler(retrofit.create(RetrofitParticipantHandler.class));
        this.matches = new SyncMatchHandler(retrofit.create(RetrofitMatchHandler.class));
        this.attachments = new SyncAttachmentHandler(retrofit.create(RetrofitAttachmentHandler.class));
    }

    public SyncTournamentHandler tournaments() {
        return tournaments;
    }

    public SyncParticipantHandler participants() {
        return participants;
    }

    public SyncMatchHandler matches() {
        return matches;
    }

    public SyncAttachmentHandler attachments() {
        return attachments;
    }
}
