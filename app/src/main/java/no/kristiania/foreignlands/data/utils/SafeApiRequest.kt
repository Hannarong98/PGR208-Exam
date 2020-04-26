package no.kristiania.foreignlands.data.utils

import retrofit2.Response
import java.io.IOException

// This helper class is taken from
// https://github.com/probelalkhan/recyclerview-mvvm/blob/master/app/src/main/java/net/simplifiedcoding/data/repositories/SafeApiRequest.kt

abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T? {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw ApiException(
                response.code().toString()
            )
        }
    }
}

class ApiException(message: String) : IOException(message)