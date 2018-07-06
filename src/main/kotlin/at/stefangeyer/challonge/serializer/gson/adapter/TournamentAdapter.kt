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
import at.stefangeyer.challonge.model.Tournament
import com.google.gson.*
import com.google.gson.reflect.TypeToken

import java.lang.reflect.Type
import java.time.OffsetDateTime

/**
 * Type adapter for the [Tournament] class.
 * The received json object is being unpacked.
 *
 * @author EXSolo
 * @version 20160819.1
 */
class TournamentAdapter internal constructor() : JsonDeserializer<Tournament> {

    private val gson: Gson

    init {
        gson = GsonBuilder()
                .registerTypeAdapter(OffsetDateTime::class.java, OffsetDateTimeAdapter())
                .registerTypeAdapter(object : TypeToken<List<Participant>>() {

                }.type, ParticipantListAdapter())
                .registerTypeAdapter(object : TypeToken<List<Match>>() {

                }.type, MatchListAdapter())
                .create()
    }

    @Throws(JsonParseException::class)
    override fun deserialize(jsonElement: JsonElement, type: Type, jsonDeserializationContext: JsonDeserializationContext): Tournament {
        val tournamentElement = jsonElement.asJsonObject.get("tournament")
        return gson.fromJson(tournamentElement, Tournament::class.java)
    }
}
