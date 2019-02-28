package at.stefangeyer.challonge.serializer;

import java.lang.reflect.Type;

/**
 * Serializer definition.
 * Serializers convert objects to their JSON representation and vice versa.
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
public interface Serializer {

    /**
     * Serializes an objects to it's JSON representation
     *
     * @param obj The object to serialize
     * @return The object's JSON representation
     */
    String serialize(Object obj);

    /**
     * Deserializes a JSON string to an object of the given type
     *
     * @param string The JSON string
     * @param type   The object's type
     * @return The deserialized object
     */
    Object deserialize(String string, Type type);
}
