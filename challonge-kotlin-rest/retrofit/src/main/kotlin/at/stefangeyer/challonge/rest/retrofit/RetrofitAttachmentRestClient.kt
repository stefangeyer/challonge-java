package at.stefangeyer.challonge.rest.retrofit

import at.stefangeyer.challonge.async.Callback
import at.stefangeyer.challonge.exception.DataAccessException
import at.stefangeyer.challonge.model.query.AttachmentQuery
import at.stefangeyer.challonge.model.wrapper.AttachmentWrapper
import at.stefangeyer.challonge.rest.AttachmentRestClient
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response

/**
 * Retrofit gson of the attachment rest client
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
class RetrofitAttachmentRestClient(private val challongeRetrofit: ChallongeRetrofit) : AttachmentRestClient {

    override fun getAttachments(tournament: String, matchId: Long): List<AttachmentWrapper> {
        val response = this.challongeRetrofit.getAttachments(tournament, matchId).execute()
        return parseResponse("GetAttachments", response)
    }

    override fun getAttachments(tournament: String, matchId: Long,
                                onSuccess: Callback<List<AttachmentWrapper>>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.getAttachments(tournament, matchId)
                .enqueue(object : retrofit2.Callback<List<AttachmentWrapper>> {
                    override fun onFailure(call: Call<List<AttachmentWrapper>>, t: Throwable) {
                        onFailure(DataAccessException("GetAttachments request was not successful", t))
                    }

                    override fun onResponse(call: Call<List<AttachmentWrapper>>, response: Response<List<AttachmentWrapper>>) {
                        onSuccess(parseResponse("GetAttachments", response))
                    }
                })
    }

    override fun getAttachment(tournament: String, matchId: Long, attachmentId: Long): AttachmentWrapper {
        val response = this.challongeRetrofit.getAttachment(tournament, matchId, attachmentId).execute()
        return parseResponse("GetAttachment", response)
    }

    override fun getAttachment(tournament: String, matchId: Long, attachmentId: Long,
                               onSuccess: Callback<AttachmentWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.getAttachment(tournament, matchId, attachmentId)
                .enqueue(object : retrofit2.Callback<AttachmentWrapper> {
                    override fun onFailure(call: Call<AttachmentWrapper>, t: Throwable) {
                        onFailure(DataAccessException("GetAttachment request was not successful", t))
                    }

                    override fun onResponse(call: Call<AttachmentWrapper>, response: Response<AttachmentWrapper>) {
                        onSuccess(parseResponse("GetAttachment", response))
                    }
                })
    }

    override fun createAttachment(tournament: String, matchId: Long, attachment: AttachmentQuery): AttachmentWrapper {
        val asset = createAssetPart(attachment)
        val url = createUrlPart(attachment)
        val desc = createDescriptionPart(attachment)

        val response = this.challongeRetrofit.createAttachment(tournament, matchId, asset, url, desc).execute()
        return parseResponse("CreateAttachment", response)
    }

    override fun createAttachment(tournament: String, matchId: Long, attachment: AttachmentQuery,
                                  onSuccess: Callback<AttachmentWrapper>, onFailure: Callback<DataAccessException>) {
        val asset = createAssetPart(attachment)
        val url = createUrlPart(attachment)
        val desc = createDescriptionPart(attachment)

        this.challongeRetrofit.createAttachment(tournament, matchId, asset, url, desc)
                .enqueue(object : retrofit2.Callback<AttachmentWrapper> {
                    override fun onFailure(call: Call<AttachmentWrapper>, t: Throwable) {
                        onFailure(DataAccessException("CreateAttachment request was not successful", t))
                    }

                    override fun onResponse(call: Call<AttachmentWrapper>, response: Response<AttachmentWrapper>) {
                        onSuccess(parseResponse("CreateAttachment", response))
                    }
                })
    }

    override fun updateAttachment(tournament: String, matchId: Long, attachmentId: Long, attachment: AttachmentQuery): AttachmentWrapper {
        val asset = createAssetPart(attachment)
        val url = createUrlPart(attachment)
        val desc = createDescriptionPart(attachment)

        val response = this.challongeRetrofit.updateAttachment(tournament, matchId, attachmentId, asset, url, desc).execute()
        return parseResponse("UpdateAttachment", response)
    }

    override fun updateAttachment(tournament: String, matchId: Long, attachmentId: Long, attachment: AttachmentQuery,
                                  onSuccess: Callback<AttachmentWrapper>, onFailure: Callback<DataAccessException>) {
        val asset = createAssetPart(attachment)
        val url = createUrlPart(attachment)
        val desc = createDescriptionPart(attachment)

        this.challongeRetrofit.createAttachment(tournament, matchId, asset, url, desc)
                .enqueue(object : retrofit2.Callback<AttachmentWrapper> {
                    override fun onFailure(call: Call<AttachmentWrapper>, t: Throwable) {
                        onFailure(DataAccessException("CreateAttachment request was not successful", t))
                    }

                    override fun onResponse(call: Call<AttachmentWrapper>, response: Response<AttachmentWrapper>) {
                        onSuccess(parseResponse("CreateAttachment", response))
                    }
                })
    }

    override fun deleteAttachment(tournament: String, matchId: Long, attachmentId: Long): AttachmentWrapper {
        val response = this.challongeRetrofit.deleteAttachment(tournament, matchId, attachmentId).execute()
        return parseResponse("DeleteAttachment", response)
    }

    override fun deleteAttachment(tournament: String, matchId: Long, attachmentId: Long,
                                  onSuccess: Callback<AttachmentWrapper>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.getAttachment(tournament, matchId, attachmentId)
                .enqueue(object : retrofit2.Callback<AttachmentWrapper> {
                    override fun onFailure(call: Call<AttachmentWrapper>, t: Throwable) {
                        onFailure(DataAccessException("DeleteAttachment request was not successful", t))
                    }

                    override fun onResponse(call: Call<AttachmentWrapper>, response: Response<AttachmentWrapper>) {
                        onSuccess(parseResponse("DeleteAttachment", response))
                    }
                })
    }

    private fun <T> parseResponse(action: String, response: Response<T>): T {
        if (!response.isSuccessful) {
            throw DataAccessException(action + " request was not successful (" +
                    response.code() + ") and returned: " + response.errorBody().toString())
        }

        val body = response.body()

        if (body != null) {
            return body
        }

        throw DataAccessException("Received response body was null")
    }

    private fun createAssetPart(attachment: AttachmentQuery): MultipartBody.Part? {
        return if (attachment.asset != null) {
            val asset = attachment.asset!!
            MultipartBody.Part.createFormData("match_attachment[asset]", asset.name,
                    RequestBody.create(
                            if (attachment.assetMimeType != null) MediaType.parse(attachment.assetMimeType!!)
                            else null, asset))
        } else
            null
    }

    private fun createUrlPart(attachment: AttachmentQuery): MultipartBody.Part? {
        return if (attachment.url != null) {
            val url = attachment.url!!
            MultipartBody.Part.createFormData("match_attachment[url]", url)
        } else
            null
    }

    private fun createDescriptionPart(attachment: AttachmentQuery): MultipartBody.Part? {
        return if (attachment.description != null) {
            val description = attachment.description!!
            MultipartBody.Part.createFormData("match_attachment[description]", description)
        } else
            null
    }
}