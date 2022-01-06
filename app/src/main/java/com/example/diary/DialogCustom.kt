package com.example.diary

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.diary.databinding.ActivityDialogCustomBinding

class DialogCustom : AppCompatActivity() {
    //전역 변수로 바인딩 객체 선언
    lateinit var dcBinding: ActivityDialogCustomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_dialog_custom)
        //자동 생성된 뷰 바인딩 클래스에서의 inflate라는 메서드를 활용해서
        //액티비티에서 사용할 바인딩 클래스의 인스턴스 생성
        dcBinding = ActivityDialogCustomBinding.inflate(layoutInflater)
        //getRoot메서드로 레이아웃 내부의 최상위 위치 뷰의
        //인스턴스를 활용하여 생성된 뷰를 액티비티에 표시
        setContentView(binding.root)

        val date = intent.getSerializableExtra("data_date")
        val content = intent.getSerializableExtra("data_title")

        binding.tvDateTitle.text = "$date"
        val et_Content = binding.etContent.setText("$content")

        binding.btnOk.setOnClickListener{
            Toast.makeText(this@DialogCustom, "오늘의 일기가 수정되었습니다.", Toast.LENGTH_SHORT).show()
            DBHelper(applicationContext).upgradeDiary(et_Content, content)
            finish()
        }
        binding.btnCancel.setOnClickListener(CancelButtonListener())
        Log.i("minhxxk", "asdasd")
    }

    //CustomDialog의 확인 버튼 클릭 시
    inner class AcceptButtonListener(): View.OnClickListener{
        override fun onClick(v: View?) {
        }
    }

    //CustomDialog의 취소 버튼 클릭 시
    inner class CancelButtonListener: View.OnClickListener{
        override fun onClick(v: View?) {
            finish()
        }
    }

//    override fun onDestroy() {
//        //onDestroy에서 binding class 인스턴스 참조를 정리해주어야 한다.
//        dcBinding = null
//        super.onDestroy()
//    }
}