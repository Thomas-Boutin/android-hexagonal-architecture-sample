package fr.sample.hexagonalarchitecture.core_characters.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class CharacterIdTest {

    @Test
    fun `should wrap value`() {
        assertThat(CharacterId("id").value).isEqualTo("id")
    }

    @Test
    fun `should not be empty`() {
        assertThatThrownBy {
            CharacterId("")
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Id can't be empty")
    }
}