package fr.sample.hexagonalarchitecture.feature_character_detail

import fr.sample.hexagonalarchitecture.commons_lang.Resource
import fr.sample.hexagonalarchitecture.core_characters.adapter.input.CharactersInputAdapter
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterDetail
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class CharacterDetailViewModelTest {
    private lateinit var charactersInputAdapter: CharactersInputAdapter
    private lateinit var viewModel: CharacterDetailViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        charactersInputAdapter = mockk(relaxed = true)
        viewModel = CharacterDetailViewModel(charactersInputAdapter)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should have no characters on init`() = runTest {
        assertThat(viewModel.characterDetail.value).isExactlyInstanceOf(Resource.Idle::class.java)
    }

    @Test
    fun `should fetch new characters`() = runTest {
        coEvery { charactersInputAdapter.getCharacterDetailWith(any()) } returns Resource.Success(
            CharacterDetail(id = "id1", name = "bob", isAlive = true),
        )
        viewModel.fetchCharacterDetailWith("id1")

        advanceUntilIdle()

        assertThat(viewModel.characterDetail.value.dataOrNull()).isEqualTo(
            CharacterDetail(id = "id1", name = "bob", isAlive = true),
        )
    }

    @Test
    fun `should propagate error`() = runTest {
        coEvery { charactersInputAdapter.getCharacterDetailWith(any()) } returns Resource.Error(
            Exception("Unable to fetch characters")
        )
        viewModel.fetchCharacterDetailWith("id1")

        advanceUntilIdle()

        assertThat(viewModel.characterDetail.value).isExactlyInstanceOf(Resource.Error::class.java)
        assertThat(viewModel.characterDetail.value.exceptionOrNull())
            .isExactlyInstanceOf(Exception::class.java)
            .hasMessage("Unable to fetch characters")
    }
}