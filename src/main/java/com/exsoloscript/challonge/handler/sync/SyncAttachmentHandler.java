package com.exsoloscript.challonge.handler.sync;

import com.exsoloscript.challonge.handler.retrofit.RetrofitAttachmentHandler;
import com.exsoloscript.challonge.model.Attachment;
import com.exsoloscript.challonge.model.AttachmentBase;

import java.io.IOException;
import java.util.List;

public class SyncAttachmentHandler extends SyncHandler {

    private RetrofitAttachmentHandler attachmentHandler;

    public SyncAttachmentHandler(RetrofitAttachmentHandler attachmentHandler) {
        this.attachmentHandler = attachmentHandler;
    }

    public Attachment getAttachment(String tournamentName, int matchId, int attachmentId) throws IOException {
        return this.handleResponse(this.attachmentHandler.getAttachment(tournamentName, matchId, attachmentId)).body();
    }

    public List<Attachment> getAttachments(String tournamentName, int matchId) throws IOException {
        return this.handleResponse(this.attachmentHandler.getAttachments(tournamentName, matchId)).body();
    }

    public Attachment createAttachment(String tournamentName, int matchId, AttachmentBase attachment) throws IOException {
        return this.handleResponse(this.attachmentHandler.createAttachment(tournamentName, matchId, attachment)).body();
    }

    public Attachment updateAttachment(String tournamentName, int matchId, int attachmentId, AttachmentBase attachment) throws IOException {
        return this.handleResponse(this.attachmentHandler.updateAttachment(tournamentName, matchId, attachmentId, attachment)).body();
    }

    public Attachment deleteAttachment(String tournamentName, int matchId, int attachmentId) throws IOException {
        return this.handleResponse(this.attachmentHandler.deleteAttachment(tournamentName, matchId, attachmentId)).body();
    }
}
