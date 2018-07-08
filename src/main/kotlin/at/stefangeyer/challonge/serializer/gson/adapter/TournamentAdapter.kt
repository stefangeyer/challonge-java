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

import at.stefangeyer.challonge.model.Tournament
import at.stefangeyer.challonge.model.enum.RankedBy
import at.stefangeyer.challonge.model.enum.TournamentState
import at.stefangeyer.challonge.model.enum.TournamentType
import at.stefangeyer.challonge.model.query.enum.GrandFinalsModifier
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type
import java.time.OffsetDateTime

/**
 * Type adapter for the [Tournament] class.
 * The received json object is being unpacked.
 *
 * @author Stefan Geyer
 * @version 2018-07-06
 */
class TournamentAdapter internal constructor() : JsonDeserializer<Tournament> {

    @Throws(JsonParseException::class)
    override fun deserialize(jsonElement: JsonElement, type: Type, context: JsonDeserializationContext): Tournament {
//        val e = jsonElement.asJsonObject.get("tournament").asJsonObject
        val e = jsonElement.asJsonObject

        val id = e.get("id").asLong
        val url = e.get("url").asString
        val name = e.get("name").asString
        val tournamentType = TournamentType.valueOf(e.get("tournament_type").asString.replace(" ", "_").toUpperCase())
        val subdomain = check(e.get("subdomain"))?.asString
        val description = check(e.get("description"))?.asString
        val openSignup = e.get("open_signup").asBoolean
        val holdThirdPlaceMatch = e.get("hold_third_place_match").asBoolean
        val pointsForMatchWin = e.get("pts_for_match_win").asFloat
        val pointsForMatchTie = e.get("pts_for_match_tie").asFloat
        val pointsForGameWin = e.get("pts_for_game_win").asFloat
        val pointsForGameTie = e.get("pts_for_game_tie").asFloat
        val pointsForBye = e.get("pts_for_bye").asFloat
        val swissRounds = e.get("swiss_rounds").asInt
        val rankedBy = check(e.get("ranked_by"))?.asString?.replace(" ", "_")?.toUpperCase()?.let { RankedBy.valueOf(it) }
        val roundRobinPointsForGameWin = e.get("rr_pts_for_game_win").asFloat
        val roundRobinPointsForGameTie = e.get("rr_pts_for_game_tie").asFloat
        val roundRobinPointsForMatchWin = e.get("rr_pts_for_match_win").asFloat
        val roundRobinPointsForMatchTie = e.get("rr_pts_for_match_tie").asFloat
        val acceptAttachments = e.get("accept_attachments").asBoolean
        val hideForum = e.get("hide_forum").asBoolean
        val showRounds = e.get("show_rounds").asBoolean
        val private = e.get("private").asBoolean
        val notifyUsersWhenMatchesOpen = e.get("notify_users_when_matches_open").asBoolean
        val notifyUsersWhenTheTournamentEnds = e.get("notify_users_when_the_tournament_ends").asBoolean
        val sequentialPairings = e.get("sequential_pairings").asBoolean
        val signupCap = check(e.get("signup_cap"))?.asInt
        val startAt = context.deserialize<OffsetDateTime>(check(e.get("start_at")), OffsetDateTime::class.java)
        val checkInDuration = check(e.get("check_in_duration"))?.asLong
        val allowParticipantMatchReporting = e.get("allow_participant_match_reporting").asBoolean
        val anonymousVoting = e.get("anonymous_voting").asBoolean
        val category = check(e.get("category"))?.asString
        val completedAt = context.deserialize<OffsetDateTime>(e.get("completed_at"), OffsetDateTime::class.java)
        val createdAt = context.deserialize<OffsetDateTime>(e.get("created_at"), OffsetDateTime::class.java)
        val createdByApi = e.get("created_by_api").asBoolean
        val creditCapped = e.get("credit_capped").asBoolean
        val gameId = check(e.get("game_id"))?.asLong
        val groupStagesEnabled = e.get("group_stages_enabled").asBoolean
        val hideSeeds = e.get("hide_seeds").asBoolean
        val maxPredictionsPerUser = e.get("max_predictions_per_user").asInt
        val participantsCount = e.get("participants_count").asInt
        val predictionMethod = e.get("prediction_method").asInt
        val predictionsOpenedAt = context.deserialize<OffsetDateTime>(e.get("predictions_opened_at"), OffsetDateTime::class.java)
        val progressMeter = e.get("progress_meter").asInt
        val quickAdvance = e.get("quick_advance").asBoolean
        val requireScoreAgreement = e.get("require_score_agreement").asBoolean
        val startedAt = context.deserialize<OffsetDateTime>(e.get("started_at"), OffsetDateTime::class.java)
        val startedCheckingInAt = context.deserialize<OffsetDateTime>(e.get("started_checking_in_at"), OffsetDateTime::class.java)
        val state = TournamentState.valueOf(e.get("state").asString.replace(" ", "_").toUpperCase())
        val teams = check(e.get("teams"))?.asBoolean
        val tieBreaks = check(e.get("tie_breaks"))?.asJsonArray?.toList()?.map { je -> je.asString }
        val updatedAt = context.deserialize<OffsetDateTime>(e.get("updated_at"), OffsetDateTime::class.java)
        val descriptionSource = check(e.get("description_source"))?.asString
        val fullChallongeUrl = check(e.get("full_challonge_url"))?.asString
        val liveImageUrl = check(e.get("live_image_url"))?.asString
        val reviewBeforeFinalizing = e.get("review_before_finalizing").asBoolean
        val acceptingPredictions = e.get("accepting_predictions").asBoolean
        val participantsLocked = e.get("participants_locked").asBoolean
        val gameName = check(e.get("game_name"))?.asString
        val participantsSwappable = e.get("participants_swappable").asBoolean
        val teamConvertable = e.get("team_convertable").asBoolean
        val groupStagesWereStarted = e.get("group_stages_were_started").asBoolean
        val lockedAt = context.deserialize<OffsetDateTime>(e.get("locked_at"), OffsetDateTime::class.java)
        val eventId = check(e.get("event_id"))?.asLong
        val publicPredictionsBeforeStartTime = check(e.get("public_predictions_before_start_time"))?.asBoolean
        val grandFinalsModifier = check(e.get("grand_finals_modifier"))?.asString?.replace(" ", "_")?.toUpperCase()?.let { GrandFinalsModifier.valueOf(it) }

        return Tournament(
                id = id, name = name, url = url, tournamentType = tournamentType, subdomain = subdomain, description = description,
                openSignup = openSignup, holdThirdPlaceMatch = holdThirdPlaceMatch, pointsForMatchWin = pointsForMatchWin,
                pointsForMatchTie = pointsForMatchTie, pointsForGameWin = pointsForGameWin, pointsForGameTie = pointsForGameTie,
                pointsForBye = pointsForBye, swissRounds = swissRounds, rankedBy = rankedBy, roundRobinPointsForGameWin = roundRobinPointsForGameWin,
                roundRobinPointsForGameTie = roundRobinPointsForGameTie, roundRobinPointsForMatchWin = roundRobinPointsForMatchWin,
                roundRobinPointsForMatchTie = roundRobinPointsForMatchTie, acceptAttachments = acceptAttachments, hideForum = hideForum, showRounds = showRounds,
                private = private, notifyUsersWhenMatchesOpen = notifyUsersWhenMatchesOpen, notifyUsersWhenTheTournamentEnds = notifyUsersWhenTheTournamentEnds,
                sequentialPairings = sequentialPairings, signupCap = signupCap, startAt = startAt, checkInDuration = checkInDuration,
                allowParticipantMatchReporting = allowParticipantMatchReporting, anonymousVoting = anonymousVoting, category = category,
                completedAt = completedAt, createdAt = createdAt, createdByApi = createdByApi, creditCapped = creditCapped, gameId = gameId,
                groupStagesEnabled = groupStagesEnabled, hideSeeds = hideSeeds, maxPredictionsPerUser = maxPredictionsPerUser,
                participantsCount = participantsCount, predictionMethod = predictionMethod, predictionsOpenedAt = predictionsOpenedAt,
                progressMeter = progressMeter, quickAdvance = quickAdvance, requireScoreAgreement = requireScoreAgreement, startedAt = startedAt,
                startedCheckingInAt = startedCheckingInAt, state = state, teams = teams, tieBreaks = tieBreaks, updatedAt = updatedAt,
                descriptionSource = descriptionSource, fullChallongeUrl = fullChallongeUrl, liveImageUrl = liveImageUrl,
                reviewBeforeFinalizing = reviewBeforeFinalizing, acceptingPredictions = acceptingPredictions,
                participantsLocked = participantsLocked, gameName = gameName, participantsSwappable = participantsSwappable,
                teamConvertable = teamConvertable, groupStagesWereStarted = groupStagesWereStarted, lockedAt = lockedAt, eventId = eventId,
                publicPredictionsBeforeStartTime = publicPredictionsBeforeStartTime, grandFinalsModifier = grandFinalsModifier
        )
    }

    private fun check(element: JsonElement): JsonElement? {
        return if (element.isJsonNull) null
        else element
    }
}