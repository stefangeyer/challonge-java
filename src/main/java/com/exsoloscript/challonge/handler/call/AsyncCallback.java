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

/**
 * Callback handler for async API calls.
 *
 * @deprecated Deprecated in favor of {@link Callback}
 * @param <T> Type of the callback
 * @author EXSolo
 * @version 20160822.1
 */
@Deprecated
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
