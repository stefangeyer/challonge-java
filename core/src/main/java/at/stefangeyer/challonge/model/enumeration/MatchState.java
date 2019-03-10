package at.stefangeyer.challonge.model.enumeration;

/**
 * This enumeration represents the different states of a match.
 *
 * @author Stefan Geyer
 * @version 20160820.1
 */
public enum MatchState {
    ALL("all"),
    PENDING("pending"),
    OPEN("open"),
    COMPLETE("complete");

    private final String converted;

    MatchState(String converted) {
        this.converted = converted;
    }

    public String toString() {
        return this.converted;
    }
}
