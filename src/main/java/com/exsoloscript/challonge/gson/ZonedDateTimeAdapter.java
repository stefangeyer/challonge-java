package com.exsoloscript.challonge.gson;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Type adapter for the {@link ZonedDateTime} class.
 *
 * @author EXSolo
 * @version 20160819.1
 */
public class ZonedDateTimeAdapter implements GsonAdapter, JsonSerializer<ZonedDateTime>, JsonDeserializer<ZonedDateTime> {

    @Override
    public ZonedDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonPrimitive jsonPrimitive = json.getAsJsonPrimitive();

        // if provided as String - '2011-12-03T10:15:30+01:00'
        if (jsonPrimitive.isString()) {
            return ZonedDateTime.parse(jsonPrimitive.getAsString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        }

        // if provided as Long
        if (jsonPrimitive.isNumber()) {
            return ZonedDateTime.ofInstant(Instant.ofEpochMilli(jsonPrimitive.getAsLong()), ZoneId.systemDefault());
        }

        throw new JsonParseException("Unable to parse ZonedDateTime");
    }

    @Override
    public JsonElement serialize(ZonedDateTime zonedDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        String s = zonedDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        return new JsonPrimitive(s);
    }
}
