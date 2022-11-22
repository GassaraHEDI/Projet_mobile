package com.example.apprecettes

import com.google.gson.annotations.SerializedName

class CategoriesResponse {

    var count: Int? = null
    var previous: String? = null
    var next: String? = null

    @SerializedName("results")
    var categories: List<Categorie>? = null
}

class Categorie {

    var idCategory: Int? = null
    var strCategory: String? = null
    var strCategoryThumb: String? = null
    var strCategoryDescription: String? = null

}