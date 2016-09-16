package com.exsoloscript.challonge.gson;

import com.exsoloscript.challonge.model.Match;
import com.exsoloscript.challonge.model.Participant;
import com.exsoloscript.challonge.model.Tournament;
import com.exsoloscript.challonge.model.enumeration.TournamentType;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Singleton;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Type adapter for the {@link Tournament} class.
 * The received json object is being unpacked.
 *
 * @author EXSolo
 * @version 20160819.1
 */
@Singleton
public class TournamentAdapter implements GsonAdapter, JsonDeserializer<Tournament> {

    private Gson gson;

    public TournamentAdapter() {
        gson = new GsonBuilder()
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeAdapter())
                .registerTypeAdapter(new TypeToken<List<Participant>>() {}.getType(), new ParticipantListAdapter())
                .registerTypeAdapter(new TypeToken<List<Match>>() {}.getType(), new MatchListAdapter())
                .create();
    }

    @Override
    public Tournament deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonElement tournamentElement = jsonElement.getAsJsonObject().get("tournament");
        return gson.fromJson(tournamentElement, Tournament.class);
    }
}
