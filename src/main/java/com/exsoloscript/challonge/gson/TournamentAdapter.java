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

package com.exsoloscript.challonge.gson;

import com.exsoloscript.challonge.model.Match;
import com.exsoloscript.challonge.model.Participant;
import com.exsoloscript.challonge.model.Tournament;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Type adapter for the {@link Tournament} class.
 * The received json object is being unpacked.
 *
 * @author EXSolo
 * @version 20160819.1
 */
public class TournamentAdapter implements GsonAdapter, JsonDeserializer<Tournament> {

    private final Gson gson;

    TournamentAdapter() {
        gson = new GsonBuilder()
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeAdapter())
                .registerTypeAdapter(new TypeToken<List<Participant>>() {
                }.getType(), new ParticipantListAdapter())
                .registerTypeAdapter(new TypeToken<List<Match>>() {
                }.getType(), new MatchListAdapter())
                .create();
    }

    @Override
    public Tournament deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonElement tournamentElement = jsonElement.getAsJsonObject().get("tournament");
        return gson.fromJson(tournamentElement, Tournament.class);
    }
}
