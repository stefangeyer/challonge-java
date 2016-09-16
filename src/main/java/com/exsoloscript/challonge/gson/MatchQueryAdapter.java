package com.exsoloscript.challonge.gson;

import com.exsoloscript.challonge.model.query.MatchQuery;
import com.google.gson.*;

import java.lang.reflect.Type;

public class MatchQueryAdapter implements GsonAdapter, JsonSerializer<MatchQuery> {

    private Gson gson;

    public MatchQueryAdapter() {
        this.gson = new GsonBuilder().create();
    }

    @Override
    public JsonElement serialize(MatchQuery matchQuery, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject parent = new JsonObject();

        parent.add("match", gson.toJsonTree(matchQuery));

        return parent;
    }
}
