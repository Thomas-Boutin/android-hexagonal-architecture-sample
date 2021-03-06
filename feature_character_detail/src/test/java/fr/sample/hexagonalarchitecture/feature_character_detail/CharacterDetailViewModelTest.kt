package fr.sample.hexagonalarchitecture.feature_character_detail

import fr.sample.hexagonalarchitecture.commons_lang.Resource
import fr.sample.hexagonalarchitecture.core_characters.adapter.input.CharactersInputAdapter
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterDetail
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterId
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterName
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterStatus
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
        coEvery { charactersInputAdapter.getCharacterDetailWith(CharacterId(any())) } returns Resource.Success(
            CharacterDetail(id = CharacterId("id1"), name = CharacterName("bob"), status = CharacterStatus.Dead),
        )
        viewModel.fetchCharacterDetailWith(CharacterId("id1"))

        advanceUntilIdle()

        assertThat(viewModel.characterDetail.value.dataOrNull()).isEqualTo(
            CharacterDetail(id = CharacterId("id1"), name = CharacterName("bob"), status = CharacterStatus.Dead),
        )
    }

    @Test
    fun `should propagate error`() = runTest {
        coEvery { charactersInputAdapter.getCharacterDetailWith(CharacterId(any())) } returns Resource.Error(
            Exception("Unable to fetch characters")
        )
        viewModel.fetchCharacterDetailWith(CharacterId("id1"))

        advanceUntilIdle()

        assertThat(viewModel.characterDetail.value).isExactlyInstanceOf(Resource.Error::class.java)
        assertThat(viewModel.characterDetail.value.exceptionOrNull())
            .isExactlyInstanceOf(Exception::class.java)
            .hasMessage("Unable to fetch characters")
    }
}