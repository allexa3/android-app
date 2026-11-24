package com.example.androidproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.androidproject.authScreen.AuthScreen
import com.example.androidproject.authScreen.AuthViewModel

class MainActivity : ComponentActivity() {

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AuthScreen(viewModel = authViewModel)
        }
    }
}