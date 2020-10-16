package com.example.recipesapp.injection.component

import com.example.recipesapp.injection.module.NetworkModule
import com.example.recipesapp.model.RecipeModel
import com.example.recipesapp.viewmodel.RecipesListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(recipeListViewModel: RecipesListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}