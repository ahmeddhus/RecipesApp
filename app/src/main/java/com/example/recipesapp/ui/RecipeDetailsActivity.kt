package com.example.recipesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.recipesapp.R
import com.example.recipesapp.model.RecipeModel
import com.example.recipesapp.utils.RECIPE_DETAILS

class RecipeDetailsActivity : AppCompatActivity() {

    lateinit var recipeImg: ImageView
    lateinit var recipeTitle: TextView
    lateinit var recipeHeadline: TextView
    lateinit var recipeFats: TextView
    lateinit var recipeCalories: TextView
    lateinit var recipeCarbos: TextView
    lateinit var recipeDesc: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)
        init()
    }

    private fun init() {
        val recipeModel = intent.getSerializableExtra(RECIPE_DETAILS) as? RecipeModel
        recipeImg = findViewById(R.id.recipe_img)
        recipeTitle = findViewById(R.id.recipe_title)
        recipeHeadline = findViewById(R.id.recipe_headline)
        recipeFats = findViewById(R.id.recipe_fats)
        recipeCalories = findViewById(R.id.recipe_calories)
        recipeCarbos = findViewById(R.id.recipe_carbos)
        recipeDesc = findViewById(R.id.recipe_desc)

        setData(recipeModel)
    }

    private fun setData(recipeModel: RecipeModel?) {
        supportActionBar?.title = recipeModel?.name
        recipeHeadline.text = recipeModel?.headline
        recipeFats.text = recipeModel?.fats
        recipeCalories.text = recipeModel?.calories
        recipeCarbos.text = recipeModel?.carbos
        recipeDesc.text = recipeModel?.description
    }
}