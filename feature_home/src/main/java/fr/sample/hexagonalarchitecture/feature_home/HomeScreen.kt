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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.sample.hexagonalarchitecture.commons_android.theme.MyApplicationTheme
import fr.sample.hexagonalarchitecture.core_characters.domain.Character
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterId
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterName

@Composable
fun HomeScreen(characters: List<Character>, onCharacterClicked: (String) -> Unit) {
    LazyColumn {
        items(items = characters, key = { it.id.toString() }) { character ->
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
            .clickable { onCharacterClicked(character.id.toString()) },
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(
                text = character.name.capitalize(),
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
                    Character(id = CharacterId("id1"), name = CharacterName("bob")),
                    Character(id = CharacterId("id2"), name = CharacterName("pamela")),
                    Character(id = CharacterId("id3"), name = CharacterName("gaga"))
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
                character = Character(id = CharacterId("id1"), name = CharacterName("gaga"))
            ) { }
        }
    }
}