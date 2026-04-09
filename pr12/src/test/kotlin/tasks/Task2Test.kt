package tasks

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Task2Test {
    private val validator = PasswordValidator()

    @Test
    fun `empty string is invalid with all rules violated`() {
        val result = validator.validate("")
        assertThat(result.isValid).isFalse()
        assertThat(result.violations).containsExactlyInAnyOrder(
            "At least 8 characters",
            "At least one uppercase letter",
            "At least one digit",
            "At least one special character (!@#$%^&*)"
        )
    }

    @Test
    fun `password without digits uppercase and specials is invalid`() {
        val result = validator.validate("password")
        assertThat(result.isValid).isFalse()
        assertThat(result.violations).containsExactlyInAnyOrder(
            "At least one uppercase letter",
            "At least one digit",
            "At least one special character (!@#$%^&*)"
        )
    }

    @Test
    fun `Password1 misses special character`() {
        val result = validator.validate("Password1")
        assertThat(result.isValid).isFalse()
        assertThat(result.violations).containsExactly("At least one special character (!@#$%^&*)")
    }

    @Test
    fun `P@ssw0rd is valid`() {
        val result = validator.validate("P@ssw0rd")
        assertThat(result.isValid).isTrue()
        assertThat(result.violations).isEmpty()
    }

    @Test
    fun `strength of abc is WEAK`() {
        assertThat(validator.strength("abc")).isEqualTo(PasswordStrength.WEAK)
    }

    @Test
    fun `strength of Password1 is MEDIUM`() {
        assertThat(validator.strength("Password1")).isEqualTo(PasswordStrength.MEDIUM)
    }

    @Test
    fun `strength of P@ssw0rd is STRONG`() {
        assertThat(validator.strength("P@ssw0rd")).isEqualTo(PasswordStrength.STRONG)
    }
}