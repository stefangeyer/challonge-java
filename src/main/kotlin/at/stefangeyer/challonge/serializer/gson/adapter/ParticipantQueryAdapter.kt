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

import at.stefangeyer.challonge.model.query.ParticipantQuery
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

class ParticipantQueryAdapter internal constructor() : JsonSerializer<ParticipantQuery> {
    override fun serialize(query: ParticipantQuery, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        val pqEntity = JsonObject()

        pqEntity.addProperty("name", query.name)
        pqEntity.addProperty("email", query.email)
        pqEntity.addProperty("challonge_username", query.challongeUsername)
        pqEntity.addProperty("seed", query.seed)
        pqEntity.addProperty("misc", query.misc)
        pqEntity.addProperty("invite_name_or_email", query.inviteNameOrEmail)

        return pqEntity
    }
}
