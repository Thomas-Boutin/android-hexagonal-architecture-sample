package fr.sample.hexagonalarchitecture.core_characters.adapter.output.http

import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterDetail
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RESTCharacter(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("status")
    val status: String,
) {
    fun toCharacterDetail(): CharacterDetail {
        return CharacterDetail(
            id = CharacterId(id.toString()),
            name = name,
            status = status,
        )
    }
}
