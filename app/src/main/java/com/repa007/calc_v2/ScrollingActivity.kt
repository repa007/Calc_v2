package com.repa007.calc_v2

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.repa007.calc_v2.databinding.ActivityMainBinding
import com.repa007.calc_v2.databinding.ActivityScrollingBinding

class ScrollingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollingBinding
    private lateinit var historyLayoutMgr: LinearLayoutManager
    private lateinit var hRecyclerView: RecyclerView

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "my-database").allowMainThreadQueries().build()
        val dbHistoryDao = db.dbHistoryDAO()
        val historyList = dbHistoryDao!!.getAll()

        // Создаем и устанавливаем адаптер для RecyclerView
        val adapter = HistoryAdapter()
        binding.userlist1.adapter = adapter
        binding.userlist1.layoutManager = LinearLayoutManager(this)
        adapter.setItems(historyList)
    }
}
