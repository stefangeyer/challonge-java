package at.stefangeyer.challonge.model.query.wrapper;

import at.stefangeyer.challonge.model.query.ParticipantQuery;

import java.util.List;

/**
 * Wrapper for easy JSON serialisation of a List<ParticipantQuery>
 *
 * @author Stefan Geyer
 * @version 2018-07-08
 */
public class ParticipantQueryListWrapper {
    private List<ParticipantQuery> participants;

    public ParticipantQueryListWrapper(List<ParticipantQuery> participants) {
        this.participants = participants;
    }

    public List<ParticipantQuery> getParticipants() {
        return this.participants;
    }
}
