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

data class Attachment(
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
)