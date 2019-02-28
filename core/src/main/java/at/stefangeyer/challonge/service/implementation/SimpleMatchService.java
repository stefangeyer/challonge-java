package at.stefangeyer.challonge.service.implementation;

import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.enumeration.MatchState;
import at.stefangeyer.challonge.model.query.MatchQuery;
import at.stefangeyer.challonge.model.query.wrapper.MatchQueryWrapper;
import at.stefangeyer.challonge.model.wrapper.MatchWrapper;
import at.stefangeyer.challonge.rest.MatchRestClient;
import at.stefangeyer.challonge.service.MatchService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Match Service Implementation
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
public class SimpleMatchService implements MatchService {

    private MatchRestClient restClient;

    public SimpleMatchService(MatchRestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public List<Match> getMatches(Tournament tournament, Participant participant,
                                  MatchState state) throws DataAccessException {
        return this.restClient.getMatches(String.valueOf(tournament.getId()),
                participant != null ? participant.getId() : null, state)
                .stream().map(MatchWrapper::getMatch).collect(Collectors.toList());
    }

    @Override
    public void getMatches(Tournament tournament, Participant participant, MatchState state,
                           Callback<List<Match>> onSuccess, Callback<DataAccessException> onFailure) {
        this.restClient.getMatches(String.valueOf(tournament.getId()), participant.getId(), state,
                list -> onSuccess.accept(list.stream().map(MatchWrapper::getMatch)
                        .collect(Collectors.toList())), onFailure);
    }

    @Override
    public Match getMatch(Tournament tournament, long matchId, boolean includeAttachments) throws DataAccessException {
        return this.restClient.getMatch(String.valueOf(tournament.getId()), matchId, includeAttachments).getMatch();
    }

    @Override
    public void getMatch(Tournament tournament, long matchId, boolean includeAttachments, Callback<Match> onSuccess,
                         Callback<DataAccessException> onFailure) {
        this.restClient.getMatch(String.valueOf(tournament.getId()), matchId, includeAttachments,
                mw -> onSuccess.accept(mw.getMatch()), onFailure);
    }

    @Override
    public Match updateMatch(Match match, MatchQuery data) throws DataAccessException {
        validateMatchQuery(data);

        return this.restClient.updateMatch(String.valueOf(match.getTournamentId()), match.getId(),
                new MatchQueryWrapper(data)).getMatch();
    }

    @Override
    public void updateMatch(Match match, MatchQuery data, Callback<Match> onSuccess,
                            Callback<DataAccessException> onFailure) {
        validateMatchQuery(data);

        this.restClient.updateMatch(String.valueOf(match.getTournamentId()), match.getId(), new MatchQueryWrapper(data),
                mw -> onSuccess.accept(mw.getMatch()), onFailure);
    }

    @Override
    public Match reopenMatch(Match match) throws DataAccessException {
        return this.restClient.reopenMatch(String.valueOf(match.getTournamentId()), match.getId()).getMatch();
    }

    @Override
    public void reopenMatch(Match match, Callback<Match> onSuccess, Callback<DataAccessException> onFailure) {
        this.restClient.reopenMatch(String.valueOf(match.getTournamentId()), match.getId(),
                mw -> onSuccess.accept(mw.getMatch()), onFailure);
    }

    private void validateMatchQuery(MatchQuery data) {
        if (data.getScoresCsv() == null && data.getWinnerId() == null && data.getVotesForPlayer1() == null &&
                data.getVotesForPlayer2() == null) {
            throw new IllegalArgumentException("All data parameters are null. Provide at least one");
        }
    }
}
