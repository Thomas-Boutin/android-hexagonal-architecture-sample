package fr.sample.hexagonalarchitecture.core_characters.adapter.input

import fr.sample.hexagonalarchitecture.commons_io.InputAdapterScope
import fr.sample.hexagonalarchitecture.commons_lang.Resource
import fr.sample.hexagonalarchitecture.core_characters.application.port.input.GetCharactersUseCase
import fr.sample.hexagonalarchitecture.core_characters.domain.Character
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

class CharactersInputAdapterTest {
    private lateinit var getCharactersUseCase: GetCharactersUseCase
    private lateinit var charactersInputAdapter: CharactersInputAdapter

    @Before
    fun setUp() {
        val dispatcher = StandardTestDispatcher()
        Dispatchers.setMain(dispatcher)
        getCharactersUseCase = mockk(relaxed = true)
        charactersInputAdapter = CharactersInputAdapter(InputAdapterScope(dispatcher), getCharactersUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should fetch new characters`() = runTest {
        coEvery { getCharactersUseCase.getCharacters() } returns Resource.Success(
            listOf(
                Character(id = "id1", name = "bob"),
                Character(id = "id2", name = "pamela"),
                Character(id = "id3", name = "gaga")
            )
        )
        assertThat(charactersInputAdapter.getCharacters().dataOrNull()).containsExactly(
            Character(id = "id1", name = "bob"),
            Character(id = "id2", name = "pamela"),
            Character(id = "id3", name = "gaga")
        )
    }

    @Test
    fun `should propagate error`() = runTest {
        coEvery { getCharactersUseCase.getCharacters() } returns Resource.Error(
            Exception("Unable to fetch characters")
        )

        assertThat(charactersInputAdapter.getCharacters().exceptionOrNull())
            .isExactlyInstanceOf(Exception::class.java)
            .hasMessage("Unable to fetch characters")
    }
}