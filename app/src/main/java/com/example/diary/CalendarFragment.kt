package com.example.diary

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match


class CalendarFragment : Fragment() {
    var diaryList = mutableListOf<ItemData>()
    lateinit var mainActivity: MainActivity
    lateinit var recyclerView: RecyclerView
    lateinit var mAdapter: ItemAdapter
    lateinit var todayText: String
    lateinit var todayDate: String

    //Fragment가 생성될 때 호출되는 부분        super.onCreate(savedInstanceState)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        todayText = arguments?.getString("TodayText").toString()
        todayDate = arguments?.getString("Today").toString()
    }

    //onCreate 후에 화면을 구성할 때 호출되는 부분
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(R.layout.fragment_calendar, container, false)
        recyclerView = rootView.findViewById(R.id.recyclerView)
        onSelect()
        initRecycler()

        return rootView
    }

    private fun initRecycler() {
        mAdapter = ItemAdapter(requireContext(), diaryList as ArrayList<ItemData>)
        recyclerView.adapter = mAdapter
        diaryList.apply {
            add(ItemData("$todayText", "$todayDate"))
            mAdapter.notifyDataSetChanged()
        }
    }
    //DIARYLIST테이블 SELECT
    private fun onSelect() {
        var query = "SELECT * FROM DIARYLIST;"
        var cursor = mainActivity.database.rawQuery(query, null)
        while (cursor.moveToNext()){
            Log.i("minhxxk", "CONTEXT : ${cursor.getString(cursor.getColumnIndexOrThrow("content"))}\n DATE : ${cursor.getString(cursor.getColumnIndexOrThrow("date"))}")
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}