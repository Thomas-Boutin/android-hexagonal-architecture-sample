package fr.sample.hexagonalarchitecture.core_characters.adapter.output.http

import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RESTCharacter(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("isAlive")
    val status: String,
) {
    fun toCharacterDetail(): CharacterDetail {
        return CharacterDetail(
            id = id,
            name = name,
            status = status,
        )
    }
}
