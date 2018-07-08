package at.stefangeyer.challonge.rest.implementation.retrofit

import at.stefangeyer.challonge.exception.DataAccessException
import at.stefangeyer.challonge.model.Attachment
import at.stefangeyer.challonge.model.query.AttachmentQuery
import at.stefangeyer.challonge.rest.AttachmentRestClient
import at.stefangeyer.challonge.async.Callback
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

    override fun getAttachments(tournament: String, matchId: Long): List<Attachment> {
        val response = this.challongeRetrofit.getAttachments(tournament, matchId).execute()
        return parseResponse("GetAttachments", response)
    }

    override fun getAttachments(tournament: String, matchId: Long, onSuccess: Callback<List<Attachment>>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.getAttachments(tournament, matchId).enqueue(object : retrofit2.Callback<List<Attachment>>{
            override fun onFailure(call: Call<List<Attachment>>, t: Throwable) {
                onFailure(DataAccessException("GetAttachments request was not successful", t))
            }

            override fun onResponse(call: Call<List<Attachment>>, response: Response<List<Attachment>>) {
                onSuccess(parseResponse("GetAttachments", response))
            }
        })
    }

    override fun getAttachment(tournament: String, matchId: Long, attachmentId: Long): Attachment {
        val response = this.challongeRetrofit.getAttachment(tournament, matchId, attachmentId).execute()
        return parseResponse("GetAttachment", response)
    }

    override fun getAttachment(tournament: String, matchId: Long, attachmentId: Long, onSuccess: Callback<Attachment>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.getAttachment(tournament, matchId, attachmentId).enqueue(object : retrofit2.Callback<Attachment>{
            override fun onFailure(call: Call<Attachment>, t: Throwable) {
                onFailure(DataAccessException("GetAttachment request was not successful", t))
            }

            override fun onResponse(call: Call<Attachment>, response: Response<Attachment>) {
                onSuccess(parseResponse("GetAttachment", response))
            }
        })
    }

    override fun createAttachment(tournament: String, matchId: Long, attachment: AttachmentQuery): Attachment {
        val asset = createAssetPart(attachment)
        val url = createUrlPart(attachment)
        val desc = createDescriptionPart(attachment)

        val response = this.challongeRetrofit.createAttachment(tournament, matchId, asset, url, desc).execute()
        return parseResponse("CreateAttachment", response)
    }

    override fun createAttachment(tournament: String, matchId: Long, attachment: AttachmentQuery, onSuccess: Callback<Attachment>, onFailure: Callback<DataAccessException>) {
        val asset = createAssetPart(attachment)
        val url = createUrlPart(attachment)
        val desc = createDescriptionPart(attachment)

        this.challongeRetrofit.createAttachment(tournament, matchId, asset, url, desc).enqueue(object : retrofit2.Callback<Attachment>{
            override fun onFailure(call: Call<Attachment>, t: Throwable) {
                onFailure(DataAccessException("CreateAttachment request was not successful", t))
            }

            override fun onResponse(call: Call<Attachment>, response: Response<Attachment>) {
                onSuccess(parseResponse("CreateAttachment", response))
            }
        })
    }

    override fun updateAttachment(tournament: String, matchId: Long, attachmentId: Long, attachment: AttachmentQuery): Attachment {
        val asset = createAssetPart(attachment)
        val url = createUrlPart(attachment)
        val desc = createDescriptionPart(attachment)

        val response = this.challongeRetrofit.updateAttachment(tournament, matchId, attachmentId, asset, url, desc).execute()
        return parseResponse("UpdateAttachment", response)
    }

    override fun updateAttachment(tournament: String, matchId: Long, attachmentId: Long, attachment: AttachmentQuery, onSuccess: Callback<Attachment>, onFailure: Callback<DataAccessException>) {
        val asset = createAssetPart(attachment)
        val url = createUrlPart(attachment)
        val desc = createDescriptionPart(attachment)

        this.challongeRetrofit.createAttachment(tournament, matchId, asset, url, desc).enqueue(object : retrofit2.Callback<Attachment>{
            override fun onFailure(call: Call<Attachment>, t: Throwable) {
                onFailure(DataAccessException("CreateAttachment request was not successful", t))
            }

            override fun onResponse(call: Call<Attachment>, response: Response<Attachment>) {
                onSuccess(parseResponse("CreateAttachment", response))
            }
        })
    }

    override fun deleteAttachment(tournament: String, matchId: Long, attachmentId: Long): Attachment {
        val response = this.challongeRetrofit.deleteAttachment(tournament, matchId, attachmentId).execute()
        return parseResponse("DeleteAttachment", response)
    }

    override fun deleteAttachment(tournament: String, matchId: Long, attachmentId: Long, onSuccess: Callback<Attachment>, onFailure: Callback<DataAccessException>) {
        this.challongeRetrofit.getAttachment(tournament, matchId, attachmentId).enqueue(object : retrofit2.Callback<Attachment>{
            override fun onFailure(call: Call<Attachment>, t: Throwable) {
                onFailure(DataAccessException("DeleteAttachment request was not successful", t))
            }

            override fun onResponse(call: Call<Attachment>, response: Response<Attachment>) {
                onSuccess(parseResponse("DeleteAttachment", response))
            }
        })
    }

    private fun <T> parseResponse(action: String, response: Response<T>) : T {
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
            MultipartBody.Part.createFormData("match_attachment[asset]", attachment.asset.name,
                    RequestBody.create(
                            if (attachment.assetMimeType != null) MediaType.parse(attachment.assetMimeType!!)
                            else null, attachment.asset))
        } else
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