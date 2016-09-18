package com.exsoloscript.challonge.gson;

import com.exsoloscript.challonge.model.Attachment;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;

/**
 * Type adapter for the {@link Attachment} class.
 * The received json object is being unpacked.
 *
 * @author EXSolo
 * @version 20160825.1
 */
public class AttachmentAdapter implements GsonAdapter, JsonDeserializer<Attachment> {

    private Gson gson;

    public AttachmentAdapter() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeAdapter())
                .create();
    }

    @Override
    public Attachment deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonElement attachmentElement = jsonElement.getAsJsonObject().get("match_attachment");
        return this.gson.fromJson(attachmentElement, Attachment.class);
    }
}