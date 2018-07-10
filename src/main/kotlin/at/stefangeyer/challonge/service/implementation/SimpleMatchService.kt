package at.stefangeyer.challonge.service.implementation

import at.stefangeyer.challonge.async.Callback
import at.stefangeyer.challonge.exception.DataAccessException
import at.stefangeyer.challonge.model.Match
import at.stefangeyer.challonge.model.Participant
import at.stefangeyer.challonge.model.Tournament
import at.stefangeyer.challonge.model.enum.MatchState
import at.stefangeyer.challonge.model.query.MatchQuery
import at.stefangeyer.challonge.model.query.wrapper.MatchQueryWrapper
import at.stefangeyer.challonge.rest.MatchRestClient
import at.stefangeyer.challonge.service.MatchService

/**
 * Match Service Implementation
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
class SimpleMatchService(private val restClient: MatchRestClient) : MatchService {

    override fun getMatches(tournament: Tournament, participant: Participant?, state: MatchState?): List<Match> =
            this.restClient.getMatches(tournament.id.toString(), participant?.id, state).map { mw -> mw.match }

    override fun getMatches(tournament: Tournament, participant: Participant?, state: MatchState?,
                            onSuccess: Callback<List<Match>>, onFailure: Callback<DataAccessException>) {
        this.restClient.getMatches(tournament.id.toString(), participant?.id, state,
                { list -> onSuccess(list.map { mw -> mw.match }) }, onFailure)
    }

    override fun getMatch(tournament: Tournament, matchId: Long, includeAttachments: Boolean): Match =
            this.restClient.getMatch(tournament.id.toString(), matchId, includeAttachments).match

    override fun getMatch(tournament: Tournament, matchId: Long, includeAttachments: Boolean,
                          onSuccess: Callback<Match>, onFailure: Callback<DataAccessException>) {
        this.restClient.getMatch(tournament.id.toString(), matchId, includeAttachments,
                { mw -> onSuccess(mw.match) }, onFailure)
    }

    override fun updateMatch(match: Match, data: MatchQuery): Match {
        validateMatchQuery(data)
        return this.restClient.updateMatch(match.tournamentId.toString(), match.id, MatchQueryWrapper(data)).match
    }

    override fun updateMatch(match: Match, data: MatchQuery, onSuccess: Callback<Match>, onFailure: Callback<DataAccessException>) {
        validateMatchQuery(data)
        return this.restClient.updateMatch(match.tournamentId.toString(), match.id, MatchQueryWrapper(data),
                { mw -> onSuccess(mw.match) }, onFailure)
    }

    override fun reopenMatch(match: Match): Match = this.restClient.reopenMatch(match.tournamentId.toString(), match.id).match

    override fun reopenMatch(match: Match, onSuccess: Callback<Match>, onFailure: Callback<DataAccessException>) {
        this.restClient.reopenMatch(match.tournamentId.toString(), match.id,
                { mw -> onSuccess(mw.match) }, onFailure)
    }

    private fun validateMatchQuery(data: MatchQuery) {
        if (data.scoresCsv == null && data.winnerId == null && data.votesForPlayer1 == null && data.votesForPlayer2 == null) {
            throw IllegalArgumentException("All data parameters are null. Provide at least one")
        }
    }
}