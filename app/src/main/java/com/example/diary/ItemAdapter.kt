package com.example.diary

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val context: Context, private val dataList: ArrayList<ItemData>): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        Log.i("minhxxk", "${dataList.size}")
        return dataList.size
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val title = itemView.findViewById<TextView>(R.id.rv_title)
        private val date = itemView.findViewById<TextView>(R.id.rv_date)

        fun bind(item: ItemData) {
            title.text = item.title
            date.text = item.date

            itemView.setOnClickListener {
                Intent(context, DialogCustom::class.java).apply {
                    putExtra("data_date", item.date)
                    putExtra("data_title", item.title)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }
            }
        }
    }
}

