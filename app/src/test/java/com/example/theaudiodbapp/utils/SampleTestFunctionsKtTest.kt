package com.example.theaudiodbapp.utils

import org.junit.Test

class SampleTestFunctionsKtTest {

    @Test
    fun `Test - Password is valid`() {
        // Setup
        val password = "Password1!"

        // Action
        val errors = verifyPassword(password)

        // Assert
        assert(errors.isEmpty())
    }

    @Test
    fun `Test - Password is invalid`() {
        val password = "invalidPassword"
        val errors = verifyPassword(password)
        assert(errors.isNotEmpty())
    }

    @Test
    fun `Test - Password contains at least 8 characters`() {
        val validPassword = "Password1!"
        val invalidPassword = "Pass1!"

        val errorsValidPassword = verifyPassword(validPassword)
        val errorsInvalidPassword = verifyPassword(invalidPassword)

        assert(errorsValidPassword.isEmpty())
        assert(errorsInvalidPassword.contains(SampleErrors.PASSWORD_TOO_SHORT.toString()))
    }

    @Test
    fun `Test - Password contains at least one digit`() {
        val validPassword = "Password1!"
        val invalidPassword = "Password!"

        val errorsValidPassword = verifyPassword(validPassword)
        val errorsInvalidPassword = verifyPassword(invalidPassword)

        assert(errorsValidPassword.isEmpty())
        assert(errorsInvalidPassword.contains(SampleErrors.PASSWORD_MISSING_DIGIT.toString()))
    }

    @Test
    fun `Test - Password contains at least one uppercase letter`() {
        val validPassword = "Password1!"
        val invalidPassword = "password1!"

        val errorsValidPassword = verifyPassword(validPassword)
        val errorsInvalidPassword = verifyPassword(invalidPassword)

        assert(errorsValidPassword.isEmpty())
        assert(errorsInvalidPassword.contains(SampleErrors.PASSWORD_MISSING_UPPERCASE.toString()))
    }

    @Test
    fun `Test - Password contains at least one lowercase letter`() {
        val validPassword = "Password1!"
        val invalidPassword = "PASSWORD1!"

        val errorsValidPassword = verifyPassword(validPassword)
        val errorsInvalidPassword = verifyPassword(invalidPassword)

        assert(errorsValidPassword.isEmpty())
        assert(errorsInvalidPassword.contains(SampleErrors.PASSWORD_MISSING_LOWERCASE.toString()))
    }

    @Test
    fun `Test - Password contains at least one special character`() {
        val validPassword = "Password1!"
        val invalidPassword = "Password1"

        val errorsValidPassword = verifyPassword(validPassword)
        val errorsInvalidPassword = verifyPassword(invalidPassword)

        assert(errorsValidPassword.isEmpty())
        assert(errorsInvalidPassword.contains(SampleErrors.PASSWORD_MISSING_SPECIAL_CHARACTER.toString()))
    }
}