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
import com.google.common.collect.Lists;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

public class MatchListAdapter implements GsonAdapter, JsonDeserializer<List<Match>> {

    private final Gson gson;

    MatchListAdapter() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Match.class, new MatchAdapter())
                .create();
    }

    @Override
    public List<Match> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement.isJsonArray()) {
            JsonArray array = jsonElement.getAsJsonArray();
            List<Match> matches = Lists.newArrayList();

            for (JsonElement arrayElement : array) {
                matches.add(this.gson.fromJson(arrayElement, Match.class));
            }

            return matches;
        }

        return null;
    }
}
