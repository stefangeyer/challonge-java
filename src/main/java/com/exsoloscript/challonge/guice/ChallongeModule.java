/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 EXSolo <exsoloscripter at gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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

    private ChallongeCredentials credentials;

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
