package com.exsoloscript.challonge.model;

import com.google.gson.annotations.SerializedName;

import java.time.ZonedDateTime;

public class Participant {
    private String name;
    @SerializedName("challonge_username")
    private String challongeUsername;
    private Integer seed;
    private String misc;
    private Boolean active;
    @SerializedName("checked_in_at")
    private ZonedDateTime checkedInAt;
    @SerializedName("created_at")
    private ZonedDateTime createdAt;
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
    private ZonedDateTime updatedAt;
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

    public String name() {
        return name;
    }

    public String challongeUsername() {
        return challongeUsername;
    }

    public Integer seed() {
        return seed;
    }

    public String misc() {
        return misc;
    }

    public Boolean active() {
        return active;
    }

    public ZonedDateTime checkedInAt() {
        return checkedInAt;
    }

    public ZonedDateTime createdAt() {
        return createdAt;
    }

    public Integer finalRank() {
        return finalRank;
    }

    public Integer groupId() {
        return groupId;
    }

    public String icon() {
        return icon;
    }

    public Integer id() {
        return id;
    }

    public Integer invitationId() {
        return invitationId;
    }

    public String inviteEmail() {
        return inviteEmail;
    }

    public Boolean onWaitingList() {
        return onWaitingList;
    }

    public Integer tournamentId() {
        return tournamentId;
    }

    public ZonedDateTime updatedAt() {
        return updatedAt;
    }

    public String challongeEmailAddressVerified() {
        return challongeEmailAddressVerified;
    }

    public Boolean removable() {
        return removable;
    }

    public Boolean participatableOrInvitationAttached() {
        return participatableOrInvitationAttached;
    }

    public Boolean confirmRemove() {
        return confirmRemove;
    }

    public Boolean invitationPending() {
        return invitationPending;
    }

    public String displayNameWithInvitationEmailAddress() {
        return displayNameWithInvitationEmailAddress;
    }

    public String emailHash() {
        return emailHash;
    }

    public String username() {
        return username;
    }

    public String attachedParticipatablePortraitUrl() {
        return attachedParticipatablePortraitUrl;
    }

    public Boolean canCheckIn() {
        return canCheckIn;
    }

    public Boolean checkedIn() {
        return checkedIn;
    }

    public Boolean reactivatable() {
        return reactivatable;
    }
}
