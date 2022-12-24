package com.example.apprecettes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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
        Picasso.with(context).load(receipts.get(position).strMealThumb).into(holder.image);
    }

    override fun getItemCount(): Int {
        return receipts.count()
    }
}