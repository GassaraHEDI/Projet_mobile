package com.example.apprecettes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apprecettes.model.Ingredient
import com.google.gson.Gson
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.net.URL

class ReceiptDetailActivity : AppCompatActivity() {
    private lateinit var receiptDetailIngredientsAdapter: ReceiptDetailIngredientsAdapter
    private lateinit var receiptDetailInstructionsAdapter: ReceiptDetailInstructionsAdapter
    private lateinit var instructionsRecyclerView: RecyclerView
    private lateinit var ingredientsRecyclerView: RecyclerView
    private lateinit var titleText :  TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt_detail)
        ingredientsRecyclerView = findViewById(R.id.receipt_ingredient_measure_recycler_view)
        instructionsRecyclerView = findViewById(R.id.instructions_recycler_view)
        titleText = findViewById(R.id.receipt_detail_title)

        val url = URL("https://www.themealdb.com/api/json/v1/1/lookup.php?i=52772")

        val request = Request.Builder()
            .url(url)
            .build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.e("ReceiptHTTP", e.localizedMessage)
            }

            override fun onResponse(call: Call, response: Response) {

                response.body?.string()?.let {
                    Log.d("ReceiptHTTP",it)
                    val json = JSONObject(it)
                    val meal = json.getJSONArray("meals").getJSONObject(0)
                    val gson = Gson()
//                    val jsonObject = gson.fromJson(meal, JsonObject::class.java)
//                    val name = jsonObject.get("idMeal").asString

                    val instructions = meal.get("strInstructions").toString().split("\n")
                    Log.d("ReceiptHTTP", instructions.toString())
//                    titleText.setText(meal.get("strMeal") as String)
                    instructions?.let { it ->
                        runOnUiThread {
                            receiptDetailInstructionsAdapter = ReceiptDetailInstructionsAdapter(it)
                            instructionsRecyclerView.adapter = receiptDetailInstructionsAdapter
                            instructionsRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
                            instructionsRecyclerView.setNestedScrollingEnabled(false)
                        }
                    }

                    val ingredients = mutableListOf<Ingredient>()



                    for (i in 1..20) {
                        val ingredient =  meal.get("strIngredient$i").toString()
                        val measure = meal.get("strMeasure$i").toString()
                        val mealIngredient = Ingredient()
                        if (!ingredient.equals("null") && !ingredient.equals("")) {
                            mealIngredient.ingredient = ingredient
                            mealIngredient.measure = measure
                            ingredients.add(mealIngredient)
                        }
                    }
                    ingredients[0].ingredient?.let { it1 -> Log.d("ReceiptHTTP", it1) }
//                    val receiptResponse = gson.fromJson(it, ReceiptDetailResponse::class.java)
                    ingredients?.let { it1 ->
                        runOnUiThread {
                            receiptDetailIngredientsAdapter = ReceiptDetailIngredientsAdapter(it1)
                            ingredientsRecyclerView.adapter = receiptDetailIngredientsAdapter
                            ingredientsRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
                            ingredientsRecyclerView.setNestedScrollingEnabled(false)
                        }
                    }

                }
            }
        })
    }
}