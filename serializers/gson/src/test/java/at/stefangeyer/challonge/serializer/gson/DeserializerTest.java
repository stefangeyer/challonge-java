package at.stefangeyer.challonge.serializer.gson;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.enumeration.MatchState;
import at.stefangeyer.challonge.model.enumeration.RankedBy;
import at.stefangeyer.challonge.model.enumeration.TournamentState;
import at.stefangeyer.challonge.model.enumeration.TournamentType;
import kotlin.jvm.internal.Intrinsics;
import org.junit.Test;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DeserializerTest {

    private final String tournamentString = "{\n" +
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
            "}";

    private final String participantString = "{\n" +
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
            "  }";

    private final String matchString = "{\n" +
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
            "  }";

    private final String attachmentString = "{\n" +
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
            "    }";

    private GsonSerializer serializer = new GsonSerializer();

    @Test
    public void testTournamentDeserialization() {
        Tournament tournament = this.serializer.deserialize(this.tournamentString, Tournament.class);

        OffsetDateTime created = OffsetDateTime.parse("2015-01-19T16:47:30-05:00");
        OffsetDateTime started = OffsetDateTime.parse("2015-01-19T16:57:17-05:00");
        OffsetDateTime updated = OffsetDateTime.parse("2015-01-19T16:57:17-05:00");

        List<String> tieBreaks = List.of("match wins vs tied", "game wins", "points scored");
        assertEquals(true, tournament.getAcceptAttachments());
        assertEquals(true, tournament.getAllowParticipantMatchReporting());
        assertEquals(true, tournament.getAnonymousVoting());
        assertEquals("ABC", tournament.getCategory());
        assertNull(tournament.getCheckInDuration());
        assertNull(tournament.getCompletedAt());
        assertEquals(created, tournament.getCreatedAt());
        assertEquals(false, tournament.getCreatedByApi());
        assertEquals(false, tournament.getCreditCapped());
        assertEquals("test", tournament.getDescription());
        assertEquals(600L, (long) tournament.getGameId());
        assertEquals(false, tournament.getGroupStagesEnabled());
        assertEquals(false, tournament.getHideForum());
        assertEquals(false, tournament.getHideSeeds());
        assertEquals(false, tournament.getHoldThirdPlaceMatch());
        assertEquals(1086875L, (long) tournament.getId());
        assertEquals(1, (int) tournament.getMaxPredictionsPerUser());
        assertEquals("Sample Tournament 1", tournament.getName());
        assertEquals(true, tournament.getNotifyUsersWhenMatchesOpen());
        assertEquals(true, tournament.getNotifyUsersWhenTheTournamentEnds());
        assertEquals(false, tournament.getOpenSignup());
        assertEquals(4, (int) tournament.getParticipantsCount());
        assertEquals(0, (int) tournament.getPredictionMethod());
        assertNull(tournament.getPredictionsOpenedAt());
        assertEquals(false, tournament.getPrivateOnly());
        assertEquals(0, (int) tournament.getProgressMeter());
        assertEquals(1.0F, tournament.getPointsForBye(), 0);
        assertEquals(0.0F, tournament.getPointsForGameTie(), 0);
        assertEquals(0.0F, tournament.getPointsForGameWin(), 0);
        assertEquals(0.5F, tournament.getPointsForMatchTie(), 0);
        assertEquals(1.0F, tournament.getPointsForMatchWin(), 0);
        assertEquals(false, tournament.getQuickAdvance());
        assertEquals(RankedBy.MATCH_WINS, tournament.getRankedBy());
        assertEquals(false, tournament.getRequireScoreAgreement());
        assertEquals(0.0F, tournament.getRoundRobinPointsForGameTie(), 0);
        assertEquals(0.0F, tournament.getRoundRobinPointsForGameWin(), 0);
        assertEquals(0.5F, tournament.getRoundRobinPointsForMatchTie(), 0);
        assertEquals(1.0F, tournament.getRoundRobinPointsForMatchWin(), 0);
        assertEquals(false, tournament.getSequentialPairings());
        assertEquals(true, tournament.getShowRounds());
        assertNull(tournament.getSignupCap());
        assertNull(tournament.getStartAt());
        assertEquals(started, tournament.getStartedAt());
        assertNull(tournament.getStartedCheckingInAt());
        assertEquals(TournamentState.UNDERWAY, tournament.getState());
        assertEquals(0, (int) tournament.getSwissRounds());
        assertEquals(false, tournament.getTeams());
        assertEquals(tieBreaks, tournament.getTieBreaks());
        assertEquals(TournamentType.SINGLE_ELIMINATION, tournament.getTournamentType());
        assertEquals(updated, tournament.getUpdatedAt());
        assertEquals("sample_tournament_1", tournament.getUrl());
        assertEquals("", tournament.getDescriptionSource());
        assertNull(tournament.getSubdomain());
        assertEquals("http://challonge.com/sample_tournament_1", tournament.getFullChallongeUrl());
        assertEquals("http://images.challonge.com/sample_tournament_1.png", tournament.getLiveImageUrl());
        assertNull(tournament.getSignUpUrl());
        assertEquals(true, tournament.getReviewBeforeFinalizing());
        assertEquals(false, tournament.getAcceptingPredictions());
        assertEquals(true, tournament.getParticipantsLocked());
        assertEquals("Table Tennis", tournament.getGameName());
        assertEquals(false, tournament.getParticipantsSwappable());
        assertEquals(false, tournament.getTeamConvertable());
        assertEquals(false, tournament.getGroupStagesWereStarted());
    }

    @Test
    public void testParticipantDeserialization() {
        Object var10000 = this.serializer.deserialize(this.participantString, (Type) Participant.class);
        Intrinsics.checkExpressionValueIsNotNull(var10000, "this.serializer.deserial… Participant::class.java)");
        Participant participant = (Participant) var10000;
        OffsetDateTime created = OffsetDateTime.parse("2015-01-19T16:54:40-05:00");
        OffsetDateTime updated = OffsetDateTime.parse("2015-01-19T16:54:40-05:00");
        assertEquals(true, participant.getActive());
        assertNull(participant.getCheckedInAt());
        assertEquals(created, participant.getCreatedAt());
        assertNull(participant.getFinalRank());
        assertNull(participant.getGroupId());
        assertNull(participant.getIcon());
        assertEquals(16543993L, (long) participant.getId());
        assertNull(participant.getInvitationId());
        assertNull(participant.getInviteEmail());
        assertNull(participant.getMisc());
        assertEquals("Participant #1", participant.getName());
        assertEquals(false, participant.getOnWaitingList());
        assertEquals(1, (int) participant.getSeed());
        assertEquals(1086875L, (long) participant.getTournamentId());
        assertEquals(updated, participant.getUpdatedAt());
        assertNull(participant.getChallongeUsername());
        assertNull(participant.getChallongeEmailAddressVerified());
        assertEquals(true, participant.getRemovable());
        assertEquals(false, participant.getParticipatableOrInvitationAttached());
        assertEquals(true, participant.getConfirmRemove());
        assertEquals(false, participant.getInvitationPending());
        assertEquals("Participant #1", participant.getDisplayNameWithInvitationEmailAddress());
        assertNull(participant.getEmailHash());
        assertNull(participant.getUsername());
        assertNull(participant.getAttachedParticipatablePortraitUrl());
        assertEquals(false, participant.getCanCheckIn());
        assertEquals(false, participant.getCheckedIn());
        assertEquals(false, participant.getReactivatable());
    }

    @Test
    public void testMatchDeserialization() {
        Match match = this.serializer.deserialize(this.matchString, (Type) Match.class);
        OffsetDateTime created = OffsetDateTime.parse("2015-01-19T16:57:17-05:00");
        OffsetDateTime started = OffsetDateTime.parse("2015-01-19T16:57:17-05:00");
        OffsetDateTime updated = OffsetDateTime.parse("2015-01-19T16:57:17-05:00");
        assertNull(match.getAttachmentCount());
        assertEquals(created, match.getCreatedAt());
        assertNull(match.getGroupId());
        assertEquals(false, match.getHasAttachment());
        assertEquals(23575258L, (long) match.getId());
        assertEquals("A", match.getIdentifier());
        assertNull(match.getLocation());
        assertNull(match.getLoserId());
        assertEquals(16543993L, (long) match.getPlayer1Id());
        assertEquals(false, match.getPlayer1IsPrerequisiteMatchLoser());
        assertNull(match.getPlayer1PrerequisiteMatchId());
        assertNull(match.getPlayer1Votes());
        assertNull(match.getPlayer2Votes());
        assertEquals(1, (int) match.getRound());
        assertNull(match.getScheduledTime());
        assertEquals(started, match.getStartedAt());
        assertEquals(MatchState.OPEN, match.getState());
        assertEquals(1086875L, (long) match.getTournamentId());
        assertNull(match.getUnderwayAt());
        assertEquals(updated, match.getUpdatedAt());
        assertNull(match.getWinnerId());
        assertEquals("", match.getPrerequisiteMatchIdsCsv());
        assertEquals("", match.getScoresCsv());
    }

    @Test
    public void testAttachmentDeserialization() {
        Object var10000 = this.serializer.deserialize(this.attachmentString, Attachment.class);
        Attachment attachment = (Attachment) var10000;
        OffsetDateTime created = OffsetDateTime.parse("2018-09-21T06:21:25.884-04:00");
        OffsetDateTime updated = OffsetDateTime.parse("2018-09-21T06:21:25.884-04:00");
        assertEquals(363084L, (long) attachment.getId());
        assertEquals(40637548L, (long) attachment.getMatchId());
        assertEquals(12345L, (long) attachment.getUserId());
        assertNull(attachment.getDescription());
        assertEquals("", attachment.getUrl());
        assertNull(attachment.getOriginalFileName());
        assertEquals(created, attachment.getCreatedAt());
        assertEquals(updated, attachment.getUpdatedAt());
        assertNull(attachment.getAssetFileName());
        assertNull(attachment.getAssetContentType());
        assertNull(attachment.getAssetFileSize());
        assertNull(attachment.getAssetUrl());
    }
}
