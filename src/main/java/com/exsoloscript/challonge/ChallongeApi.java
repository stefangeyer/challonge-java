package com.exsoloscript.challonge;

import com.exsoloscript.challonge.handler.call.AttachmentHandler;
import com.exsoloscript.challonge.handler.call.MatchHandler;
import com.exsoloscript.challonge.handler.call.ParticipantHandler;
import com.exsoloscript.challonge.handler.call.TournamentHandler;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ChallongeApi {

    @Inject
    private TournamentHandler tournaments;

    @Inject
    private ParticipantHandler participants;

    @Inject
    private MatchHandler matches;

    @Inject
    private AttachmentHandler attachments;

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