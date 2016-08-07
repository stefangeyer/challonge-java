package com.exsoloscript.challonge;

import com.exsoloscript.challonge.handler.AttachmentHandler;
import com.exsoloscript.challonge.handler.MatchHandler;
import com.exsoloscript.challonge.handler.ParticipantHandler;
import com.exsoloscript.challonge.handler.TournamentHandler;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class Challonge {

    private TournamentHandler tournaments;
    private ParticipantHandler participants;
    private MatchHandler matches;
    private AttachmentHandler attachments;

    Challonge(String username, String apiKey) {
        String baseUrl = "https://" + username + ":" + apiKey + "@api.challonge.com/v1/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        setupHandlers(retrofit);
    }

    private void setupHandlers(Retrofit retrofit) {
        this.tournaments = retrofit.create(TournamentHandler.class);
        this.participants = retrofit.create(ParticipantHandler.class);
        this.matches = retrofit.create(MatchHandler.class);
        this.attachments = retrofit.create(AttachmentHandler.class);
    }

    public TournamentHandler tournaments() {
        return tournaments;
    }

    public ParticipantHandler participants() {
        return participants;
    }

    public MatchHandler matches() {
        return matches;
    }

    public AttachmentHandler attachments() {
        return attachments;
    }
}
