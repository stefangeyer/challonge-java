package at.stefangeyer.challonge.serializer.gson.adapter;

import at.stefangeyer.challonge.model.query.ParticipantQuery;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class ParticipantQueryAdapter implements JsonSerializer<ParticipantQuery> {

    @Override
    public JsonElement serialize(ParticipantQuery src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject entity = new JsonObject();

        if (src.getName() != null) {
            entity.addProperty("name", src.getName());
        }

        if (src.getEmail() != null) {
            entity.addProperty("email", src.getEmail());
        }

        if (src.getChallongeUsername() != null) {
            entity.addProperty("challonge_username", src.getChallongeUsername());
        }

        if (src.getSeed() != null) {
            entity.addProperty("seed", src.getSeed());
        }

        if (src.getMisc() != null) {
            entity.addProperty("misc", src.getMisc());
        }

        if (src.getInviteNameOrEmail() != null) {
            entity.addProperty("invite_name_or_email", src.getInviteNameOrEmail());
        }

        return entity;
    }
}
