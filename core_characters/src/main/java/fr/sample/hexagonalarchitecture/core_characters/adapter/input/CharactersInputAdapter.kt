package fr.sample.hexagonalarchitecture.core_characters.adapter.input

import fr.sample.hexagonalarchitecture.commons_io.InputAdapter
import fr.sample.hexagonalarchitecture.commons_io.InputAdapterScope
import javax.inject.Inject

class CharactersInputAdapter @Inject constructor(override val adapterScope: InputAdapterScope) : InputAdapter {
}