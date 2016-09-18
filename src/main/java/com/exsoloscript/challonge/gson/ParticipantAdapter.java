package com.exsoloscript.challonge.gson;

import com.exsoloscript.challonge.model.Participant;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;

/**
 * Type adapter for the {@link com.exsoloscript.challonge.model.Participant} class.
 * The received json object is being unpacked.
 *
 * @author EXSolo
 * @version 20160819.1
 */
public class ParticipantAdapter implements GsonAdapter, JsonDeserializer<Participant> {

    private Gson gson;

    public ParticipantAdapter() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeAdapter())
                .create();
    }

    @Override
    public Participant deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonElement participantElement = jsonElement.getAsJsonObject().get("participant");
        return this.gson.fromJson(participantElement, Participant.class);
    }

}
