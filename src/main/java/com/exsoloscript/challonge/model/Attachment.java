package com.exsoloscript.challonge.model;

import com.google.gson.annotations.SerializedName;

import java.time.OffsetDateTime;

/**
 * The POJO that will be mapped to the attachment requests by Gson
 *
 * @author EXSolo
 * @version 20160820.1
 */
public class Attachment {

    private Integer id;
    @SerializedName("match_id")
    private Integer matchId;
    @SerializedName("user_id")
    private Integer userId;
    private String description;
    private String url;
    @SerializedName("original_file_name")
    private String originalFileName;
    @SerializedName("created_at")
    private OffsetDateTime createdAt;
    @SerializedName("updated_at")
    private OffsetDateTime updatedAt;
    @SerializedName("asset_file_name")
    private String assetFileName;
    @SerializedName("asset_content_type")
    private String assetContentType;
    @SerializedName("asset_file_size")
    private Integer assetFileSize;
    @SerializedName("asset_url")
    private String assetUrl;

    public Integer id() {
        return id;
    }

    public Integer matchId() {
        return matchId;
    }

    public Integer userId() {
        return userId;
    }

    public String originalFileName() {
        return originalFileName;
    }

    public OffsetDateTime createdAt() {
        return createdAt;
    }

    public OffsetDateTime updatedAt() {
        return updatedAt;
    }

    public String assetFileName() {
        return assetFileName;
    }

    public String assetContentType() {
        return assetContentType;
    }

    public Integer assetFileSize() {
        return assetFileSize;
    }

    public String assetUrl() {
        return assetUrl;
    }

    public String description() {
        return description;
    }

    public String url() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attachment that = (Attachment) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (matchId != null ? !matchId.equals(that.matchId) : that.matchId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (originalFileName != null ? !originalFileName.equals(that.originalFileName) : that.originalFileName != null)
            return false;
        if (assetFileName != null ? !assetFileName.equals(that.assetFileName) : that.assetFileName != null)
            return false;
        if (assetContentType != null ? !assetContentType.equals(that.assetContentType) : that.assetContentType != null)
            return false;
        if (assetFileSize != null ? !assetFileSize.equals(that.assetFileSize) : that.assetFileSize != null)
            return false;
        return assetUrl != null ? assetUrl.equals(that.assetUrl) : that.assetUrl == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (matchId != null ? matchId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (originalFileName != null ? originalFileName.hashCode() : 0);
        result = 31 * result + (assetFileName != null ? assetFileName.hashCode() : 0);
        result = 31 * result + (assetContentType != null ? assetContentType.hashCode() : 0);
        result = 31 * result + (assetFileSize != null ? assetFileSize.hashCode() : 0);
        result = 31 * result + (assetUrl != null ? assetUrl.hashCode() : 0);
        return result;
    }
}
