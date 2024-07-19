package com.example.recipeapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipeapp.ui.SampleData


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarUI() {
    var searchText by remember { mutableStateOf("") }
    var displayedRecipe by remember { mutableStateOf<Recipe?>(null) }

    displayedRecipe = SampleData.hardcodedRecipe

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top, // Change to Top for better layout
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Search for a recipe") },modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        // Display recipe details if available
        displayedRecipe?.let { recipe ->
            Column(modifier = Modifier.padding(16.dp)) { // Add padding around details
                Text(text = "Recipe: ${recipe.name}", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp)) // Add some space between name and instructions
                Text(text = "Instructions: ${recipe.instructions}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}