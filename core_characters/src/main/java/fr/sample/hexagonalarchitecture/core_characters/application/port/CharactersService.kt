package fr.sample.hexagonalarchitecture.core_characters.application.port

import fr.sample.hexagonalarchitecture.commons_lang.Resource
import fr.sample.hexagonalarchitecture.commons_lang.wrapInResource
import fr.sample.hexagonalarchitecture.core_characters.application.port.input.GetCharactersUseCase
import fr.sample.hexagonalarchitecture.core_characters.application.port.output.GetCharactersPort
import fr.sample.hexagonalarchitecture.core_characters.domain.Character
import javax.inject.Inject

class CharactersService @Inject constructor(
    private val getCharactersPort: GetCharactersPort
) : GetCharactersUseCase {
    override suspend fun getCharacters(): Resource<List<Character>> {
        return wrapInResource {
            getCharactersPort.getCharacters()
        }
    }
}