package com.example.diary

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
    val todayFragment = TodayFragment()
    val calendarFragment = CalendarFragment()
    val settingFragment = SettingFragment()


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
        R.id.menu3 -> {
            setSettingFragment()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        binding.tab3.setOnClickListener {
            setSettingFragment()
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
    private fun setSettingFragment() {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.framelayout, settingFragment)
        transaction.commit()
    }

}