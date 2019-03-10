package at.stefangeyer.challonge.model.wrapper;

import at.stefangeyer.challonge.model.Match;

/**
 * Wrapper for easy JSON serialisation of a Match
 *
 * @author Stefan Geyer
 * @version 2018-07-08
 */
public class MatchWrapper {
    private Match match;

    public MatchWrapper(Match match) {
        this.match = match;
    }

    public Match getMatch() {
        return this.match;
    }
}
