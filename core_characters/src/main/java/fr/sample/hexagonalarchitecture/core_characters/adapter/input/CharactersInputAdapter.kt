package fr.sample.hexagonalarchitecture.core_characters.adapter.input

import fr.sample.hexagonalarchitecture.commons_io.InputAdapter
import fr.sample.hexagonalarchitecture.commons_io.InputAdapterScope
import fr.sample.hexagonalarchitecture.core_characters.application.port.input.GetCharacterDetailUseCase
import fr.sample.hexagonalarchitecture.core_characters.application.port.input.GetCharactersUseCase
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharactersInputAdapter @Inject constructor(
    override val adapterScope: InputAdapterScope,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
) : InputAdapter {

    suspend fun getCharacters() = withContext(adapterScope.coroutineContext) {
        getCharactersUseCase.getCharacters()
    }

    suspend fun getCharacterDetailWith(characterId: String) = withContext(adapterScope.coroutineContext) {
        getCharacterDetailUseCase.getCharacterDetailWith(characterId)
    }
}