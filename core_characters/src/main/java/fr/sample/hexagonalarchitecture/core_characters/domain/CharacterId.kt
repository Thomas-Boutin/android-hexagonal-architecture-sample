package fr.sample.hexagonalarchitecture.core_characters.domain

@JvmInline
value class CharacterId(private val value: String) {
    init {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Id can't be empty")
        }
    }

    override fun toString(): String {
        return this.value
    }
}