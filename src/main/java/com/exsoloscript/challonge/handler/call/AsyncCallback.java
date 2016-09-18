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

/**
 * Callback handler for async API calls.
 *
 * @param <T> Type of the callback
 * @author EXSolo
 * @version 20160822.1
 */
public interface AsyncCallback<T> {

    /**
     * If the request was successful the demanded object will be accessible through this method
     *
     * @param response The requested object
     */
    void handleSuccess(T response);

    /**
     * If the request failed this method will be called with the related exception
     *
     * @param throwable The related Exception
     */
    void handleFailure(Throwable throwable);
}
