package com.example.diary

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan

class DiaryDecorator(date: String): DayViewDecorator {
    private val diaryDate = date
    override fun shouldDecorate(day: CalendarDay?): Boolean {
        val dateStr = "${day?.year}-${String.format("%02d", day?.month!! + 1)}-${String.format("%02d", day?.day)}"
//        Log.i("minhxxk", dateStr)
        return dateStr == diaryDate
    }

    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(object : DotSpan(5F, Color.GREEN){})
    }
}