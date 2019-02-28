package at.stefangeyer.challonge.model.wrapper;

import at.stefangeyer.challonge.model.Participant;

/**
 * Wrapper for easy JSON serialisation of a Participant
 *
 * @author Stefan Geyer
 * @version 2018-07-08
 */
public class ParticipantWrapper {
    private Participant participant;

    public ParticipantWrapper(Participant participant) {
        this.participant = participant;
    }

    public Participant getParticipant() {
        return this.participant;
    }
}
