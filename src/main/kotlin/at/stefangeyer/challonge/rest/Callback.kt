package at.stefangeyer.challonge.rest

/**
 * Generic callback used for async calls
 *
 * @param <T> T is usually either an instance of a model class or an exception if the call failed
 * @author Stefan Geyer
 * @version 2018-07-02
 */
interface Callback<T> {

    /**
     * Called on task completion
     *
     * @param param result parameter
     */
    fun handle(param: T)
}