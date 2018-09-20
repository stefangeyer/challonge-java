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

package at.stefangeyer.challonge.model.enum

enum class TournamentState(private val converted: String) {
    CHECKING_IN("checking_in"),
    CHECKED_IN("checked_in"),
    PENDING("pending"),
    UNDERWAY("underway"),
    GROUP_STAGES_UNDERWAY("group_stages_underway"),
    GROUP_STAGES_FINALIZED("group_stages_finalized"),
    AWAITING_REVIEW("awaiting_review"),
    COMPLETE("complete");

    override fun toString(): String {
        return converted
    }
}
