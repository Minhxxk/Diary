package com.example.diary

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, name, null, version) {
    companion object{
        private val name = "diary.db"
        private val version = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        var sql : String = "CREATE TABLE if not exists DIARYLIST (" +
                "content TEXT," +
                "date TEXT);";
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val sql = "DROP TABLE if exists DIARYLIST"
        db.execSQL(sql)
        onCreate(db)
    }

    fun upgradeDiary(et_content: String, content: String, date: String){
        val db = this.writableDatabase
        var query = "UPDATE DIARYLIST SET content = '${et_content}' WHERE date = '${date}' and content = '${content}';"
        db.execSQL(query)
    }

    fun insertDiary(todayText: String, todayDate: String){
        val db = this.writableDatabase
        var query = "INSERT INTO DIARYLIST(content, date) values('$todayText', '$todayDate');"
        db.execSQL(query)
    }
}