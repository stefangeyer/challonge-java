package at.stefangeyer.challonge.model.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchQuery {
    private Long winnerId;
    private Integer votesForPlayer1;
    private Integer votesForPlayer2;
    private String scoresCsv;
}
