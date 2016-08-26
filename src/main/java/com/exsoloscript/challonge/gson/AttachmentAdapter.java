package com.exsoloscript.challonge.gson;

import com.exsoloscript.challonge.model.Attachment;
import com.exsoloscript.challonge.model.Match;
import com.exsoloscript.challonge.model.Participant;
import com.exsoloscript.challonge.model.Tournament;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Type adapter for the {@link Attachment} class.
 * The received json object is being unpacked.
 *
 * @author EXSolo
 * @version 20160825.1
 */
public class AttachmentAdapter implements GsonAdapter, JsonDeserializer<Attachment> {

    private Gson gson = AdapterSuite.createGson(Tournament.class, Participant.class, Match.class, Attachment.class);

    @Override
    public Attachment deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonElement tournamentElement = jsonElement.getAsJsonObject().get("attachment");
        return this.gson.fromJson(tournamentElement, Attachment.class);
    }
}