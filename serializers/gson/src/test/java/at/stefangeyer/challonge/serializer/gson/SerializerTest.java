package at.stefangeyer.challonge.serializer.gson;

import at.stefangeyer.challonge.model.enumeration.TournamentType;
import at.stefangeyer.challonge.model.query.AttachmentQuery;
import at.stefangeyer.challonge.model.query.MatchQuery;
import at.stefangeyer.challonge.model.query.ParticipantQuery;
import at.stefangeyer.challonge.model.query.TournamentQuery;
import at.stefangeyer.challonge.model.query.enumeration.GrandFinalsModifier;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SerializerTest {

    private final TournamentQuery tournamentQuery = TournamentQuery.builder()
            .name("Test Tournament").url("someurl").tournamentType(TournamentType.DOUBLE_ELIMINATION)
            .acceptAttachments(false).checkInDuration(300L).description("Tournament Description")
            .grandFinalsModifier(GrandFinalsModifier.SINGLE_MATCH).hideForum(true).holdThirdPlaceMatch(false)
            .notifyUsersWhenMatchesOpen(false).build();

    private final String tournamentQueryString = "{\"name\":\"Test Tournament\",\"url\":\"someurl\"," +
            "\"tournament_type\":\"double elimination\",\"description\":\"Tournament Description\"," +
            "\"hold_third_place_match\":false,\"accept_attachments\":false,\"hide_forum\":true," +
            "\"notify_users_when_matches_open\":false,\"check_in_duration\":300," +
            "\"grand_finals_modifier\":\"single match\"}";

    private final ParticipantQuery participantQuery = ParticipantQuery.builder()
            .challongeUsername("EXSolo").email("exsolo@mail.com").inviteNameOrEmail("EXSolo")
            .misc("ROFL").name("EXSolo").seed(2).build();

    private final String participantQueryString = "{\"name\":\"EXSolo\",\"email\":\"exsolo@mail.com\"," +
            "\"challonge_username\":\"EXSolo\",\"seed\":2,\"misc\":\"ROFL\",\"invite_name_or_email\":\"EXSolo\"}";

    private final MatchQuery matchQuery = MatchQuery.builder().scoresCsv("3-2,1-3,3-2").votesForPlayer1(14)
            .votesForPlayer2(12).winnerId(12345L).build();

    private final String matchQueryString = "{\"winner_id\":12345,\"player1_votes\":14,\"player2_votes\":12," +
            "\"scores_csv\":\"3-2,1-3,3-2\"}";

    private final AttachmentQuery attachmentQuery = AttachmentQuery.builder().description("abc")
            .url("https://www.google.com").build();

    private final String attachmentQueryString = "{\"url\":\"https://www.google.com\",\"description\":\"abc\"}";

    private GsonSerializer serializer = new GsonSerializer();

    @Test
    public void testTournamentSerialization() {
        String json = this.serializer.serialize(this.tournamentQuery);
        System.out.println(json);

        assertEquals(this.tournamentQueryString, json);
    }

    @Test
    public void testParticipantSerialization() {
        String json = this.serializer.serialize(this.participantQuery);

        assertEquals(this.participantQueryString, json);
    }

    @Test
    public void testMatchSerialization() {
        String json = this.serializer.serialize(this.matchQuery);

        assertEquals(this.matchQueryString, json);
    }

    @Test
    public void testAttachmentSerialization() {
        String json = this.serializer.serialize(this.attachmentQuery);

        assertEquals(this.attachmentQueryString, json);
    }
}
