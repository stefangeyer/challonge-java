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

/**
 * This enum represents the available tournament types.
 *
 * More information about the ranking systems can be found [here](http://feedback.challonge.com/knowledgebase/articles/448540-rank-tie-break-statistics)
 *
 * @author Stefan Geyer
 * @version 20160820.1
 */
enum class RankedBy(private val converted: String) {
    MATCH_WINS("match wins"),
    GAME_WINS("game wins"),
    POINT_SCORED("point scored"),
    POINTS_DIFFERENCE("points difference"),
    CUSTOM("custom");

    override fun toString(): String{
        return converted
    }
}