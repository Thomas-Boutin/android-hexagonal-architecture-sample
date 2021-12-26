package fr.sample.hexagonalarchitecture.feature_character_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.sample.hexagonalarchitecture.core_characters.adapter.input.CharactersInputAdapter
import fr.sample.hexagonalarchitecture.core_characters.domain.Character
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val charactersInputAdapter: CharactersInputAdapter,
) : ViewModel() {
    var character = mutableStateOf<Result<Character>>(
        Result.failure(Exception())
    )
        private set

    fun fetchCharacters() = viewModelScope.launch {
        //characters.value = charactersInputAdapter.getCharacters()
    }
}