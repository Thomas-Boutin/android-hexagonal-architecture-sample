package fr.sample.hexagonalarchitecture.core_characters.application.port

import fr.sample.hexagonalarchitecture.commons_lang.wrapInResult
import fr.sample.hexagonalarchitecture.core_characters.application.port.input.GetCharactersUseCase
import fr.sample.hexagonalarchitecture.core_characters.application.port.output.GetCharactersPort
import fr.sample.hexagonalarchitecture.core_characters.domain.Character
import javax.inject.Inject

class CharactersService @Inject constructor(
    private val getCharactersPort: GetCharactersPort
) : GetCharactersUseCase {
    override suspend fun getCharacters(): Result<List<Character>> {
        return wrapInResult {
            getCharactersPort.getCharacters()
        }
    }
}