package fr.sample.hexagonalarchitecture.feature_home

import fr.sample.hexagonalarchitecture.core_characters.adapter.input.CharactersInputAdapter
import fr.sample.hexagonalarchitecture.core_characters.domain.Character
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {
    private lateinit var charactersInputAdapter: CharactersInputAdapter
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        charactersInputAdapter = mockk(relaxed = true)
        viewModel = HomeViewModel(charactersInputAdapter)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should have no characters on init`() = runTest {
        assertThat(viewModel.characters.value.getOrNull()).isEmpty()
    }

    @Test
    fun `should fetch new characters`() = runTest {
        coEvery { charactersInputAdapter.getCharacters() } returns Result.success(
            listOf(
                Character("bob"),
                Character("pamela"),
                Character("gaga")
            )
        )
        viewModel.fetchCharacters()

        advanceUntilIdle()

        assertThat(viewModel.characters.value.getOrNull()).containsExactly(
            Character("bob"),
            Character("pamela"),
            Character("gaga")
        )
    }

    @Test
    fun `should propagate error`() = runTest {
        coEvery { charactersInputAdapter.getCharacters() } returns Result.failure(
            Exception("Unable to fetch characters")
        )
        viewModel.fetchCharacters()

        advanceUntilIdle()

        assertThat(viewModel.characters.value.isFailure).isTrue
        assertThat(viewModel.characters.value.exceptionOrNull())
            .isExactlyInstanceOf(Exception::class.java)
            .hasMessage("Unable to fetch characters")
    }
}