package com.exsoloscript.challonge.gson;

import com.exsoloscript.challonge.model.enumeration.TournamentType;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Type adapter for the {@link TournamentType} enum.
 *
 * @author EXSolo
 * @version 20160819.1
 */
public class TournamentTypeAdapter implements GsonAdapter, JsonSerializer<TournamentType>, JsonDeserializer<TournamentType> {

    @Override
    public TournamentType deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return TournamentType.fromString(jsonElement.getAsString());
    }

    @Override
    public JsonElement serialize(TournamentType tournamentType, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(tournamentType.toString());
    }
}
