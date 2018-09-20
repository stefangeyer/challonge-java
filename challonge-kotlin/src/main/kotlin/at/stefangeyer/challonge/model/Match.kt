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

class Match(
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
) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Match

        if (id != other.id) return false
        if (tournamentId != other.tournamentId) return false
        if (attachmentCount != other.attachmentCount) return false
        if (groupId != other.groupId) return false
        if (hasAttachment != other.hasAttachment) return false
        if (identifier != other.identifier) return false
        if (location != other.location) return false
        if (loserId != other.loserId) return false
        if (winnerId != other.winnerId) return false
        if (player1Id != other.player1Id) return false
        if (player1IsPrerequisiteMatchLoser != other.player1IsPrerequisiteMatchLoser) return false
        if (player1PrerequisiteMatchId != other.player1PrerequisiteMatchId) return false
        if (player2Id != other.player2Id) return false
        if (player2IsPrerequisiteMatchLoser != other.player2IsPrerequisiteMatchLoser) return false
        if (player2PrerequisiteMatchId != other.player2PrerequisiteMatchId) return false
        if (round != other.round) return false
        if (state != other.state) return false
        if (prerequisiteMatchIdsCsv != other.prerequisiteMatchIdsCsv) return false
        if (scoresCsv != other.scoresCsv) return false
        if (attachments != other.attachments) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + tournamentId.hashCode()
        result = 31 * result + (attachmentCount ?: 0)
        result = 31 * result + (groupId?.hashCode() ?: 0)
        result = 31 * result + hasAttachment.hashCode()
        result = 31 * result + (identifier?.hashCode() ?: 0)
        result = 31 * result + (location?.hashCode() ?: 0)
        result = 31 * result + (loserId?.hashCode() ?: 0)
        result = 31 * result + (winnerId?.hashCode() ?: 0)
        result = 31 * result + (player1Id?.hashCode() ?: 0)
        result = 31 * result + player1IsPrerequisiteMatchLoser.hashCode()
        result = 31 * result + (player1PrerequisiteMatchId?.hashCode() ?: 0)
        result = 31 * result + (player2Id?.hashCode() ?: 0)
        result = 31 * result + player2IsPrerequisiteMatchLoser.hashCode()
        result = 31 * result + (player2PrerequisiteMatchId?.hashCode() ?: 0)
        result = 31 * result + round
        result = 31 * result + (state?.hashCode() ?: 0)
        result = 31 * result + (prerequisiteMatchIdsCsv?.hashCode() ?: 0)
        result = 31 * result + (scoresCsv?.hashCode() ?: 0)
        result = 31 * result + (attachments?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Match(id=$id, tournamentId=$tournamentId, attachmentCount=$attachmentCount, createdAt=$createdAt, groupId=$groupId, hasAttachment=$hasAttachment, identifier=$identifier, location=$location, loserId=$loserId, winnerId=$winnerId, player1Id=$player1Id, player1IsPrerequisiteMatchLoser=$player1IsPrerequisiteMatchLoser, player1PrerequisiteMatchId=$player1PrerequisiteMatchId, player2Id=$player2Id, player2IsPrerequisiteMatchLoser=$player2IsPrerequisiteMatchLoser, player2PrerequisiteMatchId=$player2PrerequisiteMatchId, round=$round, scheduledTime=$scheduledTime, startedAt=$startedAt, state=$state, underwayAt=$underwayAt, updatedAt=$updatedAt, prerequisiteMatchIdsCsv=$prerequisiteMatchIdsCsv, scoresCsv=$scoresCsv, attachments=$attachments)"
    }
}
