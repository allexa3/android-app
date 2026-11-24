package com.example.androidproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.androidproject.authScreen.AuthScreen
import com.example.androidproject.authScreen.AuthViewModel
import com.example.androidproject.tripsScreen.TripsScreen
import com.example.androidproject.tripsScreen.TripsViewModel
import com.example.androidproject.ui.theme.AndroidProjectTheme

class MainActivity : ComponentActivity() {

    private val authViewModel: AuthViewModel by viewModels()
    private val tripsViewModel: TripsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidProjectTheme(darkTheme = false) {
                if (authViewModel.isAuthenticated) {
                    TripsScreen(
                        viewModel = tripsViewModel,
                        onSignOut = { authViewModel.signOut() }
                    )
                } else {
                    AuthScreen(viewModel = authViewModel)
                }
            }
        }
    }
}