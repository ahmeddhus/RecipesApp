package com.example.recipesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipesapp.R
import com.example.recipesapp.databinding.ActivityMainBinding
import com.example.recipesapp.injection.ViewModelFactory
import com.example.recipesapp.viewmodel.RecipesListViewModel
import com.google.android.material.snackbar.Snackbar
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    var sortList = arrayOf(
        "Calories", "Fat"
    )

    lateinit var dialog: AlertDialog
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: RecipesListViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.postList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this))
            .get(RecipesListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }

    private fun openFilterDialog(){
        val myBuilder = AlertDialog.Builder(this)
        myBuilder.setTitle("Filter By:").setItems(sortList) { dialogInterface, position ->
            Toast.makeText(this@MainActivity, sortList[position], Toast.LENGTH_SHORT).show() }
        dialog = myBuilder.create()
        dialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

//        if(item.itemId == R.id.action_search)

        return super.onOptionsItemSelected(item)
    }

    fun filterFloatingBtnAction(view: View) {
        openFilterDialog()
    }
}