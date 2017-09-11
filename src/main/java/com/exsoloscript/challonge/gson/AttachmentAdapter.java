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

import com.exsoloscript.challonge.model.Attachment;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;

/**
 * Type adapter for the {@link Attachment} class.
 * The received json object is being unpacked.
 *
 * @author EXSolo
 * @version 20160825.1
 */
public class AttachmentAdapter implements GsonAdapter, JsonDeserializer<Attachment> {

    private final Gson gson;

    AttachmentAdapter() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeAdapter())
                .create();
    }

    @Override
    public Attachment deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonElement attachmentElement = jsonElement.getAsJsonObject().get("match_attachment");
        return this.gson.fromJson(attachmentElement, Attachment.class);
    }
}