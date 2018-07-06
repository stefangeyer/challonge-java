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
import com.google.gson.*

import java.lang.reflect.Type
import java.time.OffsetDateTime

/**
 * Type adapter for the [Match] class.
 * The received json object is being unpacked.
 *
 * @author EXSolo
 * @version 20160825.1
 */
class MatchAdapter internal constructor() : JsonDeserializer<Match> {

    @Throws(JsonParseException::class)
    override fun deserialize(jsonElement: JsonElement, type: Type, context: JsonDeserializationContext): Match {
        val matchElement = jsonElement.asJsonObject.get("match")
        return context.deserialize(matchElement, Match::class.java)
    }
}
