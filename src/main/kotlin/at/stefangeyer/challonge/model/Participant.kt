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

import com.google.gson.annotations.SerializedName
import java.time.OffsetDateTime

class Participant(
        val id: Long = 0L,
        @SerializedName("tournament_id")
        val tournamentId: Long = 0L,
        val name: String? = null,
        @SerializedName("challonge_username")
        val challongeUsername: String? = null,
        val seed: Int = 0,
        val misc: String? = null,
        val active: Boolean = false,
        @SerializedName("checked_in_at")
        val checkedInAt: OffsetDateTime? = null,
        @SerializedName("created_at")
        val createdAt: OffsetDateTime? = null,
        @SerializedName("final_rank")
        val finalRank: Int = 0,
        @SerializedName("group_id")
        val groupId: Long = 0L,
        val icon: String? = null,
        @SerializedName("invitation_id")
        val invitationId: Long = 0L,
        @SerializedName("invite_email")
        val inviteEmail: String? = null,
        @SerializedName("on_waiting_list")
        val onWaitingList: Boolean = false,
        @SerializedName("updated_at")
        val updatedAt: OffsetDateTime? = null,
        @SerializedName("challonge_email_address_verified")
        val challongeEmailAddressVerified: String? = null,
        val removable: Boolean = false,
        @SerializedName("participatable_or_invitation_attached")
        val participatableOrInvitationAttached: Boolean = false,
        @SerializedName("confirm_remove")
        val confirmRemove: Boolean = false,
        @SerializedName("invitation_pending")
        val invitationPending: Boolean = false,
        @SerializedName("display_name_with_invitation_email_address")
        val displayNameWithInvitationEmailAddress: String? = null,
        @SerializedName("email_hash")
        val emailHash: String? = null,
        val username: String? = null,
        @SerializedName("attached_participatable_portrait_url")
        val attachedParticipatablePortraitUrl: String? = null,
        @SerializedName("can_check_in")
        val canCheckIn: Boolean = false,
        @SerializedName("checked_in")
        val checkedIn: Boolean = false,
        val reactivatable: Boolean = false
)