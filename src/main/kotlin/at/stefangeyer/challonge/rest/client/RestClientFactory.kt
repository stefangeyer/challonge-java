package at.stefangeyer.challonge.rest.client

import at.stefangeyer.challonge.rest.AttachmentRestClient
import at.stefangeyer.challonge.rest.MatchRestClient
import at.stefangeyer.challonge.rest.ParticipantRestClient
import at.stefangeyer.challonge.rest.TournamentRestClient

/**
 * Factory definitions for the rest clients
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
interface RestClientFactory {

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