package at.stefangeyer.challonge.service

import at.stefangeyer.challonge.async.Callback
import at.stefangeyer.challonge.exception.DataAccessException
import at.stefangeyer.challonge.model.Attachment
import at.stefangeyer.challonge.model.Match
import at.stefangeyer.challonge.model.query.AttachmentQuery

/**
 * Attachment Service Definitions
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
interface AttachmentService {

    /**
     * Retrieve a match's attachments.
     *
     * @param match    The match to get the attachments from. Must contain the tournament- and match id
     * @throws DataAccessException Exchange with the rest api failed
     * @return The match attachments
     */
    @Throws(DataAccessException::class)
    fun getAttachments(match: Match): List<Attachment>

    /**
     * Retrieve a match's attachments.
     *
     * @param match      The match to get the attachments from. Must contain the tournament- and match id
     * @param onSuccess  Called with result if call was successful
     * @param onFailure  Called with exception if call was not successful
     */
    fun getAttachments(match: Match, onSuccess: Callback<List<Attachment>>,
                       onFailure: Callback<DataAccessException>)

    /**
     * Retrieve a single match attachment record.
     *
     * @param match        The match to get the attachment from. Must contain the tournament- and match id
     * @param attachmentId The attachment's unique ID
     * @throws DataAccessException Exchange with the rest api failed
     * @return The requested attachment
     */
    @Throws(DataAccessException::class)
    fun getAttachment(match: Match, attachmentId: Long): Attachment

    /**
     * Retrieve a single match attachment record.
     *
     * @param match        The match to get the attachment from. Must contain the tournament- and match id
     * @param attachmentId The attachment's unique ID
     * @param onSuccess    Called with result if call was successful
     * @param onFailure    Called with exception if call was not successful
     */
    fun getAttachment(match: Match, attachmentId: Long, onSuccess: Callback<Attachment>,
                      onFailure: Callback<DataAccessException>)

    /**
     * Add a file, link, or text attachment to a match. NOTE: The associated tournament's
     * "accept_attachments" attribute must be true for this action to succeed.
     *
     * At least 1 of the 3 optional parameters (asset, url or description in the query object) must be provided.
     * Files up to 25MB are allowed for tournaments hosted by Challonge Premier subscribers.
     *
     * @param match       The match to create the attachment for. Must contain the tournament- and match id
     * @param data        The attachment to create
     * @throws DataAccessException Exchange with the rest api failed
     * @return The created attachment
     */
    @Throws(DataAccessException::class)
    fun createAttachment(match: Match, data: AttachmentQuery): Attachment

    /**
     * Add a file, link, or text attachment to a match. NOTE: The associated tournament's
     * "accept_attachments" attribute must be true for this action to succeed.
     *
     * At least 1 of the 3 optional parameters (asset, url or description in the query object) must be provided.
     * Files up to 25MB are allowed for tournaments hosted by Challonge Premier subscribers.
     *
     * @param match       The match to create the attachment for. Must contain the tournament- and match id
     * @param data        The attachment to create
     * @param onSuccess   Called with result if call was successful
     * @param onFailure   Called with exception if call was not successful
     */
    fun createAttachment(match: Match, data: AttachmentQuery, onSuccess: Callback<Attachment>,
                         onFailure: Callback<DataAccessException>)

    /**
     * Update the attributes of a match attachment.
     *
     * Sending the asset does neither work with base64 nor with a multipart-form-data request
     *
     * At least 1 of the 3 optional parameters (asset, url or description in the query object) must be provided.
     * Files up to 25MB are allowed for tournaments hosted by Challonge Premier subscribers.
     *
     * @param match        The match to delete the attachment from. Must contain the tournament- and match id
     * @param attachment   The attachment to update. Must contain the tournament- and match id
     * @param data         The attachment to update
     * @throws DataAccessException Exchange with the rest api failed
     * @return The updated attachment
     */
    @Throws(DataAccessException::class)
    fun updateAttachment(match: Match, attachment: Attachment, data: AttachmentQuery): Attachment

    /**
     * Update the attributes of a match attachment.
     *
     * Sending the asset does neither work with base64 nor with a multipart-form-data request
     *
     * At least 1 of the 3 optional parameters (asset, url or description in the query object) must be provided.
     * Files up to 25MB are allowed for tournaments hosted by Challonge Premier subscribers.
     *
     * @param match        The match to delete the attachment from. Must contain the tournament- and match id
     * @param attachment   The attachment to update. Must contain the tournament- and match id
     * @param data         The attachment to update
     * @param onSuccess    Called with result if call was successful
     * @param onFailure    Called with exception if call was not successful
     */
    fun updateAttachment(match: Match, attachment: Attachment, data: AttachmentQuery, onSuccess: Callback<Attachment>,
                         onFailure: Callback<DataAccessException>)

    /**
     * Delete a match attachment.
     *
     * @param match The match to delete the attachment from. Must contain the tournament- and match id
     * @param attachment The attachment to delete. Must contain the tournament- and match id
     * @throws DataAccessException Exchange with the rest api failed
     * @return The deleted attachment
     */
    @Throws(DataAccessException::class)
    fun deleteAttachment(match: Match, attachment: Attachment): Attachment

    /**
     * Delete a match attachment.
     *
     * @param match The match to delete the attachment from. Must contain the tournament- and match id
     * @param attachment The attachment to delete. Must contain the tournament- and match id
     * @param onSuccess    Called with result if call was successful
     * @param onFailure    Called with exception if call was not successful
     */
    fun deleteAttachment(match: Match, attachment: Attachment, onSuccess: Callback<Attachment>,
                         onFailure: Callback<DataAccessException>)
}