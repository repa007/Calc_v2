package com.repa007.GreatCalculator

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    private var historyList: List<DBHistory> = emptyList()

    // Добавляем новое поле для хранения группированных данных
    private var groupedHistoryList: List<Pair<String, List<DBHistory>>> = emptyList()

    

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val (date, history) = groupedHistoryList[position]
        holder.bind(date, history)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun setItems(list: List<DBHistory>) {
        historyList = list
        // Группируем данные по дате
        groupedHistoryList = historyList.groupBy { getFormattedDate(it.time) }
            .toList()
            .sortedByDescending { it.first }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return groupedHistoryList.size
    }

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTextView: TextView = itemView.findViewById(R.id.history_date)
        val historyRecyclerView: RecyclerView = itemView.findViewById(R.id.history_recycler_view)

        fun bind(date: String, history: List<DBHistory>) {
            dateTextView.text = date

            // Создаем и устанавливаем адаптер для вложенного RecyclerView
            val adapter = InnerHistoryAdapter()
            historyRecyclerView.adapter = adapter
            historyRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
            adapter.setItems(history)
        }
    }

    // Вспомогательный метод для форматирования даты
    @RequiresApi(Build.VERSION_CODES.N)
    private fun getFormattedDate(time: String?): String {
        val dateFormat = SimpleDateFormat("d MMM", Locale.getDefault())
        return dateFormat.format(java.util.Date(time))
    }

    // Вложенный адаптер для отображения элементов внутри группы
    class InnerHistoryAdapter : RecyclerView.Adapter<InnerHistoryAdapter.InnerHistoryViewHolder>() {
        private var historyList: List<DBHistory> = emptyList()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerHistoryViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.inner_list_item, parent, false)
            return InnerHistoryViewHolder(view)
        }

        override fun onBindViewHolder(holder: InnerHistoryViewHolder, position: Int) {
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

        class InnerHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val calculationTextView: TextView = itemView.findViewById(R.id.history_calculation)
            val resultTextView: TextView = itemView.findViewById(R.id.history_result)

            fun bind(dbHistory: DBHistory) {
                calculationTextView.text = dbHistory.calculation.toString()
                resultTextView.text = dbHistory.result.toString()
            }
        }
    }
}
