package at.stefangeyer.challonge.model;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
public class Participant {
    private Long id;
    private Long tournamentId;
    private String name;
    private String challongeUsername;
    private Integer seed;
    private String misc;
    private Boolean active;
    private OffsetDateTime checkedInAt;
    private OffsetDateTime createdAt;
    private Integer finalRank;
    private Long groupId;
    private String icon;
    private Long invitationId;
    private String inviteEmail;
    private Boolean onWaitingList;
    private OffsetDateTime updatedAt;
    private String challongeEmailAddressVerified;
    private Boolean removable;
    private Boolean participatableOrInvitationAttached;
    private Boolean confirmRemove;
    private Boolean invitationPending;
    private String displayNameWithInvitationEmailAddress;
    private String emailHash;
    private String username;
    private String attachedParticipatablePortraitUrl;
    private Boolean canCheckIn;
    private Boolean checkedIn;
    private Boolean reactivatable;
    private List<Match> matches;
}
