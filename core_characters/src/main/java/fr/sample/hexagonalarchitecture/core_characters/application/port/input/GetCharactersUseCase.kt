package fr.sample.hexagonalarchitecture.core_characters.application.port.input

import fr.sample.hexagonalarchitecture.commons_lang.Resource
import fr.sample.hexagonalarchitecture.core_characters.domain.Character

interface GetCharactersUseCase {
    suspend fun getCharacters(): Resource<List<Character>>
}