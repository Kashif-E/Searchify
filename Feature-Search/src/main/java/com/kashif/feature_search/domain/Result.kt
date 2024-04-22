package com.kashif.feature_search.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.MissingFieldException
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import java.io.IOException


sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val errorMessage: String = "") : Result<Nothing>
    data object Loading : Result<Nothing>

}

fun <T> Flow<T>.asResult(
): Flow<Result<T>> {
    return this.map<T, Result<T>> { Result.Success(it) }
        .onStart { emit(Result.Loading) }
        .catch {
            it.printStackTrace()
            emit(Result.Error(it.getRealExceptionString()))
        }
}


@OptIn(ExperimentalSerializationApi::class)
fun Throwable.getRealExceptionString() = when (this) {
    is HttpException -> {  // Handle errors based on HTTP status codes
        when (val statusCode = this.code()) {
            in 400..499 -> {
                "Client Error: HTTP $statusCode"
            }

            in 500..599 -> {
                "Server Error: HTTP $statusCode"
            }

            else -> {
                "Unexpected HTTP Response: HTTP $statusCode"
            }
        }
    }

    is MissingFieldException ->{
        "Result contained missing fields ${this.missingFields}."
    }

    is IOException -> {
        "Network Error"
    }

    is SerializationException -> {
        "Error Parsing Response Data"
    }

    else -> {
        "Unknown Error: ${this.message}"
    }
}


