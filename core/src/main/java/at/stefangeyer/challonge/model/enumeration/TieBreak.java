package at.stefangeyer.challonge.model.enumeration;

/**
 * This enumeration represents the available tie break types.
 * <p>
 * More information about the ranking and tie break systems can be found [here](http://feedback.challonge.com/knowledgebase/articles/448540-rank-tie-break-statistics)
 *
 * @author Stefan Geyer
 * @version 20191004.1
 */
public enum TieBreak {
    MATCH_WINS("mach wins"),
    GAME_WINS("game wins"),
    GAME_WIN_PERCENTAGE("game win percentage"),
    POINTS_SCORED("points scored"),
    POINTS_DIFFERENCE("points difference"),
    MATCH_WINS_VS_TIED("match wins vs tied"),
    MEDIAN_BUCHHOLZ("median buchholz"),
    CUSTOM("custom");

    private final String converted;

    TieBreak(String converted) {
        this.converted = converted;
    }

    public String toString() {
        return this.converted;
    }
}
