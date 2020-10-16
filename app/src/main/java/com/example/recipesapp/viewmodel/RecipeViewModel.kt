package com.example.recipesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.recipesapp.model.RecipeModel

class RecipeViewModel {
    private val RecipeImg = MutableLiveData<String>()
    private val RecipeTitle = MutableLiveData<String>()
    private val RecipeDesc = MutableLiveData<String>()

    fun bind(recipeModel: RecipeModel){
        RecipeImg.value = recipeModel.image
        RecipeTitle.value = recipeModel.name
        RecipeDesc.value = recipeModel.description
    }

    fun getRecipeTitle(): MutableLiveData<String> {
        return RecipeTitle
    }

    fun getRecipeDesc(): MutableLiveData<String> {
        return RecipeDesc
    }

    fun getRecipeImg(): MutableLiveData<String>{
        return RecipeImg;
    }
}