package com.example.diary

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import com.example.diary.databinding.FragmentTodayBinding
import java.time.LocalDate
import java.util.*

class TodayFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var tvDate: TextView
    lateinit var etText: EditText
    lateinit var ivSave: ImageView


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(R.layout.fragment_today, container, false)
        // Inflate the layout for this fragment
        tvDate = rootView.findViewById(R.id.tv_date)
        etText = rootView.findViewById(R.id.et_Text)
        ivSave = rootView.findViewById(R.id.iv_save)

        //현재 날짜 가져오기
        val todayDate: LocalDate = LocalDate.now()
        tvDate.text = todayDate.toString()

        //Save버튼 클릭 시
        ivSave.setOnClickListener{
            val todayText = etText.text.toString()
            etText.text.clear()

            val calendarFragment = mainActivity.calendarFragment
            var bundle = Bundle()
            bundle.putString("TodayText", todayText)
            bundle.putString("Today", todayDate.toString())
            Log.i("minhxxk", "$todayText")
            Toast.makeText(context, "오늘의 일기가 작성되었습니다.", Toast.LENGTH_SHORT).show()
            //fragment의 arguments에 데이터를 담은 bundle을 넘겨줌
            calendarFragment.arguments = bundle
            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.framelayout, calendarFragment)
                .commit()
        }

        return rootView
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }


}