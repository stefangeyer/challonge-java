package at.stefangeyer.challonge

import at.stefangeyer.challonge.model.Credentials
import at.stefangeyer.challonge.rest.client.RestClientFactory
import at.stefangeyer.challonge.rest.client.retrofit.RetrofitRestClientFactory
import at.stefangeyer.challonge.serializer.Serializer
import at.stefangeyer.challonge.serializer.implementation.GsonSerializer
import at.stefangeyer.challonge.service.AttachmentService
import at.stefangeyer.challonge.service.MatchService
import at.stefangeyer.challonge.service.ParticipantService
import at.stefangeyer.challonge.service.TournamentService
import at.stefangeyer.challonge.service.implementation.SimpleAttachmentService
import at.stefangeyer.challonge.service.implementation.SimpleMatchService
import at.stefangeyer.challonge.service.implementation.SimpleParticipantService
import at.stefangeyer.challonge.service.implementation.SimpleTournamentService

class Challonge(credentials: Credentials, serializer: Serializer = GsonSerializer(),
                private val restClientFactory: RestClientFactory = RetrofitRestClientFactory()) {

    val tournaments: TournamentService
    val participants: ParticipantService
    val matches: MatchService
    val attachments: AttachmentService

    init {
        // Initialize factory
        this.restClientFactory.initialize(credentials, serializer)

        this.tournaments = SimpleTournamentService(this.restClientFactory.createTournamentRestClient())
        this.participants = SimpleParticipantService(this.restClientFactory.createParticipantRestClient())
        this.matches = SimpleMatchService(this.restClientFactory.createMatchRestClient())
        this.attachments = SimpleAttachmentService(this.restClientFactory.createAttachmentRestClient())
    }
}