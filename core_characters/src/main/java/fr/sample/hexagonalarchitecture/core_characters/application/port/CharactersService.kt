package fr.sample.hexagonalarchitecture.core_characters.application.port

import fr.sample.hexagonalarchitecture.core_characters.application.port.input.GetCharactersUseCase
import fr.sample.hexagonalarchitecture.core_characters.domain.Character

class CharactersService: GetCharactersUseCase {
    override fun getCharacters(): Result<List<Character>> {
        TODO("Not yet implemented")
    }
}