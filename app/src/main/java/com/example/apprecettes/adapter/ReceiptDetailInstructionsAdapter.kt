package com.example.apprecettes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apprecettes.R
import com.example.apprecettes.model.Ingredient

class ReceiptDetailInstructionsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    var instructionInfo : TextView = itemView.findViewById(R.id.instruction_info)
    var instructionId : TextView = itemView.findViewById(R.id.instruction_id)
}

class ReceiptDetailInstructionsAdapter(val instructions: List<String>): RecyclerView.Adapter<ReceiptDetailInstructionsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptDetailInstructionsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.instruction_item, parent, false)
        return ReceiptDetailInstructionsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReceiptDetailInstructionsViewHolder, position: Int) {
        holder.instructionInfo.setText(instructions.get(position))
        holder.instructionId.setText((position+1).toString())
    }

    override fun getItemCount(): Int {
        return instructions.count()
    }
}