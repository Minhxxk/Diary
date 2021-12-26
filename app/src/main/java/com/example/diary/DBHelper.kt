package com.example.diary

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_dialog_custom.*

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
        val sql : String = "DROP TABLE if exists DIARYLIST"
        db.execSQL(sql)
        onCreate(db)
    }

    fun upgradeDiary(content: EditText){
        val db = this.writableDatabase
        var query = "UPDATE DIARYLIST SET content = ${content};"
        db.execSQL(query)
    }

}