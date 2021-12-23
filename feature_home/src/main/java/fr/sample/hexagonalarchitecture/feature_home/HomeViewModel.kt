package fr.sample.hexagonalarchitecture.feature_home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    var characters = mutableStateListOf(
        "bob",
        "pamela",
        "gaga"
    )
        private set
}