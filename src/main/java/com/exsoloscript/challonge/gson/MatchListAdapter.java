package com.exsoloscript.challonge.gson;

import com.exsoloscript.challonge.model.Match;
import com.google.common.collect.Lists;
import com.google.gson.*;
import com.google.inject.Singleton;

import java.lang.reflect.Type;
import java.util.List;

@Singleton
public class MatchListAdapter implements GsonAdapter, JsonDeserializer<List<Match>> {

    private Gson gson;

    public MatchListAdapter() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Match.class, new MatchAdapter())
                .create();
    }

    @Override
    public List<Match> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement.isJsonArray()) {
            JsonArray array = jsonElement.getAsJsonArray();
            List<Match> matches = Lists.newArrayList();

            for (JsonElement arrayElement : array) {
                matches.add(this.gson.fromJson(arrayElement, Match.class));
            }

            return matches;
        }

        return null;
    }
}
