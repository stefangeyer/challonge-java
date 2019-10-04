package at.stefangeyer.challonge.serializer.gson.adapter;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.enumeration.MatchState;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Type adapter for the [Match] class.
 * The received json object is being unpacked.
 *
 * @author Stefan Geyer
 * @version 2018-07-08
 */
public class MatchAdapter implements JsonDeserializer<Match> {

    @Override
    public Match deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject e = json.getAsJsonObject().has("match") ?
                json.getAsJsonObject().get("match").getAsJsonObject() : json.getAsJsonObject();

        long id = e.get("id").getAsLong();
        long tournamentId = e.get("tournament_id").getAsLong();
        Integer attachmentCount = getOrNull(e, "attachment_count") != null ? getOrNull(e, "attachment_count").getAsInt() : null;
        OffsetDateTime createdAt = context.deserialize(e.get("created_at"), OffsetDateTime.class);
        Long groupId = getOrNull(e, "group_id") != null ? getOrNull(e, "group_id").getAsLong() : null;
        boolean hasAttachment = e.get("has_attachment").getAsBoolean();
        String identifier = getOrNull(e, "identifier") != null ? getOrNull(e, "identifier").getAsString() : null;
        String location = getOrNull(e, "location") != null ? getOrNull(e, "location").getAsString() : null;
        OffsetDateTime updatedAt = context.deserialize(e.get("updated_at"), OffsetDateTime.class);
        MatchState state = MatchState.valueOf(e.get("state").getAsString().replace(" ", "_").toUpperCase());
        OffsetDateTime startedAt = context.deserialize(e.get("started_at"), OffsetDateTime.class);
        String scoresCsv = getOrNull(e, "scores_csv") != null ? getOrNull(e, "scores_csv").getAsString() : null;
        Long winnerId = getOrNull(e, "winner_id") != null ? getOrNull(e, "winner_id").getAsLong() : null;
        Long loserId = getOrNull(e, "loser_id") != null ? getOrNull(e, "loser_id").getAsLong() : null;
        Long player1Id = getOrNull(e, "player1_id") != null ? getOrNull(e, "player1_id").getAsLong() : null;
        Long player2Id = getOrNull(e, "player2_id") != null ? getOrNull(e, "player2_id").getAsLong() : null;
        boolean player1IsPrerequisiteMatchLoser = e.get("player1_is_prereq_match_loser").getAsBoolean();
        Long player1PrerequisiteMatchId = getOrNull(e, "player1_prereq_match_id") != null ?
                getOrNull(e, "player1_prereq_match_id").getAsLong() : null;
        Integer player1Votes = getOrNull(e, "player1_votes") != null ? getOrNull(e, "player1_votes").getAsInt() : null;
        boolean player2IsPrerequisiteMatchLoser = e.get("player2_is_prereq_match_loser").getAsBoolean();
        Long player2PrerequisiteMatchId = getOrNull(e, "player2_prereq_match_id") != null ?
                getOrNull(e, "player2_prereq_match_id").getAsLong() : null;
        Integer player2Votes = getOrNull(e, "player2_votes") != null ? getOrNull(e, "player2_votes").getAsInt() : null;
        String prerequisiteMatchIdsCsv = getOrNull(e, "prerequisite_match_ids_csv") != null ?
                getOrNull(e, "prerequisite_match_ids_csv").getAsString() : null;
        int round = e.get("round").getAsInt();
        OffsetDateTime scheduledTime = context.deserialize(e.get("scheduled_time"), OffsetDateTime.class);
        OffsetDateTime underwayAt = context.deserialize(e.get("underway_at"), OffsetDateTime.class);
        OffsetDateTime completedAt = context.deserialize(e.get("completed_at"), OffsetDateTime.class);
        Boolean optional = e.get("optional").getAsBoolean();
        Boolean forfeited = getOrNull(e, "forfeited") != null ? e.get("forfeited").getAsBoolean() : null;
        List<Attachment> attachments = context.deserialize(e.get("attachments"), new TypeToken<List<Attachment>>() {
        }.getType());

        return Match.builder().id(id).tournamentId(tournamentId).attachmentCount(attachmentCount).createdAt(createdAt)
                .groupId(groupId).hasAttachment(hasAttachment).identifier(identifier).location(location)
                .updatedAt(updatedAt).state(state).startedAt(startedAt).scoresCsv(scoresCsv).winnerId(winnerId)
                .loserId(loserId).player1Id(player1Id).player2Id(player2Id)
                .player1IsPrerequisiteMatchLoser(player1IsPrerequisiteMatchLoser)
                .player1PrerequisiteMatchId(player1PrerequisiteMatchId).player1Votes(player1Votes)
                .player2IsPrerequisiteMatchLoser(player2IsPrerequisiteMatchLoser)
                .player2PrerequisiteMatchId(player2PrerequisiteMatchId).player2Votes(player2Votes)
                .prerequisiteMatchIdsCsv(prerequisiteMatchIdsCsv).round(round).scheduledTime(scheduledTime)
                .completedAt(completedAt).optional(optional).forfeited(forfeited)
                .underwayAt(underwayAt).attachments(attachments).build();
    }

    private JsonElement getOrNull(JsonObject o, String key) {
        return !o.has(key) || o.get(key).isJsonNull() ? null : o.get(key);
    }
}
