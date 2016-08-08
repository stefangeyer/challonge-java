package com.exsoloscript.challonge.gson;

import com.exsoloscript.challonge.model.Tournament.TournamentState;
import com.google.gson.*;

import java.lang.reflect.Type;

public class TournamentStateAdapter implements JsonSerializer<TournamentState>, JsonDeserializer<TournamentState> {

    @Override
    public JsonElement serialize(TournamentState tournamentState, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(tournamentState.toString());
    }

    @Override
    public TournamentState deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return TournamentState.fromString(jsonElement.getAsString());
    }
}
