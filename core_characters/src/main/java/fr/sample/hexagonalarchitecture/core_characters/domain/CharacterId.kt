package fr.sample.hexagonalarchitecture.core_characters.domain

@JvmInline
value class CharacterId(val value: String) {
    init {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Id can't be empty")
        }
    }
}