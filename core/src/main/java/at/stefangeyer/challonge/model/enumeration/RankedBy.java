package at.stefangeyer.challonge.model.enumeration;

/**
 * This enumeration represents the available tournament types.
 * <p>
 * More information about the ranking systems can be found [here](http://feedback.challonge.com/knowledgebase/articles/448540-rank-tie-break-statistics)
 *
 * @author Stefan Geyer
 * @version 20160820.1
 */
public enum RankedBy {
    MATCH_WINS("match wins"),
    GAME_WINS("game wins"),
    POINT_SCORED("point scored"),
    POINTS_DIFFERENCE("points difference"),
    CUSTOM("custom");

    private final String converted;

    RankedBy(String converted) {
        this.converted = converted;
    }

    public String toString() {
        return this.converted;
    }
}
