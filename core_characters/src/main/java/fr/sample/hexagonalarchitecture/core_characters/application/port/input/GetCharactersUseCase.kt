package fr.sample.hexagonalarchitecture.core_characters.application.port.input

import fr.sample.hexagonalarchitecture.core_characters.domain.Character

interface GetCharactersUseCase {
    fun getCharacters(): Result<List<Character>>
}