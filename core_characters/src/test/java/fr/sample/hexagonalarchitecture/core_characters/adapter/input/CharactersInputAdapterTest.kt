package fr.sample.hexagonalarchitecture.core_characters.adapter.input

import fr.sample.hexagonalarchitecture.commons_io.InputAdapterScope
import fr.sample.hexagonalarchitecture.commons_lang.Resource
import fr.sample.hexagonalarchitecture.core_characters.application.port.input.GetCharacterDetailUseCase
import fr.sample.hexagonalarchitecture.core_characters.application.port.input.GetCharactersUseCase
import fr.sample.hexagonalarchitecture.core_characters.domain.Character
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterDetail
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterId
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterName
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
    private lateinit var getCharacterDetailUseCase: GetCharacterDetailUseCase
    private lateinit var charactersInputAdapter: CharactersInputAdapter

    @Before
    fun setUp() {
        val dispatcher = StandardTestDispatcher()
        Dispatchers.setMain(dispatcher)
        getCharactersUseCase = mockk(relaxed = true)
        getCharacterDetailUseCase = mockk(relaxed = true)
        charactersInputAdapter = CharactersInputAdapter(
            InputAdapterScope(dispatcher),
            getCharactersUseCase,
            getCharacterDetailUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should fetch new characters`() = runTest {
        coEvery { getCharactersUseCase.getCharacters() } returns Resource.Success(
            listOf(
                Character(id = CharacterId("id1"), name = CharacterName("bob")),
                Character(id = CharacterId("id2"), name = CharacterName("pamela")),
                Character(id = CharacterId("id3"), name = CharacterName("gaga"))
            )
        )
        assertThat(charactersInputAdapter.getCharacters().dataOrNull()).containsExactly(
            Character(id = CharacterId("id1"), name = CharacterName("bob")),
            Character(id = CharacterId("id2"), name = CharacterName("pamela")),
            Character(id = CharacterId("id3"), name = CharacterName("gaga"))
        )
    }

    @Test
    fun `should propagate error when fetches new characters`() = runTest {
        coEvery { getCharactersUseCase.getCharacters() } returns Resource.Error(
            Exception("Unable to fetch characters")
        )

        assertThat(charactersInputAdapter.getCharacters().exceptionOrNull())
            .isExactlyInstanceOf(Exception::class.java)
            .hasMessage("Unable to fetch characters")
    }

    @Test
    fun `should fetch character's detail`() = runTest {
        coEvery { getCharacterDetailUseCase.getCharacterDetailWith(any()) } returns Resource.Success(
            CharacterDetail(id = CharacterId("id1"), name = CharacterName("bob"), status = "dead")
        )
        assertThat(charactersInputAdapter.getCharacterDetailWith("id").dataOrNull()).isEqualTo(
            CharacterDetail(id = CharacterId("id1"), name = CharacterName("bob"), status = "dead")
        )
    }

    @Test
    fun `should propagate error when fetches character detail`() = runTest {
        coEvery { getCharacterDetailUseCase.getCharacterDetailWith(any()) } returns Resource.Error(
            Exception("Unable to fetch characters")
        )

        assertThat(charactersInputAdapter.getCharacterDetailWith("id").exceptionOrNull())
            .isExactlyInstanceOf(Exception::class.java)
            .hasMessage("Unable to fetch character detail")
    }
}