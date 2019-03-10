package at.stefangeyer.challonge.serializer.gson.adapter;

import at.stefangeyer.challonge.model.wrapper.MatchWrapper;
import at.stefangeyer.challonge.model.wrapper.MatchWrapperListWrapper;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MatchWrapperListWrapperAdapter implements JsonDeserializer<MatchWrapperListWrapper> {

    @Override
    public MatchWrapperListWrapper deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray jsonArray = json.getAsJsonArray();
        List<MatchWrapper> list = new ArrayList<>();

        for (JsonElement e : jsonArray) {
            MatchWrapper wrapper = context.deserialize(e, MatchWrapper.class);
            list.add(wrapper);
        }

        return new MatchWrapperListWrapper(list);
    }
}
