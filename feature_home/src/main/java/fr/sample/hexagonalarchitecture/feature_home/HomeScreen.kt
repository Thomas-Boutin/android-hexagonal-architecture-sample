package fr.sample.hexagonalarchitecture.feature_home

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.sample.hexagonalarchitecture.commons_android.theme.MyApplicationTheme

@Composable
fun HomeScreen(characters: List<fr.sample.hexagonalarchitecture.core_characters.domain.Character>) {
    LazyColumn {
        items(characters) { character ->
            Character(character)
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

@Composable
private fun Character(character: fr.sample.hexagonalarchitecture.core_characters.domain.Character) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(
                text = character.name.capitalize(LocaleList.current),
                style = MaterialTheme.typography.body1,
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    MyApplicationTheme {
        Surface {
            HomeScreen(
                listOf(
                    fr.sample.hexagonalarchitecture.core_characters.domain.Character("gaga"),
                    fr.sample.hexagonalarchitecture.core_characters.domain.Character("pamela"),
                    fr.sample.hexagonalarchitecture.core_characters.domain.Character("yes"),
                )
            )
        }
    }
}

@Preview
@Composable
private fun CharacterPreview() {
    MyApplicationTheme {
        Surface {
            Character(character = fr.sample.hexagonalarchitecture.core_characters.domain.Character("gaga"))
        }
    }
}