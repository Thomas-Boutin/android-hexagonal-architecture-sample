package fr.sample.hexagonalarchitecture.core_characters.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class CharacterNameTest {

    @Test
    fun `should pretty print`() {
        assertThat(CharacterName("name").capitalize()).isEqualTo("Name")
    }

    @Test
    fun `should not be empty`() {
        assertThatThrownBy {
            CharacterName("")
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Name can't be empty")
    }
}