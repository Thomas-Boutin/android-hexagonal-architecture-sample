package fr.sample.hexagonalarchitecture.commons_lang

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()

    data class Error<T>(val throwable: Throwable) : Resource<T>()
    class Loading<T> : Resource<T>()
    class Idle<T> : Resource<T>()

    fun exceptionOrNull() = (this as? Error)?.throwable

    fun dataOrNull() = (this as? Success)?.data

    fun onSuccess(action: (T) -> Unit): Resource<T> = when (this) {
        is Success -> apply { action(data) }
        is Error -> this
        is Loading -> this
        is Idle -> this
    }

    fun onError(action: (Throwable) -> Unit): Resource<T> = when (this) {
        is Success -> this
        is Error -> apply { action(throwable) }
        is Loading -> this
        is Idle -> this
    }

    fun onLoading(action: () -> Unit): Resource<T> = when (this) {
        is Success -> this
        is Error -> this
        is Loading -> apply { action() }
        is Idle -> this
    }
}

suspend fun <T> wrapInResource(block: suspend () -> T): Resource<T> {
    return try {
        Resource.Success(block())
    } catch (e: Exception) {
        Resource.Error(e)
    }
}
