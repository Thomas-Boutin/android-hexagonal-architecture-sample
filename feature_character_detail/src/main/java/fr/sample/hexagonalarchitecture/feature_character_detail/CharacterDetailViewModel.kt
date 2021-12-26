package fr.sample.hexagonalarchitecture.feature_character_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.sample.hexagonalarchitecture.commons_lang.Resource
import fr.sample.hexagonalarchitecture.core_characters.adapter.input.CharactersInputAdapter
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterDetail
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val charactersInputAdapter: CharactersInputAdapter,
) : ViewModel() {
    var characterDetail = mutableStateOf<Resource<CharacterDetail>>(
        Resource.Idle()
    )
        private set

    fun fetchCharacterDetailWith(characterId: String) = viewModelScope.launch {
        characterDetail.value = charactersInputAdapter.getCharacterDetailWith(characterId)
    }
}