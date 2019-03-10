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

import com.exsoloscript.challonge.guice.ChallongeModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.commons.lang3.Validate;

/**
 * The only purpose of this class is to instantiate the {@link ChallongeApi} class.
 *
 * @author EXSolo
 * @version 20160822.1
 */
public class Challonge {

    /**
     * Create a new {@link ChallongeApi} v1 object with the given credentials.
     * Every time this method is called a new object will be created.
     *
     * @param username Challonge username
     * @param apiKey   Challonge api key
     * @return ChallongeApi
     */
    public static ChallongeApi getFor(String username, String apiKey) {
        Validate.notNull(username);
        Validate.notNull(apiKey);
        Validate.notBlank(username);
        Validate.notBlank(apiKey);

        ChallongeCredentials credentials = new ChallongeCredentials(username, apiKey);
        Injector injector = Guice.createInjector(new ChallongeModule(credentials));
        return injector.getInstance(ChallongeApi.class);
    }
}
