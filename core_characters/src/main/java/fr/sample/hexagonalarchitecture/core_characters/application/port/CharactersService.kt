package fr.sample.hexagonalarchitecture.core_characters.application.port

import fr.sample.hexagonalarchitecture.commons_io.wrapInResult
import fr.sample.hexagonalarchitecture.core_characters.application.port.input.GetCharactersUseCase
import fr.sample.hexagonalarchitecture.core_characters.domain.Character
import javax.inject.Inject

class CharactersService @Inject constructor() : GetCharactersUseCase {
    override suspend fun getCharacters(): Result<List<Character>> {
        return wrapInResult {
            listOf(
                Character("bob"),
                Character("gaga"),
                Character("pamela")
            )
        }
    }
}