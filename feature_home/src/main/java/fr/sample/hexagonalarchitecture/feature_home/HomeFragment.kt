package fr.sample.hexagonalarchitecture.feature_home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import fr.sample.hexagonalarchitecture.commons_android.theme.MyApplicationTheme
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
            .apply { screenContainer.init() }
            .root
    }

    private fun ComposeView.init() {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            MyApplicationTheme {
                Surface {
                    HomeFragmentScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun HomeFragmentScreen(viewModel: HomeViewModel) {
    viewModel.characters.value.onSuccess {
        HomeScreen(characters = it)
    }.onFailure {
        Toast.makeText(LocalContext.current, it.message, Toast.LENGTH_SHORT).show()
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Surface {
            HomeScreen(listOf("gaga", "pamela", "yes"))
        }
    }
}

@Composable
fun HomeScreen(characters: List<String>) {
    LazyColumn {
        items(characters) { character ->
            Character(character)
        }
    }
}

@Composable
private fun Character(character: String) {
    Text(
        text = "Hello $character!",
        style = MaterialTheme.typography.body1,
    )
}
