package com.example.recipesapp.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recipesapp.model.RecipesDao
import com.example.recipesapp.model.RecipeModel

@Database(entities = arrayOf(RecipeModel::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): RecipesDao
}