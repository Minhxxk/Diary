package com.example.diary

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(val context: Context, var dataList: ArrayList<ItemData>): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    //1. 아이템 레이아웃과 결합
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false)

        return ViewHolder(view)
    }
    //2. 리스트 내 아이템 개수
    override fun getItemCount(): Int {
        Log.i("minhxxk", "${dataList.size}")
        return dataList.size
    }

    //3. View에 내용 입력
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    //4. 레이아웃 내 View 연결
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val title = itemView.findViewById<TextView>(R.id.rv_title)
        private val date = itemView.findViewById<TextView>(R.id.rv_date)
        private val remove  = view.findViewById<ImageView>(R.id.iv_remove)

        fun bind(item: ItemData) {
            title.text = item.content
            date.text = item.date

            //Item의 X 버튼 클릭 시
            remove.setOnClickListener {
                var position = adapterPosition
                Log.i("minhxxk", "${position}번 Remove 클릭")

                DBHelper(context).deleteDiary(item.content, item.date)
                dataList.removeAt(position)
                DiaryDecorator(item.date)
                notifyItemRemoved(position)

            }

            //RecyclerView의 Item 클릭 시
            itemView.setOnClickListener {
                Intent(context, DialogCustom::class.java).apply {
                    putExtra("data_date", item.date)
                    putExtra("data_title", item.content)
                    //실행하는 액티비티를 새 TASK로 생성
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }
            }
        }
    }
}

