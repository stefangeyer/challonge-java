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

import com.exsoloscript.challonge.model.query.TournamentQuery;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;

public class TournamentQueryAdapter implements GsonAdapter, JsonSerializer<TournamentQuery> {

    private final Gson gson;

    TournamentQueryAdapter() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeAdapter())
                .create();
    }

    @Override
    public JsonElement serialize(TournamentQuery tournamentQuery, Type type, JsonSerializationContext jsonSerializationContext) {
        return this.gson.toJsonTree(tournamentQuery);
    }
}
