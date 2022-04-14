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
    private val mAdapter: ItemAdapter? by lazy {
        ItemAdapter(requireContext(), diaryList as ArrayList<ItemData>)
    }
    lateinit var todayDate: String
    private val saturdayDecorator = SaturdayDecorator()
    private val sundayDecorator = SundayDecorator()

    //프래그먼트가 액티비티와 연결되어 있었던 경우 호출됩니다. 여기서 액티비티가 전달됩니다.
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    //onCreate 후에 화면을 구성할 때 호출되는 부분
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.i("minhxxk", "onCreateView")
        var rootView = inflater.inflate(R.layout.fragment_calendar, container, false)
        onBinding(rootView)

        //오늘 날짜 selected
        materialCalendarView.selectedDate = CalendarDay.today()
        //해당 날짜에 작성한 일기가 있으면 Decorate
        decorateCheckDiary()
        //주말(토요일->파란색, 일요일->빨간색)
        materialCalendarView.addDecorators(saturdayDecorator, sundayDecorator)
        //작성된 오늘 일기 가져오기
        getTodayDiary()

        //MaterialCalendarView 날짜 변경 시 이벤트
        materialCalendarView.setOnDateChangedListener { widget, date, selected ->   //widget: MaterialCalendarView, date: CalendarDay, selected: Boolean
            diaryList.clear()
            var selectedDateStr = "${date.year}-" + String.format("%02d", date.month + 1) + "-" + String.format("%02d", date.day)
            mAdapter?.notifyDataSetChanged()
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

    private fun onBinding(rootView: View) {
        recyclerView = rootView.findViewById(R.id.recyclerView)
        materialCalendarView = rootView.findViewById(R.id.materialCalendar)
        recyclerView.adapter = mAdapter
    }

    //Refresh
    override fun onResume() {
        super.onResume()
        //선택된 항목의 일기 수정 후 Refresh
        getTodayDiary()
//        Log.i("minhxxk", "onResume")
    }

    //해당 날짜에 작성한 일기가 있으면 SELECT 후 Decorate 이벤트
    private fun decorateCheckDiary(){
        var query = "SELECT * FROM DIARYLIST;"
        var cursor = mainActivity.database.rawQuery(query, null)
        while (cursor.moveToNext()) {
            diaryList.apply{
                materialCalendarView.addDecorator(DiaryDecorator(cursor.getString(cursor.getColumnIndexOrThrow("date"))))
            }
        }
    }

    //데이터베이스에 저장된 오늘 일기 SELECT 이벤트
    private fun getTodayDiary(){
        todayDate = "${CalendarDay.today().year}-${String.format("%02d", CalendarDay.today().month+1)}-${String.format("%02d", CalendarDay.today().day)}"
        var query = "SELECT * FROM DIARYLIST WHERE date = '$todayDate';"
        var cursor = mainActivity.database.rawQuery(query, null)
        diaryList.clear()
        while (cursor.moveToNext()) {
            diaryList.apply{
                add(ItemData(cursor.getString(0), cursor.getString(1)))
            }
        }
        mAdapter?.notifyDataSetChanged()
    }
}