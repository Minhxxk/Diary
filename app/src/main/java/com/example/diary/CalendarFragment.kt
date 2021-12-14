package com.example.diary

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match


class CalendarFragment : Fragment() {
    var diaryList = mutableListOf<ItemData>()
    lateinit var mainActivity: MainActivity
    lateinit var materialCalendarView: MaterialCalendarView
    lateinit var recyclerView: RecyclerView
    lateinit var mAdapter: ItemAdapter
    lateinit var todayDate: String

    //프래그먼트가 액티비티와 연결되어 있었던 경우 호출됩니다. 여기서 액티비티가 전달됩니다.
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    //Fragment가 생성될 때 호출되는 부분        super.onCreate(savedInstanceState)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //onCreate 후에 화면을 구성할 때 호출되는 부분
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(R.layout.fragment_calendar, container, false)
        val saturdayDecorator = SaturdayDecorator()
        val sundayDecorator = SundayDecorator()
        recyclerView = rootView.findViewById(R.id.recyclerView)
        materialCalendarView = rootView.findViewById(R.id.materialCalendar)
//        materialCalendarView.selectedDate = CalendarDay.today()
        initRecycler()
        materialCalendarView.addDecorators(saturdayDecorator, sundayDecorator)

        materialCalendarView.setOnDateChangedListener { widget, date, selected ->
            var selectedDateStr = "${date.year}-" + String.format("%02d", date.month + 1) + "-" + String.format("%02d", date.day)
            diaryList.clear()
            mAdapter.notifyDataSetChanged()
            Log.i("minhxxk", selectedDateStr)
            var query = "SELECT * FROM DIARYLIST WHERE date = '$selectedDateStr';"
            var cursor = mainActivity.database.rawQuery(query, null)
            while (cursor.moveToNext()) {
                Log.i("minhxxk", "CONTEXT : ${cursor.getString(cursor.getColumnIndexOrThrow("content"))} DATE : ${cursor.getString(cursor.getColumnIndexOrThrow("date"))}")
                diaryList.apply{
                    add(ItemData(cursor.getString(0), cursor.getString(1)))
                }
            }
        }
        return rootView
    }

    //DIARYLIST테이블 SELECT
    private fun onSelect() {
        diaryList.clear()
        todayDate = "${CalendarDay.today().year}-${String.format("%02d", CalendarDay.today().month+1)}-${String.format("%02d", CalendarDay.today().day)}"
        Log.i("minhxxk", todayDate)
        var query = "SELECT * FROM DIARYLIST;"
        var cursor = mainActivity.database.rawQuery(query, null)
        while (cursor.moveToNext()) {
//            Log.i("minhxxk", "CONTEXT : ${cursor.getString(cursor.getColumnIndexOrThrow("content"))} DATE : ${cursor.getString(cursor.getColumnIndexOrThrow("date"))}")
            diaryList.apply{
                add(ItemData(cursor.getString(0), cursor.getString(1)))
                materialCalendarView.addDecorator(DiaryDecorator(cursor.getString(cursor.getColumnIndexOrThrow("date"))))
            }
        }
    }

    //RecyclerView 초기화
    private fun initRecycler() {
        mAdapter = ItemAdapter(requireContext(), diaryList as ArrayList<ItemData>)
        recyclerView.adapter = mAdapter
        onSelect()
        mAdapter.notifyDataSetChanged()
    }
}