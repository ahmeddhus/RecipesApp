package com.example.recipesapp.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipesDao {
    @get: Query("SELECT * FROM RecipeModel")
    val all: List<RecipeModel>

    @Insert
    fun insertAll(vararg  post: RecipeModel)
}