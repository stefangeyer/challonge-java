package com.exsoloscript.challonge.handler.call;

/**
 * Implementations of this class should return an object of the given type
 * received by the API
 *
 * @param <T> Type of the response
 * @author EXSolo
 * @version 20160822.1
 */
public interface ChallongeApiCall<T> {

    /**
     * A sync API call.
     * Blocking request which returns the object right away.
     *
     * @return The received object
     * @throws Throwable Any exception that occurred during the call
     */
    T sync() throws Throwable;

    /**
     * An async API call.
     * Once the response is received the callback method will be called.
     *
     * @param callback The callback
     * @throws Throwable Any exception that occurred during the call
     */
    void async(AsyncCallback<T> callback) throws Throwable;
}
