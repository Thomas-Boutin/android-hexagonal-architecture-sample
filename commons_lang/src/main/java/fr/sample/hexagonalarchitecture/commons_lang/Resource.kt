package fr.sample.hexagonalarchitecture.commons_lang

import timber.log.Timber

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()

    data class Error<T>(val throwable: Throwable) : Resource<T>()
    class Loading<T> : Resource<T>()
    class Idle<T> : Resource<T>()

    fun exceptionOrNull() = (this as? Error)?.throwable

    fun dataOrNull() = (this as? Success)?.data
}

inline fun <reified T> Resource<T>.onSuccess(action: (T) -> Unit): Resource<T> = when (this) {
    is Resource.Success -> apply { action(data) }
    is Resource.Error -> this
    is Resource.Loading -> this
    is Resource.Idle -> this
}

inline fun <reified T> Resource<T>.onError(action: (Throwable) -> Unit): Resource<T> = when (this) {
    is Resource.Success -> this
    is Resource.Error -> apply { action(throwable) }
    is Resource.Loading -> this
    is Resource.Idle -> this
}

inline fun <reified T> Resource<T>.onLoading(action: () -> Unit): Resource<T> = when (this) {
    is Resource.Success -> this
    is Resource.Error -> this
    is Resource.Loading -> apply { action() }
    is Resource.Idle -> this
}

suspend fun <T> wrapInResource(block: suspend () -> T): Resource<T> {
    return try {
        Resource.Success(block())
    } catch (e: Exception) {
        Timber.e(e)
        Resource.Error(e)
    }
}
