package com.exsoloscript.challonge.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

/**
 * The POJO that will be mapped to the participant requests by Gson
 *
 * @author EXSolo
 * @version 20160820.1
 */
@Data
@Accessors(fluent = true)
public class Participant {
    private String name;
    @SerializedName("challonge_username")
    private String challongeUsername;
    private Integer seed;
    private String misc;
    private Boolean active;
    @SerializedName("checked_in_at")
    private OffsetDateTime checkedInAt;
    @SerializedName("created_at")
    private OffsetDateTime createdAt;
    @SerializedName("final_rank")
    private Integer finalRank;
    @SerializedName("group_id")
    private Integer groupId;
    private String icon;
    private Integer id;
    @SerializedName("invitation_id")
    private Integer invitationId;
    @SerializedName("invite_email")
    private String inviteEmail;
    @SerializedName("on_waiting_list")
    private Boolean onWaitingList;
    @SerializedName("tournament_id")
    private Integer tournamentId;
    @SerializedName("updated_at")
    private OffsetDateTime updatedAt;
    @SerializedName("challonge_email_address_verified")
    private String challongeEmailAddressVerified;
    private Boolean removable;
    @SerializedName("participatable_or_invitation_attached")
    private Boolean participatableOrInvitationAttached;
    @SerializedName("confirm_remove")
    private Boolean confirmRemove;
    @SerializedName("invitation_pending")
    private Boolean invitationPending;
    @SerializedName("display_name_with_invitation_email_address")
    private String displayNameWithInvitationEmailAddress;
    @SerializedName("email_hash")
    private String emailHash;
    private String username;
    @SerializedName("attached_participatable_portrait_url")
    private String attachedParticipatablePortraitUrl;
    @SerializedName("can_check_in")
    private Boolean canCheckIn;
    @SerializedName("checked_in")
    private Boolean checkedIn;
    private Boolean reactivatable;
}
