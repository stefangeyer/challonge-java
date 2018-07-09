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

import at.stefangeyer.challonge.model.enum.MatchState
import java.time.OffsetDateTime

data class Match(
        val id: Long = 0L,
        val tournamentId: Long,
        val attachmentCount: Int? = 0,
        val createdAt: OffsetDateTime? = null,
        val groupId: Long? = 0L,
        val hasAttachment: Boolean = false,
        val identifier: String? = null,
        val location: String? = null,
        val loserId: Long? = 0L,
        val winnerId: Long? = 0L,
        val player1Id: Long? = 0L,
        val player1IsPrerequisiteMatchLoser: Boolean = false,
        val player1PrerequisiteMatchId: Long? = 0L,
        val player2Id: Long? = 0L,
        val player2IsPrerequisiteMatchLoser: Boolean = false,
        val player2PrerequisiteMatchId: Long? = 0L,
        val round: Int = 0,
        val scheduledTime: OffsetDateTime? = null,
        val startedAt: OffsetDateTime? = null,
        val state: MatchState? = MatchState.OPEN,
        val underwayAt: OffsetDateTime? = null,
        val updatedAt: OffsetDateTime? = null,
        val prerequisiteMatchIdsCsv: String? = null,
        val scoresCsv: String? = null,
        val attachments: List<Attachment>? = listOf()
)
