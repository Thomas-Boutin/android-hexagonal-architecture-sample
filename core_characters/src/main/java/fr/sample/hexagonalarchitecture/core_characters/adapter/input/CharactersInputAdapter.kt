package fr.sample.hexagonalarchitecture.core_characters.adapter.input

import fr.sample.hexagonalarchitecture.commons_io.InputAdapter
import fr.sample.hexagonalarchitecture.commons_io.InputAdapterScope
import fr.sample.hexagonalarchitecture.core_characters.domain.Character
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharactersInputAdapter @Inject constructor(
    override val adapterScope: InputAdapterScope
) :
    InputAdapter {

    suspend fun getCharacters() = withContext(adapterScope.coroutineContext) {
        Result.success(
            listOf(
                Character("bob"),
                Character("pamela"),
                Character("gaga")
            )
        )
    }
}