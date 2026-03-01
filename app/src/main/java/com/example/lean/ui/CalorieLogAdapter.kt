package com.example.lean.ui

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.lean.R
import com.example.lean.data.CalorieLog
import com.example.lean.util.formatDateTime
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial
import java.util.Locale

class CalorieLogAdapter(
    private val onDelete: (CalorieLog) -> Unit
) : RecyclerView.Adapter<CalorieLogAdapter.CalorieLogViewHolder>() {

    private val items = mutableListOf<CalorieLog>()

    fun submitList(logs: List<CalorieLog>) {
        items.clear()
        items.addAll(logs.sortedByDescending { it.timestamp })
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalorieLogViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_calorie_log, parent, false)
        return CalorieLogViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalorieLogViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class CalorieLogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvFood: TextView = itemView.findViewById(R.id.tvFood)
        private val tvDetail: TextView = itemView.findViewById(R.id.tvDetail)
        private val tvMacros: TextView = itemView.findViewById(R.id.tvMacros)
        private val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)

        fun bind(item: CalorieLog) {
            tvFood.text = item.name
            tvDetail.text = "${item.calories} kcal • ${formatDateTime(item.timestamp)}"
            tvMacros.text = String.format(
                Locale.US,
                "P %.1fg  •  C %.1fg  •  F %.1fg",
                item.proteinGrams,
                item.carbsGrams,
                item.fatsGrams
            )

            val iconColor = ColorStateList.valueOf(
                ContextCompat.getColor(itemView.context, R.color.ink_secondary)
            )
            val iconSizePx = (20 * itemView.resources.displayMetrics.density).toInt()
            btnDelete.setImageDrawable(
                IconicsDrawable(itemView.context, CommunityMaterial.Icon3.cmd_trash_can_outline)
                    .apply {
                        colorList = iconColor
                        sizeXPx = iconSizePx
                        sizeYPx = iconSizePx
                    }
            )
            btnDelete.setOnClickListener { onDelete(item) }
        }
    }
}
