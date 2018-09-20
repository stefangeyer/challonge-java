/*
 * Copyright 2017 Stefan Geyer <stefangeyer at outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package at.stefangeyer.challonge.model

import java.time.OffsetDateTime

class Participant(
        val id: Long = 0L,
        val tournamentId: Long = 0L,
        val name: String? = null,
        val challongeUsername: String? = null,
        val seed: Int = 0,
        val misc: String? = null,
        val active: Boolean = false,
        val checkedInAt: OffsetDateTime? = null,
        val createdAt: OffsetDateTime? = null,
        val finalRank: Int? = 0,
        val groupId: Long? = 0L,
        val icon: String? = null,
        val invitationId: Long? = 0L,
        val inviteEmail: String? = null,
        val onWaitingList: Boolean = false,
        val updatedAt: OffsetDateTime? = null,
        val challongeEmailAddressVerified: String? = null,
        val removable: Boolean = false,
        val participatableOrInvitationAttached: Boolean = false,
        val confirmRemove: Boolean = false,
        val invitationPending: Boolean = false,
        val displayNameWithInvitationEmailAddress: String? = null,
        val emailHash: String? = null,
        val username: String? = null,
        val attachedParticipatablePortraitUrl: String? = null,
        val canCheckIn: Boolean = false,
        val checkedIn: Boolean = false,
        val reactivatable: Boolean = false,
        val matches: List<Match>? = listOf()
) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Participant

        if (id != other.id) return false
        if (tournamentId != other.tournamentId) return false
        if (name != other.name) return false
        if (challongeUsername != other.challongeUsername) return false
        if (seed != other.seed) return false
        if (misc != other.misc) return false
        if (active != other.active) return false
        if (finalRank != other.finalRank) return false
        if (groupId != other.groupId) return false
        if (icon != other.icon) return false
        if (invitationId != other.invitationId) return false
        if (inviteEmail != other.inviteEmail) return false
        if (onWaitingList != other.onWaitingList) return false
        if (challongeEmailAddressVerified != other.challongeEmailAddressVerified) return false
        if (removable != other.removable) return false
        if (participatableOrInvitationAttached != other.participatableOrInvitationAttached) return false
        if (confirmRemove != other.confirmRemove) return false
        if (invitationPending != other.invitationPending) return false
        if (displayNameWithInvitationEmailAddress != other.displayNameWithInvitationEmailAddress) return false
        if (emailHash != other.emailHash) return false
        if (username != other.username) return false
        if (attachedParticipatablePortraitUrl != other.attachedParticipatablePortraitUrl) return false
        if (canCheckIn != other.canCheckIn) return false
        if (checkedIn != other.checkedIn) return false
        if (reactivatable != other.reactivatable) return false
        if (matches != other.matches) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + tournamentId.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (challongeUsername?.hashCode() ?: 0)
        result = 31 * result + seed
        result = 31 * result + (misc?.hashCode() ?: 0)
        result = 31 * result + active.hashCode()
        result = 31 * result + (finalRank ?: 0)
        result = 31 * result + (groupId?.hashCode() ?: 0)
        result = 31 * result + (icon?.hashCode() ?: 0)
        result = 31 * result + (invitationId?.hashCode() ?: 0)
        result = 31 * result + (inviteEmail?.hashCode() ?: 0)
        result = 31 * result + onWaitingList.hashCode()
        result = 31 * result + (challongeEmailAddressVerified?.hashCode() ?: 0)
        result = 31 * result + removable.hashCode()
        result = 31 * result + participatableOrInvitationAttached.hashCode()
        result = 31 * result + confirmRemove.hashCode()
        result = 31 * result + invitationPending.hashCode()
        result = 31 * result + (displayNameWithInvitationEmailAddress?.hashCode() ?: 0)
        result = 31 * result + (emailHash?.hashCode() ?: 0)
        result = 31 * result + (username?.hashCode() ?: 0)
        result = 31 * result + (attachedParticipatablePortraitUrl?.hashCode() ?: 0)
        result = 31 * result + canCheckIn.hashCode()
        result = 31 * result + checkedIn.hashCode()
        result = 31 * result + reactivatable.hashCode()
        result = 31 * result + (matches?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Participant(id=$id, tournamentId=$tournamentId, name=$name, challongeUsername=$challongeUsername, seed=$seed, misc=$misc, active=$active, checkedInAt=$checkedInAt, createdAt=$createdAt, finalRank=$finalRank, groupId=$groupId, icon=$icon, invitationId=$invitationId, inviteEmail=$inviteEmail, onWaitingList=$onWaitingList, updatedAt=$updatedAt, challongeEmailAddressVerified=$challongeEmailAddressVerified, removable=$removable, participatableOrInvitationAttached=$participatableOrInvitationAttached, confirmRemove=$confirmRemove, invitationPending=$invitationPending, displayNameWithInvitationEmailAddress=$displayNameWithInvitationEmailAddress, emailHash=$emailHash, username=$username, attachedParticipatablePortraitUrl=$attachedParticipatablePortraitUrl, canCheckIn=$canCheckIn, checkedIn=$checkedIn, reactivatable=$reactivatable, matches=$matches)"
    }
}