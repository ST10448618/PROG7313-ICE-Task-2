package com.example.betwise.ui.screens

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betwise.viewmodel.CategoryViewModel
import com.example.betwise.data.entity.Category

@Composable
fun CategoryScreen(
    viewModel: CategoryViewModel,
    onClick: (Category) -> Unit
) {
    val categories by viewModel.categories.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.insertDefaults()
        viewModel.loadCategories()
    }

    Column(Modifier.padding(16.dp)) {

        Text("Categories", style = MaterialTheme.typography.headlineMedium)

        categories.forEach {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onClick(it) }
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(it.name)
                    Text(it.description ?: "")
                }
            }
        }
    }
}