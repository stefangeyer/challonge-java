package com.exsoloscript.challonge.handler.call;

import com.exsoloscript.challonge.model.exception.ChallongeException;
import com.exsoloscript.challonge.util.ErrorUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

public class ChallongeApiCall<T> {

    private Call<T> retrofitCall;
    private ErrorUtil errorUtil;

    public ChallongeApiCall(Call<T> retrofitCall, ErrorUtil errorUtil) {
        this.retrofitCall = retrofitCall;
        this.errorUtil = errorUtil;
    }

    public T sync() throws IOException, ChallongeException {
        Response<T> response = this.retrofitCall.execute();

        if (response.isSuccessful()) {
            return response.body();
        } else {
            this.errorUtil.parseException(response);
            return response.body();
        }
    }

    public void async(AsyncCallback<T> callback) {
        this.retrofitCall.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    callback.handleSuccess(response.body());
                } else {
                    try {
                        ChallongeApiCall.this.errorUtil.parseException(response);
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
