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

package com.exsoloscript.challonge.model.enumeration;

import com.google.gson.annotations.SerializedName;

/**
 * This enumeration represents the available tournament types.
 * <p>
 * More information about the ranking systems can be found <a href="http://feedback.challonge.com/knowledgebase/articles/448540-rank-tie-break-statistics">here</a>
 *
 * @author EXSolo
 * @version 20160820.1
 */
public enum RankedBy {
    @SerializedName("match wins")
    MATCH_WINS,
    @SerializedName("game wins")
    GAME_WINS,
    @SerializedName("point scored")
    POINT_SCORED,
    @SerializedName("points difference")
    POINTS_DIFFERENCE,
    @SerializedName("custom")
    CUSTOM
}