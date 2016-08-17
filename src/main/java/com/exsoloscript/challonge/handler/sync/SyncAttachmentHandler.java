package com.exsoloscript.challonge.handler.sync;

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
public class SyncAttachmentHandler extends SyncHandler {

    private RetrofitAttachmentHandler attachmentHandler;

    @Inject
    SyncAttachmentHandler(RetrofitServiceProvider provider) {
        this.attachmentHandler = provider.createService(RetrofitAttachmentHandler.class);
    }

    public Attachment getAttachment(String tournamentName, int matchId, int attachmentId) throws IOException, ChallongeException {
        return this.handleResponse(this.attachmentHandler.getAttachment(tournamentName, matchId, attachmentId)).body();
    }

    public List<Attachment> getAttachments(String tournamentName, int matchId) throws IOException, ChallongeException {
        return this.handleResponse(this.attachmentHandler.getAttachments(tournamentName, matchId)).body();
    }

    public Attachment createAttachment(String tournamentName, int matchId, AttachmentQuery attachment) throws IOException, ChallongeException {

        RequestBody asset = attachment.getFile() != null ? RequestBody.create(MediaType.parse("multipart/form-data"), attachment.getFile()) : null;
        RequestBody description = attachment.getDescription() != null ? RequestBody.create(MediaType.parse("multipart/form-data"), attachment.getDescription()) : null;
        RequestBody url = attachment.getUrl() != null ? RequestBody.create(MediaType.parse("multipart/form-data"), attachment.getUrl()) : null;

        return this.handleResponse(this.attachmentHandler.createAttachment(tournamentName, matchId, asset, url, description)).body();
    }

    public Attachment updateAttachment(String tournamentName, int matchId, int attachmentId, AttachmentQuery attachment) throws IOException, ChallongeException {
        return this.handleResponse(this.attachmentHandler.updateAttachment(tournamentName, matchId, attachmentId, attachment)).body();
    }

    public Attachment deleteAttachment(String tournamentName, int matchId, int attachmentId) throws IOException, ChallongeException {
        return this.handleResponse(this.attachmentHandler.deleteAttachment(tournamentName, matchId, attachmentId)).body();
    }
}
