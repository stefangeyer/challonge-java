package at.stefangeyer.challonge.serializer.gson.adapter

import at.stefangeyer.challonge.model.wrapper.ParticipantWrapper
import at.stefangeyer.challonge.model.wrapper.ParticipantWrapperListWrapper
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ParticipantWrapperListWrapperAdapter : JsonDeserializer<ParticipantWrapperListWrapper> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): ParticipantWrapperListWrapper {
        val jsonArray = json.asJsonArray
        val list = mutableListOf<ParticipantWrapper>()

        for (e in jsonArray) {
            val wrapper = context.deserialize<ParticipantWrapper>(e, ParticipantWrapper::class.java)
            list.add(wrapper)
        }

        return ParticipantWrapperListWrapper(list)
    }
}