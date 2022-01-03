package fr.sample.hexagonalarchitecture.core_characters.application.port.output

import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterDetail
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterId

interface GetCharacterDetailPort {
    suspend fun getCharacterDetailWith(characterId: CharacterId): CharacterDetail
}