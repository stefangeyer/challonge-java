package at.stefangeyer.challonge.model.wrapper;

import java.util.List;

/**
 * Wrapper for easy JSON serialisation of a ParticipantWrapper
 *
 * @author Stefan Geyer
 * @version 2018-07-08
 */
public class ParticipantWrapperListWrapper {
    private List<ParticipantWrapper> participants;

    public ParticipantWrapperListWrapper(List<ParticipantWrapper> participants) {
        this.participants = participants;
    }

    public List<ParticipantWrapper> getParticipants() {
        return this.participants;
    }
}
