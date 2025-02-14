package com.example.recipeapp.ui

import android.util.Log
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Composable function that displays the search bar and recipe details.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarUI() {
    var displayedRecipe by remember { mutableStateOf<Recipe?>(null) } // Initially no recipe displayed
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchButton(coroutineScope) { query ->
            val searchResult = fetchRecipesBySearch(query)
            displayedRecipe = searchResult
        }

        RandomRecipeButton(coroutineScope) {
            val randomRecipe = fetchRandomRecipe()
            displayedRecipe = randomRecipe
        }

        DisplayedRecipe(recipe = displayedRecipe, key = displayedRecipe?.id)
    }
}

/**
 * Composable function to display the recipe details.
 */
@Composable
fun DisplayedRecipe(recipe: Recipe?, key: String? = null) {
    // Apply the key if it's not null, otherwise don't apply it
    val context = LocalContext.current
    val jumpToIngredients = remember { mutableStateOf(false) } // State for jump target
    val lazyListState = rememberLazyListState() // Remember the scroll statekey(key)

    key(key) {
        recipe?.let {
            LazyColumn(modifier = Modifier.padding(16.dp), state = lazyListState) {
                // Recipe Name
                item {
                    Button(onClick = { jumpToIngredients.value = true }) {
                        Text("Jump to Ingredients")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Recipe: ${it.name}", style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // Display the image
                item {
                    AsyncImage(
                        model = it.mealThumb,
                        contentDescription = "Recipe Image",
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Youtube link
                item {
                    Text(
                        text = "Watch on YouTube",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Blue,
                        modifier = Modifier.clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.youtube))
                            startActivity(context, intent, null)
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // Instructions Item
                item {
                    Text(text = "Instructions: ${it.instructions}", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // Ingredients Section (with key for jump target)
                item(key = "ingredients") { // Key for jump target
                    val customGreen = Color(0xFF008000)
                    Text(
                        text = "Ingredients",
                        style = MaterialTheme.typography.headlineSmall,
                        color = customGreen
                    )
                    if (it.ingredient1?.isNotBlank() == true) {
                        Text(
                            text = "- ${it.measure1} of ${it.ingredient1}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    if (it.ingredient2?.isNotBlank() == true) {
                        Text(
                            text = "- ${it.measure2} of ${it.ingredient2}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    if (it.ingredient3?.isNotBlank() == true) {
                        Text(
                            text = "- ${it.measure3} of ${it.ingredient3}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    if (it.ingredient4?.isNotBlank() == true) {
                        Text(
                            text = "- ${it.measure4} of ${it.ingredient4}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    if (it.ingredient5?.isNotBlank() == true) {
                        Text(
                            text = "- ${it.measure5} of ${it.ingredient5}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    if (it.ingredient6?.isNotBlank() == true) {
                        Text(
                            text = "- ${it.measure6} of ${it.ingredient6}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    if (it.ingredient7?.isNotBlank() == true) {
                        Text(
                            text = "- ${it.measure7} of ${it.ingredient7}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    if (it.ingredient8?.isNotBlank() == true) {
                        Text(
                            text = "- ${it.measure8} of ${it.ingredient8}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    if (it.ingredient9?.isNotBlank() == true) {
                        Text(
                            text = "- ${it.measure9} of ${it.ingredient9}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    if (it.ingredient10?.isNotBlank() == true) {
                        Text(
                            text = "- ${it.measure10} of ${it.ingredient10}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    if (it.ingredient11?.isNotBlank() == true) {
                        Text(
                            text = "- ${it.measure11} of ${it.ingredient11}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    if (it.ingredient12?.isNotBlank() == true) {
                        Text(
                            text = "- ${it.measure12} of ${it.ingredient12}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    if (it.ingredient13?.isNotBlank() == true) {
                        Text(
                            text = "- ${it.measure13} of ${it.ingredient13}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    if (it.ingredient14?.isNotBlank() == true) {
                        Text(
                            text = "- ${it.measure14} of ${it.ingredient14}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    if (it.ingredient15?.isNotBlank() == true) {
                        Text(
                            text = "- ${it.measure15} of ${it.ingredient15}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    if (it.ingredient16?.isNotBlank() == true) {
                        Text(
                            text = "- ${it.measure16} of ${it.ingredient16}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    if (it.ingredient17?.isNotBlank() == true) {
                        Text(
                            text = "- ${it.measure17} of ${it.ingredient17}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    if (it.ingredient18?.isNotBlank() == true) {
                        Text(
                            text = "- ${it.measure18} of ${it.ingredient18}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    if (it.ingredient19?.isNotBlank() == true) {
                        Text(
                            text = "- ${it.measure19} of ${it.ingredient19}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    if (it.ingredient20?.isNotBlank() == true) {
                        Text(
                            text = "- ${it.measure20} of ${it.ingredient20}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            // LaunchedEffect to observe jumpToIngredients
            LaunchedEffect(jumpToIngredients.value) {
                if (jumpToIngredients.value) {
                    lazyListState.animateScrollToItem(index = 4) // Adjust index if needed
                    jumpToIngredients.value = false
                }}
        }
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
        Text("Search Random Recipe")
    }
}

/**
 * Placeholder function for fetching a random recipe.
 */
suspend fun fetchRandomRecipe(): Recipe? {
    return withContext(Dispatchers.IO) {
        Log.d("fetchRandomRecipe", "Did I fetch Random?")
        val mealApiService = createMealApiService()

        try {
            val response = mealApiService.getRandomRecipe().execute()
            Log.d("RecipeApp", "API Response: ${response.code()}") // Log response code
            if (response.isSuccessful) {
                val recipeResponse = response.body()
                val meal = recipeResponse?.meals?.firstOrNull()
                if (meal != null) {
                    Log.d("RecipeApp", "Fetched Recipe Name: $meal.strMeal") // Log fetched recipe
                    createRecipeFromMeal(meal)
                } else {
                    null
                }
            } else {
                Log.e("RecipeApp", "API Error: ${response} or ${response.code()}") // Log error response code
                null
            }
        } catch (e: Exception) {
            Log.e("RecipeApp", "API Exception: ${e}") // Log error message
            null
        }
    }
}

/**
 * Composable function for the "Search Recipes" button.
 */
@Composable
fun SearchButton(coroutineScope: CoroutineScope, onSearchClick: suspend (String) -> Unit) {
    var searchQuery by remember { mutableStateOf("") }

    Row(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search first letter of a recipe") },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {
                coroutineScope.launch {
                    val firstLetter = searchQuery.firstOrNull()?.toString() ?: "" // Extract first letter or empty string
                    onSearchClick(firstLetter) // Pass only the first letter to the API
                }
            }
        ) {
            Text("Search")
        }
    }
}

/**
 * Placeholder function for fetching a searched recipe.
 */
suspend fun fetchRecipesBySearch(query: String): Recipe? {
    return withContext(Dispatchers.IO) {
        Log.d("fetchRandomRecipe", "Did I fetch Search?")
        val mealApiService = createMealApiService()

        try {
            val response = mealApiService.searchRecipes(query).execute()
            Log.d("RecipeApp", "Search API Response: ${response.code()}")
            if (response.isSuccessful && response.body() != null) {
                val recipeResponse = response.body()!!
                val meal = recipeResponse.meals.randomOrNull() // Select a random meal from the list
                if (meal != null) {
                    Log.d("RecipeApp", "Fetched Recipe Name: ${meal.strMeal}")
                    createRecipeFromMeal(meal)
                } else {
                    null // No matching recipes found
                }
            } else {
                Log.e("RecipeApp", "Search API Error: Response bodyis null or unsuccessful")
                null
            }
        } catch (e: Exception) {
            Log.e("RecipeApp", "Search API Exception: ", e)
            null
        }
    }
}

/**
 * Function that sets up and returns the MealApiService instance
 */
private fun createMealApiService(): MealApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(MealApiService::class.java)
}

/**
 * Function representing a recipe / meal
 */
private fun createRecipeFromMeal(meal: Meal?):Recipe? {
    return meal?.let {
        Recipe(
            id = it.idMeal,
            name = it.strMeal,
            instructions = it.strInstructions,
            mealThumb = it.strMealThumb,
            youtube = it.strYoutube,
            ingredient1 = it.strIngredient1,
            ingredient2 = it.strIngredient2,
            ingredient3 = it.strIngredient3,
            ingredient4 = it.strIngredient4,
            ingredient5 = it.strIngredient5,
            ingredient6 = it.strIngredient6,
            ingredient7 = it.strIngredient7,
            ingredient8 = it.strIngredient8,
            ingredient9 = it.strIngredient9,
            ingredient10 = it.strIngredient10,
            ingredient11 = it.strIngredient11,
            ingredient12 = it.strIngredient12,
            ingredient13 = it.strIngredient13,
            ingredient14 = it.strIngredient14,
            ingredient15 = it.strIngredient15,
            ingredient16 = it.strIngredient16,
            ingredient17 = it.strIngredient17,
            ingredient18 = it.strIngredient18,
            ingredient19 = it.strIngredient19,
            ingredient20 = it.strIngredient20,
            measure1 = it.strMeasure1,
            measure2 = it.strMeasure2,
            measure3 = it.strMeasure3,
            measure4 = it.strMeasure4,
            measure5 = it.strMeasure5,
            measure6 = it.strMeasure6,
            measure7 = it.strMeasure7,
            measure8 = it.strMeasure8,
            measure9 = it.strMeasure9,
            measure10 = it.strMeasure10,
            measure11 = it.strMeasure11,
            measure12 = it.strMeasure12,
            measure13 = it.strMeasure13,
            measure14 = it.strMeasure14,
            measure15 = it.strMeasure15,
            measure16 = it.strMeasure16,
            measure17 = it.strMeasure17,
            measure18 = it.strMeasure18,
            measure19 = it.strMeasure19,
            measure20 = it.strMeasure20,
            source = it.strSource,
        )
    }
}
