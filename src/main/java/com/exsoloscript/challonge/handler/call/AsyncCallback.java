package com.exsoloscript.challonge.handler.call;

/**
 * Callback handler for async API calls.
 *
 * @param <T> Type of the callback
 * @author EXSolo
 * @version 20160822.1
 */
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
