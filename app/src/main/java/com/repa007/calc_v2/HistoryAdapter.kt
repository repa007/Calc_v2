package com.repa007.calc_v2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>(){
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
    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val calculationTextView: TextView = itemView.findViewById(R.id.history_calculation)
        val resultTextView: TextView = itemView.findViewById(R.id.history_result)
        val timeTextView: TextView = itemView.findViewById(R.id.history_time)

        fun bind(dbHistory: DBHistory) {
            calculationTextView.text = dbHistory.calculation.toString()
            resultTextView.text = dbHistory.result.toString()
            timeTextView.text = dbHistory.time.toString()
        }
        var str = "blabla"


    }



}