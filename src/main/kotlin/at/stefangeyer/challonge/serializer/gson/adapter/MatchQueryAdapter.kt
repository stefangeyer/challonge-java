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

import at.stefangeyer.challonge.model.query.MatchQuery
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

class MatchQueryAdapter : JsonSerializer<MatchQuery> {

    override fun serialize(query: MatchQuery, type: Type, context: JsonSerializationContext): JsonElement {
        val mqEntity = JsonObject()

        if (query.winnerId != null) mqEntity.addProperty("winner_id", query.winnerId)
        if (query.votesForPlayer1 != null) mqEntity.addProperty("player1_votes", query.votesForPlayer1)
        if (query.votesForPlayer2 != null) mqEntity.addProperty("player2_votes", query.votesForPlayer2)
        if (query.scoresCsv != null) mqEntity.addProperty("scores_csv", query.scoresCsv)

        return mqEntity
    }
}
