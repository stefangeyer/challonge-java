package at.stefangeyer.challonge.service;

import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.query.AttachmentQuery;

import java.util.List;

/**
 * Attachment Service Definitions
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
public interface AttachmentService {

    /**
     * Retrieve a match's attachments.
     *
     * @param match The match to get the attachments from. Must contain the tournament- and match id
     * @return The match attachments
     * @throws DataAccessException Exchange with the rest api failed
     */
    List<Attachment> getAttachments(Match match) throws DataAccessException;

    /**
     * Retrieve a match's attachments.
     *
     * @param match     The match to get the attachments from. Must contain the tournament- and match id
     * @param onSuccess Called with result if call was successful
     * @param onFailure Called with exception if call was not successful
     */
    void getAttachments(Match match, Callback<List<Attachment>> onSuccess, Callback<DataAccessException> onFailure);

    /**
     * Retrieve a single match attachment record.
     *
     * @param match        The match to get the attachment from. Must contain the tournament- and match id
     * @param attachmentId The attachment's unique ID
     * @return The requested attachment
     * @throws DataAccessException Exchange with the rest api failed
     */
    Attachment getAttachment(Match match, long attachmentId) throws DataAccessException;

    /**
     * Retrieve a single match attachment record.
     *
     * @param match        The match to get the attachment from. Must contain the tournament- and match id
     * @param attachmentId The attachment's unique ID
     * @param onSuccess    Called with result if call was successful
     * @param onFailure    Called with exception if call was not successful
     */
    void getAttachment(Match match, long attachmentId, Callback<Attachment> onSuccess,
                       Callback<DataAccessException> onFailure);

    /**
     * Add a file, link, or text attachment to a match. NOTE: The associated tournament's
     * "accept_attachments" attribute must be true for this action to succeed.
     * <p>
     * At least 1 of the 3 optional parameters (asset, url or description in the enumeration object) must be provided.
     * Files up to 25MB are allowed for tournaments hosted by Challonge Premier subscribers.
     *
     * @param match The match to create the attachment for. Must contain the tournament- and match id
     * @param data  The attachment to create
     * @return The created attachment
     * @throws DataAccessException Exchange with the rest api failed
     */
    Attachment createAttachment(Match match, AttachmentQuery data) throws DataAccessException;

    /**
     * Add a file, link, or text attachment to a match. NOTE: The associated tournament's
     * "accept_attachments" attribute must be true for this action to succeed.
     * <p>
     * At least 1 of the 3 optional parameters (asset, url or description in the enumeration object) must be provided.
     * Files up to 25MB are allowed for tournaments hosted by Challonge Premier subscribers.
     *
     * @param match     The match to create the attachment for. Must contain the tournament- and match id
     * @param data      The attachment to create
     * @param onSuccess Called with result if call was successful
     * @param onFailure Called with exception if call was not successful
     */
    void createAttachment(Match match, AttachmentQuery data, Callback<Attachment> onSuccess,
                          Callback<DataAccessException> onFailure);

    /**
     * Update the attributes of a match attachment.
     * <p>
     * Sending the asset does neither work with base64 nor with a multipart-form-data request
     * <p>
     * At least 1 of the 3 optional parameters (asset, url or description in the enumeration object) must be provided.
     * Files up to 25MB are allowed for tournaments hosted by Challonge Premier subscribers.
     *
     * @param match      The match to delete the attachment from. Must contain the tournament- and match id
     * @param attachment The attachment to update. Must contain the tournament- and match id
     * @param data       The attachment to update
     * @return The updated attachment
     * @throws DataAccessException Exchange with the rest api failed
     */
    Attachment updateAttachment(Match match, Attachment attachment, AttachmentQuery data) throws DataAccessException;

    /**
     * Update the attributes of a match attachment.
     * <p>
     * Sending the asset does neither work with base64 nor with a multipart-form-data request
     * <p>
     * At least 1 of the 3 optional parameters (asset, url or description in the enumeration object) must be provided.
     * Files up to 25MB are allowed for tournaments hosted by Challonge Premier subscribers.
     *
     * @param match      The match to delete the attachment from. Must contain the tournament- and match id
     * @param attachment The attachment to update. Must contain the tournament- and match id
     * @param data       The attachment to update
     * @param onSuccess  Called with result if call was successful
     * @param onFailure  Called with exception if call was not successful
     */
    void updateAttachment(Match match, Attachment attachment, AttachmentQuery data, Callback<Attachment> onSuccess,
                          Callback<DataAccessException> onFailure);

    /**
     * Delete a match attachment.
     *
     * @param match      The match to delete the attachment from. Must contain the tournament- and match id
     * @param attachment The attachment to delete. Must contain the tournament- and match id
     * @return The deleted attachment
     * @throws DataAccessException Exchange with the rest api failed
     */
    Attachment deleteAttachment(Match match, Attachment attachment) throws DataAccessException;

    /**
     * Delete a match attachment.
     *
     * @param match      The match to delete the attachment from. Must contain the tournament- and match id
     * @param attachment The attachment to delete. Must contain the tournament- and match id
     * @param onSuccess  Called with result if call was successful
     * @param onFailure  Called with exception if call was not successful
     */
    void deleteAttachment(Match match, Attachment attachment, Callback<Attachment> onSuccess,
                          Callback<DataAccessException> onFailure);
}
