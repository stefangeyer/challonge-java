/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 EXSolo <exsoloscripter at gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
