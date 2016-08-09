package com.exsoloscript.challonge.handler.retrofit;

import com.exsoloscript.challonge.model.Attachment;
import com.exsoloscript.challonge.model.AttachmentBase;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface RetrofitAttachmentHandler {

    @GET("/tournaments/{tournament}/matches/{match_id}/attachments/{attachment_id}.json")
    Call<Attachment> getAttachment(@Path("tournament") String tournamentName,
                                   @Query("match_id") int matchId,
                                   @Query("attachment_id") int attachmentId);

    @GET("/tournaments/{tournament}/matches/{match_id}/attachments.json")
    Call<List<Attachment>> getAttachments(@Path("tournament") String tournamentName,
                                          @Query("match_id") int matchId);

    @POST("tournaments/{tournament}/matches/{match_id}/attachments.json")
        // use multipart to fix?
    Call<Attachment> createAttachment(@Path("tournament") String tournamentName,
                                      @Query("match_id") int matchId,
                                      @Body AttachmentBase attachment);

    @PUT("/tournaments/{tournament}/matches/{match_id}/attachments/{attachment_id}.json")
        // use multipart to fix?
    Call<Attachment> updateAttachment(@Path("tournament") String tournamentName,
                                      @Query("match_id") int matchId,
                                      @Query("attachment_id") int attachmentId,
                                      @Body AttachmentBase attachment);

    @DELETE("/tournaments/{tournament}/matches/{match_id}/attachments/{attachment_id}.json")
    Call<Attachment> deleteAttachment(@Path("tournament") String tournamentName,
                                      @Query("match_id") int matchId,
                                      @Query("attachment_id") int attachmentId);
}
