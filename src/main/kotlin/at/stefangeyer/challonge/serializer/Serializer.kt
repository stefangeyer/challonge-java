package at.stefangeyer.challonge.serializer

import java.lang.reflect.Type

/**
 * Serializer definition.
 * Serializers convert objects to their JSON representation and vice versa.
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
interface Serializer {

    /**
     * Serializes an objects to it's JSON representation
     *
     * @param any The object to serialize
     * @return The object's JSON representation
     */
    fun serialize(any: Any): String

    /**
     * Deserializes a JSON string to an object of the given type
     *
     * @param string The JSON string
     * @param type The object's type
     * @return The deserialized object
     */
    fun <T> deserialize(string: String, type: Type): T
}