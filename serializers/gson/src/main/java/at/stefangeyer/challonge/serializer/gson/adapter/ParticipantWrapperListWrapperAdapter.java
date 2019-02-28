package at.stefangeyer.challonge.serializer.gson.adapter;

import at.stefangeyer.challonge.model.wrapper.ParticipantWrapper;
import at.stefangeyer.challonge.model.wrapper.ParticipantWrapperListWrapper;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ParticipantWrapperListWrapperAdapter implements JsonDeserializer<ParticipantWrapperListWrapper> {

    @Override
    public ParticipantWrapperListWrapper deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray jsonArray = json.getAsJsonArray();
        List<ParticipantWrapper> list = new ArrayList<>();

        for (JsonElement e : jsonArray) {
            ParticipantWrapper wrapper = context.deserialize(e, ParticipantWrapper.class);
            list.add(wrapper);
        }

        return new ParticipantWrapperListWrapper(list);
    }
}
