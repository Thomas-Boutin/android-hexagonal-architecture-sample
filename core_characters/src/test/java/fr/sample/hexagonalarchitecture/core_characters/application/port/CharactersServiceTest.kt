package fr.sample.hexagonalarchitecture.core_characters.application.port

import fr.sample.hexagonalarchitecture.core_characters.application.port.output.GetCharacterDetailPort
import fr.sample.hexagonalarchitecture.core_characters.application.port.output.GetCharactersPort
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

class CharactersServiceTest {
    private lateinit var getCharactersPort: GetCharactersPort
    private lateinit var getCharacterDetailPort: GetCharacterDetailPort
    private lateinit var charactersService: CharactersService

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        getCharactersPort = mockk(relaxed = true)
        getCharacterDetailPort = mockk(relaxed = true)
        charactersService = CharactersService(getCharactersPort, getCharacterDetailPort)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should fetch new characters`() = runTest {
        coEvery { getCharactersPort.getCharacters() } returns listOf(
            Character(id = CharacterId("id1"), name = CharacterName("bob")),
            Character(id = CharacterId("id2"), name = CharacterName("pamela")),
            Character(id = CharacterId("id3"), name = CharacterName("gaga"))
        )
        assertThat(charactersService.getCharacters().dataOrNull()).containsExactly(
            Character(id = CharacterId("id1"), name = CharacterName("bob")),
            Character(id = CharacterId("id2"), name = CharacterName("pamela")),
            Character(id = CharacterId("id3"), name = CharacterName("gaga"))
        )
    }

    @Test
    fun `should propagate error when fetching new characters`() = runTest {
        coEvery { getCharactersPort.getCharacters() } throws Exception("Unable to fetch characters")

        assertThat(charactersService.getCharacters().exceptionOrNull())
            .isExactlyInstanceOf(Exception::class.java)
            .hasMessage("Unable to fetch characters")
    }

    @Test
    fun `should fetch character's detail`() = runTest {
        coEvery { getCharacterDetailPort.getCharacterDetailWith(any()) } returns CharacterDetail(
            id = CharacterId("id"),
            name = CharacterName("bob"),
            status = "dead",
        )
        assertThat(charactersService.getCharacterDetailWith("id").dataOrNull()).isEqualTo(
            CharacterDetail(
                id = CharacterId("id"),
                name = CharacterName("bob"),
                status = "dead",
            )
        )
    }

    @Test
    fun `should propagate error when fetching character's detail`() = runTest {
        coEvery { getCharacterDetailPort.getCharacterDetailWith(any()) } throws Exception("Unable to fetch character detail")

        assertThat(charactersService.getCharacterDetailWith("id").exceptionOrNull())
            .isExactlyInstanceOf(Exception::class.java)
            .hasMessage("Unable to fetch character detail")
    }
}