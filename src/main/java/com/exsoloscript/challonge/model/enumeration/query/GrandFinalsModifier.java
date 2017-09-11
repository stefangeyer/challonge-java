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

package com.exsoloscript.challonge.model.enumeration.query;

import com.google.gson.annotations.SerializedName;

/**
 * Type of the grand finals in a double elimination tournament.
 * <p>
 * This option only affects double elimination.
 * <p>
 * null/blank (default) - give the winners bracket finalist two chances to beat the losers bracket finalist<br>
 * 'single match' - create only one grand finals match<br>
 * 'skip' - don't create a finals match between winners and losers bracket finalists
 * </p>
 *
 * @author EXSolo
 * @version 20160820.1
 */
public enum GrandFinalsModifier {
    @SerializedName("")
    BLANK,
    @SerializedName("single match")
    SINGLE_MATCH,
    @SerializedName("skip")
    SKIP
}