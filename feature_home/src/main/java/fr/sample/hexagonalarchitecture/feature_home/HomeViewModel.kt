package fr.sample.hexagonalarchitecture.feature_home

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    var characters = mutableStateOf<Result<List<String>>>(Result.success(emptyList()))
        private set

    fun fetchCharacters() = viewModelScope.launch {
        characters.value = Result.success(
            listOf(
                "bob",
                "pamela",
                "gaga"
            )
        )
    }
}