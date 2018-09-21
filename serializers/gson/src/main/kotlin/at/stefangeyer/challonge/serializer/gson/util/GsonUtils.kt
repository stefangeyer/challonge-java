package at.stefangeyer.challonge.serializer.gson.util

import com.google.gson.JsonElement
import com.google.gson.JsonObject

fun JsonObject.getOrNull(key: String): JsonElement? {
    return if (!this.has(key) || this.get(key).isJsonNull) null
    else get(key)
}