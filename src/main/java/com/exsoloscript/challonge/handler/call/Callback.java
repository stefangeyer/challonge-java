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
 * Generic callback used for async calls
 *
 * @param <T> T is usually either an instance of a model class or an exception if the call failed
 * @author Stefan Geyer
 * @version 20170909.1
 */
public interface Callback<T> {

    /**
     * Called on task completion
     *
     * @param obj handle
     */
    void handle(T obj);
}
