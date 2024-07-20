package com.example.recipeapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipeapp.ui.SampleData


/**
 * Composable function that displays the search bar and recipe details.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarUI() {
    var searchText by remember { mutableStateOf("") } // State for the search text
    var displayedRecipe by remember { mutableStateOf<Recipe?>(null) } // State for the currently displayed recipe

    displayedRecipe = SampleData.hardcodedRecipe // Initially display the hardcoded recipe

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top, // Change to Top for better layout
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = searchText,
            onValueChange = { searchText = it }, // Update search text on input
            label = { Text("Search for a recipe") },modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        // Display recipe details if available
        displayedRecipe?.let { recipe ->
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Recipe: ${recipe.name}", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Instructions: ${recipe.instructions}", style = MaterialTheme.typography.bodyMedium)
            }
        }
        // Button to trigger random recipe display (currently shows the hardcoded recipe)
        Button(
            onClick = {
                displayedRecipe = SampleData.hardcodedRecipe
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Random Recipe")
        }
    }
}