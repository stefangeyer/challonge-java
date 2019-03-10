package at.stefangeyer.challonge.rest.retrofit;

import at.stefangeyer.challonge.model.Credentials;
import at.stefangeyer.challonge.rest.AttachmentRestClient;
import at.stefangeyer.challonge.rest.MatchRestClient;
import at.stefangeyer.challonge.rest.ParticipantRestClient;
import at.stefangeyer.challonge.rest.TournamentRestClient;
import at.stefangeyer.challonge.serializer.Serializer;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class RetrofitRestClientTest {

    private RetrofitRestClient restClient;

    public RetrofitRestClientTest() {
        this.restClient = new RetrofitRestClient();

        Serializer serializer = mock(Serializer.class);
        Credentials credentials = new Credentials("username", "apikey");

        this.restClient.initialize(credentials, serializer);
    }

    @Test
    public void testCreateTournamentRestClient() {
        TournamentRestClient rc = this.restClient.createTournamentRestClient();
        assertNotNull(rc);
    }

    @Test(expected = IllegalStateException.class)
    public void testInvalidTournamentRestClientAccess() {
        RetrofitRestClient rc = new RetrofitRestClient();
        rc.createTournamentRestClient();
    }

    @Test
    public void testCreateParticipantRestClient() {
        ParticipantRestClient rc = this.restClient.createParticipantRestClient();
        assertNotNull(rc);
    }

    @Test(expected = IllegalStateException.class)
    public void testInvalidParticipantRestClientAccess() {
        RetrofitRestClient rc = new RetrofitRestClient();
        rc.createParticipantRestClient();
    }

    @Test
    public void testCreateMatchRestClient() {
        MatchRestClient rc = this.restClient.createMatchRestClient();
        assertNotNull(rc);
    }

    @Test(expected = IllegalStateException.class)
    public void testInvalidMatchRestClientAccess() {
        RetrofitRestClient rc = new RetrofitRestClient();
        rc.createMatchRestClient();
    }

    @Test
    public void testCreateAttachmentRestClient() {
        AttachmentRestClient rc = this.restClient.createAttachmentRestClient();
        assertNotNull(rc);
    }

    @Test(expected = IllegalStateException.class)
    public void testInvalidAttachmentRestClientAccess() {
        RetrofitRestClient rc = new RetrofitRestClient();
        rc.createAttachmentRestClient();
    }
}
