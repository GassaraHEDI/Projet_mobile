package com.example.apprecettes.model

import com.google.gson.annotations.SerializedName

class CategoriesResponse {

    @SerializedName("categories")
    var meals: List<Meal>? = null
}

class Meal {

    var idMeal: Int? = null
    var strMeal: String? = null
    var strMealThumb: String? = null

}