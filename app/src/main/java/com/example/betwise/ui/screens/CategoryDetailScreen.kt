package com.example.betwise.ui.screens

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betwise.viewmodel.SessionViewModel
import com.example.betwise.data.entity.Session

@Composable
fun CategoryDetailScreen(
    categoryId: Int,
    sessionViewModel: SessionViewModel
) {
    val sessions by sessionViewModel.sessions.collectAsState()

    var title by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        sessionViewModel.loadSessions(categoryId)
    }

    Column(Modifier.padding(16.dp)) {

        Text("Sessions", style = MaterialTheme.typography.headlineMedium)

        sessions.forEach {
            Text("${it.title} - R${it.amount}")
        }

        Spacer(Modifier.height(20.dp))

        OutlinedTextField(title, { title = it }, label = { Text("Session Title") })
        OutlinedTextField(amount, { amount = it }, label = { Text("Amount") })

        Button(onClick = {
            sessionViewModel.addSession(
                Session(
                    title = title,
                    date = "Today",
                    duration = "1h",
                    amount = amount.toDoubleOrNull() ?: 0.0,
                    categoryId = categoryId
                )
            )
        }) {
            Text("Add Session")
        }
    }
}