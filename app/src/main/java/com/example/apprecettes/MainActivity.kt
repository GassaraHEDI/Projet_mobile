package com.example.apprecettes

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var categoriesAdapter : CategoriesAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var categories: List<Categorie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.setTitle("Categories")

        recyclerView = findViewById(R.id.recycler_view)
        val url = URL("https://www.themealdb.com/api/json/v1/1/categories.php")

        val request = Request.Builder()
            .url(url)
            .build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.e("OKHTTP", e.localizedMessage)
            }

            override fun onResponse(call: Call, response: Response) {

                response.body?.string()?.let {
                    Log.d("OKHTTP",it)
                    val gson = Gson()
                    val categoriesResponse = gson.fromJson(it, CategoriesResponse::class.java)
                    categoriesResponse.categories?.let { it1 ->
                        runOnUiThread {
                            categories = it1
                            categoriesAdapter = CategoriesAdapter(it1, context = applicationContext)
                            recyclerView.adapter = categoriesAdapter
                            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                        }

                    }

                    Log.d("OKHTTP", "Got " + categoriesResponse.categories?.count() + " results")
                }
            }
        })

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        val search : MenuItem? = menu?.findItem(R.id.nav_search)
        val searchView : SearchView = search?.actionView as SearchView
        searchView.queryHint = "Search something"

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return  false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {

                    recyclerView.adapter = filtredList(newText)
                    recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                }
                if (TextUtils.isEmpty(newText)){
                    categoriesAdapter =CategoriesAdapter(categories, context = applicationContext)
                    recyclerView.adapter = categoriesAdapter
                    recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                }
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    fun filtredList(text : String): CategoriesAdapter {
        var filtredList: ArrayList<Categorie> =  ArrayList<Categorie>()
        Log.d("OKHTTP", categoriesAdapter.categories.toString())
        for (categorie in categoriesAdapter.categories) {
            if (categorie.strCategory?.toLowerCase()?.contains(text.toLowerCase()) == true) {
                   filtredList.add(categorie)

                Log.d("OKHTTP", filtredList.toString())
            }
        }

        if (!filtredList.isEmpty()){
            categoriesAdapter.setFilteredList(filtredList);
        }
        return categoriesAdapter
    }

}