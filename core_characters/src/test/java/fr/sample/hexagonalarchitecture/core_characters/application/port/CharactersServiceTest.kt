package fr.sample.hexagonalarchitecture.core_characters.application.port

import fr.sample.hexagonalarchitecture.core_characters.application.port.output.GetCharactersPort
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

class CharactersServiceTest {
    private lateinit var getCharactersPort: GetCharactersPort
    private lateinit var charactersService: CharactersService

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        getCharactersPort = mockk(relaxed = true)
        charactersService = CharactersService(getCharactersPort)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should fetch new characters`() = runTest {
        coEvery { getCharactersPort.getCharacters() } returns listOf(
            Character(id = "id1", name = "bob"),
            Character(id = "id2", name = "pamela"),
            Character(id = "id3", name = "gaga")
        )
        assertThat(charactersService.getCharacters().dataOrNull()).containsExactly(
            Character(id = "id1", name = "bob"),
            Character(id = "id2", name = "pamela"),
            Character(id = "id3", name = "gaga")
        )
    }

    @Test
    fun `should propagate error`() = runTest {
        coEvery { getCharactersPort.getCharacters() } throws Exception("Unable to fetch characters")

        assertThat(charactersService.getCharacters().exceptionOrNull())
            .isExactlyInstanceOf(Exception::class.java)
            .hasMessage("Unable to fetch characters")
    }
}