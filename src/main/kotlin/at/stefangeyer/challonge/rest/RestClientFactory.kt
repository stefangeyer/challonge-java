package at.stefangeyer.challonge.rest

import at.stefangeyer.challonge.model.Credentials
import at.stefangeyer.challonge.rest.AttachmentRestClient
import at.stefangeyer.challonge.rest.MatchRestClient
import at.stefangeyer.challonge.rest.ParticipantRestClient
import at.stefangeyer.challonge.rest.TournamentRestClient
import at.stefangeyer.challonge.serializer.Serializer

/**
 * Factory definitions for the rest clients
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
interface RestClientFactory {

    /**
     * Initializes the factory with the necessary dependencies
     * @param credentials The username and api key to use
     * @param serializer The serializer to use
     */
    fun initialize(credentials: Credentials, serializer: Serializer)

    /**
     * Creates an instance of the TournamentRestClient
     * @return TournamentRestClient
     */
    fun createTournamentRestClient(): TournamentRestClient

    /**
     * Creates an instance of the ParticipantRestClient
     * @return ParticipantRestClient
     */
    fun createParticipantRestClient(): ParticipantRestClient

    /**
     * Creates an instance of the MatchRestClient
     * @return MatchRestClient
     */
    fun createMatchRestClient(): MatchRestClient

    /**
     * Creates an instance of the AttachmentRestClient
     * @return AttachmentRestClient
     */
    fun createAttachmentRestClient(): AttachmentRestClient
}