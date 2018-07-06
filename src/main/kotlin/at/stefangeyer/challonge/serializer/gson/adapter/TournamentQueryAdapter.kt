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

import at.stefangeyer.challonge.model.query.TournamentQuery
import com.google.gson.*

import java.lang.reflect.Type
import java.time.OffsetDateTime

class TournamentQueryAdapter internal constructor() : JsonSerializer<TournamentQuery> {

    private val gson: Gson

    init {
        this.gson = GsonBuilder()
                .registerTypeAdapter(OffsetDateTime::class.java, OffsetDateTimeAdapter())
                .create()
    }

    override fun serialize(tournamentQuery: TournamentQuery, type: Type, jsonSerializationContext: JsonSerializationContext): JsonElement {
        val parent = JsonObject()

        parent.add("tournament", gson.toJsonTree(tournamentQuery))

        return parent
    }
}
