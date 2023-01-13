package com.example.apprecettes.model

import com.google.gson.annotations.SerializedName

class CategoriesResponse {

    @SerializedName("categories")
    var categories: List<Categorie>? = null
}

class Categorie {

    var idCategory: Int? = null
    var strCategory: String? = null
    var strCategoryThumb: String? = null
    var strCategoryDescription: String? = null

}