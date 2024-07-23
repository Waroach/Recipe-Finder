package com.example.recipeapp.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipeapp.ui.SampleData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Composable function that displays the search bar and recipe details.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarUI() {
    var searchText by remember { mutableStateOf("") } // State for the search text
    var displayedRecipe by remember { mutableStateOf<Recipe?>(null) } // Initially no recipe displayed
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(searchText, onSearchTextChanged = { newText ->
            searchText = newText
            displayedRecipe = filterRecipe(newText, displayedRecipe)
        })

        DisplayedRecipe(recipe = displayedRecipe, key = displayedRecipe?.id)

        RandomRecipeButton(coroutineScope) {
            // TODO: Replace with actual API call to fetch a random recipe
            val randomRecipe = fetchRandomRecipe()
            displayedRecipe = randomRecipe
        }
    }
}

/**
 * Composable function for the search bar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(searchText: String, onSearchTextChanged: (String) -> Unit) {
    TextField(
        value = searchText,
        onValueChange = onSearchTextChanged,
        label = { Text("Search for a recipe") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

/**
 * Composable function to display the recipe details.
 */@Composable
fun DisplayedRecipe(recipe: Recipe?, key: String? = null) {
    key?.let { // Only apply the key if it's not null
        key(it) { // Use the key function to apply the key
            recipe?.let {
                Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Recipe: ${it.name}", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Instructions: ${it.instructions}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
    } ?: run { // If key is null, don't apply it
        recipe?.let {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Recipe: ${it.name}", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Instructions: ${it.instructions}", style = MaterialTheme.typography.bodyMedium)
            }}
    }
}

/**
 * Composable function for the "Random Recipe" button.
 */
@Composable
fun RandomRecipeButton(coroutineScope: CoroutineScope, onRandomRecipeClick: suspend () -> Unit) {
    Button(
        onClick = {
            coroutineScope.launch {
                onRandomRecipeClick()
            }
        },
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Random Recipe")
    }
}

/**
 * Placeholder function for fetching a random recipe (to be implemented later).
 */
suspend fun fetchRandomRecipe(): Recipe? {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/") // Base URL of the API
        .addConverterFactory(GsonConverterFactory.create()) // Use Gson for JSON parsing
        .build()

    val mealApiService = retrofit.create(MealApiService::class.java)

    return try {
        val response = mealApiService.getRandomRecipe().execute()
        Log.d("RecipeApp", "API Response: ${response.code()}") // Log response code
        if (response.isSuccessful) {
            val recipeResponse = response.body()
            val meal = recipeResponse?.meals?.firstOrNull()
            if (meal != null) {
                Log.d("RecipeApp", "Fetched Recipe Name: $meal.strMeal") // Log fetched recipe
                Recipe(id = meal.idMeal,
                    name = meal.strMeal,
                    instructions = meal.strInstructions,
                    mealThumb = meal.strMealThumb,
                    youtube = meal.strYoutube,
                    ingredient1 = meal.strIngredient1,
                    ingredient2 = meal.strIngredient2,
                    ingredient3 = meal.strIngredient3,
                    ingredient4 = meal.strIngredient4,
                    ingredient5 = meal.strIngredient5,
                    ingredient6 = meal.strIngredient6,
                    ingredient7 = meal.strIngredient7,
                    ingredient8 = meal.strIngredient8,
                    ingredient9 = meal.strIngredient9,
                    ingredient10 = meal.strIngredient10,
                    ingredient11 = meal.strIngredient11,
                    ingredient12 = meal.strIngredient12,
                    ingredient13 = meal.strIngredient13,
                    ingredient14 = meal.strIngredient14,
                    ingredient15 = meal.strIngredient15,
                    ingredient16 = meal.strIngredient16,
                    ingredient17 = meal.strIngredient17,
                    ingredient18 = meal.strIngredient18,
                    ingredient19 = meal.strIngredient19,
                    ingredient20 = meal.strIngredient20,
                    measure1 = meal.strMeasure1,
                    measure2 = meal.strMeasure2,
                    measure3 = meal.strMeasure3,
                    measure4 = meal.strMeasure4,
                    measure5 = meal.strMeasure5,
                    measure6 = meal.strMeasure6,
                    measure7 = meal.strMeasure7,
                    measure8 = meal.strMeasure8,
                    measure9 = meal.strMeasure9,
                    measure10 = meal.strMeasure10,
                    measure11 = meal.strMeasure11,
                    measure12 = meal.strMeasure12,
                    measure13 = meal.strMeasure13,
                    measure14 = meal.strMeasure14,
                    measure15 = meal.strMeasure15,
                    measure16 = meal.strMeasure16,
                    measure17 = meal.strMeasure17,
                    measure18 = meal.strMeasure18,
                    measure19 = meal.strMeasure19,
                    measure20 = meal.strMeasure20,
                    source = meal.strSource,
                    // Map other fields as needed
                )
            } else {
                null
            }
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
}

/**
 * Filters the recipe based on the search text.
 */
private fun filterRecipe(searchText: String, displayedRecipe: Recipe?): Recipe? {
    return if (searchText.isBlank()) {
        null
    } else {
        displayedRecipe?.let { fetchedRecipe ->
            if (fetchedRecipe.name.contains(searchText, ignoreCase = true)) {
                fetchedRecipe
            } else {
                null
            }
        }
    }
}