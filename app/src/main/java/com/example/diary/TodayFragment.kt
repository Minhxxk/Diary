package com.example.diary

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import java.time.LocalDate

class TodayFragment : Fragment() {
    lateinit var mainActivity: MainActivity
    lateinit var tvDate: TextView
    lateinit var etText: EditText
    lateinit var btnSave: Button

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(R.layout.fragment_today, container, false)
        // Inflate the layout for this fragment
        onBinding(rootView)

        //현재 날짜 가져오기
        val todayDate: LocalDate = LocalDate.now()
        tvDate.text = todayDate.toString()

        //Save버튼 클릭 시
        btnSave.setOnClickListener{
            val todayText: String
            if(etText.text.toString() == ""){
                todayText = etText.hint.toString()
            }
            else{
                todayText = etText.text.toString()
            }
            etText.text.clear()

            //DB에 일기 INSERT
            DBHelper(requireContext()).insertDiary(todayText, todayDate.toString())
            Toast.makeText(context, "오늘의 일기가 작성되었습니다.", Toast.LENGTH_SHORT).show()

        }
        return rootView
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    fun onBinding(rootView: View){
        tvDate = rootView.findViewById(R.id.tv_date)
        etText = rootView.findViewById(R.id.et_Text)
        btnSave = rootView.findViewById(R.id.btn_save)
    }


}