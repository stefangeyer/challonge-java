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
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type
import java.time.OffsetDateTime

/**
 * Type adapter for the [Attachment] class.
 * The received json object is being unpacked.
 *
 * @author Stefan Geyer
 * @version 2018-07-06
 */
class AttachmentAdapter : JsonDeserializer<Attachment> {

    @Throws(JsonParseException::class)
    override fun deserialize(jsonElement: JsonElement, type: Type, context: JsonDeserializationContext): Attachment {
        val e = jsonElement.asJsonObject

        return Attachment(
                id = e.get("id").asLong, matchId = e.get("match_id").asLong, userId = e.get("user_id").asLong,
                description = e.get("description").asString,
                url = e.get("url").asString, originalFileName = e.get("original_file_name").asString,
                createdAt = context.deserialize(e.get("created_at"), OffsetDateTime::class.java),
                updatedAt = context.deserialize(e.get("updated_at"), OffsetDateTime::class.java),
                assetFileName = e.get("asset_file_name").asString, assetContentType = e.get("asset_content_type").asString,
                assetFileSize = e.get("asset_file_size").asLong, assetUrl = e.get("asset_url").asString
        )
    }
}