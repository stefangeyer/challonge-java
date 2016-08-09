package com.exsoloscript.challonge.gson;

import com.exsoloscript.challonge.model.Tournament.RankedBy;
import com.google.gson.*;

import java.lang.reflect.Type;

public class RankedByAdapter implements GsonAdapter, JsonSerializer<RankedBy>, JsonDeserializer<RankedBy> {

    @Override
    public RankedBy deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return RankedBy.fromString(jsonElement.getAsString());
    }

    @Override
    public JsonElement serialize(RankedBy rankedBy, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(rankedBy.toString());
    }
}
