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

package at.stefangeyer.challonge.serializer.gson.adapter

import at.stefangeyer.challonge.model.Match
import at.stefangeyer.challonge.model.Participant
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.time.OffsetDateTime

/**
 * Type adapter for the [Participant] class.
 * The received json object is being unpacked.
 *
 * @author Stefan Geyer
 * @version 2018-07-06
 */
class ParticipantAdapter internal constructor() : JsonDeserializer<Participant> {

    @Throws(JsonParseException::class)
    override fun deserialize(jsonElement: JsonElement, type: Type, context: JsonDeserializationContext): Participant {
        val e = jsonElement.asJsonObject.get("participant").asJsonObject
        return Participant(id = e.get("id").asLong, updatedAt = context.deserialize(e.get("updated_at"), OffsetDateTime::class.java),
                groupId = e.get("group_id").asLong, createdAt = context.deserialize(e.get("created_at"), OffsetDateTime::class.java),
                tournamentId = e.get("tournament_id").asLong, seed = e.get("seed").asInt, name = e.get("name").asString,
                displayNameWithInvitationEmailAddress = e.get("display_name_with_invitation_email_address").asString,
                misc = e.get("misc").asString, challongeUsername = e.get("challonge_username").asString,
                inviteEmail = e.get("invite_email").asString, active = e.get("active").asBoolean,
                attachedParticipatablePortraitUrl = e.get("attached_participatable_portrait_url").asString,
                canCheckIn = e.get("can_check_in").asBoolean,
                challongeEmailAddressVerified = e.get("challonge_email_address_verified").asString,
                checkedIn = e.get("checked_in").asBoolean,
                checkedInAt = context.deserialize(e.get("checked_in_at"), OffsetDateTime::class.java),
                confirmRemove = e.get("confirm_remove").asBoolean, emailHash = e.get("email_hash").asString,
                finalRank = e.get("final_rank").asInt,
                icon = e.get("icon").asString, invitationId = e.get("invitation_id").asLong,
                invitationPending = e.get("invitation_pending").asBoolean,
                onWaitingList = e.get("on_waiting_list").asBoolean,
                participatableOrInvitationAttached = e.get("participatable_or_invitation_attached").asBoolean,
                reactivatable = e.get("reactivatable").asBoolean, removable = e.get("removable").asBoolean, username = e.get("username").asString,
                matches = context.deserialize(e.get("matches"), object : TypeToken<List<Match>>() {}.type))
    }

}
