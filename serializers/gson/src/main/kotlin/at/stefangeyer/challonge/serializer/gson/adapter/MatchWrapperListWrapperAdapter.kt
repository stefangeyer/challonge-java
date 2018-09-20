package at.stefangeyer.challonge.serializer.gson.adapter

import at.stefangeyer.challonge.model.wrapper.MatchWrapper
import at.stefangeyer.challonge.model.wrapper.MatchWrapperListWrapper
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class MatchWrapperListWrapperAdapter : JsonDeserializer<MatchWrapperListWrapper> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): MatchWrapperListWrapper {
        val jsonArray = json.asJsonArray
        val list = mutableListOf<MatchWrapper>()

        for (e in jsonArray) {
            val wrapper = context.deserialize<MatchWrapper>(e, MatchWrapper::class.java)
            list.add(wrapper)
        }

        return MatchWrapperListWrapper(list)
    }
}