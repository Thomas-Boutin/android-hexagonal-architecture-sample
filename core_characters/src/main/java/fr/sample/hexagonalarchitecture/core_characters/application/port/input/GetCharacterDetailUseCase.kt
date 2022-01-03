package fr.sample.hexagonalarchitecture.core_characters.application.port.input

import fr.sample.hexagonalarchitecture.commons_lang.Resource
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterDetail
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterId

interface GetCharacterDetailUseCase {
    suspend fun getCharacterDetailWith(characterId: CharacterId): Resource<CharacterDetail>
}