package com.example.androidproject.authScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AuthScreen(
    viewModel: AuthViewModel
) {
    val context = LocalContext.current

    // Show Toast whenever loginMessage updates in the ViewModel
    LaunchedEffect(viewModel.loginMessage) {
        viewModel.loginMessage?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            viewModel.clearLoginMessage()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)
    ) {
        Text(
            text = "Username",
            fontSize = 30.sp
        )

        TextField(
            value = viewModel.username,
            onValueChange = { newUsername ->
                viewModel.updateUsername(newUsername)
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            supportingText = {
                if (viewModel.usernameError.isNotEmpty()) {
                    Text(text = viewModel.usernameError)
                }
            },
            isError = viewModel.usernameError.isNotEmpty()
        )

        Spacer(Modifier.height(30.dp))

        Text(
            text = "Password",
            fontSize = 30.sp
        )

        TextField(
            value = viewModel.password,
            onValueChange = { newPassword ->
                viewModel.updatePassword(newPassword)
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            supportingText = {
                if (viewModel.passwordError.isNotEmpty()) {
                    Text(text = viewModel.passwordError)
                }
            },
            isError = viewModel.passwordError.isNotEmpty()
        )

        Button(
            onClick = {
                viewModel.signIn()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black
            )
        ) {
            Text(
                text = "Sign In"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AuthScreenPreview() {
    val previewViewModel = remember { AuthViewModel() }
    AuthScreen(viewModel = previewViewModel)
}