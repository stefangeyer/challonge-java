package at.stefangeyer.challonge.service.implementation

import at.stefangeyer.challonge.model.Attachment
import at.stefangeyer.challonge.model.Match
import at.stefangeyer.challonge.model.query.AttachmentQuery
import at.stefangeyer.challonge.rest.AttachmentRestClient
import at.stefangeyer.challonge.service.AttachmentService

/**
 * Attachment Service Implementation
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
class SimpleAttachmentService(private val restClient: AttachmentRestClient): AttachmentService {

    override fun getAttachments(match: Match): List<Attachment> =
            this.restClient.getAttachments(match.tournamentId.toString(), match.id)

    override fun getAttachment(match: Match, attachmentId: Long): Attachment =
            this.restClient.getAttachment(match.tournamentId.toString(), match.id, attachmentId)

    override fun createAttachment(match: Match, data: AttachmentQuery): Attachment =
            this.restClient.createAttachment(match.tournamentId.toString(), match.id, data)

    override fun updateAttachment(match: Match, attachment: Attachment, data: AttachmentQuery): Attachment =
            this.restClient.updateAttachment(match.tournamentId.toString(), match.id, attachment.id, data)

    override fun deleteAttachment(match: Match, attachment: Attachment): Attachment =
            this.restClient.deleteAttachment(match.tournamentId.toString(), match.id, attachment.id)
}