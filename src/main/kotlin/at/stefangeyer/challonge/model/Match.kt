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

import at.stefangeyer.challonge.model.enumeration.MatchState
import com.google.gson.annotations.SerializedName
import java.time.OffsetDateTime

class Match(
        @SerializedName("attachment_count")
        val attachmentCount: Int,
        @SerializedName("createdAt")
        val createdAt: OffsetDateTime,
        @SerializedName("group_id")
        val groupId: Int,
        @SerializedName("has_attachment")
        val hasAttachment: Boolean,
        val id: Int,
        val identifier: String,
        val location: String,
        @SerializedName("loser_id")
        val loserId: Int,
        @SerializedName("winner_id")
        val winnerId: Int,
        @SerializedName("player1_id")
        val player1Id: Int,
        @SerializedName("player1_is_prereq_match_loser")
        val player1IsPrerequisiteMatchLoser: Boolean,
        @SerializedName("player1_prereq_match_id")
        val player1PrerequisiteMatchId: Int,
        @SerializedName("player2_id")
        val player2Id: Int,
        @SerializedName("player2_is_prereq_match_loser")
        val player2IsPrerequisiteMatchLoser: Boolean,
        @SerializedName("player2_prereq_match_id")
        val player2PrerequisiteMatchId: Int,
        val round: Int,
        @SerializedName("scheduled_time")
        val scheduledTime: OffsetDateTime,
        @SerializedName("started_at")
        val startedAt: OffsetDateTime,
        val state: MatchState,
        @SerializedName("tournament_id")
        val tournamentId: Int,
        @SerializedName("underway_at")
        val underwayAt: OffsetDateTime,
        @SerializedName("updated_at")
        val updatedAt: OffsetDateTime,
        @SerializedName("prerequisite_match_ids_csv")
        val prerequisiteMatchIdsCsv: String,
        @SerializedName("scores_csv")
        val scoresCsv: String
)
