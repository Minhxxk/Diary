package com.example.diary

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_dialog_custom.*

class DialogCustom : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_custom)

        val date = intent.getSerializableExtra("data_date")
        val content = intent.getSerializableExtra("data_title")
        tv_date_title.setText("$date")
        et_content.setText("$content")

        btn_ok.setOnClickListener(AcceptButtonListener())
        btn_cancel.setOnClickListener(CancelButtonListener())
    }

    inner class AcceptButtonListener: View.OnClickListener{
        override fun onClick(v: View?) {
            Log.i("minhxxk", "확인 버튼 클릭")
        }
    }
    inner class CancelButtonListener: View.OnClickListener{
        override fun onClick(v: View?) {
            Log.i("minhxxk", "취소 버튼 클릭")
        }

    }
}