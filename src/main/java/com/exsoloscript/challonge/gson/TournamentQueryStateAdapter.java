package com.exsoloscript.challonge.gson;

import com.exsoloscript.challonge.model.query.TournamentQuery.TournamentQueryState;
import com.google.gson.*;

import java.lang.reflect.Type;

public class TournamentQueryStateAdapter implements GsonAdapter, JsonSerializer<TournamentQueryState>, JsonDeserializer<TournamentQueryState> {
    @Override
    public TournamentQueryState deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return TournamentQueryState.fromString(jsonElement.getAsString());
    }

    @Override
    public JsonElement serialize(TournamentQueryState tournamentQueryState, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(tournamentQueryState.toString());
    }
}
