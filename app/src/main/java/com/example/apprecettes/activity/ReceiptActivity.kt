package com.example.apprecettes.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apprecettes.R
import com.example.apprecettes.adapter.ReceiptsAdapter
import com.example.apprecettes.model.ReceiptResponse
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.net.URL

class ReceiptActivity: AppCompatActivity()  {
    private lateinit var receiptsAdapter: ReceiptsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var circularProgressIndicator: CircularProgressIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // calling the action bar
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        setContentView(R.layout.activity_receipt)

        recyclerView = findViewById(R.id.recycler_view2)
        circularProgressIndicator = findViewById(R.id.progress_indicator)

        circularProgressIndicator.visibility = View.VISIBLE

        val bundle = intent.extras
        val categoryTitle = bundle?.getString("categoryTitle").toString();
        getSupportActionBar()?.setTitle(categoryTitle)

        Log.d("OKHTTP",categoryTitle)
        val url = URL("https://www.themealdb.com/api/json/v1/1/filter.php?c="+categoryTitle)

        val request = Request.Builder()
            .url(url)
            .build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.e("OKHTTP", e.localizedMessage)
                runOnUiThread {
                    circularProgressIndicator.visibility = View.GONE
                }
            }

            override fun onResponse(call: Call, response: Response) {

                response.body?.string()?.let {
                    Log.d("OKHTTP",it)
                    val gson = Gson()
                    val receiptResponse = gson.fromJson(it, ReceiptResponse::class.java)
                    Log.d("OKHTTP", "Got 1" + receiptResponse.receipts?.count() + " results")
                    receiptResponse.receipts?.let { it1 ->
                        runOnUiThread {
                            circularProgressIndicator.visibility = View.GONE
                            receiptsAdapter = ReceiptsAdapter(it1, context = applicationContext)
                            recyclerView.adapter = receiptsAdapter
                            recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
                        }

                    }

                    Log.d("OKHTTP", "Got " + receiptResponse.receipts?.count() + " results")
                }
            }
        })
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}