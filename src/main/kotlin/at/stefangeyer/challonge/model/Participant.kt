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
        val name: String?,
        @SerializedName("challonge_username")
        val challongeUsername: String?,
        val seed: Int?,
        val misc: String?,
        val active: Boolean?,
        @SerializedName("checked_in_at")
        val checkedInAt: OffsetDateTime?,
        @SerializedName("created_at")
        val createdAt: OffsetDateTime?,
        @SerializedName("final_rank")
        val finalRank: Int?,
        @SerializedName("group_id")
        val groupId: Long?,
        val icon: String?,
        val id: Long,
        @SerializedName("invitation_id")
        val invitationId: Long?,
        @SerializedName("invite_email")
        val inviteEmail: String?,
        @SerializedName("on_waiting_list")
        val onWaitingList: Boolean?,
        @SerializedName("tournament_id")
        val tournamentId: Long,
        @SerializedName("updated_at")
        val updatedAt: OffsetDateTime?,
        @SerializedName("challonge_email_address_verified")
        val challongeEmailAddressVerified: String?,
        val removable: Boolean?,
        @SerializedName("participatable_or_invitation_attached")
        val participatableOrInvitationAttached: Boolean?,
        @SerializedName("confirm_remove")
        val confirmRemove: Boolean?,
        @SerializedName("invitation_pending")
        val invitationPending: Boolean?,
        @SerializedName("display_name_with_invitation_email_address")
        val displayNameWithInvitationEmailAddress: String?,
        @SerializedName("email_hash")
        val emailHash: String?,
        val username: String?,
        @SerializedName("attached_participatable_portrait_url")
        val attachedParticipatablePortraitUrl: String?,
        @SerializedName("can_check_in")
        val canCheckIn: Boolean?,
        @SerializedName("checked_in")
        val checkedIn: Boolean?,
        val reactivatable: Boolean?
)