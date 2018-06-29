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

package at.stefangeyer.challonge.model

import com.google.gson.annotations.SerializedName
import java.time.OffsetDateTime

class Attachment(
        val id: Int,
        @SerializedName("match_id")
        val matchId: Int,
        @SerializedName("user_id")
        val userId: Int,
        val description: String,
        val url: String,
        @SerializedName("original_file_name")
        val originalFileName: String,
        @SerializedName("created_at")
        val createdAt: OffsetDateTime,
        @SerializedName("updated_at")
        val updatedAt: OffsetDateTime,
        @SerializedName("asset_file_name")
        val assetFileName: String,
        @SerializedName("asset_content_type")
        val assetContentType: String,
        @SerializedName("asset_file_size")
        val assetFileSize: Int,
        @SerializedName("asset_url")
        val assetUrl: String
)