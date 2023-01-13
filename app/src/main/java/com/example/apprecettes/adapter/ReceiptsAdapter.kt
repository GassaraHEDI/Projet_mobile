package com.example.apprecettes.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apprecettes.R
import com.example.apprecettes.activity.ReceiptDetailActivity
import com.example.apprecettes.model.Receipt
import com.squareup.picasso.Picasso


class ReceiptViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    var receiptTitle : TextView = itemView.findViewById(R.id.receipt_title)
    var image : ImageView = itemView.findViewById(R.id.imageView_content)
}

class ReceiptsAdapter(val receipts: List<Receipt>, val context: Context): RecyclerView.Adapter<ReceiptViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.receipt_item, parent, false)
        return ReceiptViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReceiptViewHolder, position: Int) {
        holder.receiptTitle.setText(receipts.get(position).strMeal)
        Picasso.with(context).load(receipts.get(position).strMealThumb).into(holder.image)
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("receiptId", receipts.get(position).idMeal)
            bundle.putString("receiptTitle", receipts.get(position).strMeal)
            receipts.get(position).strMeal?.let { it1 -> Log.d("OKHTTP", it1) }
            val switchActivityIntent = Intent(context , ReceiptDetailActivity::class.java)
            switchActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
            switchActivityIntent.putExtras(bundle)
            context.startActivity(switchActivityIntent)
        }
    }

    override fun getItemCount(): Int {
        return receipts.count()
    }
}