package com.example.recipesapp.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class RecipeModel(
    @PrimaryKey val id: String,
    val calories: String,
    val carbos: String,
    val description: String,
    val difficulty: Int,
    val fats: String,
    val headline: String,
    val image: String,
    val name: String,
    val proteins: String,
    val thumb: String,
    val time: String
) : Serializable {

}



