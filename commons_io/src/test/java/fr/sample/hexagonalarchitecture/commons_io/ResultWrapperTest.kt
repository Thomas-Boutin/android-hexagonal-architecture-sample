package fr.sample.hexagonalarchitecture.commons_io

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ResultWrapperTest {
    @Test
    fun `should wrap in success`() = runBlocking<Unit> {
        assertThat(wrapInResult { 1 }.getOrNull()).isEqualTo(1)
    }

    @Test
    fun `should wrap in failure`() = runBlocking<Unit> {
        assertThat(wrapInResult { throw Exception("Error") }.exceptionOrNull())
            .isExactlyInstanceOf(Exception::class.java)
            .hasMessage("Error")
    }
}