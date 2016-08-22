package com.exsoloscript.challonge.handler.retrofit;

import com.exsoloscript.challonge.model.Attachment;
import com.exsoloscript.challonge.model.query.AttachmentQuery;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Match related API bindings
 *
 * @author EXSolo
 * @version 20160822.1
 */
public interface RetrofitAttachmentHandler {

    /**
     * Retrieve a single match attachment record.
     *
     * @param tournament   Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                     If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                     (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId      The match's unique ID
     * @param attachmentId The attachment's unique ID
     * @return Call
     */
    @GET("tournaments/{tournament}/matches/{match_id}/attachments/{attachment_id}.json")
    Call<Attachment> getAttachment(@Path("tournament") String tournament,
                                   @Path("match_id") int matchId,
                                   @Path("attachment_id") int attachmentId);

    /**
     * Retrieve a match's attachments.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId    The match's unique ID
     * @return Call
     */
    @GET("tournaments/{tournament}/matches/{match_id}/attachments.json")
    Call<List<Attachment>> getAttachments(@Path("tournament") String tournament,
                                          @Path("match_id") int matchId);

    /**
     * Add a file, link, or text attachment to a match. NOTE: The associated tournament's
     * "accept_attachments" attribute must be true for this action to succeed.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId    The match's unique ID
     * @param attachment The attachment data
     * @return Call
     */
    @POST("tournaments/{tournament}/matches/{match_id}/attachments.json")
    Call<Attachment> createAttachment(@Path("tournament") String tournament,
                                      @Path("match_id") int matchId,
                                      @Body AttachmentQuery attachment);

//    @Multipart
//    @POST("tournaments/{tournament}/matches/{match_id}/attachments.json")
//    Call<Attachment> createAttachment(@Path("tournament") String tournament,
//                                      @Path("match_id") int matchId,
//                                      @Part("asset") RequestBody asset,
//                                      @Part("url") RequestBody url,
//                                      @Part("description") RequestBody description);

    /**
     * Update the attributes of a match attachment.
     * <p>
     * Sending the asset does neither work with base64 nor with a multipart-form-data request
     *
     * @param tournament   Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                     If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                     (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId      The match's unique ID
     * @param attachmentId The attachment's unique ID
     * @param attachment   The attachment data
     * @return Call
     */
    @PUT("tournaments/{tournament}/matches/{match_id}/attachments/{attachment_id}.json")
    Call<Attachment> updateAttachment(@Path("tournament") String tournament,
                                      @Path("match_id") int matchId,
                                      @Path("attachment_id") int attachmentId,
                                      @Body AttachmentQuery attachment);

    /**
     * Delete a match attachment.
     *
     * @param tournament   Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                     If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                     (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId      The match's unique ID
     * @param attachmentId The attachment's unique ID
     * @return Call
     */
    @DELETE("tournaments/{tournament}/matches/{match_id}/attachments/{attachment_id}.json")
    Call<Attachment> deleteAttachment(@Path("tournament") String tournament,
                                      @Path("match_id") int matchId,
                                      @Path("attachment_id") int attachmentId);
}
