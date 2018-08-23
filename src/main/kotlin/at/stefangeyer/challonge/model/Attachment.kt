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

import java.time.OffsetDateTime

class Attachment(
        val id: Long = 0L,
        val matchId: Long = 0L,
        val userId: Long = 0L,
        val description: String? = null,
        val url: String? = null,
        val originalFileName: String? = null,
        val createdAt: OffsetDateTime? = null,
        val updatedAt: OffsetDateTime? = null,
        val assetFileName: String? = null,
        val assetContentType: String? = null,
        val assetFileSize: Long? = 0L,
        val assetUrl: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Attachment

        if (id != other.id) return false
        if (matchId != other.matchId) return false
        if (userId != other.userId) return false
        if (description != other.description) return false
        if (url != other.url) return false
        if (originalFileName != other.originalFileName) return false
        if (assetFileName != other.assetFileName) return false
        if (assetContentType != other.assetContentType) return false
        if (assetFileSize != other.assetFileSize) return false
        if (assetUrl != other.assetUrl) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + matchId.hashCode()
        result = 31 * result + userId.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (url?.hashCode() ?: 0)
        result = 31 * result + (originalFileName?.hashCode() ?: 0)
        result = 31 * result + (assetFileName?.hashCode() ?: 0)
        result = 31 * result + (assetContentType?.hashCode() ?: 0)
        result = 31 * result + (assetFileSize?.hashCode() ?: 0)
        result = 31 * result + (assetUrl?.hashCode() ?: 0)
        return result
    }
}