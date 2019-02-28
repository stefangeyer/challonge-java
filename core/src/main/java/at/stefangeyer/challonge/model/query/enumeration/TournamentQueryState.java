package at.stefangeyer.challonge.model.query.enumeration;

/**
 * Represents the different states that can be queried.
 * This enumeration does not represent the actual state of the tournament,
 * but groups a list ouf tournaments to subgroups.
 *
 * @author Stefan Geyer
 * @version 20160820.1
 */
public enum TournamentQueryState {
    ALL("all"),
    PENDING("pending"),
    IN_PROGRESS("in_progress"),
    ENDED("ended");

    private String converted;

    TournamentQueryState(String converted) {
        this.converted = converted;
    }

    public String toString() {
        return this.converted;
    }
}
