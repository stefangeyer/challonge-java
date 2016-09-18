package com.exsoloscript.challonge.gson;

import com.exsoloscript.challonge.model.Match;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;

/**
 * Type adapter for the {@link Match} class.
 * The received json object is being unpacked.
 *
 * @author EXSolo
 * @version 20160825.1
 */
public class MatchAdapter implements GsonAdapter, JsonDeserializer<Match> {

    private Gson gson;

    public MatchAdapter() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeAdapter())
                .create();
    }

    @Override
    public Match deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonElement matchElement = jsonElement.getAsJsonObject().get("match");
        return this.gson.fromJson(matchElement, Match.class);
    }
}
