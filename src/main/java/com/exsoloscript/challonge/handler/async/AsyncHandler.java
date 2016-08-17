package com.exsoloscript.challonge.handler.async;

import com.exsoloscript.challonge.handler.async.callback.AsyncCallback;
import com.exsoloscript.challonge.model.exception.ChallongeException;
import com.exsoloscript.challonge.util.ErrorUtil;
import com.google.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

public class AsyncHandler {

    @Inject
    private ErrorUtil errorUtil;

    public <T> void handleResponse(Call<T> call, AsyncCallback<T> callback) throws IOException, ChallongeException {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    callback.handleSuccess(response.body());
                } else {
                    try {
                        AsyncHandler.this.errorUtil.parseException(response);
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
