package at.stefangeyer.challonge.model.wrapper;

import at.stefangeyer.challonge.model.Match;

import java.util.List;

public class MatchListWrapper {
    private List<Match> matches;

    public MatchListWrapper(List<Match> matches) {
        this.matches = matches;
    }

    public List<Match> getMatches() {
        return this.matches;
    }
}
