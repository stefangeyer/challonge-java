package com.exsoloscript.challonge;

import com.exsoloscript.challonge.handler.sync.SyncAttachmentHandler;
import com.exsoloscript.challonge.handler.sync.SyncMatchHandler;
import com.exsoloscript.challonge.handler.sync.SyncParticipantHandler;
import com.exsoloscript.challonge.handler.sync.SyncTournamentHandler;
import com.google.inject.Inject;

public class SyncChallonge {

    private SyncTournamentHandler tournaments;
    private SyncParticipantHandler participants;
    private SyncMatchHandler matches;
    private SyncAttachmentHandler attachments;

    @Inject
    SyncChallonge(SyncTournamentHandler tournaments, SyncParticipantHandler participants, SyncMatchHandler matches, SyncAttachmentHandler attachments) {
        this.tournaments = tournaments;
        this.participants = participants;
        this.matches = matches;
        this.attachments = attachments;
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
