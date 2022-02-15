package com.example.diary

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.diary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val todayFragment = TodayFragment()
    private val calendarFragment = CalendarFragment()
    lateinit var dbHelper: DBHelper         //dbHelper 선언
    lateinit var database: SQLiteDatabase   //database 선언


    //메뉴 구성 함수
    //액티비티 코드에 menu.xml 적용
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        R.id.menu1 -> {
            setTodayFragment()
            true
        }
        R.id.menu2 -> {
            setCalendarFragment()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //DB생성 및 불러오기
        Log.i("minhxxk", "dsadsa")
        Log.i("minhxxk", "DB 생성")
        dbHelper = DBHelper(this)
        database = dbHelper.writableDatabase

        setTodayFragment()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTodayFragment()
        binding.tab1.setOnClickListener {
            setTodayFragment()
        }
        binding.tab2.setOnClickListener {
            setCalendarFragment()
        }
    }

    private fun setTodayFragment() {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.framelayout, todayFragment)
        transaction.commit()
    }
    private fun setCalendarFragment() {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.framelayout, calendarFragment)
        transaction.commit()
    }
}