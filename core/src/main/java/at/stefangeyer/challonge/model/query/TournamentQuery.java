package at.stefangeyer.challonge.model.query;

import at.stefangeyer.challonge.model.enumeration.RankedBy;
import at.stefangeyer.challonge.model.enumeration.TieBreak;
import at.stefangeyer.challonge.model.enumeration.TournamentType;
import at.stefangeyer.challonge.model.query.enumeration.GrandFinalsModifier;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(exclude = {"startAt"})
public class TournamentQuery {
    private String name;
    private TournamentType tournamentType;
    private String url;
    private String subdomain;
    private String description;
    private Boolean openSignup;
    private Boolean holdThirdPlaceMatch;
    private Float pointsForMatchWin;
    private Float pointsForMatchTie;
    private Float pointsForGameWin;
    private Float pointsForGameTie;
    private Float pointsForBye;
    private Integer swissRounds;
    private RankedBy rankedBy;
    private Float roundRobinPointsForGameWin;
    private Float roundRobinPointsForGameTie;
    private Float roundRobinPointsForMatchWin;
    private Float roundRobinPointsForMatchTie;
    private Boolean acceptAttachments;
    private Boolean hideForum;
    private Boolean showRounds;
    private Boolean privateOnly;
    private Boolean notifyUsersWhenMatchesOpen;
    private Boolean notifyUsersWhenTheTournamentEnds;
    private Boolean sequentialPairings;
    private Integer signupCap;
    private OffsetDateTime startAt;
    private Long checkInDuration;
    private GrandFinalsModifier grandFinalsModifier;
    private List<TieBreak> tieBreaks;
    private String gameName;
}
