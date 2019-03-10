package at.stefangeyer.challonge.rest;

import at.stefangeyer.challonge.model.Credentials;
import at.stefangeyer.challonge.serializer.Serializer;

/**
 * Factory definitions for the rest clients
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
public interface RestClient {

    /**
     * Initializes the factory with the necessary dependencies
     *
     * @param credentials The username and api key to use
     * @param serializer  The serializer to use
     */
    void initialize(Credentials credentials, Serializer serializer);

    /**
     * Creates an instance of the TournamentRestClient
     *
     * @return TournamentRestClient
     */
    TournamentRestClient createTournamentRestClient();

    /**
     * Creates an instance of the ParticipantRestClient
     *
     * @return ParticipantRestClient
     */
    ParticipantRestClient createParticipantRestClient();

    /**
     * Creates an instance of the MatchRestClient
     *
     * @return MatchRestClient
     */
    MatchRestClient createMatchRestClient();

    /**
     * Creates an instance of the AttachmentRestClient
     *
     * @return AttachmentRestClient
     */
    AttachmentRestClient createAttachmentRestClient();
}
