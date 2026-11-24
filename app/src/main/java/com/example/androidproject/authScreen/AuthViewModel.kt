package com.example.androidproject.authScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    var username by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var usernameError by mutableStateOf("")
        private set

    var passwordError by mutableStateOf("")
        private set

    var loginMessage by mutableStateOf<String?>(null)
        private set

    fun updateUsername(value: String) {
        username = value
        validateUsername()
    }

    fun updatePassword(value: String) {
        password = value
        validatePassword()
    }

    private fun validateUsername(): Boolean {
        val trimmed = username.trim()
        usernameError = when {
            trimmed.isEmpty() -> "Username cannot be empty!"
            trimmed.length < 3 -> "Username is too short!"
            else -> ""
        }
        return usernameError.isEmpty()
    }

    private fun validatePassword(): Boolean {
        passwordError = when {
            password.isEmpty() -> "Password cannot be empty!"
            password.length < 6 -> "Password is too short!"
            else -> ""
        }
        return passwordError.isEmpty()
    }

    fun signIn() {
        val isUserValid = validateUsername()
        val isPassValid = validatePassword()

        if (isUserValid && isPassValid) {
            loginMessage = if (username.trim() == "admin" && password == "password") {
                "Login Successful!"
            } else {
                "Invalid username or password!"
            }
        }
    }

    fun clearLoginMessage() {
        loginMessage = null
    }
}