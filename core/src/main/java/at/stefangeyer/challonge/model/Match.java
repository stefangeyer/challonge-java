package at.stefangeyer.challonge.model;

import at.stefangeyer.challonge.model.enumeration.MatchState;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(exclude = {"createdAt", "scheduledTime", "startedAt", "underwayAt", "updatedAt"})
public class Match {
    private Long id;
    private Long tournamentId;
    private Integer attachmentCount;
    private OffsetDateTime createdAt;
    private Long groupId;
    private Boolean hasAttachment;
    private String identifier;
    private String location;
    private Long loserId;
    private Long winnerId;
    private Long player1Id;
    private Boolean player1IsPrerequisiteMatchLoser;
    private Long player1PrerequisiteMatchId;
    private Integer player1Votes;
    private Long player2Id;
    private Boolean player2IsPrerequisiteMatchLoser;
    private Long player2PrerequisiteMatchId;
    private Integer player2Votes;
    private Integer round;
    private OffsetDateTime scheduledTime;
    private OffsetDateTime startedAt;
    private MatchState state;
    private OffsetDateTime underwayAt;
    private OffsetDateTime updatedAt;
    private OffsetDateTime completedAt;
    private String prerequisiteMatchIdsCsv;
    private String scoresCsv;
    private Boolean optional;
    private Boolean forfeited;
    private List<Attachment> attachments;
}
