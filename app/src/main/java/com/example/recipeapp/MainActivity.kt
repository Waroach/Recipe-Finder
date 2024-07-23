package com.example.recipeapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.recipeapp.ui.SearchBarUI


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("onCreate", "Did I run?")
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