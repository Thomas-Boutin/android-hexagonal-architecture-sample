package fr.sample.hexagonalarchitecture.core_characters.adapter.output.http

import fr.sample.hexagonalarchitecture.core_characters.application.port.output.GetCharactersPort
import fr.sample.hexagonalarchitecture.core_characters.domain.Character
import javax.inject.Inject

class GraphQLCharactersOutputAdapter @Inject constructor(): GetCharactersPort {
    override suspend fun getCharacters(): List<Character> {
        TODO("Not yet implemented")
    }
}