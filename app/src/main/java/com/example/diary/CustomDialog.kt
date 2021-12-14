package com.example.diary

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CustomDialog(context: Context){
    private val dialog = Dialog(context)

    fun myDig(){
        setContentView(R.layout.activity_custom_dialog)
        dialog.show()
    }
}