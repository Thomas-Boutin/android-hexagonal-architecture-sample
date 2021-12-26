package fr.sample.hexagonalarchitecture.core_characters.application.port.input

import fr.sample.hexagonalarchitecture.commons_lang.Resource
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterDetail

interface GetCharacterDetailUseCase {
    suspend fun getCharacterDetailWith(characterId: String): Resource<CharacterDetail>
}