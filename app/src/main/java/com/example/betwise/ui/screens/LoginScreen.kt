package com.example.betwise.ui.screens

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betwise.viewmodel.AuthViewModel
import com.example.betwise.auth.SessionManager

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    session: SessionManager,
    onLoginSuccess: () -> Unit,
    onNavigateRegister: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {

        Text("Login", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(value = username, onValueChange = { username = it }, label = { Text("Username") })
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") })

        Button(onClick = {
            viewModel.login(username, password) {
                if (it) {
                    session.saveLogin(username)
                    onLoginSuccess()
                } else {
                    error = "Invalid credentials"
                }
            }
        }) {
            Text("Login")
        }

        Text(error)

        TextButton(onClick = onNavigateRegister) {
            Text("Go to Register")
        }
    }
}