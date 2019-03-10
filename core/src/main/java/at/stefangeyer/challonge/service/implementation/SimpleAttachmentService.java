package at.stefangeyer.challonge.service.implementation;

import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.query.AttachmentQuery;
import at.stefangeyer.challonge.model.wrapper.AttachmentWrapper;
import at.stefangeyer.challonge.rest.AttachmentRestClient;
import at.stefangeyer.challonge.service.AttachmentService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Attachment Service Implementation
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
public class SimpleAttachmentService implements AttachmentService {

    private AttachmentRestClient restClient;

    public SimpleAttachmentService(AttachmentRestClient restClient) {
        this.restClient = restClient;
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
