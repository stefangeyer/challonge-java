package com.exsoloscript.challonge.gson;

import com.exsoloscript.challonge.model.Tournament;
import com.exsoloscript.challonge.model.enumeration.query.GrandFinalsModifier;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Type adapter for the {@link Tournament} class.
 * The received json object is being unpacked.
 *
 * @author EXSolo
 * @version 20160819.1
 */
public class TournamentAdapter implements GsonAdapter, JsonDeserializer<Tournament> {

    private Gson gson = AdapterSuite.createGson(Tournament.class);

    @Override
    public Tournament deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonElement tournamentElement = jsonElement.getAsJsonObject().get("tournament");
        return this.gson.fromJson(tournamentElement, Tournament.class);
    }

}
