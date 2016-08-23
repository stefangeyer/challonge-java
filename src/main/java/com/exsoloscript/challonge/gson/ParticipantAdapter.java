package com.exsoloscript.challonge.gson;

import com.exsoloscript.challonge.model.Participant;
import com.exsoloscript.challonge.model.Tournament;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Type adapter for the {@link com.exsoloscript.challonge.model.Participant} class.
 * The received json object is being unpacked.
 *
 * @author EXSolo
 * @version 20160819.1
 */
public class ParticipantAdapter implements GsonAdapter, JsonDeserializer<Participant> {

    private Gson gson = AdapterSuite.createGson(Participant.class, Participant.class);

    public ParticipantAdapter() {
    }

    @Override
    public Participant deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonElement tournamentElement = jsonElement.getAsJsonObject().get("participant");
        return this.gson.fromJson(tournamentElement, Participant.class);
    }

}
