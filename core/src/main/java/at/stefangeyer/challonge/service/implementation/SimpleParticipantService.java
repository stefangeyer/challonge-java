package at.stefangeyer.challonge.service.implementation;

import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.query.ParticipantQuery;
import at.stefangeyer.challonge.model.query.wrapper.ParticipantQueryListWrapper;
import at.stefangeyer.challonge.model.query.wrapper.ParticipantQueryWrapper;
import at.stefangeyer.challonge.model.wrapper.ParticipantWrapper;
import at.stefangeyer.challonge.rest.ParticipantRestClient;
import at.stefangeyer.challonge.service.ParticipantService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Participant Service Implementation
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
public class SimpleParticipantService implements ParticipantService {

    private ParticipantRestClient restClient;

    public SimpleParticipantService(ParticipantRestClient restClient) {
        this.restClient = restClient;
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
}
