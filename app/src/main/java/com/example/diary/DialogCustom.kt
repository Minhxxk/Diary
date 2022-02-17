package com.example.diary

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.diary.databinding.ActivityDialogCustomBinding

class DialogCustom() : AppCompatActivity() {
    //전역 변수로 바인딩 객체 선언
    var diaryList = mutableListOf<ItemData>()
    lateinit var dcBinding: ActivityDialogCustomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_dialog_custom)
        //자동 생성된 뷰 바인딩 클래스에서의 inflate라는 메서드를 활용해서
        //액티비티에서 사용할 바인딩 클래스의 인스턴스 생성
        dcBinding = ActivityDialogCustomBinding.inflate(layoutInflater)
        //getRoot메서드로 레이아웃 내부의 최상위 위치 뷰의
        //인스턴스를 활용하여 생성된 뷰를 액티비티에 표시
        setContentView(dcBinding.root)


        val date = intent.getStringExtra("data_date")
        val content = intent.getStringExtra("data_title")
        dcBinding.tvDateTitle.setText("$date")
        dcBinding.etContent.setText("$content")

        dcBinding.btnOk.setOnClickListener{
            val et_Content = dcBinding.etContent.text.toString()
            DBHelper(applicationContext).upgradeDiary(et_Content, content.toString(), date.toString())
            Toast.makeText(this@DialogCustom, "오늘의 일기가 수정되었습니다.", Toast.LENGTH_SHORT).show()
            Log.i("minhxxk", "Refresh 실행 완료")

            finish()
            Log.i("minhxxk", "DialogCustom 종료")
        }
        dcBinding.btnCancel.setOnClickListener(CancelButtonListener())
    }

    //CustomDialog의 취소 버튼 클릭 시
    inner class CancelButtonListener: View.OnClickListener{
        override fun onClick(v: View?) {
            finish()
            Log.i("minhxxk", "DialogCustom 취소버튼 클릭")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("minhxxk", "DialogCustom 파괴")
    }
}