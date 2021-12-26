package fr.sample.hexagonalarchitecture.feature_character_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import fr.sample.hexagonalarchitecture.commons_android.theme.MyApplicationTheme
import fr.sample.hexagonalarchitecture.commons_lang.onError
import fr.sample.hexagonalarchitecture.commons_lang.onSuccess
import fr.sample.hexagonalarchitecture.feature_character_detail.databinding.FragmentCharacterDetailBinding

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {
    private val viewModel: CharacterDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCharacterDetailBinding
            .inflate(inflater, container, false)
            .apply { screenContainer.init() }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchCharacters()
    }

    private fun ComposeView.init() {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            MyApplicationTheme {
                Surface {
                    CharacterDetailFragmentScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun CharacterDetailFragmentScreen(viewModel: CharacterDetailViewModel) {
    viewModel.character.value.onSuccess {
        //CharacterDetailScreen(characters = it)
    }.onError {
        Toast.makeText(LocalContext.current, it.message, Toast.LENGTH_LONG).show()
    }
}