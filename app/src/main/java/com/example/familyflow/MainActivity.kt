package com.example.familyflow

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), OnDateSelectedListener {
    private val events = mutableListOf<Event>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EventAdapter

    override fun onDateSelected(selectedDate: String) {
        showAddEventFragment(selectedDate)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)

        val calendarView = findViewById<CustomCalendarView>(R.id.calendarView)
        calendarView.setOnDateSelectedListener(this)

        recyclerView = findViewById<RecyclerView>(R.id.RecyclerView)
        adapter = EventAdapter(events)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initial events (you can remove or modify these)
        events.addAll(
            listOf(
                Event("14", "Mom's Birthday"),
                Event("23", "House Blessing"),
                Event("30", "Holiday")
            )
        )
        adapter.notifyDataSetChanged() // Notify adapter of initial data

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year"
            showAddEventFragment(selectedDate)
        }
    }

    private fun showAddEventFragment(selectedDate: String) {
        val addEventFragment = AddEventFragment()
        val bundle = Bundle()
        bundle.putString("selectedDate", selectedDate)
        addEventFragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, addEventFragment)
            .addToBackStack(null)
            .commit()
    }

    fun addEvent(event: Event) {
        events.add(event)
        adapter.notifyItemInserted(events.size - 1)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}