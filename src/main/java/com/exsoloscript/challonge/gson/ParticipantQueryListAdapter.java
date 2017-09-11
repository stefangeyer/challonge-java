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

import com.exsoloscript.challonge.model.query.ParticipantQuery;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

public class ParticipantQueryListAdapter implements GsonAdapter, JsonSerializer<List<ParticipantQuery>> {

    @Override
    public JsonElement serialize(List<ParticipantQuery> participantQueryList, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject base = new JsonObject();
        JsonArray participantArray = new JsonArray();

        for (ParticipantQuery query : participantQueryList) {
            participantArray.add(jsonSerializationContext.serialize(query));
        }

        base.add("participants", participantArray);
        return base;
    }
}
