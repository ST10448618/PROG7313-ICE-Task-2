package com.example.betwise.ui.screens

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betwise.viewmodel.AuthViewModel
import com.example.betwise.data.entity.User

@Composable
fun RegisterScreen(
    viewModel: AuthViewModel,
    onRegisterSuccess: () -> Unit,
    onBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {

        Text("Register")

        OutlinedTextField(name, { name = it }, label = { Text("Full Name") })
        OutlinedTextField(email, { email = it }, label = { Text("Email") })
        OutlinedTextField(username, { username = it }, label = { Text("Username") })
        OutlinedTextField(password, { password = it }, label = { Text("Password") })
        OutlinedTextField(confirm, { confirm = it }, label = { Text("Confirm Password") })

        Button(onClick = {
            if (password != confirm) {
                error = "Passwords do not match"
                return@Button
            }

            viewModel.register(
                User(fullName = name, email = email, username = username, password = password)
            ) {
                if (it) onRegisterSuccess()
                else error = "User exists"
            }
        }) {
            Text("Register")
        }

        Text(error)

        TextButton(onClick = onBack) {
            Text("Back to Login")
        }
    }
}