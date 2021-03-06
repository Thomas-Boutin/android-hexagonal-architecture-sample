package fr.sample.hexagonalarchitecture.feature_character_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.sample.hexagonalarchitecture.commons_android.theme.MyApplicationTheme
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterDetail
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterId
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterName
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterStatus

@Composable
fun CharacterDetailScreen(characterDetail: CharacterDetail) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = characterDetail.name.capitalize(),
                style = MaterialTheme.typography.body1,
            )
            Text(
                text = stringResource(id = R.string.status, characterDetail.status),
                style = MaterialTheme.typography.body1,
            )
        }
    }
}

@Preview
@Composable
private fun CharacterDetailScreenPreview() {
    MyApplicationTheme {
        Surface {
            CharacterDetailScreen(
                CharacterDetail(id = CharacterId("id1"), name = CharacterName("bob"), status = CharacterStatus.Dead),
            )
        }
    }
}