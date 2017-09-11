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

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Type adapter for the {@link OffsetDateTime} class.
 *
 * @author EXSolo
 * @version 20160819.1
 */
public class OffsetDateTimeAdapter implements GsonAdapter, JsonSerializer<OffsetDateTime>, JsonDeserializer<OffsetDateTime> {

    @Override
    public OffsetDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonPrimitive jsonPrimitive = json.getAsJsonPrimitive();

        // if provided as String - '2011-12-03T10:15:30+01:00'
        if (jsonPrimitive.isString()) {
            return OffsetDateTime.parse(jsonPrimitive.getAsString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        }

        throw new JsonParseException("Unable to parse OffsetDateTime. DateTime was not provided as string.");
    }

    @Override
    public JsonElement serialize(OffsetDateTime offsetDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        String s = offsetDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        return new JsonPrimitive(s);
    }
}
