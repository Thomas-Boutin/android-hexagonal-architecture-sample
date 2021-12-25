package fr.sample.hexagonalarchitecture.core_characters.adapter.input

import fr.sample.hexagonalarchitecture.commons_io.InputAdapterScope
import fr.sample.hexagonalarchitecture.core_characters.domain.Character
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
    private lateinit var charactersInputAdapter: CharactersInputAdapter

    @Before
    fun setUp() {
        val scheduler = StandardTestDispatcher()
        Dispatchers.setMain(scheduler)
        charactersInputAdapter = CharactersInputAdapter(InputAdapterScope(scheduler))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should fetch new characters`() = runTest {
        /*coEvery { charactersInputAdapter.getCharacters() } returns Result.success(
            listOf(
                Character("bob"),
                Character("pamela"),
                Character("gaga")
            )
        )*/
        assertThat(charactersInputAdapter.getCharacters().getOrNull()).containsExactly(
            Character("bob"),
            Character("pamela"),
            Character("gaga")
        )
    }

    @Test
    fun `should propagate error`() = runTest {
        /*coEvery { charactersInputAdapter.getCharacters() } returns Result.failure(
            Exception("Unable to fetch characters")
        )*/

        assertThat(charactersInputAdapter.getCharacters().exceptionOrNull())
            .isExactlyInstanceOf(Exception::class.java)
            .hasMessage("Unable to fetch characters")
    }
}