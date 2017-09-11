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

package com.exsoloscript.challonge.util;

import com.exsoloscript.challonge.model.exception.ChallongeException;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;

/**
 * This class converts Retrofit responses to throwable exceptions
 *
 * @author EXSolo
 * @version 20160820.1
 */
@Singleton
public class ErrorUtil {

    private final Retrofit retrofit;

    @Inject
    public ErrorUtil(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    /**
     * This method takes a Retrofit response and throws an exception parsed from the response body
     *
     * @param response The response that will be parsed
     * @throws IOException        Problems with connection
     * @throws ChallongeException The parsed exception
     */
    public void parseException(Response<?> response) throws IOException, ChallongeException {
        if (!response.isSuccessful()) {
            Converter<ResponseBody, ChallongeException> converter = this.retrofit.responseBodyConverter(ChallongeException.class, new Annotation[0]);
            throw converter.convert(response.errorBody());
        }
    }

}
