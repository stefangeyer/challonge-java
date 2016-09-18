package com.exsoloscript.challonge.gson;

import com.exsoloscript.challonge.model.query.TournamentQuery;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;

public class TournamentQueryAdapter implements GsonAdapter, JsonSerializer<TournamentQuery> {

    private Gson gson;

    public TournamentQueryAdapter() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeAdapter())
                .create();
    }

    @Override
    public JsonElement serialize(TournamentQuery tournamentQuery, Type type, JsonSerializationContext jsonSerializationContext) {
        return this.gson.toJsonTree(tournamentQuery);
    }
}
