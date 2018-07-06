package at.stefangeyer.challonge.serializer.gson.adapter

import at.stefangeyer.challonge.model.Match
import com.google.gson.*

import java.lang.reflect.Type

class MatchListAdapter internal constructor() : JsonDeserializer<List<Match>> {

    @Throws(JsonParseException::class)
    override fun deserialize(jsonElement: JsonElement, type: Type, context: JsonDeserializationContext): List<Match>? {
        if (jsonElement.isJsonArray) {
            val array = jsonElement.asJsonArray
            val matches = mutableListOf<Match>()

            for (arrayElement in array) {
                matches.add(context.deserialize(arrayElement, Match::class.java))
            }

            return matches
        }

        return null
    }
}
