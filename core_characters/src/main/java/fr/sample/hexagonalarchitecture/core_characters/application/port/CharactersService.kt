package fr.sample.hexagonalarchitecture.core_characters.application.port

import fr.sample.hexagonalarchitecture.commons_lang.Resource
import fr.sample.hexagonalarchitecture.commons_lang.wrapInResource
import fr.sample.hexagonalarchitecture.core_characters.application.port.input.GetCharacterDetailUseCase
import fr.sample.hexagonalarchitecture.core_characters.application.port.input.GetCharactersUseCase
import fr.sample.hexagonalarchitecture.core_characters.application.port.output.GetCharacterDetailPort
import fr.sample.hexagonalarchitecture.core_characters.application.port.output.GetCharactersPort
import fr.sample.hexagonalarchitecture.core_characters.domain.Character
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterDetail
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterId
import javax.inject.Inject

class CharactersService @Inject constructor(
    private val getCharactersPort: GetCharactersPort,
    private val getCharacterDetailPort: GetCharacterDetailPort,
) : GetCharactersUseCase, GetCharacterDetailUseCase {
    override suspend fun getCharacters(): Resource<List<Character>> {
        return wrapInResource {
            getCharactersPort.getCharacters()
        }
    }

    override suspend fun getCharacterDetailWith(characterId: CharacterId): Resource<CharacterDetail> {
        return wrapInResource {
            getCharacterDetailPort.getCharacterDetailWith(characterId)
        }
    }
}