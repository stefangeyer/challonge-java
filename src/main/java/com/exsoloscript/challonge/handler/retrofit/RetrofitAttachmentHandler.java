package com.exsoloscript.challonge.handler.retrofit;

import com.exsoloscript.challonge.model.Attachment;
import com.exsoloscript.challonge.model.query.AttachmentQuery;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface RetrofitAttachmentHandler {

    @GET("v1/tournaments/{tournament}/matches/{match_id}/attachments/{attachment_id}.json")
    Call<Attachment> getAttachment(@Path("tournament") String tournamentName,
                                   @Path("match_id") int matchId,
                                   @Path("attachment_id") int attachmentId);

    @GET("v1/tournaments/{tournament}/matches/{match_id}/attachments.json")
    Call<List<Attachment>> getAttachments(@Path("tournament") String tournamentName,
                                          @Path("match_id") int matchId);

    @Multipart
    @POST("v1/tournaments/{tournament}/matches/{match_id}/attachments.json")
    Call<Attachment> createAttachment(@Path("tournament") String tournamentName,
                                      @Path("match_id") int matchId,
                                      @Part("asset") RequestBody asset,
                                      @Part("url") RequestBody url,
                                      @Part("description") RequestBody description);

    @PUT("v1/tournaments/{tournament}/matches/{match_id}/attachments/{attachment_id}.json")
    // TODO use multipart to fix?
    Call<Attachment> updateAttachment(@Path("tournament") String tournamentName,
                                      @Path("match_id") int matchId,
                                      @Path("attachment_id") int attachmentId,
                                      @Body AttachmentQuery attachment);

    @DELETE("v1/tournaments/{tournament}/matches/{match_id}/attachments/{attachment_id}.json")
    Call<Attachment> deleteAttachment(@Path("tournament") String tournamentName,
                                      @Path("match_id") int matchId,
                                      @Path("attachment_id") int attachmentId);
}
