/*
 * Copyright 2017 Stefan Geyer <stefangeyer at outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package at.stefangeyer.challonge.serializer.gson.adapter

import at.stefangeyer.challonge.model.query.TournamentQuery
import com.google.gson.*
import java.lang.reflect.Type

class TournamentQueryAdapter internal constructor() : JsonSerializer<TournamentQuery> {

    override fun serialize(query: TournamentQuery, type: Type, context: JsonSerializationContext): JsonElement {
        val parent = JsonObject()

        val tqEntity = JsonObject()

        if (query.name != null) tqEntity.addProperty("name", query.name)
        if (query.url != null) tqEntity.addProperty("url", query.url)
        if (query.tournamentType != null) tqEntity.addProperty("tournament_type", query.tournamentType.toString().toLowerCase().replace("_", " "))
        if (query.subdomain != null) tqEntity.addProperty("subdomain", query.subdomain)
        if (query.description != null) tqEntity.addProperty("description", query.description)
        if (query.openSignup != null) tqEntity.addProperty("open_signup", query.openSignup)
        if (query.holdThirdPlaceMatch != null) tqEntity.addProperty("hold_third_place_match", query.holdThirdPlaceMatch)
        if (query.pointsForMatchWin != null) tqEntity.addProperty("pts_for_match_win", query.pointsForMatchWin)
        if (query.pointsForMatchTie != null) tqEntity.addProperty("pts_for_match_tie", query.pointsForMatchTie)
        if (query.pointsForGameWin != null) tqEntity.addProperty("pts_for_game_win", query.pointsForGameWin)
        if (query.pointsForGameTie != null) tqEntity.addProperty("pts_for_game_tie", query.pointsForGameTie)
        if (query.pointsForBye != null) tqEntity.addProperty("pts_for_bye", query.pointsForBye)
        if (query.swissRounds != null) tqEntity.addProperty("swiss_rounds", query.swissRounds)
        if (query.rankedBy != null) tqEntity.addProperty("ranked_by", query.rankedBy.toString().toLowerCase().replace("_", " "))
        if (query.roundRobinPointsForGameWin != null) tqEntity.addProperty("rr_pts_for_game_win", query.roundRobinPointsForGameWin)
        if (query.roundRobinPointsForGameTie != null) tqEntity.addProperty("rr_pts_for_game_tie", query.roundRobinPointsForGameTie)
        if (query.roundRobinPointsForMatchWin != null) tqEntity.addProperty("rr_pts_for_match_win", query.roundRobinPointsForMatchWin)
        if (query.roundRobinPointsForMatchTie != null) tqEntity.addProperty("rr_pts_for_match_tie", query.roundRobinPointsForMatchTie)
        if (query.acceptAttachments != null) tqEntity.addProperty("accept_attachments", query.acceptAttachments)
        if (query.hideForum != null) tqEntity.addProperty("hide_forum", query.hideForum)
        if (query.showRounds != null) tqEntity.addProperty("show_rounds", query.showRounds)
        if (query.private != null) tqEntity.addProperty("private", query.private)
        if (query.notifyUsersWhenMatchesOpen != null) tqEntity.addProperty("notify_users_when_matches_open", query.notifyUsersWhenMatchesOpen)
        if (query.notifyUsersWhenTheTournamentEnds != null) tqEntity.addProperty("notify_users_when_the_tournament_ends", query.notifyUsersWhenTheTournamentEnds)
        if (query.sequentialPairings != null) tqEntity.addProperty("sequential_pairings", query.sequentialPairings)
        if (query.signupCap != null) tqEntity.addProperty("signup_cap", query.signupCap)
        if (query.startAt != null) tqEntity.add("start_at", context.serialize(query.startAt))
        if (query.checkInDuration != null) tqEntity.addProperty("check_in_duration", query.checkInDuration)
        if (query.grandFinalsModifier != null) tqEntity.addProperty("grand_finals_modifier", query.grandFinalsModifier.toString().toLowerCase().replace("_", " "))
        tqEntity.add("tie_breaks", context.serialize(query.tieBreaks))

        parent.add("tournament", tqEntity)

        return parent
    }
}
