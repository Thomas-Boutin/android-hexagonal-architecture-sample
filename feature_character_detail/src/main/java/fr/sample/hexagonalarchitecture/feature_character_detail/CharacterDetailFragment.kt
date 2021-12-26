package fr.sample.hexagonalarchitecture.feature_character_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import fr.sample.hexagonalarchitecture.commons_android.extensions.initWith
import fr.sample.hexagonalarchitecture.commons_lang.onError
import fr.sample.hexagonalarchitecture.commons_lang.onSuccess
import fr.sample.hexagonalarchitecture.feature_character_detail.databinding.FragmentCharacterDetailBinding

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {
    private val characterDetailFragmentArgs: CharacterDetailFragmentArgs by navArgs()
    private val viewModel: CharacterDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCharacterDetailBinding
            .inflate(inflater, container, false)
            .apply(::initView)
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchCharacterDetailWith(characterDetailFragmentArgs.characterId)
    }

    private fun initView(binding: FragmentCharacterDetailBinding) = binding.apply {
        screenContainer.initWith { CharacterDetailFragmentScreen(viewModel) }
    }
}

@Composable
fun CharacterDetailFragmentScreen(viewModel: CharacterDetailViewModel) {
    viewModel.characterDetail.value.onSuccess {
        CharacterDetailScreen(characterDetail = it)
    }.onError {
        Toast.makeText(LocalContext.current, it.message, Toast.LENGTH_LONG).show()
    }
}