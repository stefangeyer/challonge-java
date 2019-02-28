package at.stefangeyer.challonge.model.query.wrapper;

import at.stefangeyer.challonge.model.query.ParticipantQuery;

public class ParticipantQueryWrapper {
    private ParticipantQuery participant;

    public ParticipantQueryWrapper(ParticipantQuery participant) {
        this.participant = participant;
    }

    public ParticipantQuery getParticipant() {
        return this.participant;
    }
}
