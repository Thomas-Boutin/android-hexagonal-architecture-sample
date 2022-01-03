package fr.sample.hexagonalarchitecture.feature_home

import fr.sample.hexagonalarchitecture.commons_lang.Resource
import fr.sample.hexagonalarchitecture.core_characters.adapter.input.CharactersInputAdapter
import fr.sample.hexagonalarchitecture.core_characters.domain.Character
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterId
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterName
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
        assertThat(viewModel.characters.value).isExactlyInstanceOf(Resource.Idle::class.java)
    }

    @Test
    fun `should fetch new characters`() = runTest {
        coEvery { charactersInputAdapter.getCharacters() } returns Resource.Success(
            listOf(
                Character(id = CharacterId("id1"), name = CharacterName("bob")),
                Character(id = CharacterId("id2"), name = CharacterName("pamela")),
                Character(id = CharacterId("id3"), name = CharacterName("gaga"))
            )
        )
        viewModel.fetchCharacters()

        advanceUntilIdle()

        assertThat(viewModel.characters.value.dataOrNull()).containsExactly(
            Character(id = CharacterId("id1"), name = CharacterName("bob")),
            Character(id = CharacterId("id2"), name = CharacterName("pamela")),
            Character(id = CharacterId("id3"), name = CharacterName("gaga"))
        )
    }

    @Test
    fun `should propagate error`() = runTest {
        coEvery { charactersInputAdapter.getCharacters() } returns Resource.Error(
            Exception("Unable to fetch characters")
        )
        viewModel.fetchCharacters()

        advanceUntilIdle()

        assertThat(viewModel.characters.value).isExactlyInstanceOf(Resource.Error::class.java)
        assertThat(viewModel.characters.value.exceptionOrNull())
            .isExactlyInstanceOf(Exception::class.java)
            .hasMessage("Unable to fetch characters")
    }
}