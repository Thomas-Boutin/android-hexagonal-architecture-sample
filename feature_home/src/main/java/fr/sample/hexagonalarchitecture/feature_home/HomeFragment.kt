package fr.sample.hexagonalarchitecture.feature_home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import fr.sample.hexagonalarchitecture.feature_home.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentHomeBinding
            .inflate(inflater, container, false)
            .apply { body.init() }
            .root
    }

    private fun ComposeView.init() {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            Body()
        }
    }
}

@Composable
fun Body() {
    MaterialTheme {
        Text("Hello Compose!")
    }
}

@Preview
@Composable
fun DefaultPreview() {
    Body()
}