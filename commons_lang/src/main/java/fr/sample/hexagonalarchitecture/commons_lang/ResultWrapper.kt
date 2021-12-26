package fr.sample.hexagonalarchitecture.commons_lang

suspend fun <T> wrapInResult(block: suspend () -> T): Result<T> {
    return try {
        Result.success(block())
    } catch (e: Exception) {
        Result.failure(e)
    }
}