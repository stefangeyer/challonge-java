package at.stefangeyer.challonge.serializer.gson

import at.stefangeyer.challonge.serializer.Serializer
import com.google.gson.Gson
import java.lang.reflect.Type

/**
 * Gson serializer gson
 *
 * @author Stefan Geyer
 * @version 2018-06-30
 */
class GsonSerializer(val gson: Gson) : Serializer {

    /**
     * Create serializer with a new Gson object
     */
    constructor() : this(Gson())

    override fun serialize(any: Any): String = this.gson.toJson(any)

    override fun <T> deserialize(string: String, type: Type): T = this.gson.fromJson<T>(string, type)
}