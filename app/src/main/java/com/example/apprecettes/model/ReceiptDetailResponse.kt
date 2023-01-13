package com.example.apprecettes.model

import com.example.apprecettes.model.Categorie
import com.google.gson.annotations.SerializedName

class ReceiptDetailResponse{
    @SerializedName("meals")
    var meals: List<Meal>? = null
}

class Meal{
    var idMeal :  Int? = null
    var strMeal : String? = null
    var strInstructions : String? = null

}

class Ingredient{
    var ingredient :  String? = null
    var measure : String? = null
}
