package at.stefangeyer.challonge.service.implementation

import at.stefangeyer.challonge.model.Match
import at.stefangeyer.challonge.model.Participant
import at.stefangeyer.challonge.model.Tournament
import at.stefangeyer.challonge.model.enumeration.MatchState
import at.stefangeyer.challonge.model.query.MatchQuery
import at.stefangeyer.challonge.rest.MatchRestClient
import at.stefangeyer.challonge.service.MatchService

/**
 * Match Service Implementation
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
class SimpleMatchService(private val restClient: MatchRestClient): MatchService {

    override fun getMatches(tournament: Tournament, participant: Participant?, state: MatchState?): List<Match> =
            this.restClient.getMatches(tournament.id.toString(), participant?.id, state)

    override fun getMatch(tournament: Tournament, matchId: Long, includeAttachments: Boolean): Match =
            this.restClient.getMatch(tournament.id.toString(), matchId, includeAttachments)

    override fun updateMatch(match: Match, data: MatchQuery): Match =
            this.restClient.updateMatch(match.tournamentId.toString(), match.id, data)

    override fun reopenMatch(match: Match): Match = this.restClient.reopenMatch(match.tournamentId.toString(), match.id)
}