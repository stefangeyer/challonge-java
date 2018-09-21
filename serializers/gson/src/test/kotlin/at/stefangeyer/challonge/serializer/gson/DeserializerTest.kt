package at.stefangeyer.challonge.serializer.gson

import at.stefangeyer.challonge.model.Attachment
import at.stefangeyer.challonge.model.Match
import at.stefangeyer.challonge.model.Participant
import at.stefangeyer.challonge.model.Tournament
import at.stefangeyer.challonge.model.enum.MatchState
import at.stefangeyer.challonge.model.enum.RankedBy
import at.stefangeyer.challonge.model.enum.TournamentState
import at.stefangeyer.challonge.model.enum.TournamentType
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.OffsetDateTime

class DeserializerTest {

    private val serializer = GsonSerializer()
    
    private val tournamentString = "{\n" +
            "  \"tournament\": {\n" +
            "    \"accept_attachments\": true,\n" +
            "    \"allow_participant_match_reporting\": true,\n" +
            "    \"anonymous_voting\": true,\n" +
            "    \"category\": \"ABC\",\n" +
            "    \"check_in_duration\": null,\n" +
            "    \"completed_at\": null,\n" +
            "    \"created_at\": \"2015-01-19T16:47:30-05:00\",\n" +
            "    \"created_by_api\": false,\n" +
            "    \"credit_capped\": false,\n" +
            "    \"description\": \"test\",\n" +
            "    \"game_id\": 600,\n" +
            "    \"group_stages_enabled\": false,\n" +
            "    \"hide_forum\": false,\n" +
            "    \"hide_seeds\": false,\n" +
            "    \"hold_third_place_match\": false,\n" +
            "    \"id\": 1086875,\n" +
            "    \"max_predictions_per_user\": 1,\n" +
            "    \"name\": \"Sample Tournament 1\",\n" +
            "    \"notify_users_when_matches_open\": true,\n" +
            "    \"notify_users_when_the_tournament_ends\": true,\n" +
            "    \"open_signup\": false,\n" +
            "    \"participants_count\": 4,\n" +
            "    \"prediction_method\": 0,\n" +
            "    \"predictions_opened_at\": null,\n" +
            "    \"private\": false,\n" +
            "    \"progress_meter\": 0,\n" +
            "    \"pts_for_bye\": \"1.0\",\n" +
            "    \"pts_for_game_tie\": \"0.0\",\n" +
            "    \"pts_for_game_win\": \"0.0\",\n" +
            "    \"pts_for_match_tie\": \"0.5\",\n" +
            "    \"pts_for_match_win\": \"1.0\",\n" +
            "    \"quick_advance\": false,\n" +
            "    \"ranked_by\": \"match wins\",\n" +
            "    \"require_score_agreement\": false,\n" +
            "    \"rr_pts_for_game_tie\": \"0.0\",\n" +
            "    \"rr_pts_for_game_win\": \"0.0\",\n" +
            "    \"rr_pts_for_match_tie\": \"0.5\",\n" +
            "    \"rr_pts_for_match_win\": \"1.0\",\n" +
            "    \"sequential_pairings\": false,\n" +
            "    \"show_rounds\": true,\n" +
            "    \"signup_cap\": null,\n" +
            "    \"start_at\": null,\n" +
            "    \"started_at\": \"2015-01-19T16:57:17-05:00\",\n" +
            "    \"started_checking_in_at\": null,\n" +
            "    \"state\": \"underway\",\n" +
            "    \"swiss_rounds\": 0,\n" +
            "    \"teams\": false,\n" +
            "    \"tie_breaks\": [\n" +
            "      \"match wins vs tied\",\n" +
            "      \"game wins\",\n" +
            "      \"points scored\"\n" +
            "    ],\n" +
            "    \"tournament_type\": \"single elimination\",\n" +
            "    \"updated_at\": \"2015-01-19T16:57:17-05:00\",\n" +
            "    \"url\": \"sample_tournament_1\",\n" +
            "    \"description_source\": \"\",\n" +
            "    \"subdomain\": null,\n" +
            "    \"full_challonge_url\": \"http://challonge.com/sample_tournament_1\",\n" +
            "    \"live_image_url\": \"http://images.challonge.com/sample_tournament_1.png\",\n" +
            "    \"sign_up_url\": null,\n" +
            "    \"review_before_finalizing\": true,\n" +
            "    \"accepting_predictions\": false,\n" +
            "    \"participants_locked\": true,\n" +
            "    \"game_name\": \"Table Tennis\",\n" +
            "    \"participants_swappable\": false,\n" +
            "    \"team_convertable\": false,\n" +
            "    \"group_stages_were_started\": false\n" +
            "  }\n" +
            "}"

    private val participantString = "{\n" +
            "    \"participant\": {\n" +
            "      \"active\": true,\n" +
            "      \"checked_in_at\": null,\n" +
            "      \"created_at\": \"2015-01-19T16:54:40-05:00\",\n" +
            "      \"final_rank\": null,\n" +
            "      \"group_id\": null,\n" +
            "      \"icon\": null,\n" +
            "      \"id\": 16543993,\n" +
            "      \"invitation_id\": null,\n" +
            "      \"invite_email\": null,\n" +
            "      \"misc\": null,\n" +
            "      \"name\": \"Participant #1\",\n" +
            "      \"on_waiting_list\": false,\n" +
            "      \"seed\": 1,\n" +
            "      \"tournament_id\": 1086875,\n" +
            "      \"updated_at\": \"2015-01-19T16:54:40-05:00\",\n" +
            "      \"challonge_username\": null,\n" +
            "      \"challonge_email_address_verified\": null,\n" +
            "      \"removable\": true,\n" +
            "      \"participatable_or_invitation_attached\": false,\n" +
            "      \"confirm_remove\": true,\n" +
            "      \"invitation_pending\": false,\n" +
            "      \"display_name_with_invitation_email_address\": \"Participant #1\",\n" +
            "      \"email_hash\": null,\n" +
            "      \"username\": null,\n" +
            "      \"attached_participatable_portrait_url\": null,\n" +
            "      \"can_check_in\": false,\n" +
            "      \"checked_in\": false,\n" +
            "      \"reactivatable\": false\n" +
            "    }\n" +
            "  }"

    private val matchString = "{\n" +
            "    \"match\": {\n" +
            "      \"attachment_count\": null,\n" +
            "      \"created_at\": \"2015-01-19T16:57:17-05:00\",\n" +
            "      \"group_id\": null,\n" +
            "      \"has_attachment\": false,\n" +
            "      \"id\": 23575258,\n" +
            "      \"identifier\": \"A\",\n" +
            "      \"location\": null,\n" +
            "      \"loser_id\": null,\n" +
            "      \"player1_id\": 16543993,\n" +
            "      \"player1_is_prereq_match_loser\": false,\n" +
            "      \"player1_prereq_match_id\": null,\n" +
            "      \"player1_votes\": null,\n" +
            "      \"player2_id\": 16543997,\n" +
            "      \"player2_is_prereq_match_loser\": false,\n" +
            "      \"player2_prereq_match_id\": null,\n" +
            "      \"player2_votes\": null,\n" +
            "      \"round\": 1,\n" +
            "      \"scheduled_time\": null,\n" +
            "      \"started_at\": \"2015-01-19T16:57:17-05:00\",\n" +
            "      \"state\": \"open\",\n" +
            "      \"tournament_id\": 1086875,\n" +
            "      \"underway_at\": null,\n" +
            "      \"updated_at\": \"2015-01-19T16:57:17-05:00\",\n" +
            "      \"winner_id\": null,\n" +
            "      \"prerequisite_match_ids_csv\": \"\",\n" +
            "      \"scores_csv\": \"\"\n" +
            "    }\n" +
            "  }"

    private val attachmentString = "{\n" +
            "        \"match_attachment\": {\n" +
            "            \"id\": 363084,\n" +
            "            \"match_id\": 40637548,\n" +
            "            \"user_id\": 12345,\n" +
            "            \"description\": null,\n" +
            "            \"url\": \"\",\n" +
            "            \"original_file_name\": null,\n" +
            "            \"created_at\": \"2018-09-21T06:21:25.884-04:00\",\n" +
            "            \"updated_at\": \"2018-09-21T06:21:25.884-04:00\",\n" +
            "            \"asset_file_name\": null,\n" +
            "            \"asset_content_type\": null,\n" +
            "            \"asset_file_size\": null,\n" +
            "            \"asset_url\": null\n" +
            "        }\n" +
            "    }"

    @Test
    fun testTournamentDeserialization() {
        val tournament: Tournament = this.serializer.deserialize(this.tournamentString, Tournament::class.java)

        val created = OffsetDateTime.parse("2015-01-19T16:47:30-05:00")
        val started = OffsetDateTime.parse("2015-01-19T16:57:17-05:00")
        val updated = OffsetDateTime.parse("2015-01-19T16:57:17-05:00")
        val tieBreaks = listOf("match wins vs tied", "game wins", "points scored")

        assertEquals(true, tournament.acceptAttachments)
        assertEquals(true, tournament.allowParticipantMatchReporting)
        assertEquals(true, tournament.anonymousVoting)
        assertEquals("ABC", tournament.category)
        assertEquals(null, tournament.checkInDuration)
        assertEquals(null, tournament.completedAt)
        assertEquals(created, tournament.createdAt)
        assertEquals(false, tournament.createdByApi)
        assertEquals(false, tournament.creditCapped)
        assertEquals("test", tournament.description)
        assertEquals(600L, tournament.gameId)
        assertEquals(false, tournament.groupStagesEnabled)
        assertEquals(false, tournament.hideForum)
        assertEquals(false, tournament.hideSeeds)
        assertEquals(false, tournament.holdThirdPlaceMatch)
        assertEquals(1086875, tournament.id)
        assertEquals(1, tournament.maxPredictionsPerUser)
        assertEquals("Sample Tournament 1", tournament.name)
        assertEquals(true, tournament.notifyUsersWhenMatchesOpen)
        assertEquals(true, tournament.notifyUsersWhenTheTournamentEnds)
        assertEquals(false, tournament.openSignup)
        assertEquals(4, tournament.participantsCount)
        assertEquals(0, tournament.predictionMethod)
        assertEquals(null, tournament.predictionsOpenedAt)
        assertEquals(false, tournament.private)
        assertEquals(0, tournament.progressMeter)
        assertEquals(1.0F, tournament.pointsForBye)
        assertEquals(0.0F, tournament.pointsForGameTie)
        assertEquals(0.0F, tournament.pointsForGameWin)
        assertEquals(0.5F, tournament.pointsForMatchTie)
        assertEquals(1.0F, tournament.pointsForMatchWin)
        assertEquals(false, tournament.quickAdvance)
        assertEquals(RankedBy.MATCH_WINS, tournament.rankedBy)
        assertEquals(false, tournament.requireScoreAgreement)
        assertEquals(0.0F, tournament.roundRobinPointsForGameTie)
        assertEquals(0.0F, tournament.roundRobinPointsForGameWin)
        assertEquals(0.5F, tournament.roundRobinPointsForMatchTie)
        assertEquals(1.0F, tournament.roundRobinPointsForMatchWin)
        assertEquals(false, tournament.sequentialPairings)
        assertEquals(true, tournament.showRounds)
        assertEquals(null, tournament.signupCap)
        assertEquals(null, tournament.startAt)
        assertEquals(started, tournament.startedAt)
        assertEquals(null, tournament.startedCheckingInAt)
        assertEquals(TournamentState.UNDERWAY, tournament.state)
        assertEquals(0, tournament.swissRounds)
        assertEquals(false, tournament.teams)
        assertEquals(tieBreaks, tournament.tieBreaks)
        assertEquals(TournamentType.SINGLE_ELIMINATION, tournament.tournamentType)
        assertEquals(updated, tournament.updatedAt)
        assertEquals("sample_tournament_1", tournament.url)
        assertEquals("", tournament.descriptionSource)
        assertEquals(null, tournament.subdomain)
        assertEquals("http://challonge.com/sample_tournament_1", tournament.fullChallongeUrl)
        assertEquals( "http://images.challonge.com/sample_tournament_1.png", tournament.liveImageUrl)
        assertEquals(null, tournament.signUpUrl)
        assertEquals(true, tournament.reviewBeforeFinalizing)
        assertEquals(false, tournament.acceptingPredictions)
        assertEquals(true, tournament.participantsLocked)
        assertEquals("Table Tennis", tournament.gameName)
        assertEquals(false, tournament.participantsSwappable)
        assertEquals(false, tournament.teamConvertable)
        assertEquals(false, tournament.groupStagesWereStarted)
    }

    @Test
    fun testParticipantDeserialization() {
        val participant: Participant = this.serializer.deserialize(this.participantString, Participant::class.java)

        val created = OffsetDateTime.parse("2015-01-19T16:54:40-05:00")
        val updated = OffsetDateTime.parse("2015-01-19T16:54:40-05:00")

        assertEquals(true, participant.active)
        assertEquals(null, participant.checkedInAt)
        assertEquals(created, participant.createdAt)
        assertEquals(null, participant.finalRank)
        assertEquals(null, participant.groupId)
        assertEquals(null, participant.icon)
        assertEquals(16543993L, participant.id)
        assertEquals(null, participant.invitationId)
        assertEquals(null, participant.inviteEmail)
        assertEquals(null, participant.misc)
        assertEquals("Participant #1", participant.name)
        assertEquals(false, participant.onWaitingList)
        assertEquals(1, participant.seed)
        assertEquals(1086875L, participant.tournamentId)
        assertEquals(updated, participant.updatedAt)
        assertEquals(null, participant.challongeUsername)
        assertEquals(null, participant.challongeEmailAddressVerified)
        assertEquals(true, participant.removable)
        assertEquals(false, participant.participatableOrInvitationAttached)
        assertEquals(true, participant.confirmRemove)
        assertEquals(false, participant.invitationPending)
        assertEquals("Participant #1", participant.displayNameWithInvitationEmailAddress)
        assertEquals(null, participant.emailHash)
        assertEquals(null, participant.username)
        assertEquals(null, participant.attachedParticipatablePortraitUrl)
        assertEquals(false, participant.canCheckIn)
        assertEquals(false, participant.checkedIn)
        assertEquals(false, participant.reactivatable)
    }

    @Test
    fun testMatchDeserialization() {
        val match: Match = this.serializer.deserialize(this.matchString, Match::class.java)

        val created = OffsetDateTime.parse("2015-01-19T16:57:17-05:00")
        val started = OffsetDateTime.parse("2015-01-19T16:57:17-05:00")
        val updated = OffsetDateTime.parse("2015-01-19T16:57:17-05:00")

        assertEquals(null, match.attachmentCount)
        assertEquals(created, match.createdAt)
        assertEquals(null, match.groupId)
        assertEquals(false, match.hasAttachment)
        assertEquals(23575258L, match.id)
        assertEquals("A", match.identifier)
        assertEquals(null, match.location)
        assertEquals(null, match.loserId)
        assertEquals(16543993L, match.player1Id)
        assertEquals(false, match.player1IsPrerequisiteMatchLoser)
        assertEquals(null, match.player1PrerequisiteMatchId)
        assertEquals(null, match.player1Votes)
        assertEquals(null, match.player2Votes)
        assertEquals(1, match.round)
        assertEquals(null, match.scheduledTime)
        assertEquals(started, match.startedAt)
        assertEquals(MatchState.OPEN, match.state)
        assertEquals(1086875L, match.tournamentId)
        assertEquals(null, match.underwayAt)
        assertEquals(updated, match.updatedAt)
        assertEquals(null, match.winnerId)
        assertEquals("", match.prerequisiteMatchIdsCsv)
        assertEquals("", match.scoresCsv)
    }

    @Test
    fun testAttachmentDeserialization() {
        val attachment: Attachment = this.serializer.deserialize(this.attachmentString, Attachment::class.java)

        val created = OffsetDateTime.parse("2018-09-21T06:21:25.884-04:00")
        val updated = OffsetDateTime.parse("2018-09-21T06:21:25.884-04:00")

        assertEquals(363084L, attachment.id)
        assertEquals(40637548L, attachment.matchId)
        assertEquals(12345L, attachment.userId)
        assertEquals(null, attachment.description)
        assertEquals("", attachment.url)
        assertEquals(null, attachment.originalFileName)
        assertEquals(created, attachment.createdAt)
        assertEquals(updated, attachment.updatedAt)
        assertEquals(null, attachment.assetFileName)
        assertEquals(null, attachment.assetContentType)
        assertEquals(null, attachment.assetFileSize)
        assertEquals(null, attachment.assetUrl)
    }
}