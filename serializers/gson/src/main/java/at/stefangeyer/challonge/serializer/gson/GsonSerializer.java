package at.stefangeyer.challonge.serializer.gson;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.query.MatchQuery;
import at.stefangeyer.challonge.model.query.ParticipantQuery;
import at.stefangeyer.challonge.model.query.TournamentQuery;
import at.stefangeyer.challonge.model.wrapper.MatchWrapperListWrapper;
import at.stefangeyer.challonge.model.wrapper.ParticipantWrapperListWrapper;
import at.stefangeyer.challonge.serializer.Serializer;
import at.stefangeyer.challonge.serializer.gson.adapter.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;

/**
 * Gson serializer
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
public class GsonSerializer implements Serializer {

    private final Gson gson;

    public GsonSerializer(GsonBuilder builder) {
        // register type adapters
        builder.registerTypeAdapter(Tournament.class, new TournamentAdapter());
        builder.registerTypeAdapter(Participant.class, new ParticipantAdapter());
        builder.registerTypeAdapter(Match.class, new MatchAdapter());
        builder.registerTypeAdapter(Attachment.class, new AttachmentAdapter());

        builder.registerTypeAdapter(TournamentQuery.class, new TournamentQueryAdapter());
        builder.registerTypeAdapter(ParticipantQuery.class, new ParticipantQueryAdapter());
        builder.registerTypeAdapter(MatchQuery.class, new MatchQueryAdapter());

        builder.registerTypeAdapter(ParticipantWrapperListWrapper.class, new ParticipantWrapperListWrapperAdapter());
        builder.registerTypeAdapter(MatchWrapperListWrapper.class, new MatchWrapperListWrapperAdapter());

        builder.registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeAdapter());

        this.gson = builder.create();
    }

    /**
     * Create serializer using a new GsonBuilder object
     */
    public GsonSerializer() {
        this(new GsonBuilder());
    }

    @Override
    public String serialize(Object obj) {
        return this.gson.toJson(obj);
    }

    @Override
    public <T> T deserialize(String string, Type type) {
        return this.gson.fromJson(string, type);
    }
}
