package com.example.familyflow

import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.util.AttributeSet
import android.widget.CalendarView
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date

class CustomCalendarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CalendarView(context, attrs, defStyleAttr) {

    private var onDateSelectedListener: OnDateSelectedListener? = null

    fun setOnDateSelectedListener(listener: OnDateSelectedListener) {
        onDateSelectedListener = listener
    }

    init {
        setBackgroundResource(R.drawable.rounded_corners)

        // Find the TextView that displays the date text
        val dateTextView = findViewById<TextView>(android.R.id.text1) // Or the appropriate ID for your CalendarView implementation
        dateTextView?.setTextColor(Color.BLACK) // Set text color to black

        setOnDateChangeListener { _, year, month, dayOfMonth ->
            // ... (rest of your code)
        }
    }
}