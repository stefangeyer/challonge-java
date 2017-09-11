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

package com.exsoloscript.challonge;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.codec.binary.Base64;

/**
 * Challonge credentials containing username and api-key.
 *
 * @author EXSolo
 * @version 20160822.1
 */
@Data
@AllArgsConstructor
public class ChallongeCredentials {

    private String username;
    private String apiKey;

    /**
     * Creates a HTTP basic auth String from the given credentials
     *
     * @return HTTP basic auth String
     */
    public String toHttpAuthString() {
        String credentials = this.username + ":" + this.apiKey;
        return "Basic " + Base64.encodeBase64String(credentials.getBytes());
    }
}
