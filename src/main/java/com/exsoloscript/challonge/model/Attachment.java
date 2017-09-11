/*
 * Copyright 2017 Stefan Geyer <stefangeyer at outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

    public String assetUrl() {
        // The asset url is returned without the protocol prefix
        return "https:" + assetUrl;
    }
}
