package com.example.lean.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lean.R
import com.example.lean.data.WorkoutLog
import com.example.lean.util.formatDateTime

class WorkoutLogAdapter : RecyclerView.Adapter<WorkoutLogAdapter.WorkoutLogViewHolder>() {

    private val items = mutableListOf<WorkoutLog>()

    fun submitList(logs: List<WorkoutLog>) {
        items.clear()
        items.addAll(logs.sortedByDescending { it.timestamp })
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutLogViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_workout_log, parent, false)
        return WorkoutLogViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkoutLogViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class WorkoutLogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvMeta: TextView = itemView.findViewById(R.id.tvMeta)

        fun bind(item: WorkoutLog) {
            tvName.text = item.routineName
            tvMeta.text = "${item.minutes} min • ${item.source} • ${formatDateTime(item.timestamp)}"
        }
    }
}
