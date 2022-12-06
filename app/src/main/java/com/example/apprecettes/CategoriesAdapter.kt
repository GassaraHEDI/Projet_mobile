package com.example.apprecettes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CategorieViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var categorieTitle : TextView = itemView.findViewById(R.id.categorie_title)
}

class CategoriesAdapter(val categories: List<Categorie>): RecyclerView.Adapter<CategorieViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategorieViewHolder {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.categorie_item, parent, false)
                return CategorieViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: CategorieViewHolder, position: Int) {
                holder.categorieTitle.setText(categories.get(position).strCategory)
        }

        override fun getItemCount(): Int {
                return categories.count()
        }
}