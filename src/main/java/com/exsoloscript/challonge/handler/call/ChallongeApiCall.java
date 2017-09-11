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
     * Performs a sync API call.
     * Blocking request which returns the object right away.
     *
     * @return The received object
     * @throws IOException        Exception thrown by error handling
     * @throws ChallongeException Exception thrown by error handling
     */
    T sync() throws IOException, ChallongeException;

    /**
     * Performs an async API call.
     * One of the callbacks must be called on completion
     *
     * @param success Called on successful call completion
     * @param error Called on failure
     */
    void async(Callback<T> success, Callback<Throwable> error);

    /**
     * Performs an async API call.
     * One of the callbacks must be called on completion
     *
     * @deprecated Deprecated in favor of {@link ChallongeApiCall#async(Callback, Callback)}
     * @param callback The callback
     */
    @Deprecated
    void async(AsyncCallback<T> callback);
}
