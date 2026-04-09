package tasks

class PasswordValidator {
    fun validate(password: String): ValidationResult {
        val violations = mutableListOf<String>()
        if (password.length < 8) violations.add("At least 8 characters")
        if (!password.any { it.isUpperCase() }) violations.add("At least one uppercase letter")
        if (!password.any { it.isDigit() }) violations.add("At least one digit")
        if (!password.any { "!@#$%^&*".contains(it) }) violations.add("At least one special character (!@#$%^&*)")
        return ValidationResult(violations.isEmpty(), violations)
    }

    fun strength(password: String): PasswordStrength {
        val rulesPassed = listOf(
            password.length >= 8,
            password.any { it.isUpperCase() },
            password.any { it.isDigit() },
            password.any { "!@#$%^&*".contains(it) }
        ).count { it }
        return when (rulesPassed) {
            0, 1 -> PasswordStrength.WEAK
            2, 3 -> PasswordStrength.MEDIUM
            else -> PasswordStrength.STRONG
        }
    }
}

data class ValidationResult(val isValid: Boolean, val violations: List<String>)

enum class PasswordStrength {
    WEAK, MEDIUM, STRONG
}