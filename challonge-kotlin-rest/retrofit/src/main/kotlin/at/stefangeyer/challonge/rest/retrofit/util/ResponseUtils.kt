package at.stefangeyer.challonge.rest.retrofit.util

import at.stefangeyer.challonge.exception.DataAccessException
import retrofit2.Response

fun <T> Response<T>.parse(action: String): T {
    if (!isSuccessful) {
        throw DataAccessException(action + " request was not successful (" +
                code() + ") and returned: " + errorBody().toString())
    }

    val body = body()

    if (body != null) {
        return body
    }

    throw DataAccessException("Received response body was null")
}