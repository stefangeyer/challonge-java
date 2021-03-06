package at.stefangeyer.challonge.model;

import at.stefangeyer.challonge.model.enumeration.RankedBy;
import at.stefangeyer.challonge.model.enumeration.TieBreak;
import at.stefangeyer.challonge.model.enumeration.TournamentState;
import at.stefangeyer.challonge.model.enumeration.TournamentType;
import at.stefangeyer.challonge.model.query.enumeration.GrandFinalsModifier;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(exclude = {
        "startAt", "completedAt", "createdAt",
        "predictionsOpenedAt", "startedAt", "startedCheckingInAt",
        "updatedAt", "lockedAt"})
public class Tournament {
    private Long id;
    private String url;
    private String name;
    private TournamentType tournamentType;
    private String subdomain;
    private String description;
    private Boolean openSignup;
    private Boolean holdThirdPlaceMatch;
    private Boolean ranked;
    private Boolean predictTheLosersBracket;
    private Float pointsForMatchWin;
    private Float pointsForMatchTie;
    private Float pointsForGameWin;
    private Float pointsForGameTie;
    private Float pointsForBye;
    private Integer swissRounds;
    private RankedBy rankedBy;
    private Integer roundRobinIterations;
    private Float roundRobinPointsForGameWin;
    private Float roundRobinPointsForGameTie;
    private Float roundRobinPointsForMatchWin;
    private Float roundRobinPointsForMatchTie;
    private Boolean acceptAttachments;
    private Boolean hideForum;
    private Boolean showRounds;
    private Boolean privateOnly;
    private Boolean notifyUsersWhenTheTournamentEnds;
    private Boolean sequentialPairings;
    private Float registrationFee;
    private String registrationType;
    private Integer signupCap;
    private OffsetDateTime startAt;
    private Long checkInDuration;
    private Boolean allowParticipantMatchReporting;
    private Boolean anonymousVoting;
    private String category;
    private OffsetDateTime completedAt;
    private OffsetDateTime createdAt;
    private Boolean createdByApi;
    private Boolean creditCapped;
    private Long gameId;
    private Boolean groupStagesEnabled;
    private Boolean hideSeeds;
    private Integer maxPredictionsPerUser;
    private Boolean notifyUsersWhenMatchesOpen;
    private Integer participantsCount;
    private Integer predictionMethod;
    private OffsetDateTime predictionsOpenedAt;
    private Integer progressMeter;
    private Boolean quickAdvance;
    private Boolean requireScoreAgreement;
    private OffsetDateTime startedAt;
    private OffsetDateTime startedCheckingInAt;
    private TournamentState state;
    private Boolean teams;
    private List<TieBreak> tieBreaks;
    private OffsetDateTime updatedAt;
    private String descriptionSource;
    private String fullChallongeUrl;
    private String liveImageUrl;
    private String signUpUrl;
    private Boolean reviewBeforeFinalizing;
    private Boolean acceptingPredictions;
    private Boolean participantsLocked;
    private String gameName;
    private Boolean participantsSwappable;
    private Boolean teamConvertable;
    private Boolean groupStagesWereStarted;
    private OffsetDateTime lockedAt;
    private Long eventId;
    private Boolean publicPredictionsBeforeStartTime;
    private GrandFinalsModifier grandFinalsModifier;
    private List<Participant> participants;
    private List<Match> matches;
}
