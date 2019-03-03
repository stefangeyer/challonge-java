package at.stefangeyer.challonge.serializer.gson;

import at.stefangeyer.challonge.model.enumeration.RankedBy;
import at.stefangeyer.challonge.model.enumeration.TournamentType;
import at.stefangeyer.challonge.model.query.AttachmentQuery;
import at.stefangeyer.challonge.model.query.MatchQuery;
import at.stefangeyer.challonge.model.query.ParticipantQuery;
import at.stefangeyer.challonge.model.query.TournamentQuery;
import at.stefangeyer.challonge.model.query.enumeration.GrandFinalsModifier;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class SerializerTest {

    private final TournamentQuery tournamentQuery = TournamentQuery.builder()
            .name("Test Tournament").url("someurl").tournamentType(TournamentType.DOUBLE_ELIMINATION)
            .acceptAttachments(false).checkInDuration(300L).description("Tournament Description")
            .grandFinalsModifier(GrandFinalsModifier.SINGLE_MATCH).hideForum(true).holdThirdPlaceMatch(false)
            .notifyUsersWhenMatchesOpen(false).startAt(OffsetDateTime.parse("2015-01-19T16:57:17-05:00"))
            .subdomain("subdomain").openSignup(false).pointsForMatchWin(1F).pointsForMatchTie(0F).pointsForGameWin(2F)
            .pointsForGameTie(1F).pointsForBye(1F).swissRounds(2).rankedBy(RankedBy.GAME_WINS).roundRobinPointsForGameWin(2F)
            .roundRobinPointsForGameTie(1F).roundRobinPointsForMatchTie(1F).roundRobinPointsForMatchWin(1F).showRounds(false)
            .privateOnly(false).notifyUsersWhenMatchesOpen(false).notifyUsersWhenTheTournamentEnds(true).sequentialPairings(true)
            .signupCap(2).tieBreaks(Collections.singletonList("game wins")).build();

    private final ParticipantQuery participantQuery = ParticipantQuery.builder()
            .challongeUsername("EXSolo").email("exsolo@mail.com").inviteNameOrEmail("EXSolo")
            .misc("ROFL").name("EXSolo").seed(2).build();

    private final MatchQuery matchQuery = MatchQuery.builder().scoresCsv("3-2,1-3,3-2").votesForPlayer1(14)
            .votesForPlayer2(12).winnerId(12345L).build();

    private final AttachmentQuery attachmentQuery = AttachmentQuery.builder().description("abc")
            .url("https://www.google.com").build();

    private GsonSerializer serializer = new GsonSerializer();

    @Test
    public void testTournamentSerialization() {
        String json = this.serializer.serialize(this.tournamentQuery);

        String tournamentQueryString = "{\"name\":\"Test Tournament\",\"url\":\"someurl\"," +
                "\"tournament_type\":\"double elimination\",\"subdomain\":\"subdomain\"," +
                "\"description\":\"Tournament Description\",\"open_signup\":false,\"hold_third_place_match\":false," +
                "\"pts_for_match_win\":1.0,\"pts_for_match_tie\":0.0,\"pts_for_game_win\":2.0,\"pts_for_game_tie\":1.0," +
                "\"pts_for_bye\":1.0,\"swiss_rounds\":2,\"ranked_by\":\"game wins\",\"rr_pts_for_game_win\":2.0," +
                "\"rr_pts_for_game_tie\":1.0,\"rr_pts_for_match_win\":1.0,\"rr_pts_for_match_tie\":1.0," +
                "\"accept_attachments\":false,\"hide_forum\":true,\"show_rounds\":false,\"private\":false," +
                "\"notify_users_when_matches_open\":false,\"notify_users_when_the_tournament_ends\":true," +
                "\"sequential_pairings\":true,\"signup_cap\":2,\"start_at\":\"2015-01-19T16:57:17-05:00\"," +
                "\"check_in_duration\":300,\"grand_finals_modifier\":\"single match\",\"tie_breaks\":[\"game wins\"]}";

        assertEquals(tournamentQueryString, json);
    }

    @Test
    public void testParticipantSerialization() {
        String json = this.serializer.serialize(this.participantQuery);

        String participantQueryString = "{\"name\":\"EXSolo\",\"email\":\"exsolo@mail.com\"," +
                "\"challonge_username\":\"EXSolo\",\"seed\":2,\"misc\":\"ROFL\",\"invite_name_or_email\":\"EXSolo\"}";

        assertEquals(participantQueryString, json);
    }

    @Test
    public void testMatchSerialization() {
        String json = this.serializer.serialize(this.matchQuery);

        String matchQueryString = "{\"winner_id\":12345,\"player1_votes\":14,\"player2_votes\":12," +
                "\"scores_csv\":\"3-2,1-3,3-2\"}";

        assertEquals(matchQueryString, json);
    }

    @Test
    public void testAttachmentSerialization() {
        String json = this.serializer.serialize(this.attachmentQuery);

        String attachmentQueryString = "{\"url\":\"https://www.google.com\",\"description\":\"abc\"}";

        assertEquals(attachmentQueryString, json);
    }
}
