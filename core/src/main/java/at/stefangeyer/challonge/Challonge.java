package at.stefangeyer.challonge;

import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.*;
import at.stefangeyer.challonge.model.enumeration.MatchState;
import at.stefangeyer.challonge.model.enumeration.TournamentType;
import at.stefangeyer.challonge.model.query.AttachmentQuery;
import at.stefangeyer.challonge.model.query.MatchQuery;
import at.stefangeyer.challonge.model.query.ParticipantQuery;
import at.stefangeyer.challonge.model.query.TournamentQuery;
import at.stefangeyer.challonge.model.query.enumeration.TournamentQueryState;
import at.stefangeyer.challonge.rest.RestClient;
import at.stefangeyer.challonge.serializer.Serializer;
import at.stefangeyer.challonge.service.*;
import at.stefangeyer.challonge.service.implementation.SimpleChallongeService;

import java.time.OffsetDateTime;
import java.util.List;

public class Challonge implements ChallongeService {

    private ChallongeService service;

    public Challonge(Credentials credentials, Serializer serializer, RestClient restClient) {
        // Initialize factory
        restClient.initialize(credentials, serializer);

        this.service = new SimpleChallongeService(restClient);
    }

    public final List<Tournament> getTournaments(TournamentQueryState state, TournamentType type, OffsetDateTime createdAfter,
                                                 OffsetDateTime createdBefore, String subdomain) throws DataAccessException {
        return this.service.getTournaments(state, type, createdAfter, createdBefore, subdomain);
    }

    public final List<Tournament> getTournaments() throws DataAccessException {
        return getTournaments(null, null, null, null, null);
    }

    public final void getTournaments(TournamentQueryState state, TournamentType type, OffsetDateTime createdAfter,
                                     OffsetDateTime createdBefore, String subdomain, Callback<List<Tournament>> onSuccess,
                                     Callback<DataAccessException> onFailure) {
        this.service.getTournaments(state, type, createdAfter, createdBefore, subdomain, onSuccess, onFailure);
    }

    public final void getTournaments(TournamentQueryState state, TournamentType type, OffsetDateTime createdAfter,
                                     OffsetDateTime createdBefore, Callback<List<Tournament>> onSuccess,
                                     Callback<DataAccessException> onFailure) {
        getTournaments(state, type, createdAfter, createdBefore, null, onSuccess, onFailure);
    }

    public final void getTournaments(Callback<List<Tournament>> onSuccess, Callback<DataAccessException> onFailure) {
        getTournaments(null, null, null, null, null, onSuccess, onFailure);
    }

    public final Tournament getTournament(String tournament, boolean includeParticipants,
                                          boolean includeMatches) throws DataAccessException {
        return this.service.getTournament(tournament, includeParticipants, includeMatches);
    }

    public final Tournament getTournament(String tournament) throws DataAccessException {
        return getTournament(tournament, false, false);
    }

    public final void getTournament(String tournament, boolean includeParticipants, boolean includeMatches,
                                    Callback<Tournament> onSuccess, Callback<DataAccessException> onFailure) {
        this.service.getTournament(tournament, includeParticipants, includeMatches, onSuccess, onFailure);
    }

    public final void getTournament(String tournament, Callback<Tournament> onSuccess, Callback<DataAccessException> onFailure) {
        getTournament(tournament, false, false, onSuccess, onFailure);
    }

    public final Tournament createTournament(TournamentQuery data) throws DataAccessException {
        return this.service.createTournament(data);
    }

    public final void createTournament(TournamentQuery data, Callback<Tournament> onSuccess,
                                       Callback<DataAccessException> onFailure) {
        this.service.createTournament(data, onSuccess, onFailure);
    }

    public final Tournament updateTournament(Tournament tournament, TournamentQuery data) throws DataAccessException {
        return this.service.updateTournament(tournament, data);
    }

    public final void updateTournament(Tournament tournament, TournamentQuery data, Callback<Tournament> onSuccess,
                                       Callback<DataAccessException> onFailure) {
        this.service.updateTournament(tournament, data, onSuccess, onFailure);
    }

    public final Tournament deleteTournament(Tournament tournament) throws DataAccessException {
        return this.service.deleteTournament(tournament);
    }

    public final void deleteTournament(Tournament tournament, Callback<Tournament> onSuccess,
                                       Callback<DataAccessException> onFailure) {
        this.service.deleteTournament(tournament, onSuccess, onFailure);
    }

    public final Tournament processCheckIns(Tournament tournament, boolean includeParticipants,
                                            boolean includeMatches) throws DataAccessException {
        return this.service.processCheckIns(tournament, includeParticipants, includeMatches);
    }

    public final Tournament processCheckIns(Tournament tournament) throws DataAccessException {
        return processCheckIns(tournament, false, false);
    }

    public final void processCheckIns(Tournament tournament, boolean includeParticipants, boolean includeMatches,
                                      Callback<Tournament> onSuccess, Callback<DataAccessException> onFailure) {
        this.service.processCheckIns(tournament, includeParticipants, includeMatches, onSuccess, onFailure);
    }

    public final void processCheckIns(Tournament tournament, Callback<Tournament> onSuccess,
                                      Callback<DataAccessException> onFailure) {
        processCheckIns(tournament, false, false, onSuccess, onFailure);
    }

    public final Tournament abortCheckIn(Tournament tournament, boolean includeParticipants,
                                         boolean includeMatches) throws DataAccessException {
        return this.service.abortCheckIn(tournament, includeParticipants, includeMatches);
    }

    public final Tournament abortCheckIn(Tournament tournament) throws DataAccessException {
        return abortCheckIn(tournament, false, false);
    }

    public final void abortCheckIn(Tournament tournament, boolean includeParticipants, boolean includeMatches,
                                   Callback<Tournament> onSuccess, Callback<DataAccessException> onFailure) {
        this.service.abortCheckIn(tournament, includeParticipants, includeMatches, onSuccess, onFailure);
    }

    public final void abortCheckIn(Tournament tournament, Callback<Tournament> onSuccess,
                                   Callback<DataAccessException> onFailure) {
        abortCheckIn(tournament, false, false, onSuccess, onFailure);
    }

    public final Tournament startTournament(Tournament tournament, boolean includeParticipants,
                                            boolean includeMatches) throws DataAccessException {
        return this.service.startTournament(tournament, includeParticipants, includeMatches);
    }

    public final Tournament startTournament(Tournament tournament) throws DataAccessException {
        return startTournament(tournament, false, false);
    }

    public final void startTournament(Tournament tournament, boolean includeParticipants, boolean includeMatches,
                                      Callback<Tournament> onSuccess, Callback<DataAccessException> onFailure) {
        this.service.startTournament(tournament, includeParticipants, includeMatches, onSuccess, onFailure);
    }

    public final void startTournament(Tournament tournament, Callback<Tournament> onSuccess,
                                      Callback<DataAccessException> onFailure) {
        startTournament(tournament, false, false, onSuccess, onFailure);
    }

    public final Tournament finalizeTournament(Tournament tournament, boolean includeParticipants,
                                               boolean includeMatches) throws DataAccessException {
        return this.service.finalizeTournament(tournament, includeParticipants, includeMatches);
    }

    public final Tournament finalizeTournament(Tournament tournament) throws DataAccessException {
        return finalizeTournament(tournament, false, false);
    }

    public final void finalizeTournament(Tournament tournament, boolean includeParticipants, boolean includeMatches,
                                         Callback<Tournament> onSuccess, Callback<DataAccessException> onFailure) {
        this.service.finalizeTournament(tournament, includeParticipants, includeMatches, onSuccess, onFailure);
    }

    public final void finalizeTournament(Tournament tournament, Callback<Tournament> onSuccess,
                                         Callback<DataAccessException> onFailure) {
        finalizeTournament(tournament, false, false, onSuccess, onFailure);
    }

    public final Tournament resetTournament(Tournament tournament, boolean includeParticipants,
                                            boolean includeMatches) throws DataAccessException {
        return this.service.resetTournament(tournament, includeParticipants, includeMatches);
    }

    public final Tournament resetTournament(Tournament tournament) throws DataAccessException {
        return resetTournament(tournament, false, false);
    }

    public final void resetTournament(Tournament tournament, boolean includeParticipants, boolean includeMatches,
                                      Callback<Tournament> onSuccess, Callback<DataAccessException> onFailure) {
        this.service.resetTournament(tournament, includeParticipants, includeMatches, onSuccess, onFailure);
    }

    public final void resetTournament(Tournament tournament, Callback<Tournament> onSuccess,
                                      Callback<DataAccessException> onFailure) {
        resetTournament(tournament, false, false, onSuccess, onFailure);
    }

    public final Tournament openTournamentForPredictions(Tournament tournament, boolean includeParticipants,
                                                         boolean includeMatches) throws DataAccessException {
        return this.service.openTournamentForPredictions(tournament, includeParticipants, includeMatches);
    }

    public final Tournament openTournamentForPredictions(Tournament tournament) throws DataAccessException {
        return openTournamentForPredictions(tournament, false, false);
    }

    public final void openTournamentForPredictions(Tournament tournament, boolean includeParticipants,
                                                   boolean includeMatches, Callback<Tournament> onSuccess,
                                                   Callback<DataAccessException> onFailure) {
        this.service.openTournamentForPredictions(tournament, includeParticipants, includeMatches, onSuccess, onFailure);
    }

    public final void openTournamentForPredictions(Tournament tournament, Callback<Tournament> onSuccess,
                                                   Callback<DataAccessException> onFailure) {
        openTournamentForPredictions(tournament, false, false, onSuccess, onFailure);
    }

    public final List<Participant> getParticipants(Tournament tournament) throws DataAccessException {
        return this.service.getParticipants(tournament);
    }

    public final void getParticipants(Tournament tournament, Callback<List<Participant>> onSuccess,
                                      Callback<DataAccessException> onFailure) {
        this.service.getParticipants(tournament, onSuccess, onFailure);
    }

    public final Participant getParticipant(Tournament tournament, long participantId,
                                            boolean includeMatches) throws DataAccessException {
        return this.service.getParticipant(tournament, participantId, includeMatches);
    }

    public final Participant getParticipant(Tournament tournament, long participantId) throws DataAccessException {
        return this.service.getParticipant(tournament, participantId, false);
    }

    public final void getParticipant(Tournament tournament, long participantId, boolean includeMatches,
                                     Callback<Participant> onSuccess, Callback<DataAccessException> onFailure) {
        this.service.getParticipant(tournament, participantId, includeMatches, onSuccess, onFailure);
    }

    public final void getParticipant(Tournament tournament, long participantId, Callback<Participant> onSuccess,
                                     Callback<DataAccessException> onFailure) {
        this.service.getParticipant(tournament, participantId, false, onSuccess, onFailure);
    }

    public final Participant addParticipant(Tournament tournament, ParticipantQuery data) throws DataAccessException {
        return this.service.addParticipant(tournament, data);
    }

    public final void addParticipant(Tournament tournament, ParticipantQuery data, Callback<Participant> onSuccess,
                                     Callback<DataAccessException> onFailure) {
        this.service.addParticipant(tournament, data, onSuccess, onFailure);
    }

    public final List<Participant> bulkAddParticipants(Tournament tournament, List<ParticipantQuery> data) throws DataAccessException {
        return this.service.bulkAddParticipants(tournament, data);
    }

    public final void bulkAddParticipants(Tournament tournament, List<ParticipantQuery> data,
                                          Callback<List<Participant>> onSuccess,
                                          Callback<DataAccessException> onFailure) {
        this.service.bulkAddParticipants(tournament, data, onSuccess, onFailure);
    }

    public final Participant updateParticipant(Participant participant, ParticipantQuery data) throws DataAccessException {
        return this.service.updateParticipant(participant, data);
    }

    public final void updateParticipant(Participant participant, ParticipantQuery data, Callback<Participant> onSuccess,
                                        Callback<DataAccessException> onFailure) {
        this.service.updateParticipant(participant, data, onSuccess, onFailure);
    }

    public final Participant checkInParticipant(Participant participant) throws DataAccessException {
        return this.service.checkInParticipant(participant);
    }

    public final void checkInParticipant(Participant participant, Callback<Participant> onSuccess,
                                         Callback<DataAccessException> onFailure) {
        this.service.checkInParticipant(participant, onSuccess, onFailure);
    }

    public final Participant undoCheckInParticipant(Participant participant) throws DataAccessException {
        return this.service.undoCheckInParticipant(participant);
    }

    public final void undoCheckInParticipant(Participant participant, Callback<Participant> onSuccess,
                                             Callback<DataAccessException> onFailure) {
        this.service.undoCheckInParticipant(participant, onSuccess, onFailure);
    }

    public final Participant deleteParticipant(Participant participant) throws DataAccessException {
        return this.service.deleteParticipant(participant);
    }

    public final void deleteParticipant(Participant participant, Callback<Participant> onSuccess,
                                        Callback<DataAccessException> onFailure) {
        this.service.deleteParticipant(participant, onSuccess, onFailure);
    }

    public final List<Participant> randomizeParticipants(Tournament tournament) throws DataAccessException {
        return this.service.randomizeParticipants(tournament);
    }

    public final void randomizeParticipants(Tournament tournament, Callback<List<Participant>> onSuccess,
                                            Callback<DataAccessException> onFailure) {
        this.service.randomizeParticipants(tournament, onSuccess, onFailure);
    }

    public final List<Match> getMatches(Tournament tournament, Participant participant,
                                        MatchState state) throws DataAccessException {
        return this.service.getMatches(tournament, participant, state);
    }

    public final List<Match> getMatches(Tournament tournament, Participant participant) throws DataAccessException {
        return getMatches(tournament, participant, null);
    }

    public final List<Match> getMatches(Tournament tournament) throws DataAccessException {
        return getMatches(tournament, (Participant) null, null);
    }

    public final void getMatches(Tournament tournament, Participant participant, MatchState state,
                                 Callback<List<Match>> onSuccess, Callback<DataAccessException> onFailure) {
        this.service.getMatches(tournament, participant, state, onSuccess, onFailure);
    }

    public final void getMatches(Tournament tournament, Participant participant, Callback<List<Match>> onSuccess,
                                 Callback<DataAccessException> onFailure) {
        getMatches(tournament, participant, null, onSuccess, onFailure);
    }

    public final void getMatches(Tournament tournament, Callback<List<Match>> onSuccess,
                                 Callback<DataAccessException> onFailure) {
        getMatches(tournament, null, null, onSuccess, onFailure);
    }

    public final Match getMatch(Tournament tournament, long matchId, boolean includeAttachments) throws DataAccessException {
        return this.service.getMatch(tournament, matchId, includeAttachments);
    }

    public final Match getMatch(Tournament tournament, long matchId) throws DataAccessException {
        return getMatch(tournament, matchId, false);
    }

    public final void getMatch(Tournament tournament, long matchId, boolean includeAttachments, Callback<Match> onSuccess,
                               Callback<DataAccessException> onFailure) {
        this.service.getMatch(tournament, matchId, includeAttachments, onSuccess, onFailure);
    }


    public final void getMatch(Tournament tournament, long matchId, Callback<Match> onSuccess,
                               Callback<DataAccessException> onFailure) {
        getMatch(tournament, matchId, false, onSuccess, onFailure);
    }

    public final Match updateMatch(Match match, MatchQuery data) throws DataAccessException {
        return this.service.updateMatch(match, data);
    }

    public final void updateMatch(Match match, MatchQuery data, Callback<Match> onSuccess,
                                  Callback<DataAccessException> onFailure) {
        this.service.updateMatch(match, data, onSuccess, onFailure);
    }

    public final Match markMatchAsUnderway(Match match) throws DataAccessException {
        return this.service.markMatchAsUnderway(match);
    }

    public final void markMatchAsUnderway(Match match, Callback<Match> onSuccess, Callback<DataAccessException> onFailure) {
        this.service.markMatchAsUnderway(match, onSuccess, onFailure);
    }

    public final Match unmarkMatchAsUnderway(Match match) throws DataAccessException {
        return this.service.unmarkMatchAsUnderway(match);
    }

    public final void unmarkMatchAsUnderway(Match match, Callback<Match> onSuccess, Callback<DataAccessException> onFailure) {
        this.service.unmarkMatchAsUnderway(match, onSuccess, onFailure);
    }

    public final Match reopenMatch(Match match) throws DataAccessException {
        return this.service.reopenMatch(match);
    }

    public final void reopenMatch(Match match, Callback<Match> onSuccess, Callback<DataAccessException> onFailure) {
        this.service.reopenMatch(match, onSuccess, onFailure);
    }

    public final List<Attachment> getAttachments(Match match) throws DataAccessException {
        return this.service.getAttachments(match);
    }

    public final void getAttachments(Match match, Callback<List<Attachment>> onSuccess, Callback<DataAccessException> onFailure) {
        this.service.getAttachments(match, onSuccess, onFailure);
    }

    public final Attachment getAttachment(Match match, long attachmentId) throws DataAccessException {
        return this.service.getAttachment(match, attachmentId);
    }

    public final void getAttachment(Match match, long attachmentId, Callback<Attachment> onSuccess,
                                    Callback<DataAccessException> onFailure) {
        this.service.getAttachment(match, attachmentId, onSuccess, onFailure);
    }

    public final Attachment createAttachment(Match match, AttachmentQuery data) throws DataAccessException {
        return this.service.createAttachment(match, data);
    }

    public final void createAttachment(Match match, AttachmentQuery data, Callback<Attachment> onSuccess,
                                       Callback<DataAccessException> onFailure) {
        this.service.createAttachment(match, data, onSuccess, onFailure);
    }

    public final Attachment updateAttachment(Match match, Attachment attachment, AttachmentQuery data) throws DataAccessException {
        return this.service.updateAttachment(match, attachment, data);
    }

    public final void updateAttachment(Match match, Attachment attachment, AttachmentQuery data, Callback<Attachment> onSuccess,
                                       Callback<DataAccessException> onFailure) {
        this.service.updateAttachment(match, attachment, data, onSuccess, onFailure);
    }

    public final Attachment deleteAttachment(Match match, Attachment attachment) throws DataAccessException {
        return this.service.deleteAttachment(match, attachment);
    }

    public final void deleteAttachment(Match match, Attachment attachment, Callback<Attachment> onSuccess,
                                       Callback<DataAccessException> onFailure) {
        this.service.deleteAttachment(match, attachment, onSuccess, onFailure);
    }
}
