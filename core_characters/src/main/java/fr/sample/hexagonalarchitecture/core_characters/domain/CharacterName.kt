package fr.sample.hexagonalarchitecture.core_characters.domain

@JvmInline
value class CharacterName(private val value: String) {
    init {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Name can't be empty")
        }
    }

    fun capitalize(): String {
        return this.value
            .replaceFirstChar {
                if (it.isLowerCase()) it.uppercase() else it.toString()
            }
    }
}