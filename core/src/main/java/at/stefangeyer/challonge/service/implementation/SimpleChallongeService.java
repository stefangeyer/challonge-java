package at.stefangeyer.challonge.service.implementation;

import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.enumeration.MatchState;
import at.stefangeyer.challonge.model.enumeration.TournamentType;
import at.stefangeyer.challonge.model.query.AttachmentQuery;
import at.stefangeyer.challonge.model.query.MatchQuery;
import at.stefangeyer.challonge.model.query.ParticipantQuery;
import at.stefangeyer.challonge.model.query.TournamentQuery;
import at.stefangeyer.challonge.model.query.enumeration.TournamentQueryState;
import at.stefangeyer.challonge.model.query.wrapper.MatchQueryWrapper;
import at.stefangeyer.challonge.model.query.wrapper.ParticipantQueryListWrapper;
import at.stefangeyer.challonge.model.query.wrapper.ParticipantQueryWrapper;
import at.stefangeyer.challonge.model.query.wrapper.TournamentQueryWrapper;
import at.stefangeyer.challonge.model.wrapper.AttachmentWrapper;
import at.stefangeyer.challonge.model.wrapper.MatchWrapper;
import at.stefangeyer.challonge.model.wrapper.ParticipantWrapper;
import at.stefangeyer.challonge.model.wrapper.TournamentWrapper;
import at.stefangeyer.challonge.rest.RestClient;
import at.stefangeyer.challonge.service.ChallongeService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Challonge Service Implementation
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
public class SimpleChallongeService implements ChallongeService {
    
    private RestClient restClient;

    public SimpleChallongeService(RestClient restClient) {
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

    @Override
    public List<Participant> getParticipants(Tournament tournament) throws DataAccessException {
        return this.restClient.getParticipants(String.valueOf(tournament.getId())).stream()
                .map(ParticipantWrapper::getParticipant).collect(Collectors.toList());
    }

    @Override
    public void getParticipants(Tournament tournament, Callback<List<Participant>> onSuccess, Callback<DataAccessException> onFailure) {
        this.restClient.getParticipants(String.valueOf(tournament.getId()), list -> onSuccess.accept(list.stream()
                .map(ParticipantWrapper::getParticipant).collect(Collectors.toList())), onFailure);
    }

    @Override
    public Participant getParticipant(Tournament tournament, long participantId, boolean includeMatches) throws DataAccessException {
        return this.restClient.getParticipant(String.valueOf(tournament.getId()), participantId, includeMatches).getParticipant();
    }

    @Override
    public void getParticipant(Tournament tournament, long participantId, boolean includeMatches,
                               Callback<Participant> onSuccess, Callback<DataAccessException> onFailure) {
        this.restClient.getParticipant(String.valueOf(tournament.getId()), participantId, includeMatches,
                pw -> onSuccess.accept(pw.getParticipant()), onFailure);
    }

    @Override
    public Participant addParticipant(Tournament tournament, ParticipantQuery data) throws DataAccessException {
        validateParticipantQuery(data);
        return this.restClient.addParticipant(String.valueOf(tournament.getId()), new ParticipantQueryWrapper(data)).getParticipant();
    }

    @Override
    public void addParticipant(Tournament tournament, ParticipantQuery data, Callback<Participant> onSuccess,
                               Callback<DataAccessException> onFailure) {
        validateParticipantQuery(data);
        this.restClient.addParticipant(String.valueOf(tournament.getId()), new ParticipantQueryWrapper(data),
                pw -> onSuccess.accept(pw.getParticipant()), onFailure);
    }

    @Override
    public List<Participant> bulkAddParticipants(Tournament tournament, List<ParticipantQuery> data) throws DataAccessException {
        for (ParticipantQuery query : data) {
            validateParticipantQuery(query);
        }

        return this.restClient.bulkAddParticipants(String.valueOf(tournament.getId()),
                new ParticipantQueryListWrapper(data)).stream().map(ParticipantWrapper::getParticipant)
                .collect(Collectors.toList());
    }

    @Override
    public void bulkAddParticipants(Tournament tournament, List<ParticipantQuery> data,
                                    Callback<List<Participant>> onSuccess, Callback<DataAccessException> onFailure) {
        for (ParticipantQuery query : data) {
            validateParticipantQuery(query);
        }

        this.restClient.bulkAddParticipants(String.valueOf(tournament.getId()), new ParticipantQueryListWrapper(data),
                list -> onSuccess.accept(list.stream().map(ParticipantWrapper::getParticipant).collect(Collectors.toList())), onFailure);
    }

    @Override
    public Participant updateParticipant(Participant participant, ParticipantQuery data) throws DataAccessException {
        validateParticipantQuery(data);

        return this.restClient.updateParticipant(String.valueOf(participant.getTournamentId()), participant.getId(),
                new ParticipantQueryWrapper(data)).getParticipant();
    }

    @Override
    public void updateParticipant(Participant participant, ParticipantQuery data, Callback<Participant> onSuccess,
                                  Callback<DataAccessException> onFailure) {
        validateParticipantQuery(data);

        this.restClient.updateParticipant(String.valueOf(participant.getTournamentId()), participant.getId(),
                new ParticipantQueryWrapper(data), pw -> onSuccess.accept(pw.getParticipant()), onFailure);
    }

    @Override
    public Participant checkInParticipant(Participant participant) throws DataAccessException {
        return this.restClient.checkInParticipant(String.valueOf(participant.getTournamentId()), participant.getId()).getParticipant();
    }

    @Override
    public void checkInParticipant(Participant participant, Callback<Participant> onSuccess, Callback<DataAccessException> onFailure) {
        this.restClient.checkInParticipant(String.valueOf(participant.getTournamentId()), participant.getId(),
                pw -> onSuccess.accept(pw.getParticipant()), onFailure);
    }

    @Override
    public Participant undoCheckInParticipant(Participant participant) throws DataAccessException {
        return this.restClient.undoCheckInParticipant(String.valueOf(participant.getTournamentId()), participant.getId()).getParticipant();
    }

    @Override
    public void undoCheckInParticipant(Participant participant, Callback<Participant> onSuccess, Callback<DataAccessException> onFailure) {
        this.restClient.undoCheckInParticipant(String.valueOf(participant.getTournamentId()), participant.getId(),
                pw -> onSuccess.accept(pw.getParticipant()), onFailure);
    }

    @Override
    public Participant deleteParticipant(Participant participant) throws DataAccessException {
        return this.restClient.deleteParticipant(String.valueOf(participant.getTournamentId()), participant.getId()).getParticipant();
    }

    @Override
    public void deleteParticipant(Participant participant, Callback<Participant> onSuccess, Callback<DataAccessException> onFailure) {
        this.restClient.deleteParticipant(String.valueOf(participant.getTournamentId()), participant.getId(),
                pw -> onSuccess.accept(pw.getParticipant()), onFailure);
    }

    @Override
    public List<Participant> randomizeParticipants(Tournament tournament) throws DataAccessException {
        return this.restClient.randomizeParticipants(String.valueOf(tournament.getId())).stream()
                .map(ParticipantWrapper::getParticipant).collect(Collectors.toList());
    }

    @Override
    public void randomizeParticipants(Tournament tournament, Callback<List<Participant>> onSuccess, Callback<DataAccessException> onFailure) {
        this.restClient.randomizeParticipants(String.valueOf(tournament.getId()),
                list -> onSuccess.accept(list.stream().map(ParticipantWrapper::getParticipant).collect(Collectors.toList())), onFailure);
    }

    private void validateParticipantQuery(ParticipantQuery data) {
        if (data.getName() == null && data.getEmail() == null && data.getChallongeUsername() == null &&
                data.getSeed() == null && data.getMisc() == null && data.getInviteNameOrEmail() == null) {
            throw new IllegalArgumentException("All data parameters are null. Provide at least one");
        }
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
        this.restClient.getMatches(String.valueOf(tournament.getId()),
                participant != null ? participant.getId() : null, state,
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
    public Match markMatchAsUnderway(Match match) throws DataAccessException {
    	return this.restClient.markMatchAsUnderway(String.valueOf(match.getTournamentId()), match.getId()).getMatch();
    }

    @Override
    public void markMatchAsUnderway(Match match, Callback<Match> onSuccess, Callback<DataAccessException> onFailure) {
    	this.restClient.markMatchAsUnderway(String.valueOf(match.getTournamentId()), match.getId(),
    			mw -> onSuccess.accept(mw.getMatch()), onFailure);
    }

    @Override
    public Match unmarkMatchAsUnderway(Match match) throws DataAccessException {
    	return this.restClient.unmarkMatchAsUnderway(String.valueOf(match.getTournamentId()), match.getId()).getMatch();
    }

    @Override
    public void unmarkMatchAsUnderway(Match match, Callback<Match> onSuccess, Callback<DataAccessException> onFailure) {
    	this.restClient.unmarkMatchAsUnderway(String.valueOf(match.getTournamentId()), match.getId(),
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

    @Override
    public List<Attachment> getAttachments(Match match) throws DataAccessException {
        return this.restClient.getAttachments(String.valueOf(match.getTournamentId()), match.getId()).stream()
                .map(AttachmentWrapper::getMatchAttachment).collect(Collectors.toList());
    }

    @Override
    public void getAttachments(Match match, Callback<List<Attachment>> onSuccess,
                               Callback<DataAccessException> onFailure) {
        this.restClient.getAttachments(String.valueOf(match.getTournamentId()), match.getId(),
                list -> onSuccess.accept(list.stream().map(AttachmentWrapper::getMatchAttachment)
                        .collect(Collectors.toList())), onFailure);
    }

    @Override
    public Attachment getAttachment(Match match, long attachmentId) throws DataAccessException {
        return this.restClient.getAttachment(String.valueOf(match.getTournamentId()), match.getId(),
                attachmentId).getMatchAttachment();
    }

    @Override
    public void getAttachment(Match match, long attachmentId, Callback<Attachment> onSuccess,
                              Callback<DataAccessException> onFailure) {
        this.restClient.getAttachment(String.valueOf(match.getTournamentId()), match.getId(), attachmentId,
                aw -> onSuccess.accept(aw.getMatchAttachment()), onFailure);
    }

    @Override
    public Attachment createAttachment(Match match, AttachmentQuery data) throws DataAccessException {
        validateAttachmentQuery(data);

        return this.restClient.createAttachment(String.valueOf(match.getTournamentId()), match.getId(),
                data).getMatchAttachment();
    }

    @Override
    public void createAttachment(Match match, AttachmentQuery data, Callback<Attachment> onSuccess,
                                 Callback<DataAccessException> onFailure) {
        validateAttachmentQuery(data);

        this.restClient.createAttachment(String.valueOf(match.getTournamentId()), match.getId(), data,
                aw -> onSuccess.accept(aw.getMatchAttachment()), onFailure);
    }

    @Override
    public Attachment updateAttachment(Match match, Attachment attachment,
                                       AttachmentQuery data) throws DataAccessException {
        validateAttachmentQuery(data);

        return this.restClient.updateAttachment(String.valueOf(match.getTournamentId()), match.getId(),
                attachment.getId(), data).getMatchAttachment();
    }

    @Override
    public void updateAttachment(Match match, Attachment attachment, AttachmentQuery data,
                                 Callback<Attachment> onSuccess, Callback<DataAccessException> onFailure) {
        validateAttachmentQuery(data);

        this.restClient.updateAttachment(String.valueOf(match.getTournamentId()), match.getId(),
                attachment.getId(), data, aw -> onSuccess.accept(aw.getMatchAttachment()), onFailure);
    }

    @Override
    public Attachment deleteAttachment(Match match, Attachment attachment) throws DataAccessException {
        return this.restClient.deleteAttachment(String.valueOf(match.getTournamentId()), match.getId(),
                attachment.getId()).getMatchAttachment();
    }

    @Override
    public void deleteAttachment(Match match, Attachment attachment, Callback<Attachment> onSuccess,
                                 Callback<DataAccessException> onFailure) {
        this.restClient.deleteAttachment(String.valueOf(match.getTournamentId()), match.getId(),
                attachment.getId(), aw -> onSuccess.accept(aw.getMatchAttachment()), onFailure);
    }

    private void validateAttachmentQuery(AttachmentQuery data) {
        if (data.getAsset() == null && data.getUrl() == null && data.getDescription() == null) {
            throw new IllegalArgumentException("All data parameters are null. Provide at least one");
        }
    }
}
