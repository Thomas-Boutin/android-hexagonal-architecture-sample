package fr.sample.hexagonalarchitecture.commons_lang

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ResourceTest {
    @Test
    fun `should wrap in success`() = runBlocking<Unit> {
        val result = wrapInResource {
            1 + 1
        }

        assertThat(result is Resource.Success<Int>).isTrue
    }

    @Test
    fun `should wrap in error`() = runBlocking<Unit> {
        val result = wrapInResource {
            throw IllegalArgumentException()
        }

        assertThat(result is Resource.Error).isTrue
    }

    @Test
    fun `should return exception`() = runBlocking<Unit> {
        val result = wrapInResource {
            throw IllegalArgumentException()
        }

        assertThat(result.exceptionOrNull())
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `should return no exception when is idle`() {
        assertThat(Resource.Idle<Any>().exceptionOrNull()).isNull()
    }

    @Test
    fun `should return no exception when is success`() {
        assertThat(Resource.Success<Any>(1).exceptionOrNull()).isNull()
    }

    @Test
    fun `should return no exception when is loading`() {
        assertThat(Resource.Loading<Any>().exceptionOrNull()).isNull()
    }

    @Test
    fun `should return data`() = runBlocking<Unit> {
        val result = wrapInResource {
            1
        }

        assertThat(result.dataOrNull())
            .isEqualTo(1)
    }

    @Test
    fun `should return no data when is idle`() {
        assertThat(Resource.Idle<Any>().dataOrNull()).isNull()
    }

    @Test
    fun `should return no data when is error`() {
        assertThat(Resource.Error<Any>(RuntimeException()).dataOrNull()).isNull()
    }

    @Test
    fun `should data no exception when is loading`() {
        assertThat(Resource.Loading<Any>().dataOrNull()).isNull()
    }

    @Test
    fun `should perform on success when resource is success`() {
        var integer = 1

        Resource.Success(1).onSuccess {
            integer += 1
        }

        assertThat(integer).isEqualTo(2)
    }

    @Test
    fun `should not perform on success when resource is idle`() {
        var integer = 1

        Resource.Idle<Int>().onSuccess {
            integer += 1
        }

        assertThat(integer).isEqualTo(1)
    }

    @Test
    fun `should not perform on success when resource is error`() {
        var integer = 1

        Resource.Error<Int>(RuntimeException()).onSuccess {
            integer += 1
        }

        assertThat(integer).isEqualTo(1)
    }

    @Test
    fun `should not perform on success when resource is loading`() {
        var integer = 1

        Resource.Loading<Int>().onSuccess { integer += 1 }

        assertThat(integer).isEqualTo(1)
    }

    @Test
    fun `should perform on error when resource is error`() {
        var integer = 1

        Resource.Error<Int>(RuntimeException()).onError { integer += 1 }

        assertThat(integer).isEqualTo(2)
    }

    @Test
    fun `should not perform on error when resource is idle`() {
        var integer = 1

        Resource.Idle<Int>().onError { integer += 1 }

        assertThat(integer).isEqualTo(1)
    }

    @Test
    fun `should not perform on error when resource is success`() {
        var integer = 1

        Resource.Success(1).onError { integer += 1 }

        assertThat(integer).isEqualTo(1)
    }

    @Test
    fun `should not perform on error when resource is loading`() {
        var integer = 1

        Resource.Loading<Int>().onError {
            integer += 1
        }

        assertThat(integer).isEqualTo(1)
    }

    @Test
    fun `should perform on loading when resource is loading`() {
        var integer = 1

        Resource.Loading<Int>().onLoading { integer += 1 }

        assertThat(integer).isEqualTo(2)
    }

    @Test
    fun `should not perform on loading when resource is idle`() {
        var integer = 1

        Resource.Idle<Int>().onLoading { integer += 1 }

        assertThat(integer).isEqualTo(1)
    }

    @Test
    fun `should not perform on loading when resource is success`() {
        var integer = 1

        Resource.Success(1).onLoading { integer += 1 }

        assertThat(integer).isEqualTo(1)
    }

    @Test
    fun `should not perform on loading when resource is error`() {
        var integer = 1

        Resource.Error<Int>(RuntimeException()).onLoading {
            integer += 1
        }

        assertThat(integer).isEqualTo(1)
    }
}