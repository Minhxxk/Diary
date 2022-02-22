package com.example.diary

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.diary.databinding.ActivityDialogCustomBinding

class DialogCustom() : AppCompatActivity() {
    //전역 변수로 바인딩 객체 선언
//    var diaryList = mutableListOf<ItemData>()
    lateinit var date: String
    lateinit var content: String
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
        //클릭된 Item의 일기 날짜, 내용 가져오기
        getDiary()
        dcBinding.tvDateTitle.setText("$date")
        dcBinding.etContent.setText("$content")

        //CustomDialog의 확인 버튼 클릭 시
        dcBinding.btnOk.setOnClickListener(AcceptButtonListener())
        //CustomDialog의 취소 버튼 클릭 시
        dcBinding.btnCancel.setOnClickListener(CancelButtonListener())
    }

    //CustomDialog의 확인 버튼 클릭 이벤트
    inner class AcceptButtonListener: View.OnClickListener{
        override fun onClick(v: View?) {
            val et_Content = dcBinding.etContent.text.toString()
            DBHelper(applicationContext).upgradeDiary(et_Content, content, date)
            Toast.makeText(this@DialogCustom, "오늘의 일기가 수정되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
            Log.i("minhxxk", "DialogCustom 확인..")
        }
    }

    //CustomDialog의 취소 버튼 클릭 이벤트
    inner class CancelButtonListener: View.OnClickListener{
        override fun onClick(v: View?) {
            finish()
            Log.i("minhxxk", "DialogCustom 취소..")
        }
    }
    fun getDiary() {
        date = intent.getStringExtra("data_date").toString()
        content = intent.getStringExtra("data_title").toString()
    }
}