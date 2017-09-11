/*
 * Copyright 2017 Stefan Geyer <stefangeyer at outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.exsoloscript.challonge.handler.retrofit;

import com.exsoloscript.challonge.model.Attachment;
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
     * @param asset        A file upload (250KB max, no more than 4 attachments per match). If provided, the url parameter will be ignored.
     * @param url          A web URL
     * @param description  Text to describe the file or URL attachment.
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
