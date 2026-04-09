package tasks

class PasswordValidator {
    fun validate(password: String): ValidationResult {
        TODO("Not yet implemented")
    }

    fun strength(password: String): PasswordStrength {
        TODO("Not yet implemented")
    }
}

data class ValidationResult(val isValid: Boolean, val violations: List<String>)

enum class PasswordStrength {
    WEAK, MEDIUM, STRONG
}