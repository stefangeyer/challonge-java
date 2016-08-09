package com.exsoloscript.challonge.gson;

import com.exsoloscript.challonge.model.Tournament;
import com.google.gson.*;

import java.lang.reflect.Type;

public class TournamentAdapter implements GsonAdapter, JsonDeserializer<Tournament> {

    private Gson gson = AdapterSuite.createGson(Tournament.class);

    @Override
    public Tournament deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonElement tournamentElement = jsonElement.getAsJsonObject().get("tournament");
        return this.gson.fromJson(tournamentElement, Tournament.class);
    }

}
