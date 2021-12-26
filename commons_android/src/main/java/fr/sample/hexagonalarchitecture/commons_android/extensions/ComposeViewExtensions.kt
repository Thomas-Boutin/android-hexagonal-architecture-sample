package fr.sample.hexagonalarchitecture.commons_android.extensions

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import fr.sample.hexagonalarchitecture.commons_android.theme.MyApplicationTheme

fun ComposeView.initWith(screen: @Composable () -> @Composable Unit) {
    setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
    setContent {
        MyApplicationTheme {
            Surface {
                screen()
            }
        }
    }
}