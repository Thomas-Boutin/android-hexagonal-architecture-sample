package fr.sample.hexagonalarchitecture.feature_home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import fr.sample.hexagonalarchitecture.commons_android.theme.MyApplicationTheme
import fr.sample.hexagonalarchitecture.feature_home.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
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

    @Composable
    fun HomeScreen() {
        MyApplicationTheme {
            LazyColumn {
                items(characters) { character ->
                    Character(character)
                }
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

    @Preview
    @Composable
    fun DefaultPreview() {
        HomeScreen()
    }

    private fun ComposeView.init() {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            HomeScreen()
        }
    }

    companion object {
        private val characters = listOf(
            "bob",
            "pamela",
            "gaga"
        )
    }
}