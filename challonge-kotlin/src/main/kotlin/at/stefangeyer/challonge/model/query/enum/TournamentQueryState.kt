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

package at.stefangeyer.challonge.model.query.enum

/**
 * Represents the different states that can be queried.
 * This enum does not represent the actual state of the tournament,
 * but groups a list ouf tournaments to subgroups.
 *
 * @author Stefan Geyer
 * @version 20160820.1
 */
enum class TournamentQueryState(private val converted: String) {
    ALL("all"),
    PENDING("pending"),
    IN_PROGRESS("in_progress"),
    ENDED("ended");

    override fun toString(): String {
        return converted
    }
}
