package fr.sample.hexagonalarchitecture.core_characters.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class CharacterStatusTest {
    @Test
    fun `should create status from string`() {
        assertThat(CharacterStatus.from("Dead")).isEqualTo(CharacterStatus.Dead)
    }

    @Test
    fun `should create status from string ignoring case`() {
        assertThat(CharacterStatus.from("alive")).isEqualTo(CharacterStatus.Alive)
    }

    @Test
    fun `should throw when unknown value`() {
        assertThatThrownBy {
            CharacterStatus.from("arbitrary value")
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Unknown value")
    }
}