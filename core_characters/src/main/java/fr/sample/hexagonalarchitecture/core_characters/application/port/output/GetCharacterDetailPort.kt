package fr.sample.hexagonalarchitecture.core_characters.application.port.output

import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterDetail

interface GetCharacterDetailPort {
    suspend fun getCharacterDetailWith(characterId: String): CharacterDetail
}