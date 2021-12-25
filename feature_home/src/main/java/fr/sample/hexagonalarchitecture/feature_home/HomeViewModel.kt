package fr.sample.hexagonalarchitecture.feature_home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.sample.hexagonalarchitecture.core_characters.adapter.input.CharactersInputAdapter
import fr.sample.hexagonalarchitecture.core_characters.domain.Character
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val charactersInputAdapter: CharactersInputAdapter,
) : ViewModel() {
    var characters = mutableStateOf(
        Result.success(
            emptyList<Character>()
        )
    )
        private set

    fun fetchCharacters() = viewModelScope.launch {
        characters.value = charactersInputAdapter.getCharacters()
    }
}