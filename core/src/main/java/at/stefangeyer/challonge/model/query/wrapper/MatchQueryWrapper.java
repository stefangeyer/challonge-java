package at.stefangeyer.challonge.model.query.wrapper;

import at.stefangeyer.challonge.model.query.MatchQuery;

public class MatchQueryWrapper {
    private MatchQuery match;

    public MatchQueryWrapper(MatchQuery match) {
        this.match = match;
    }

    public MatchQuery getMatch() {
        return this.match;
    }
}
