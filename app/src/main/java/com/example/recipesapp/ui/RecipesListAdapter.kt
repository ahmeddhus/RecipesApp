package com.example.recipesapp.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesapp.R
import com.example.recipesapp.databinding.RecipeItemBinding
import com.example.recipesapp.model.RecipeModel
import com.example.recipesapp.utils.RECIPE_DETAILS
import com.example.recipesapp.viewmodel.RecipeViewModel

class RecipesListAdapter : RecyclerView.Adapter<RecipesListAdapter.ViewHolder>() {
    private lateinit var recipesList: List<RecipeModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RecipeItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recipe_item, parent, false
        )
        return ViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recipesList[position])

    }

    override fun getItemCount(): Int {
        return if (::recipesList.isInitialized) recipesList.size else 0
    }

    fun updateRecipesList(RecipesList: List<RecipeModel>) {
        this.recipesList = RecipesList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: RecipeItemBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        private val viewModel = RecipeViewModel()

        fun bind(recipeModel: RecipeModel) {
            viewModel.bind(recipeModel)
            binding.viewModel = viewModel

            binding.root.findViewById<CardView>(R.id.item_view).setOnClickListener {
                val intent = Intent(context, RecipeDetailsActivity::class.java)
                intent.putExtra(RECIPE_DETAILS, recipeModel)
                context.startActivity(intent)
            }
        }
    }
}