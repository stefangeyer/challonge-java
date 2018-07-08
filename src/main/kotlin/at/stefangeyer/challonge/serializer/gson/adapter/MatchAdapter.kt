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
class MatchAdapter internal constructor() : JsonDeserializer<Match> {

    @Throws(JsonParseException::class)
    override fun deserialize(jsonElement: JsonElement, type: Type, context: JsonDeserializationContext): Match {
//        val e = jsonElement.asJsonObject.get("match").asJsonObject
        val e = jsonElement.asJsonObject

        return Match(id = e.get("id").asLong, tournamentId = e.get("tournament_id").asLong, attachmentCount = e.get("attachment_count").asInt,
                createdAt = context.deserialize(e.get("created_at"), OffsetDateTime::class.java), groupId = e.get("group_id").asLong,
                hasAttachment = e.get("has_attachment").asBoolean, identifier = e.get("identifier").asString, location = e.get("location").asString,
                updatedAt = context.deserialize(e.get("updated_at"), OffsetDateTime::class.java),
                state = MatchState.valueOf(e.get("state").asString.replace(" ", "_").toUpperCase()),
                startedAt = context.deserialize(e.get("started_at"), OffsetDateTime::class.java), scoresCsv = e.get("scores_csv").asString,
                winnerId = e.get("winner_id").asLong, loserId = e.get("loser_id").asLong, player1Id = e.get("player1_id").asLong,
                player2Id = e.get("player2_id").asLong,
                player1IsPrerequisiteMatchLoser = e.get("player1_is_prereq_match_loser").asBoolean,
                player1PrerequisiteMatchId = e.get("player1_prereq_match_id").asLong,
                player2IsPrerequisiteMatchLoser = e.get("player2_is_prereq_match_loser").asBoolean,
                player2PrerequisiteMatchId = e.get("player2_prereq_match_id").asLong,
                prerequisiteMatchIdsCsv = e.get("prerequisite_match_ids_csv").asString, round = e.get("round").asInt,
                scheduledTime = context.deserialize(e.get("scheduled_time"), OffsetDateTime::class.java),
                underwayAt = context.deserialize(e.get("underway_at"), OffsetDateTime::class.java),
                attachments = context.deserialize(e.get("attachments"), object : TypeToken<List<Attachment>>() {}.type)
        )
    }
}
