/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 EXSolo <exsoloscripter at gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.exsoloscript.challonge.handler.retrofit;

import com.exsoloscript.challonge.model.Attachment;
import com.exsoloscript.challonge.model.query.AttachmentQuery;
import okhttp3.MultipartBody;
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
     * <p>
     * At least 1 of the 3 optional parameters (asset, url or description in the query object) must be provided.
     * Files up to 25MB are allowed for tournaments hosted by Challonge Premier subscribers.
     *
     * @param tournament  Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                    If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                    (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId     The match's unique ID
     * @param asset       A file upload (250KB max, no more than 4 attachments per match). If provided, the url parameter will be ignored.
     * @param url         A web URL
     * @param description Text to describe the file or URL attachment, or this can simply be standalone text.
     * @return Call
     */
//    @POST("tournaments/{tournament}/matches/{match_id}/attachments.json")
//    Call<Attachment> createAttachment(@Path("tournament") String tournament,
//                                      @Path("match_id") int matchId,
//                                      @Body AttachmentQuery attachment);
    @Multipart
    @POST("tournaments/{tournament}/matches/{match_id}/attachments.json")
    Call<Attachment> createAttachment(@Path("tournament") String tournament,
                                      @Path("match_id") int matchId,
                                      @Part MultipartBody.Part asset,
                                      @Part MultipartBody.Part url,
                                      @Part MultipartBody.Part description);

    /**
     * Update the attributes of a match attachment.
     * <p>
     * Sending the asset does neither work with base64 nor with a multipart-form-data request
     * </p>
     * At least 1 of the 3 optional parameters (asset, url or description in the query object) must be provided.
     * Files up to 25MB are allowed for tournaments hosted by Challonge Premier subscribers.
     *
     * @param tournament   Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                     If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                     (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId      The match's unique ID
     * @param attachmentId The attachment's unique ID
     * @param asset       A file upload (250KB max, no more than 4 attachments per match). If provided, the url parameter will be ignored.
     * @param url         A web URL
     * @param description Text to describe the file or URL attachment.
     * @return Call
     */
    @Multipart
    @PUT("tournaments/{tournament}/matches/{match_id}/attachments/{attachment_id}.json")
    Call<Attachment> updateAttachment(@Path("tournament") String tournament,
                                      @Path("match_id") int matchId,
                                      @Path("attachment_id") int attachmentId,
                                      @Part MultipartBody.Part asset,
                                      @Part MultipartBody.Part url,
                                      @Part MultipartBody.Part description);

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
