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
import com.exsoloscript.challonge.util.ErrorUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

/**
 * Call implementation for Retrofit.
 *
 * @param <T> Type of the received object
 * @author EXSolo
 * @version 20160822.1
 */
public class RetrofitChallongeApiCall<T> implements ChallongeApiCall<T> {

    private Call<T> retrofitCall;
    private ErrorUtil errorUtil;

    /**
     * Create new object with a call and a way to handle errors.
     *
     * @param retrofitCall Call
     * @param errorUtil    ErrorUtil
     */
    public RetrofitChallongeApiCall(Call<T> retrofitCall, ErrorUtil errorUtil) {
        this.retrofitCall = retrofitCall;
        this.errorUtil = errorUtil;
    }

    @Override
    public T sync() throws IOException, ChallongeException {
        Response<T> response = this.retrofitCall.execute();

        if (response.isSuccessful()) {
            return response.body();
        } else {
            this.errorUtil.parseException(response);
            return response.body();
        }
    }

    @Override
    public void async(AsyncCallback<T> callback) {
        this.retrofitCall.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    callback.handleSuccess(response.body());
                } else {
                    try {
                        RetrofitChallongeApiCall.this.errorUtil.parseException(response);
                    } catch (IOException | ChallongeException e) {
                        callback.handleFailure(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                callback.handleFailure(t);
            }
        });
    }
}
