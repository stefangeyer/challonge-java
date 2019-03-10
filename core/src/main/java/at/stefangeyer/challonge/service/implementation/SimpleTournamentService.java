package at.stefangeyer.challonge.service.implementation;

import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.enumeration.TournamentType;
import at.stefangeyer.challonge.model.query.TournamentQuery;
import at.stefangeyer.challonge.model.query.enumeration.TournamentQueryState;
import at.stefangeyer.challonge.model.query.wrapper.TournamentQueryWrapper;
import at.stefangeyer.challonge.model.wrapper.TournamentWrapper;
import at.stefangeyer.challonge.rest.TournamentRestClient;
import at.stefangeyer.challonge.service.TournamentService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Tournament Service Implementation
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
public class SimpleTournamentService implements TournamentService {

    private TournamentRestClient restClient;

    public SimpleTournamentService(TournamentRestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public List<Tournament> getTournaments(TournamentQueryState state, TournamentType type, OffsetDateTime createdAfter,
                                           OffsetDateTime createdBefore, String subdomain) throws DataAccessException {
        return this.restClient.getTournaments(state, type, createdAfter, createdBefore, subdomain).stream().map(TournamentWrapper::getTournament).collect(Collectors.toList());
    }

    @Override
    public void getTournaments(TournamentQueryState state, TournamentType type, OffsetDateTime createdAfter,
                               OffsetDateTime createdBefore, String subdomain, Callback<List<Tournament>> onSuccess,
                               Callback<DataAccessException> onFailure) {
        this.restClient.getTournaments(state, type, createdAfter, createdBefore, subdomain,
                list -> onSuccess.accept(list.stream().map(TournamentWrapper::getTournament).collect(Collectors.toList())),
                onFailure);
    }

    @Override
    public Tournament getTournament(String tournament, boolean includeParticipants, boolean includeMatches) throws DataAccessException {
        return this.restClient.getTournament(tournament, includeParticipants, includeMatches).getTournament();
    }

    @Override
    public void getTournament(String tournament, boolean includeParticipants, boolean includeMatches,
                              Callback<Tournament> onSuccess, Callback<DataAccessException> onFailure) {
        this.restClient.getTournament(tournament, includeParticipants, includeMatches,
                tw -> onSuccess.accept(tw.getTournament()), onFailure);
    }

    @Override
    public Tournament createTournament(TournamentQuery data) throws DataAccessException {
        validateTournamentQuery(data);

        return this.restClient.createTournament(new TournamentQueryWrapper(data)).getTournament();
    }

    @Override
    public void createTournament(TournamentQuery data, Callback<Tournament> onSuccess,
                                 Callback<DataAccessException> onFailure) {
        validateTournamentQuery(data);

        this.restClient.createTournament(new TournamentQueryWrapper(data), tw -> onSuccess.accept(tw.getTournament()), onFailure);
    }

    @Override
    public Tournament updateTournament(Tournament tournament, TournamentQuery data) throws DataAccessException {
        validateTournamentQuery(data);

        return this.restClient.updateTournament(String.valueOf(tournament.getId()), new TournamentQueryWrapper(data)).getTournament();
    }

    @Override
    public void updateTournament(Tournament tournament, TournamentQuery data, Callback<Tournament> onSuccess,
                                 Callback<DataAccessException> onFailure) {
        validateTournamentQuery(data);

        this.restClient.updateTournament(String.valueOf(tournament.getId()), new TournamentQueryWrapper(data),
                tw -> onSuccess.accept(tw.getTournament()), onFailure);
    }

    @Override
    public Tournament deleteTournament(Tournament tournament) throws DataAccessException {
        return this.restClient.deleteTournament(String.valueOf(tournament.getId())).getTournament();
    }

    @Override
    public void deleteTournament(Tournament tournament, Callback<Tournament> onSuccess,
                                 Callback<DataAccessException> onFailure) {
        this.restClient.deleteTournament(String.valueOf(tournament.getId()),
                tw -> onSuccess.accept(tw.getTournament()), onFailure);
    }

    @Override
    public Tournament processCheckIns(Tournament tournament, boolean includeParticipants,
                                      boolean includeMatches) throws DataAccessException {
        return this.restClient.processCheckIns(String.valueOf(tournament.getId()), includeParticipants,
                includeMatches).getTournament();
    }

    @Override
    public void processCheckIns(Tournament tournament, boolean includeParticipants, boolean includeMatches,
                                Callback<Tournament> onSuccess, Callback<DataAccessException> onFailure) {
        this.restClient.processCheckIns(String.valueOf(tournament.getId()), includeParticipants, includeMatches,
                tw -> onSuccess.accept(tw.getTournament()), onFailure);
    }

    @Override
    public Tournament abortCheckIn(Tournament tournament, boolean includeParticipants,
                                   boolean includeMatches) throws DataAccessException {
        return this.restClient.abortCheckIn(String.valueOf(tournament.getId()), includeParticipants,
                includeMatches).getTournament();
    }

    @Override
    public void abortCheckIn(Tournament tournament, boolean includeParticipants, boolean includeMatches,
                             Callback<Tournament> onSuccess, Callback<DataAccessException> onFailure) {
        this.restClient.abortCheckIn(String.valueOf(tournament.getId()), includeParticipants, includeMatches,
                tw -> onSuccess.accept(tw.getTournament()), onFailure);
    }

    @Override
    public Tournament startTournament(Tournament tournament, boolean includeParticipants,
                                      boolean includeMatches) throws DataAccessException {
        return this.restClient.startTournament(String.valueOf(tournament.getId()), includeParticipants,
                includeMatches).getTournament();
    }

    @Override
    public void startTournament(Tournament tournament, boolean includeParticipants, boolean includeMatches,
                                Callback<Tournament> onSuccess, Callback<DataAccessException> onFailure) {
        this.restClient.startTournament(String.valueOf(tournament.getId()), includeParticipants, includeMatches,
                tw -> onSuccess.accept(tw.getTournament()), onFailure);
    }

    @Override
    public Tournament finalizeTournament(Tournament tournament, boolean includeParticipants,
                                         boolean includeMatches) throws DataAccessException {
        return this.restClient.finalizeTournament(String.valueOf(tournament.getId()), includeParticipants,
                includeMatches).getTournament();
    }

    @Override
    public void finalizeTournament(Tournament tournament, boolean includeParticipants, boolean includeMatches,
                                   Callback<Tournament> onSuccess, Callback<DataAccessException> onFailure) {
        this.restClient.finalizeTournament(String.valueOf(tournament.getId()), includeParticipants,
                includeMatches, tw -> onSuccess.accept(tw.getTournament()), onFailure);
    }

    @Override
    public Tournament resetTournament(Tournament tournament, boolean includeParticipants,
                                      boolean includeMatches) throws DataAccessException {
        return this.restClient.resetTournament(String.valueOf(tournament.getId()), includeParticipants,
                includeMatches).getTournament();
    }

    @Override
    public void resetTournament(Tournament tournament, boolean includeParticipants, boolean includeMatches,
                                Callback<Tournament> onSuccess, Callback<DataAccessException> onFailure) {
        this.restClient.resetTournament(String.valueOf(tournament.getId()), includeParticipants,
                includeMatches, tw -> onSuccess.accept(tw.getTournament()), onFailure);
    }

    @Override
    public Tournament openTournamentForPredictions(Tournament tournament, boolean includeParticipants,
                                                   boolean includeMatches) throws DataAccessException {
        return this.restClient.openTournamentForPredictions(String.valueOf(tournament.getId()), includeParticipants,
                includeMatches).getTournament();
    }

    @Override
    public void openTournamentForPredictions(Tournament tournament, boolean includeParticipants, boolean includeMatches,
                                             Callback<Tournament> onSuccess, Callback<DataAccessException> onFailure) {
        this.restClient.openTournamentForPredictions(String.valueOf(tournament.getId()), includeParticipants,
                includeMatches, tw -> onSuccess.accept(tw.getTournament()), onFailure);
    }

    private void validateTournamentQuery(TournamentQuery data) {
        if (data.getName() == null && data.getTournamentType() == null &&
                data.getSubdomain() == null && data.getDescription() == null && data.getOpenSignup() == null &&
                data.getHoldThirdPlaceMatch() == null && data.getPointsForMatchWin() == null && data.getPointsForMatchTie() == null &&
                data.getPointsForGameWin() == null && data.getPointsForGameTie() == null && data.getPointsForBye() == null &&
                data.getSwissRounds() == null && data.getRankedBy() == null && data.getPointsForMatchWin() == null &&
                data.getPointsForMatchTie() == null && data.getPointsForGameWin() == null &&
                data.getPointsForGameTie() == null && data.getAcceptAttachments() == null && data.getHideForum() == null &&
                data.getShowRounds() == null && data.getPrivateOnly() == null && data.getNotifyUsersWhenMatchesOpen() == null &&
                data.getNotifyUsersWhenTheTournamentEnds() == null && data.getSequentialPairings() == null &&
                data.getSignupCap() == null && data.getStartAt() == null && data.getCheckInDuration() == null &&
                data.getGrandFinalsModifier() == null && data.getTieBreaks() == null) {
            throw new IllegalArgumentException("All data parameters are null. Provide at least one");
        }
    }
}
