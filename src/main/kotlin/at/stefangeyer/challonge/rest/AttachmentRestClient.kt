package at.stefangeyer.challonge.rest

import at.stefangeyer.challonge.model.Attachment
import at.stefangeyer.challonge.model.query.AttachmentQuery
import at.stefangeyer.challonge.rest.exception.DataAccessException

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
    fun getAttachments(tournament: String, matchId: Int): List<Attachment>

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
    fun getAttachment(tournament: String, matchId: Int, attachmentId: Int): Attachment

    /**
     * Add a file, link, or text attachment to a match. NOTE: The associated tournament's
     * "accept_attachments" attribute must be true for this action to succeed.
     *
     * At least 1 of the 3 optional parameters (asset, url or description in the query object) must be provided.
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
    fun createAttachment(tournament: String, matchId: Int, attachment: AttachmentQuery): Attachment

    /**
     * Update the attributes of a match attachment.
     *
     * Sending the asset does neither work with base64 nor with a multipart-form-data request
     *
     * At least 1 of the 3 optional parameters (asset, url or description in the query object) must be provided.
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
    fun updateAttachment(tournament: String, matchId: Int, attachmentId: Int, attachment: AttachmentQuery): Attachment

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
    fun deleteAttachment(tournament: String, matchId: Int, attachmentId: Int): Attachment
}