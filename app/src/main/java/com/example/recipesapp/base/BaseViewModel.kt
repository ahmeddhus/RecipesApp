package com.example.recipesapp.base

import androidx.lifecycle.ViewModel
import com.example.recipesapp.injection.component.DaggerViewModelInjector
import com.example.recipesapp.injection.component.ViewModelInjector
import com.example.recipesapp.injection.module.NetworkModule
import com.example.recipesapp.viewmodel.RecipesListViewModel

abstract class BaseViewModel:ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is RecipesListViewModel -> injector.inject(this)
        }
    }
}