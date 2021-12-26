package fr.sample.hexagonalarchitecture.core_characters.adapter.output.http

import fr.sample.hexagonalarchitecture.commons_io.OutputAdapterScopeMain
import fr.sample.hexagonalarchitecture.commons_io.OutputAdapterScopeWorker
import fr.sample.hexagonalarchitecture.commons_lang.wrapInResource
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterDetail
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class RESTCharactersOutputAdapterTest {
    private lateinit var restCharactersApi: RESTCharactersApi
    private lateinit var restCharactersOutputAdapter: RESTCharactersOutputAdapter

    @Before
    fun setUp() {
        val dispatcher = StandardTestDispatcher()
        Dispatchers.setMain(dispatcher)
        restCharactersApi = mockk(relaxed = true)
        restCharactersOutputAdapter = RESTCharactersOutputAdapter(
            OutputAdapterScopeMain(dispatcher),
            OutputAdapterScopeWorker(dispatcher),
            restCharactersApi,
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should fetch new characters`() = runTest {
        coEvery { restCharactersApi.getCharacterWith(any()) } returns RESTCharacter(
            id = "id1",
            name = "bob",
            isAlive = true,
        )

        assertThat(restCharactersOutputAdapter.getCharacterDetailWith("id1")).isEqualTo(
            CharacterDetail(id = "id1", name = "bob", isAlive = true)
        )
    }

    @Test
    fun `should propagate error`() = runTest {
        coEvery { restCharactersApi.getCharacterWith(any()) } throws java.lang.RuntimeException(
            "[500]"
        )

        val result = wrapInResource { restCharactersOutputAdapter.getCharacterDetailWith("id") }
        assertThat(result.exceptionOrNull())
            .isInstanceOf(RuntimeException::class.java)
            .hasMessage("[500]")
    }
}