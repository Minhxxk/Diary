package com.example.diary

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.diary.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_dialog_custom.*

class DialogCustom : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_custom)

        val date = intent.getSerializableExtra("data_date")
        val content = intent.getSerializableExtra("data_title")
        tv_date_title.text = "$date"
        et_content.setText("$content")

        btn_ok.setOnClickListener(AcceptButtonListener())
        btn_cancel.setOnClickListener(CancelButtonListener())
    }

    //CustomDialog의 확인 버튼 클릭 시
    inner class AcceptButtonListener: View.OnClickListener{
        override fun onClick(v: View?) {
            val database = MainActivity().database
            var query = "UPDATE DIARYLIST SET content = ${et_content.text};"
            database.execSQL(query)
            Toast.makeText(this@DialogCustom, "오늘의 일기가 수정되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    //CustomDialog의 취소 버튼 클릭 시
    inner class CancelButtonListener: View.OnClickListener{
        override fun onClick(v: View?) {
            finish()
        }

    }
}