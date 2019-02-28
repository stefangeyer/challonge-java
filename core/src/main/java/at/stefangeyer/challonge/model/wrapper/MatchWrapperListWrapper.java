package at.stefangeyer.challonge.model.wrapper;

import java.util.List;

public class MatchWrapperListWrapper {
    private List<MatchWrapper> matches;

    public MatchWrapperListWrapper(List<MatchWrapper> matches) {
        this.matches = matches;
    }

    public List<MatchWrapper> getMatches() {
        return this.matches;
    }
}
