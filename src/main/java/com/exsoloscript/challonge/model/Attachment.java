package com.exsoloscript.challonge.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

/**
 * The POJO that will be mapped to the attachment requests by Gson
 *
 * @author EXSolo
 * @version 20160820.1
 */
@Data
@Accessors(fluent = true)
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
}
