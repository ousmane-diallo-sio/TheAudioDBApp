package com.example.theaudiodbapp.utils

fun verifyPassword(password: String): List<String> {
    val errors = mutableListOf<String>()

    if (password.length < 8) {
        errors.add(SampleErrors.PASSWORD_TOO_SHORT.toString())
    }

    if (!password.contains(Regex("[0-9]"))) {
        errors.add(SampleErrors.PASSWORD_MISSING_DIGIT.toString())
    }

    if (!password.contains(Regex("[A-Z]"))) {
        errors.add(SampleErrors.PASSWORD_MISSING_UPPERCASE.toString())
    }

    if (!password.contains(Regex("[a-z]"))) {
        errors.add(SampleErrors.PASSWORD_MISSING_LOWERCASE.toString())
    }

    if (!password.contains(Regex("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]"))) {
        errors.add(SampleErrors.PASSWORD_MISSING_SPECIAL_CHARACTER.toString())
    }

    return errors
}

enum class SampleErrors {
    PASSWORD_TOO_SHORT {
        override fun toString(): String {
            return "Password must be at least 8 characters long"
        }
    },
    PASSWORD_MISSING_DIGIT {
        override fun toString(): String {
            return "Password must contain at least one digit"
        }
    },
    PASSWORD_MISSING_UPPERCASE {
        override fun toString(): String {
            return "Password must contain at least one uppercase letter"
        }
    },
    PASSWORD_MISSING_LOWERCASE {
        override fun toString(): String {
            return "Password must contain at least one lowercase letter"
        }
    },
    PASSWORD_MISSING_SPECIAL_CHARACTER {
        override fun toString(): String {
            return "Password must contain at least one special character"
        }
    }
}