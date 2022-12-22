package com.example.apprecettes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apprecettes.model.Ingredient

class ReceiptDetailViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    var ingredientTitle : TextView = itemView.findViewById(R.id.ingredient_title)
    var measureTitle : TextView = itemView.findViewById(R.id.measure_title)
}

class ReceiptDetailAdapter(val ingredients: List<Ingredient>): RecyclerView.Adapter<ReceiptDetailViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptDetailViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ingredient_measure_item, parent, false)
        return ReceiptDetailViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReceiptDetailViewHolder, position: Int) {
        holder.ingredientTitle.setText(ingredients.get(position).ingredient)
        holder.measureTitle.setText(ingredients.get(position).measure)
    }

    override fun getItemCount(): Int {
        return ingredients.count()
    }
}