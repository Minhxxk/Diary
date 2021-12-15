package com.example.diary

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import com.example.diary.databinding.ActivityCustomDialogBinding

class CustomDialog(context: Context){
    private val dialog = Dialog(context)
    lateinit var customDialogBinding: ActivityCustomDialogBinding

    init(content: String, date: String) {
        this.content = content
        this.date = date
    }

    fun ShowDialog() {
        Log.i("minhxxk", "$content + $date")
        dialog.setContentView(R.layout.activity_custom_dialog)
        //Dialog크기 조정
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT)
        //Dialog 영역 밖을 터치하였을 때, Dialog가 사라짐
        dialog.setCanceledOnTouchOutside(true)
        //Dialog 취소가 가능하도록 하는 코드
        dialog.setCancelable(true)


        dialog.show()
    }
}