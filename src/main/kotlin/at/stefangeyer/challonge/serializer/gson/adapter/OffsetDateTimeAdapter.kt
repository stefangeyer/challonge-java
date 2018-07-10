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

import com.google.gson.*

import java.lang.reflect.Type
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

/**
 * Type adapter for the [OffsetDateTime] class.
 *
 * @author Stefan Geyer
 * @version 2018-07-10
 */
class OffsetDateTimeAdapter : JsonSerializer<OffsetDateTime>, JsonDeserializer<OffsetDateTime> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, type: Type, context: JsonDeserializationContext): OffsetDateTime {
        val jsonPrimitive = json.asJsonPrimitive

        // if provided as String - '2011-12-03T10:15:30+01:00'
        if (jsonPrimitive.isString) {
            return OffsetDateTime.parse(jsonPrimitive.asString, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        }

        throw JsonParseException("Unable to parse OffsetDateTime. DateTime was not provided as string.")
    }

    override fun serialize(offsetDateTime: OffsetDateTime, type: Type, context: JsonSerializationContext): JsonElement {
        val s = offsetDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        return JsonPrimitive(s)
    }
}
