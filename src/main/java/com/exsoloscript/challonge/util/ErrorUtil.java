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
