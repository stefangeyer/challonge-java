package at.stefangeyer.challonge.serializer.implementation

import at.stefangeyer.challonge.serializer.Serializer
import com.google.gson.Gson
import java.lang.reflect.Type

/**
 * Gson serializer implementation
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
class GsonSerializer(val gson: Gson) : Serializer {

    override fun serialize(any: Any): String = this.gson.toJson(any)

    override fun <T> deserialize(string: String, type: Type): T = this.gson.fromJson<T>(string, type)
}