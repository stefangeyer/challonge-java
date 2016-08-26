package com.exsoloscript.challonge.gson;

import com.exsoloscript.challonge.model.Attachment;
import com.exsoloscript.challonge.model.Match;
import com.exsoloscript.challonge.model.Participant;
import com.exsoloscript.challonge.model.Tournament;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Type adapter for the {@link Match} class.
 * The received json object is being unpacked.
 *
 * @author EXSolo
 * @version 20160825.1
 */
public class MatchAdapter implements GsonAdapter, JsonDeserializer<Match> {

    private Gson gson = AdapterSuite.createGson(Tournament.class, Participant.class, Match.class, Attachment.class);

    @Override
    public Match deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonElement tournamentElement = jsonElement.getAsJsonObject().get("match");
        return this.gson.fromJson(tournamentElement, Match.class);
    }
}
