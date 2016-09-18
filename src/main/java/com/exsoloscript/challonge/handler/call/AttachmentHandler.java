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

package com.exsoloscript.challonge.handler.call;

import com.exsoloscript.challonge.handler.retrofit.RetrofitAttachmentHandler;
import com.exsoloscript.challonge.handler.retrofit.RetrofitServiceProvider;
import com.exsoloscript.challonge.model.Attachment;
import com.exsoloscript.challonge.model.exception.ChallongeException;
import com.exsoloscript.challonge.model.query.AttachmentQuery;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.util.List;

/**
 * Accessible API handler for attachments.
 *
 * @author EXSolo
 * @version 20160822.1
 */
@Singleton
public class AttachmentHandler {

    private RetrofitAttachmentHandler attachmentHandler;
    private RetrofitChallongeApiCallFactory factory;

    @Inject
    AttachmentHandler(RetrofitServiceProvider provider, RetrofitChallongeApiCallFactory factory) {
        this.attachmentHandler = provider.createService(RetrofitAttachmentHandler.class);
        this.factory = factory;
    }

    /**
     * @see RetrofitAttachmentHandler#getAttachments(String, int)
     */
    public ChallongeApiCall<List<Attachment>> getAttachments(String tournament, int matchId) throws IOException, ChallongeException {
        Validate.isTrue(StringUtils.isNotBlank(tournament), "Tournament string is required");
        Validate.notNull(matchId, "Match id is required");
        return this.factory.createApiCall(this.attachmentHandler.getAttachments(tournament, matchId));
    }

    /**
     * @see RetrofitAttachmentHandler#getAttachment(String, int, int)
     */
    public ChallongeApiCall<Attachment> getAttachment(String tournament, int matchId, int attachmentId) throws IOException, ChallongeException {
        Validate.isTrue(StringUtils.isNotBlank(tournament), "Tournament string is required");
        Validate.notNull(matchId, "Match id is required");
        Validate.notNull(attachmentId, "Attachment id is required");
        return this.factory.createApiCall(this.attachmentHandler.getAttachment(tournament, matchId, attachmentId));
    }

    /**
     * @see RetrofitAttachmentHandler#createAttachment(String, int, AttachmentQuery)
     */
    public ChallongeApiCall<Attachment> createAttachment(String tournament, int matchId, AttachmentQuery attachment) throws IOException, ChallongeException {
        Validate.isTrue(StringUtils.isNotBlank(tournament), "Tournament string is required");
        Validate.notNull(matchId, "Match id is required");

        // The API provider neither accepts a base64 encoded file nor a multipart request
        // MediaType multipart = MediaType.parse("multipart/form-data");
        //
        // RequestBody asset = attachment.asset() != null ? RequestBody.create(multipart, attachment.asset()) : null;
        // RequestBody description = attachment.description() != null ? RequestBody.create(multipart, attachment.description()) : null;
        // RequestBody url = attachment.url() != null ? RequestBody.create(multipart, attachment.url()) : null;

        return this.factory.createApiCall(this.attachmentHandler.createAttachment(tournament, matchId, attachment));
    }

    /**
     * @see RetrofitAttachmentHandler#updateAttachment(String, int, int, AttachmentQuery)
     */
    public ChallongeApiCall<Attachment> updateAttachment(String tournament, int matchId, int attachmentId, AttachmentQuery attachment) throws IOException, ChallongeException {
        Validate.isTrue(StringUtils.isNotBlank(tournament), "Tournament string is required");
        Validate.notNull(matchId, "Match id is required");
        Validate.notNull(attachmentId, "Attachment id is required");
        return this.factory.createApiCall(this.attachmentHandler.updateAttachment(tournament, matchId, attachmentId, attachment));
    }

    /**
     * @see RetrofitAttachmentHandler#deleteAttachment(String, int, int)
     */
    public ChallongeApiCall<Attachment> deleteAttachment(String tournament, int matchId, int attachmentId) throws IOException, ChallongeException {
        Validate.isTrue(StringUtils.isNotBlank(tournament), "Tournament string is required");
        Validate.notNull(matchId, "Match id is required");
        Validate.notNull(attachmentId, "Attachment id is required");
        return this.factory.createApiCall(this.attachmentHandler.deleteAttachment(tournament, matchId, attachmentId));
    }
}
