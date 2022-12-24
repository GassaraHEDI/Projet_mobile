package com.example.apprecettes.model
import com.google.gson.annotations.SerializedName

class ReceiptResponse {

    @SerializedName("meals")
    var receipts: List<Receipt>? = null
}

class Receipt {

    var strMeal: String? = null
    var strMealThumb: String? = null
    var idMeal: String? = null

}