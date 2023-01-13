package com.example.apprecettes.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apprecettes.R
import com.example.apprecettes.activity.ReceiptActivity
import com.example.apprecettes.model.Categorie


class CategorieViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var categorieTitle : TextView = itemView.findViewById(R.id.categorie_title)
}

class CategoriesAdapter(var categories: List<Categorie>, val context: Context): RecyclerView.Adapter<CategorieViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategorieViewHolder {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.categorie_item, parent, false)
                return CategorieViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: CategorieViewHolder, position: Int) {
                holder.categorieTitle.setText(categories.get(position).strCategory)
                holder.itemView.setOnClickListener {
                        val bundle = Bundle()
                        bundle.putString("categoryTitle", categories.get(position).strCategory)
                        categories.get(position).strCategory?.let { it1 -> Log.d("OKHTTP", it1) }
                        val switchActivityIntent = Intent(context , ReceiptActivity::class.java)
                        switchActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
                        switchActivityIntent.putExtras(bundle)
                        context.startActivity(switchActivityIntent)
                }
        }

        override fun getItemCount(): Int {
                return categories.count()
        }

        fun setFilteredList(filtredList: List<Categorie>){
                categories = filtredList;
        }
}