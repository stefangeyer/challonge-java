package com.exsoloscript.challonge.handler.call;

import com.exsoloscript.challonge.handler.retrofit.RetrofitAttachmentHandler;
import com.exsoloscript.challonge.handler.retrofit.RetrofitServiceProvider;
import com.exsoloscript.challonge.model.query.AttachmentQuery;
import com.exsoloscript.challonge.model.Attachment;
import com.exsoloscript.challonge.model.exception.ChallongeException;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.io.IOException;
import java.util.List;

@Singleton
public class AttachmentHandler {

    private RetrofitAttachmentHandler attachmentHandler;
    private ChallongeApiCallFactory factory;

    @Inject
    AttachmentHandler(RetrofitServiceProvider provider, ChallongeApiCallFactory factory) {
        this.attachmentHandler = provider.createService(RetrofitAttachmentHandler.class);
        this.factory = factory;
    }
    
    public ChallongeApiCall<List<Attachment>> getAttachments(String tournament, int matchId) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.attachmentHandler.getAttachments(tournament, matchId));
    }
    
    public ChallongeApiCall<Attachment> getAttachment(String tournament, int matchId, int attachmentId) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.attachmentHandler.getAttachment(tournament, matchId, attachmentId));
    }

    public ChallongeApiCall<Attachment> createAttachment(String tournament, int matchId, AttachmentQuery attachment) throws IOException, ChallongeException {

//        MediaType multipart = MediaType.parse("multipart/form-data");
//
//        RequestBody asset = attachment.asset() != null ? RequestBody.create(multipart, attachment.asset()) : null;
//        RequestBody description = attachment.description() != null ? RequestBody.create(multipart, attachment.description()) : null;
//        RequestBody url = attachment.url() != null ? RequestBody.create(multipart, attachment.url()) : null;

        return this.factory.createApiCall(this.attachmentHandler.createAttachment(tournament, matchId, attachment));
    }

    public ChallongeApiCall<Attachment> updateAttachment(String tournament, int matchId, int attachmentId, AttachmentQuery attachment) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.attachmentHandler.updateAttachment(tournament, matchId, attachmentId, attachment));
    }

    public ChallongeApiCall<Attachment> deleteAttachment(String tournament, int matchId, int attachmentId) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.attachmentHandler.deleteAttachment(tournament, matchId, attachmentId));
    }
}
