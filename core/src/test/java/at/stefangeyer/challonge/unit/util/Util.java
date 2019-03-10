package at.stefangeyer.challonge.unit.util;

/**
 * Core project utility methods
 *
 * @author Stefan Geyer
 * @version 2019-02-28
 */
public class Util {

    /**
     * Returns t if not null or def otherwise.
     *
     * @param t   The object to perform the null check on
     * @param def The default value
     * @param <T> Generic object type
     * @return t or def
     */
    public static <T> T ifNotNull(T t, T def) {
        return t != null ? t : def;
    }
}
