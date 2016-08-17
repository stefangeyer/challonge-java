package com.exsoloscript.challonge.model;

import com.google.gson.annotations.SerializedName;

import java.time.ZonedDateTime;

public class Attachment {
    private Integer id;
    @SerializedName("match_id")
    private Integer matchId;
    @SerializedName("user_id")
    private Integer userId;
    @SerializedName("original_file_name")
    private String originalFileName;
    @SerializedName("created_at")
    private ZonedDateTime createdAt;
    @SerializedName("updated_at")
    private ZonedDateTime updatedAt;
    @SerializedName("asset_file_name")
    private String assetFileName;
    @SerializedName("asset_content_type")
    private String assetContentType;
    @SerializedName("asset_file_size")
    private Integer assetFileSize;
    @SerializedName("asset_url")
    private String assetUrl;

    public Integer getId() {
        return id;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getAssetFileName() {
        return assetFileName;
    }

    public String getAssetContentType() {
        return assetContentType;
    }

    public Integer getAssetFileSize() {
        return assetFileSize;
    }

    public String getAssetUrl() {
        return assetUrl;
    }
}
