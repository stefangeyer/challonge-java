package com.exsoloscript.challonge.gson;

import com.exsoloscript.challonge.model.enumerations.query.GrandFinalsModifier;
import com.google.gson.*;

import java.lang.reflect.Type;

public class GrandFinalsModifierAdapter implements GsonAdapter, JsonSerializer<GrandFinalsModifier>, JsonDeserializer<GrandFinalsModifier> {

    @Override
    public GrandFinalsModifier deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return GrandFinalsModifier.fromString(jsonElement.getAsString());
    }

    @Override
    public JsonElement serialize(GrandFinalsModifier grandFinalsModifier, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(grandFinalsModifier.toString());
    }
}
