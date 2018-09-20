package at.stefangeyer.challonge.serializer.gson.util

import com.google.gson.JsonElement

fun JsonElement.getOrNull(): JsonElement? {
    return if (isJsonNull) null
    else this
}