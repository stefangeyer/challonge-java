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

import com.exsoloscript.challonge.model.query.MatchQuery;
import com.google.gson.*;

import java.lang.reflect.Type;

public class MatchQueryAdapter implements GsonAdapter, JsonSerializer<MatchQuery> {

    private final Gson gson;

    public MatchQueryAdapter() {
        this.gson = new GsonBuilder().create();
    }

    @Override
    public JsonElement serialize(MatchQuery matchQuery, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject parent = new JsonObject();

        parent.add("match", gson.toJsonTree(matchQuery));

        return parent;
    }
}
