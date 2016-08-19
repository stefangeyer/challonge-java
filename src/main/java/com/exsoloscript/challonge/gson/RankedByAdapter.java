package com.exsoloscript.challonge.gson;

import com.exsoloscript.challonge.model.enumeration.RankedBy;
import com.exsoloscript.challonge.model.enumeration.query.GrandFinalsModifier;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Type adapter for the {@link RankedBy} enum.
 *
 * @author EXSolo
 * @version 20160819.1
 */
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
