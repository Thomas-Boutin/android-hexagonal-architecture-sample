package fr.sample.hexagonalarchitecture.core_characters.domain

enum class CharacterStatus {
    Dead,
    Alive,
    Unknown;

    companion object {
        fun from(value: String): CharacterStatus {
            return values()
                .firstOrNull { it.name.equals(other = value, ignoreCase = true) }
                ?: throw IllegalArgumentException("Unknown value")
        }
    }
}