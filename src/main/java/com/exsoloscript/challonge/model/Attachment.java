package com.exsoloscript.challonge.model;

import com.google.gson.annotations.SerializedName;

import java.time.ZonedDateTime;

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

    public ZonedDateTime createdAt() {
        return createdAt;
    }

    public ZonedDateTime updatedAt() {
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
}
