package com.exsoloscript.challonge.handler.async.callback;

public interface AsyncCallback<T> {
    void handleSuccess(T response);

    void handleFailure(Throwable throwable);
}
