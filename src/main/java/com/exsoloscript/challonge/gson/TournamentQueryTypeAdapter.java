package com.exsoloscript.challonge.gson;

import com.exsoloscript.challonge.model.enumeration.query.TournamentQueryType;
import com.google.gson.*;

import java.lang.reflect.Type;

public class TournamentQueryTypeAdapter implements GsonAdapter, JsonSerializer<TournamentQueryType>, JsonDeserializer<TournamentQueryType> {
    @Override
    public TournamentQueryType deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return TournamentQueryType.fromString(jsonElement.getAsString());
    }

    @Override
    public JsonElement serialize(TournamentQueryType tournamentQueryState, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(tournamentQueryState.toString());
    }
}
