package at.stefangeyer.challonge.rest

import at.stefangeyer.challonge.async.Callback
import at.stefangeyer.challonge.exception.DataAccessException
import at.stefangeyer.challonge.model.query.AttachmentQuery
import at.stefangeyer.challonge.model.wrapper.AttachmentWrapper

/**
 * Attachment Rest Client Definition
 *
 * @author Stefan Geyer
 * @version 2018-06-29
 */
interface AttachmentRestClient {

    /**
     * Retrieve a match's attachments.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId    The match's unique ID
     * @throws DataAccessException Exchange with the rest api failed
     * @return The match attachments
     */
    @Throws(DataAccessException::class)
    fun getAttachments(tournament: String, matchId: Long): List<AttachmentWrapper>

    /**
     * Retrieve a match's attachments.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId    The match's unique ID
     * @param onSuccess  Called with result if call was successful
     * @param onFailure  Called with exception if call was not successful
     */
    fun getAttachments(tournament: String, matchId: Long, onSuccess: Callback<List<AttachmentWrapper>>,
                       onFailure: Callback<DataAccessException>)

    /**
     * Retrieve a single match attachment record.
     *
     * @param tournament   Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                     If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                     (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId      The match's unique ID
     * @param attachmentId The attachment's unique ID
     * @throws DataAccessException Exchange with the rest api failed
     * @return The requested attachment
     */
    @Throws(DataAccessException::class)
    fun getAttachment(tournament: String, matchId: Long, attachmentId: Long): AttachmentWrapper

    /**
     * Retrieve a single match attachment record.
     *
     * @param tournament   Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                     If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                     (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId      The match's unique ID
     * @param attachmentId The attachment's unique ID
     * @param onSuccess    Called with result if call was successful
     * @param onFailure    Called with exception if call was not successful
     */
    fun getAttachment(tournament: String, matchId: Long, attachmentId: Long, onSuccess: Callback<AttachmentWrapper>,
                      onFailure: Callback<DataAccessException>)

    /**
     * Add a file, link, or text attachment to a match. NOTE: The associated tournament's
     * "accept_attachments" attribute must be true for this action to succeed.
     *
     * At least 1 of the 3 optional parameters (asset, url or description in the enum object) must be provided.
     * Files up to 25MB are allowed for tournaments hosted by Challonge Premier subscribers.
     *
     * @param tournament  Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                    If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                    (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId     The match's unique ID
     * @param attachment  The attachment to create
     * @throws DataAccessException Exchange with the rest api failed
     * @return The created attachment
     */
    @Throws(DataAccessException::class)
    fun createAttachment(tournament: String, matchId: Long, attachment: AttachmentQuery): AttachmentWrapper

    /**
     * Add a file, link, or text attachment to a match. NOTE: The associated tournament's
     * "accept_attachments" attribute must be true for this action to succeed.
     *
     * At least 1 of the 3 optional parameters (asset, url or description in the enum object) must be provided.
     * Files up to 25MB are allowed for tournaments hosted by Challonge Premier subscribers.
     *
     * @param tournament  Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                    If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                    (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId     The match's unique ID
     * @param attachment  The attachment to create
     * @param onSuccess   Called with result if call was successful
     * @param onFailure   Called with exception if call was not successful
     */
    fun createAttachment(tournament: String, matchId: Long, attachment: AttachmentQuery, onSuccess: Callback<AttachmentWrapper>,
                         onFailure: Callback<DataAccessException>)

    /**
     * Update the attributes of a match attachment.
     *
     * Sending the asset does neither work with base64 nor with a multipart-form-data request
     *
     * At least 1 of the 3 optional parameters (asset, url or description in the enum object) must be provided.
     * Files up to 25MB are allowed for tournaments hosted by Challonge Premier subscribers.
     *
     * @param tournament   Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                     If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                     (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId      The match's unique ID
     * @param attachmentId The attachment's unique ID
     * @param attachment   The attachment to update
     * @throws DataAccessException Exchange with the rest api failed
     * @return The updated attachment
     */
    @Throws(DataAccessException::class)
    fun updateAttachment(tournament: String, matchId: Long, attachmentId: Long, attachment: AttachmentQuery): AttachmentWrapper

    /**
     * Update the attributes of a match attachment.
     *
     * Sending the asset does neither work with base64 nor with a multipart-form-data request
     *
     * At least 1 of the 3 optional parameters (asset, url or description in the enum object) must be provided.
     * Files up to 25MB are allowed for tournaments hosted by Challonge Premier subscribers.
     *
     * @param tournament   Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                     If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                     (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId      The match's unique ID
     * @param attachmentId The attachment's unique ID
     * @param attachment   The attachment to update
     * @param onSuccess    Called with result if call was successful
     * @param onFailure    Called with exception if call was not successful
     */
    fun updateAttachment(tournament: String, matchId: Long, attachmentId: Long, attachment: AttachmentQuery,
                         onSuccess: Callback<AttachmentWrapper>, onFailure: Callback<DataAccessException>)

    /**
     * Delete a match attachment.
     *
     * @param tournament   Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                     If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                     (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId      The match's unique ID
     * @param attachmentId The attachment's unique ID
     * @throws DataAccessException Exchange with the rest api failed
     * @return The deleted attachment
     */
    @Throws(DataAccessException::class)
    fun deleteAttachment(tournament: String, matchId: Long, attachmentId: Long): AttachmentWrapper

    /**
     * Delete a match attachment.
     *
     * @param tournament   Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                     If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                     (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId      The match's unique ID
     * @param attachmentId The attachment's unique ID
     * @param onSuccess    Called with result if call was successful
     * @param onFailure    Called with exception if call was not successful
     */
    fun deleteAttachment(tournament: String, matchId: Long, attachmentId: Long, onSuccess: Callback<AttachmentWrapper>,
                         onFailure: Callback<DataAccessException>)
}