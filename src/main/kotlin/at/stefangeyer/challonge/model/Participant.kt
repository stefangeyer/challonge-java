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

data class Participant(
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
)