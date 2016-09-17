package com.exsoloscript.challonge.model;

import com.google.gson.annotations.SerializedName;

import java.time.OffsetDateTime;

/**
 * The POJO that will be mapped to the participant requests by Gson
 *
 * @author EXSolo
 * @version 20160820.1
 */
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

    public OffsetDateTime checkedInAt() {
        return checkedInAt;
    }

    public OffsetDateTime createdAt() {
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

    public OffsetDateTime updatedAt() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Participant that = (Participant) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (challongeUsername != null ? !challongeUsername.equals(that.challongeUsername) : that.challongeUsername != null)
            return false;
        if (seed != null ? !seed.equals(that.seed) : that.seed != null) return false;
        if (misc != null ? !misc.equals(that.misc) : that.misc != null) return false;
        if (active != null ? !active.equals(that.active) : that.active != null) return false;
        if (checkedInAt != null ? !checkedInAt.equals(that.checkedInAt) : that.checkedInAt != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (finalRank != null ? !finalRank.equals(that.finalRank) : that.finalRank != null) return false;
        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;
        if (icon != null ? !icon.equals(that.icon) : that.icon != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (invitationId != null ? !invitationId.equals(that.invitationId) : that.invitationId != null) return false;
        if (inviteEmail != null ? !inviteEmail.equals(that.inviteEmail) : that.inviteEmail != null) return false;
        if (onWaitingList != null ? !onWaitingList.equals(that.onWaitingList) : that.onWaitingList != null)
            return false;
        if (tournamentId != null ? !tournamentId.equals(that.tournamentId) : that.tournamentId != null) return false;
        if (updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) return false;
        if (challongeEmailAddressVerified != null ? !challongeEmailAddressVerified.equals(that.challongeEmailAddressVerified) : that.challongeEmailAddressVerified != null)
            return false;
        if (removable != null ? !removable.equals(that.removable) : that.removable != null) return false;
        if (participatableOrInvitationAttached != null ? !participatableOrInvitationAttached.equals(that.participatableOrInvitationAttached) : that.participatableOrInvitationAttached != null)
            return false;
        if (confirmRemove != null ? !confirmRemove.equals(that.confirmRemove) : that.confirmRemove != null)
            return false;
        if (invitationPending != null ? !invitationPending.equals(that.invitationPending) : that.invitationPending != null)
            return false;
        if (displayNameWithInvitationEmailAddress != null ? !displayNameWithInvitationEmailAddress.equals(that.displayNameWithInvitationEmailAddress) : that.displayNameWithInvitationEmailAddress != null)
            return false;
        if (emailHash != null ? !emailHash.equals(that.emailHash) : that.emailHash != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (attachedParticipatablePortraitUrl != null ? !attachedParticipatablePortraitUrl.equals(that.attachedParticipatablePortraitUrl) : that.attachedParticipatablePortraitUrl != null)
            return false;
        if (canCheckIn != null ? !canCheckIn.equals(that.canCheckIn) : that.canCheckIn != null) return false;
        if (checkedIn != null ? !checkedIn.equals(that.checkedIn) : that.checkedIn != null) return false;
        return reactivatable != null ? reactivatable.equals(that.reactivatable) : that.reactivatable == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (challongeUsername != null ? challongeUsername.hashCode() : 0);
        result = 31 * result + (seed != null ? seed.hashCode() : 0);
        result = 31 * result + (misc != null ? misc.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (checkedInAt != null ? checkedInAt.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (finalRank != null ? finalRank.hashCode() : 0);
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (invitationId != null ? invitationId.hashCode() : 0);
        result = 31 * result + (inviteEmail != null ? inviteEmail.hashCode() : 0);
        result = 31 * result + (onWaitingList != null ? onWaitingList.hashCode() : 0);
        result = 31 * result + (tournamentId != null ? tournamentId.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (challongeEmailAddressVerified != null ? challongeEmailAddressVerified.hashCode() : 0);
        result = 31 * result + (removable != null ? removable.hashCode() : 0);
        result = 31 * result + (participatableOrInvitationAttached != null ? participatableOrInvitationAttached.hashCode() : 0);
        result = 31 * result + (confirmRemove != null ? confirmRemove.hashCode() : 0);
        result = 31 * result + (invitationPending != null ? invitationPending.hashCode() : 0);
        result = 31 * result + (displayNameWithInvitationEmailAddress != null ? displayNameWithInvitationEmailAddress.hashCode() : 0);
        result = 31 * result + (emailHash != null ? emailHash.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (attachedParticipatablePortraitUrl != null ? attachedParticipatablePortraitUrl.hashCode() : 0);
        result = 31 * result + (canCheckIn != null ? canCheckIn.hashCode() : 0);
        result = 31 * result + (checkedIn != null ? checkedIn.hashCode() : 0);
        result = 31 * result + (reactivatable != null ? reactivatable.hashCode() : 0);
        return result;
    }
}
