package com.exsoloscript.challonge.handler.call;

public interface AsyncCallback<T> {
    void handleSuccess(T response);

    void handleFailure(Throwable throwable);
}
