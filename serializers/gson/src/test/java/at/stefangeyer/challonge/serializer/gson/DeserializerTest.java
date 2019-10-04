package at.stefangeyer.challonge.serializer.gson;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.enumeration.*;
import com.google.gson.JsonParseException;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static at.stefangeyer.challonge.model.enumeration.TieBreak.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class DeserializerTest {
    private final Tournament tournament = Tournament.builder().
            acceptAttachments(true).allowParticipantMatchReporting(true).anonymousVoting(true).category("ABC").
            checkInDuration(null).completedAt(null).createdAt(OffsetDateTime.parse("2015-01-19T16:47:30-05:00")).
            createdByApi(false).creditCapped(false).description("test").gameId(600L).groupStagesEnabled(false).
            hideForum(false).hideSeeds(false).holdThirdPlaceMatch(false).id(1086875L).maxPredictionsPerUser(1).
            name("Sample Tournament 1").notifyUsersWhenMatchesOpen(true).notifyUsersWhenTheTournamentEnds(true).
            openSignup(false).participantsCount(4).predictionMethod(0).predictionsOpenedAt(null).privateOnly(false).
            progressMeter(0).pointsForBye(1.0F).pointsForGameTie(0.0F).pointsForGameWin(0.0F).pointsForMatchTie(0.5F).
            pointsForMatchWin(1.0F).quickAdvance(false).rankedBy(RankedBy.MATCH_WINS).requireScoreAgreement(false).
            roundRobinPointsForGameTie(0.0F).roundRobinPointsForGameWin(0.0F).roundRobinPointsForMatchTie(0.5F).
            roundRobinPointsForMatchWin(1.0F).sequentialPairings(false).showRounds(true).signupCap(null).startAt(null).
            startedAt(OffsetDateTime.parse("2015-01-19T16:57:17-05:00")).startedCheckingInAt(null).
            ranked(true).predictTheLosersBracket(true).roundRobinIterations(3).registrationFee(2.0F).registrationType("free").
            state(TournamentState.UNDERWAY).swissRounds(0).teams(false).descriptionSource("").subdomain(null).
            tieBreaks(Arrays.asList(MATCH_WINS_VS_TIED, TieBreak.GAME_WINS, TieBreak.POINTS_SCORED)).url("sample_tournament_1").
            tournamentType(TournamentType.SINGLE_ELIMINATION).updatedAt(OffsetDateTime.parse("2015-01-19T16:57:17-05:00")).
            fullChallongeUrl("http://challonge.com/sample_tournament_1").signUpUrl(null).reviewBeforeFinalizing(true).
            liveImageUrl("http://images.challonge.com/sample_tournament_1.png").participantsSwappable(false).
            acceptingPredictions(false).participantsLocked(true).gameName("Table Tennis").teamConvertable(false).
            groupStagesWereStarted(false).participants(Arrays.asList(
            Participant.builder().id(1L).tournamentId(12345L).name("P1").misc("ABC").seed(3)
                    .active(true).checkedInAt(null).onWaitingList(false).removable(true)
                    .displayName("display name").checkInOpen(true).hasIrrelevantSeed(true)
                    .participatableOrInvitationAttached(false).confirmRemove(true).invitationPending(false)
                    .canCheckIn(false).checkedIn(false).reactivatable(false).build(),
            Participant.builder().id(2L).tournamentId(12345L).name("P2").misc("DEF").seed(4)
                    .active(true).checkedInAt(null).onWaitingList(false).removable(true)
                    .displayName("display name").checkInOpen(true).hasIrrelevantSeed(true)
                    .participatableOrInvitationAttached(false).confirmRemove(true).invitationPending(false)
                    .canCheckIn(false).checkedIn(false).reactivatable(false).build())).matches(Collections.singletonList(
            Match.builder().id(1L).tournamentId(12345L).hasAttachment(false).player1IsPrerequisiteMatchLoser(false).
                    player2IsPrerequisiteMatchLoser(false).round(2).build())).
            matches(Collections.singletonList(Match.builder().id(1L).tournamentId(12345L).hasAttachment(false).player1IsPrerequisiteMatchLoser(false).
                    completedAt(OffsetDateTime.parse("2015-01-19T16:57:17-05:00")).optional(true).forfeited(true).
                    player2IsPrerequisiteMatchLoser(false).round(2).state(MatchState.OPEN).build())).
            build();

    private final Participant participant = Participant.builder().active(true).checkedInAt(null).
            createdAt(OffsetDateTime.parse("2015-01-19T16:54:40-05:00")).finalRank(null).groupId(null).
            icon(null).id(16543993L).invitationId(null).inviteEmail(null).misc(null).name("Participant #1").
            onWaitingList(false).seed(1).tournamentId(1086875L).
            displayName("display name").checkInOpen(true).hasIrrelevantSeed(true).
            updatedAt(OffsetDateTime.parse("2015-01-19T16:54:40-05:00")).challongeUsername(null).
            challongeEmailAddressVerified(null).removable(true).participatableOrInvitationAttached(false).
            confirmRemove(true).invitationPending(false).displayNameWithInvitationEmailAddress("Participant #1").
            emailHash(null).username(null).attachedParticipatablePortraitUrl(null).canCheckIn(false).
            checkedIn(false).reactivatable(false).build();

    private final Match match = Match.builder().attachmentCount(null).
            createdAt(OffsetDateTime.parse("2015-01-19T16:57:17-05:00")).groupId(null).hasAttachment(false).
            id(23575258L).identifier("A").location(null).loserId(null).player1Id(16543993L).
            player1IsPrerequisiteMatchLoser(false).player1PrerequisiteMatchId(null).player1Votes(null).
            player2Id(16543997L).player2IsPrerequisiteMatchLoser(false).player2PrerequisiteMatchId(null).
            player2Votes(null).round(1).scheduledTime(null).startedAt(OffsetDateTime.parse("2015-01-19T16:57:17-05:00")).
            state(MatchState.OPEN).tournamentId(1086875L).underwayAt(null).
            completedAt(OffsetDateTime.parse("2015-01-19T16:57:17-05:00")).optional(true).forfeited(true).
            updatedAt(OffsetDateTime.parse("2015-01-19T16:57:17-05:00")).winnerId(null).prerequisiteMatchIdsCsv("").
            scoresCsv("").build();

    private final Attachment attachment = Attachment.builder().
            id(363084L).matchId(40637548L).userId(12345L).description(null).url("").originalFileName(null).
            createdAt(OffsetDateTime.parse("2018-09-21T06:21:25.884-04:00")).assetFileSize(null)
            .assetUrl("https://www.google.com").updatedAt(OffsetDateTime.parse("2018-09-21T06:21:25.884-04:00"))
            .assetFileName(null).assetContentType(null).build();

    private GsonSerializer serializer = new GsonSerializer();

    @Test
    public void testTournamentDeserialization() {
        String tournamentString = "{\"accept_attachments\":true," +
                "\"allow_participant_match_reporting\":true,\"anonymous_voting\":true,\"category\":\"ABC\"," +
                "\"ranked\":true,\"predict_the_losers_bracket\":true,\"rr_iterations\":3," +
                "\"registration_fee\":2.0,\"registration_type\":\"free\"," +
                "\"check_in_duration\":null,\"completed_at\":null,\"created_at\":\"2015-01-19T16:47:30-05:00\"," +
                "\"created_by_api\":false,\"credit_capped\":false,\"description\":\"test\",\"game_id\":600," +
                "\"group_stages_enabled\":false,\"hide_forum\":false,\"hide_seeds\":false,\"hold_third_place_match\":false," +
                "\"id\":1086875,\"max_predictions_per_user\":1,\"name\":\"Sample Tournament 1\"," +
                "\"notify_users_when_matches_open\":true,\"notify_users_when_the_tournament_ends\":true," +
                "\"open_signup\":false,\"participants_count\":4,\"prediction_method\":0,\"predictions_opened_at\":null," +
                "\"private\":false,\"progress_meter\":0,\"pts_for_bye\":\"1.0\",\"pts_for_game_tie\":\"0.0\"," +
                "\"pts_for_game_win\":\"0.0\",\"pts_for_match_tie\":\"0.5\",\"pts_for_match_win\":\"1.0\"," +
                "\"quick_advance\":false,\"ranked_by\":\"match wins\",\"require_score_agreement\":false," +
                "\"rr_pts_for_game_tie\":\"0.0\",\"rr_pts_for_game_win\":\"0.0\",\"rr_pts_for_match_tie\":\"0.5\"," +
                "\"rr_pts_for_match_win\":\"1.0\",\"sequential_pairings\":false,\"show_rounds\":true,\"signup_cap\":null," +
                "\"start_at\":null,\"started_at\":\"2015-01-19T16:57:17-05:00\",\"started_checking_in_at\":null," +
                "\"state\":\"underway\",\"swiss_rounds\":0,\"teams\":false,\"tie_breaks\":[\"match wins vs tied\"," +
                "\"game wins\",\"points scored\"],\"tournament_type\":\"single elimination\"," +
                "\"updated_at\":\"2015-01-19T16:57:17-05:00\",\"url\":\"sample_tournament_1\",\"description_source\":\"\"," +
                "\"subdomain\":null,\"full_challonge_url\":\"http://challonge.com/sample_tournament_1\"," +
                "\"live_image_url\":\"http://images.challonge.com/sample_tournament_1.png\",\"sign_up_url\":null," +
                "\"review_before_finalizing\":true,\"accepting_predictions\":false,\"participants_locked\":true," +
                "\"game_name\":\"Table Tennis\",\"participants_swappable\":false,\"team_convertable\":false," +
                "\"group_stages_were_started\":false,\"participants\":[{\"participant\":{\"id\":1,\"tournament_id\":12345," +
                "\"name\":\"P1\",\"seed\":3,\"misc\":\"ABC\",\"active\":true,\"on_waiting_list\":false,\"removable\":true," +
                "\"display_name\": \"display name\", \"check_in_open\": true, \"has_irrelevant_seed\": true," +
                "\"participatable_or_invitation_attached\":false,\"confirm_remove\":true,\"invitation_pending\":false," +
                "\"can_check_in\":false,\"checked_in\":false,\"reactivatable\":false}},{\"participant\":{\"id\":2," +
                "\"tournament_id\":12345,\"name\":\"P2\",\"seed\":4,\"misc\":\"DEF\",\"active\":true,\"active\":true," +
                "\"display_name\": \"display name\", \"check_in_open\": true, \"has_irrelevant_seed\": true," +
                "\"on_waiting_list\":false,\"removable\":true,\"participatable_or_invitation_attached\":false," +
                "\"confirm_remove\":true,\"invitation_pending\":false,\"can_check_in\":false,\"checked_in\":false," +
                "\"reactivatable\":false}}],\"matches\":[{\"match\":{\"id\":1,\"tournament_id\":12345," +
                "\"has_attachment\":false,\"player1_is_prereq_match_loser\":false," +
                "\"completed_at\":\"2015-01-19T16:57:17-05:00\", \"optional\": true, \"forfeited\": true," +
                "\"player2_is_prereq_match_loser\":false,\"round\":2,\"state\":\"open\"}}]}";

        Tournament tournament = this.serializer.deserialize(tournamentString, Tournament.class);

        OffsetDateTime created = OffsetDateTime.parse("2015-01-19T16:47:30-05:00");
        OffsetDateTime started = OffsetDateTime.parse("2015-01-19T16:57:17-05:00");
        OffsetDateTime updated = OffsetDateTime.parse("2015-01-19T16:57:17-05:00");

        List<TieBreak> tieBreaks = Arrays.asList(MATCH_WINS_VS_TIED, GAME_WINS, POINTS_SCORED);
        assertEquals(true, tournament.getRanked());
        assertEquals(true, tournament.getPredictTheLosersBracket());
        assertEquals(3L, (long) tournament.getRoundRobinIterations());
        assertEquals(2.0F, tournament.getRegistrationFee(), 0);
        assertEquals("free", tournament.getRegistrationType());
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

        assertEquals(this.tournament, tournament);
    }

    @Test
    public void testParticipantDeserialization() {
        String participantString = "{\"participant\":{\"active\":true,\"checked_in_at\":null," +
                "\"created_at\":\"2015-01-19T16:54:40-05:00\",\"final_rank\":null,\"group_id\":null,\"icon\":null," +
                "\"display_name\": \"display name\", \"check_in_open\": true, \"has_irrelevant_seed\": true," +
                "\"id\":16543993,\"invitation_id\":null,\"invite_email\":null,\"misc\":null,\"name\":\"Participant #1\"," +
                "\"on_waiting_list\":false,\"seed\":1,\"tournament_id\":1086875,\"updated_at\":\"2015-01-19T16:54:40-05:00\"," +
                "\"challonge_username\":null,\"challonge_email_address_verified\":null,\"removable\":true," +
                "\"participatable_or_invitation_attached\":false,\"confirm_remove\":true,\"invitation_pending\":false," +
                "\"display_name_with_invitation_email_address\":\"Participant #1\",\"email_hash\":null,\"username\":null," +
                "\"attached_participatable_portrait_url\":null,\"can_check_in\":false,\"checked_in\":false," +
                "\"reactivatable\":false}}";

        Participant participant = this.serializer.deserialize(participantString, Participant.class);

        OffsetDateTime created = OffsetDateTime.parse("2015-01-19T16:54:40-05:00");
        OffsetDateTime updated = OffsetDateTime.parse("2015-01-19T16:54:40-05:00");

        assertEquals("display name", participant.getDisplayName());
        assertEquals(true, participant.getCheckInOpen());
        assertEquals(true, participant.getHasIrrelevantSeed());
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

        assertEquals(this.participant, participant);
    }

    @Test
    public void testMatchDeserialization() {
        String matchString = "{\"match\":{\"attachment_count\":null,\"created_at\":\"2015-01-19T16:57:17-05:00\"," +
                "\"group_id\":null,\"has_attachment\":false,\"id\":23575258,\"identifier\":\"A\",\"location\":null," +
                "\"loser_id\":null,\"player1_id\":16543993,\"player1_is_prereq_match_loser\":false," +
                "\"player1_prereq_match_id\":null,\"player1_votes\":null,\"player2_id\":16543997," +
                "\"player2_is_prereq_match_loser\":false,\"player2_prereq_match_id\":null,\"player2_votes\":null," +
                "\"round\":1,\"scheduled_time\":null,\"started_at\":\"2015-01-19T16:57:17-05:00\",\"state\":\"open\"," +
                "\"tournament_id\":1086875,\"underway_at\":null,\"updated_at\":\"2015-01-19T16:57:17-05:00\"," +
                "\"completed_at\":\"2015-01-19T16:57:17-05:00\", \"optional\": true, \"forfeited\": true," +
                "\"winner_id\":null,\"prerequisite_match_ids_csv\":\"\",\"scores_csv\":\"\"}}";

        Match match = this.serializer.deserialize(matchString, Match.class);

        OffsetDateTime created = OffsetDateTime.parse("2015-01-19T16:57:17-05:00");
        OffsetDateTime started = OffsetDateTime.parse("2015-01-19T16:57:17-05:00");
        OffsetDateTime updated = OffsetDateTime.parse("2015-01-19T16:57:17-05:00");
        OffsetDateTime completed = OffsetDateTime.parse("2015-01-19T16:57:17-05:00");

        assertEquals(completed, match.getCompletedAt());
        assertTrue(match.getOptional());
        assertTrue(match.getForfeited());
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

        assertEquals(this.match, match);
    }

    @Test
    public void testAttachmentDeserialization() {
        String attachmentString = "{\"match_attachment\":{\"id\":363084,\"match_id\":40637548,\"user_id\":12345," +
                "\"description\":null,\"url\":\"\",\"original_file_name\":null,\"created_at\":\"2018-09-21T06:21:25.884-04:00\"," +
                "\"updated_at\":\"2018-09-21T06:21:25.884-04:00\",\"asset_file_name\":null,\"asset_content_type\":null," +
                "\"asset_file_size\":null,\"asset_url\":\"//www.google.com\"}}";

        Attachment attachment = this.serializer.deserialize(attachmentString, Attachment.class);

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
        assertEquals("https://www.google.com", attachment.getAssetUrl());

        assertEquals(this.attachment, attachment);
    }

    @Test(expected = JsonParseException.class)
    public void testInvalidDateDeserialization() {
        String attachmentStringInvalidDate = "{\"match_attachment\":{\"id\":363084,\"match_id\":40637548,\"user_id\":12345," +
                "\"description\":null,\"url\":\"\",\"original_file_name\":null,\"created_at\":\"2018-09-21T06:21:25.884-04:00\"," +
                "\"updated_at\":\"2018-09-2:25.8804:00\",\"asset_file_name\":null,\"asset_content_type\":null," +
                "\"asset_file_size\":null,\"asset_url\":null}}";

        this.serializer.deserialize(attachmentStringInvalidDate, Attachment.class);
    }
}
