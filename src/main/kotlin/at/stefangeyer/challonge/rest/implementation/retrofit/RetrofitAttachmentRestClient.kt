package at.stefangeyer.challonge.rest.implementation.retrofit

import at.stefangeyer.challonge.model.Attachment
import at.stefangeyer.challonge.model.query.AttachmentQuery
import at.stefangeyer.challonge.rest.AttachmentRestClient
import at.stefangeyer.challonge.rest.client.retrofit.ChallongeRetrofit
import at.stefangeyer.challonge.exception.DataAccessException
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody

/**
 * Retrofit implementation of the attachment rest client
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
class RetrofitAttachmentRestClient(private val challongeRetrofit: ChallongeRetrofit) : AttachmentRestClient {

    override fun getAttachments(tournament: String, matchId: Long): List<Attachment> {
        val response = this.challongeRetrofit.getAttachments(tournament, matchId).execute()

        if (!response.isSuccessful) {
            throw DataAccessException("GetAttachments request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }

    override fun getAttachment(tournament: String, matchId: Long, attachmentId: Long): Attachment {
        val response = this.challongeRetrofit.getAttachment(tournament, matchId, attachmentId).execute()

        if (!response.isSuccessful) {
            throw DataAccessException("GetAttachment request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }

    override fun createAttachment(tournament: String, matchId: Long, attachment: AttachmentQuery): Attachment {
        val asset = createAssetPart(attachment)
        val url = createUrlPart(attachment)
        val desc = createDescriptionPart(attachment)

        val response = this.challongeRetrofit.createAttachment(tournament, matchId, asset, url, desc).execute()

        if (!response.isSuccessful) {
            throw DataAccessException("CreateAttachment request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }

    override fun updateAttachment(tournament: String, matchId: Long, attachmentId: Long, attachment: AttachmentQuery): Attachment {
        val asset = createAssetPart(attachment)
        val url = createUrlPart(attachment)
        val desc = createDescriptionPart(attachment)

        val response = this.challongeRetrofit.updateAttachment(tournament, matchId, attachmentId, asset, url, desc).execute()

        if (!response.isSuccessful) {
            throw DataAccessException("UpdateAttachment request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }

    override fun deleteAttachment(tournament: String, matchId: Long, attachmentId: Long): Attachment {
        val response = this.challongeRetrofit.deleteAttachment(tournament, matchId, attachmentId).execute()

        if (!response.isSuccessful) {
            throw DataAccessException("DeleteAttachment request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }

    private fun createAssetPart(attachment: AttachmentQuery): MultipartBody.Part? {
        return if (attachment.asset != null)
            MultipartBody.Part.createFormData("match_attachment[asset]", attachment.asset.name,
                    RequestBody.create(MediaType.parse(attachment.getMimeType()), attachment.asset))
        else
            null
    }

    private fun createUrlPart(attachment: AttachmentQuery): MultipartBody.Part? {
        return if (attachment.url != null)
            MultipartBody.Part.createFormData("match_attachment[url]", attachment.url)
        else
            null
    }

    private fun createDescriptionPart(attachment: AttachmentQuery): MultipartBody.Part? {
        return if (attachment.description != null)
            MultipartBody.Part.createFormData("match_attachment[description]", attachment.description)
        else
            null
    }
}