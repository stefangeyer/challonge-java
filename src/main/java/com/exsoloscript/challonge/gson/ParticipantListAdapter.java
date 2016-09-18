package com.exsoloscript.challonge.gson;

import com.exsoloscript.challonge.model.Participant;
import com.google.common.collect.Lists;
import com.google.gson.*;
import com.google.inject.Singleton;

import java.lang.reflect.Type;
import java.util.List;

@Singleton
public class ParticipantListAdapter implements GsonAdapter, JsonDeserializer<List<Participant>> {

    private Gson gson;

    public ParticipantListAdapter() {
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
