package com.example.apprecettes.adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apprecettes.R
import com.example.apprecettes.activity.ReceiptActivity
import com.example.apprecettes.model.Categorie
import com.squareup.picasso.Picasso


class CategorieViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var categorieTitle : TextView = itemView.findViewById(R.id.categorie_title)
        var image : ImageView = itemView.findViewById(R.id.imageView_categorie)
        var heartImageView : ImageView = itemView.findViewById(R.id.favorite_icon)
}

class CategoriesAdapter(var categories: List<Categorie>, val context: Context): RecyclerView.Adapter<CategorieViewHolder>(){
        val sharedPreferences:SharedPreferences? = context.getSharedPreferences("shared_preferences", android.content.Context.MODE_PRIVATE)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategorieViewHolder {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.categorie_item, parent, false)
                return CategorieViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: CategorieViewHolder, position: Int) {
                val isFavorite = sharedPreferences?.getBoolean("is_favorite_"+categories.get(position).idCategory, false)
                if (isFavorite == true){
                        holder.heartImageView.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN)
                }
                holder.heartImageView.setOnClickListener(object : View.OnClickListener {
                        override fun onClick(v: View?) {
                                val isFavorite = sharedPreferences?.getBoolean("is_favorite_"+categories.get(position).idCategory, false)
                                val editor = sharedPreferences?.edit()
                                if (editor != null) {
                                        editor.putBoolean("is_favorite_"+categories.get(position).idCategory, !isFavorite!!)
                                        editor.apply()
                                }
                                if (holder.heartImageView.getColorFilter() == null) {
                                        holder.heartImageView.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN)
                                        sharedPreferences?.getBoolean("is_favorite_"+categories.get(position).idCategory, false)
                                } else {
                                        holder.heartImageView.clearColorFilter()
                                }
                        }
                })
                holder.categorieTitle.setText(categories.get(position).strCategory)
                Picasso.with(context).load(categories.get(position).strCategoryThumb).into(holder.image)
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