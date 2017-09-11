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

package com.exsoloscript.challonge.guice;

import com.exsoloscript.challonge.Challonge;
import com.exsoloscript.challonge.ChallongeCredentials;
import com.exsoloscript.challonge.gson.AdapterSuite;
import com.exsoloscript.challonge.handler.retrofit.RetrofitServiceProvider;
import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import retrofit2.Retrofit;

/**
 * Guice module for {@link Challonge} initialization.
 *
 * @author EXSolo
 * @version 20160819.1
 */
public class ChallongeModule extends AbstractModule {

    private final ChallongeCredentials credentials;

    /**
     * Binding the credentials, that will be injected later
     *
     * @param credentials ChallongeCredentials
     */
    public ChallongeModule(ChallongeCredentials credentials) {
        this.credentials = credentials;
    }

    @Override
    protected void configure() {
        bind(ChallongeCredentials.class).toInstance(this.credentials);
        bind(Retrofit.class).toProvider(RetrofitServiceProvider.class);
        bind(Gson.class).toProvider(AdapterSuite.class);
    }
}
