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

package com.exsoloscript.challonge.integration;

import com.exsoloscript.challonge.Challonge;
import com.exsoloscript.challonge.ChallongeApi;
import com.google.inject.Provider;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.Map;

@RunWith(Suite.class)
@Suite.SuiteClasses({SyncTournamentTest.class, SyncParticipantTest.class, SyncMatchTest.class, SyncAttachmentTest.class, AsyncTest.class})
public class ChallongeTestSuite implements Provider<ChallongeApi> {

    @Override
    public ChallongeApi get() {
        Map<String, String> env = System.getenv();

        String usernameKey = "CHALLONGE_USERNAME";
        String apiKeyKey = "CHALLONGE_API_KEY";

        if (env.get(usernameKey) == null || env.get(apiKeyKey) == null) {
            throw new IllegalArgumentException("Provide environment variables for CHALLONGE_USERNAME and CHALLONGE_API_KEY");
        }

        return Challonge.getFor(env.get(usernameKey), env.get(apiKeyKey));
    }
}
