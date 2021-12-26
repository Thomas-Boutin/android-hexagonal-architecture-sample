package fr.sample.hexagonalarchitecture.feature_home

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.sample.hexagonalarchitecture.commons_android.theme.MyApplicationTheme
import fr.sample.hexagonalarchitecture.core_characters.domain.Character

@Composable
fun HomeScreen(characters: List<Character>, onCharacterClicked: (String) -> Unit) {
    LazyColumn {
        items(items = characters, key = { it.id }) { character ->
            CharacterItem(character, onCharacterClicked)
        }
    }
}

@Composable
private fun CharacterItem(character: Character, onCharacterClicked: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { onCharacterClicked(character.id) },
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
                    Character(id = "id1", name = "bob"),
                    Character(id = "id2", name = "pamela"),
                    Character(id = "id3", name = "gaga")
                )
            ) {}
        }
    }
}

@Preview
@Composable
private fun CharacterItemPreview() {
    MyApplicationTheme {
        Surface {
            CharacterItem(
                character = Character(id = "id1", name = "gaga")
            ) { }
        }
    }
}