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

package com.exsoloscript.challonge;

import com.exsoloscript.challonge.guice.ChallongeModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

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
        ChallongeCredentials credentials = new ChallongeCredentials(username, apiKey);
        Injector injector = Guice.createInjector(new ChallongeModule(credentials));
        return injector.getInstance(ChallongeApi.class);
    }
}
