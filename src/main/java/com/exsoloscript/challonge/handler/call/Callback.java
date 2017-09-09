package com.exsoloscript.challonge.handler.call;

/**
 * Generic callback used for async calls
 *
 * @param <T> T is usually either an instance of a model class or an exception if the call failed
 * @author Stefan Geyer
 * @version 20170909.1
 */
public interface Callback<T> {

    /**
     * Called on task completion
     *
     * @param obj handle
     */
    void handle(T obj);
}
