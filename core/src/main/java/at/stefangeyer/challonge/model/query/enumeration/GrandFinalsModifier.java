package at.stefangeyer.challonge.model.query.enumeration;

/**
 * Type of the grand finals in a double elimination tournament.
 * <p>
 * This option only affects double elimination.
 * <p>
 * null/blank (default) - give the winners bracket finalist two chances to beat the losers bracket finalist<br>
 * 'single match' - create only one grand finals match<br>
 * 'skip' - don't create a finals match between winners and losers bracket finalists
 *
 * @author Stefan Geyer
 * @version 20160820.1
 */
public enum GrandFinalsModifier {
    BLANK(""),
    SINGLE_MATCH("single match"),
    SKIP("skip");

    private String converted;

    GrandFinalsModifier(String converted) {
        this.converted = converted;
    }

    public String toString() {
        return this.converted;
    }
}
