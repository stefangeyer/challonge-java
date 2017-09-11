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

import com.exsoloscript.challonge.handler.call.Callback;
import com.exsoloscript.challonge.model.exception.ChallongeException;
import com.exsoloscript.challonge.util.ErrorUtil;
import retrofit2.Call;
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

    private final Call<T> retrofitCall;
    private final ErrorUtil errorUtil;

    /**
     * Create new object with a call and a way to handle errors.
     *
     * @param retrofitCall Call
     * @param errorUtil    ErrorUtil
     */
    RetrofitChallongeApiCall(Call<T> retrofitCall, ErrorUtil errorUtil) {
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
    public void async(Callback<T> success, Callback<Throwable> error) {
        this.retrofitCall.enqueue(new retrofit2.Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    success.handle(response.body());
                } else {
                    try {
                        RetrofitChallongeApiCall.this.errorUtil.parseException(response);
                    } catch (IOException | ChallongeException e) {
                        error.handle(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                error.handle(t);
            }
        });
    }

    @Override
    @Deprecated
    public void async(AsyncCallback<T> callback) {
        async(callback::handleSuccess, callback::handleFailure);
    }
}
