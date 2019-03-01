package at.stefangeyer.challonge.rest.retrofit;

import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.query.AttachmentQuery;
import at.stefangeyer.challonge.model.wrapper.AttachmentWrapper;
import at.stefangeyer.challonge.rest.AttachmentRestClient;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static at.stefangeyer.challonge.rest.retrofit.RetrofitUtil.callback;
import static at.stefangeyer.challonge.rest.retrofit.RetrofitUtil.parse;

public class RetrofitAttachmentRestClient implements AttachmentRestClient {

    private ChallongeRetrofit challongeRetrofit;

    public RetrofitAttachmentRestClient(ChallongeRetrofit challongeRetrofit) {
        this.challongeRetrofit = challongeRetrofit;
    }

    @Override
    public List<AttachmentWrapper> getAttachments(String tournament, long matchId) throws DataAccessException {
        Response<List<AttachmentWrapper>> response;

        try {
            response = this.challongeRetrofit.getAttachments(tournament, matchId).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while getting attachments", e);
        }

        return parse(response);
    }

    @Override
    public void getAttachments(String tournament, long matchId, Callback<List<AttachmentWrapper>> onSuccess,
                               Callback<DataAccessException> onFailure) {
        this.challongeRetrofit.getAttachments(tournament, matchId)
                .enqueue(callback(onSuccess, onFailure, "Error while getting attachments"));
    }

    @Override
    public AttachmentWrapper getAttachment(String tournament, long matchId, long attachmentId) throws DataAccessException {
        Response<AttachmentWrapper> response;

        try {
            response = this.challongeRetrofit.getAttachment(tournament, matchId, attachmentId).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while getting attachment", e);
        }

        return parse(response);
    }

    @Override
    public void getAttachment(String tournament, long matchId, long attachmentId, Callback<AttachmentWrapper> onSuccess,
                              Callback<DataAccessException> onFailure) {
        this.challongeRetrofit.getAttachment(tournament, matchId, attachmentId)
                .enqueue(callback(onSuccess, onFailure, "Error while getting attachment"));
    }

    @Override
    public AttachmentWrapper createAttachment(String tournament, long matchId,
                                              AttachmentQuery attachment) throws DataAccessException {
        Response<AttachmentWrapper> response;

        MultipartBody.Part asset;
        MultipartBody.Part url = createUrlPart(attachment);
        MultipartBody.Part desc = createDescriptionPart(attachment);

        try {
            asset = createAssetPart(attachment);
        } catch (IOException e) {
            throw new DataAccessException("Error creating the asset body part", e);
        }

        try {
            response = this.challongeRetrofit.createAttachment(tournament, matchId, asset, url, desc).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while creating attachment", e);
        }

        return parse(response);
    }

    @Override
    public void createAttachment(String tournament, long matchId, AttachmentQuery attachment,
                                 Callback<AttachmentWrapper> onSuccess, Callback<DataAccessException> onFailure) {
        MultipartBody.Part asset;
        MultipartBody.Part url = createUrlPart(attachment);
        MultipartBody.Part desc = createDescriptionPart(attachment);

        try {
            asset = createAssetPart(attachment);
        } catch (IOException e) {
            onFailure.accept(new DataAccessException("Error creating the asset body part", e));
            return;
        }

        this.challongeRetrofit.createAttachment(tournament, matchId, asset, url, desc)
                .enqueue(callback(onSuccess, onFailure, "Error while creating attachment"));
    }

    @Override
    public AttachmentWrapper updateAttachment(String tournament, long matchId, long attachmentId,
                                              AttachmentQuery attachment) throws DataAccessException {
        Response<AttachmentWrapper> response;

        MultipartBody.Part asset;
        MultipartBody.Part url = createUrlPart(attachment);
        MultipartBody.Part desc = createDescriptionPart(attachment);

        try {
            asset = createAssetPart(attachment);
        } catch (IOException e) {
            throw new DataAccessException("Error creating the asset body part", e);
        }

        try {
            response = this.challongeRetrofit.updateAttachment(tournament, matchId, attachmentId, asset, url, desc).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while updating attachment", e);
        }

        return parse(response);
    }

    @Override
    public void updateAttachment(String tournament, long matchId, long attachmentId, AttachmentQuery attachment,
                                 Callback<AttachmentWrapper> onSuccess, Callback<DataAccessException> onFailure) {
        MultipartBody.Part asset;
        MultipartBody.Part url = createUrlPart(attachment);
        MultipartBody.Part desc = createDescriptionPart(attachment);

        try {
            asset = createAssetPart(attachment);
        } catch (IOException e) {
            onFailure.accept(new DataAccessException("Error creating the asset body part", e));
            return;
        }

        this.challongeRetrofit.updateAttachment(tournament, matchId, attachmentId, asset, url, desc)
                .enqueue(callback(onSuccess, onFailure, "Error while updating attachment"));
    }

    @Override
    public AttachmentWrapper deleteAttachment(String tournament, long matchId, long attachmentId) throws DataAccessException {
        Response<AttachmentWrapper> response;

        try {
            response = this.challongeRetrofit.deleteAttachment(tournament, matchId, attachmentId).execute();
        } catch (IOException e) {
            throw new DataAccessException("Error while deleting attachment", e);
        }

        return parse(response);
    }

    @Override
    public void deleteAttachment(String tournament, long matchId, long attachmentId, Callback<AttachmentWrapper> onSuccess,
                                 Callback<DataAccessException> onFailure) {
        this.challongeRetrofit.getAttachment(tournament, matchId, attachmentId)
                .enqueue(callback(onSuccess, onFailure, "Error while deleting attachment"));
    }

    private MultipartBody.Part createAssetPart(AttachmentQuery attachment) throws IOException {
        if (attachment.getAsset() != null) {
            File asset = attachment.getAsset();
            MediaType mediaType = attachment.getAssetMimeType() != null ? MediaType.parse(attachment.getAssetMimeType()) : null;
            RequestBody requestBody = RequestBody.create(mediaType, attachment.getAsset());
            return MultipartBody.Part.createFormData("match_attachment[asset]", asset.getName(), requestBody);
        }
        return null;
    }

    private MultipartBody.Part createUrlPart(AttachmentQuery attachment) {
        if (attachment.getUrl() != null) {
            return MultipartBody.Part.createFormData("match_attachment[url]", attachment.getUrl());
        }
        return null;
    }

    private MultipartBody.Part createDescriptionPart(AttachmentQuery attachment) {
        if (attachment.getDescription() != null) {
            return MultipartBody.Part.createFormData("match_attachment[description]", attachment.getDescription());
        }
        return null;
    }
}
