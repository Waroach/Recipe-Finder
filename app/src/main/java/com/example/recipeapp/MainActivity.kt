package com.example.recipeapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
// need to import this manually maybe?
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("onCreate", "Did i run?")
        super.onCreate(savedInstanceState)
        setContent {
            RecipeAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SearchBarUI()
                }
            }
        }
    }
}

@Composable
fun RecipeAppTheme(content: @Composable () -> Unit) {
    MaterialTheme { // Or use Material3 theme if you prefer
        content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarUI() {
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Search for a recipe") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}