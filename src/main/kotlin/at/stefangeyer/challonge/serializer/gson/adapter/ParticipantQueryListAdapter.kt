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
import com.google.gson.*

import java.lang.reflect.Type

class ParticipantQueryListAdapter : JsonSerializer<List<ParticipantQuery>> {

    override fun serialize(participantQueryList: List<ParticipantQuery>, type: Type, jsonSerializationContext: JsonSerializationContext): JsonElement {
        val base = JsonObject()
        val participantArray = JsonArray()

        for (query in participantQueryList) {
            /**
            val pqEntity = JsonObject()
            pqEntity.addProperty("name", enum.name)
            pqEntity.addProperty("email", enum.email)
            pqEntity.addProperty("challonge_username", enum.challongeUsername)
            pqEntity.addProperty("seed", enum.seed)
            pqEntity.addProperty("misc", enum.misc)
            pqEntity.addProperty("invite_name_or_email", enum.inviteNameOrEmail)

            participantArray.add(pqEntity)
             */
            participantArray.add(jsonSerializationContext.serialize(query))
        }

        base.add("participants", participantArray)
        return base
    }
}
