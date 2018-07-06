package at.stefangeyer.challonge.serializer.gson.adapter

import at.stefangeyer.challonge.model.Tournament
import at.stefangeyer.challonge.model.enumeration.RankedBy
import at.stefangeyer.challonge.model.enumeration.TournamentState
import at.stefangeyer.challonge.model.enumeration.TournamentType
import at.stefangeyer.challonge.model.enumeration.query.GrandFinalsModifier
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.OffsetDateTime

class TournamentTypeAdapter : TypeAdapter<Tournament>() {
    override fun write(writer: JsonWriter, value: Tournament) {

    }

    override fun read(reader: JsonReader): Tournament {
        val tournament = Tournament()

        reader.beginObject()

        while (reader.hasNext()) {
            when (reader.nextName()) {
                "id" -> tournament.id = reader.nextLong()
                "url" -> tournament.url = reader.nextString()
                "tournament_type" -> tournament.tournamentType =
                        TournamentType.valueOf(reader.nextString().replace(" ", "_").toUpperCase())
                "subdomain" -> tournament.subdomain = reader.nextString()
                "description" -> tournament.description = reader.nextString()
                "open_signup" -> tournament.openSignup = reader.nextBoolean()
                "hold_third_place_match" -> tournament.holdThirdPlaceMatch = reader.nextBoolean()
                "pts_for_match_win" -> tournament.pointsForMatchWin = reader.nextDouble().toFloat()
                "pts_for_match_tie" -> tournament.pointsForMatchTie = reader.nextDouble().toFloat()
                "pts_for_game_win" -> tournament.pointsForGameWin = reader.nextDouble().toFloat()
                "pts_for_game_tie" -> tournament.pointsForGameTie = reader.nextDouble().toFloat()
                "pts_for_bye" -> tournament.pointsForBye = reader.nextDouble().toFloat()
                "swiss_rounds" -> tournament.swissRounds = reader.nextInt()
                "ranked_by" -> tournament.rankedBy =
                        RankedBy.valueOf(reader.nextString().replace(" ", "_").toUpperCase())
                "rr_pts_for_game_win" -> tournament.roundRobinPointsForGameWin = reader.nextDouble().toFloat()
                "rr_pts_for_game_tie" -> tournament.roundRobinPointsForGameTie = reader.nextDouble().toFloat()
                "rr_pts_for_match_win" -> tournament.roundRobinPointsForMatchWin = reader.nextDouble().toFloat()
                "rr_pts_for_match_tie" -> tournament.roundRobinPointsForMatchTie = reader.nextDouble().toFloat()
                "accept_attachments" -> tournament.acceptAttachments = reader.nextBoolean()
                "hide_forum" -> tournament.hideForum = reader.nextBoolean()
                "show_rounds" -> tournament.showRounds = reader.nextBoolean()
                "private" -> tournament.private = reader.nextBoolean()
                "notify_users_when_matches_open" -> tournament.notifyUsersWhenMatchesOpen = reader.nextBoolean()
                "notify_users_when_the_tournament_ends" -> tournament.notifyUsersWhenTheTournamentEnds = reader.nextBoolean()
                "sequential_pairings" -> tournament.sequentialPairings = reader.nextBoolean()
                "signup_cap" -> tournament.signupCap = reader.nextInt()
                "start_at" -> tournament.startAt = OffsetDateTime.parse(reader.nextString())
                "check_in_duration" -> tournament.checkInDuration = reader.nextLong()
                "allow_participant_match_reporting" -> tournament.allowParticipantMatchReporting = reader.nextBoolean()
                "anonymous_voting" -> tournament.anonymousVoting = reader.nextBoolean()
                "category" -> tournament.category = reader.nextString()
                "completed_at" -> tournament.completedAt = OffsetDateTime.parse(reader.nextString())
                "created_at" -> tournament.createdAt = OffsetDateTime.parse(reader.nextString())
                "created_by_api" -> tournament.createdByApi = reader.nextBoolean()
                "credit_capped" -> tournament.creditCapped = reader.nextBoolean()
                "game_id" -> tournament.gameId = reader.nextLong()
                "group_stages_enabled" -> tournament.groupStagesEnabled = reader.nextBoolean()
                "hide_seeds" -> tournament.hideSeeds = reader.nextBoolean()
                "max_predictions_per_user" -> tournament.maxPredictionsPerUser = reader.nextInt()
                "participants_count" -> tournament.participantsCount = reader.nextInt()
                "prediction_method" -> tournament.predictionMethod = reader.nextInt()
                "predictions_opened_at" -> tournament.predictionsOpenedAt = OffsetDateTime.parse(reader.nextString())
                "progress_meter" -> tournament.progressMeter = reader.nextInt()
                "quick_advance" -> tournament.quickAdvance = reader.nextBoolean()
                "require_score_agreement" -> tournament.requireScoreAgreement = reader.nextBoolean()
                "started_at" -> tournament.startedAt = OffsetDateTime.parse(reader.nextString())
                "started_checking_in_at" -> tournament.startedCheckingInAt = OffsetDateTime.parse(reader.nextString())
                "state" -> tournament.state = TournamentState.valueOf(reader.nextString().replace(" ", "_").toUpperCase())
                "teams" -> tournament.teams = reader.nextBoolean()
                "tie_breaks" -> {
                    val tieBreaks = mutableListOf<String>()
                    reader.beginArray()
                    while (reader.hasNext()) {
                        tieBreaks.add(reader.nextString())
                    }
                    reader.endArray()
                    tournament.tieBreaks = tieBreaks
                }
                "updated_at" -> tournament.updatedAt = OffsetDateTime.parse(reader.nextString())
                "description_source" -> tournament.descriptionSource = reader.nextString()
                "full_challonge_url" -> tournament.fullChallongeUrl = reader.nextString()
                "live_image_url" -> tournament.liveImageUrl = reader.nextString()
                "sign_up_url" -> tournament.signUpUrl = reader.nextString()
                "review_before_finalizing" -> tournament.reviewBeforeFinalizing = reader.nextBoolean()
                "accepting_predictions" -> tournament.acceptingPredictions = reader.nextBoolean()
                "participants_locked" -> tournament.participantsLocked = reader.nextBoolean()
                "game_name" -> tournament.gameName = reader.nextString()
                "participants_swappable" -> tournament.participantsSwappable = reader.nextBoolean()
                "team_convertable" -> tournament.teamConvertable = reader.nextBoolean()
                "group_stages_were_started" -> tournament.groupStagesWereStarted = reader.nextBoolean()
                "locked_at" -> tournament.lockedAt = OffsetDateTime.parse(reader.nextString())
                "event_id" -> tournament.eventId = reader.nextLong()
                "public_predictions_before_start_time" -> tournament.publicPredictionsBeforeStartTime = reader.nextBoolean()
                "grand_finals_modifier" -> tournament.grandFinalsModifier =
                        GrandFinalsModifier.valueOf(reader.nextString().replace(" ", "_").toUpperCase())
            }
        }

        reader.endObject()

        return tournament
    }
}