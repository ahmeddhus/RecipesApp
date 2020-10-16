package com.example.recipesapp.network

import com.example.recipesapp.model.RecipeModel
import io.reactivex.Observable

import retrofit2.http.GET

interface RecipesAPI {
    @GET("recipes.json")
    fun getRecipes(): Observable<List<RecipeModel>>
}