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
import com.example.lean.data.TimetableEntry
import com.example.lean.util.toTimeLabel
import com.google.android.material.checkbox.MaterialCheckBox
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial

class TimetableAdapter(
    private val onEdit: (TimetableEntry) -> Unit,
    private val onDelete: (TimetableEntry) -> Unit,
    private val onDoneToggled: (TimetableEntry, Boolean) -> Unit
) : RecyclerView.Adapter<TimetableAdapter.TimetableViewHolder>() {

    private val items = mutableListOf<TimetableEntry>()
    private var completedIds = emptySet<Long>()

    fun submitList(entries: List<TimetableEntry>, completedForDay: Set<Long>) {
        items.clear()
        items.addAll(entries.sortedBy { it.timeMinutes })
        completedIds = completedForDay
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimetableViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_timetable_entry, parent, false)
        return TimetableViewHolder(view)
    }

    override fun onBindViewHolder(holder: TimetableViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class TimetableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvSubtitle: TextView = itemView.findViewById(R.id.tvSubtitle)
        private val checkDone: MaterialCheckBox = itemView.findViewById(R.id.checkDone)
        private val btnEdit: ImageButton = itemView.findViewById(R.id.btnEdit)
        private val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)

        fun bind(item: TimetableEntry) {
            tvTitle.text = item.title
            tvSubtitle.text = "${toTimeLabel(item.timeMinutes)}"

            checkDone.setOnCheckedChangeListener(null)
            checkDone.isChecked = completedIds.contains(item.id)
            checkDone.setOnCheckedChangeListener { _, checked ->
                onDoneToggled(item, checked)
            }

            val iconColor = ColorStateList.valueOf(
                ContextCompat.getColor(itemView.context, R.color.ink_secondary)
            )
            val iconSizePx = (20 * itemView.resources.displayMetrics.density).toInt()
            btnEdit.setImageDrawable(
                IconicsDrawable(itemView.context, CommunityMaterial.Icon3.cmd_pencil_outline).apply {
                    colorList = iconColor
                    sizeXPx = iconSizePx
                    sizeYPx = iconSizePx
                }
            )
            btnDelete.setImageDrawable(
                IconicsDrawable(itemView.context, CommunityMaterial.Icon3.cmd_trash_can_outline)
                    .apply {
                        colorList = iconColor
                        sizeXPx = iconSizePx
                        sizeYPx = iconSizePx
                    }
            )

            btnEdit.setOnClickListener { onEdit(item) }
            btnDelete.setOnClickListener { onDelete(item) }
        }
    }
}
