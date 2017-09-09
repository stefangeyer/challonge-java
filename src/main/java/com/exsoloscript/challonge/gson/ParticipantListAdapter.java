/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 EXSolo <exsoloscripter at gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.exsoloscript.challonge.gson;

import com.exsoloscript.challonge.model.Participant;
import com.google.common.collect.Lists;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

public class ParticipantListAdapter implements GsonAdapter, JsonDeserializer<List<Participant>> {

    private final Gson gson;

    ParticipantListAdapter() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Participant.class, new ParticipantAdapter())
                .create();
    }

    @Override
    public List<Participant> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement.isJsonArray()) {
            JsonArray array = jsonElement.getAsJsonArray();
            List<Participant> participants = Lists.newArrayList();

            for (JsonElement arrayElement : array) {
                participants.add(this.gson.fromJson(arrayElement, Participant.class));
            }

            return participants;
        }

        return null;
    }
}
