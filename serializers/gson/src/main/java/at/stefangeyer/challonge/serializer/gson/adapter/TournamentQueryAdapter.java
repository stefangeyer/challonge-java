package at.stefangeyer.challonge.serializer.gson.adapter;

import at.stefangeyer.challonge.model.query.TournamentQuery;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class TournamentQueryAdapter implements JsonSerializer<TournamentQuery> {

    @Override
    public JsonElement serialize(TournamentQuery src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject entity = new JsonObject();

        if (src.getName() != null) {
            entity.addProperty("name", src.getName());
        }

        if (src.getUrl() != null) {
            entity.addProperty("url", src.getUrl());
        }

        if (src.getTournamentType() != null) {
            entity.addProperty("tournament_type", src.getTournamentType().toString().toLowerCase().replace("_", " "));
        }

        if (src.getSubdomain() != null) {
            entity.addProperty("subdomain", src.getSubdomain());
        }

        if (src.getDescription() != null) {
            entity.addProperty("description", src.getDescription());
        }

        if (src.getOpenSignup() != null) {
            entity.addProperty("open_signup", src.getOpenSignup());
        }

        if (src.getHoldThirdPlaceMatch() != null) {
            entity.addProperty("hold_third_place_match", src.getHoldThirdPlaceMatch());
        }

        if (src.getPointsForMatchWin() != null) {
            entity.addProperty("pts_for_match_win", src.getPointsForMatchWin());
        }

        if (src.getPointsForMatchTie() != null) {
            entity.addProperty("pts_for_match_tie", src.getPointsForMatchTie());
        }

        if (src.getPointsForGameWin() != null) {
            entity.addProperty("pts_for_game_win", src.getPointsForGameWin());
        }

        if (src.getPointsForGameTie() != null) {
            entity.addProperty("pts_for_game_tie", src.getPointsForGameTie());
        }

        if (src.getPointsForBye() != null) {
            entity.addProperty("pts_for_bye", src.getPointsForBye());
        }

        if (src.getSwissRounds() != null) {
            entity.addProperty("swiss_rounds", src.getSwissRounds());
        }

        if (src.getRankedBy() != null) {
            entity.addProperty("ranked_by", src.getRankedBy().toString().toLowerCase().replace("_", " "));
        }

        if (src.getRoundRobinPointsForGameWin() != null) {
            entity.addProperty("rr_pts_for_game_win", src.getRoundRobinPointsForGameWin());
        }

        if (src.getRoundRobinPointsForGameTie() != null) {
            entity.addProperty("rr_pts_for_game_tie", src.getRoundRobinPointsForGameTie());
        }

        if (src.getRoundRobinPointsForMatchWin() != null) {
            entity.addProperty("rr_pts_for_match_win", src.getRoundRobinPointsForMatchWin());
        }

        if (src.getRoundRobinPointsForMatchTie() != null) {
            entity.addProperty("rr_pts_for_match_tie", src.getRoundRobinPointsForMatchTie());
        }

        if (src.getAcceptAttachments() != null) {
            entity.addProperty("accept_attachments", src.getAcceptAttachments());
        }

        if (src.getHideForum() != null) {
            entity.addProperty("hide_forum", src.getHideForum());
        }

        if (src.getShowRounds() != null) {
            entity.addProperty("show_rounds", src.getShowRounds());
        }

        if (src.getPrivateOnly() != null) {
            entity.addProperty("private", src.getPrivateOnly());
        }

        if (src.getNotifyUsersWhenMatchesOpen() != null) {
            entity.addProperty("notify_users_when_matches_open", src.getNotifyUsersWhenMatchesOpen());
        }

        if (src.getNotifyUsersWhenTheTournamentEnds() != null) {
            entity.addProperty("notify_users_when_the_tournament_ends", src.getNotifyUsersWhenTheTournamentEnds());
        }

        if (src.getSequentialPairings() != null) {
            entity.addProperty("sequential_pairings", src.getSequentialPairings());
        }

        if (src.getSignupCap() != null) {
            entity.addProperty("signup_cap", src.getSignupCap());
        }

        if (src.getStartAt() != null) {
            entity.add("start_at", context.serialize(src.getStartAt()));
        }

        if (src.getCheckInDuration() != null) {
            entity.addProperty("check_in_duration", src.getCheckInDuration());
        }

        if (src.getGrandFinalsModifier() != null) {
            entity.addProperty("grand_finals_modifier", src.getGrandFinalsModifier().toString().toLowerCase().replace("_", " "));
        }

        if (src.getTieBreaks() != null) {
            entity.add("tie_breaks", context.serialize(src.getTieBreaks()));
        }

        return entity;
    }
}
