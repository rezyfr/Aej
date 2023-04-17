package com.rezyfr.aej.utils

import com.rezyfr.aej.core.network.response.ErrorResponse
import com.rezyfr.aej.core.network.response.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

sealed class ResultState<out T> {
    object Initial : ResultState<Nothing>()
    object Loading : ResultState<Nothing>()
    data class Failure(val message: String) : ResultState<Nothing>()
    data class Success<T>(val data: T) : ResultState<T>()
    object Empty : ResultState<Nothing>()

    /**
     * Returns the data if this instance represents [Success], otherwise `null`
     */
    fun getOrNull(): T? =
        when (this) {
            is Success -> data
            else -> null
        }

    fun isSuccess(): Boolean = this is Success
    fun isLoading(): Boolean = this is Loading
    fun isFailure(): Boolean = this is Failure

    companion object {
        fun initial() = Initial
        fun loading() = Loading
        fun failure(message: String) = Failure(message)
        fun <T> success(data: T?) = if (data == null) Empty else Success(data)
    }
}

fun <R, T> fetchNetwork(
    fetch: suspend () -> NetworkResponse<T, ErrorResponse>,
    onSuccess: suspend (T) -> R
): Flow<ResultState<R>> = flow {
    emit(ResultState.Loading)
    when (val response = fetch()) {
        is NetworkResponse.Success -> {
            emit(ResultState.Success(onSuccess(response.body)))
        }
        is NetworkResponse.ServerError -> emit(
            ResultState.Failure(
                response.body?.message.orEmpty()
            )
        )
        is NetworkResponse.NetworkError -> emit(ResultState.Failure(response.error.message.orEmpty()))
        is NetworkResponse.UnknownError -> emit(
            ResultState.Failure(
                response.error.message.orEmpty(),
            )
        )
    }
}.flowOn(Dispatchers.IO)