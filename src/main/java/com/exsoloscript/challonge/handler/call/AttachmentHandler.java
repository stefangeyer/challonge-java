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

package com.exsoloscript.challonge.handler.call;

import com.exsoloscript.challonge.handler.retrofit.RetrofitAttachmentHandler;
import com.exsoloscript.challonge.handler.retrofit.RetrofitServiceProvider;
import com.exsoloscript.challonge.model.Attachment;
import com.exsoloscript.challonge.model.query.AttachmentQuery;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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

    private final RetrofitAttachmentHandler attachmentHandler;
    private final RetrofitChallongeApiCallFactory factory;

    @Inject
    AttachmentHandler(RetrofitServiceProvider provider, RetrofitChallongeApiCallFactory factory) {
        this.attachmentHandler = provider.createService(RetrofitAttachmentHandler.class);
        this.factory = factory;
    }

    /**
     * @see RetrofitAttachmentHandler#getAttachments(String, int)
     */
    public ChallongeApiCall<List<Attachment>> getAttachments(String tournament, int matchId) {
        Validate.isTrue(StringUtils.isNotBlank(tournament), "Tournament string is required");
        return this.factory.createApiCall(this.attachmentHandler.getAttachments(tournament, matchId));
    }

    /**
     * @see RetrofitAttachmentHandler#getAttachment(String, int, int)
     */
    public ChallongeApiCall<Attachment> getAttachment(String tournament, int matchId, int attachmentId) {
        Validate.isTrue(StringUtils.isNotBlank(tournament), "Tournament string is required");
        return this.factory.createApiCall(this.attachmentHandler.getAttachment(tournament, matchId, attachmentId));
    }

    /**
     * @see RetrofitAttachmentHandler#createAttachment(String, int, MultipartBody.Part, MultipartBody.Part, MultipartBody.Part)
     */
    public ChallongeApiCall<Attachment> createAttachment(String tournament, int matchId, AttachmentQuery attachment) throws IOException {
        Validate.isTrue(StringUtils.isNotBlank(tournament), "Tournament string is required");

        MultipartBody.Part asset = attachment.asset() != null ?
                MultipartBody.Part.createFormData("match_attachment[asset]",
                        attachment.asset().getName(),
                        RequestBody.create(MediaType.parse(attachment.getMimeType()), attachment.asset())
                ) : null;

        MultipartBody.Part url = attachment.url() != null ?
                MultipartBody.Part.createFormData("match_attachment[url]", attachment.url()) : null;

        MultipartBody.Part desc = attachment.description() != null ?
                MultipartBody.Part.createFormData("match_attachment[description]", attachment.description()) : null;

        return this.factory.createApiCall(this.attachmentHandler.createAttachment(tournament, matchId, asset, desc, url));
    }

    /**
     * @see RetrofitAttachmentHandler#updateAttachment(String, int, int, MultipartBody.Part, MultipartBody.Part, MultipartBody.Part)
     */
    public ChallongeApiCall<Attachment> updateAttachment(String tournament, int matchId, int attachmentId, AttachmentQuery attachment) throws IOException {
        Validate.isTrue(StringUtils.isNotBlank(tournament), "Tournament string is required");

        MultipartBody.Part asset = attachment.asset() != null ?
                MultipartBody.Part.createFormData("match_attachment[asset]",
                        attachment.asset().getName(),
                        RequestBody.create(MediaType.parse(attachment.getMimeType()), attachment.asset())
                ) : null;

        MultipartBody.Part url = attachment.url() != null ?
                MultipartBody.Part.createFormData("match_attachment[url]", attachment.url()) : null;

        MultipartBody.Part desc = attachment.description() != null ?
                MultipartBody.Part.createFormData("match_attachment[description]", attachment.description()) : null;

        return this.factory.createApiCall(this.attachmentHandler.updateAttachment(tournament, matchId, attachmentId, asset, url, desc));
    }

    /**
     * @see RetrofitAttachmentHandler#deleteAttachment(String, int, int)
     */
    public ChallongeApiCall<Attachment> deleteAttachment(String tournament, int matchId, int attachmentId) {
        Validate.isTrue(StringUtils.isNotBlank(tournament), "Tournament string is required");
        return this.factory.createApiCall(this.attachmentHandler.deleteAttachment(tournament, matchId, attachmentId));
    }
}
