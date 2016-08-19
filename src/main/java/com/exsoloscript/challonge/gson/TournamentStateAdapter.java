package com.exsoloscript.challonge.gson;

import com.exsoloscript.challonge.model.enumeration.TournamentState;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Type adapter for the {@link TournamentState} enum.
 *
 * @author EXSolo
 * @version 20160819.1
 */
public class TournamentStateAdapter implements GsonAdapter, JsonSerializer<TournamentState>, JsonDeserializer<TournamentState> {

    @Override
    public JsonElement serialize(TournamentState tournamentState, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(tournamentState.toString());
    }

    @Override
    public TournamentState deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return TournamentState.fromString(jsonElement.getAsString());
    }
}
