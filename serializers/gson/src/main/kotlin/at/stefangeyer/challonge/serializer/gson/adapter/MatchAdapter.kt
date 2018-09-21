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

import at.stefangeyer.challonge.model.Attachment
import at.stefangeyer.challonge.model.Match
import at.stefangeyer.challonge.model.enum.MatchState
import at.stefangeyer.challonge.serializer.gson.util.getOrNull
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.time.OffsetDateTime

/**
 * Type adapter for the [Match] class.
 * The received json object is being unpacked.
 *
 * @author Stefan Geyer
 * @version 2018-07-08
 */
class MatchAdapter : JsonDeserializer<Match> {

    @Throws(JsonParseException::class)
    override fun deserialize(jsonElement: JsonElement, type: Type, context: JsonDeserializationContext): Match {
        var e = jsonElement.asJsonObject

        if (e.has("match")) {
            e = e.get("match").asJsonObject
        }

        val id = e.get("id").asLong
        val tournamentId = e.get("tournament_id").asLong
        val attachmentCount = e.getOrNull("attachment_count")?.asInt
        val createdAt = context.deserialize<OffsetDateTime>(e.get("created_at"), OffsetDateTime::class.java)
        val groupId = e.getOrNull("group_id")?.asLong
        val hasAttachment = e.get("has_attachment").asBoolean
        val identifier = e.getOrNull("identifier")?.asString
        val location = e.getOrNull("location")?.asString
        val updatedAt = context.deserialize<OffsetDateTime>(e.get("updated_at"), OffsetDateTime::class.java)
        val state = MatchState.valueOf(e.get("state").asString.replace(" ", "_").toUpperCase())
        val startedAt = context.deserialize<OffsetDateTime>(e.get("started_at"), OffsetDateTime::class.java)
        val scoresCsv = e.getOrNull("scores_csv")?.asString
        val winnerId = e.getOrNull("winner_id")?.asLong
        val loserId = e.getOrNull("loser_id")?.asLong
        val player1Id = e.getOrNull("player1_id")?.asLong
        val player2Id = e.getOrNull("player2_id")?.asLong
        val player1IsPrerequisiteMatchLoser = e.get("player1_is_prereq_match_loser").asBoolean
        val player1PrerequisiteMatchId = e.getOrNull("player1_prereq_match_id")?.asLong
        val player2IsPrerequisiteMatchLoser = e.get("player2_is_prereq_match_loser").asBoolean
        val player2PrerequisiteMatchId = e.getOrNull("player2_prereq_match_id")?.asLong
        val prerequisiteMatchIdsCsv = e.getOrNull("prerequisite_match_ids_csv")?.asString
        val round = e.get("round").asInt
        val scheduledTime = context.deserialize<OffsetDateTime>(e.get("scheduled_time"), OffsetDateTime::class.java)
        val underwayAt = context.deserialize<OffsetDateTime>(e.get("underway_at"), OffsetDateTime::class.java)
        val attachments = context.deserialize<List<Attachment>>(e.get("attachments"), object : TypeToken<List<Attachment>>() {}.type)

        return Match(id = id, tournamentId = tournamentId, attachmentCount = attachmentCount, createdAt = createdAt, groupId = groupId,
                hasAttachment = hasAttachment, identifier = identifier, location = location, updatedAt = updatedAt, state = state,
                startedAt = startedAt, scoresCsv = scoresCsv, winnerId = winnerId, loserId = loserId, player1Id = player1Id, player2Id = player2Id,
                player1IsPrerequisiteMatchLoser = player1IsPrerequisiteMatchLoser, player1PrerequisiteMatchId = player1PrerequisiteMatchId,
                player2IsPrerequisiteMatchLoser = player2IsPrerequisiteMatchLoser, player2PrerequisiteMatchId = player2PrerequisiteMatchId,
                prerequisiteMatchIdsCsv = prerequisiteMatchIdsCsv, round = round, scheduledTime = scheduledTime, underwayAt = underwayAt,
                attachments = attachments)
    }
}
