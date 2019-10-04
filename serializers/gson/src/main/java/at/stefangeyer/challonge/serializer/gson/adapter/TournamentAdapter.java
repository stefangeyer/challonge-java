package at.stefangeyer.challonge.serializer.gson.adapter;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.enumeration.RankedBy;
import at.stefangeyer.challonge.model.enumeration.TieBreak;
import at.stefangeyer.challonge.model.enumeration.TournamentState;
import at.stefangeyer.challonge.model.enumeration.TournamentType;
import at.stefangeyer.challonge.model.query.enumeration.GrandFinalsModifier;
import at.stefangeyer.challonge.model.wrapper.MatchWrapper;
import at.stefangeyer.challonge.model.wrapper.MatchWrapperListWrapper;
import at.stefangeyer.challonge.model.wrapper.ParticipantWrapper;
import at.stefangeyer.challonge.model.wrapper.ParticipantWrapperListWrapper;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TournamentAdapter implements JsonDeserializer<Tournament> {

    @Override
    public Tournament deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject e = json.getAsJsonObject().has("tournament") ?
                json.getAsJsonObject().get("tournament").getAsJsonObject() : json.getAsJsonObject();

        long id = e.get("id").getAsLong();
        String url = e.get("url").getAsString();
        String name = e.get("name").getAsString();
        TournamentType tournamentType = TournamentType.valueOf(e.get("tournament_type").getAsString().replace(" ", "_").toUpperCase());
        String subdomain = getOrNull(e, "subdomain") != null ? getOrNull(e, "subdomain").getAsString() : null;
        String description = getOrNull(e, "description") != null ? getOrNull(e, "description").getAsString() : null;
        boolean openSignup = e.get("open_signup").getAsBoolean();
        boolean holdThirdPlaceMatch = e.get("hold_third_place_match").getAsBoolean();
        float pointsForMatchWin = e.get("pts_for_match_win").getAsFloat();
        float pointsForMatchTie = e.get("pts_for_match_tie").getAsFloat();
        float pointsForGameWin = e.get("pts_for_game_win").getAsFloat();
        float pointsForGameTie = e.get("pts_for_game_tie").getAsFloat();
        float pointsForBye = e.get("pts_for_bye").getAsFloat();
        int swissRounds = e.get("swiss_rounds").getAsInt();
        RankedBy rankedBy = getOrNull(e, "ranked_by") != null ?
                RankedBy.valueOf(getOrNull(e, "ranked_by").getAsString().replace(" ", "_").toUpperCase()) : null;
        float roundRobinPointsForGameWin = e.get("rr_pts_for_game_win").getAsFloat();
        float roundRobinPointsForGameTie = e.get("rr_pts_for_game_tie").getAsFloat();
        float roundRobinPointsForMatchWin = e.get("rr_pts_for_match_win").getAsFloat();
        float roundRobinPointsForMatchTie = e.get("rr_pts_for_match_tie").getAsFloat();
        boolean acceptAttachments = e.get("accept_attachments").getAsBoolean();
        boolean hideForum = e.get("hide_forum").getAsBoolean();
        boolean showRounds = e.get("show_rounds").getAsBoolean();
        boolean privateOnly = e.get("private").getAsBoolean();
        boolean notifyUsersWhenMatchesOpen = e.get("notify_users_when_matches_open").getAsBoolean();
        boolean notifyUsersWhenTheTournamentEnds = e.get("notify_users_when_the_tournament_ends").getAsBoolean();
        boolean sequentialPairings = e.get("sequential_pairings").getAsBoolean();
        Integer signupCap = this.getOrNull(e, "signup_cap") != null ? this.getOrNull(e, "signup_cap").getAsInt() : null;
        OffsetDateTime startAt = context.deserialize(this.getOrNull(e, "start_at"), OffsetDateTime.class);
        Long checkInDuration = this.getOrNull(e, "check_in_duration") != null ? this.getOrNull(e, "check_in_duration").getAsLong() : null;
        boolean allowParticipantMatchReporting = e.get("allow_participant_match_reporting").getAsBoolean();
        boolean anonymousVoting = e.get("anonymous_voting").getAsBoolean();
        String category = getOrNull(e, "category") != null ? getOrNull(e, "category").getAsString() : null;
        OffsetDateTime completedAt = context.deserialize(e.get("completed_at"), OffsetDateTime.class);
        OffsetDateTime createdAt = context.deserialize(e.get("created_at"), OffsetDateTime.class);
        boolean createdByApi = e.get("created_by_api").getAsBoolean();
        boolean creditCapped = e.get("credit_capped").getAsBoolean();
        Long gameId = getOrNull(e, "game_id") != null ? getOrNull(e, "game_id").getAsLong() : null;
        boolean groupStagesEnabled = e.get("group_stages_enabled").getAsBoolean();
        boolean hideSeeds = e.get("hide_seeds").getAsBoolean();
        int maxPredictionsPerUser = e.get("max_predictions_per_user").getAsInt();
        int participantsCount = e.get("participants_count").getAsInt();
        int predictionMethod = e.get("prediction_method").getAsInt();
        OffsetDateTime predictionsOpenedAt = context.deserialize(e.get("predictions_opened_at"), OffsetDateTime.class);
        int progressMeter = e.get("progress_meter").getAsInt();
        boolean quickAdvance = e.get("quick_advance").getAsBoolean();
        boolean requireScoreAgreement = e.get("require_score_agreement").getAsBoolean();
        OffsetDateTime startedAt = context.deserialize(e.get("started_at"), OffsetDateTime.class);
        OffsetDateTime startedCheckingInAt = context.deserialize(e.get("started_checking_in_at"), OffsetDateTime.class);
        TournamentState state = TournamentState.valueOf(e.get("state").getAsString().replace(" ", "_").toUpperCase());
        Boolean teams = getOrNull(e, "teams") != null ? getOrNull(e, "teams").getAsBoolean() : null;

        List<TieBreak> tieBreaks = new ArrayList<>();
        if (getOrNull(e, "tie_breaks") != null) {
            getOrNull(e, "tie_breaks").getAsJsonArray().forEach(inner -> tieBreaks.add(TieBreak.valueOf(inner.getAsString().replace(" ", "_").toUpperCase())));
        }

        String descriptionSource = getOrNull(e, "description_source") != null ? getOrNull(e, "description_source").getAsString() : null;
        boolean participantsLocked = e.get("participants_locked").getAsBoolean();
        String gameName = getOrNull(e, "game_name") != null ? getOrNull(e, "game_name").getAsString() : null;
        boolean participantsSwappable = e.get("participants_swappable").getAsBoolean();
        boolean teamConvertable = e.get("team_convertable").getAsBoolean();
        boolean groupStagesWereStarted = e.get("group_stages_were_started").getAsBoolean();
        OffsetDateTime lockedAt = context.deserialize(e.get("locked_at"), OffsetDateTime.class);
        Long eventId = getOrNull(e, "event_id") != null ? getOrNull(e, "event_id").getAsLong() : null;
        Boolean publicPredictionsBeforeStartTime = getOrNull(e, "public_predictions_before_start_time") != null ?
                getOrNull(e, "public_predictions_before_start_time").getAsBoolean() : null;
        OffsetDateTime updatedAt = context.deserialize(e.get("updated_at"), OffsetDateTime.class);
        String fullChallongeUrl = getOrNull(e, "full_challonge_url") != null ? getOrNull(e, "full_challonge_url").getAsString() : null;
        String liveImageUrl = getOrNull(e, "live_image_url") != null ? getOrNull(e, "live_image_url").getAsString() : null;
        boolean reviewBeforeFinalizing = e.get("review_before_finalizing").getAsBoolean();
        boolean acceptingPredictions = e.get("accepting_predictions").getAsBoolean();
        Boolean ranked = e.get("ranked").getAsBoolean();
        Boolean predictTheLosersBracket = getOrNull(e, "predict_the_losers_bracket") != null ? e.get("predict_the_losers_bracket").getAsBoolean() : null;
        Integer roundRobinIterations = getOrNull(e, "rr_iterations") != null ? e.get("rr_iterations").getAsInt() : null;
        Float registrationFee = e.get("registration_fee").getAsFloat();
        String registrationType = e.get("registration_type").getAsString();

        GrandFinalsModifier grandFinalsModifier = null;
        if (getOrNull(e, "grand_finals_modifier") != null) {
            grandFinalsModifier = GrandFinalsModifier.
                    valueOf(getOrNull(e, "grand_finals_modifier").getAsString().replace(" ", "_").toUpperCase());
        }

        List<Participant> participants = new ArrayList<>();
        JsonElement pe = e.get("participants");
        if (pe != null && !pe.isJsonNull()) {
            participants.addAll(context.<ParticipantWrapperListWrapper>deserialize(pe, ParticipantWrapperListWrapper.class)
                    .getParticipants().stream().map(ParticipantWrapper::getParticipant).collect(Collectors.toList()));
        }

        List<Match> matches = new ArrayList<>();
        JsonElement pm = e.get("matches");
        if (pm != null && !pm.isJsonNull()) {
            matches.addAll(context.<MatchWrapperListWrapper>deserialize(pm, MatchWrapperListWrapper.class).getMatches().stream().
                    map(MatchWrapper::getMatch).collect(Collectors.toList()));
        }

        return Tournament.builder().id(id).name(name).url(url).tournamentType(tournamentType).subdomain(subdomain)
                .description(description).openSignup(openSignup).holdThirdPlaceMatch(holdThirdPlaceMatch)
                .pointsForMatchWin(pointsForMatchWin).pointsForMatchTie(pointsForMatchTie)
                .pointsForGameWin(pointsForGameWin).pointsForGameTie(pointsForGameTie).pointsForBye(pointsForBye)
                .swissRounds(swissRounds).rankedBy(rankedBy).roundRobinPointsForGameWin(roundRobinPointsForGameWin)
                .roundRobinPointsForGameTie(roundRobinPointsForGameTie).roundRobinPointsForMatchWin(roundRobinPointsForMatchWin)
                .roundRobinPointsForMatchTie(roundRobinPointsForMatchTie).acceptAttachments(acceptAttachments)
                .hideForum(hideForum).showRounds(showRounds).privateOnly(privateOnly)
                .notifyUsersWhenMatchesOpen(notifyUsersWhenMatchesOpen)
                .notifyUsersWhenTheTournamentEnds(notifyUsersWhenTheTournamentEnds)
                .sequentialPairings(sequentialPairings).signupCap(signupCap).startAt(startAt)
                .checkInDuration(checkInDuration).allowParticipantMatchReporting(allowParticipantMatchReporting)
                .anonymousVoting(anonymousVoting).category(category).completedAt(completedAt).createdAt(createdAt)
                .createdByApi(createdByApi).creditCapped(creditCapped).gameId(gameId).groupStagesEnabled(groupStagesEnabled)
                .hideSeeds(hideSeeds).maxPredictionsPerUser(maxPredictionsPerUser).participantsCount(participantsCount)
                .predictionMethod(predictionMethod).predictionsOpenedAt(predictionsOpenedAt).progressMeter(progressMeter)
                .quickAdvance(quickAdvance).requireScoreAgreement(requireScoreAgreement).startedAt(startedAt)
                .startedCheckingInAt(startedCheckingInAt).state(state).teams(teams).tieBreaks(tieBreaks)
                .updatedAt(updatedAt).descriptionSource(descriptionSource).fullChallongeUrl(fullChallongeUrl)
                .liveImageUrl(liveImageUrl).reviewBeforeFinalizing(reviewBeforeFinalizing)
                .acceptingPredictions(acceptingPredictions).participantsLocked(participantsLocked).gameName(gameName)
                .participantsSwappable(participantsSwappable).teamConvertable(teamConvertable)
                .groupStagesWereStarted(groupStagesWereStarted).lockedAt(lockedAt).eventId(eventId)
                .publicPredictionsBeforeStartTime(publicPredictionsBeforeStartTime)
                .grandFinalsModifier(grandFinalsModifier).participants(participants).matches(matches)
                .ranked(ranked).predictTheLosersBracket(predictTheLosersBracket).roundRobinIterations(roundRobinIterations)
                .registrationFee(registrationFee).registrationType(registrationType).build();
    }

    private JsonElement getOrNull(JsonObject o, String key) {
        return !o.has(key) || o.get(key).isJsonNull() ? null : o.get(key);
    }
}
