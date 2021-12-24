package fr.sample.hexagonalarchitecture.feature_home

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = HomeViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should have no characters on init`() = runTest {
        advanceUntilIdle()

        assertThat(viewModel.characters.value.getOrNull()).isEmpty()
    }

    @Test
    fun `should fetch new characters`() = runTest {
        viewModel.fetchCharacters()

        advanceUntilIdle()

        assertThat(viewModel.characters.value.getOrNull()).containsExactly(
            "bob",
            "pamela",
            "gaga"
        )
    }

    @Test
    fun `should propagate error`() = runTest {
        viewModel.fetchCharacters()

        advanceUntilIdle()

        assertThat(viewModel.characters.value.isFailure).isTrue()
    }
}