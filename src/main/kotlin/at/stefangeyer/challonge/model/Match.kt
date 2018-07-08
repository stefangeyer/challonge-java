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
import com.google.gson.annotations.SerializedName
import java.time.OffsetDateTime

class Match(
        val id: Long = 0L,
        @SerializedName("tournament_id")
        val tournamentId: Long,
        @SerializedName("attachment_count")
        val attachmentCount: Int = 0,
        @SerializedName("createdAt")
        val createdAt: OffsetDateTime? = null,
        @SerializedName("group_id")
        val groupId: Long = 0L,
        @SerializedName("has_attachment")
        val hasAttachment: Boolean = false,
        val identifier: String? = null,
        val location: String? = null,
        @SerializedName("loser_id")
        val loserId: Long = 0L,
        @SerializedName("winner_id")
        val winnerId: Long = 0L,
        @SerializedName("player1_id")
        val player1Id: Long = 0L,
        @SerializedName("player1_is_prereq_match_loser")
        val player1IsPrerequisiteMatchLoser: Boolean = false,
        @SerializedName("player1_prereq_match_id")
        val player1PrerequisiteMatchId: Long = 0L,
        @SerializedName("player2_id")
        val player2Id: Long = 0L,
        @SerializedName("player2_is_prereq_match_loser")
        val player2IsPrerequisiteMatchLoser: Boolean = false,
        @SerializedName("player2_prereq_match_id")
        val player2PrerequisiteMatchId: Long = 0L,
        val round: Int = 0,
        @SerializedName("scheduled_time")
        val scheduledTime: OffsetDateTime? = null,
        @SerializedName("started_at")
        val startedAt: OffsetDateTime? = null,
        val state: MatchState? = MatchState.OPEN,
        @SerializedName("underway_at")
        val underwayAt: OffsetDateTime? = null,
        @SerializedName("updated_at")
        val updatedAt: OffsetDateTime? = null,
        @SerializedName("prerequisite_match_ids_csv")
        val prerequisiteMatchIdsCsv: String? = null,
        @SerializedName("scores_csv")
        val scoresCsv: String? = null,
        val attachments: List<Attachment>? = listOf()
)
