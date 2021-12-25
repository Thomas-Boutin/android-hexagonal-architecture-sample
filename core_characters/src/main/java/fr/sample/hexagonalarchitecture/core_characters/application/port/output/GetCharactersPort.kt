package fr.sample.hexagonalarchitecture.core_characters.application.port.output

import fr.sample.hexagonalarchitecture.core_characters.domain.Character

interface GetCharactersPort {
    suspend fun getCharacters(): List<Character>
}