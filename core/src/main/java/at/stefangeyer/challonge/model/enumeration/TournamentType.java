package at.stefangeyer.challonge.model.enumeration;

/**
 * This enumeration represents the available tournament types
 *
 * @author Stefan Geyer
 * @version 20160820.1
 */
public enum TournamentType {
    SINGLE_ELIMINATION("single elimination"),
    DOUBLE_ELIMINATION("double elimination"),
    ROUND_ROBIN("round robin"),
    SWISS("swiss"),
    FREE_FOR_ALL("free for all");

    private final String converted;

    TournamentType(String converted) {
        this.converted = converted;
    }

    @Override
    public String toString() {
        return this.converted;
    }
}
