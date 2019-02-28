package at.stefangeyer.challonge.model.wrapper;

import at.stefangeyer.challonge.model.Participant;

import java.util.List;

public class ParticipantListWrapper {
    private List<Participant> participants;

    public ParticipantListWrapper(List<Participant> participants) {
        this.participants = participants;
    }

    public List<Participant> getParticipants() {
        return this.participants;
    }
}
