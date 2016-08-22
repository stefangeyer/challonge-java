package com.exsoloscript.challonge;

import com.exsoloscript.challonge.handler.call.AttachmentHandler;
import com.exsoloscript.challonge.handler.call.MatchHandler;
import com.exsoloscript.challonge.handler.call.ParticipantHandler;
import com.exsoloscript.challonge.handler.call.TournamentHandler;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * A collection of all the available handlers.
 * For more details see the individual handlers.
 *
 * @author EXSolo
 * @version 20160822.1
 */
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

    /**
     * @return TournamentHandler
     */
    public TournamentHandler tournaments() {
        return tournaments;
    }

    /**
     * @return ParticipantHandler
     */
    public ParticipantHandler participants() {
        return participants;
    }

    /**
     * @return MatchHandler
     */
    public MatchHandler matches() {
        return matches;
    }

    /**
     * @return AttachmentHandler
     */
    public AttachmentHandler attachments() {
        return attachments;
    }
}