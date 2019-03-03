package at.stefangeyer.challonge.serializer.gson.adapter;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Type adapter for the [OffsetDateTime] class.
 *
 * @author Stefan Geyer
 * @version 2018-07-10
 */
public class OffsetDateTimeAdapter implements JsonSerializer<OffsetDateTime>, JsonDeserializer<OffsetDateTime> {

    @Override
    public OffsetDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonPrimitive jsonPrimitive = json.getAsJsonPrimitive();

        if (jsonPrimitive.isString()) {
            try {
                return OffsetDateTime.parse(jsonPrimitive.getAsString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            } catch (DateTimeParseException ignored) {
            }
        }

        throw new JsonParseException("Unable to parse OffsetDateTime. DateTime was not provided as string or an invalid format was used.");
    }

    @Override
    public JsonElement serialize(OffsetDateTime src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }
}
