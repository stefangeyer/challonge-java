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
