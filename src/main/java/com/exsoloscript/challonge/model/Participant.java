package com.exsoloscript.challonge.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(exclude = {"checkedInAt", "createdAt", "updatedAt"})
public class Participant {
    private final String name;
    @SerializedName("challonge_username")
    private final String challongeUsername;
    private final Integer seed;
    private final String misc;
    private final Boolean active;
    @SerializedName("checked_in_at")
    private final OffsetDateTime checkedInAt;
    @SerializedName("created_at")
    private final OffsetDateTime createdAt;
    @SerializedName("final_rank")
    private final Integer finalRank;
    @SerializedName("group_id")
    private final Integer groupId;
    private final String icon;
    private final Integer id;
    @SerializedName("invitation_id")
    private final Integer invitationId;
    @SerializedName("invite_email")
    private final String inviteEmail;
    @SerializedName("on_waiting_list")
    private final Boolean onWaitingList;
    @SerializedName("tournament_id")
    private final Integer tournamentId;
    @SerializedName("updated_at")
    private final OffsetDateTime updatedAt;
    @SerializedName("challonge_email_address_verified")
    private final String challongeEmailAddressVerified;
    private final Boolean removable;
    @SerializedName("participatable_or_invitation_attached")
    private final Boolean participatableOrInvitationAttached;
    @SerializedName("confirm_remove")
    private final Boolean confirmRemove;
    @SerializedName("invitation_pending")
    private final Boolean invitationPending;
    @SerializedName("display_name_with_invitation_email_address")
    private final String displayNameWithInvitationEmailAddress;
    @SerializedName("email_hash")
    private final String emailHash;
    private final String username;
    @SerializedName("attached_participatable_portrait_url")
    private final String attachedParticipatablePortraitUrl;
    @SerializedName("can_check_in")
    private final Boolean canCheckIn;
    @SerializedName("checked_in")
    private final Boolean checkedIn;
    private final Boolean reactivatable;
}
