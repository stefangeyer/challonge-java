package at.stefangeyer.challonge.serializer.gson.adapter;

import at.stefangeyer.challonge.model.Attachment;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;

/**
 * Type adapter for the [Attachment] class.
 * The received json object is being unpacked.
 *
 * @author Stefan Geyer
 * @version 2018-07-06
 */
public class AttachmentAdapter implements JsonDeserializer<Attachment> {

    @Override
    public Attachment deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject e = json.getAsJsonObject().has("match_attachment") ?
                json.getAsJsonObject().get("match_attachment").getAsJsonObject() : json.getAsJsonObject();

        long id = e.get("id").getAsLong();
        long matchId = e.get("match_id").getAsLong();
        long userId = e.get("user_id").getAsLong();
        String description = getOrNull(e, "description") != null ? getOrNull(e, "description").getAsString() : null;
        String url = getOrNull(e, "url") != null ? getOrNull(e, "url").getAsString() : null;
        String originalFileName = getOrNull(e, "original_file_name") != null ? getOrNull(e, "original_file_name").getAsString() : null;
        OffsetDateTime createdAt = context.deserialize(e.get("created_at"), OffsetDateTime.class);
        OffsetDateTime updatedAt = context.deserialize(e.get("updated_at"), OffsetDateTime.class);
        String assetFileName = getOrNull(e, "asset_file_name") != null ? getOrNull(e, "asset_file_name").getAsString() : null;
        String assetContentType = getOrNull(e, "asset_content_type") != null ? getOrNull(e, "asset_content_type").getAsString() : null;
        Long assetFileSize = getOrNull(e, "asset_file_size") != null ? getOrNull(e, "asset_file_size").getAsLong() : null;
        String assetUrl = getOrNull(e, "asset_url") != null ? getOrNull(e, "asset_url").getAsString() : null;
        if (assetUrl != null) {
            assetUrl = "https:" + assetUrl;
        }

        return Attachment.builder().id(id).matchId(matchId).userId(userId).description(description).url(url)
                .originalFileName(originalFileName).createdAt(createdAt).updatedAt(updatedAt).assetFileName(assetFileName)
                .assetContentType(assetContentType).assetFileSize(assetFileSize).assetUrl(assetUrl).build();
    }

    private JsonElement getOrNull(JsonObject o, String key) {
        return !o.has(key) || o.get(key).isJsonNull() ? null : o.get(key);
    }
}
