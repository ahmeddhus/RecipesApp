package com.example.recipesapp.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.recipesapp.R
import com.example.recipesapp.base.BaseViewModel
import com.example.recipesapp.model.RecipesDao
import com.example.recipesapp.model.RecipeModel
import com.example.recipesapp.network.RecipesAPI
import com.example.recipesapp.ui.RecipesListAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RecipesListViewModel(private val recipeDao: RecipesDao): BaseViewModel(){
    @Inject
    lateinit var recipesAPI: RecipesAPI

    private lateinit var subscription: Disposable
    val recipesListAdapter: RecipesListAdapter = RecipesListAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadRecipes() }

    init {
        loadRecipes()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadRecipes() {
        subscription = Observable.fromCallable { recipeDao.all }
            .concatMap {
                    dbPostList ->
                if(dbPostList.isEmpty())
                    recipesAPI.getRecipes().concatMap {
                            apiPostList -> recipeDao.insertAll(*apiPostList.toTypedArray())
                        Observable.just(apiPostList)
                    }
                else
                    Observable.just(dbPostList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result -> onRetrievePostListSuccess(result) },
                { onRetrievePostListError() }
            )
    }

    private fun onRetrievePostListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(recipesList: List<RecipeModel>) {
        recipesListAdapter.updateRecipesList(recipesList)
    }

    private fun onRetrievePostListError() {
        errorMessage.value = R.string.loading_error
    }
}