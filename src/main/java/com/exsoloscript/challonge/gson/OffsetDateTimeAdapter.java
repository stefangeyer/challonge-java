package com.exsoloscript.challonge.gson;

import com.google.gson.*;
import com.google.inject.Singleton;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Type adapter for the {@link OffsetDateTime} class.
 *
 * @author EXSolo
 * @version 20160819.1
 */
@Singleton
public class OffsetDateTimeAdapter implements GsonAdapter, JsonSerializer<OffsetDateTime>, JsonDeserializer<OffsetDateTime> {

    @Override
    public OffsetDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonPrimitive jsonPrimitive = json.getAsJsonPrimitive();

        // if provided as String - '2011-12-03T10:15:30+01:00'
        if (jsonPrimitive.isString()) {
            return OffsetDateTime.parse(jsonPrimitive.getAsString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        }

        throw new JsonParseException("Unable to parse OffsetDateTime. DateTime was not provided as string.");
    }

    @Override
    public JsonElement serialize(OffsetDateTime offsetDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        String s = offsetDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        return new JsonPrimitive(s);
    }
}
