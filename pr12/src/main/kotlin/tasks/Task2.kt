package tasks

class PasswordValidator {
    companion object {
        private const val MIN_LENGTH = 8
        private val SPECIAL_CHARS = "!@#$%^&*".toSet()
    }

    fun validate(password: String): ValidationResult {
        val violations = mutableListOf<String>()
        if (password.length < MIN_LENGTH) violations.add("At least $MIN_LENGTH characters")
        if (!password.hasUppercase()) violations.add("At least one uppercase letter")
        if (!password.hasDigit()) violations.add("At least one digit")
        if (!password.hasSpecial()) violations.add("At least one special character (!@#$%^&*)")
        return ValidationResult(violations.isEmpty(), violations)
    }

    fun strength(password: String): PasswordStrength {
        val rules = listOf(
            password.length >= MIN_LENGTH,
            password.hasUppercase(),
            password.hasDigit(),
            password.hasSpecial()
        )
        return when (rules.count { it }) {
            0, 1 -> PasswordStrength.WEAK
            2, 3 -> PasswordStrength.MEDIUM
            else -> PasswordStrength.STRONG
        }
    }

    private fun String.hasUppercase() = any { it.isUpperCase() }
    private fun String.hasDigit() = any { it.isDigit() }
    private fun String.hasSpecial() = any { it in SPECIAL_CHARS }
}

data class ValidationResult(val isValid: Boolean, val violations: List<String>)

enum class PasswordStrength {
    WEAK, MEDIUM, STRONG
}