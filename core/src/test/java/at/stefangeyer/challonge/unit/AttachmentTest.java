package at.stefangeyer.challonge.unit;

import at.stefangeyer.challonge.Challonge;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.*;
import at.stefangeyer.challonge.model.enumeration.TournamentType;
import at.stefangeyer.challonge.model.query.AttachmentQuery;
import at.stefangeyer.challonge.model.wrapper.AttachmentWrapper;
import at.stefangeyer.challonge.rest.AttachmentRestClient;
import at.stefangeyer.challonge.rest.RestClient;
import at.stefangeyer.challonge.serializer.Serializer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AttachmentTest {

    private Challonge challonge;

    private Random random = new Random();

    private Tournament tournament = Tournament.builder()
            .id(10L).url("tourney123").tournamentType(TournamentType.SINGLE_ELIMINATION)
            .matches(new ArrayList<>(List.of(
                    Match.builder().id(1L).tournamentId(10L).player1Id(1L).player2Id(2L).attachments(new ArrayList<>(List.of(
                            Attachment.builder().id(1L).description("Attachment note").build(),
                            Attachment.builder().id(2L).description("Some description").build(),
                            Attachment.builder().id(3L).url("http://some.resource.com/resource").build()
                    ))).build()
            ))).participants(new ArrayList<>(List.of(
                    Participant.builder().id(1L).tournamentId(10L).name("Participant 1").matches(new ArrayList<>()).build(),
                    Participant.builder().id(2L).tournamentId(10L).name("Participant 2").matches(new ArrayList<>()).build()
            )))
            .build();

    public AttachmentTest() throws DataAccessException {
        AttachmentRestClient arc = mock(AttachmentRestClient.class);

        when(arc.getAttachments(any(), anyLong())).thenAnswer(i -> {
            Tournament t = getTournament(i.getArgument(0));
            Match m = getMatch(t, i.getArgument(1));

            return m.getAttachments().stream().map(AttachmentWrapper::new).collect(Collectors.toList());
        });

        when(arc.getAttachment(any(), anyLong(), anyLong())).thenAnswer(i -> {
            Tournament t = getTournament(i.getArgument(0));
            Match m = getMatch(t, i.getArgument(1));
            Attachment a = getAttachment(m, i.getArgument(2));

            return new AttachmentWrapper(a);
        });

        when(arc.createAttachment(any(), anyLong(), any())).thenAnswer(i -> {
            Tournament t = getTournament(i.getArgument(0));
            Match m = getMatch(t, i.getArgument(1));

            AttachmentQuery data = i.getArgument(2);
            Attachment a = Attachment.builder()
                    .id((long) this.random.nextInt(1000))
                    .assetUrl(data.getUrl())
                    .description(data.getDescription()).build();

            m.getAttachments().add(a);

            return new AttachmentWrapper(a);
        });

        when(arc.updateAttachment(any(), anyLong(), anyLong(), any())).thenAnswer(i -> {
            Tournament t = getTournament(i.getArgument(0));
            Match m = getMatch(t, i.getArgument(1));
            Attachment a = getAttachment(m, i.getArgument(2));

            // emitted content update

            return new AttachmentWrapper(a);
        });

        when(arc.deleteAttachment(any(), anyLong(), anyLong())).thenAnswer(i -> {
            Tournament t = getTournament(i.getArgument(0));
            Match m = getMatch(t, i.getArgument(1));
            Attachment a = getAttachment(m, i.getArgument(2));

            m.getAttachments().remove(a);

            return new AttachmentWrapper(a);
        });

        RestClient restClient = mock(RestClient.class);

        when(restClient.createAttachmentRestClient()).thenReturn(arc);

        Serializer serializer = mock(Serializer.class);

        this.challonge = new Challonge(new Credentials("", ""), serializer, restClient);
    }

    private Tournament getTournament(String key) {
        return this.tournament;
    }

    private Match getMatch(Tournament t, long id) throws DataAccessException {
        Optional<Match> optional = t.getMatches().stream().filter(m -> m.getId().equals(id)).findFirst();

        if (!optional.isPresent()) {
            throw new DataAccessException("match not found");
        }

        return optional.get();
    }

    private Attachment getAttachment(Match m, long id) throws DataAccessException {
        Optional<Attachment> optional = m.getAttachments().stream().filter(a -> a.getId().equals(id)).findFirst();

        if (!optional.isPresent()) {
            throw new DataAccessException("attachment not found");
        }

        return optional.get();
    }

    @Test
    public void testGetAttachments() throws DataAccessException {
        var tournament = getTournament("tourney123");
        var match = tournament.getMatches().get(0);
        var local = this.challonge.getAttachments(match);
        assertEquals(match.getAttachments(), local);
    }

    @Test
    public void testGetAttachment() throws DataAccessException {
        var tournament = getTournament("tourney123");
        var match = tournament.getMatches().get(0);
        var local = this.challonge.getAttachment(match, 1);
        assertEquals("Attachment note", local.getDescription());
    }

    @Test
    public void testCreateAttachment() throws DataAccessException {
        var tournament = getTournament("tourney123");
        var match = tournament.getMatches().get(0);
        assertNotNull(match.getAttachments());

        var attachments = match.getAttachments();
        var local = this.challonge.createAttachment(match,
                AttachmentQuery.builder().description("Some new attachment").build());
        assertTrue(attachments.contains(local));
    }

    @Test
    public void testUpdateAttachment() throws DataAccessException {
        var tournament = getTournament("tourney123");
        var match = tournament.getMatches().get(0);
        assertNotNull(match.getAttachments());
        var attachments = match.getAttachments();
        var local = this.challonge.updateAttachment(match, attachments.get(0),
                AttachmentQuery.builder().url("https://www.google.com").build());
        assertEquals(attachments.get(0), local);
    }

    @Test
    public void testDeleteAttachment() throws DataAccessException {
        var tournament = getTournament("tourney123");
        var match = tournament.getMatches().get(0);
        assertNotNull(match.getAttachments());
        var attachments = match.getAttachments();
        var local = this.challonge.deleteAttachment(match, attachments.get(0));
        assertFalse(attachments.contains(local));
    }
}
