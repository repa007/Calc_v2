package com.repa007.calc_v2

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.repa007.calc_v2.databinding.ActivityScrollingBinding

class ScrollingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)


        val RecyclerView = findViewById<ListView>(R.id.userlist)
        val adapter: HistoryAdapter = HistoryAdapter()
        binding.RecyclerView.adapter = adapter
        RecyclerView.layoutManager = LinearLayoutManager(this)

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "my-database").build()
        val dbHistoryDao = db.dbHistoryDAO()
        val historyList = dbHistoryDao!!.getAll()
        adapter.setItems(historyList)
    }
}
class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val calculationTextView: TextView = itemView.findViewById(R.id.history_calculation)
    val resultTextView: TextView = itemView.findViewById(R.id.history_result)
    val timeTextView: TextView = itemView.findViewById(R.id.history_time)

    fun bind(dbHistory: DBHistory) {
        calculationTextView.text = dbHistory.calculation
        resultTextView.text = dbHistory.result.toString()
        timeTextView.text = dbHistory.time.toString()
    }
}