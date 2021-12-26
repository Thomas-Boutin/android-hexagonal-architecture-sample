package fr.sample.hexagonalarchitecture.core_characters.adapter.output.http

import fr.sample.hexagonalarchitecture.core_characters.application.port.output.GetCharacterDetailPort
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterDetail
import javax.inject.Inject

class RESTCharactersOutputAdapter @Inject constructor(): GetCharacterDetailPort {
    override suspend fun getCharacterDetailWith(characterId: String): CharacterDetail {
        TODO("Not yet implemented")
    }
}