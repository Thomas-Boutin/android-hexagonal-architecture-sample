package fr.sample.hexagonalarchitecture.feature_home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import fr.sample.hexagonalarchitecture.commons_android.extensions.initWith
import fr.sample.hexagonalarchitecture.commons_lang.onError
import fr.sample.hexagonalarchitecture.commons_lang.onSuccess
import fr.sample.hexagonalarchitecture.feature_home.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentHomeBinding
            .inflate(inflater, container, false)
            .apply(::initView)
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchCharacters()
    }

    private fun initView(binding: FragmentHomeBinding) = binding.apply {
        screenContainer.initWith {
            HomeFragmentScreen(
                viewModel = viewModel,
                onCharacterClicked = ::goToCharacterDetail
            )
        }
    }

    private fun goToCharacterDetail(characterId: String) {
        HomeFragmentDirections
            .actionHomeFragmentToNavGraphCharacterDetail(characterId)
            .let { findNavController().navigate(it) }
    }
}

@Composable
fun HomeFragmentScreen(viewModel: HomeViewModel, onCharacterClicked: (String) -> Unit) {
    viewModel.characters.value.onSuccess {
        HomeScreen(characters = it, onCharacterClicked = onCharacterClicked)
    }.onError {
        Toast.makeText(LocalContext.current, it.message, Toast.LENGTH_LONG).show()
    }
}