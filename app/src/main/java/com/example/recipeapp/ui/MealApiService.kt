package com.example.recipeapp.ui

import retrofit2.Call
import retrofit2.http.GET

interface MealApiService {
    @GET("random.php")
    fun getRandomRecipe(): Call<RecipeResponse> // Replace RecipeResponse with the actual response data class
}