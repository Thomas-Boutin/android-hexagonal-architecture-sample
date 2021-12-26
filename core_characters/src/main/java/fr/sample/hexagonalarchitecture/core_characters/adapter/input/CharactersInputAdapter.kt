package fr.sample.hexagonalarchitecture.core_characters.adapter.input

import fr.sample.hexagonalarchitecture.commons_io.InputAdapter
import fr.sample.hexagonalarchitecture.commons_io.InputAdapterScope
import fr.sample.hexagonalarchitecture.commons_lang.Resource
import fr.sample.hexagonalarchitecture.core_characters.application.port.input.GetCharactersUseCase
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterDetail
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharactersInputAdapter @Inject constructor(
    override val adapterScope: InputAdapterScope,
    private val getCharactersUseCase: GetCharactersUseCase,
) : InputAdapter {

    suspend fun getCharacters() = withContext(adapterScope.coroutineContext) {
        getCharactersUseCase.getCharacters()
    }

    suspend fun getCharacterDetail(characterId: String) = withContext<Resource<CharacterDetail>>(adapterScope.coroutineContext) {
        Resource.Success(CharacterDetail(id = "id1", name = "bob", isAlive = true))
    }
}