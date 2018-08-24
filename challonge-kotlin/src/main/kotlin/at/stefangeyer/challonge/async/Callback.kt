package at.stefangeyer.challonge.async

/**
 * Generic callback used for async calls
 *
 * @param <T> T is usually either an instance of a model class or an exception if the call failed
 * @author Stefan Geyer
 * @version 2018-07-02
 */
typealias Callback<T> = (param: T) -> Unit