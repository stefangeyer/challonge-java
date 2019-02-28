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
    SWISS("swiss");

    private final String converted;

    TournamentType(String converted) {
        this.converted = converted;
    }

    public String toString() {
        return this.converted;
    }
}
