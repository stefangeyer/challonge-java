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

package com.exsoloscript.challonge.handler.call;

import com.exsoloscript.challonge.model.exception.ChallongeException;

import java.io.IOException;

/**
 * Implementations of this class should return an object of the given type
 * received by the API
 *
 * @param <T> Type of the response
 * @author EXSolo
 * @version 20160822.1
 */
public interface ChallongeApiCall<T> {

    /**
     * A sync API call.
     * Blocking request which returns the object right away.
     *
     * @return The received object
     * @throws IOException        Exception thrown by error handling
     * @throws ChallongeException Exception thrown by error handling
     */
    T sync() throws IOException, ChallongeException;

    /**
     * An async API call.
     * Once the response is received the callback method will be called.
     *
     * @param callback The callback
     */
    void async(AsyncCallback<T> callback);
}
