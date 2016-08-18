package com.exsoloscript.challonge.handler.call;

import com.exsoloscript.challonge.handler.retrofit.RetrofitAttachmentHandler;
import com.exsoloscript.challonge.handler.retrofit.RetrofitServiceProvider;
import com.exsoloscript.challonge.model.Attachment;
import com.exsoloscript.challonge.model.query.AttachmentQuery;
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
    
    public ChallongeApiCall<List<Attachment>> getAttachments(String tournamentName, int matchId) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.attachmentHandler.getAttachments(tournamentName, matchId));
    }
    
    public ChallongeApiCall<Attachment> getAttachment(String tournamentName, int matchId, int attachmentId) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.attachmentHandler.getAttachment(tournamentName, matchId, attachmentId));
    }

    public ChallongeApiCall<Attachment> createAttachment(String tournamentName, int matchId, AttachmentQuery attachment) throws IOException, ChallongeException {

        RequestBody asset = attachment.getFile() != null ? RequestBody.create(MediaType.parse("multipart/form-data"), attachment.getFile()) : null;
        RequestBody description = attachment.getDescription() != null ? RequestBody.create(MediaType.parse("multipart/form-data"), attachment.getDescription()) : null;
        RequestBody url = attachment.getUrl() != null ? RequestBody.create(MediaType.parse("multipart/form-data"), attachment.getUrl()) : null;

        return this.factory.createApiCall(this.attachmentHandler.createAttachment(tournamentName, matchId, asset, url, description));
    }

    public ChallongeApiCall<Attachment> updateAttachment(String tournamentName, int matchId, int attachmentId, AttachmentQuery attachment) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.attachmentHandler.updateAttachment(tournamentName, matchId, attachmentId, attachment));
    }

    public ChallongeApiCall<Attachment> deleteAttachment(String tournamentName, int matchId, int attachmentId) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.attachmentHandler.deleteAttachment(tournamentName, matchId, attachmentId));
    }
}
