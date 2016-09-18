package com.exsoloscript.challonge.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(exclude = {"createdAt", "updatedAt"})
public class Attachment {
    private final Integer id;
    @SerializedName("match_id")
    private final Integer matchId;
    @SerializedName("user_id")
    private final Integer userId;
    private final String description;
    private final String url;
    @SerializedName("original_file_name")
    private final String originalFileName;
    @SerializedName("created_at")
    private final OffsetDateTime createdAt;
    @SerializedName("updated_at")
    private final OffsetDateTime updatedAt;
    @SerializedName("asset_file_name")
    private final String assetFileName;
    @SerializedName("asset_content_type")
    private final String assetContentType;
    @SerializedName("asset_file_size")
    private final Integer assetFileSize;
    @SerializedName("asset_url")
    private final String assetUrl;
}
