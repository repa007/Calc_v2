package com.repa007.calc_v2

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter : RecyclerView.Adapter<HistoryViewHolder>(){
    private var historyList: List<DBHistory> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val history = historyList[position]
        holder.bind(history)
    }

    fun setItems(list: List<DBHistory>) {
        historyList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return historyList.size
    }
}